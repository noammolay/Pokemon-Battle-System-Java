package pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Assignment7Tests {
    private MyForest<String> forest;
    private Pokedex pokedex;

    @BeforeEach
    public void setUp() {
        forest = new MyForest<>();
        pokedex = new Pokedex();
    }

    @Test
    public void testAddRootAndChild() {
        assertTrue(forest.add("A"));
        assertTrue(forest.add("A", "B"));
        assertTrue(forest.exists("A"));
        assertTrue(forest.exists("B"));
    }

    @Test
    public void testAddMultipleTreesAndRelation() {
        assertTrue(forest.add("A"));
        assertTrue(forest.add("C"));
        assertTrue(forest.add("A", "B"));
        assertTrue(forest.add("C", "D"));
        assertFalse(forest.areRelated("B", "D")); // in different trees
    }

    @Test
    public void testAddDuplicateRootFails() {
        assertTrue(forest.add("A"));
        assertFalse(forest.add("A")); // Duplicate root
    }

    @Test
    public void testAddChildWithNonexistentParentFails() {
        assertFalse(forest.add("A", "B"));
    }

    @Test
    public void testAddChildEqualToParentFails() {
        assertTrue(forest.add("A"));
        assertFalse(forest.add("A", "A"));
    }

    @Test
    public void testRemoveRoot() {
        forest.add("A");
        assertTrue(forest.remove("A"));
        assertFalse(forest.exists("A"));
    }

    @Test
    public void testRemoveLeafNode() {
        forest.add("A");
        forest.add("A", "B");
        assertTrue(forest.remove("B"));
        assertTrue(forest.exists("A"));
        assertFalse(forest.exists("B"));
    }

    @Test
    public void testRemoveIntermediateNode() {
        forest.add("A");
        forest.add("A", "B");
        forest.add("B", "C");
        assertTrue(forest.remove("B"));
        assertTrue(forest.exists("A"));
        assertFalse(forest.exists("B"));
        assertFalse(forest.exists("C")); // C removed with B
    }

    @Test
    public void testGetTreeReturnsNullIfNotFound() {
        assertNull(forest.getTree("A"));
    }

    @Test
    public void testAddElementWithNullParentActsAsRoot() {
        assertTrue(forest.add(null, "Snorlax"));
        assertTrue(forest.exists("Snorlax"));
    }

    @Test
    public void testAddPokemonAndRetrieveEvolutionTree() throws PokemonException {
        Pokemon bulbasaur = new Pokemon("Bulbasaur", new Type("Grass"), 50, 40, 30);
        pokedex.addPokemon(bulbasaur);
        assertNotNull(pokedex.getEvolutionTreeByName("Bulbasaur"));
    }

    @Test
    public void testAddDuplicatePokemonThrows() throws PokemonException {
        Pokemon p1 = new Pokemon("Charmander", new Type("Fire"), 10, 10, 10);
        Pokemon p2 = new Pokemon("Charmander", new Type("Fire"), 20, 20, 20);
        pokedex.addPokemon(p1);
        assertThrows(PokemonException.class, () -> pokedex.addPokemon(p2));
    }

    @Test
    public void testAddPokemonWithMissingParentThrows() throws PokemonException {
        Pokemon fakeParent = new Pokemon("Missing", new Type("Ghost"), 1, 1, 1);
        Pokemon p = new Pokemon("Ghastly", new Type("Ghost"), 2, 2, 2);
        assertThrows(PokemonException.class, () -> pokedex.addPokemon(fakeParent, p.getName()));
    }

    @Test
    public void testCopyConstructorDeepCopy() throws PokemonException {
        Pokemon p = new Pokemon("Pikachu", new Type("Electric"), 40, 30, 20);
        pokedex.addPokemon(p);
        Pokedex copy = new Pokedex(pokedex);
        pokedex.addPokemon(new Pokemon("Raichu", new Type("Electric"), 50, 40, 30));
        assertNull(copy.getEvolutionTreeByName("Raichu")); // Raichu only in original
    }

    @Test
    public void testSeparateTreesAreIndependent() {
        forest.add("Root1");
        forest.add("Root2");
        forest.add("Root1", "X");
        forest.add("Root2", "Y");
        assertFalse(forest.areRelated("X", "Y"));
    }

    @Test
    public void testPreventCycle() {
        forest.add("A");
        forest.add("A", "B");
        assertFalse(forest.add("B", "A")); // Should fail to avoid cycle
    }

    @Test
    public void testPokemonEqualityAffectsStructure() throws PokemonException {
        Pokemon a1 = new Pokemon("Treecko", new Type("Grass"), 1, 1, 1);
        Pokemon a2 = new Pokemon("Treecko", new Type("Grass"), 2, 2, 2); // Same name
        pokedex.addPokemon(a1);
        assertThrows(PokemonException.class, () -> pokedex.addPokemon(a2));
    }
}
