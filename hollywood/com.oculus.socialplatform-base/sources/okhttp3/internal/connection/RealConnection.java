package okhttp3.internal.connection;

import X.AnonymousClass006;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.systemdialog.CommonSystemDialogActions;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Address;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.ws.RealWebSocket;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.RealBufferedSink;
import okio.Source;

public final class RealConnection extends Http2Connection.Listener implements Connection {
    public int allocationLimit = 1;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    public final ConnectionPool connectionPool;
    public Handshake handshake;
    public Http2Connection http2Connection;
    public long idleAtNanos = RecyclerView.FOREVER_NS;
    public boolean noNewStreams;
    public Protocol protocol;
    public Socket rawSocket;
    public final Route route;
    public BufferedSink sink;
    public Socket socket;
    public BufferedSource source;
    public int successCount;

    public RealWebSocket.Streams newWebSocketStreams(final StreamAllocation streamAllocation) {
        return new RealWebSocket.Streams(true, this.source, this.sink) {
            /* class okhttp3.internal.connection.RealConnection.AnonymousClass1 */

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                StreamAllocation streamAllocation = streamAllocation;
                streamAllocation.streamFinished(true, streamAllocation.codec());
            }
        };
    }

    private void connectSocket(int i, int i2) throws IOException {
        Socket createSocket;
        Route route2 = this.route;
        Proxy proxy = route2.proxy;
        Address address = route2.address;
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) {
            createSocket = address.socketFactory.createSocket();
        } else {
            createSocket = new Socket(proxy);
        }
        this.rawSocket = createSocket;
        createSocket.setSoTimeout(i2);
        try {
            Platform.PLATFORM.connectSocket(this.rawSocket, this.route.inetSocketAddress, i);
            this.source = Okio.buffer(Okio.source(this.rawSocket));
            this.sink = new RealBufferedSink(Okio.sink(this.rawSocket));
        } catch (ConnectException e) {
            StringBuilder sb = new StringBuilder("Failed to connect to ");
            sb.append(this.route.inetSocketAddress);
            ConnectException connectException = new ConnectException(sb.toString());
            connectException.initCause(e);
            throw connectException;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00dc A[Catch:{ all -> 0x00e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e2 A[Catch:{ all -> 0x00e3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void connectTls(okhttp3.internal.connection.ConnectionSpecSelector r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 242
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connectTls(okhttp3.internal.connection.ConnectionSpecSelector):void");
    }

    private Request createTunnel(int i, int i2, Request request, HttpUrl httpUrl) throws IOException {
        Response build;
        String A09 = AnonymousClass006.A09("CONNECT ", Util.hostHeader(httpUrl, true), " HTTP/1.1");
        do {
            BufferedSource bufferedSource = this.source;
            Http1Codec http1Codec = new Http1Codec(null, null, bufferedSource, this.sink);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            bufferedSource.timeout().timeout((long) i, timeUnit);
            this.sink.timeout().timeout((long) i2, timeUnit);
            http1Codec.writeRequest(request.headers, A09);
            http1Codec.finishRequest();
            Response.Builder readResponseHeaders = http1Codec.readResponseHeaders(false);
            readResponseHeaders.request = request;
            build = readResponseHeaders.build();
            long contentLength = HttpHeaders.contentLength(build.headers);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = http1Codec.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            int i3 = build.code;
            if (i3 != 200) {
                if (i3 == 407) {
                    Route route2 = this.route;
                    request = route2.address.proxyAuthenticator.authenticate(route2, build);
                    if (request == null) {
                        throw new IOException("Failed to authenticate with proxy");
                    }
                } else {
                    throw new IOException(AnonymousClass006.A03("Unexpected response code for CONNECT: ", i3));
                }
            } else if (this.source.buffer().exhausted() && this.sink.buffer().exhausted()) {
                return null;
            } else {
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
        } while (!CommonSystemDialogActions.CLOSE.equalsIgnoreCase(build.header("Connection")));
        return request;
    }

    private Request createTunnelRequest() {
        Request.Builder builder = new Request.Builder();
        HttpUrl httpUrl = this.route.address.url;
        builder.url(httpUrl);
        builder.header("Host", Util.hostHeader(httpUrl, true));
        builder.header("Proxy-Connection", "Keep-Alive");
        builder.header("User-Agent", "okhttp/3.6.0");
        return builder.build();
    }

    private void establishProtocol(ConnectionSpecSelector connectionSpecSelector) throws IOException {
        if (this.route.address.sslSocketFactory == null) {
            this.protocol = Protocol.HTTP_1_1;
            this.socket = this.rawSocket;
            return;
        }
        connectTls(connectionSpecSelector);
        if (this.protocol == Protocol.HTTP_2) {
            this.socket.setSoTimeout(0);
            Http2Connection.Builder builder = new Http2Connection.Builder(true);
            builder.socket(this.socket, this.route.address.url.host, this.source, this.sink);
            builder.listener = this;
            Http2Connection http2Connection2 = new Http2Connection(builder);
            this.http2Connection = http2Connection2;
            http2Connection2.start(true);
        }
    }

    public static RealConnection testConnection(ConnectionPool connectionPool2, Route route2, Socket socket2, long j) {
        RealConnection realConnection = new RealConnection(connectionPool2, route2);
        realConnection.socket = socket2;
        realConnection.idleAtNanos = j;
        return realConnection;
    }

    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0055 A[Catch:{ IOException -> 0x0060 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005c A[Catch:{ IOException -> 0x0060 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a1 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(int r6, int r7, int r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 171
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnection.connect(int, int, int, boolean):void");
    }

    public boolean isEligible(Address address) {
        if (this.allocations.size() >= this.allocationLimit || !address.equals(route().address) || this.noNewStreams) {
            return false;
        }
        return true;
    }

    /* JADX INFO: finally extract failed */
    public boolean isHealthy(boolean z) {
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            return false;
        }
        Http2Connection http2Connection2 = this.http2Connection;
        if (http2Connection2 != null) {
            return !http2Connection2.isShutdown();
        }
        if (z) {
            try {
                int soTimeout = this.socket.getSoTimeout();
                try {
                    this.socket.setSoTimeout(1);
                    if (this.source.exhausted()) {
                        this.socket.setSoTimeout(soTimeout);
                        return false;
                    }
                    this.socket.setSoTimeout(soTimeout);
                    return true;
                } catch (Throwable th) {
                    this.socket.setSoTimeout(soTimeout);
                    throw th;
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    public boolean isMultiplexed() {
        if (this.http2Connection != null) {
            return true;
        }
        return false;
    }

    public HttpCodec newCodec(OkHttpClient okHttpClient, StreamAllocation streamAllocation) throws SocketException {
        Http2Connection http2Connection2 = this.http2Connection;
        if (http2Connection2 != null) {
            return new Http2Codec(okHttpClient, streamAllocation, http2Connection2);
        }
        this.socket.setSoTimeout(okHttpClient.readTimeout);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.source.timeout().timeout((long) okHttpClient.readTimeout, timeUnit);
        this.sink.timeout().timeout((long) okHttpClient.writeTimeout, timeUnit);
        return new Http1Codec(okHttpClient, streamAllocation, this.source, this.sink);
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void onSettings(Http2Connection http2Connection2) {
        synchronized (this.connectionPool) {
            this.allocationLimit = http2Connection2.maxConcurrentStreams();
        }
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void onStream(Http2Stream http2Stream) throws IOException {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }

    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Connection{");
        Route route2 = this.route;
        HttpUrl httpUrl = route2.address.url;
        sb.append(httpUrl.host);
        sb.append(":");
        sb.append(httpUrl.port);
        sb.append(", proxy=");
        sb.append(route2.proxy);
        sb.append(" hostAddress=");
        sb.append(this.route.inetSocketAddress);
        sb.append(" cipherSuite=");
        Handshake handshake2 = this.handshake;
        if (handshake2 != null) {
            obj = handshake2.cipherSuite;
        } else {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.protocol);
        sb.append('}');
        return sb.toString();
    }

    public RealConnection(ConnectionPool connectionPool2, Route route2) {
        this.connectionPool = connectionPool2;
        this.route = route2;
    }

    private void connectTunnel(int i, int i2, int i3) throws IOException {
        Request createTunnelRequest = createTunnelRequest();
        HttpUrl httpUrl = createTunnelRequest.url;
        int i4 = 0;
        while (true) {
            i4++;
            if (i4 <= 21) {
                connectSocket(i, i2);
                createTunnelRequest = createTunnel(i2, i3, createTunnelRequest, httpUrl);
                if (createTunnelRequest != null) {
                    Util.closeQuietly(this.rawSocket);
                    this.rawSocket = null;
                    this.sink = null;
                    this.source = null;
                } else {
                    return;
                }
            } else {
                throw new ProtocolException(AnonymousClass006.A03("Too many tunnel connections attempted: ", 21));
            }
        }
    }

    @Override // okhttp3.Connection
    public Handshake handshake() {
        return this.handshake;
    }

    @Override // okhttp3.Connection
    public Protocol protocol() {
        return this.protocol;
    }

    @Override // okhttp3.Connection
    public Route route() {
        return this.route;
    }

    @Override // okhttp3.Connection
    public Socket socket() {
        return this.socket;
    }
}
