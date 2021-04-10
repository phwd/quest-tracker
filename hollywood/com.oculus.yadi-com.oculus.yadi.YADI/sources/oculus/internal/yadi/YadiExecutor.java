package oculus.internal.yadi;

import android.os.Process;
import com.oculus.os.yadi.YadiStatus;
import java.lang.Comparable;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import oculus.internal.yadi.YadiExecutor.Task;

/* access modifiers changed from: package-private */
public abstract class YadiExecutor<K extends Comparable<K>, T extends Task<K>> {
    private final Map<K, Thread> _activeTasks = new HashMap();
    private final Map<K, T> _pendingTasks = new HashMap();
    private final Thread[] _pool;
    private volatile boolean _running;
    private final PriorityQueue<K> _taskOrder = new PriorityQueue<>();

    /* access modifiers changed from: package-private */
    public interface Task<V extends Comparable<V>> {
        void after(YadiStatus yadiStatus);

        void before();

        void cancel();

        V getIdentifier();
    }

    /* access modifiers changed from: protected */
    public abstract YadiStatus run(T t);

    public YadiExecutor(int i, int i2) {
        this._pool = new Thread[i];
        this._running = true;
        for (int i3 = 0; i3 < i; i3++) {
            this._pool[i3] = new YadiThread(i2);
            this._pool[i3].start();
        }
    }

    /* access modifiers changed from: protected */
    public void wakeAllActiveTasks() {
        synchronized (this._taskOrder) {
            this._taskOrder.notifyAll();
        }
    }

    public void shutdown() {
        if (this._running) {
            this._running = false;
            wakeAllActiveTasks();
            long currentTimeMillis = System.currentTimeMillis() + 5000;
            Thread[] threadArr = this._pool;
            for (Thread thread : threadArr) {
                try {
                    thread.interrupt();
                    thread.join(Math.max(10L, currentTimeMillis - System.currentTimeMillis()));
                } catch (InterruptedException unused) {
                }
            }
            this._taskOrder.clear();
            for (T t : this._pendingTasks.values()) {
                t.cancel();
            }
            this._pendingTasks.clear();
        }
    }

    public K enqueue(T t) {
        K k = (K) t.getIdentifier();
        synchronized (this._taskOrder) {
            if (this._pendingTasks.containsKey(k) || this._activeTasks.containsKey(k)) {
                throw new IllegalArgumentException("task already queued: " + k);
            }
            this._pendingTasks.put(k, t);
            this._taskOrder.offer(k);
            this._taskOrder.notify();
        }
        return k;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v2, resolved type: java.util.Map<K extends java.lang.Comparable<K>, T extends oculus.internal.yadi.YadiExecutor$Task<K>> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: java.util.PriorityQueue<K extends java.lang.Comparable<K>> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void endTask(T t, YadiStatus yadiStatus) {
        Comparable identifier = t.getIdentifier();
        synchronized (this._taskOrder) {
            this._activeTasks.remove(identifier);
            if (yadiStatus == YadiStatus.Paused && this._running) {
                this._pendingTasks.put(identifier, t);
                this._taskOrder.offer(identifier);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private T nextTask(Thread thread) {
        synchronized (this._taskOrder) {
            K k = null;
            do {
                K poll = this._taskOrder.poll();
                while (this._running && poll == null) {
                    try {
                        this._taskOrder.wait();
                        poll = this._taskOrder.poll();
                    } catch (InterruptedException unused) {
                    }
                }
                if (this._pendingTasks.containsKey(poll)) {
                    k = poll;
                }
                if (!this._running) {
                    break;
                }
            } while (k == null);
            if (!this._running) {
                return null;
            }
            this._activeTasks.put(k, thread);
            return this._pendingTasks.remove(k);
        }
    }

    private class YadiThread extends Thread {
        private final int _priority;

        YadiThread(int i) {
            this._priority = i;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: oculus.internal.yadi.YadiExecutor */
        /* JADX WARN: Multi-variable type inference failed */
        public void run() {
            Process.setThreadPriority(this._priority);
            while (YadiExecutor.this._running) {
                Task nextTask = YadiExecutor.this.nextTask(this);
                if (nextTask != null) {
                    nextTask.before();
                    YadiStatus run = YadiExecutor.this.run(nextTask);
                    YadiExecutor.this.endTask(nextTask, run);
                    nextTask.after(run);
                }
            }
        }
    }
}
