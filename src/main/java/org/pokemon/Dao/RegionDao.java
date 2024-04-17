package org.pokemon.Dao;

import org.pokemon.Model.Region;

import java.util.List;

public interface RegionDao {

    Region getRegionById(int id);

    Region getRegionByName(String name, boolean useWildCard);

    Region getRegionByPokemonName(String name, boolean useWildCard);

    Region getRegionByPokemonId(int id);

    List<Region> getRegions();


}
