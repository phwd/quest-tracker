package java.util;

/* access modifiers changed from: package-private */
public class RegularEnumSet extends EnumSet {
    private static final long serialVersionUID = 3411599620347842686L;
    private long elements = 0;

    static /* synthetic */ long access$074(RegularEnumSet regularEnumSet, long j) {
        long j2 = j & regularEnumSet.elements;
        regularEnumSet.elements = j2;
        return j2;
    }

    RegularEnumSet(Class cls, Enum[] enumArr) {
        super(cls, enumArr);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator iterator() {
        return new EnumSetIterator();
    }

    private class EnumSetIterator implements Iterator {
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
        public Enum next() {
            long j = this.unseen;
            if (j != 0) {
                this.lastReturned = (-j) & j;
                long j2 = this.lastReturned;
                this.unseen = j - j2;
                return RegularEnumSet.this.universe[Long.numberOfTrailingZeros(j2)];
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
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        Class cls = obj.getClass();
        if (cls != this.elementType && cls.getSuperclass() != this.elementType) {
            return false;
        }
        if (((1 << ((Enum) obj).ordinal()) & this.elements) != 0) {
            return true;
        }
        return false;
    }

    public boolean add(Enum r5) {
        typeCheck(r5);
        long j = this.elements;
        this.elements = (1 << r5.ordinal()) | j;
        return this.elements != j;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        Class cls = obj.getClass();
        if (cls != this.elementType && cls.getSuperclass() != this.elementType) {
            return false;
        }
        long j = this.elements;
        this.elements = (~(1 << ((Enum) obj).ordinal())) & j;
        if (this.elements != j) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        if (!(collection instanceof RegularEnumSet)) {
            return super.containsAll(collection);
        }
        RegularEnumSet regularEnumSet = (RegularEnumSet) collection;
        if (regularEnumSet.elementType != this.elementType) {
            return regularEnumSet.isEmpty();
        }
        return ((~this.elements) & regularEnumSet.elements) == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection collection) {
        if (!(collection instanceof RegularEnumSet)) {
            return super.addAll(collection);
        }
        RegularEnumSet regularEnumSet = (RegularEnumSet) collection;
        if (regularEnumSet.elementType == this.elementType) {
            long j = this.elements;
            this.elements = regularEnumSet.elements | j;
            if (this.elements != j) {
                return true;
            }
            return false;
        } else if (regularEnumSet.isEmpty()) {
            return false;
        } else {
            throw new ClassCastException(regularEnumSet.elementType + " != " + this.elementType);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean removeAll(Collection collection) {
        if (!(collection instanceof RegularEnumSet)) {
            return super.removeAll(collection);
        }
        RegularEnumSet regularEnumSet = (RegularEnumSet) collection;
        if (regularEnumSet.elementType != this.elementType) {
            return false;
        }
        long j = this.elements;
        this.elements = (~regularEnumSet.elements) & j;
        if (this.elements != j) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        if (!(collection instanceof RegularEnumSet)) {
            return super.retainAll(collection);
        }
        RegularEnumSet regularEnumSet = (RegularEnumSet) collection;
        boolean z = true;
        if (regularEnumSet.elementType != this.elementType) {
            if (this.elements == 0) {
                z = false;
            }
            this.elements = 0;
            return z;
        }
        long j = this.elements;
        this.elements = regularEnumSet.elements & j;
        return this.elements != j;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.elements = 0;
    }

    @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean equals(Object obj) {
        if (!(obj instanceof RegularEnumSet)) {
            return super.equals(obj);
        }
        RegularEnumSet regularEnumSet = (RegularEnumSet) obj;
        return regularEnumSet.elementType != this.elementType ? this.elements == 0 && regularEnumSet.elements == 0 : regularEnumSet.elements == this.elements;
    }
}
