package com.squareup.okhttp.internal.io;

import X.AnonymousClass06;
import X.AnonymousClass8i;
import X.C00148h;
import X.Dp;
import X.Du;
import X.WF;
import X.WL;
import com.facebook.acra.util.HttpRequestMultipart;
import com.squareup.okhttp.Address;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.ConnectionSpec;
import com.squareup.okhttp.Handshake;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Route;
import com.squareup.okhttp.internal.ConnectionSpecSelector;
import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.framed.FramedConnection;
import com.squareup.okhttp.internal.http.Http1xStream;
import com.squareup.okhttp.internal.http.OkHeaders;
import com.squareup.okhttp.internal.http.RouteException;
import com.squareup.okhttp.internal.http.StreamAllocation;
import com.squareup.okhttp.internal.tls.TrustRootIndex;
import java.io.IOException;
import java.lang.ref.Reference;
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
    public static SSLSocketFactory lastSslSocketFactory;
    public static TrustRootIndex lastTrustRootIndex;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    public volatile FramedConnection framedConnection;
    public Handshake handshake;
    public long idleAtNanos = Long.MAX_VALUE;
    public boolean noNewStreams;
    public Protocol protocol;
    public Socket rawSocket;
    public final Route route;
    public Du sink;
    public Socket socket;
    public Dp source;
    public int streamCount;

    private void connectSocket(int i, int i2, int i3, ConnectionSpecSelector connectionSpecSelector) throws IOException {
        this.rawSocket.setSoTimeout(i2);
        try {
            Platform.PLATFORM.connectSocket(this.rawSocket, this.route.inetSocketAddress, i);
            this.source = new C00148h(WL.A01(this.rawSocket));
            this.sink = new AnonymousClass8i(WL.A00(this.rawSocket));
            if (this.route.address.sslSocketFactory != null) {
                connectTls(i2, i3, connectionSpecSelector);
            } else {
                this.protocol = Protocol.HTTP_1_1;
                this.socket = this.rawSocket;
            }
            Protocol protocol2 = this.protocol;
            if (protocol2 == Protocol.SPDY_3 || protocol2 == Protocol.HTTP_2) {
                this.socket.setSoTimeout(0);
                FramedConnection.Builder builder = new FramedConnection.Builder(true);
                builder.socket(this.socket, this.route.address.url.host, this.source, this.sink);
                builder.protocol = this.protocol;
                FramedConnection framedConnection2 = new FramedConnection(builder);
                framedConnection2.sendConnectionPreface();
                this.framedConnection = framedConnection2;
            }
        } catch (ConnectException unused) {
            StringBuilder sb = new StringBuilder("Failed to connect to ");
            sb.append(this.route.inetSocketAddress);
            throw new ConnectException(sb.toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0102 A[Catch:{ all -> 0x0109 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0108 A[Catch:{ all -> 0x0109 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x010f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void connectTls(int r9, int r10, com.squareup.okhttp.internal.ConnectionSpecSelector r11) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 280
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.io.RealConnection.connectTls(int, int, com.squareup.okhttp.internal.ConnectionSpecSelector):void");
    }

    private Request createTunnelRequest() throws IOException {
        Request.Builder builder = new Request.Builder();
        HttpUrl httpUrl = this.route.address.url;
        builder.url(httpUrl);
        builder.headers.set("Host", Util.hostHeader(httpUrl));
        builder.headers.set("Proxy-Connection", "Keep-Alive");
        builder.headers.set(HttpRequestMultipart.USER_AGENT, "okhttp/2.7.5");
        return builder.build();
    }

    public static synchronized TrustRootIndex trustRootIndex(SSLSocketFactory sSLSocketFactory) {
        TrustRootIndex trustRootIndex;
        synchronized (RealConnection.class) {
            if (sSLSocketFactory != lastSslSocketFactory) {
                Platform platform = Platform.PLATFORM;
                lastTrustRootIndex = platform.trustRootIndex(platform.trustManager(sSLSocketFactory));
                lastSslSocketFactory = sSLSocketFactory;
            }
            trustRootIndex = lastTrustRootIndex;
        }
        return trustRootIndex;
    }

    public int allocationLimit() {
        FramedConnection framedConnection2 = this.framedConnection;
        if (framedConnection2 != null) {
            return framedConnection2.maxConcurrentStreams();
        }
        return 1;
    }

    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }

    public void connect(int i, int i2, int i3, List<ConnectionSpec> list, boolean z) throws RouteException {
        Socket createSocket;
        if (this.protocol == null) {
            ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(list);
            Route route2 = this.route;
            Proxy proxy = route2.proxy;
            Address address = route2.address;
            if (address.sslSocketFactory != null || list.contains(ConnectionSpec.CLEARTEXT)) {
                RouteException routeException = null;
                while (this.protocol == null) {
                    try {
                        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) {
                            createSocket = address.socketFactory.createSocket();
                        } else {
                            createSocket = new Socket(proxy);
                        }
                        this.rawSocket = createSocket;
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
            StringBuilder sb = new StringBuilder("CLEARTEXT communication not supported: ");
            sb.append(list);
            throw new RouteException(new UnknownServiceException(sb.toString()));
        }
        throw new IllegalStateException("already connected");
    }

    @Override // com.squareup.okhttp.Connection
    public Protocol getProtocol() {
        Protocol protocol2 = this.protocol;
        if (protocol2 == null) {
            return Protocol.HTTP_1_1;
        }
        return protocol2;
    }

    public boolean isConnected() {
        if (this.protocol != null) {
            return true;
        }
        return false;
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
                    if (this.source.A1V()) {
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
        if (this.framedConnection != null) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str;
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
            str = handshake2.cipherSuite;
        } else {
            str = "none";
        }
        sb.append(str);
        sb.append(" protocol=");
        sb.append(this.protocol);
        sb.append('}');
        return sb.toString();
    }

    public RealConnection(Route route2) {
        this.route = route2;
    }

    private void createTunnel(int i, int i2) throws IOException {
        String str;
        Request createTunnelRequest = createTunnelRequest();
        HttpUrl httpUrl = createTunnelRequest.url;
        StringBuilder sb = new StringBuilder("CONNECT ");
        sb.append(httpUrl.host);
        sb.append(":");
        sb.append(httpUrl.port);
        sb.append(" HTTP/1.1");
        String obj = sb.toString();
        while (true) {
            Dp dp = this.source;
            Http1xStream http1xStream = new Http1xStream(null, dp, this.sink);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            dp.timeout().timeout((long) i, timeUnit);
            this.sink.timeout().timeout((long) i2, timeUnit);
            http1xStream.writeRequest(createTunnelRequest.headers, obj);
            http1xStream.finishRequest();
            Response.Builder readResponse = http1xStream.readResponse();
            readResponse.request = createTunnelRequest;
            Response build = readResponse.build();
            long contentLength = OkHeaders.contentLength(build.headers);
            if (contentLength == -1) {
                contentLength = 0;
            }
            WF newFixedLengthSource = http1xStream.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            int i3 = build.code;
            if (i3 != 200) {
                if (i3 != 407) {
                    str = AnonymousClass06.A01("Unexpected response code for CONNECT: ", i3);
                    break;
                }
                Route route2 = this.route;
                createTunnelRequest = OkHeaders.processAuthHeader(route2.address.authenticator, build, route2.proxy);
                if (createTunnelRequest == null) {
                    str = "Failed to authenticate with proxy";
                    break;
                }
            } else if (!this.source.A16().A1V() || !this.sink.A16().A1V()) {
                str = "TLS tunnel buffered too many bytes!";
            } else {
                return;
            }
        }
        throw new IOException(str);
    }

    @Override // com.squareup.okhttp.Connection
    public Handshake getHandshake() {
        return this.handshake;
    }

    @Override // com.squareup.okhttp.Connection
    public Route getRoute() {
        return this.route;
    }

    @Override // com.squareup.okhttp.Connection
    public Socket getSocket() {
        return this.socket;
    }
}
