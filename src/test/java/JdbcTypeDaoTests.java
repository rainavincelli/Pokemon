import org.junit.Before;
import org.pokemon.Dao.JdbcTypeDao;

public class JdbcTypeDaoTests extends BaseDaoTests{

    private JdbcTypeDao jdbcTypeDao;

    @Before
    public void setup(){
        jdbcTypeDao = new JdbcTypeDao(dataSource);
    }
}
