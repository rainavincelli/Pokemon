package org.pokemon.Dao;

import org.pokemon.Model.Type;

import java.util.List;

public interface TypeDao {

    Type getTypeById(int id);

    Type getTypeByName(String name, boolean useWildCard);

    List<Type> getTypeByPokemonId(int id);

    List<Type> getTypeByPokemonName(String name, boolean useWildCard);

    List<Type> getTypes();
}
