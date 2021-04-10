package java.util.concurrent;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ScheduledThreadPoolExecutor extends ThreadPoolExecutor implements ScheduledExecutorService {
    private static final long DEFAULT_KEEPALIVE_MILLIS = 10;
    private static final AtomicLong sequencer = new AtomicLong();
    private volatile boolean continueExistingPeriodicTasksAfterShutdown;
    private volatile boolean executeExistingDelayedTasksAfterShutdown = true;
    volatile boolean removeOnCancel;

    /* access modifiers changed from: private */
    public class ScheduledFutureTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
        int heapIndex;
        RunnableScheduledFuture<V> outerTask = this;
        private final long period;
        private final long sequenceNumber;
        private volatile long time;

        ScheduledFutureTask(Runnable r, V result, long triggerTime, long sequenceNumber2) {
            super(r, result);
            this.time = triggerTime;
            this.period = 0;
            this.sequenceNumber = sequenceNumber2;
        }

        ScheduledFutureTask(Runnable r, V result, long triggerTime, long period2, long sequenceNumber2) {
            super(r, result);
            this.time = triggerTime;
            this.period = period2;
            this.sequenceNumber = sequenceNumber2;
        }

        ScheduledFutureTask(Callable<V> callable, long triggerTime, long sequenceNumber2) {
            super(callable);
            this.time = triggerTime;
            this.period = 0;
            this.sequenceNumber = sequenceNumber2;
        }

        @Override // java.util.concurrent.Delayed
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        public int compareTo(Delayed other) {
            if (other == this) {
                return 0;
            }
            if (other instanceof ScheduledFutureTask) {
                ScheduledFutureTask<?> x = (ScheduledFutureTask) other;
                long diff = this.time - x.time;
                if (diff < 0) {
                    return -1;
                }
                return (diff <= 0 && this.sequenceNumber < x.sequenceNumber) ? -1 : 1;
            }
            long diff2 = getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS);
            if (diff2 < 0) {
                return -1;
            }
            if (diff2 > 0) {
                return 1;
            }
            return 0;
        }

        @Override // java.util.concurrent.RunnableScheduledFuture
        public boolean isPeriodic() {
            return this.period != 0;
        }

        private void setNextRunTime() {
            long p = this.period;
            if (p > 0) {
                this.time += p;
            } else {
                this.time = ScheduledThreadPoolExecutor.this.triggerTime(-p);
            }
        }

        @Override // java.util.concurrent.Future, java.util.concurrent.FutureTask
        public boolean cancel(boolean mayInterruptIfRunning) {
            boolean cancelled = super.cancel(mayInterruptIfRunning);
            if (cancelled && ScheduledThreadPoolExecutor.this.removeOnCancel && this.heapIndex >= 0) {
                ScheduledThreadPoolExecutor.this.remove(this);
            }
            return cancelled;
        }

        @Override // java.util.concurrent.RunnableFuture, java.util.concurrent.FutureTask, java.lang.Runnable
        public void run() {
            boolean periodic = isPeriodic();
            if (!ScheduledThreadPoolExecutor.this.canRunInCurrentRunState(periodic)) {
                cancel(false);
            } else if (!periodic) {
                super.run();
            } else if (super.runAndReset()) {
                setNextRunTime();
                ScheduledThreadPoolExecutor.this.reExecutePeriodic(this.outerTask);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean canRunInCurrentRunState(boolean periodic) {
        boolean z;
        if (periodic) {
            z = this.continueExistingPeriodicTasksAfterShutdown;
        } else {
            z = this.executeExistingDelayedTasksAfterShutdown;
        }
        return isRunningOrShutdown(z);
    }

    private void delayedExecute(RunnableScheduledFuture<?> task) {
        if (isShutdown()) {
            reject(task);
            return;
        }
        super.getQueue().add(task);
        if (!isShutdown() || canRunInCurrentRunState(task.isPeriodic()) || !remove(task)) {
            ensurePrestart();
        } else {
            task.cancel(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void reExecutePeriodic(RunnableScheduledFuture<?> task) {
        if (canRunInCurrentRunState(true)) {
            super.getQueue().add(task);
            if (canRunInCurrentRunState(true) || !remove(task)) {
                ensurePrestart();
            } else {
                task.cancel(false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.concurrent.ThreadPoolExecutor
    public void onShutdown() {
        BlockingQueue<Runnable> q = super.getQueue();
        boolean keepDelayed = getExecuteExistingDelayedTasksAfterShutdownPolicy();
        boolean keepPeriodic = getContinueExistingPeriodicTasksAfterShutdownPolicy();
        if (keepDelayed || keepPeriodic) {
            Object[] array = q.toArray();
            for (Object e : array) {
                if (e instanceof RunnableScheduledFuture) {
                    RunnableScheduledFuture<?> t = (RunnableScheduledFuture) e;
                    if (!t.isPeriodic() ? keepDelayed : keepPeriodic) {
                        if (!t.isCancelled()) {
                        }
                    }
                    if (q.remove(t)) {
                        t.cancel(false);
                    }
                }
            }
        } else {
            Object[] array2 = q.toArray();
            for (Object e2 : array2) {
                if (e2 instanceof RunnableScheduledFuture) {
                    ((RunnableScheduledFuture) e2).cancel(false);
                }
            }
            q.clear();
        }
        tryTerminate();
    }

    /* access modifiers changed from: protected */
    public <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> task) {
        return task;
    }

    /* access modifiers changed from: protected */
    public <V> RunnableScheduledFuture<V> decorateTask(Callable<V> callable, RunnableScheduledFuture<V> task) {
        return task;
    }

    public ScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, Integer.MAX_VALUE, DEFAULT_KEEPALIVE_MILLIS, TimeUnit.MILLISECONDS, new DelayedWorkQueue());
    }

    public ScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, Integer.MAX_VALUE, (long) DEFAULT_KEEPALIVE_MILLIS, TimeUnit.MILLISECONDS, new DelayedWorkQueue(), threadFactory);
    }

    public ScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, Integer.MAX_VALUE, (long) DEFAULT_KEEPALIVE_MILLIS, TimeUnit.MILLISECONDS, new DelayedWorkQueue(), handler);
    }

    public ScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, Integer.MAX_VALUE, DEFAULT_KEEPALIVE_MILLIS, TimeUnit.MILLISECONDS, new DelayedWorkQueue(), threadFactory, handler);
    }

    private long triggerTime(long delay, TimeUnit unit) {
        long j = 0;
        if (delay >= 0) {
            j = delay;
        }
        return triggerTime(unit.toNanos(j));
    }

    /* access modifiers changed from: package-private */
    public long triggerTime(long delay) {
        return System.nanoTime() + (delay < 4611686018427387903L ? delay : overflowFree(delay));
    }

    private long overflowFree(long delay) {
        Delayed head = (Delayed) super.getQueue().peek();
        if (head == null) {
            return delay;
        }
        long headDelay = head.getDelay(TimeUnit.NANOSECONDS);
        if (headDelay >= 0 || delay - headDelay >= 0) {
            return delay;
        }
        return headDelay + Long.MAX_VALUE;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        if (command == null || unit == null) {
            throw new NullPointerException();
        }
        RunnableScheduledFuture<Void> t = decorateTask(command, new ScheduledFutureTask(command, null, triggerTime(delay, unit), sequencer.getAndIncrement()));
        delayedExecute(t);
        return t;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        if (callable == null || unit == null) {
            throw new NullPointerException();
        }
        RunnableScheduledFuture<V> t = decorateTask(callable, new ScheduledFutureTask(callable, triggerTime(delay, unit), sequencer.getAndIncrement()));
        delayedExecute(t);
        return t;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        if (command == null || unit == null) {
            throw new NullPointerException();
        } else if (period > 0) {
            ScheduledFutureTask<Void> sft = new ScheduledFutureTask<>(command, null, triggerTime(initialDelay, unit), unit.toNanos(period), sequencer.getAndIncrement());
            RunnableScheduledFuture<V> decorateTask = decorateTask(command, sft);
            sft.outerTask = decorateTask;
            delayedExecute(decorateTask);
            return decorateTask;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        if (command == null || unit == null) {
            throw new NullPointerException();
        } else if (delay > 0) {
            ScheduledFutureTask<Void> sft = new ScheduledFutureTask<>(command, null, triggerTime(initialDelay, unit), -unit.toNanos(delay), sequencer.getAndIncrement());
            RunnableScheduledFuture<V> decorateTask = decorateTask(command, sft);
            sft.outerTask = decorateTask;
            delayedExecute(decorateTask);
            return decorateTask;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public void execute(Runnable command) {
        schedule(command, 0, TimeUnit.NANOSECONDS);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable task) {
        return schedule(task, 0, TimeUnit.NANOSECONDS);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable task, T result) {
        return schedule(Executors.callable(task, result), 0, TimeUnit.NANOSECONDS);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> task) {
        return schedule(task, 0, TimeUnit.NANOSECONDS);
    }

    public void setContinueExistingPeriodicTasksAfterShutdownPolicy(boolean value) {
        this.continueExistingPeriodicTasksAfterShutdown = value;
        if (!value && isShutdown()) {
            onShutdown();
        }
    }

    public boolean getContinueExistingPeriodicTasksAfterShutdownPolicy() {
        return this.continueExistingPeriodicTasksAfterShutdown;
    }

    public void setExecuteExistingDelayedTasksAfterShutdownPolicy(boolean value) {
        this.executeExistingDelayedTasksAfterShutdown = value;
        if (!value && isShutdown()) {
            onShutdown();
        }
    }

    public boolean getExecuteExistingDelayedTasksAfterShutdownPolicy() {
        return this.executeExistingDelayedTasksAfterShutdown;
    }

    public void setRemoveOnCancelPolicy(boolean value) {
        this.removeOnCancel = value;
    }

    public boolean getRemoveOnCancelPolicy() {
        return this.removeOnCancel;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public void shutdown() {
        super.shutdown();
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return super.shutdownNow();
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    public BlockingQueue<Runnable> getQueue() {
        return super.getQueue();
    }

    static class DelayedWorkQueue extends AbstractQueue<Runnable> implements BlockingQueue<Runnable> {
        private static final int INITIAL_CAPACITY = 16;
        private final Condition available = this.lock.newCondition();
        private Thread leader;
        private final ReentrantLock lock = new ReentrantLock();
        private RunnableScheduledFuture<?>[] queue = new RunnableScheduledFuture[16];
        private int size;

        DelayedWorkQueue() {
        }

        private void setIndex(RunnableScheduledFuture<?> f, int idx) {
            if (f instanceof ScheduledFutureTask) {
                ((ScheduledFutureTask) f).heapIndex = idx;
            }
        }

        private void siftUp(int k, RunnableScheduledFuture<?> key) {
            while (k > 0) {
                int parent = (k - 1) >>> 1;
                RunnableScheduledFuture<?> e = this.queue[parent];
                if (key.compareTo(e) >= 0) {
                    break;
                }
                this.queue[k] = e;
                setIndex(e, k);
                k = parent;
            }
            this.queue[k] = key;
            setIndex(key, k);
        }

        private void siftDown(int k, RunnableScheduledFuture<?> key) {
            int half = this.size >>> 1;
            while (k < half) {
                int child = (k << 1) + 1;
                RunnableScheduledFuture<?>[] runnableScheduledFutureArr = this.queue;
                RunnableScheduledFuture<?> c = runnableScheduledFutureArr[child];
                int right = child + 1;
                if (right < this.size && c.compareTo(runnableScheduledFutureArr[right]) > 0) {
                    child = right;
                    c = this.queue[right];
                }
                if (key.compareTo(c) <= 0) {
                    break;
                }
                this.queue[k] = c;
                setIndex(c, k);
                k = child;
            }
            this.queue[k] = key;
            setIndex(key, k);
        }

        private void grow() {
            int oldCapacity = this.queue.length;
            int newCapacity = (oldCapacity >> 1) + oldCapacity;
            if (newCapacity < 0) {
                newCapacity = Integer.MAX_VALUE;
            }
            this.queue = (RunnableScheduledFuture[]) Arrays.copyOf(this.queue, newCapacity);
        }

        private int indexOf(Object x) {
            if (x == null) {
                return -1;
            }
            if (x instanceof ScheduledFutureTask) {
                int i = ((ScheduledFutureTask) x).heapIndex;
                if (i < 0 || i >= this.size || this.queue[i] != x) {
                    return -1;
                }
                return i;
            }
            for (int i2 = 0; i2 < this.size; i2++) {
                if (x.equals(this.queue[i2])) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
        public boolean contains(Object x) {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            try {
                return indexOf(x) != -1;
            } finally {
                lock2.unlock();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
        public boolean remove(Object x) {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            try {
                int i = indexOf(x);
                if (i < 0) {
                    return false;
                }
                setIndex(this.queue[i], -1);
                int s = this.size - 1;
                this.size = s;
                RunnableScheduledFuture<?> replacement = this.queue[s];
                this.queue[s] = null;
                if (s != i) {
                    siftDown(i, replacement);
                    if (this.queue[i] == replacement) {
                        siftUp(i, replacement);
                    }
                }
                lock2.unlock();
                return true;
            } finally {
                lock2.unlock();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            try {
                return this.size;
            } finally {
                lock2.unlock();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override // java.util.concurrent.BlockingQueue
        public int remainingCapacity() {
            return Integer.MAX_VALUE;
        }

        @Override // java.util.Queue
        public RunnableScheduledFuture<?> peek() {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            try {
                return this.queue[0];
            } finally {
                lock2.unlock();
            }
        }

        /* JADX INFO: finally extract failed */
        public boolean offer(Runnable x) {
            if (x != null) {
                RunnableScheduledFuture<?> e = (RunnableScheduledFuture) x;
                ReentrantLock lock2 = this.lock;
                lock2.lock();
                try {
                    int i = this.size;
                    if (i >= this.queue.length) {
                        grow();
                    }
                    this.size = i + 1;
                    if (i == 0) {
                        this.queue[0] = e;
                        setIndex(e, 0);
                    } else {
                        siftUp(i, e);
                    }
                    if (this.queue[0] == e) {
                        this.leader = null;
                        this.available.signal();
                    }
                    lock2.unlock();
                    return true;
                } catch (Throwable th) {
                    lock2.unlock();
                    throw th;
                }
            } else {
                throw new NullPointerException();
            }
        }

        public void put(Runnable e) {
            offer(e);
        }

        public boolean add(Runnable e) {
            return offer(e);
        }

        public boolean offer(Runnable e, long timeout, TimeUnit unit) {
            return offer(e);
        }

        private RunnableScheduledFuture<?> finishPoll(RunnableScheduledFuture<?> f) {
            int s = this.size - 1;
            this.size = s;
            RunnableScheduledFuture<?>[] runnableScheduledFutureArr = this.queue;
            RunnableScheduledFuture<?> x = runnableScheduledFutureArr[s];
            runnableScheduledFutureArr[s] = null;
            if (s != 0) {
                siftDown(0, x);
            }
            setIndex(f, -1);
            return f;
        }

        @Override // java.util.Queue
        public RunnableScheduledFuture<?> poll() {
            RunnableScheduledFuture<?> runnableScheduledFuture;
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            try {
                RunnableScheduledFuture<?> first = this.queue[0];
                if (first != null) {
                    if (first.getDelay(TimeUnit.NANOSECONDS) <= 0) {
                        runnableScheduledFuture = finishPoll(first);
                        return runnableScheduledFuture;
                    }
                }
                runnableScheduledFuture = null;
                return runnableScheduledFuture;
            } finally {
                lock2.unlock();
            }
        }

        /* Return type fixed from 'java.util.concurrent.RunnableScheduledFuture<?>' to match base method */
        @Override // java.util.concurrent.BlockingQueue
        public Runnable take() throws InterruptedException {
            RunnableScheduledFuture<?> first;
            ReentrantLock lock2 = this.lock;
            lock2.lockInterruptibly();
            while (true) {
                try {
                    first = this.queue[0];
                    if (first == null) {
                        this.available.await();
                    } else {
                        long delay = first.getDelay(TimeUnit.NANOSECONDS);
                        if (delay <= 0) {
                            break;
                        } else if (this.leader != null) {
                            this.available.await();
                        } else {
                            Thread thisThread = Thread.currentThread();
                            this.leader = thisThread;
                            try {
                                this.available.awaitNanos(delay);
                            } finally {
                                if (this.leader == thisThread) {
                                    this.leader = null;
                                }
                            }
                        }
                    }
                } finally {
                    if (this.leader == null && this.queue[0] != null) {
                        this.available.signal();
                    }
                    lock2.unlock();
                }
            }
            return finishPoll(first);
        }

        /* Return type fixed from 'java.util.concurrent.RunnableScheduledFuture<?>' to match base method */
        @Override // java.util.concurrent.BlockingQueue
        public Runnable poll(long timeout, TimeUnit unit) throws InterruptedException {
            long nanos = unit.toNanos(timeout);
            ReentrantLock lock2 = this.lock;
            lock2.lockInterruptibly();
            while (true) {
                try {
                    RunnableScheduledFuture<?> first = this.queue[0];
                    if (first != null) {
                        long delay = first.getDelay(TimeUnit.NANOSECONDS);
                        if (delay <= 0) {
                            RunnableScheduledFuture<?> finishPoll = finishPoll(first);
                            if (this.leader == null && this.queue[0] != null) {
                                this.available.signal();
                            }
                            lock2.unlock();
                            return finishPoll;
                        } else if (nanos <= 0) {
                            if (this.leader == null && this.queue[0] != null) {
                                this.available.signal();
                            }
                            lock2.unlock();
                            return null;
                        } else if (nanos < delay || this.leader != null) {
                            nanos = this.available.awaitNanos(nanos);
                        } else {
                            Thread thisThread = Thread.currentThread();
                            this.leader = thisThread;
                            try {
                                nanos -= delay - this.available.awaitNanos(delay);
                            } finally {
                                if (this.leader == thisThread) {
                                    this.leader = null;
                                }
                            }
                        }
                    } else if (nanos <= 0) {
                        return null;
                    } else {
                        nanos = this.available.awaitNanos(nanos);
                    }
                } finally {
                    if (this.leader == null && this.queue[0] != null) {
                        this.available.signal();
                    }
                    lock2.unlock();
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue
        public void clear() {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            for (int i = 0; i < this.size; i++) {
                try {
                    RunnableScheduledFuture<?> t = this.queue[i];
                    if (t != null) {
                        this.queue[i] = null;
                        setIndex(t, -1);
                    }
                } finally {
                    lock2.unlock();
                }
            }
            this.size = 0;
        }

        private RunnableScheduledFuture<?> peekExpired() {
            RunnableScheduledFuture<?> first = this.queue[0];
            if (first == null || first.getDelay(TimeUnit.NANOSECONDS) > 0) {
                return null;
            }
            return first;
        }

        @Override // java.util.concurrent.BlockingQueue
        public int drainTo(Collection<? super Runnable> c) {
            if (c == null) {
                throw new NullPointerException();
            } else if (c != this) {
                ReentrantLock lock2 = this.lock;
                lock2.lock();
                int n = 0;
                while (true) {
                    try {
                        RunnableScheduledFuture<?> first = peekExpired();
                        if (first == null) {
                            return n;
                        }
                        c.add(first);
                        finishPoll(first);
                        n++;
                    } finally {
                        lock2.unlock();
                    }
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        @Override // java.util.concurrent.BlockingQueue
        public int drainTo(Collection<? super Runnable> c, int maxElements) {
            if (c == null) {
                throw new NullPointerException();
            } else if (c == this) {
                throw new IllegalArgumentException();
            } else if (maxElements <= 0) {
                return 0;
            } else {
                ReentrantLock lock2 = this.lock;
                lock2.lock();
                int n = 0;
                while (n < maxElements) {
                    try {
                        RunnableScheduledFuture<?> first = peekExpired();
                        if (first == null) {
                            break;
                        }
                        c.add(first);
                        finishPoll(first);
                        n++;
                    } catch (Throwable th) {
                        lock2.unlock();
                        throw th;
                    }
                }
                lock2.unlock();
                return n;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            try {
                return Arrays.copyOf(this.queue, this.size, Object[].class);
            } finally {
                lock2.unlock();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] a) {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            try {
                if (a.length < this.size) {
                    return (T[]) Arrays.copyOf(this.queue, this.size, a.getClass());
                }
                System.arraycopy(this.queue, 0, a, 0, this.size);
                if (a.length > this.size) {
                    a[this.size] = null;
                }
                lock2.unlock();
                return a;
            } finally {
                lock2.unlock();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Runnable> iterator() {
            return new Itr((RunnableScheduledFuture[]) Arrays.copyOf(this.queue, this.size));
        }

        private class Itr implements Iterator<Runnable> {
            final RunnableScheduledFuture<?>[] array;
            int cursor;
            int lastRet = -1;

            Itr(RunnableScheduledFuture<?>[] array2) {
                this.array = array2;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.cursor < this.array.length;
            }

            @Override // java.util.Iterator
            public Runnable next() {
                int i = this.cursor;
                RunnableScheduledFuture<?>[] runnableScheduledFutureArr = this.array;
                if (i < runnableScheduledFutureArr.length) {
                    this.lastRet = i;
                    this.cursor = i + 1;
                    return runnableScheduledFutureArr[i];
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                int i = this.lastRet;
                if (i >= 0) {
                    DelayedWorkQueue.this.remove(this.array[i]);
                    this.lastRet = -1;
                    return;
                }
                throw new IllegalStateException();
            }
        }
    }
}
