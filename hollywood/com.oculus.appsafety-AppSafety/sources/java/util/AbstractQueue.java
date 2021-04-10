package java.util;

public abstract class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E> {
    protected AbstractQueue() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Queue
    public boolean add(E e) {
        if (offer(e)) {
            return true;
        }
        throw new IllegalStateException("Queue full");
    }

    @Override // java.util.Queue
    public E remove() {
        E x = poll();
        if (x != null) {
            return x;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Queue
    public E element() {
        E x = peek();
        if (x != null) {
            return x;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        do {
        } while (poll() != null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.AbstractQueue<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException();
        } else if (c != this) {
            boolean modified = false;
            Iterator<? extends E> it = c.iterator();
            while (it.hasNext()) {
                if (add(it.next())) {
                    modified = true;
                }
            }
            return modified;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
