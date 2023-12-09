package synApps.refit.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import synApps.refit.ReFitApplication;

@Configuration
@EnableFeignClients(basePackageClasses = ReFitApplication.class)
public class FeignClientConfig {
}
