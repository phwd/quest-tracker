package okhttp3.internal.ws;

import X.AnonymousClass006;
import com.facebook.tigon.iface.TigonRequest;
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
import okhttp3.Call;
import okhttp3.Callback;
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
import org.apache.commons.cli.HelpFormatter;

public final class RealWebSocket implements WebSocketReader.FrameCallback, WebSocket {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    public static final long MAX_QUEUE_SIZE = 16777216;
    public static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    public Call call;
    public ScheduledFuture<?> cancelFuture;
    public boolean enqueuedClose;
    public ScheduledExecutorService executor;
    public boolean failed;
    public final String key;
    public final WebSocketListener listener;
    public final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    public final Request originalRequest;
    public int pingCount;
    public int pongCount;
    public final ArrayDeque<ByteString> pongQueue = new ArrayDeque<>();
    public long queueSize;
    public final Random random;
    public WebSocketReader reader;
    public int receivedCloseCode = -1;
    public String receivedCloseReason;
    public Streams streams;
    public WebSocketWriter writer;
    public final Runnable writerRunnable;

    public final class CancelRunnable implements Runnable {
        public CancelRunnable() {
        }

        public void run() {
            RealWebSocket.this.cancel();
        }
    }

    public final class PingRunnable implements Runnable {
        public PingRunnable() {
        }

        public void run() {
            RealWebSocket.this.writePingFrame();
        }
    }

    public void failWebSocket(Exception exc, Response response) {
        synchronized (this) {
            if (!this.failed) {
                this.failed = true;
                Streams streams2 = this.streams;
                this.streams = null;
                ScheduledFuture<?> scheduledFuture = this.cancelFuture;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
                ScheduledExecutorService scheduledExecutorService = this.executor;
                if (scheduledExecutorService != null) {
                    scheduledExecutorService.shutdown();
                }
                Util.closeQuietly(streams2);
            }
        }
    }

