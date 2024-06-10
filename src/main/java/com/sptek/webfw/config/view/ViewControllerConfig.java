package com.sptek.webfw.config.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class ViewControllerConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
        //swagger.
        viewControllerRegistry.addRedirectViewController("/api/demo-ui.html", "/demo-ui.html");

        viewControllerRegistry.addViewController("/").setViewName("/pages/example/test/none");
        viewControllerRegistry.addViewController("/none").setViewName("/pages/example/test/none");
        viewControllerRegistry.addViewController("/temporaryParkingPageForXXX").setViewName("/pages/example/test/temporaryParkingView");
        viewControllerRegistry.addViewController("/sorry").setViewName("/pages/example/test/temporaryParkingView");
        viewControllerRegistry.addViewController("/fileUpload").setViewName("/pages/example/test/fileUpload");
        viewControllerRegistry.addViewController("/pageWithPost").setViewName("/pages/example/test/pageWithPost");
        viewControllerRegistry.addViewController("/apiWithAjax").setViewName("/pages/example/test/apiWithAjax");

    }

    /*
    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
        //thymeleaf 설정을 application.yml 에서 설정하고 있어서 사용하지 않도록 처리됨

        //jsp로 설정이 필요한 경우
        viewResolverRegistry.jsp("/WEB-INF/views/", ".jsp");
        WebMvcConfigurer.super.configureViewResolvers(viewResolverRegistry);
    }
    */
}
