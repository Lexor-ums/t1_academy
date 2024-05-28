package productmodule.mapping;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import productmodule.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author YStepanov
 */
@Component
public class UserMapper implements RowMapper<List<User>> {
    private static final String ID_COLUMN = "id";
    private static final String USERNAME_COLUMN = "username";

    @Override
    public List<User> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            userList.add(User.builder()
                    .id(rs.getLong(ID_COLUMN))
                    .username(rs.getString(USERNAME_COLUMN))
                    .build());
        }
        return userList;
    }
}
