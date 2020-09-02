package com.lot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lot.controller"))
                .paths(PathSelectors.any()).build();
    }

    @Bean
    public Docket lotWeiXinApi() {
        return new Docket(DocumentationType.SWAGGER_2) //
                .groupName("weixin-api") //
                .apiInfo(new ApiInfoBuilder().title("weixin-api").build()) //
                .directModelSubstitute(Timestamp.class, String.class) //
                .select() //
                .apis(RequestHandlerSelectors.basePackage("com.lot.wechatApi"))
                //.paths(PathSelectors.ant("/lot/weixin/**")) ///
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("好好测试，不留bug!")
                .description("欢迎来到本api测试文档")
                .termsOfServiceUrl("http://swagger.io/")
                .contact(new Contact("janis", "127.0.0.1", "zelTime@163.com"))
                .version("1.0")
                .build();

    }

}
