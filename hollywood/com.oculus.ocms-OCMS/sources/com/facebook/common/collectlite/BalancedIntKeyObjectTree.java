package com.facebook.common.collectlite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import javax.annotation.Nullable;

public class BalancedIntKeyObjectTree<E> {
    private TreeNode<E> rootNode = null;

    public interface IntKeyExtractor<E> {
        int getIntKey(E e);
    }

    public BalancedIntKeyObjectTree(IntKeyExtractor<E> intKeyExtractor, Iterable<E> iterable) {
        buildTree(intKeyExtractor, convertToSortedList(intKeyExtractor, iterable));
    }

    public BalancedIntKeyObjectTree(IntKeyExtractor<E> intKeyExtractor, E[] eArr) {
        buildTree(intKeyExtractor, convertToSortedList(intKeyExtractor, eArr));
    }

    private void buildTree(IntKeyExtractor<E> intKeyExtractor, List<TreeNode> list) {
        this.rootNode = buildTree(intKeyExtractor, list, 0, list.size() - 1);
    }

    @Nullable
    private TreeNode buildTree(IntKeyExtractor<E> intKeyExtractor, List<TreeNode> list, int i, int i2) {
        if (i > i2) {
            return null;
        }
        int i3 = (i + i2) >> 1;
        TreeNode treeNode = list.get(i3);
        treeNode.mLeft = buildTree(intKeyExtractor, list, i, i3 - 1);
        treeNode.mRight = buildTree(intKeyExtractor, list, i3 + 1, i2);
        return treeNode;
    }

    @Nullable
    public E get(int i) {
        return getFromTree(this.rootNode, i);
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [E, F] */
    @Nullable
    private E getFromTree(TreeNode treeNode, int i) {
        if (treeNode == null) {
            return null;
        }
        if (i == treeNode.mKey) {
            return treeNode.mItem;
        }
        return getFromTree(i < treeNode.mKey ? treeNode.mLeft : treeNode.mRight, i);
    }

    public int size() {
        return countNodes(this.rootNode);
    }

    private int countNodes(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return countNodes(treeNode.mLeft) + 1 + countNodes(treeNode.mRight);
    }

    /* access modifiers changed from: package-private */
    public TreeNode getRootNode() {
        return this.rootNode;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.TreeSet */
    /* JADX WARN: Multi-variable type inference failed */
    private List<TreeNode> convertToSortedList(IntKeyExtractor<E> intKeyExtractor, Iterable<E> iterable) {
        TreeSet treeSet = new TreeSet(getComparator());
        for (E e : iterable) {
            treeSet.add(new TreeNode(intKeyExtractor, e));
        }
        return new ArrayList(treeSet);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.TreeSet */
    /* JADX WARN: Multi-variable type inference failed */
    private List<TreeNode> convertToSortedList(IntKeyExtractor<E> intKeyExtractor, E[] eArr) {
        TreeSet treeSet = new TreeSet(getComparator());
        for (E e : eArr) {
            treeSet.add(new TreeNode(intKeyExtractor, e));
        }
        return new ArrayList(treeSet);
    }

    private Comparator<TreeNode> getComparator() {
        return new Comparator<TreeNode>() {
            /* class com.facebook.common.collectlite.BalancedIntKeyObjectTree.AnonymousClass1 */

            public int compare(TreeNode treeNode, TreeNode treeNode2) {
                return treeNode.mKey - treeNode2.mKey;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public static class TreeNode<F> {
        F mItem;
        int mKey;
        TreeNode mLeft = null;
        TreeNode mRight = null;

        TreeNode(IntKeyExtractor<F> intKeyExtractor, F f) {
            this.mKey = intKeyExtractor.getIntKey(f);
            this.mItem = f;
        }
    }
}
