package synApps.refit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import synApps.refit.global.config.properties.AppProperties;
import synApps.refit.global.config.properties.CorsProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		CorsProperties.class,
		AppProperties.class
})
public class ReFitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReFitApplication.class, args);
	}

}
