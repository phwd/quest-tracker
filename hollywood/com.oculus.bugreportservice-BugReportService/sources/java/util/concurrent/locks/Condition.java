package java.util.concurrent.locks;

public interface Condition {
    void await();

    long awaitNanos(long j);

    void signal();

    void signalAll();
}
