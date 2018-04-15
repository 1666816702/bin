package cn.edu.hnie;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootStartApplication extends SpringBootServletInitializer {

	/**
	 * 使用外置的tomact时，该代码屏蔽
	 * 
	 * @param args
	 */
	/*
	 * public static void main(String[] args) {
	 * new SpringApplication(SpringBootStartApplication.class).run(args);
	 * }
	 */

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootStartApplication.class);
	}
}
