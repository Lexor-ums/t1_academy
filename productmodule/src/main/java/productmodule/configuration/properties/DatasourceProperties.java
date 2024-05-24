package productmodule.configuration.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author YStepanov
 */
@ConfigurationProperties("spring.datasource")
public record DatasourceProperties(
        String url,
        String username,
        String password,
        String driverClassName
) {
}
