package senatskov.ilya.frontend;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
@ImportResource("classpath:persistence.xml")
public class SimpleDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean insertNewUser(String login, String password) {
        int count = jdbcTemplate.update("insert into users(login, password) "+
                            "SELECT ?, ? WHERE NOT EXISTS ( "+
                                    "SELECT id FROM users WHERE login = ?)",
                             login, password, login);
        return count == 1 ? true: false;
    }
    
    
    public BigDecimal getUserBalance(String login, String password) throws UserNotExistsException, PasswordNotCorrectException  {
        final class Inner {
            BigDecimal balance;
            String password;
        }
        try {
          Inner result = jdbcTemplate.queryForObject("select balance, password from users where login = ?", 
                 new RowMapper<Inner>() {
                     public Inner mapRow(ResultSet rs, int rowNum) throws SQLException {
                         Inner inner = new Inner();
                         inner.balance = rs.getBigDecimal("balance");
                         inner.password = rs.getString("password");
                         return inner;
                     }
                 }, 
                 login);
          if (result.password.equals(password))
              return result.balance;
          else
              throw new PasswordNotCorrectException();
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotExistsException();
        }
    }
}