package org.pokemon.Dao;

import org.pokemon.Model.Pokemon;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.pokemon.Exception.DaoException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcPokemonDao implements PokemonDao{

    private JdbcTemplate jdbcTemplate;

    private final String POKEMON_SELECT = "SELECT pokemon.pokemon_id, pokemon.name, hp, attack, defense, " +
            "sp_attack, sp_defense, speed, total, pokemon.region_id FROM pokemon ";

//    private final String DATA_INTEGRITY_ERROR_MESSAGE = "Data integrity violation.";

    private final String CONNECTION_VIOLATION_MESSAGE = "Cannot connect to database.";

    public JdbcPokemonDao(DataSource datasource){
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public Pokemon getPokemonById(int id) {
        Pokemon pokemon = null;
        String sql = POKEMON_SELECT + "WHERE pokemon_id = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

            if (results.next()){
               pokemon = mapRowToPokemon(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return pokemon;
    }

    @Override
    public Pokemon getPokemonByName(String name, boolean useWildCard) {
        Pokemon pokemon = null;
        String sql = POKEMON_SELECT + "WHERE pokemon.name ILIKE ?";
        if (useWildCard){
            name = "%" + name + "%";
        }

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            if (results.next()){
                pokemon = mapRowToPokemon(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return pokemon;
    }

    @Override
    public List<Pokemon> getPokemonByType(String type, boolean useWildCard) {
        List<Pokemon> pokemonList = new ArrayList<>();
        String sql = POKEMON_SELECT + "JOIN pokemon_type AS pt ON pokemon.pokemon_id = pt.pokemon_id " +
                "JOIN type ON pt.type_id = type.type_id WHERE type_name ILIKE ?";
        if (useWildCard){
            type = "%" + type + "%";
        }
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, type);
            while (results.next()){
                pokemonList.add(mapRowToPokemon(results));
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return pokemonList;
    }

    @Override
    public List<Pokemon> getPokemonByTypeId(int id) {
        List<Pokemon> pokemonList = new ArrayList<>();
        String sql = POKEMON_SELECT + "JOIN pokemon_type AS pt ON pokemon.pokemon_id = pt.pokemon_id " +
                "WHERE pt.type_id = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

            while (results.next()){
                pokemonList.add(mapRowToPokemon(results));
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return pokemonList;
    }

    @Override
    public List<Pokemon> getPokemonByRegion(String region, boolean useWildCard) {
        List<Pokemon> pokemonList = new ArrayList<>();
        String sql = POKEMON_SELECT + "JOIN region AS r ON pokemon.region_id = r.region_id " +
                "WHERE region_name ILIKE ?";
        if (useWildCard){
            region = "%" + region + "%";
        }
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, region);
            while (results.next()){
                pokemonList.add(mapRowToPokemon(results));
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return pokemonList;
    }

    @Override
    public List<Pokemon> getPokemonByRegionId(int id) {
        List<Pokemon> pokemonList = new ArrayList<>();
        String sql = POKEMON_SELECT + "JOIN region AS r ON pokemon.region_id = r.region_id " +
                "WHERE pokemon.region_id = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

            while (results.next()){
                pokemonList.add(mapRowToPokemon(results));
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return pokemonList;
    }

    @Override
    public List<Pokemon> getAllPokemon() {
        List<Pokemon> pokemonList = new ArrayList<>();
        String sql = POKEMON_SELECT;

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                pokemonList.add(mapRowToPokemon(results));
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return pokemonList;
    }

    private Pokemon mapRowToPokemon(SqlRowSet rowSet){
        Pokemon pokemon = new Pokemon();

        pokemon.setPokemonId(rowSet.getInt("pokemon_id"));
        pokemon.setName(rowSet.getString("name"));
        pokemon.setHp(rowSet.getInt("hp"));
        pokemon.setAttack(rowSet.getInt("attack"));
        pokemon.setDefense(rowSet.getInt("defense"));
        pokemon.setSpAttack(rowSet.getInt("sp_attack"));
        pokemon.setSpDefense(rowSet.getInt("sp_defense"));
        pokemon.setSpeed(rowSet.getInt("speed"));
        pokemon.setTotal(rowSet.getInt("total"));
        pokemon.setRegionId(rowSet.getInt("region_id"));

        return pokemon;
    }
}
