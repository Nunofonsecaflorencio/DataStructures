package datastructures;

import nodes.BSTNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Nuno Fonseca
 */
public class AVLTree<T extends Comparable> extends BinaryTree<T> {


    /*
        Special Operations
    */
    protected BSTNode<T> insert(BSTNode<T> root, T key) {

        if (root == null) return new BSTNode<>(key);

        if (key.compareTo(root.getKey()) < 0)
            root.left = insert(root.left, key);

        else if (key.compareTo(root.getKey()) > 0)
            root.right = insert(root.right, key);

        else
            return root;


        // Bottom UP

        updateHeight(root);
        int balance = balanceFactor(root);

        if (balance > 1) { // LL Case

            if (key.compareTo(root.left.getKey()) > 0) // LR
                root.left = rotateLeft(root.left);

            return rotateRight(root);

        } else if (balance < -1) {  // RR

            if (key.compareTo(root.left.getKey()) < 0)  // RL
                root.right = rotateRight(root.right);

            return rotateLeft(root);

        }


        return root;
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

        updateHeight(root);
        int balance = balanceFactor(root);

        if (balance > 1) { // LL Case

            if (balanceFactor(root.left) < 0) // LR
                root.left = rotateLeft(root.left);

            return rotateRight(root);

        } else if (balance < -1) {  // RR

            if (balanceFactor(root.right) > 0)  // RL
                root.right = rotateRight(root.right);

            return rotateLeft(root);

        }


        return root;
    }

    @Override
    public int height() {
        return height(root);
    }
 
    
    /*
        Helper Funcions
    */

    private int height(BSTNode<T> node) {
        if (node == null) return 0;
        return node.getHeight();
    }

    private int balanceFactor(BSTNode<T> node) {
        return height(node.left) - height(node.right);
    }

    private void updateHeight(BSTNode<T> node) {
        int height = 1 + Math.max(height(node.left), height(node.right));
        node.setHeight(height);
    }
    
    /*
        Rotations
    */
    
    /*      
            y                               x
           / \     Right Rotation          /  \
          x   T3   - - - - - - - >        T1   y 
         / \       < - - - - - - -            / \
        T1  T2     Left Rotation            T2  T3
    */

    public BSTNode<T> rotateRight(BSTNode<T> y) {

        BSTNode<T> x = y.left; // (x)
        BSTNode<T> T2 = x.right; // (T2)

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x; // new root
    }

    public BSTNode<T> rotateLeft(BSTNode<T> x) { // (x)

        BSTNode<T> y = x.right; // (y)
        BSTNode<T> T2 = y.left; // (T2)

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y; // new root
    }


    public ArrayList<Integer> balanceFactorsList() {
        ArrayList<Integer> balances = new ArrayList<>();
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

                balances.add(balanceFactor(current));

                current = current.right;
            }
        }

        return balances;

    }

}
