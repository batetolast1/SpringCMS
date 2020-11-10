package io.github.batetolast1.springcms.config;

import io.github.batetolast1.springcms.dto.ArticleDto;
import io.github.batetolast1.springcms.model.Article;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.persistence.EntityManagerFactory;
import java.util.Locale;

@Configuration
public class BeansConfig {

    // Hibernate

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

    // ModelMapper

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

    // locale settings

    @Bean(name = "localeResolver")
    public LocaleContextResolver getLocaleContextResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pl", "PL"));
        return localeResolver;
    }
}
