package interfaces;

/**
 * @author Nuno Fonseca
 */
public interface BinarySearchTree<T> {
    void insert(T key);

    void delete(T key);

    boolean contains(T key);

    int height();

    int size();
}
