package productmodule.repository;


import productmodule.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author YStepanov
 */
public interface UserRepository {
    void deleteUser(Long id) throws SQLException;

    User addUser(User user);

    User getUser(Long id);

    List<User> getAllUsers();
}
