package com.android.okhttp;

import com.android.okhttp.internal.RouteDatabase;
import com.android.okhttp.internal.Util;
import com.android.okhttp.internal.http.StreamAllocation;
import com.android.okhttp.internal.io.RealConnection;
import java.lang.ref.Reference;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ConnectionPool {
    private static final ConnectionPool systemDefault;
    private Runnable cleanupRunnable;
    private final Deque connections;
    private final Executor executor;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;
    final RouteDatabase routeDatabase;

    static {
        String property = System.getProperty("http.keepAlive");
        String property2 = System.getProperty("http.keepAliveDuration");
        String property3 = System.getProperty("http.maxConnections");
        long parseLong = property2 != null ? Long.parseLong(property2) : 300000;
        if (property != null && !Boolean.parseBoolean(property)) {
            systemDefault = new ConnectionPool(0, parseLong);
        } else if (property3 != null) {
            systemDefault = new ConnectionPool(Integer.parseInt(property3), parseLong);
        } else {
            systemDefault = new ConnectionPool(5, parseLong);
        }
    }

    public ConnectionPool(int i, long j) {
        this(i, j, TimeUnit.MILLISECONDS);
    }

    public ConnectionPool(int i, long j, TimeUnit timeUnit) {
        this.executor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
        this.cleanupRunnable = new Runnable() {
            /* class com.android.okhttp.ConnectionPool.AnonymousClass1 */

            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x002a */
            @Override // java.lang.Runnable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r6 = this;
                L_0x0000:
                    com.android.okhttp.ConnectionPool r0 = com.android.okhttp.ConnectionPool.this
                    long r1 = java.lang.System.nanoTime()
                    long r0 = r0.cleanup(r1)
                    r2 = -1
                    int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                    if (r2 != 0) goto L_0x0011
                    return
                L_0x0011:
                    r2 = 0
                    int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                    if (r2 <= 0) goto L_0x0000
                    r2 = 1000000(0xf4240, double:4.940656E-318)
                    long r4 = r0 / r2
                    long r2 = r2 * r4
                    long r0 = r0 - r2
                    com.android.okhttp.ConnectionPool r2 = com.android.okhttp.ConnectionPool.this
                    monitor-enter(r2)
                    com.android.okhttp.ConnectionPool r3 = com.android.okhttp.ConnectionPool.this     // Catch:{ InterruptedException -> 0x002a }
                    int r0 = (int) r0     // Catch:{ InterruptedException -> 0x002a }
                    r3.wait(r4, r0)     // Catch:{ InterruptedException -> 0x002a }
                    goto L_0x002a
                L_0x0028:
                    r6 = move-exception
                    goto L_0x002c
                L_0x002a:
                    monitor-exit(r2)     // Catch:{ all -> 0x0028 }
                    goto L_0x0000
                L_0x002c:
                    monitor-exit(r2)     // Catch:{ all -> 0x0028 }
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.okhttp.ConnectionPool.AnonymousClass1.run():void");
            }
        };
        this.connections = new ArrayDeque();
        this.routeDatabase = new RouteDatabase();
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = timeUnit.toNanos(j);
        if (j <= 0) {
            throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
        }
    }

    public static ConnectionPool getDefault() {
        return systemDefault;
    }

    /* access modifiers changed from: package-private */
    public RealConnection get(Address address, StreamAllocation streamAllocation) {
        for (RealConnection realConnection : this.connections) {
            if (realConnection.allocations.size() < realConnection.allocationLimit() && address.equals(realConnection.getRoute().address) && !realConnection.noNewStreams) {
                streamAllocation.acquire(realConnection);
                return realConnection;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void put(RealConnection realConnection) {
        if (this.connections.isEmpty()) {
            this.executor.execute(this.cleanupRunnable);
        }
        this.connections.add(realConnection);
    }

    /* access modifiers changed from: package-private */
    public boolean connectionBecameIdle(RealConnection realConnection) {
        if (realConnection.noNewStreams || this.maxIdleConnections == 0) {
            this.connections.remove(realConnection);
            return true;
        }
        notifyAll();
        return false;
    }

    /* access modifiers changed from: package-private */
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
            if (j2 < this.keepAliveDurationNs) {
                if (i <= this.maxIdleConnections) {
                    if (i > 0) {
                        return this.keepAliveDurationNs - j2;
                    } else if (i2 <= 0) {
                        return -1;
                    } else {
                        return this.keepAliveDurationNs;
                    }
                }
            }
            this.connections.remove(realConnection);
            Util.closeQuietly(realConnection.getSocket());
            return 0;
        }
    }

    private int pruneAndGetAllocationCount(RealConnection realConnection, long j) {
        List list = realConnection.allocations;
        int i = 0;
        while (i < list.size()) {
            if (((Reference) list.get(i)).get() != null) {
                i++;
            } else {
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
}
