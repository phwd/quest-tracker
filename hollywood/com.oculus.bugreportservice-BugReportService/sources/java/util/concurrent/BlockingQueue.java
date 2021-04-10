package java.util.concurrent;

import java.util.Queue;

public interface BlockingQueue extends Queue {
    @Override // java.util.Queue
    boolean offer(Object obj);

    Object poll(long j, TimeUnit timeUnit);

    @Override // java.util.Collection
    boolean remove(Object obj);

    Object take();
}
