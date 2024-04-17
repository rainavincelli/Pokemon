import org.junit.Before;
import org.pokemon.Dao.JdbcRegionDao;

public class JdbcRegionDaoTests extends BaseDaoTests{

    private JdbcRegionDao jdbcRegionDao;

    @Before
    private void setup(){
        jdbcRegionDao = new JdbcRegionDao(dataSource);
    }
}
