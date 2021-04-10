package java.util;

public abstract class AbstractSet extends AbstractCollection implements Set {
    protected AbstractSet() {
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Collection collection = (Collection) obj;
        if (collection.size() != size()) {
            return false;
        }
        try {
            return containsAll(collection);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            if (next != null) {
                i += next.hashCode();
            }
        }
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection collection) {
        Objects.requireNonNull(collection);
        boolean z = false;
        if (size() > collection.size()) {
            for (Object obj : collection) {
                z |= remove(obj);
            }
        } else {
            Iterator it = iterator();
            while (it.hasNext()) {
                if (collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
        }
        return z;
    }
}
