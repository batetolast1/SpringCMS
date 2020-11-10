package io.github.batetolast1.springcms;

import io.github.batetolast1.springcms.config.AppConfig;
import io.github.batetolast1.springcms.config.BeansConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {

        // creating app context
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        // registering config classes
        context.register(AppConfig.class);
        context.register(BeansConfig.class);

        // creating servlet context
        context.setServletContext(servletContext);

        // creating DispatcherServlet
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.setLoadOnStartup(1);

        // default url mapping
        servlet.addMapping("/");

        // setting UTF-8 encoding
        FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        filter.setInitParameter("encoding", "UTF-8");
        filter.setInitParameter("forceEncoding", "true");
        filter.addMappingForUrlPatterns(null, true, "/*");
    }
}
