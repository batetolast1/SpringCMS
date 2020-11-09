package io.github.batetolast1.springcms.config;

import io.github.batetolast1.springcms.converter.AuthorDtoConverter;
import io.github.batetolast1.springcms.converter.CategoryDtoConverter;
import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.model.Article;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "io.github.batetolast1")
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Collections.singletonList(new MediaType("text", "plain", StandardCharsets.UTF_8)));
        converters.add(stringConverter);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean entityManagerFactoryBean = new LocalEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName("springCmsPersistenceUnit");
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        TypeMap<Article, ArticleDto> articleToArticleDtoTypeMap = modelMapper.createTypeMap(Article.class, ArticleDto.class);
        articleToArticleDtoTypeMap.addMapping(Article::getAuthor, ArticleDto::setAuthorDto);
        articleToArticleDtoTypeMap.addMapping(Article::getCategorySet, ArticleDto::setCategoryDtos);

        TypeMap<ArticleDto, Article> articleDtoToArticleTypeMap = modelMapper.createTypeMap(ArticleDto.class, Article.class);
        articleDtoToArticleTypeMap.addMapping(ArticleDto::getAuthorDto, Article::setAuthor);
        articleDtoToArticleTypeMap.addMapping(ArticleDto::getCategoryDtos, Article::setCategorySet);

        return modelMapper;
    }

    @Bean
    public AuthorDtoConverter authorDtoConverter() {
        return new AuthorDtoConverter();
    }

    @Bean
    public CategoryDtoConverter categoryDtoConverter() {
        return new CategoryDtoConverter();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(authorDtoConverter());
        registry.addConverter(categoryDtoConverter());
    }
}
