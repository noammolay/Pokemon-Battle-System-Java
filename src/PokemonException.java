package pokemon;

public class PokemonException extends Exception {
    public PokemonException(String pokemonName) {
        super("bad name: " + pokemonName);
    }

    public PokemonException(int parameter) {
        super("bad parameter: " + parameter);
    }
}
