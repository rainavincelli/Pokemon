package org.pokemon.Dao;

import org.pokemon.Model.Region;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.pokemon.Exception.DaoException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcRegionDao implements RegionDao{

    private final String REGION_SELECT = "SELECT region.region_id, region_name, map_url FROM region ";

    private JdbcTemplate jdbcTemplate;

    private final String CONNECTION_VIOLATION_MESSAGE = "Cannot connect to database.";

    public JdbcRegionDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Region getRegionById(int id) {
        Region region = null;
        String sql = REGION_SELECT + "WHERE region_id = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

            if (results.next()){
                region = mapRowToRegion(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return region;
    }

    @Override
    public Region getRegionByName(String name, boolean useWildCard) {
        Region region = null;
        String sql = REGION_SELECT + "WHERE region_name = ?";
        if (useWildCard){
            name = "%" + name + "%";
        }

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);

            if (results.next()){
                region = mapRowToRegion(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return region;
    }

    @Override
    public Region getRegionByPokemonName(String name, boolean useWildCard) {
        Region region = null;
        String sql = REGION_SELECT + "JOIN pokemon ON pokemon.region_id = region.region_id " +
                "WHERE pokemon.name = ?";
        if (useWildCard){
            name = "%" + name + "%";
        }
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);

            if (results.next()){
                region = mapRowToRegion(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return region;
    }

    @Override
    public Region getRegionByPokemonId(int id) {
        Region region = null;
        String sql = REGION_SELECT + "WHERE region.region_id = (SELECT pokemon.region_id FROM pokemon" +
                                    "WHERE pokemon_id = ?;)";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if (result.next()){
                region = mapRowToRegion(result);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return region;
    }

    @Override
    public List<Region> getRegions() {
        List<Region> regions = new ArrayList<>();
        String sql = REGION_SELECT;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                regions.add(mapRowToRegion(results));
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException(CONNECTION_VIOLATION_MESSAGE, e);
        }
        return regions;
    }

    private Region mapRowToRegion(SqlRowSet rowSet){
        Region region = new Region();

        region.setRegionId(rowSet.getInt("region_id"));
        region.setRegionName(rowSet.getString("region_name"));
        region.setMapUrl(rowSet.getString("map_url"));

        return region;
    }
}
