package java.util;

import java.lang.Enum;

/* access modifiers changed from: package-private */
public class RegularEnumSet<E extends Enum<E>> extends EnumSet<E> {
    private static final long serialVersionUID = 3411599620347842686L;
    private long elements = 0;

    static /* synthetic */ long access$074(RegularEnumSet x0, long x1) {
        long j = x0.elements & x1;
        x0.elements = j;
        return j;
    }

    RegularEnumSet(Class<E> elementType, Enum<?>[] universe) {
        super(elementType, universe);
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.EnumSet
    public void addRange(E from, E to) {
        this.elements = (-1 >>> ((from.ordinal() - to.ordinal()) - 1)) << from.ordinal();
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.EnumSet
    public void addAll() {
        if (this.universe.length != 0) {
            this.elements = -1 >>> (-this.universe.length);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.EnumSet
    public void complement() {
        if (this.universe.length != 0) {
            this.elements = ~this.elements;
            this.elements &= -1 >>> (-this.universe.length);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator<E> iterator() {
        return new EnumSetIterator();
    }

    private class EnumSetIterator<E extends Enum<E>> implements Iterator<E> {
        long lastReturned = 0;
        long unseen;

        EnumSetIterator() {
            this.unseen = RegularEnumSet.this.elements;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.unseen != 0;
        }

        @Override // java.util.Iterator
        public E next() {
            long j = this.unseen;
            if (j != 0) {
                this.lastReturned = (-j) & j;
                this.unseen = j - this.lastReturned;
                return (E) RegularEnumSet.this.universe[Long.numberOfTrailingZeros(this.lastReturned)];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            long j = this.lastReturned;
            if (j != 0) {
                RegularEnumSet.access$074(RegularEnumSet.this, ~j);
                this.lastReturned = 0;
                return;
            }
            throw new IllegalStateException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return Long.bitCount(this.elements);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.elements == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object e) {
        if (e == null) {
            return false;
        }
        Class<?> eClass = e.getClass();
        if ((eClass == this.elementType || eClass.getSuperclass() == this.elementType) && (this.elements & (1 << ((Enum) e).ordinal())) != 0) {
            return true;
        }
        return false;
    }

    public boolean add(E e) {
        typeCheck(e);
        long oldElements = this.elements;
        this.elements |= 1 << e.ordinal();
        return this.elements != oldElements;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object e) {
        if (e == null) {
            return false;
        }
        Class<?> eClass = e.getClass();
        if (eClass != this.elementType && eClass.getSuperclass() != this.elementType) {
            return false;
        }
        long oldElements = this.elements;
        this.elements &= ~(1 << ((Enum) e).ordinal());
        if (this.elements != oldElements) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> c) {
        if (!(c instanceof RegularEnumSet)) {
            return super.containsAll(c);
        }
        RegularEnumSet<?> es = (RegularEnumSet) c;
        if (es.elementType != this.elementType) {
            return es.isEmpty();
        }
        return (es.elements & (~this.elements)) == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> c) {
        if (!(c instanceof RegularEnumSet)) {
            return super.addAll(c);
        }
        RegularEnumSet<?> es = (RegularEnumSet) c;
        if (es.elementType == this.elementType) {
            long oldElements = this.elements;
            this.elements |= es.elements;
            if (this.elements != oldElements) {
                return true;
            }
            return false;
        } else if (es.isEmpty()) {
            return false;
        } else {
            throw new ClassCastException(((Object) es.elementType) + " != " + ((Object) this.elementType));
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean removeAll(Collection<?> c) {
        if (!(c instanceof RegularEnumSet)) {
            return super.removeAll(c);
        }
        RegularEnumSet<?> es = (RegularEnumSet) c;
        if (es.elementType != this.elementType) {
            return false;
        }
        long oldElements = this.elements;
        this.elements &= ~es.elements;
        if (this.elements != oldElements) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Multiple debug info for r1v1 long: [D('changed' boolean), D('oldElements' long)] */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> c) {
        if (!(c instanceof RegularEnumSet)) {
            return super.retainAll(c);
        }
        RegularEnumSet<?> es = (RegularEnumSet) c;
        boolean changed = true;
        if (es.elementType != this.elementType) {
            if (this.elements == 0) {
                changed = false;
            }
            this.elements = 0;
            return changed;
        }
        long oldElements = this.elements;
        this.elements &= es.elements;
        return this.elements != oldElements;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.elements = 0;
    }

    @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean equals(Object o) {
        if (!(o instanceof RegularEnumSet)) {
            return super.equals(o);
        }
        RegularEnumSet<?> es = (RegularEnumSet) o;
        return es.elementType != this.elementType ? this.elements == 0 && es.elements == 0 : es.elements == this.elements;
    }
}
