package pokemon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyForest<T> implements Serializable {
    private List<MyTree<T>> trees;

    //regular constructor.
    public MyForest() {
        this.trees = new ArrayList<>();
    }
    // deep copy constructor.
    public MyForest(MyForest<T> other) {
        this.trees = new ArrayList<>();
        for (MyTree<T> tree : other.trees) {
            this.trees.add(new MyTree<>(tree));
        }
    }

    // add element as a root of a new tree.
    public boolean add(T element) {
        if (!exists(element)) {
            this.trees.add(new MyTree<>(element));
            return true;
        }
        return false;
    }
    // add element as a child of parent. if parent null create new tree.
    public boolean add(T parent, T element) {
        if (parent == null) {
            trees.add(new MyTree<>(element));
            return true;
        }
        for (MyTree<T> tree : trees) {
            if (tree.exists(parent)) {
                return tree.add(parent, element);
            }
        }
        return false;
    }
    // remove subtree of element, if exists.
    public boolean remove(T element) {
        for (MyTree<T> tree : trees) {
            if (tree.exists(element)){
                if (element.equals(tree.getData())){
                    trees.remove(tree);
                    return true;
                }
                tree.remove(element);
                return true;
            }
        }
        return false;
    }
    // checks if the two elements have a path between them.
    public boolean areRelated(T element1, T element2) {
        for (MyTree<T> tree : trees) {
            if ((tree.isSuccessorOf(element1, element2)) || (tree.isSuccessorOf(element2, element1))) {
                return true;
            }
        }
        return false;
    }
    // checks if the element exists in the forest.
    public boolean exists(T element) {
        for (MyTree<T> tree : trees) {
            if (tree.exists(element)) {
                return true;
            }
        }
        return false;
    }
    // get the whole tree which the element is in it.
    public MyTree<T> getTree(T element) {
        for (MyTree<T> tree : trees) {
            if (tree.exists(element)) {
                T root = tree.getData(); // get root.
                return tree.get(root);
            }
        }
        return null;
    }
}
