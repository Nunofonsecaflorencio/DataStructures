package testing;

import datastructures.AVLTree;

import java.util.ArrayList;

/**
 * @author Nuno Fonseca
 */
public class BSTTest {

    static void printStats(AVLTree tree) {
        ArrayList<Integer> balances = tree.balanceFactorsList();
        System.out.println();
        System.out.println("[PREORDER]\t" + tree.preOrderList().toString());
        System.out.println("[INORDER]\t" + tree.inOrderList().toString());
        System.out.println("[POSORDER]\t" + tree.posOrderList().toString());
        System.out.println("[BALANCES]\t" + balances.toString());
        System.out.println("[SIZE]\t\t" + tree.size());
        System.out.println("[HEIGHT]\t" + tree.height());
        System.out.println();
    }

    public static void main(String[] args) {

        AVLTree<Integer> tree = new AVLTree<>();

        for (int i : new int[]{9, 5, 10, 0, 6, 11, -1, 1, 2}) {
            tree.insert(i);
        }

        printStats(tree);
        tree.delete(10);
        printStats(tree);
    }
}
