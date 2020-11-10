package io.github.batetolast1.springcms.config;

import io.github.batetolast1.springcms.converter.AuthorDtoConverter;
import io.github.batetolast1.springcms.converter.CategoryDtoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "io.github.batetolast1")
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {

    // setting UTF-8 encoding for @ResponseBody
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Collections.singletonList(new MediaType("text", "plain", StandardCharsets.UTF_8)));
        converters.add(stringConverter);
    }

    // configuring ViewResolver for @Controller
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    // forwarding all requests to the default Servlet "/" and allowing to load static resources
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // registering DTO converters
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

    // configuring webjars
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/")
                .resourceChain(false);
        registry.setOrder(1);
    }
}
