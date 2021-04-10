package java.util;

public interface Deque extends Queue {
    @Override // java.util.Collection
    boolean add(Object obj);

    @Override // java.util.Collection, java.lang.Iterable
    Iterator iterator();

    Object poll();

    @Override // java.util.Collection
    boolean remove(Object obj);

    Object removeFirst();
}
