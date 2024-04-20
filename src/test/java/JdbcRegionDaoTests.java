import org.junit.Before;
import org.pokemon.Dao.JdbcRegionDao;
import org.pokemon.Model.Region;

public class JdbcRegionDaoTests extends BaseDaoTests{

    private JdbcRegionDao jdbcRegionDao;

    private final Region REGION_1 = new Region();

    @Before
    private void setup(){
        jdbcRegionDao = new JdbcRegionDao(dataSource);
    }
}
