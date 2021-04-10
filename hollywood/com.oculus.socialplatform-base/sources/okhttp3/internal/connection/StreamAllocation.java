package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpCodec;

public final class StreamAllocation {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public final Address address;
    public final Object callStackTrace;
    public boolean canceled;
    public HttpCodec codec;
    public RealConnection connection;
    public final ConnectionPool connectionPool;
    public int refusedStreamCount;
    public boolean released;
    public Route route;
    public final RouteSelector routeSelector;

    private Socket deallocate(boolean z, boolean z2, boolean z3) {
        Socket socket;
        if (z3) {
            this.codec = null;
        }
        if (z2) {
            this.released = true;
        }
        RealConnection realConnection = this.connection;
        if (realConnection != null) {
            if (z) {
                realConnection.noNewStreams = true;
            }
            if (this.codec == null && (this.released || realConnection.noNewStreams)) {
                release(realConnection);
                if (this.connection.allocations.isEmpty()) {
                    RealConnection realConnection2 = this.connection;
                    realConnection2.idleAtNanos = System.nanoTime();
                    if (Internal.instance.connectionBecameIdle(this.connectionPool, realConnection2)) {
                        socket = this.connection.socket();
                        this.connection = null;
                        return socket;
                    }
                }
                socket = null;
                this.connection = null;
                return socket;
            }
        }
        return null;
    }

    public synchronized RealConnection connection() {
        return this.connection;
    }

    public static final class StreamAllocationReference extends WeakReference<StreamAllocation> {
        public final Object callStackTrace;

