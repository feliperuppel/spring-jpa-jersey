package br.com.cinq.spring.data.sample.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class DefaultView implements WebMvcConfigurer {

    	@Override
    	public void addViewControllers(ViewControllerRegistry registry) {
    		registry.addViewController("").setViewName("redirect:/");
    		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    	}

}
