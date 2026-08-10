package pokemon;

import java.util.Comparator;

public class PokemonCalcAttackComparator implements Comparator<Pokemon> {
    @Override
    public int compare(Pokemon poke1, Pokemon poke2) {
        if ((poke1 == null) && (poke2 == null)) { // both are nulls.
            return 0;
        }
        if (poke1 == null) { // poke2 is bigger
            return -1;
        }
        if (poke2 == null) { // poke1 is bigger
            return 1;
        }
        // compare their attacks using the calcAttack() method.
        if (poke1.calcAttack() > poke2.calcAttack()) {
            return 1;
        } else if (poke1.calcAttack() < poke2.calcAttack()) {
            return -1;
        } else { // equal
            return 0;
        }
    }
}
