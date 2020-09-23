package com.shiant.study.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableWebMvc
@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket buildDocket(){
		//添加header请求头参数
		/*
		 * ParameterBuilder ticketPar = new ParameterBuilder(); List<Parameter> pars =
		 * new ArrayList<Parameter>();
		 * ticketPar.name("ticket").description("user ticket") .modelRef(new
		 * ModelRef("string")).parameterType("header") .required(false).build();
		 * //header中的ticket参数非必填，传空也可以 pars.add(ticketPar.build());
		 * //根据每个方法名也知道当前方法在设置什么参数
		 */    	
    	//.globalOperationParameters(pars)
    	
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(buildApiInf())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.shiant.study.web.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo buildApiInf(){
		return new ApiInfoBuilder()
				.title("留学平台协会端网站相关API")
				.description("文档中定义留学平台协会端所有相关API信息")
				.build();
	}
}
