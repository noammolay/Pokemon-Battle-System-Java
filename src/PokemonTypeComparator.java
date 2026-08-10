package pokemon;
import java.util.Comparator;

public class PokemonTypeComparator implements Comparator<Pokemon> {
    @Override
    public int compare(Pokemon poke1, Pokemon poke2) {
        if ((poke1 == null) && (poke2 == null)) { //both are nulls.
            return 0;
        }
        if (poke1 == null) { // poke2 is bigger
            return -1;
        }
        if (poke2 == null) { // poke1 is bigger
            return 1;
        }
        return poke1.getType().compareTo(poke2.getType()); // compare types using the Type class's compareTo method.
    }
}


