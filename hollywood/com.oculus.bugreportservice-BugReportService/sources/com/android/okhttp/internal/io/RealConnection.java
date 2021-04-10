package com.android.okhttp.internal.io;

import com.android.okhttp.Address;
import com.android.okhttp.Connection;
import com.android.okhttp.ConnectionSpec;
import com.android.okhttp.Handshake;
import com.android.okhttp.HttpUrl;
import com.android.okhttp.Protocol;
import com.android.okhttp.Request;
import com.android.okhttp.Response;
import com.android.okhttp.Route;
import com.android.okhttp.internal.ConnectionSpecSelector;
import com.android.okhttp.internal.Platform;
import com.android.okhttp.internal.Util;
import com.android.okhttp.internal.Version;
import com.android.okhttp.internal.framed.FramedConnection;
import com.android.okhttp.internal.http.Http1xStream;
import com.android.okhttp.internal.http.OkHeaders;
import com.android.okhttp.internal.http.RouteException;
import com.android.okhttp.internal.tls.TrustRootIndex;
import com.android.okhttp.okio.BufferedSink;
import com.android.okhttp.okio.BufferedSource;
import com.android.okhttp.okio.Okio;
import com.android.okhttp.okio.Source;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSocketFactory;

public final class RealConnection implements Connection {
    private static SSLSocketFactory lastSslSocketFactory;
    private static TrustRootIndex lastTrustRootIndex;
    public final List allocations = new ArrayList();
    public volatile FramedConnection framedConnection;
    private Handshake handshake;
    public long idleAtNanos = Long.MAX_VALUE;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    public BufferedSink sink;
    public Socket socket;
    public BufferedSource source;
    public int streamCount;

    public RealConnection(Route route2) {
        this.route = route2;
    }

    public void connect(int i, int i2, int i3, List list, boolean z) {
        Socket socket2;
        if (this.protocol == null) {
            ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(list);
            Proxy proxy = this.route.getProxy();
            Address address = this.route.getAddress();
            if (this.route.getAddress().getSslSocketFactory() != null || list.contains(ConnectionSpec.CLEARTEXT)) {
                RouteException routeException = null;
                while (this.protocol == null) {
                    try {
                        if (proxy.type() != Proxy.Type.DIRECT) {
                            if (proxy.type() != Proxy.Type.HTTP) {
                                socket2 = new Socket(proxy);
                                this.rawSocket = socket2;
                                connectSocket(i, i2, i3, connectionSpecSelector);
                            }
                        }
                        socket2 = address.getSocketFactory().createSocket();
                        this.rawSocket = socket2;
                        connectSocket(i, i2, i3, connectionSpecSelector);
                    } catch (IOException e) {
                        Util.closeQuietly(this.socket);
                        Util.closeQuietly(this.rawSocket);
                        this.socket = null;
                        this.rawSocket = null;
                        this.source = null;
                        this.sink = null;
                        this.handshake = null;
                        this.protocol = null;
                        if (routeException == null) {
                            routeException = new RouteException(e);
                        } else {
                            routeException.addConnectException(e);
                        }
                        if (!z || !connectionSpecSelector.connectionFailed(e)) {
                            throw routeException;
                        }
                    }
                }
                return;
            }
            throw new RouteException(new UnknownServiceException("CLEARTEXT communication not supported: " + list));
        }
        throw new IllegalStateException("already connected");
    }

