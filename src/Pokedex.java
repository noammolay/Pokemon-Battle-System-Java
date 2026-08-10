package pokemon;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class Pokedex implements Iterable<Pokemon>, Serializable {
    private HashMap<String, Pokemon> new_pokedex_lst;
    private MyForest<String> forest;

    // default constructor.
    public Pokedex() {
        this.new_pokedex_lst = new HashMap<String, Pokemon>();
        this.forest = new MyForest<>();
    }

    // copy constructor.
    public Pokedex(Pokedex other) {
        this.new_pokedex_lst = new HashMap<String, Pokemon>();
        for (Pokemon poke : other.new_pokedex_lst.values()) {
            this.new_pokedex_lst.put(poke.getName(), poke.clone());
        }
        this.forest = new MyForest<>(other.forest);
    }

    // returns a pokemon (if exist) according to name.
    public Pokemon getByName(String name) throws PokemonException {
        for (Pokemon poke : this.new_pokedex_lst.values()) {
            if (poke.getName().equals(name)) {
                return poke;
            }
        }
        throw new PokemonException(name);
    }

    // add pokemon to the poke-dex if it's not already there, and create a new tree as root in the forest.
    public void addPokemon(Pokemon toAdd) throws PokemonException {
        if (toAdd != null) {
            for (Pokemon poke : this.new_pokedex_lst.values()) {
                if (toAdd.getName().equals(poke.getName())) {
                    throw new PokemonException(toAdd.getName());
                }
            }
            this.new_pokedex_lst.put(toAdd.getName(), toAdd.clone());
        }
        this.forest.add(toAdd.getName());
    }
    // add pokemon to the poke-dex if it's not already there, and add 'toAdd' as child to parent in forest.
    public void addPokemon(Pokemon toAdd, String parent) throws PokemonException{
        if (parent == null || !new_pokedex_lst.containsKey(parent)) {
            throw new PokemonException(parent);
        }
        if (toAdd != null) {
            for (Pokemon poke : this.new_pokedex_lst.values()) {
                if (toAdd.getName().equals(poke.getName())) {
                    throw new PokemonException(toAdd.getName());
                }
            }
            this.new_pokedex_lst.put(toAdd.getName(), toAdd.clone());
        }
        this.forest.add(parent,toAdd.getName());
    }

    // get the whole tree which the name is in it.
    public MyTree<String> getEvolutionTreeByName(String name){
        return this.forest.getTree(name);
    }

    // iterate over the pokedex.
    @Override
    public Iterator<Pokemon> iterator() {
        return this.new_pokedex_lst.values().iterator();
    }
}



