package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Address;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.RouteDatabase;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.io.RealConnection;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.WeakReference;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

public final class StreamAllocation {
    public final Address address;
    public boolean canceled;
    public RealConnection connection;
    public final ConnectionPool connectionPool;
    public boolean released;
    public RouteSelector routeSelector;
    public HttpStream stream;

    public synchronized RealConnection connection() {
        return this.connection;
    }

    public void noNewStreams() {
        deallocate(true, false, false);
    }

    private void deallocate(boolean z, boolean z2, boolean z3) {
        RealConnection realConnection;
        synchronized (this.connectionPool) {
            if (z3) {
                this.stream = null;
            }
            if (z2) {
                this.released = true;
            }
            RealConnection realConnection2 = this.connection;
            if (realConnection2 != null) {
                if (z) {
                    realConnection2.noNewStreams = true;
                }
                if (this.stream == null && (this.released || realConnection2.noNewStreams)) {
                    release(realConnection2);
                    RealConnection realConnection3 = this.connection;
                    if (realConnection3.streamCount > 0) {
                        this.routeSelector = null;
                    }
                    if (realConnection3.allocations.isEmpty()) {
                        RealConnection realConnection4 = this.connection;
                        realConnection4.idleAtNanos = System.nanoTime();
                        if (Internal.instance.connectionBecameIdle(this.connectionPool, realConnection4)) {
                            realConnection = this.connection;
                            this.connection = null;
                        }
                    }
                    realConnection = null;
                    this.connection = null;
                }
            }
            realConnection = null;
        }
        if (realConnection != null) {
            Util.closeQuietly(realConnection.getSocket());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003b, code lost:
        r3 = new com.squareup.okhttp.internal.io.RealConnection(r9.routeSelector.next());
        acquire(r3);
        r2 = r9.connectionPool;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        com.squareup.okhttp.internal.Internal.instance.put(r9.connectionPool, r3);
        r9.connection = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0057, code lost:
        if (r9.canceled != false) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0059, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        r3.connect(r10, r11, r12, r9.address.connectionSpecs, r13);
        routeDatabase().connected(r3.getRoute());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0070, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0078, code lost:
        throw new java.io.IOException("Canceled");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.squareup.okhttp.internal.io.RealConnection findConnection(int r10, int r11, int r12, boolean r13) throws java.io.IOException, com.squareup.okhttp.internal.http.RouteException {
        /*
        // Method dump skipped, instructions count: 151
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.StreamAllocation.findConnection(int, int, int, boolean):com.squareup.okhttp.internal.io.RealConnection");
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }

    public void acquire(RealConnection realConnection) {
        realConnection.allocations.add(new WeakReference(this));
    }

    public void cancel() {
        HttpStream httpStream;
        RealConnection realConnection;
        synchronized (this.connectionPool) {
            this.canceled = true;
            httpStream = this.stream;
            realConnection = this.connection;
        }
        if (httpStream != null) {
            httpStream.cancel();
        } else if (realConnection != null) {
            realConnection.cancel();
        }
    }

    public HttpStream stream() {
        HttpStream httpStream;
        synchronized (this.connectionPool) {
            httpStream = this.stream;
        }
        return httpStream;
    }

    public void streamFinished(HttpStream httpStream) {
        synchronized (this.connectionPool) {
            if (httpStream != null) {
                if (httpStream == this.stream) {
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("expected ");
            sb.append(this.stream);
            sb.append(" but was ");
            sb.append(httpStream);
            throw new IllegalStateException(sb.toString());
        }
        deallocate(false, false, true);
    }

    public String toString() {
        return this.address.toString();
    }

    public StreamAllocation(ConnectionPool connectionPool2, Address address2) {
        this.connectionPool = connectionPool2;
        this.address = address2;
    }

    private RealConnection findHealthyConnection(int i, int i2, int i3, boolean z, boolean z2) throws IOException, RouteException {
        RealConnection findConnection;
        int i4;
        while (true) {
            findConnection = findConnection(i, i2, i3, z);
            synchronized (this.connectionPool) {
                i4 = findConnection.streamCount;
            }
            if (i4 == 0 || findConnection.isHealthy(z2)) {
                return findConnection;
            }
            connectionFailed();
        }
        return findConnection;
    }

    public HttpStream newStream(int i, int i2, int i3, boolean z, boolean z2) throws RouteException, IOException {
        HttpStream http1xStream;
        try {
            RealConnection findHealthyConnection = findHealthyConnection(i, i2, i3, z, z2);
            if (findHealthyConnection.framedConnection != null) {
                http1xStream = new Http2xStream(this, findHealthyConnection.framedConnection);
            } else {
                findHealthyConnection.getSocket().setSoTimeout(i2);
                findHealthyConnection.source.timeout().timeout((long) i2, TimeUnit.MILLISECONDS);
                findHealthyConnection.sink.timeout().timeout((long) i3, TimeUnit.MILLISECONDS);
                http1xStream = new Http1xStream(this, findHealthyConnection.source, findHealthyConnection.sink);
            }
            synchronized (this.connectionPool) {
                findHealthyConnection.streamCount++;
                this.stream = http1xStream;
            }
            return http1xStream;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    private void connectionFailed(IOException iOException) {
        synchronized (this.connectionPool) {
            RouteSelector routeSelector2 = this.routeSelector;
            if (routeSelector2 != null) {
                RealConnection realConnection = this.connection;
                if (realConnection.streamCount == 0) {
                    routeSelector2.connectFailed(realConnection.getRoute(), iOException);
                } else {
                    this.routeSelector = null;
                }
            }
        }
        connectionFailed();
    }

    private boolean isRecoverable(RouteException routeException) {
        IOException iOException = routeException.lastException;
        if (!(iOException instanceof ProtocolException)) {
            if (iOException instanceof InterruptedIOException) {
                return iOException instanceof SocketTimeoutException;
            }
            if ((!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRecoverable(IOException iOException) {
        if ((iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        return true;
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

    public void connectionFailed() {
        deallocate(true, false, true);
    }

    public boolean recover(RouteException routeException) {
        if (this.connection != null) {
            connectionFailed(routeException.lastException);
        }
        RouteSelector routeSelector2 = this.routeSelector;
        return (routeSelector2 == null || routeSelector2.hasNext()) && isRecoverable(routeException);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000b, code lost:
        if (r0 == 1) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        if ((r6 instanceof com.squareup.okhttp.internal.http.RetryableSink) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean recover(java.io.IOException r5, okio.Sink r6) {
        /*
            r4 = this;
            com.squareup.okhttp.internal.io.RealConnection r0 = r4.connection
            r3 = 0
            r2 = 1
            if (r0 == 0) goto L_0x000e
            int r0 = r0.streamCount
            r4.connectionFailed(r5)
            if (r0 != r2) goto L_0x000e
        L_0x000d:
            return r3
        L_0x000e:
            if (r6 == 0) goto L_0x0015
            boolean r0 = r6 instanceof com.squareup.okhttp.internal.http.RetryableSink
            r1 = 0
            if (r0 == 0) goto L_0x0016
        L_0x0015:
            r1 = 1
        L_0x0016:
            com.squareup.okhttp.internal.http.RouteSelector r0 = r4.routeSelector
            if (r0 == 0) goto L_0x0020
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L_0x000d
        L_0x0020:
            boolean r0 = r4.isRecoverable(r5)
            if (r0 == 0) goto L_0x000d
            if (r1 == 0) goto L_0x000d
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.http.StreamAllocation.recover(java.io.IOException, okio.Sink):boolean");
    }

    public void release() {
        deallocate(false, true, false);
    }
}
