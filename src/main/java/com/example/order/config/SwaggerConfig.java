package com.example.order.config;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi   // 开启Swagger自定义接口文档
 @Configuration   // 相当于Spring配置中的<beans>
 public class SwaggerConfig {
 @Bean   // 相当于Spring 配置中的<bean>
 public Docket createRestApi() {
     return new Docket(DocumentationType.OAS_30)
                 .apiInfo(apiInfo())
                 .select()
                 .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                 .paths(PathSelectors.any())
                 .build();
     }
     // API基础信息定义（就是更新Swagger默认页面上的信息）
     private ApiInfo apiInfo() {
         return new ApiInfoBuilder()
                 .title("Swagger3接口文档测试")
                 .description("文档描述：更多问题，请联系开发者")
                 //.contact(new Contact("xsge123", "作者网站(url)", "1511868921@qq.com"))
                 .version("1.0")
                 .build();
     }
//    paramType：表示参数放在哪个地方
//    header-->请求参数的获取：@RequestHeader(代码中接收注解)
//    query-->请求参数的获取：@RequestParam(代码中接收注解)
//    path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
//    body-->请求参数的获取：@RequestBody(代码中接收注解)
 }
