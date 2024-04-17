package org.pokemon.Model;

public class Pokemon {

    private int pokemonId;
    private int regionId;
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private int spAttack;
    private int spDefense;
    private int speed;
    private int total;

    public Pokemon() {
    }

    public Pokemon(int pokemonId, int regionId, String name, int hp, int attack, int defense, int spAttack, int spDefense, int speed, int total) {
        this.pokemonId = pokemonId;
        this.regionId = regionId;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
        this.total = total;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpAttack() {
        return spAttack;
    }

    public void setSpAttack(int spAttack) {
        this.spAttack = spAttack;
    }

    public int getSpDefense() {
        return spDefense;
    }

    public void setSpDefense(int spDefense) {
        this.spDefense = spDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pokedex Number: " + pokemonId + System.lineSeparator() +
                "Pokemon Name: " + name + System.lineSeparator() +
                "Attack: " + attack + System.lineSeparator() +
                "Defense: " + defense + System.lineSeparator() +
                "Special Attack: " + spAttack + System.lineSeparator() +
                "Special Defense: " + spDefense + System.lineSeparator() +
                "Speed: " + speed + System.lineSeparator() +
                "Total: " + total;
    }

}

