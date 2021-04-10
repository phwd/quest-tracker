package okhttp3.internal.ws;

import com.google.common.net.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.ws.WebSocketReader;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    static final /* synthetic */ boolean $assertionsDisabled = (!RealWebSocket.class.desiredAssertionStatus());
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    private boolean awaitingPong;
    private Call call;
    private ScheduledFuture<?> cancelFuture;
    private boolean enqueuedClose;
    private ScheduledExecutorService executor;
    private boolean failed;
    private final String key;
    final WebSocketListener listener;
    private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque<ByteString> pongQueue = new ArrayDeque<>();
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode = -1;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private WebSocketWriter writer;
    private final Runnable writerRunnable;

    public RealWebSocket(Request request, WebSocketListener listener2, Random random2, long pingIntervalMillis2) {
        if (!"GET".equals(request.method())) {
            throw new IllegalArgumentException("Request must be GET: " + request.method());
        }
        this.originalRequest = request;
        this.listener = listener2;
        this.random = random2;
        this.pingIntervalMillis = pingIntervalMillis2;
        byte[] nonce = new byte[16];
        random2.nextBytes(nonce);
        this.key = ByteString.of(nonce).base64();
        this.writerRunnable = new Runnable() {
            /* class okhttp3.internal.ws.RealWebSocket.AnonymousClass1 */

            public void run() {
                do {
                    try {
                    } catch (IOException e) {
                        RealWebSocket.this.failWebSocket(e, null);
                        return;
                    }
                } while (RealWebSocket.this.writeOneFrame());
            }
        };
    }

    @Override // okhttp3.WebSocket
    public Request request() {
        return this.originalRequest;
    }

    @Override // okhttp3.WebSocket
    public synchronized long queueSize() {
        return this.queueSize;
    }

    @Override // okhttp3.WebSocket
    public void cancel() {
        this.call.cancel();
    }

    public void connect(OkHttpClient client) {
        OkHttpClient client2 = client.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        final Request request = this.originalRequest.newBuilder().header(HttpHeaders.UPGRADE, "websocket").header(HttpHeaders.CONNECTION, HttpHeaders.UPGRADE).header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").build();
        this.call = Internal.instance.newWebSocketCall(client2, request);
        this.call.timeout().clearTimeout();
        this.call.enqueue(new Callback() {
            /* class okhttp3.internal.ws.RealWebSocket.AnonymousClass2 */

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                try {
                    RealWebSocket.this.checkResponse(response);
                    StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                    streamAllocation.noNewStreams();
                    Streams streams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                    try {
                        RealWebSocket.this.listener.onOpen(RealWebSocket.this, response);
                        RealWebSocket.this.initReaderAndWriter("OkHttp WebSocket " + request.url().redact(), streams);
                        streamAllocation.connection().socket().setSoTimeout(0);
                        RealWebSocket.this.loopReader();
                    } catch (Exception e) {
                        RealWebSocket.this.failWebSocket(e, null);
                    }
                } catch (ProtocolException e2) {
                    RealWebSocket.this.failWebSocket(e2, response);
                    Util.closeQuietly(response);
                }
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                RealWebSocket.this.failWebSocket(e, null);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void checkResponse(Response response) throws ProtocolException {
        if (response.code() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + " " + response.message() + "'");
        }
        String headerConnection = response.header(HttpHeaders.CONNECTION);
        if (!HttpHeaders.UPGRADE.equalsIgnoreCase(headerConnection)) {
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + headerConnection + "'");
        }
        String headerUpgrade = response.header(HttpHeaders.UPGRADE);
        if (!"websocket".equalsIgnoreCase(headerUpgrade)) {
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + headerUpgrade + "'");
        }
        String headerAccept = response.header("Sec-WebSocket-Accept");
        String acceptExpected = ByteString.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
        if (!acceptExpected.equals(headerAccept)) {
            throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + acceptExpected + "' but was '" + headerAccept + "'");
        }
    }

    public void initReaderAndWriter(String name, Streams streams2) throws IOException {
        synchronized (this) {
            this.streams = streams2;
            this.writer = new WebSocketWriter(streams2.client, streams2.sink, this.random);
            this.executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(name, false));
            if (this.pingIntervalMillis != 0) {
                this.executor.scheduleAtFixedRate(new PingRunnable(), this.pingIntervalMillis, this.pingIntervalMillis, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
        }
        this.reader = new WebSocketReader(streams2.client, streams2.source, this);
    }

    public void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            this.reader.processNextFrame();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean processNextFrame() throws IOException {
        try {
            this.reader.processNextFrame();
            if (this.receivedCloseCode == -1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            failWebSocket(e, null);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void awaitTermination(int timeout, TimeUnit timeUnit) throws InterruptedException {
        this.executor.awaitTermination((long) timeout, timeUnit);
    }

    /* access modifiers changed from: package-private */
    public void tearDown() throws InterruptedException {
        if (this.cancelFuture != null) {
            this.cancelFuture.cancel(false);
        }
        this.executor.shutdown();
        this.executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    /* access modifiers changed from: package-private */
    public synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    /* access modifiers changed from: package-private */
    public synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    /* access modifiers changed from: package-private */
    public synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(String text) throws IOException {
        this.listener.onMessage(this, text);
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(ByteString bytes) throws IOException {
        this.listener.onMessage(this, bytes);
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPing(ByteString payload) {
        if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
            this.pongQueue.add(payload);
            runWriter();
            this.receivedPingCount++;
        }
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPong(ByteString buffer) {
        this.receivedPongCount++;
        this.awaitingPong = false;
    }

    @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadClose(int code, String reason) {
        if (code == -1) {
            throw new IllegalArgumentException();
        }
        Streams toClose = null;
        synchronized (this) {
            if (this.receivedCloseCode != -1) {
                throw new IllegalStateException("already closed");
            }
            this.receivedCloseCode = code;
            this.receivedCloseReason = reason;
            if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                toClose = this.streams;
                this.streams = null;
                if (this.cancelFuture != null) {
                    this.cancelFuture.cancel(false);
                }
                this.executor.shutdown();
            }
        }
        try {
            this.listener.onClosing(this, code, reason);
            if (toClose != null) {
                this.listener.onClosed(this, code, reason);
            }
        } finally {
            Util.closeQuietly(toClose);
        }
    }

    @Override // okhttp3.WebSocket
    public boolean send(String text) {
        if (text != null) {
            return send(ByteString.encodeUtf8(text), 1);
        }
        throw new NullPointerException("text == null");
    }

    @Override // okhttp3.WebSocket
    public boolean send(ByteString bytes) {
        if (bytes != null) {
            return send(bytes, 2);
        }
        throw new NullPointerException("bytes == null");
    }

    private synchronized boolean send(ByteString data, int formatOpcode) {
        boolean z = false;
        synchronized (this) {
            if (!this.failed && !this.enqueuedClose) {
                if (this.queueSize + ((long) data.size()) > 16777216) {
                    close(1001, null);
                } else {
                    this.queueSize += (long) data.size();
                    this.messageAndCloseQueue.add(new Message(formatOpcode, data));
                    runWriter();
                    z = true;
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean pong(ByteString payload) {
        boolean z;
        if (this.failed || (this.enqueuedClose && this.messageAndCloseQueue.isEmpty())) {
            z = false;
        } else {
            this.pongQueue.add(payload);
            runWriter();
            z = true;
        }
        return z;
    }

    @Override // okhttp3.WebSocket
    public boolean close(int code, String reason) {
        return close(code, reason, 60000);
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean close(int code, String reason, long cancelAfterCloseMillis) {
        boolean z = true;
        synchronized (this) {
            WebSocketProtocol.validateCloseCode(code);
            ByteString reasonBytes = null;
            if (reason != null) {
                reasonBytes = ByteString.encodeUtf8(reason);
                if (((long) reasonBytes.size()) > 123) {
                    throw new IllegalArgumentException("reason.size() > 123: " + reason);
                }
            }
            if (this.failed || this.enqueuedClose) {
                z = false;
            } else {
                this.enqueuedClose = true;
                this.messageAndCloseQueue.add(new Close(code, reasonBytes, cancelAfterCloseMillis));
                runWriter();
            }
        }
        return z;
    }

    private void runWriter() {
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (this.executor != null) {
            this.executor.execute(this.writerRunnable);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        if (r5 == null) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r10.writePong(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004b, code lost:
        okhttp3.internal.Util.closeQuietly(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0075, code lost:
        if ((r4 instanceof okhttp3.internal.ws.RealWebSocket.Message) == false) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0077, code lost:
        r3 = ((okhttp3.internal.ws.RealWebSocket.Message) r4).data;
        r8 = okio.Okio.buffer(r10.newMessageSink(((okhttp3.internal.ws.RealWebSocket.Message) r4).formatOpcode, (long) r3.size()));
        r8.write(r3);
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0094, code lost:
        monitor-enter(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r16.queueSize -= (long) r3.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a3, code lost:
        monitor-exit(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a8, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a9, code lost:
        okhttp3.internal.Util.closeQuietly(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ac, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00af, code lost:
        if ((r4 instanceof okhttp3.internal.ws.RealWebSocket.Close) == false) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b1, code lost:
        r0 = (okhttp3.internal.ws.RealWebSocket.Close) r4;
        r10.writeClose(r0.code, r0.reason);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bc, code lost:
        if (r9 == null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00be, code lost:
        r16.listener.onClosed(r16, r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00cd, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean writeOneFrame() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 206
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.writeOneFrame():boolean");
    }

    /* access modifiers changed from: private */
    public final class PingRunnable implements Runnable {
        PingRunnable() {
        }

        public void run() {
            RealWebSocket.this.writePingFrame();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        if (r1 == -1) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        failWebSocket(new java.net.SocketTimeoutException("sent ping but didn't receive pong within " + r9.pingIntervalMillis + "ms (after " + (r1 - 1) + " successful ping/pongs)"), null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r2.writePing(okio.ByteString.EMPTY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0058, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0059, code lost:
        failWebSocket(r0, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writePingFrame() {
        /*
            r9 = this;
            r8 = 0
            r3 = -1
            monitor-enter(r9)
            boolean r4 = r9.failed     // Catch:{ all -> 0x004f }
            if (r4 == 0) goto L_0x0009
            monitor-exit(r9)     // Catch:{ all -> 0x004f }
        L_0x0008:
            return
        L_0x0009:
            okhttp3.internal.ws.WebSocketWriter r2 = r9.writer     // Catch:{ all -> 0x004f }
            boolean r4 = r9.awaitingPong     // Catch:{ all -> 0x004f }
            if (r4 == 0) goto L_0x004d
            int r1 = r9.sentPingCount     // Catch:{ all -> 0x004f }
        L_0x0011:
            int r4 = r9.sentPingCount     // Catch:{ all -> 0x004f }
            int r4 = r4 + 1
            r9.sentPingCount = r4     // Catch:{ all -> 0x004f }
            r4 = 1
            r9.awaitingPong = r4     // Catch:{ all -> 0x004f }
            monitor-exit(r9)     // Catch:{ all -> 0x004f }
            if (r1 == r3) goto L_0x0052
            java.net.SocketTimeoutException r3 = new java.net.SocketTimeoutException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "sent ping but didn't receive pong within "
            java.lang.StringBuilder r4 = r4.append(r5)
            long r6 = r9.pingIntervalMillis
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r5 = "ms (after "
            java.lang.StringBuilder r4 = r4.append(r5)
            int r5 = r1 + -1
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " successful ping/pongs)"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            r9.failWebSocket(r3, r8)
            goto L_0x0008
        L_0x004d:
            r1 = r3
            goto L_0x0011
        L_0x004f:
            r3 = move-exception
            monitor-exit(r9)
            throw r3
        L_0x0052:
            okio.ByteString r3 = okio.ByteString.EMPTY     // Catch:{ IOException -> 0x0058 }
            r2.writePing(r3)     // Catch:{ IOException -> 0x0058 }
            goto L_0x0008
        L_0x0058:
            r0 = move-exception
            r9.failWebSocket(r0, r8)
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.writePingFrame():void");
    }

    public void failWebSocket(Exception e, @Nullable Response response) {
        synchronized (this) {
            if (!this.failed) {
                this.failed = true;
                Streams streamsToClose = this.streams;
                this.streams = null;
                if (this.cancelFuture != null) {
                    this.cancelFuture.cancel(false);
                }
                if (this.executor != null) {
                    this.executor.shutdown();
                }
                try {
                    this.listener.onFailure(this, e, response);
                } finally {
                    Util.closeQuietly(streamsToClose);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Message {
        final ByteString data;
        final int formatOpcode;

        Message(int formatOpcode2, ByteString data2) {
            this.formatOpcode = formatOpcode2;
            this.data = data2;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Close {
        final long cancelAfterCloseMillis;
        final int code;
        final ByteString reason;

        Close(int code2, ByteString reason2, long cancelAfterCloseMillis2) {
            this.code = code2;
            this.reason = reason2;
            this.cancelAfterCloseMillis = cancelAfterCloseMillis2;
        }
    }

    public static abstract class Streams implements Closeable {
        public final boolean client;
        public final BufferedSink sink;
        public final BufferedSource source;

        public Streams(boolean client2, BufferedSource source2, BufferedSink sink2) {
            this.client = client2;
            this.source = source2;
            this.sink = sink2;
        }
    }

    /* access modifiers changed from: package-private */
    public final class CancelRunnable implements Runnable {
        CancelRunnable() {
        }

        public void run() {
            RealWebSocket.this.cancel();
        }
    }
}
