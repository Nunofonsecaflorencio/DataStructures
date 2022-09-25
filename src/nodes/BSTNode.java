package nodes;

/**
 * @author Nuno Fonseca
 */
public class BSTNode<T> {
    private T key;
    private int height; // only for AVL Tree
    public BSTNode<T> left, right;

    public BSTNode(T key) {
        this.key = key;
        height = 1;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
