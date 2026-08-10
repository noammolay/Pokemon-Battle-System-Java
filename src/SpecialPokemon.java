package pokemon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SpecialPokemon extends Pokemon {
    private int specialAttack;

    // constructor.
    public SpecialPokemon(String name, Type type, int attack, int defense, int health, int specialAttack) throws PokemonException {
        super(name, type, attack, defense, health);
        this.specialAttack = specialAttack;
    }
    // constructor: reads the given file and assign each field of the special pokemon.
    public SpecialPokemon(File file) throws PokemonException {
        try {
            Scanner scanner = new Scanner(file);
            this.setName(scanner.nextLine().split(": ")[1]);
            this.setType(new Type(scanner.nextLine().split(": ")[1]));
            this.setAttack(Integer.parseInt(scanner.nextLine().split(": ")[1]));
            this.setDefense(Integer.parseInt(scanner.nextLine().split(": ")[1]));
            this.setHealth(Integer.parseInt(scanner.nextLine().split(": ")[1]));
            this.specialAttack = Integer.parseInt(scanner.nextLine().split(": ")[1]);
        } catch (IOException ignore){
    }
}

    public int getSpecialAttack() {
        return this.specialAttack;
    }

    // override calcAttack() for specialpokemon.
    public int calcAttack() {
        return this.specialAttack * super.calcAttack();
    }

    // checks if two SpecialPokemons are equal.
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SpecialPokemon other = (SpecialPokemon) obj;
        if (super.equals(other) && this.specialAttack == other.specialAttack) {
            return true;
        }
        return false;
    }

    // override clone() method for specialpokemon.
    @Override
    public SpecialPokemon clone() {
        try {
            return new SpecialPokemon(super.getName(), super.getType(), super.getAttack(), super.getDefense(), super.getHealth(), this.specialAttack);
        } catch (PokemonException ignore) {
            return null;
        }
    }
    // writes the file.
    public void saveToFile(File file) {
        try (FileWriter outputFile = new FileWriter(file)) {
            outputFile.write("Name: " + this.getName() + "\n");
            outputFile.write("Type: " + this.getType().getType() + "\n");
            outputFile.write("Attack: " + this.getAttack() + "\n");
            outputFile.write("Defense: " + this.getDefense() + "\n");
            outputFile.write("Health: " + this.getHealth() + "\n");
            outputFile.write("Special Attack: " + this.getSpecialAttack() + "\n");
        } catch (IOException ignore) {
        }
    }
}
