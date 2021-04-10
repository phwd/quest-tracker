package X;

import com.google.common.collect.BoundType;
import com.google.common.collect.ComparatorOrdering;
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;

public abstract class YG<E> extends AbstractC0140De<E> implements AbstractC0118Bl<E> {
    public transient Set A00;
    public transient Comparator A01;
    public transient NavigableSet A02;

    @Override // X.AbstractC0118Bl
    public final AbstractC0118Bl A1g() {
        return ((AnonymousClass24) this).A00;
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC1179ua A29() {
        return ((AnonymousClass24) this).A00.A3f();
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC0118Bl A3B(Object obj, BoundType boundType) {
        return ((AnonymousClass24) this).A00.A5F(obj, boundType).A1g();
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC1179ua A3f() {
        return ((AnonymousClass24) this).A00.A29();
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC1179ua A4V() {
        return ((AnonymousClass24) this).A00.A4W();
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC1179ua A4W() {
        return ((AnonymousClass24) this).A00.A4V();
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC0118Bl A5A(Object obj, BoundType boundType, Object obj2, BoundType boundType2) {
        return ((AnonymousClass24) this).A00.A5A(obj2, boundType2, obj, boundType).A1g();
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC0118Bl A5F(Object obj, BoundType boundType) {
        return ((AnonymousClass24) this).A00.A3B(obj, boundType).A1g();
    }

    @Override // X.AbstractC0118Bl
    public final NavigableSet A1l() {
        NavigableSet navigableSet = this.A02;
        if (navigableSet != null) {
            return navigableSet;
        }
        SI si = new SI(this);
        this.A02 = si;
        return si;
    }

    @Override // X.UY, X.AbstractC0118Bl
    public final Comparator comparator() {
        UR comparatorOrdering;
        Comparator comparator = this.A01;
        if (comparator != null) {
            return comparator;
        }
        Comparator comparator2 = ((AnonymousClass24) this).A00.comparator();
        if (comparator2 instanceof UR) {
            comparatorOrdering = (UR) comparator2;
        } else {
            comparatorOrdering = new ComparatorOrdering(comparator2);
        }
        UR A002 = comparatorOrdering.A00();
        this.A01 = A002;
        return A002;
    }

    @Override // java.util.Collection, java.lang.Iterable, X.u6
    public final Iterator iterator() {
        if (!(this instanceof AnonymousClass24)) {
            return new UN(this, entrySet().iterator());
        }
        AbstractC0118Bl A1g = ((AnonymousClass24) this).A00.A1g();
        return new UN(A1g, A1g.entrySet().iterator());
    }

    @Override // X.Tu
    public final String toString() {
        return entrySet().toString();
    }

    @Override // X.u6
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // java.util.Collection, X.u6
    public final Object[] toArray(Object[] objArr) {
        int size = size();
        if (objArr.length < size) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
        }
        int i = 0;
        for (E e : this) {
            objArr[i] = e;
            i++;
        }
        if (objArr.length > size) {
            objArr[size] = null;
        }
        return objArr;
    }
}
