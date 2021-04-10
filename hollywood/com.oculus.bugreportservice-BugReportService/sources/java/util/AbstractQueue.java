package java.util;

public abstract class AbstractQueue extends AbstractCollection implements Queue {
    protected AbstractQueue() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean add(Object obj) {
        if (offer(obj)) {
            return true;
        }
        throw new IllegalStateException("Queue full");
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection collection) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection != this) {
            boolean z = false;
            for (Object obj : collection) {
                if (add(obj)) {
                    z = true;
                }
            }
            return z;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
