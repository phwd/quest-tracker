package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HashSet extends AbstractSet implements Set, Cloneable, Serializable {
    private static final Object PRESENT = new Object();
    static final long serialVersionUID = -5024744406713321676L;
    private transient HashMap map;

    public HashSet() {
        this.map = new HashMap();
    }

    public HashSet(Collection collection) {
        this.map = new HashMap(Math.max(((int) (((float) collection.size()) / 0.75f)) + 1, 16));
        addAll(collection);
    }

    public HashSet(int i) {
        this.map = new HashMap(i);
    }

    HashSet(int i, float f, boolean z) {
        this.map = new LinkedHashMap(i, f);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator iterator() {
        return this.map.keySet().iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.map.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        return this.map.put(obj, PRESENT) == null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        return this.map.remove(obj) == PRESENT;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.map.clear();
    }

    public Object clone() {
        try {
            HashSet hashSet = (HashSet) super.clone();
            hashSet.map = (HashMap) this.map.clone();
            return hashSet;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
