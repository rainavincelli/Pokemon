package org.pokemon.Dao;

import org.pokemon.Model.Pokemon;

import java.util.List;

public interface PokemonDao {

    Pokemon getPokemonById(int id);

    Pokemon getPokemonByName(String name, boolean useWildCard);

    List<Pokemon> getPokemonByType(String type, boolean useWildCard);

    List<Pokemon> getPokemonByTypeId(int id);

    List<Pokemon> getPokemonByRegion(String region, boolean useWildCard);

    List<Pokemon> getPokemonByRegionId(int id);

    List<Pokemon> getPokemon();
}


