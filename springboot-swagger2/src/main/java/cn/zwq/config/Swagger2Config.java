package cn.zwq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger2Config extends WebMvcConfigurationSupport {

    //api接口包扫描路径
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "cn.zwq.controller";

    //Swagger2接口文档版本
    public static final String VERSION = "1.0.0";

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()) //可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Spring Boot 测试使用 Swagger2 构建RESTful API") //设置文档的标题
                .description("前后端分离接口文档")    //设置文档的描述
                .version(VERSION)   //设置文档的版本
                .termsOfServiceUrl("")  //设置文档的License信息
                .contact(new Contact("xxxq","https://blog.csdn.net/weixin_44176169","Xxx@163.com"))
                .build();
    }

    /**
     * 配置swagger2的静态资源路径
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}