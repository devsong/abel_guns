package com.stylefeng.guns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.stylefeng.guns.config.properties.GunsProperties;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class GunsWebApplication implements WebMvcConfigurer {
	protected final static Logger logger = LoggerFactory.getLogger(GunsWebApplication.class);
	@Autowired
	private GunsProperties gunsProperties;

	/**
	 * swagger的支持
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (gunsProperties.getSwaggerOpen()) {
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(GunsWebApplication.class, args);
		logger.info("GunsWebApplication is success!");
	}
}
