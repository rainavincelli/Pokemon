import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pokemon.Dao.JdbcPokemonDao;
import org.pokemon.Model.Pokemon;

import java.util.List;

public class JdbcPokemonDaoTests extends BaseDaoTests {
    private JdbcPokemonDao jdbcPokemonDao;

    private final Pokemon POKEMON_1 = new Pokemon(1, 1, "Bulbasaur", 45, 49, 49, 65, 65, 45, 318);
    private final Pokemon POKEMON_3 = new Pokemon(3, 1, "Venusaur", 80,82,83,100,100,80,525);
    private final Pokemon POKEMON_4 = new Pokemon(4, 1, "Charmander", 39,52,43,60,50,65,309);
    private final Pokemon POKEMON_5 = new Pokemon(5, 1, "Charmeleon", 58, 64, 58, 80, 65, 80, 405);
    @Before
    public void setup(){
        jdbcPokemonDao = new JdbcPokemonDao(dataSource);
    }

    @Test
    public void getPokemon_returns_all_pokemon(){
//        List<Pokemon> pokemonList = jdbcPokemonDao.getPokemon();

//        Assert.assertEquals(4, pokemonList.size());
//        Assert.assertNotNull(pokemonList);
    }

    public void assertPokemonMatch(Pokemon expected, Pokemon actual){
        Assert.assertEquals(expected.getPokemonId(), actual.getPokemonId());
        Assert.assertEquals(expected.getRegionId(), actual.getRegionId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getAttack(), actual.getAttack());
        Assert.assertEquals(expected.getDefense(), actual.getDefense());
        Assert.assertEquals(expected.getSpAttack(), actual.getSpAttack());
        Assert.assertEquals(expected.getSpDefense(), actual.getSpDefense());
        Assert.assertEquals(expected.getSpeed(), actual.getSpeed());
        Assert.assertEquals(expected.getTotal(), actual.getTotal());
    }

}
