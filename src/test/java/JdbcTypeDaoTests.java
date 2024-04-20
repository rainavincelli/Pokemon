import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pokemon.Dao.JdbcTypeDao;
import org.pokemon.Model.Type;

import java.util.List;

public class JdbcTypeDaoTests extends BaseDaoTests{

    private JdbcTypeDao jdbcTypeDao;

    private final Type TYPE_2 = new Type(2, "Fire", "Water");
    private final Type TYPE_5 = new Type(5, "Grass", "Fire");
    private final Type TYPE_8 = new Type(8, "Poison", "Ground");

    @Before
    public void setup(){
        jdbcTypeDao = new JdbcTypeDao(dataSource);
    }

    @Test
    public void getTypeById_with_valid_id_returns_type(){
        Type type = jdbcTypeDao.getTypeById(2);
        assertTypesMatch(TYPE_2, type);
        Assert.assertNotNull(type);

        type = jdbcTypeDao.getTypeById(5);
        assertTypesMatch(TYPE_5, type);
        Assert.assertNotNull(type);
    }

    @Test
    public void getTypeById_with_invalid_id_returns_null(){
        Type type = jdbcTypeDao.getTypeById(50);
        Assert.assertNull(type);
    }

    @Test
    public void getTypeByName_with_valid_name_returns_type(){
        Type type = jdbcTypeDao.getTypeByName("Fire", false);
        assertTypesMatch(TYPE_2, type);
        Assert.assertNotNull(type);

        type = jdbcTypeDao.getTypeByName("POISON", true);
        assertTypesMatch(TYPE_8, type);
        Assert.assertNotNull(type);
        }

    @Test
    public void getTypeByName_with_invalid_name_returns_null(){
        Type type = jdbcTypeDao.getTypeByName("Charmander", true);
        Assert.assertNull(type);

        type = jdbcTypeDao.getTypeByName("poisonn", true);
        Assert.assertNull(type);
    }

    @Test
    public void getTypeByPokemonId_with_valid_pokemon_id_returns_list_type(){
        List<Type> typeList = jdbcTypeDao.getTypeByPokemonId(1);
        Assert.assertEquals(2, typeList.size());
        Assert.assertEquals(TYPE_5.getTypeName(), typeList.get(0).getTypeName());
        Assert.assertEquals(TYPE_8.getTypeName(), typeList.get(1).getTypeName());

        typeList = jdbcTypeDao.getTypeByPokemonId(4);
        Assert.assertEquals(1, typeList.size());
        Assert.assertEquals(TYPE_2.getTypeName(), typeList.get(0).getTypeName());
    }

    @Test
    public void getTypeByPokemonId_with_invalid_id_returns_empty_type_list(){
        List<Type> typeList = jdbcTypeDao.getTypeByPokemonId(100);
        Assert.assertEquals(0, typeList.size());
    }

    @Test
    public void getTypeByPokemonName_with_valid_pokemon_name_returns_list_type(){
        List <Type> typeList = jdbcTypeDao.getTypeByPokemonName("Charmander", false);
        Assert.assertEquals(1, typeList.size());
        Assert.assertEquals(TYPE_2.getTypeName(), typeList.get(0).getTypeName());

        typeList = jdbcTypeDao.getTypeByPokemonName("VENUSAUR", true);
        Assert.assertEquals(2, typeList.size());
        Assert.assertEquals(TYPE_5.getTypeName(), typeList.get(0).getTypeName());
        Assert.assertEquals(TYPE_8.getTypeName(), typeList.get(1).getTypeName());
    }

    @Test
    public void getTypeByPokemonName_with_invalid_pokemon_name_returns_empty_list(){
        List <Type> typeList = jdbcTypeDao.getTypeByPokemonName("Chillie", true);
        Assert.assertEquals(0, typeList.size());

        typeList = jdbcTypeDao.getTypeByPokemonName("Fire", true);
        Assert.assertEquals(0, typeList.size());
    }

    @Test
    public void getTypes_returns_all_types_list(){
        List <Type> allTypes = jdbcTypeDao.getTypes();
        Assert.assertEquals(18,allTypes.size());
        Assert.assertNotNull(allTypes);
    }

    public void assertTypesMatch(Type expected, Type actual){
        Assert.assertEquals(expected.getTypeId(), actual.getTypeId());
        Assert.assertEquals(expected.getTypeName(), actual.getTypeName());
        Assert.assertEquals(expected.getTypeWeakness(), actual.getTypeWeakness());
    }
}
