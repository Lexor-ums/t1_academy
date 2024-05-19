package lesson_four;

import lesson_four.model.User;
import lesson_four.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.sql.SQLException;

/**
 * @author YStepanov
 */
@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        UserService userService = applicationContext.getBean(UserService.class);
        User userOne = User.builder()
                .id(1L)
                .username("Admin")
                .build();
        Assert.isTrue(userOne.equals(userService.addUser(userOne)), "Cant save entity");

        User userTwo = User.builder()
                .id(2L)
                .username("User")
                .build();

        userService.addUser(userTwo);

        Assert.isTrue(userService.getAllUsers().size() == 2, "entities size is wrong");

        Assert.isTrue(userService.getUser(userTwo.getId()).equals(userTwo), "Cant get user");

        userService.deleteUser(userOne.getId());

        Assert.isTrue(userService.getAllUsers().size() == 1, "Cant delete user" );
    }
}
