package org.pokemon.Dao;

import org.pokemon.Model.Region;
import org.pokemon.Model.Type;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.pokemon.Exception.DaoException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcTypeDao implements TypeDao{

    private final String TYPE_SELECT = "SELECT type.type_id, type_name, type_weakness FROM type ";
    private JdbcTemplate jdbcTemplate;
    private final String CONNECTION_VIOLATION_MESSAGE = "Cannot connect to database";

    public JdbcTypeDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Type getTypeById(int id) {
        Type type = null;
        String sql = TYPE_SELECT + "WHERE type_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            while (result.next()){
                type = (mapRowToType(result));
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return type;
    }

    public Type getTypeByName(String name, boolean useWildCard) {
        Type type = null;
        String sql = TYPE_SELECT + "WHERE type_name ILIKE ?;";
        if (useWildCard){
            name = "%" + name + "%";
        }
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);

            if (results.next()){
                type = mapRowToType(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return type;
    }

    @Override
    public List<Type> getTypeByPokemonId(int id) {
        List <Type> type = new ArrayList<>();
        String sql = TYPE_SELECT + "JOIN pokemon_type AS pt ON type.type_id = pt.type_id " +
                "WHERE pokemon_id = ?;";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            while (result.next()){
                type.add(mapRowToType(result));
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return type;
    }

    @Override
    public List<Type> getTypeByPokemonName(String name, boolean useWildCard) {
        List <Type> type = new ArrayList<>();
        String sql = TYPE_SELECT + "JOIN pokemon_type AS pt ON type.type_id = pt.type_id " +
                "JOIN pokemon AS p ON pt.pokemon_id = p.pokemon_id " + "WHERE p.name ILIKE ?;";
        if (useWildCard){
            name = "%" + name + "%";
        }
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, name);
            while (result.next()){
                type.add(mapRowToType(result));
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return type;
    }

    @Override
    public List<Type> getTypes() {
        List<Type> types = new ArrayList<>();
        String sql = TYPE_SELECT;
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()){
                types.add(mapRowToType(result));
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return types;
    }

    private Type mapRowToType(SqlRowSet rowSet){
        Type type = new Type();

        type.setTypeId(rowSet.getInt("type_id"));
        type.setTypeName(rowSet.getString("type_name"));
        type.setTypeWeakness(rowSet.getString("type_weakness"));

        return type;
    }
}
