package com.test.config.swagger;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.configuration.ObjectMapperConfigured;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig implements ApplicationListener<ObjectMapperConfigured>{

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket springSwaggerPluginConfiguration(){
        log.info(String.format("String %s",enableSwagger?"enable":"disabled"));
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("account")
                .apiInfo(new ApiInfoBuilder().title("Guest Account Creation")
                .description("Allow to create all types of guest account")
                .license("Dharam")
                .version("1.0")
                .build())
                .useDefaultResponseMessages(false)
                .enable(enableSwagger)
                .select()
                .apis(withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build().pathMapping("/");
    }

    @Override
    public void onApplicationEvent(ObjectMapperConfigured event) {
        event.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }
}
