package pokemon;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String poke_name;
    private int attack_points;
    private int defense_points;
    private int health;
    private Type poke_type;

    // default constructor
    public Pokemon() {
        this.poke_name = "Magikarp";
        this.attack_points = 10;
        this.defense_points = 55;
        this.health = 20;
        this.poke_type = new Type("Water");
    }

    // regular constructor
    public Pokemon(String name, Type type, int attack, int defense, int health) throws PokemonException {
        this.poke_name = name;
        this.attack_points = attack;
        this.defense_points = defense;
        this.health = health;
        this.poke_type = type;
        check_params();
    }

    // deep copy constructor
    public Pokemon(Pokemon other) throws PokemonException {
        this.poke_name = other.poke_name;
        this.attack_points = other.attack_points;
        this.defense_points = other.defense_points;
        this.health = other.health;
        this.poke_type = new Type(other.poke_type.getType());
        check_params();
    }

    // validation of parameters.
    public void check_params() throws PokemonException{
        if (this.attack_points < 0) {
            throw new PokemonException(this.attack_points);
        }
        if (this.defense_points < 0) {
            throw new PokemonException(this.defense_points);
        }
        if (this.health < 1) {
            throw new PokemonException(this.health);
        }
        if (this.poke_name == null) {
            throw new PokemonException("Empty");
        }
        if (this.poke_type == null) {
            this.poke_type = new Type("Water");
        }
    }

    public String getName() {
        return this.poke_name;
    }

    public void setName(String name) throws PokemonException {
        if (name == null) {
            throw new PokemonException("Empty");
        }
        else {
            this.poke_name = name;
        }
    }

    public Type getType() {
        return new Type(this.poke_type.getType());
    }

    public void setType(Type type) {
        if (type == null) {
            this.poke_type = new Type("Water");
        } else {
            this.poke_type = type;
        }
    }

    public int getAttack() {
        return this.attack_points;
    }

    public void setAttack(int attack) throws PokemonException {
        if (attack < 0) {
            throw new PokemonException(attack);
        } else {
            this.attack_points = attack;
        }
    }

    public int getDefense() {
        return this.defense_points;
    }

    public void setDefense(int defense) throws PokemonException{
        if (defense < 0) {
            throw new PokemonException(defense);
        } else {
            this.defense_points = defense;
        }
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) throws PokemonException {
        if (health < 1) {
            throw new PokemonException(health);
        } else {
            this.health = health;
        }
    }

    public int calcAttack() {
        return this.attack_points;
    }
    // checks if two Pokemons are equal according to value.
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Pokemon other = (Pokemon) obj;
        if ((this.poke_name.equals(other.poke_name)) && (this.attack_points == other.attack_points) && (this.defense_points == other.defense_points) && (this.health == other.health) && (this.poke_type.getType().equals(other.poke_type.getType()))) {
            return true;
        }
        return false;
    }
    // create a copy of the pokemon.
    @Override
    public Pokemon clone() {
        try {
            return new Pokemon(this.poke_name, this.poke_type, this.attack_points, this.defense_points, this.health);
        }
        catch (PokemonException ignore) {
            return null;

        }
    }

}
