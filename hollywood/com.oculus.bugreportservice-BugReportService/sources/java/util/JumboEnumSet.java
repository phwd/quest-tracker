package java.util;

/* access modifiers changed from: package-private */
public class JumboEnumSet extends EnumSet {
    private static final long serialVersionUID = 334349849919042784L;
    private long[] elements;
    private int size = 0;

    static /* synthetic */ int access$110(JumboEnumSet jumboEnumSet) {
        int i = jumboEnumSet.size;
        jumboEnumSet.size = i - 1;
        return i;
    }

    JumboEnumSet(Class cls, Enum[] enumArr) {
        super(cls, enumArr);
        this.elements = new long[((enumArr.length + 63) >>> 6)];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator iterator() {
        return new EnumSetIterator();
    }

    private class EnumSetIterator implements Iterator {
        long lastReturned = 0;
        int lastReturnedIndex = 0;
        long unseen;
        int unseenIndex = 0;

        EnumSetIterator() {
            this.unseen = JumboEnumSet.this.elements[0];
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (this.unseen == 0 && this.unseenIndex < JumboEnumSet.this.elements.length - 1) {
                long[] jArr = JumboEnumSet.this.elements;
                int i = this.unseenIndex + 1;
                this.unseenIndex = i;
                this.unseen = jArr[i];
            }
            if (this.unseen != 0) {
                return true;
            }
            return false;
        }

        @Override // java.util.Iterator
        public Enum next() {
            if (hasNext()) {
                long j = this.unseen;
                this.lastReturned = (-j) & j;
                this.lastReturnedIndex = this.unseenIndex;
                long j2 = this.lastReturned;
                this.unseen = j - j2;
                return JumboEnumSet.this.universe[(this.lastReturnedIndex << 6) + Long.numberOfTrailingZeros(j2)];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastReturned != 0) {
                long j = JumboEnumSet.this.elements[this.lastReturnedIndex];
                long[] jArr = JumboEnumSet.this.elements;
                int i = this.lastReturnedIndex;
                jArr[i] = jArr[i] & (~this.lastReturned);
                if (j != JumboEnumSet.this.elements[this.lastReturnedIndex]) {
                    JumboEnumSet.access$110(JumboEnumSet.this);
                }
                this.lastReturned = 0;
                return;
            }
            throw new IllegalStateException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.size == 0;
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
        int ordinal = ((Enum) obj).ordinal();
        if (((1 << ordinal) & this.elements[ordinal >>> 6]) != 0) {
            return true;
        }
        return false;
    }

    public boolean add(Enum r9) {
        typeCheck(r9);
        int ordinal = r9.ordinal();
        int i = ordinal >>> 6;
        long[] jArr = this.elements;
        long j = jArr[i];
        jArr[i] = jArr[i] | (1 << ordinal);
        boolean z = jArr[i] != j;
        if (z) {
            this.size++;
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        Class cls = obj.getClass();
        if (cls != this.elementType && cls.getSuperclass() != this.elementType) {
            return false;
        }
        int ordinal = ((Enum) obj).ordinal();
        int i = ordinal >>> 6;
        long[] jArr = this.elements;
        long j = jArr[i];
        jArr[i] = jArr[i] & (~(1 << ordinal));
        if (jArr[i] != j) {
            z = true;
        }
        if (z) {
            this.size--;
        }
        return z;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        if (!(collection instanceof JumboEnumSet)) {
            return super.containsAll(collection);
        }
        JumboEnumSet jumboEnumSet = (JumboEnumSet) collection;
        if (jumboEnumSet.elementType != this.elementType) {
            return jumboEnumSet.isEmpty();
        }
        int i = 0;
        while (true) {
            long[] jArr = this.elements;
            if (i >= jArr.length) {
                return true;
            }
            if ((jumboEnumSet.elements[i] & (~jArr[i])) != 0) {
                return false;
            }
            i++;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection collection) {
        if (!(collection instanceof JumboEnumSet)) {
            return super.addAll(collection);
        }
        JumboEnumSet jumboEnumSet = (JumboEnumSet) collection;
        int i = 0;
        if (jumboEnumSet.elementType == this.elementType) {
            while (true) {
                long[] jArr = this.elements;
                if (i >= jArr.length) {
                    return recalculateSize();
                }
                jArr[i] = jArr[i] | jumboEnumSet.elements[i];
                i++;
            }
        } else if (jumboEnumSet.isEmpty()) {
            return false;
        } else {
            throw new ClassCastException(jumboEnumSet.elementType + " != " + this.elementType);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean removeAll(Collection collection) {
        if (!(collection instanceof JumboEnumSet)) {
            return super.removeAll(collection);
        }
        JumboEnumSet jumboEnumSet = (JumboEnumSet) collection;
        int i = 0;
        if (jumboEnumSet.elementType != this.elementType) {
            return false;
        }
        while (true) {
            long[] jArr = this.elements;
            if (i >= jArr.length) {
                return recalculateSize();
            }
            jArr[i] = jArr[i] & (~jumboEnumSet.elements[i]);
            i++;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        if (!(collection instanceof JumboEnumSet)) {
            return super.retainAll(collection);
        }
        JumboEnumSet jumboEnumSet = (JumboEnumSet) collection;
        int i = 0;
        boolean z = false;
        if (jumboEnumSet.elementType != this.elementType) {
            if (this.size != 0) {
                z = true;
            }
            clear();
            return z;
        }
        while (true) {
            long[] jArr = this.elements;
            if (i >= jArr.length) {
                return recalculateSize();
            }
            jArr[i] = jArr[i] & jumboEnumSet.elements[i];
            i++;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        Arrays.fill(this.elements, 0);
        this.size = 0;
    }

    @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean equals(Object obj) {
        if (!(obj instanceof JumboEnumSet)) {
            return super.equals(obj);
        }
        JumboEnumSet jumboEnumSet = (JumboEnumSet) obj;
        if (jumboEnumSet.elementType != this.elementType) {
            return this.size == 0 && jumboEnumSet.size == 0;
        }
        return Arrays.equals(jumboEnumSet.elements, this.elements);
    }

    private boolean recalculateSize() {
        int i = this.size;
        this.size = 0;
        long[] jArr = this.elements;
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.size += Long.bitCount(jArr[i2]);
        }
        if (this.size != i) {
            return true;
        }
        return false;
    }

    @Override // java.util.EnumSet, java.util.EnumSet
    public EnumSet clone() {
        JumboEnumSet jumboEnumSet = (JumboEnumSet) super.clone();
        jumboEnumSet.elements = (long[]) jumboEnumSet.elements.clone();
        return jumboEnumSet;
    }
}
