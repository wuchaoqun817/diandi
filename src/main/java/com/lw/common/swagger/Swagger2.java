package com.lw.common.swagger;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置
 * @author wuchaoqun
 * @date 2019年1月8日
 * @description
 */
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger")
public class Swagger2 {
	
	@Value("${swagger.host}")
	private String host;
    
    //公用接口
    @Bean
    public Docket createCommonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(commonApiInfo())
                .tags(new Tag("videos","视频相关接口"),getApiTags())
                .host(host)
                .select()             
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo commonApiInfo() {
        return new ApiInfoBuilder()
                .title("公用接口")
                .description("公用接口")
                .version("1.0")
                .build();
    }

    

    
    //tag定义
    private Tag[] getApiTags() {
        Tag[] tags = {
                //公共
                new Tag("videos","课程相关接口"),
            };
        return tags;
    }
}
