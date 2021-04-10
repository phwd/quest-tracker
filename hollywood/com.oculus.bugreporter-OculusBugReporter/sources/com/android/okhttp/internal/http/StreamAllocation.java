package com.android.okhttp.internal.http;

import com.android.okhttp.Address;
import com.android.okhttp.ConnectionPool;
import com.android.okhttp.internal.Internal;
import com.android.okhttp.internal.RouteDatabase;
import com.android.okhttp.internal.Util;
import com.android.okhttp.internal.io.RealConnection;
import com.android.okhttp.okio.Sink;
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
    private boolean canceled;
    private RealConnection connection;
    private final ConnectionPool connectionPool;
    private boolean released;
    private RouteSelector routeSelector;
    private HttpStream stream;

    public StreamAllocation(ConnectionPool connectionPool2, Address address2) {
        this.connectionPool = connectionPool2;
        this.address = address2;
    }

    public HttpStream newStream(int connectTimeout, int readTimeout, int writeTimeout, boolean connectionRetryEnabled, boolean doExtensiveHealthChecks) throws RouteException, IOException {
        HttpStream resultStream;
        try {
            RealConnection resultConnection = findHealthyConnection(connectTimeout, readTimeout, writeTimeout, connectionRetryEnabled, doExtensiveHealthChecks);
            if (resultConnection.framedConnection != null) {
                resultStream = new Http2xStream(this, resultConnection.framedConnection);
            } else {
                resultConnection.getSocket().setSoTimeout(readTimeout);
                resultConnection.source.timeout().timeout((long) readTimeout, TimeUnit.MILLISECONDS);
                resultConnection.sink.timeout().timeout((long) writeTimeout, TimeUnit.MILLISECONDS);
                resultStream = new Http1xStream(this, resultConnection.source, resultConnection.sink);
            }
            synchronized (this.connectionPool) {
                resultConnection.streamCount++;
                this.stream = resultStream;
            }
            return resultStream;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r0.isHealthy(r8) == false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.android.okhttp.internal.io.RealConnection findHealthyConnection(int r4, int r5, int r6, boolean r7, boolean r8) throws java.io.IOException, com.android.okhttp.internal.http.RouteException {
        /*
            r3 = this;
        L_0x0000:
            com.android.okhttp.internal.io.RealConnection r0 = r3.findConnection(r4, r5, r6, r7)
            com.android.okhttp.ConnectionPool r1 = r3.connectionPool
            monitor-enter(r1)
            int r2 = r0.streamCount     // Catch:{ all -> 0x0019 }
            if (r2 != 0) goto L_0x000d
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            return r0
        L_0x000d:
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            boolean r1 = r0.isHealthy(r8)
            if (r1 == 0) goto L_0x0015
            return r0
        L_0x0015:
            r3.connectionFailed()
            goto L_0x0000
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.okhttp.internal.http.StreamAllocation.findHealthyConnection(int, int, int, boolean, boolean):com.android.okhttp.internal.io.RealConnection");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003b, code lost:
        r0 = new com.android.okhttp.internal.io.RealConnection(r9.routeSelector.next());
        acquire(r0);
        r2 = r9.connectionPool;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004c, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        com.android.okhttp.internal.Internal.instance.put(r9.connectionPool, r0);
        r9.connection = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0058, code lost:
        if (r9.canceled != false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005b, code lost:
        r0.connect(r10, r11, r12, r9.address.getConnectionSpecs(), r13);
        routeDatabase().connected(r0.getRoute());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0074, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007c, code lost:
        throw new java.io.IOException("Canceled");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.android.okhttp.internal.io.RealConnection findConnection(int r10, int r11, int r12, boolean r13) throws java.io.IOException, com.android.okhttp.internal.http.RouteException {
        /*
        // Method dump skipped, instructions count: 155
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.okhttp.internal.http.StreamAllocation.findConnection(int, int, int, boolean):com.android.okhttp.internal.io.RealConnection");
    }

    public void streamFinished(HttpStream stream2) {
        synchronized (this.connectionPool) {
            if (stream2 != null) {
                if (stream2 == this.stream) {
                }
            }
            throw new IllegalStateException("expected " + ((Object) this.stream) + " but was " + ((Object) stream2));
        }
        deallocate(false, false, true);
    }

    public HttpStream stream() {
        HttpStream httpStream;
        synchronized (this.connectionPool) {
            httpStream = this.stream;
        }
        return httpStream;
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }

    public synchronized RealConnection connection() {
        return this.connection;
    }

    public void release() {
        deallocate(false, true, false);
    }

    public void noNewStreams() {
        deallocate(true, false, false);
    }

    private void deallocate(boolean noNewStreams, boolean released2, boolean streamFinished) {
        RealConnection connectionToClose = null;
        synchronized (this.connectionPool) {
            if (streamFinished) {
                try {
                    this.stream = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (released2) {
                this.released = true;
            }
            if (this.connection != null) {
                if (noNewStreams) {
                    this.connection.noNewStreams = true;
                }
                if (this.stream == null && (this.released || this.connection.noNewStreams)) {
                    release(this.connection);
                    if (this.connection.streamCount > 0) {
                        this.routeSelector = null;
                    }
                    if (this.connection.allocations.isEmpty()) {
                        this.connection.idleAtNanos = System.nanoTime();
                        if (Internal.instance.connectionBecameIdle(this.connectionPool, this.connection)) {
                            connectionToClose = this.connection;
                        }
                    }
                    this.connection = null;
                }
            }
        }
        if (connectionToClose != null) {
            Util.closeQuietly(connectionToClose.getSocket());
        }
    }

    public void cancel() {
        HttpStream streamToCancel;
        RealConnection connectionToCancel;
        synchronized (this.connectionPool) {
            this.canceled = true;
            streamToCancel = this.stream;
            connectionToCancel = this.connection;
        }
        if (streamToCancel != null) {
            streamToCancel.cancel();
        } else if (connectionToCancel != null) {
            connectionToCancel.cancel();
        }
    }

    private void connectionFailed(IOException e) {
        synchronized (this.connectionPool) {
            if (this.routeSelector != null) {
                if (this.connection.streamCount == 0) {
                    this.routeSelector.connectFailed(this.connection.getRoute(), e);
                } else {
                    this.routeSelector = null;
                }
            }
        }
        connectionFailed();
    }

    public void connectionFailed() {
        deallocate(true, false, true);
    }

    public void acquire(RealConnection connection2) {
        connection2.allocations.add(new WeakReference(this));
    }

    private void release(RealConnection connection2) {
        int size = connection2.allocations.size();
        for (int i = 0; i < size; i++) {
            if (connection2.allocations.get(i).get() == this) {
                connection2.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public boolean recover(RouteException e) {
        if (this.canceled) {
            return false;
        }
        if (this.connection != null) {
            connectionFailed(e.getLastConnectException());
        }
        RouteSelector routeSelector2 = this.routeSelector;
        if ((routeSelector2 == null || routeSelector2.hasNext()) && isRecoverable(e)) {
            return true;
        }
        return false;
    }

    public boolean recover(IOException e, Sink requestBodyOut) {
        RealConnection realConnection = this.connection;
        if (realConnection != null) {
            int streamCount = realConnection.streamCount;
            connectionFailed(e);
            if (streamCount == 1) {
                return false;
            }
        }
        boolean canRetryRequestBody = requestBodyOut == null || (requestBodyOut instanceof RetryableSink);
        RouteSelector routeSelector2 = this.routeSelector;
        return (routeSelector2 == null || routeSelector2.hasNext()) && isRecoverable(e) && canRetryRequestBody;
    }

    private boolean isRecoverable(IOException e) {
        if (!(e instanceof ProtocolException) && !(e instanceof InterruptedIOException)) {
            return true;
        }
        return false;
    }

    private boolean isRecoverable(RouteException e) {
        IOException ioe = e.getLastConnectException();
        if (ioe instanceof ProtocolException) {
            return false;
        }
        if (ioe instanceof InterruptedIOException) {
            return ioe instanceof SocketTimeoutException;
        }
        if ((!(ioe instanceof SSLHandshakeException) || !(ioe.getCause() instanceof CertificateException)) && !(ioe instanceof SSLPeerUnverifiedException)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.address.toString();
    }
}