    public void initReaderAndWriter(String str, long j, Streams streams2) throws IOException {
        synchronized (this) {
            this.streams = streams2;
            this.writer = new WebSocketWriter(streams2.client, streams2.sink, this.random);
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, 
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: CONSTRUCTOR  (r3v1 'scheduledThreadPoolExecutor' java.util.concurrent.ScheduledThreadPoolExecutor) = 
                  (1 int)
                  (wrap: okhttp3.internal.Util$1 : 0x0014: CONSTRUCTOR  (r0v2 okhttp3.internal.Util$1) = (r11v0 'str' java.lang.String), false call: okhttp3.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR)
                 call: java.util.concurrent.ScheduledThreadPoolExecutor.<init>(int, java.util.concurrent.ThreadFactory):void type: CONSTRUCTOR in method: okhttp3.internal.ws.RealWebSocket.initReaderAndWriter(java.lang.String, long, okhttp3.internal.ws.RealWebSocket$Streams):void, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:249)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:71)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0014: CONSTRUCTOR  (r0v2 okhttp3.internal.Util$1) = (r11v0 'str' java.lang.String), false call: okhttp3.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR in method: okhttp3.internal.ws.RealWebSocket.initReaderAndWriter(java.lang.String, long, okhttp3.internal.ws.RealWebSocket$Streams):void, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:663)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                	... 21 more
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: okhttp3.internal.Util, state: GENERATED_AND_UNLOADED
                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                	... 27 more
                */
            /*
                this = this;
                monitor-enter(r10)
                r10.streams = r14     // Catch:{ all -> 0x0048 }
                boolean r3 = r14.client     // Catch:{ all -> 0x0048 }
                okio.BufferedSink r2 = r14.sink     // Catch:{ all -> 0x0048 }
                java.util.Random r1 = r10.random     // Catch:{ all -> 0x0048 }
                okhttp3.internal.ws.WebSocketWriter r0 = new okhttp3.internal.ws.WebSocketWriter     // Catch:{ all -> 0x0048 }
                r0.<init>(r3, r2, r1)     // Catch:{ all -> 0x0048 }
                r10.writer = r0     // Catch:{ all -> 0x0048 }
                r2 = 1
                r1 = 0
                okhttp3.internal.Util$1 r0 = new okhttp3.internal.Util$1     // Catch:{ all -> 0x0048 }
                r0.<init>(r11, r1)     // Catch:{ all -> 0x0048 }
                java.util.concurrent.ScheduledThreadPoolExecutor r3 = new java.util.concurrent.ScheduledThreadPoolExecutor     // Catch:{ all -> 0x0048 }
                r3.<init>(r2, r0)     // Catch:{ all -> 0x0048 }
                r10.executor = r3     // Catch:{ all -> 0x0048 }
                r1 = 0
                r5 = r12
                int r0 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
                if (r0 == 0) goto L_0x0030
                okhttp3.internal.ws.RealWebSocket$PingRunnable r4 = new okhttp3.internal.ws.RealWebSocket$PingRunnable     // Catch:{ all -> 0x0048 }
                r4.<init>()     // Catch:{ all -> 0x0048 }
                java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0048 }
                r7 = r12
                r3.scheduleAtFixedRate(r4, r5, r7, r9)     // Catch:{ all -> 0x0048 }
            L_0x0030:
                java.util.ArrayDeque<java.lang.Object> r0 = r10.messageAndCloseQueue     // Catch:{ all -> 0x0048 }
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0048 }
                if (r0 != 0) goto L_0x003b
                r10.runWriter()     // Catch:{ all -> 0x0048 }
            L_0x003b:
                monitor-exit(r10)     // Catch:{ all -> 0x0048 }
                boolean r2 = r14.client
                okio.BufferedSource r1 = r14.source
                okhttp3.internal.ws.WebSocketReader r0 = new okhttp3.internal.ws.WebSocketReader
                r0.<init>(r2, r1, r10)
                r10.reader = r0
                return
            L_0x0048:
                r0 = move-exception
                monitor-exit(r10)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.initReaderAndWriter(java.lang.String, long, okhttp3.internal.ws.RealWebSocket$Streams):void");
        }

        @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
        public void onReadClose(int i, String str) {
            Streams streams2;
            if (i != -1) {
                synchronized (this) {
                    if (this.receivedCloseCode == -1) {
                        this.receivedCloseCode = i;
                        this.receivedCloseReason = str;
                        if (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty()) {
                            streams2 = null;
                        } else {
                            streams2 = this.streams;
                            this.streams = null;
                            ScheduledFuture<?> scheduledFuture = this.cancelFuture;
                            if (scheduledFuture != null) {
                                scheduledFuture.cancel(false);
                            }
                            this.executor.shutdown();
                        }
                    } else {
                        throw new IllegalStateException("already closed");
                    }
                }
                Util.closeQuietly(streams2);
                return;
            }
            throw new IllegalArgumentException();
        }

        @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
        public synchronized void onReadPing(ByteString byteString) {
            if (!this.failed) {
                if (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty()) {
                    this.pongQueue.add(byteString);
                    runWriter();
                    this.pingCount++;
                }
            }
        }

        @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
        public synchronized void onReadPong(ByteString byteString) {
            this.pongCount++;
        }

        public synchronized int pingCount() {
            return this.pingCount;
        }

        public synchronized boolean pong(ByteString byteString) {
            boolean z;
            if (!this.failed) {
                if (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty()) {
                    this.pongQueue.add(byteString);
                    runWriter();
                    z = true;
                }
            }
            z = false;
            return z;
        }

        public synchronized int pongCount() {
            return this.pongCount;
        }

        public boolean processNextFrame() throws IOException {
            try {
                this.reader.processNextFrame();
                return this.receivedCloseCode == -1;
            } catch (Exception e) {
                failWebSocket(e, null);
                return false;
            }
        }

        @Override // okhttp3.WebSocket
        public synchronized long queueSize() {
            return this.queueSize;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
            if (r8 == null) goto L_0x004d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            r3.writePong(r8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x004f, code lost:
            if ((r7 instanceof okhttp3.internal.ws.RealWebSocket.Message) == false) goto L_0x007b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0051, code lost:
            r7 = (okhttp3.internal.ws.RealWebSocket.Message) r7;
            r5 = r7.data;
            r0 = new okio.RealBufferedSink(r3.newMessageSink(r7.formatOpcode, (long) r5.size()));
            r0.write(r5);
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x006b, code lost:
            monitor-enter(r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            r9.queueSize -= (long) r5.size();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
            monitor-exit(r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x007d, code lost:
            if ((r7 instanceof okhttp3.internal.ws.RealWebSocket.Close) == false) goto L_0x008d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x007f, code lost:
            r7 = (okhttp3.internal.ws.RealWebSocket.Close) r7;
            r3.writeClose(r7.code, r7.reason);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0088, code lost:
            okhttp3.internal.Util.closeQuietly(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x008c, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0092, code lost:
            throw new java.lang.AssertionError();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0093, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0094, code lost:
            okhttp3.internal.Util.closeQuietly(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0097, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean writeOneFrame() throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 157
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.writeOneFrame():boolean");
        }

        public void writePingFrame() {
            synchronized (this) {
                if (!this.failed) {
                    WebSocketWriter webSocketWriter = this.writer;
                    try {
                        webSocketWriter.writePing(ByteString.EMPTY);
                    } catch (IOException e) {
                        failWebSocket(e, null);
                    }
                }
            }
        }

        public static final class Close {
            public final long cancelAfterCloseMillis;
            public final int code;
            public final ByteString reason;

            public Close(int i, ByteString byteString, long j) {
                this.code = i;
                this.reason = byteString;
                this.cancelAfterCloseMillis = j;
            }
        }

        public static final class Message {
            public final ByteString data;
            public final int formatOpcode;

            public Message(int i, ByteString byteString) {
                this.formatOpcode = i;
                this.data = byteString;
            }
        }

        public static abstract class Streams implements Closeable {
            public final boolean client;
            public final BufferedSink sink;
            public final BufferedSource source;

            public Streams(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink) {
                this.client = z;
                this.source = bufferedSource;
                this.sink = bufferedSink;
            }
        }

        private void runWriter() {
            ScheduledExecutorService scheduledExecutorService = this.executor;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.execute(this.writerRunnable);
            }
        }

        public void awaitTermination(int i, TimeUnit timeUnit) throws InterruptedException {
            this.executor.awaitTermination((long) i, timeUnit);
        }

        @Override // okhttp3.WebSocket
        public void cancel() {
            this.call.cancel();
        }

        public void checkResponse(Response response) throws ProtocolException {
            int i = response.code;
            if (i == 101) {
                String header = response.header("Connection");
                if ("Upgrade".equalsIgnoreCase(header)) {
                    String header2 = response.header("Upgrade");
                    if ("websocket".equalsIgnoreCase(header2)) {
                        String header3 = response.header("Sec-WebSocket-Accept");
                        String base64 = ByteString.encodeUtf8(AnonymousClass006.A07(this.key, WebSocketProtocol.ACCEPT_MAGIC)).sha1().base64();
                        if (!base64.equals(header3)) {
                            throw new ProtocolException(AnonymousClass006.A0C("Expected 'Sec-WebSocket-Accept' header value '", base64, "' but was '", header3, "'"));
                        }
                        return;
                    }
                    throw new ProtocolException(AnonymousClass006.A09("Expected 'Upgrade' header value 'websocket' but was '", header2, "'"));
                }
                throw new ProtocolException(AnonymousClass006.A09("Expected 'Connection' header value 'Upgrade' but was '", header, "'"));
            }
            StringBuilder sb = new StringBuilder("Expected HTTP 101 response but was '");
            sb.append(i);
            sb.append(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
            sb.append(response.message);
            sb.append("'");
            throw new ProtocolException(sb.toString());
        }

        public void loopReader() throws IOException {
            while (this.receivedCloseCode == -1) {
                this.reader.processNextFrame();
            }
        }

        public void tearDown() throws InterruptedException {
            ScheduledFuture<?> scheduledFuture = this.cancelFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.executor.shutdown();
            this.executor.awaitTermination(10, TimeUnit.SECONDS);
        }

        public RealWebSocket(Request request, WebSocketListener webSocketListener, Random random2) {
            String str = request.method;
            if (TigonRequest.GET.equals(str)) {
                this.originalRequest = request;
                this.listener = webSocketListener;
                this.random = random2;
                byte[] bArr = new byte[16];
                random2.nextBytes(bArr);
                this.key = ByteString.of(bArr).base64();
                this.writerRunnable = new Runnable() {
                    /* class okhttp3.internal.ws.RealWebSocket.AnonymousClass1 */

                    public void run() {
                        while (RealWebSocket.this.writeOneFrame()) {
                            try {
                            } catch (IOException e) {
                                RealWebSocket.this.failWebSocket(e, null);
                                return;
                            }
                        }
                    }
                };
                return;
            }
            throw new IllegalArgumentException(AnonymousClass006.A07("Request must be GET: ", str));
        }

        public void connect(OkHttpClient okHttpClient) {
            OkHttpClient.Builder newBuilder = okHttpClient.newBuilder();
            newBuilder.protocols(ONLY_HTTP1);
            OkHttpClient build = newBuilder.build();
            final int i = build.pingInterval;
            Request.Builder newBuilder2 = this.originalRequest.newBuilder();
            newBuilder2.header("Upgrade", "websocket");
            newBuilder2.header("Connection", "Upgrade");
            newBuilder2.header("Sec-WebSocket-Key", this.key);
            newBuilder2.header("Sec-WebSocket-Version", "13");
            final Request build2 = newBuilder2.build();
            Call newWebSocketCall = Internal.instance.newWebSocketCall(build, build2);
            this.call = newWebSocketCall;
            newWebSocketCall.enqueue(new Callback() {
                /* class okhttp3.internal.ws.RealWebSocket.AnonymousClass2 */

                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    RealWebSocket.this.failWebSocket(iOException, null);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) {
                    try {
                        RealWebSocket.this.checkResponse(response);
                        StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                        streamAllocation.noNewStreams();
                        Streams newWebSocketStreams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                        try {
                            RealWebSocket.this.initReaderAndWriter(AnonymousClass006.A07("OkHttp WebSocket ", build2.url.redact()), (long) i, newWebSocketStreams);
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
            });
        }

        @Override // okhttp3.WebSocket
        public Request request() {
            return this.originalRequest;
        }

        private synchronized boolean send(ByteString byteString, int i) {
            if (!this.failed && !this.enqueuedClose) {
                long size = this.queueSize + ((long) byteString.size());
                if (size > MAX_QUEUE_SIZE) {
                    close(WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY, null);
                } else {
                    this.queueSize = size;
                    this.messageAndCloseQueue.add(new Message(i, byteString));
                    runWriter();
                    return true;
                }
            }
            return false;
        }

        @Override // okhttp3.WebSocket
        public boolean close(int i, String str) {
            return close(i, str, 60000);
        }

        public synchronized boolean close(int i, String str, long j) {
            WebSocketProtocol.validateCloseCode(i);
            ByteString byteString = null;
            if (str != null) {
                byteString = ByteString.encodeUtf8(str);
                if (((long) byteString.size()) > 123) {
                    throw new IllegalArgumentException(AnonymousClass006.A07("reason.size() > 123: ", str));
                }
            }
            if (this.failed || this.enqueuedClose) {
                return false;
            }
            this.enqueuedClose = true;
            this.messageAndCloseQueue.add(new Close(i, byteString, j));
            runWriter();
            return true;
        }

        @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
        public void onReadMessage(String str) throws IOException {
        }

        @Override // okhttp3.internal.ws.WebSocketReader.FrameCallback
        public void onReadMessage(ByteString byteString) throws IOException {
        }

        @Override // okhttp3.WebSocket
        public boolean send(String str) {
            if (str != null) {
                return send(ByteString.encodeUtf8(str), 1);
            }
            throw new NullPointerException("text == null");
        }

        @Override // okhttp3.WebSocket
        public boolean send(ByteString byteString) {
            if (byteString != null) {
                return send(byteString, 2);
            }
            throw new NullPointerException("bytes == null");
        }
    }
