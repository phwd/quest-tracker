package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.math.IntMath;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible
public final class CartesianList<E> extends AbstractList<List<E>> implements RandomAccess {
    private final transient ImmutableList<List<E>> axes;
    private final transient int[] axesSizeProduct;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableList$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    static <E> List<List<E>> create(List<? extends List<? extends E>> lists) {
        ImmutableList.Builder<List<E>> axesBuilder = new ImmutableList.Builder<>(lists.size());
        for (List<? extends E> list : lists) {
            List<E> copy = ImmutableList.copyOf((Collection) list);
            if (copy.isEmpty()) {
                return ImmutableList.of();
            }
            axesBuilder.add(copy);
        }
        return new CartesianList(axesBuilder.build());
    }

    CartesianList(ImmutableList<List<E>> axes2) {
        this.axes = axes2;
        int[] axesSizeProduct2 = new int[(axes2.size() + 1)];
        axesSizeProduct2[axes2.size()] = 1;
        try {
            for (int i = axes2.size() - 1; i >= 0; i--) {
                axesSizeProduct2[i] = IntMath.checkedMultiply(axesSizeProduct2[i + 1], axes2.get(i).size());
            }
            this.axesSizeProduct = axesSizeProduct2;
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Cartesian product too large; must have size at most Integer.MAX_VALUE");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int getAxisIndexForProductIndex(int index, int axis) {
        return (index / this.axesSizeProduct[axis + 1]) % this.axes.get(axis).size();
    }

    @Override // java.util.List, java.util.AbstractList
    public ImmutableList<E> get(final int index) {
        Preconditions.checkElementIndex(index, size());
        return new ImmutableList<E>() {
            /* class com.google.common.collect.CartesianList.AnonymousClass1 */

            public int size() {
                return CartesianList.this.axes.size();
            }

            @Override // java.util.List
            public E get(int axis) {
                Preconditions.checkElementIndex(axis, size());
                return (E) ((List) CartesianList.this.axes.get(axis)).get(CartesianList.this.getAxisIndexForProductIndex(index, axis));
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableCollection
            public boolean isPartialView() {
                return true;
            }
        };
    }

    public int size() {
        return this.axesSizeProduct[0];
    }

    public boolean contains(@Nullable Object o) {
        if (!(o instanceof List)) {
            return false;
        }
        List<?> list = (List) o;
        if (list.size() != this.axes.size()) {
            return false;
        }
        ListIterator<?> itr = list.listIterator();
        while (itr.hasNext()) {
            if (!this.axes.get(itr.nextIndex()).contains(itr.next())) {
                return false;
            }
        }
        return true;
    }
}
