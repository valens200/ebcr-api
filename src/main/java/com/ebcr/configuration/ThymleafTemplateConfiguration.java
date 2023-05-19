package com.ebcr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.charset.StandardCharsets;

@Configuration
public class ThymleafTemplateConfiguration {
  @Bean
  public SpringTemplateEngine springTemplateEngine(){
    SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
    springTemplateEngine.addTemplateResolver(htmlTemplateResolver());
    return springTemplateEngine;
  }

  public ClassLoaderTemplateResolver htmlTemplateResolver(){
       ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
       classLoaderTemplateResolver.setPrefix("templates/");
       classLoaderTemplateResolver.setSuffix(".html");
       classLoaderTemplateResolver.setCacheable(false);
       classLoaderTemplateResolver.setTemplateMode(TemplateMode.HTML);
       classLoaderTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
       return classLoaderTemplateResolver;
  }
}
