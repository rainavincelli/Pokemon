package org.pokemon.View;

import org.pokemon.Dao.*;
import org.pokemon.Model.Pokemon;
import org.pokemon.Model.Region;
import org.pokemon.connection.DbConnection;

import java.util.Scanner;

public class PokemonApp {
    public static void main(String[] args) {
        PokemonDao pokemonDao = new JdbcPokemonDao(DbConnection.get());
        TypeDao typeDao = new JdbcTypeDao(DbConnection.get());
        RegionDao regionDao = new JdbcRegionDao(DbConnection.get());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to my pokemon project!");
        System.out.println(pokemonDao.getPokemonById(56));
        System.out.println(typeDao.getTypeByPokemonId(56));



    }
}