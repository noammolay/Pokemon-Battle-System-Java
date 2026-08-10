package pokemon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyTree<T> implements Serializable {
    private T node;
    private List<MyTree<T>> children;

    //regular constructor.
    public MyTree(T element) {
        this.node = element;
        this.children = new ArrayList<>();
    }
    // deep copy constructor.
    public MyTree(MyTree<T> other) {
        this.node = other.node; // אם T הוא immutable. אחרת clone/העתק
        this.children = new ArrayList<>();
        for (MyTree<T> child : other.children) {
            this.children.add(new MyTree<>(child));
        }
    }
    // checks if the node exists, and if not root, then remove it and subtree.
    public boolean remove(T element) throws IllegalArgumentException {
        if (element.equals(this.node)) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < children.size(); i++) { // check for the root's children.
            if (children.get(i).node.equals(element)) {
                children.remove(i);
                return true;
            }
        }
        for (MyTree<T> child : children) { // check recursively for each child's children.
            if (child.remove(element)) {
                return true;
            }
        }
        return false;

    }

    // get the subtree of element.
    public MyTree<T> get(T element) {
        if (this.node.equals(element)) { // if the element is the root, return the tree.
            return this;
        }
        for (MyTree<T> child : children) {
            MyTree<T> result = child.get(element);
            if (result != null) { // stop condition, if we reached the leaves.
                return result;
            }
        }
        return null; // does not exist.

    }
    // checks if element doesn't exist yet, and the parent is, then add the element to be the parent's child.
    public boolean add(T parent, T element) {
        if (!this.exists(parent)) { // parent does not exist.
            return false;
        }
        if (this.exists(element)) { // child already exist.
            return false;
        }
        MyTree<T> p = get(parent); // get the subtree of parent.
        MyTree<T> newchild = new MyTree<>(element);
        p.children.add(newchild);
        return true;
    }

    // checks if the element is a node in the tree.
    public boolean exists(T element) {
        if (this.node.equals(element)) { // checks if the element is the root.
            return true;
        }
        for (MyTree<T> child : children) { // check for the child's children.
            if (child.exists(element)) {
                return true;
            }
        }
        return false;
    }

    // checks of the child is in the parent's tree.
    public boolean isSuccessorOf(T child, T parent) {
        if (child.equals(parent)){
            return false;
        }
        if (!exists(parent)){
            return false;
        }
        MyTree<T> p = get(parent); // get the subtree of parent.
        return p.exists(child); // check if the child exist in the parent's subtree.
    }

    public boolean isPredecessorOf(T parent, T child) {
        if (child.equals(parent)){
            return false;
        }
        if (!exists(parent)){
            return false;
        }
        MyTree<T> p = get(parent); // get the subtree of parent.
        return p.exists(child); // check if the child exist in the parent's subtree.
    }

    // counts the number of nodes of the tree.
    public int size() {
        int count = 1;
        for (MyTree<T> child : children) {
            count += child.size(); // recursive call to the child's children.
        }
        return count;
    }

    public T getData() {
        return this.node;

    }
}
