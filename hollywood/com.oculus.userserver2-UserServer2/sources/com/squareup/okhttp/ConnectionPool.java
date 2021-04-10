package com.squareup.okhttp;

import X.AnonymousClass06;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.RouteDatabase;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.StreamAllocation;
import com.squareup.okhttp.internal.io.RealConnection;
import java.lang.ref.Reference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public final class ConnectionPool {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final long DEFAULT_KEEP_ALIVE_DURATION_MS = 300000;
    public static final ConnectionPool systemDefault;
    public Runnable cleanupRunnable;
    public final Deque<RealConnection> connections;
    public final Executor executor;
    public final long keepAliveDurationNs;
    public final int maxIdleConnections;
    public final RouteDatabase routeDatabase;

    public long cleanup(long j) {
        synchronized (this) {
            int i = 0;
            RealConnection realConnection = null;
            long j2 = Long.MIN_VALUE;
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
                Util.closeQuietly(realConnection.getSocket());
                return 0;
            }
            if (i > 0) {
                j4 -= j2;
            } else if (i2 <= 0) {
                j4 = -1;
            }
            return j4;
        }
    }

    public synchronized int getConnectionCount() {
        return this.connections.size();
    }

    public synchronized int getHttpConnectionCount() {
        return this.connections.size() - getMultiplexedConnectionCount();
    }

    public synchronized int getIdleConnectionCount() {
        int i;
        i = 0;
        for (RealConnection realConnection : this.connections) {
            if (realConnection.allocations.isEmpty()) {
                i++;
            }
        }
        return i;
    }

    public synchronized int getMultiplexedConnectionCount() {
        int i;
        i = 0;
        for (RealConnection realConnection : this.connections) {
            if (realConnection.isMultiplexed()) {
                i++;
            }
        }
        return i;
    }

    @Deprecated
    public synchronized int getSpdyConnectionCount() {
        return getMultiplexedConnectionCount();
    }

    static {
        long j;
        int i;
        String property = System.getProperty("http.keepAlive");
        String property2 = System.getProperty("http.keepAliveDuration");
        String property3 = System.getProperty("http.maxConnections");
        if (property2 != null) {
            j = Long.parseLong(property2);
        } else {
            j = DEFAULT_KEEP_ALIVE_DURATION_MS;
        }
        if (property != null && !Boolean.parseBoolean(property)) {
            i = 0;
        } else if (property3 != null) {
            i = Integer.parseInt(property3);
        } else {
            i = 5;
        }
        systemDefault = new ConnectionPool(i, j);
    }

    private int pruneAndGetAllocationCount(RealConnection realConnection, long j) {
        List<Reference<StreamAllocation>> list = realConnection.allocations;
        int i = 0;
        while (i < list.size()) {
            if (list.get(i).get() != null) {
                i++;
            } else {
                Logger logger = Internal.logger;
                StringBuilder sb = new StringBuilder("A connection to ");
                sb.append(realConnection.getRoute().address.url);
                sb.append(" was leaked. Did you forget to close a response body?");
                logger.warning(sb.toString());
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
            Util.closeQuietly(((RealConnection) it2.next()).getSocket());
        }
    }

    public RealConnection get(Address address, StreamAllocation streamAllocation) {
        for (RealConnection realConnection : this.connections) {
            if (realConnection.allocations.size() < realConnection.allocationLimit() && address.equals(realConnection.getRoute().address) && !realConnection.noNewStreams) {
                streamAllocation.acquire(realConnection);
                return realConnection;
            }
        }
        return null;
    }

    public void put(RealConnection realConnection) {
        if (this.connections.isEmpty()) {
            this.executor.execute(this.cleanupRunnable);
        }
        this.connections.add(realConnection);
    }

    public static ConnectionPool getDefault() {
        return systemDefault;
    }

    public void setCleanupRunnableForTest(Runnable runnable) {
        this.cleanupRunnable = runnable;
    }

    public ConnectionPool(int i, long j) {
        this(i, j, TimeUnit.MILLISECONDS);
    }

    public ConnectionPool(int i, long j, TimeUnit timeUnit) {
        this.executor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory("OkHttp ConnectionPool", true) {
            /* class com.squareup.okhttp.internal.Util.AnonymousClass1 */

            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, r1);
                thread.setDaemon(r2);
                return thread;
            }
        });
        this.cleanupRunnable = new Runnable() {
            /* class com.squareup.okhttp.ConnectionPool.AnonymousClass1 */

            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0027 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                L_0x0000:
                    com.squareup.okhttp.ConnectionPool r2 = com.squareup.okhttp.ConnectionPool.this
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
                    com.squareup.okhttp.ConnectionPool r2 = com.squareup.okhttp.ConnectionPool.this
                    monitor-enter(r2)
                    com.squareup.okhttp.ConnectionPool r1 = com.squareup.okhttp.ConnectionPool.this     // Catch:{ InterruptedException -> 0x0027 }
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
                throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.ConnectionPool.AnonymousClass1.run():void");
            }
        };
        this.connections = new ArrayDeque();
        this.routeDatabase = new RouteDatabase();
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = timeUnit.toNanos(j);
        if (j <= 0) {
            throw new IllegalArgumentException(AnonymousClass06.A02("keepAliveDuration <= 0: ", j));
        }
    }
}
