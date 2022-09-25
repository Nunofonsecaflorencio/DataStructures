package datastructures;

import interfaces.BinarySearchTree;
import nodes.BSTNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Nuno Fonseca
 */
public class BinaryTree<T extends Comparable> implements BinarySearchTree<T> {

    protected BSTNode<T> root;

    public BSTNode<T> getRoot() {
        return root;
    }

    public void setRoot(BSTNode<T> root) {
        this.root = root;
    }
    
    
    
    
    /*
        Operations
    */

    protected BSTNode<T> insert(BSTNode<T> root, T key) {

        if (root == null) return new BSTNode<>(key);

        if (key.compareTo(root.getKey()) < 0)
            root.left = insert(root.left, key);

        if (key.compareTo(root.getKey()) > 0)
            root.right = insert(root.right, key);

        else
            return root;


        return root;
    }


    private BSTNode<T> search(BSTNode<T> root, T key) {

        if (root == null || root.getKey().equals(key)) return root;

        if (key.compareTo(root.getKey()) < 0)
            return search(root.left, key);

        return search(root.right, key);
    }

    protected BSTNode<T> delete(BSTNode<T> root, T key) {

        if (root == null) return root;

        if (key.compareTo(root.getKey()) < 0)
            root.left = delete(root.left, key);

        else if (key.compareTo(root.getKey()) > 0)
            root.right = delete(root.right, key);

        else {
            // Case 1 & 2: no child or one child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Case 3: 2 children
            BSTNode<T> sucessor = minimum(root.right);
            root.setKey(sucessor.getKey());
            root.right = delete(root.right, sucessor.getKey());
        }


        return root;
    }

    private int countNodes(BSTNode<T> root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int treeHeight(BSTNode<T> root) {
        if (root == null) return 0;
        return 1 + Math.max(treeHeight(root.left), treeHeight(root.right));
    }


    protected BSTNode<T> minimum(BSTNode<T> root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /*
        Traversals
    */
    public void printPreOrder(BSTNode<T> root) {
        if (root == null) return;

        System.out.println(root.getKey());
        printPreOrder(root.left);
        printPreOrder(root.right);

    }

    public void printInOrder(BSTNode<T> root) {
        if (root == null) return;

        printInOrder(root.left);
        System.out.println(root.getKey());
        printInOrder(root.right);

    }

    public void printPosOrder(BSTNode<T> root) {
        if (root == null) return;

        printPosOrder(root.left);
        printPosOrder(root.right);
        System.out.println(root.getKey());

    }

    public ArrayList<T> preOrderList() {
        ArrayList<T> keys = new ArrayList<>();
        Stack<BSTNode<T>> stack = new Stack<>();
        BSTNode<T> current = root;

        while (!stack.empty() || current != null) {
            if (current != null) {
                keys.add(current.getKey());
                // Going deep do left
                stack.add(current);
                current = current.left;

            } else {
                // In the Leaf
                current = stack.pop();
                current = current.right;
            }
        }


        return keys;
    }

    public ArrayList<T> inOrderList() {
        ArrayList<T> keys = new ArrayList<>();
        Stack<BSTNode<T>> stack = new Stack<>();
        BSTNode<T> current = root;

        while (!stack.empty() || current != null) {
            if (current != null) {
                // Going deep do left
                stack.add(current);
                current = current.left;

            } else {
                // In the Leaf
                current = stack.pop();
                keys.add(current.getKey());
                current = current.right;
            }
        }


        return keys;
    }

    public ArrayList<T> posOrderList() {
        ArrayList<T> keys = new ArrayList<>();
        Stack<BSTNode<T>> mainStack = new Stack<>();
        Stack<BSTNode<T>> rightStack = new Stack<>();
        BSTNode<T> current = root;

        while (!mainStack.empty() || current != null) {
            if (current != null) {

                if (current.right != null)
                    rightStack.add(current.right);

                mainStack.add(current);
                current = current.left;

            } else {
                // In the Leaf
                current = mainStack.peek();
                if (!rightStack.empty() && current.right == rightStack.peek())
                    current = rightStack.pop();
                else {

                    keys.add(current.getKey());
                    mainStack.pop();
                    current = null; // dont go to left again!

                }

            }
        }


        return keys;
    }


//    @Override
//    public String toString() {
//        return inOrderList().toString();
//    }


    /*
        Feeding Interface
    */
    @Override
    public void insert(T key) {
        root = insert(root, key);
    }

    @Override
    public void delete(T key) {
        root = delete(root, key);
    }

    @Override
    public boolean contains(T key) {
        return search(root, key) != null;
    }

    @Override
    public int height() {
        return treeHeight(root);
    }

    @Override
    public int size() {
        return countNodes(root);
    }


}
