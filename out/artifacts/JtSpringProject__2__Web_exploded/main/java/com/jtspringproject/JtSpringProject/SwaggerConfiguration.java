package com.jtspringproject.JtSpringProject;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Конфігураційний клас для налаштування Swagger.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Налаштовує обробник мапінгу веб-кінцевих точок для Swagger.
     *
     * @param webEndpointsSupplier       Постачальник веб-кінцевих точок.
     * @param servletEndpointsSupplier   Постачальник сервлет-кінцевих точок.
     * @param controllerEndpointsSupplier Постачальник контролер-кінцевих точок.
     * @param endpointMediaTypes          Типи медіа-вмісту для кінцевих точок.
     * @param corsProperties             Налаштування обмежень CORS.
     * @param webEndpointProperties      Налаштування веб-кінцевих точок.
     * @param environment                Об'єкт, який надає доступ до конфігурації оточення.
     * @return Об'єкт WebMvcEndpointHandlerMapping для Swagger.
     */
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }

    /**
     * Перевіряє, чи слід реєструвати мапінг для лінків.
     *
     * @param webEndpointProperties Налаштування веб-кінцевих точок.
     * @param environment           Об'єкт, який надає доступ до конфігурації оточення.
     * @param basePath              Базовий шлях веб-кінцевих точок.
     * @return true, якщо слід реєструвати мапінг для лінків; false, якщо не слід.
     */
    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }

    /**
     * Налаштовує Docket для Swagger.
     *
     * @return Об'єкт Docket для Swagger.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