        public StreamAllocationReference(StreamAllocation streamAllocation, Object obj) {
            super(streamAllocation);
            this.callStackTrace = obj;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002b, code lost:
        if (r1 != null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002d, code lost:
        r1 = r5.routeSelector.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0033, code lost:
        r2 = r5.connectionPool;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0035, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r5.route = r1;
        r5.refusedStreamCount = 0;
        r4 = new okhttp3.internal.connection.RealConnection(r5.connectionPool, r1);
        acquire(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0047, code lost:
        if (r5.canceled != false) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0049, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004a, code lost:
        r4.connect(r6, r7, r8, r9);
        routeDatabase().connected(r4.route());
        r2 = null;
        r3 = r5.connectionPool;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005b, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        okhttp3.internal.Internal.instance.put(r5.connectionPool, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0067, code lost:
        if (r4.isMultiplexed() == false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0069, code lost:
        r2 = okhttp3.internal.Internal.instance.deduplicate(r5.connectionPool, r5.address, r5);
        r4 = r5.connection;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0075, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0076, code lost:
        okhttp3.internal.Util.closeQuietly(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0079, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0084, code lost:
        throw new java.io.IOException("Canceled");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.internal.connection.RealConnection findConnection(int r6, int r7, int r8, boolean r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 163
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.StreamAllocation.findConnection(int, int, int, boolean):okhttp3.internal.connection.RealConnection");
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }

    public void acquire(RealConnection realConnection) {
        if (this.connection == null) {
            this.connection = realConnection;
            realConnection.allocations.add(new StreamAllocationReference(this, this.callStackTrace));
            return;
        }
        throw new IllegalStateException();
    }

    public void cancel() {
        HttpCodec httpCodec;
        RealConnection realConnection;
        synchronized (this.connectionPool) {
            this.canceled = true;
            httpCodec = this.codec;
            realConnection = this.connection;
        }
        if (httpCodec != null) {
            httpCodec.cancel();
        } else if (realConnection != null) {
            realConnection.cancel();
        }
    }

    public HttpCodec codec() {
        HttpCodec httpCodec;
        synchronized (this.connectionPool) {
            httpCodec = this.codec;
        }
        return httpCodec;
    }

    public boolean hasMoreRoutes() {
        if (this.route != null || this.routeSelector.hasNext()) {
            return true;
        }
        return false;
    }

    public HttpCodec newStream(OkHttpClient okHttpClient, boolean z) {
        try {
            HttpCodec newCodec = findHealthyConnection(okHttpClient.connectTimeout, okHttpClient.readTimeout, okHttpClient.writeTimeout, okHttpClient.retryOnConnectionFailure, z).newCodec(okHttpClient, this);
            synchronized (this.connectionPool) {
                this.codec = newCodec;
            }
            return newCodec;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    public void noNewStreams() {
        Socket deallocate;
        synchronized (this.connectionPool) {
            deallocate = deallocate(true, false, false);
        }
        Util.closeQuietly(deallocate);
    }

    public Socket releaseAndAcquire(RealConnection realConnection) {
        if (this.codec == null && this.connection.allocations.size() == 1) {
            Reference<StreamAllocation> reference = this.connection.allocations.get(0);
            Socket deallocate = deallocate(true, false, false);
            this.connection = realConnection;
            realConnection.allocations.add(reference);
            return deallocate;
        }
        throw new IllegalStateException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        if (r7.refusedStreamCount <= 1) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void streamFailed(java.io.IOException r8) {
        /*
            r7 = this;
            okhttp3.ConnectionPool r3 = r7.connectionPool
            monitor-enter(r3)
            boolean r0 = r8 instanceof okhttp3.internal.http2.StreamResetException     // Catch:{ all -> 0x004a }
            r6 = 0
            r5 = 0
            r4 = 1
            if (r0 == 0) goto L_0x001a
            okhttp3.internal.http2.StreamResetException r8 = (okhttp3.internal.http2.StreamResetException) r8     // Catch:{ all -> 0x004a }
            okhttp3.internal.http2.ErrorCode r2 = r8.errorCode     // Catch:{ all -> 0x004a }
            okhttp3.internal.http2.ErrorCode r1 = okhttp3.internal.http2.ErrorCode.REFUSED_STREAM     // Catch:{ all -> 0x004a }
            if (r2 != r1) goto L_0x0017
            int r0 = r7.refusedStreamCount     // Catch:{ all -> 0x004a }
            int r0 = r0 + r4
            r7.refusedStreamCount = r0     // Catch:{ all -> 0x004a }
        L_0x0017:
            if (r2 != r1) goto L_0x003c
            goto L_0x0038
        L_0x001a:
            okhttp3.internal.connection.RealConnection r1 = r7.connection     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x0040
            boolean r0 = r1.isMultiplexed()     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0028
            boolean r0 = r8 instanceof okhttp3.internal.http2.ConnectionShutdownException     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0040
        L_0x0028:
            int r0 = r1.successCount     // Catch:{ all -> 0x004a }
            if (r0 != 0) goto L_0x003e
            okhttp3.Route r1 = r7.route     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x003c
            if (r8 == 0) goto L_0x003c
            okhttp3.internal.connection.RouteSelector r0 = r7.routeSelector     // Catch:{ all -> 0x004a }
            r0.connectFailed(r1, r8)     // Catch:{ all -> 0x004a }
            goto L_0x003c
        L_0x0038:
            int r0 = r7.refusedStreamCount     // Catch:{ all -> 0x004a }
            if (r0 <= r4) goto L_0x0040
        L_0x003c:
            r7.route = r6     // Catch:{ all -> 0x004a }
        L_0x003e:
            r0 = 1
            goto L_0x0041
        L_0x0040:
            r0 = 0
        L_0x0041:
            java.net.Socket r0 = r7.deallocate(r0, r5, r4)     // Catch:{ all -> 0x004a }
            monitor-exit(r3)     // Catch:{ all -> 0x004a }
            okhttp3.internal.Util.closeQuietly(r0)
            return
        L_0x004a:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.StreamAllocation.streamFailed(java.io.IOException):void");
    }

    public void streamFinished(boolean z, HttpCodec httpCodec) {
        Socket deallocate;
        synchronized (this.connectionPool) {
            if (httpCodec != null) {
                if (httpCodec == this.codec) {
                    if (!z) {
                        this.connection.successCount++;
                    }
                    deallocate = deallocate(z, false, true);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("expected ");
            sb.append(this.codec);
            sb.append(" but was ");
            sb.append(httpCodec);
            throw new IllegalStateException(sb.toString());
        }
        Util.closeQuietly(deallocate);
    }

    public StreamAllocation(ConnectionPool connectionPool2, Address address2, Object obj) {
        this.connectionPool = connectionPool2;
        this.address = address2;
        this.routeSelector = new RouteSelector(address2, routeDatabase());
        this.callStackTrace = obj;
    }

    private RealConnection findHealthyConnection(int i, int i2, int i3, boolean z, boolean z2) throws IOException {
        RealConnection findConnection;
        int i4;
        while (true) {
            findConnection = findConnection(i, i2, i3, z);
            synchronized (this.connectionPool) {
                i4 = findConnection.successCount;
            }
            if (i4 == 0 || findConnection.isHealthy(z2)) {
                return findConnection;
            }
            noNewStreams();
        }
        return findConnection;
    }

    public String toString() {
        RealConnection connection2 = connection();
        if (connection2 != null) {
            return connection2.toString();
        }
        return this.address.toString();
    }

    private void release(RealConnection realConnection) {
        int size = realConnection.allocations.size();
        for (int i = 0; i < size; i++) {
            if (realConnection.allocations.get(i).get() == this) {
                realConnection.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public void release() {
        Socket deallocate;
        synchronized (this.connectionPool) {
            deallocate = deallocate(false, true, false);
        }
        Util.closeQuietly(deallocate);
    }
}
