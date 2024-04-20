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
    public void getPokemonById_with_valid_id_returns_pokemon(){
        Pokemon pokemon = jdbcPokemonDao.getPokemonById(1);
        assertPokemonMatch(POKEMON_1, pokemon);
        Assert.assertNotNull(pokemon);

        pokemon = jdbcPokemonDao.getPokemonById(5);
        assertPokemonMatch(POKEMON_5, pokemon);
        Assert.assertNotNull(pokemon);
    }
    @Test
    public void getPokemonById_with_invalid_id_returns_null(){
        Pokemon pokemon = jdbcPokemonDao.getPokemonById(8);
        Assert.assertNull(pokemon);
    }
    @Test
    public void GetPokemonByName_with_valid_names_returns_Pokemon(){
        Pokemon pokemon  = jdbcPokemonDao.getPokemonByName("Bulbasaur", false);
        Assert.assertNotNull(pokemon);
        assertPokemonMatch(POKEMON_1, pokemon);

        pokemon = jdbcPokemonDao.getPokemonByName("VENUSAUR", true);
        Assert.assertNotNull(pokemon);
        assertPokemonMatch(POKEMON_3, pokemon);

        pokemon = jdbcPokemonDao.getPokemonByName("ChArMaNdER", true);
        Assert.assertNotNull(pokemon);
        assertPokemonMatch(POKEMON_4, pokemon);
    }
    @Test
    public void getPokemonByName_with_invalid_name_returns_null(){
        Pokemon pokemon = jdbcPokemonDao.getPokemonByName("Bulbbasaur", true);
        Assert.assertNull(pokemon);
        Assert.assertNotEquals(POKEMON_1, pokemon);

        pokemon = jdbcPokemonDao.getPokemonByName("NOTRIGHTNAME", true);
        Assert.assertNull(pokemon);
    }

    @Test
    public void getPokemonByType_with_valid_type_returns_pokemon(){
        List<Pokemon> pokemonByType  = jdbcPokemonDao.getPokemonByType("Fire", false);
        Assert.assertNotNull(pokemonByType);
        Assert.assertEquals(2, pokemonByType.size());

        pokemonByType  = jdbcPokemonDao.getPokemonByType("POIsoN", true);
        Assert.assertNotNull(pokemonByType);
        Assert.assertEquals(2, pokemonByType.size());

    }

    @Test
    public void getPokemonByType_with_invalid_type_returns_empty_list(){
        List<Pokemon> pokemonByType  = jdbcPokemonDao.getPokemonByType("Seven^^", true);
        Assert.assertEquals(0, pokemonByType.size());

        pokemonByType  = jdbcPokemonDao.getPokemonByType("Charmander", true);
        Assert.assertEquals(0, pokemonByType.size());
    }

    @Test
    public void GetPokemonByTypeId_with_correct_type_id_returns_pokemon_list(){
        List<Pokemon> pokemonList = jdbcPokemonDao.getPokemonByTypeId(2);
        Assert.assertEquals(2, pokemonList.size());
        Assert.assertNotNull(pokemonList);

        pokemonList = jdbcPokemonDao.getPokemonByTypeId(5);
        Assert.assertEquals(2, pokemonList.size());
        Assert.assertNotNull(pokemonList);
    }

    public void GetPokemonByTypeId_with_incorrect_type_id_returns_null(){
        List<Pokemon> pokemonList = jdbcPokemonDao.getPokemonByTypeId(19);
        Assert.assertNotNull(pokemonList);
        Assert.assertEquals(0, pokemonList.size());

        pokemonList = jdbcPokemonDao.getPokemonByTypeId(209);
        Assert.assertNotNull(pokemonList);
        Assert.assertEquals(0, pokemonList.size());
    }
    @Test
    public void getPokemonByRegion_with_valid_region_returns_list_pokemon(){
        List<Pokemon> pokemonList = jdbcPokemonDao.getPokemonByRegion("Kanto", false);
        Assert.assertEquals(4, pokemonList.size());

        pokemonList = jdbcPokemonDao.getPokemonByRegion("kANTO", true);
        Assert.assertEquals(4, pokemonList.size());
    }

    @Test
    public void getPokemonByRegion_with_invalid_region_returns_empty_list(){
        List<Pokemon> pokemonList = jdbcPokemonDao.getPokemonByRegion("Florida", true);
        Assert.assertEquals(0, pokemonList.size());

        pokemonList = jdbcPokemonDao.getPokemonByRegion("Bulbasaur", true);
        Assert.assertEquals(0, pokemonList.size());
    }

    @Test
    public void getPokemonByRegionId_with_valid_id_returns_list_pokemon(){
        List<Pokemon> pokemonList = jdbcPokemonDao.getPokemonByRegionId(1);
        Assert.assertEquals(4, pokemonList.size());
        Assert.assertNotEquals(0,pokemonList.size());
    }

    @Test
    public void getPokemonByRegionId_with_invalid_id_returns_empty_list(){
        List<Pokemon> pokemonList = jdbcPokemonDao.getPokemonByRegionId(89);
        Assert.assertEquals(0, pokemonList.size());
        Assert.assertNotEquals(4,pokemonList.size());
    }

    @Test
    public void getPokemon_returns_all_pokemon(){
        List<Pokemon> pokemonList = jdbcPokemonDao.getAllPokemon();

        Assert.assertEquals(4, pokemonList.size());
        Assert.assertNotNull(pokemonList);
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
