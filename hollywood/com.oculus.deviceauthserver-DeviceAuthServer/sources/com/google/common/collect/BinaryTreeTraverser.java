package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;
import java.util.Iterator;

@Beta
@GwtCompatible(emulated = true)
public abstract class BinaryTreeTraverser<T> extends TreeTraverser<T> {
    public abstract Optional<T> leftChild(T t);

    public abstract Optional<T> rightChild(T t);

    @Override // com.google.common.collect.TreeTraverser
    public final Iterable<T> children(final T root) {
        Preconditions.checkNotNull(root);
        return new FluentIterable<T>() {
            /* class com.google.common.collect.BinaryTreeTraverser.AnonymousClass1 */

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return new AbstractIterator<T>() {
                    /* class com.google.common.collect.BinaryTreeTraverser.AnonymousClass1.AnonymousClass1 */
                    boolean doneLeft;
                    boolean doneRight;

                    /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: com.google.common.collect.BinaryTreeTraverser */
                    /* JADX DEBUG: Multi-variable search result rejected for r0v7, resolved type: com.google.common.collect.BinaryTreeTraverser */
                    /* JADX WARN: Multi-variable type inference failed */
                    /* access modifiers changed from: protected */
                    @Override // com.google.common.collect.AbstractIterator
                    public T computeNext() {
                        if (!this.doneLeft) {
                            this.doneLeft = true;
                            Optional<T> left = BinaryTreeTraverser.this.leftChild(root);
                            if (left.isPresent()) {
                                return left.get();
                            }
                        }
                        if (!this.doneRight) {
                            this.doneRight = true;
                            Optional<T> right = BinaryTreeTraverser.this.rightChild(root);
                            if (right.isPresent()) {
                                return right.get();
                            }
                        }
                        return (T) endOfData();
                    }
                };
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.TreeTraverser
    public UnmodifiableIterator<T> preOrderIterator(T root) {
        return new PreOrderIterator(root);
    }

    private final class PreOrderIterator extends UnmodifiableIterator<T> implements PeekingIterator<T> {
        private final Deque<T> stack = new ArrayDeque();

        PreOrderIterator(T root) {
            this.stack.addLast(root);
        }

        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override // java.util.Iterator, com.google.common.collect.PeekingIterator
        public T next() {
            T result = this.stack.removeLast();
            BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.rightChild(result));
            BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.leftChild(result));
            return result;
        }

        @Override // com.google.common.collect.PeekingIterator
        public T peek() {
            return this.stack.getLast();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.TreeTraverser
    public UnmodifiableIterator<T> postOrderIterator(T root) {
        return new PostOrderIterator(root);
    }

    private final class PostOrderIterator extends UnmodifiableIterator<T> {
        private final BitSet hasExpanded;
        private final Deque<T> stack = new ArrayDeque();

        PostOrderIterator(T root) {
            this.stack.addLast(root);
            this.hasExpanded = new BitSet();
        }

        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override // java.util.Iterator
        public T next() {
            while (true) {
                T node = this.stack.getLast();
                if (this.hasExpanded.get(this.stack.size() - 1)) {
                    this.stack.removeLast();
                    this.hasExpanded.clear(this.stack.size());
                    return node;
                }
                this.hasExpanded.set(this.stack.size() - 1);
                BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.rightChild(node));
                BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.leftChild(node));
            }
        }
    }

    public final FluentIterable<T> inOrderTraversal(final T root) {
        Preconditions.checkNotNull(root);
        return new FluentIterable<T>() {
            /* class com.google.common.collect.BinaryTreeTraverser.AnonymousClass2 */

            @Override // java.lang.Iterable
            public UnmodifiableIterator<T> iterator() {
                return new InOrderIterator(root);
            }
        };
    }

    /* access modifiers changed from: private */
    public final class InOrderIterator extends AbstractIterator<T> {
        private final BitSet hasExpandedLeft = new BitSet();
        private final Deque<T> stack = new ArrayDeque();

        InOrderIterator(T root) {
            this.stack.addLast(root);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIterator
        public T computeNext() {
            while (!this.stack.isEmpty()) {
                T node = this.stack.getLast();
                if (this.hasExpandedLeft.get(this.stack.size() - 1)) {
                    this.stack.removeLast();
                    this.hasExpandedLeft.clear(this.stack.size());
                    BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.rightChild(node));
                    return node;
                }
                this.hasExpandedLeft.set(this.stack.size() - 1);
                BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.leftChild(node));
            }
            return (T) endOfData();
        }
    }

    /* access modifiers changed from: private */
    public static <T> void pushIfPresent(Deque<T> stack, Optional<T> node) {
        if (node.isPresent()) {
            stack.addLast(node.get());
        }
    }
}
