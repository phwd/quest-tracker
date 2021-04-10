package java.util;

import java.lang.Enum;

/* access modifiers changed from: package-private */
public class JumboEnumSet<E extends Enum<E>> extends EnumSet<E> {
    private static final long serialVersionUID = 334349849919042784L;
    private long[] elements;
    private int size = 0;

    static /* synthetic */ int access$110(JumboEnumSet x0) {
        int i = x0.size;
        x0.size = i - 1;
        return i;
    }

    JumboEnumSet(Class<E> elementType, Enum<?>[] universe) {
        super(elementType, universe);
        this.elements = new long[((universe.length + 63) >>> 6)];
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.EnumSet
    public void addRange(E from, E to) {
        int fromIndex = from.ordinal() >>> 6;
        int toIndex = to.ordinal() >>> 6;
        if (fromIndex == toIndex) {
            this.elements[fromIndex] = (-1 >>> ((from.ordinal() - to.ordinal()) - 1)) << from.ordinal();
        } else {
            this.elements[fromIndex] = -1 << from.ordinal();
            for (int i = fromIndex + 1; i < toIndex; i++) {
                this.elements[i] = -1;
            }
            this.elements[toIndex] = -1 >>> (63 - to.ordinal());
        }
        this.size = (to.ordinal() - from.ordinal()) + 1;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.EnumSet
    public void addAll() {
        int i = 0;
        while (true) {
            long[] jArr = this.elements;
            if (i < jArr.length) {
                jArr[i] = -1;
                i++;
            } else {
                int length = jArr.length - 1;
                jArr[length] = jArr[length] >>> (-this.universe.length);
                this.size = this.universe.length;
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.EnumSet
    public void complement() {
        int i = 0;
        while (true) {
            long[] jArr = this.elements;
            if (i < jArr.length) {
                jArr[i] = ~jArr[i];
                i++;
            } else {
                int length = jArr.length - 1;
                jArr[length] = jArr[length] & (-1 >>> (-this.universe.length));
                this.size = this.universe.length - this.size;
                return;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator<E> iterator() {
        return new EnumSetIterator();
    }

    private class EnumSetIterator<E extends Enum<E>> implements Iterator<E> {
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
        public E next() {
            if (hasNext()) {
                long j = this.unseen;
                this.lastReturned = (-j) & j;
                this.lastReturnedIndex = this.unseenIndex;
                this.unseen = j - this.lastReturned;
                return (E) JumboEnumSet.this.universe[(this.lastReturnedIndex << 6) + Long.numberOfTrailingZeros(this.lastReturned)];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastReturned != 0) {
                long oldElements = JumboEnumSet.this.elements[this.lastReturnedIndex];
                long[] jArr = JumboEnumSet.this.elements;
                int i = this.lastReturnedIndex;
                jArr[i] = jArr[i] & (~this.lastReturned);
                if (oldElements != JumboEnumSet.this.elements[this.lastReturnedIndex]) {
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
    public boolean contains(Object e) {
        if (e == null) {
            return false;
        }
        Class<?> eClass = e.getClass();
        if (eClass != this.elementType && eClass.getSuperclass() != this.elementType) {
            return false;
        }
        int eOrdinal = ((Enum) e).ordinal();
        if ((this.elements[eOrdinal >>> 6] & (1 << eOrdinal)) != 0) {
            return true;
        }
        return false;
    }

    public boolean add(E e) {
        typeCheck(e);
        int eOrdinal = e.ordinal();
        int eWordNum = eOrdinal >>> 6;
        long[] jArr = this.elements;
        long oldElements = jArr[eWordNum];
        jArr[eWordNum] = jArr[eWordNum] | (1 << eOrdinal);
        boolean result = jArr[eWordNum] != oldElements;
        if (result) {
            this.size++;
        }
        return result;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object e) {
        boolean result = false;
        if (e == null) {
            return false;
        }
        Class<?> eClass = e.getClass();
        if (eClass != this.elementType && eClass.getSuperclass() != this.elementType) {
            return false;
        }
        int eOrdinal = ((Enum) e).ordinal();
        int eWordNum = eOrdinal >>> 6;
        long[] jArr = this.elements;
        long oldElements = jArr[eWordNum];
        jArr[eWordNum] = jArr[eWordNum] & (~(1 << eOrdinal));
        if (jArr[eWordNum] != oldElements) {
            result = true;
        }
        if (result) {
            this.size--;
        }
        return result;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> c) {
        if (!(c instanceof JumboEnumSet)) {
            return super.containsAll(c);
        }
        JumboEnumSet<?> es = (JumboEnumSet) c;
        if (es.elementType != this.elementType) {
            return es.isEmpty();
        }
        int i = 0;
        while (true) {
            long[] jArr = this.elements;
            if (i >= jArr.length) {
                return true;
            }
            if ((es.elements[i] & (~jArr[i])) != 0) {
                return false;
            }
            i++;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> c) {
        if (!(c instanceof JumboEnumSet)) {
            return super.addAll(c);
        }
        JumboEnumSet<?> es = (JumboEnumSet) c;
        if (es.elementType == this.elementType) {
            int i = 0;
            while (true) {
                long[] jArr = this.elements;
                if (i >= jArr.length) {
                    return recalculateSize();
                }
                jArr[i] = jArr[i] | es.elements[i];
                i++;
            }
        } else if (es.isEmpty()) {
            return false;
        } else {
            throw new ClassCastException(((Object) es.elementType) + " != " + ((Object) this.elementType));
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean removeAll(Collection<?> c) {
        if (!(c instanceof JumboEnumSet)) {
            return super.removeAll(c);
        }
        JumboEnumSet<?> es = (JumboEnumSet) c;
        if (es.elementType != this.elementType) {
            return false;
        }
        int i = 0;
        while (true) {
            long[] jArr = this.elements;
            if (i >= jArr.length) {
                return recalculateSize();
            }
            jArr[i] = jArr[i] & (~es.elements[i]);
            i++;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> c) {
        if (!(c instanceof JumboEnumSet)) {
            return super.retainAll(c);
        }
        JumboEnumSet<?> es = (JumboEnumSet) c;
        if (es.elementType != this.elementType) {
            boolean changed = this.size != 0;
            clear();
            return changed;
        }
        int i = 0;
        while (true) {
            long[] jArr = this.elements;
            if (i >= jArr.length) {
                return recalculateSize();
            }
            jArr[i] = jArr[i] & es.elements[i];
            i++;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        Arrays.fill(this.elements, 0L);
        this.size = 0;
    }

    @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean equals(Object o) {
        if (!(o instanceof JumboEnumSet)) {
            return super.equals(o);
        }
        JumboEnumSet<?> es = (JumboEnumSet) o;
        if (es.elementType != this.elementType) {
            return this.size == 0 && es.size == 0;
        }
        return Arrays.equals(es.elements, this.elements);
    }

    private boolean recalculateSize() {
        int oldSize = this.size;
        this.size = 0;
        long[] jArr = this.elements;
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            this.size += Long.bitCount(jArr[i]);
        }
        if (this.size != oldSize) {
            return true;
        }
        return false;
    }

    @Override // java.util.EnumSet, java.util.EnumSet
    public EnumSet<E> clone() {
        JumboEnumSet<E> result = (JumboEnumSet) super.clone();
        result.elements = (long[]) result.elements.clone();
        return result;
    }
}
