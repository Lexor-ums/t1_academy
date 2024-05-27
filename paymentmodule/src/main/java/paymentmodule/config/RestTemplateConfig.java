package paymentmodule.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import paymentmodule.config.properties.ProductServiceProperties;

import java.time.Duration;

/**
 * @author YStepanov
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate productIntegrationRestTemplate(ProductServiceProperties properties) {
        return new RestTemplateBuilder()
                .rootUri(properties.baseUrl())
                .setConnectTimeout(Duration.ofSeconds(properties.connectionTimeout()))
                .setReadTimeout(Duration.ofSeconds(properties.readTimeout()))
                .build();
    }
}
