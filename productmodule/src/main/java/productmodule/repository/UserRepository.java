package productmodule.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import productmodule.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author YStepanov
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.id = :id")
    void deleteUser(Long id) throws SQLException;

    @Query(value = "SELECT u FROM User u WHERE u.id = :id")
    User getUser(Long id);

}
