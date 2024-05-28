import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * @author YStepanov
 */
@Configuration
@ComponentScan(basePackages = "productmodule")
//@PropertySources({
//        @PropertySource("classpath:./application.properties"),
//})
public class TestApplication {
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestApplication.class);
    }
}
