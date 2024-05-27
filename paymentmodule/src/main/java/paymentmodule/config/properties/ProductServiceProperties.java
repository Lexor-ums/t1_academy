package paymentmodule.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author YStepanov
 */
@ConfigurationProperties(prefix = "integration.productservice")
public record ProductServiceProperties(
        String baseUrl,
        String paymentPath,
        Integer readTimeout,
        Integer connectionTimeout
) {
}
