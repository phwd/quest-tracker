package java.util.concurrent;

public interface RunnableFuture<V> extends Runnable, Future<V> {
    @Override // java.lang.Runnable
    void run();
}
