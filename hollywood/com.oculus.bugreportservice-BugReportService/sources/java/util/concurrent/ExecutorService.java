package java.util.concurrent;

public interface ExecutorService extends Executor {
    Future submit(Runnable runnable);
}
