package PetProject.GenealogyProject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.Arrays;

@SpringBootApplication
public class GenealogyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenealogyProjectApplication.class, args);

	}
//	@Bean
//	public FilterRegistrationBean hiddenHttpMethodFilter() {
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new HiddenHttpMethodFilter());
//		filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
//		return filterRegistrationBean;
//	}
}
