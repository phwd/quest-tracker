package sun.nio.ch;

import java.io.IOException;
import java.nio.channels.AsynchronousChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* access modifiers changed from: package-private */
public final class PendingFuture<V, A> implements Future<V> {
    private static final CancellationException CANCELLED = new CancellationException();
    private final A attachment;
    private final AsynchronousChannel channel;
    private volatile Object context;
    private volatile Throwable exc;
    private final CompletionHandler<V, ? super A> handler;
    private volatile boolean haveResult;
    private CountDownLatch latch;
    private volatile V result;
    private Future<?> timeoutTask;

    PendingFuture(AsynchronousChannel channel2, CompletionHandler<V, ? super A> handler2, A attachment2, Object context2) {
        this.channel = channel2;
        this.handler = handler2;
        this.attachment = attachment2;
        this.context = context2;
    }

    PendingFuture(AsynchronousChannel channel2, CompletionHandler<V, ? super A> handler2, A attachment2) {
        this.channel = channel2;
        this.handler = handler2;
        this.attachment = attachment2;
    }

    PendingFuture(AsynchronousChannel channel2) {
        this(channel2, null, null);
    }

    PendingFuture(AsynchronousChannel channel2, Object context2) {
        this(channel2, null, null, context2);
    }

    /* access modifiers changed from: package-private */
    public AsynchronousChannel channel() {
        return this.channel;
    }

    /* access modifiers changed from: package-private */
    public CompletionHandler<V, ? super A> handler() {
        return this.handler;
    }

    /* access modifiers changed from: package-private */
    public A attachment() {
        return this.attachment;
    }

    /* access modifiers changed from: package-private */
    public void setContext(Object context2) {
        this.context = context2;
    }

    /* access modifiers changed from: package-private */
    public Object getContext() {
        return this.context;
    }

    /* access modifiers changed from: package-private */
    public void setTimeoutTask(Future<?> task) {
        synchronized (this) {
            if (this.haveResult) {
                task.cancel(false);
            } else {
                this.timeoutTask = task;
            }
        }
    }

    private boolean prepareForWait() {
        synchronized (this) {
            if (this.haveResult) {
                return false;
            }
            if (this.latch == null) {
                this.latch = new CountDownLatch(1);
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void setResult(V res) {
        synchronized (this) {
            if (!this.haveResult) {
                this.result = res;
                this.haveResult = true;
                if (this.timeoutTask != null) {
                    this.timeoutTask.cancel(false);
                }
                if (this.latch != null) {
                    this.latch.countDown();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setFailure(Throwable x) {
        if (!(x instanceof IOException) && !(x instanceof SecurityException)) {
            x = new IOException(x);
        }
        synchronized (this) {
            if (!this.haveResult) {
                this.exc = x;
                this.haveResult = true;
                if (this.timeoutTask != null) {
                    this.timeoutTask.cancel(false);
                }
                if (this.latch != null) {
                    this.latch.countDown();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setResult(V res, Throwable x) {
        if (x == null) {
            setResult(res);
        } else {
            setFailure(x);
        }
    }

    @Override // java.util.concurrent.Future
    public V get() throws ExecutionException, InterruptedException {
        if (!this.haveResult && prepareForWait()) {
            this.latch.await();
        }
        if (this.exc == null) {
            return this.result;
        }
        if (this.exc == CANCELLED) {
            throw new CancellationException();
        }
        throw new ExecutionException(this.exc);
    }

    @Override // java.util.concurrent.Future
    public V get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
        if (!this.haveResult && prepareForWait() && !this.latch.await(timeout, unit)) {
            throw new TimeoutException();
        } else if (this.exc == null) {
            return this.result;
        } else {
            if (this.exc == CANCELLED) {
                throw new CancellationException();
            }
            throw new ExecutionException(this.exc);
        }
    }

    /* access modifiers changed from: package-private */
    public Throwable exception() {
        if (this.exc != CANCELLED) {
            return this.exc;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public V value() {
        return this.result;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.exc == CANCELLED;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.haveResult;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        if (r4 == false) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        channel().close();
     */
    @Override // java.util.concurrent.Future
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean cancel(boolean r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.haveResult     // Catch:{ all -> 0x003d }
            r1 = 0
            if (r0 == 0) goto L_0x0008
            monitor-exit(r3)     // Catch:{ all -> 0x003d }
            return r1
        L_0x0008:
            java.nio.channels.AsynchronousChannel r0 = r3.channel()     // Catch:{ all -> 0x003d }
            boolean r0 = r0 instanceof sun.nio.ch.Cancellable     // Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x0019
            java.nio.channels.AsynchronousChannel r0 = r3.channel()     // Catch:{ all -> 0x003d }
            sun.nio.ch.Cancellable r0 = (sun.nio.ch.Cancellable) r0     // Catch:{ all -> 0x003d }
            r0.onCancel(r3)     // Catch:{ all -> 0x003d }
        L_0x0019:
            java.util.concurrent.CancellationException r0 = sun.nio.ch.PendingFuture.CANCELLED     // Catch:{ all -> 0x003d }
            r3.exc = r0     // Catch:{ all -> 0x003d }
            r0 = 1
            r3.haveResult = r0     // Catch:{ all -> 0x003d }
            java.util.concurrent.Future<?> r2 = r3.timeoutTask     // Catch:{ all -> 0x003d }
            if (r2 == 0) goto L_0x0029
            java.util.concurrent.Future<?> r2 = r3.timeoutTask     // Catch:{ all -> 0x003d }
            r2.cancel(r1)     // Catch:{ all -> 0x003d }
        L_0x0029:
            monitor-exit(r3)     // Catch:{ all -> 0x003d }
            if (r4 == 0) goto L_0x0035
            java.nio.channels.AsynchronousChannel r1 = r3.channel()     // Catch:{ IOException -> 0x0034 }
            r1.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0035
        L_0x0034:
            r1 = move-exception
        L_0x0035:
            java.util.concurrent.CountDownLatch r1 = r3.latch
            if (r1 == 0) goto L_0x003c
            r1.countDown()
        L_0x003c:
            return r0
        L_0x003d:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.PendingFuture.cancel(boolean):boolean");
    }
}
