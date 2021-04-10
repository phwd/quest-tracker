package okhttp3;

import X.AnonymousClass006;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.platform.Platform;

public final class ConnectionPool {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Executor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactory("OkHttp ConnectionPool", true) {
        /* class okhttp3.internal.Util.AnonymousClass1 */

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, r1);
            thread.setDaemon(r2);
            return thread;
        }
    });
    public final Runnable cleanupRunnable;
    public boolean cleanupRunning;
    public final Deque<RealConnection> connections;
    public final long keepAliveDurationNs;
    public final int maxIdleConnections;
    public final RouteDatabase routeDatabase;

    public long cleanup(long j) {
        synchronized (this) {
            RealConnection realConnection = null;
            long j2 = Long.MIN_VALUE;
            int i = 0;
            int i2 = 0;
            for (RealConnection realConnection2 : this.connections) {
                if (pruneAndGetAllocationCount(realConnection2, j) > 0) {
                    i2++;
                } else {
                    i++;
                    long j3 = j - realConnection2.idleAtNanos;
                    if (j3 > j2) {
                        realConnection = realConnection2;
                        j2 = j3;
                    }
                }
            }
            long j4 = this.keepAliveDurationNs;
            if (j2 >= j4 || i > this.maxIdleConnections) {
                this.connections.remove(realConnection);
                Util.closeQuietly(realConnection.socket());
                return 0;
            }
            if (i > 0) {
                j4 -= j2;
            } else if (i2 <= 0) {
                this.cleanupRunning = false;
                j4 = -1;
            }
            return j4;
        }
    }

    public synchronized int connectionCount() {
        return this.connections.size();
    }

    public synchronized int idleConnectionCount() {
        int i;
        i = 0;
        for (RealConnection realConnection : this.connections) {
            if (realConnection.allocations.isEmpty()) {
                i++;
            }
        }
        return i;
    }

    private int pruneAndGetAllocationCount(RealConnection realConnection, long j) {
        List<Reference<StreamAllocation>> list = realConnection.allocations;
        int i = 0;
        while (i < list.size()) {
            Reference<StreamAllocation> reference = list.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                StringBuilder sb = new StringBuilder("A connection to ");
                sb.append(realConnection.route().address.url);
                sb.append(" was leaked. Did you forget to close a response body?");
                Platform.PLATFORM.logCloseableLeak(sb.toString(), ((StreamAllocation.StreamAllocationReference) reference).callStackTrace);
                list.remove(i);
                realConnection.noNewStreams = true;
                if (list.isEmpty()) {
                    realConnection.idleAtNanos = j - this.keepAliveDurationNs;
                    return 0;
                }
            }
        }
        return list.size();
    }

    public boolean connectionBecameIdle(RealConnection realConnection) {
        if (realConnection.noNewStreams || this.maxIdleConnections == 0) {
            this.connections.remove(realConnection);
            return true;
        }
        notifyAll();
        return false;
    }

    public Socket deduplicate(Address address, StreamAllocation streamAllocation) {
        for (RealConnection realConnection : this.connections) {
            if (realConnection.isEligible(address) && realConnection.isMultiplexed() && realConnection != streamAllocation.connection()) {
                return streamAllocation.releaseAndAcquire(realConnection);
            }
        }
        return null;
    }

    public void evictAll() {
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<RealConnection> it = this.connections.iterator();
            while (it.hasNext()) {
                RealConnection next = it.next();
                if (next.allocations.isEmpty()) {
                    next.noNewStreams = true;
                    arrayList.add(next);
                    it.remove();
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Util.closeQuietly(((RealConnection) it2.next()).socket());
        }
    }

    public RealConnection get(Address address, StreamAllocation streamAllocation) {
        for (RealConnection realConnection : this.connections) {
            if (realConnection.isEligible(address)) {
                streamAllocation.acquire(realConnection);
                return realConnection;
            }
        }
        return null;
    }

    public void put(RealConnection realConnection) {
        if (!this.cleanupRunning) {
            this.cleanupRunning = true;
            executor.execute(this.cleanupRunnable);
        }
        this.connections.add(realConnection);
    }

    public ConnectionPool() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public ConnectionPool(int i, long j, TimeUnit timeUnit) {
        this.cleanupRunnable = new Runnable() {
            /* class okhttp3.ConnectionPool.AnonymousClass1 */

            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0027 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                L_0x0000:
                    okhttp3.ConnectionPool r2 = okhttp3.ConnectionPool.this
                    long r0 = java.lang.System.nanoTime()
                    long r5 = r2.cleanup(r0)
                    r1 = -1
                    int r0 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
                    if (r0 != 0) goto L_0x0011
                    return
                L_0x0011:
                    r1 = 0
                    int r0 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
                    if (r0 <= 0) goto L_0x0000
                    r0 = 1000000(0xf4240, double:4.940656E-318)
                    long r3 = r5 / r0
                    long r0 = r0 * r3
                    long r5 = r5 - r0
                    okhttp3.ConnectionPool r2 = okhttp3.ConnectionPool.this
                    monitor-enter(r2)
                    okhttp3.ConnectionPool r1 = okhttp3.ConnectionPool.this     // Catch:{ InterruptedException -> 0x0027 }
                    int r0 = (int) r5     // Catch:{ InterruptedException -> 0x0027 }
                    r1.wait(r3, r0)     // Catch:{ InterruptedException -> 0x0027 }
                L_0x0027:
                    monitor-exit(r2)     // Catch:{ all -> 0x0029 }
                    goto L_0x0000
                L_0x0029:
                    r0 = move-exception
                    monitor-exit(r2)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: okhttp3.ConnectionPool.AnonymousClass1.run():void");
            }
        };
        this.connections = new ArrayDeque();
        this.routeDatabase = new RouteDatabase();
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = timeUnit.toNanos(j);
        if (j <= 0) {
            throw new IllegalArgumentException(AnonymousClass006.A06("keepAliveDuration <= 0: ", j));
        }
    }
}
