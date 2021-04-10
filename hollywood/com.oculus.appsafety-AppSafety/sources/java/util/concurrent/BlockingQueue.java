package java.util.concurrent;

import java.util.Collection;
import java.util.Queue;

public interface BlockingQueue<E> extends Queue<E> {
    @Override // java.util.Collection, java.util.Queue
    boolean add(E e);

    @Override // java.util.Collection
    boolean contains(Object obj);

    int drainTo(Collection<? super E> collection);

    int drainTo(Collection<? super E> collection, int i);

    @Override // java.util.Queue
    boolean offer(E e);

    boolean offer(E e, long j, TimeUnit timeUnit) throws InterruptedException;

    E poll(long j, TimeUnit timeUnit) throws InterruptedException;

    void put(E e) throws InterruptedException;

    int remainingCapacity();

    @Override // java.util.Collection
    boolean remove(Object obj);

    E take() throws InterruptedException;
}