    private void connectSocket(int i, int i2, int i3, ConnectionSpecSelector connectionSpecSelector) {
        this.rawSocket.setSoTimeout(i2);
        try {
            Platform.get().connectSocket(this.rawSocket, this.route.getSocketAddress(), i);
            this.source = Okio.buffer(Okio.source(this.rawSocket));
            this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            if (this.route.getAddress().getSslSocketFactory() != null) {
                connectTls(i2, i3, connectionSpecSelector);
            } else {
                this.protocol = Protocol.HTTP_1_1;
                this.socket = this.rawSocket;
            }
            Protocol protocol2 = this.protocol;
            if (protocol2 == Protocol.SPDY_3 || protocol2 == Protocol.HTTP_2) {
                this.socket.setSoTimeout(0);
                FramedConnection.Builder builder = new FramedConnection.Builder(true);
                builder.socket(this.socket, this.route.getAddress().url().host(), this.source, this.sink);
                builder.protocol(this.protocol);
                FramedConnection build = builder.build();
                build.sendConnectionPreface();
                this.framedConnection = build;
            }
        } catch (ConnectException unused) {
            throw new ConnectException("Failed to connect to " + this.route.getSocketAddress());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0121 A[Catch:{ all -> 0x0117 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0127 A[Catch:{ all -> 0x0117 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x012a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void connectTls(int r6, int r7, com.android.okhttp.internal.ConnectionSpecSelector r8) {
        /*
        // Method dump skipped, instructions count: 309
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.okhttp.internal.io.RealConnection.connectTls(int, int, com.android.okhttp.internal.ConnectionSpecSelector):void");
    }

    private static synchronized TrustRootIndex trustRootIndex(SSLSocketFactory sSLSocketFactory) {
        TrustRootIndex trustRootIndex;
        synchronized (RealConnection.class) {
            if (sSLSocketFactory != lastSslSocketFactory) {
                lastTrustRootIndex = Platform.get().trustRootIndex(Platform.get().trustManager(sSLSocketFactory));
                lastSslSocketFactory = sSLSocketFactory;
            }
            trustRootIndex = lastTrustRootIndex;
        }
        return trustRootIndex;
    }

    private void createTunnel(int i, int i2) {
        Request createTunnelRequest = createTunnelRequest();
        HttpUrl httpUrl = createTunnelRequest.httpUrl();
        String str = "CONNECT " + Util.hostHeader(httpUrl, true) + " HTTP/1.1";
        do {
            Http1xStream http1xStream = new Http1xStream(null, this.source, this.sink);
            this.source.timeout().timeout((long) i, TimeUnit.MILLISECONDS);
            this.sink.timeout().timeout((long) i2, TimeUnit.MILLISECONDS);
            http1xStream.writeRequest(createTunnelRequest.headers(), str);
            http1xStream.finishRequest();
            Response.Builder readResponse = http1xStream.readResponse();
            readResponse.request(createTunnelRequest);
            Response build = readResponse.build();
            long contentLength = OkHeaders.contentLength(build);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = http1xStream.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            int code = build.code();
            if (code != 200) {
                if (code == 407) {
                    createTunnelRequest = OkHeaders.processAuthHeader(this.route.getAddress().getAuthenticator(), build, this.route.getProxy());
                } else {
                    throw new IOException("Unexpected response code for CONNECT: " + build.code());
                }
            } else if (!this.source.buffer().exhausted() || !this.sink.buffer().exhausted()) {
                throw new IOException("TLS tunnel buffered too many bytes!");
            } else {
                return;
            }
        } while (createTunnelRequest != null);
        throw new IOException("Failed to authenticate with proxy");
    }

    private Request createTunnelRequest() {
        Request.Builder builder = new Request.Builder();
        builder.url(this.route.getAddress().url());
        builder.header("Host", Util.hostHeader(this.route.getAddress().url(), true));
        builder.header("Proxy-Connection", "Keep-Alive");
        builder.header("User-Agent", Version.userAgent());
        return builder.build();
    }

    @Override // com.android.okhttp.Connection
    public Route getRoute() {
        return this.route;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public int allocationLimit() {
        FramedConnection framedConnection2 = this.framedConnection;
        if (framedConnection2 != null) {
            return framedConnection2.maxConcurrentStreams();
        }
        return 1;
    }

    /* JADX INFO: finally extract failed */
    public boolean isHealthy(boolean z) {
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            return false;
        }
        if (this.framedConnection == null && z) {
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

    @Override // com.android.okhttp.Connection
    public Handshake getHandshake() {
        return this.handshake;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.route.getAddress().url().host());
        sb.append(":");
        sb.append(this.route.getAddress().url().port());
        sb.append(", proxy=");
        sb.append(this.route.getProxy());
        sb.append(" hostAddress=");
        sb.append(this.route.getSocketAddress());
        sb.append(" cipherSuite=");
        Handshake handshake2 = this.handshake;
        sb.append(handshake2 != null ? handshake2.cipherSuite() : "none");
        sb.append(" protocol=");
        sb.append(this.protocol);
        sb.append('}');
        return sb.toString();
    }
}
