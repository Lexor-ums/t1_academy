package productmodule.repository.impl;

import productmodule.mapping.UserMapper;
import productmodule.model.User;
import productmodule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author YStepanov
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private static final String GET_ALL_USERS_QUERY = "SELECT id, username FROM users";
    private static final String GET_USER_BY_ID_QUERY = "SELECT id, username FROM users WHERE id = ?";
    private static final String SAVE_USER_QUERY = "INSERT INTO users VALUES (?,?)";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";

    private final UserMapper userMapper;
    private final DataSource dataSource;
    @Override
    public void deleteUser(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User addUser(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_USER_QUERY);
            statement.setLong(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            List<User> userList = userMapper.mapRow(res, 0);
            if (userList.size() > 1) {
                throw new RuntimeException("Request return more then one row");
            }
            return userList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS_QUERY);
            ResultSet res = statement.executeQuery();
            return userMapper.mapRow(res, 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
