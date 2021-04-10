package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Http2Reader;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.RealBufferedSink;

public final class Http2Connection implements Closeable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    public static final ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), ;
    public long bytesLeftInWriteWindow;
    public final boolean client;
    public final Set<Integer> currentPushRequests = new LinkedHashSet();
    public final String hostname;
    public int lastGoodStreamId;
    public final Listener listener;
    public int nextPingId;
    public int nextStreamId;
    public Settings okHttpSettings = new Settings();
    public final Settings peerSettings = new Settings();
    public Map<Integer, Ping> pings;
    public final ExecutorService pushExecutor;
    public final PushObserver pushObserver;
    public final ReaderRunnable readerRunnable;
    public boolean receivedInitialPeerSettings = false;
    public boolean shutdown;
    public final Socket socket;
    public final Map<Integer, Http2Stream> streams = new LinkedHashMap();
    public long unacknowledgedBytesRead = 0;
    public final Http2Writer writer;

    public static class Builder {
        public boolean client;
        public String hostname;
        public Listener listener = Listener.REFUSE_INCOMING_STREAMS;
        public PushObserver pushObserver = PushObserver.CANCEL;
        public BufferedSink sink;
        public Socket socket;
        public BufferedSource source;

        public Http2Connection build() throws IOException {
            return new Http2Connection(this);
        }

        public Builder(boolean z) {
            this.client = z;
        }

        public Builder listener(Listener listener2) {
            this.listener = listener2;
            return this;
        }

        public Builder pushObserver(PushObserver pushObserver2) {
            this.pushObserver = pushObserver2;
            return this;
        }

        public Builder socket(Socket socket2) throws IOException {
            socket(socket2, ((InetSocketAddress) socket2.getRemoteSocketAddress()).getHostName(), Okio.buffer(Okio.source(socket2)), new RealBufferedSink(Okio.sink(socket2)));
            return this;
        }

        public Builder socket(Socket socket2, String str, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.socket = socket2;
            this.hostname = str;
            this.source = bufferedSource;
            this.sink = bufferedSink;
            return this;
        }
    }

    public static abstract class Listener {
        public static final Listener REFUSE_INCOMING_STREAMS = new Listener() {
            /* class okhttp3.internal.http2.Http2Connection.Listener.AnonymousClass1 */

            @Override // okhttp3.internal.http2.Http2Connection.Listener
            public void onStream(Http2Stream http2Stream) throws IOException {
                http2Stream.close(ErrorCode.REFUSED_STREAM);
            }
        };

        public void onSettings(Http2Connection http2Connection) {
        }

        public abstract void onStream(Http2Stream http2Stream) throws IOException;
    }

    public class ReaderRunnable extends NamedRunnable implements Http2Reader.Handler {
        public final Http2Reader reader;

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ackSettings() {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void alternateService(int i, String str, ByteString byteString, String str2, int i2, long j) {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void priority(int i, int i2, int i3, boolean z) {
        }

        public ReaderRunnable(Http2Reader http2Reader) {
            super("OkHttp %s", Http2Connection.this.hostname);
            this.reader = http2Reader;
        }

        private void applyAndAckSettings(final Settings settings) {
            Http2Connection.executor.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[]{Http2Connection.this.hostname}) {
                /* class okhttp3.internal.http2.Http2Connection.ReaderRunnable.AnonymousClass3 */

                @Override // okhttp3.internal.NamedRunnable
                public void execute() {
                    try {
                        Http2Connection.this.writer.applyAndAckSettings(settings);
                    } catch (IOException unused) {
                    }
                }
            });
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void data(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException {
            Http2Connection http2Connection = Http2Connection.this;
            if (http2Connection.pushedStream(i)) {
                http2Connection.pushDataLater(i, bufferedSource, i2, z);
                return;
            }
            Http2Stream stream = http2Connection.getStream(i);
            if (stream == null) {
                Http2Connection.this.writeSynResetLater(i, ErrorCode.PROTOCOL_ERROR);
                bufferedSource.skip((long) i2);
                return;
            }
            stream.receiveData(bufferedSource, i2);
            if (z) {
                stream.receiveFin();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
            r0 = r4.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0026, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
            r4.this$0.close(r3, r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
            okhttp3.internal.Util.closeQuietly(r4.reader);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
            r3 = okhttp3.internal.http2.ErrorCode.PROTOCOL_ERROR;
            r1 = r3;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0018 */
        @Override // okhttp3.internal.NamedRunnable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void execute() {
            /*
                r4 = this;
                okhttp3.internal.http2.ErrorCode r3 = okhttp3.internal.http2.ErrorCode.INTERNAL_ERROR
                r2 = r3
                okhttp3.internal.http2.Http2Reader r0 = r4.reader     // Catch:{ IOException -> 0x0018 }
                r0.readConnectionPreface(r4)     // Catch:{ IOException -> 0x0018 }
            L_0x0008:
                okhttp3.internal.http2.Http2Reader r1 = r4.reader     // Catch:{ IOException -> 0x0018 }
                r0 = 0
                boolean r0 = r1.nextFrame(r0, r4)     // Catch:{ IOException -> 0x0018 }
                if (r0 != 0) goto L_0x0008
                okhttp3.internal.http2.ErrorCode r3 = okhttp3.internal.http2.ErrorCode.NO_ERROR     // Catch:{ IOException -> 0x0018 }
                okhttp3.internal.http2.ErrorCode r1 = okhttp3.internal.http2.ErrorCode.CANCEL     // Catch:{ IOException -> 0x0018 }
                okhttp3.internal.http2.Http2Connection r0 = okhttp3.internal.http2.Http2Connection.this     // Catch:{ IOException -> 0x0020 }
                goto L_0x001d
            L_0x0018:
                okhttp3.internal.http2.ErrorCode r3 = okhttp3.internal.http2.ErrorCode.PROTOCOL_ERROR     // Catch:{ all -> 0x0026 }
                r1 = r3
                okhttp3.internal.http2.Http2Connection r0 = okhttp3.internal.http2.Http2Connection.this
            L_0x001d:
                r0.close(r3, r1)
            L_0x0020:
                okhttp3.internal.http2.Http2Reader r0 = r4.reader
                okhttp3.internal.Util.closeQuietly(r0)
                return
            L_0x0026:
                r1 = move-exception
                okhttp3.internal.http2.Http2Connection r0 = okhttp3.internal.http2.Http2Connection.this     // Catch:{ IOException -> 0x002c }
                r0.close(r3, r2)     // Catch:{ IOException -> 0x002c }
            L_0x002c:
                okhttp3.internal.http2.Http2Reader r0 = r4.reader
                okhttp3.internal.Util.closeQuietly(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.ReaderRunnable.execute():void");
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void goAway(int i, ErrorCode errorCode, ByteString byteString) {
            Http2Stream[] http2StreamArr;
            synchronized (Http2Connection.this) {
                http2StreamArr = (Http2Stream[]) Http2Connection.this.streams.values().toArray(new Http2Stream[Http2Connection.this.streams.size()]);
                Http2Connection.this.shutdown = true;
            }
            for (Http2Stream http2Stream : http2StreamArr) {
                if (http2Stream.id > i && http2Stream.isLocallyInitiated()) {
                    http2Stream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    Http2Connection.this.removeStream(http2Stream.id);
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
            r0.receiveHeaders(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
            if (r12 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0057, code lost:
            r0.receiveFin();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x005a, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            return;
         */
        @Override // okhttp3.internal.http2.Http2Reader.Handler
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void headers(boolean r12, int r13, int r14, java.util.List<okhttp3.internal.http2.Header> r15) {
            /*
                r11 = this;
                okhttp3.internal.http2.Http2Connection r4 = okhttp3.internal.http2.Http2Connection.this
                r6 = r13
                boolean r0 = r4.pushedStream(r13)
                r10 = r15
                r9 = r12
                if (r0 == 0) goto L_0x000f
                r4.pushHeadersLater(r13, r15, r12)
            L_0x000e:
                return
            L_0x000f:
                monitor-enter(r4)
                okhttp3.internal.http2.Http2Connection r1 = okhttp3.internal.http2.Http2Connection.this     // Catch:{ all -> 0x005c }
                boolean r0 = r1.shutdown     // Catch:{ all -> 0x005c }
                if (r0 != 0) goto L_0x004f
                okhttp3.internal.http2.Http2Stream r0 = r1.getStream(r13)     // Catch:{ all -> 0x005c }
                if (r0 != 0) goto L_0x0051
                okhttp3.internal.http2.Http2Connection r7 = okhttp3.internal.http2.Http2Connection.this     // Catch:{ all -> 0x005c }
                int r0 = r7.lastGoodStreamId     // Catch:{ all -> 0x005c }
                if (r13 <= r0) goto L_0x004f
                int r2 = r13 % 2
                int r1 = r7.nextStreamId     // Catch:{ all -> 0x005c }
                r0 = 2
                int r1 = r1 % r0
                if (r2 == r1) goto L_0x004f
                r8 = 0
                okhttp3.internal.http2.Http2Stream r5 = new okhttp3.internal.http2.Http2Stream     // Catch:{ all -> 0x005c }
                r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x005c }
                r7.lastGoodStreamId = r13     // Catch:{ all -> 0x005c }
                java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r0 = r7.streams     // Catch:{ all -> 0x005c }
                java.lang.Integer r1 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x005c }
                r0.put(r1, r5)     // Catch:{ all -> 0x005c }
                java.util.concurrent.ExecutorService r3 = okhttp3.internal.http2.Http2Connection.executor     // Catch:{ all -> 0x005c }
                java.lang.String r2 = "OkHttp %s stream %d"
                okhttp3.internal.http2.Http2Connection r0 = okhttp3.internal.http2.Http2Connection.this     // Catch:{ all -> 0x005c }
                java.lang.String r0 = r0.hostname     // Catch:{ all -> 0x005c }
                java.lang.Object[] r1 = new java.lang.Object[]{r0, r1}     // Catch:{ all -> 0x005c }
                okhttp3.internal.http2.Http2Connection$ReaderRunnable$1 r0 = new okhttp3.internal.http2.Http2Connection$ReaderRunnable$1     // Catch:{ all -> 0x005c }
                r0.<init>(r2, r1, r5)     // Catch:{ all -> 0x005c }
                r3.execute(r0)     // Catch:{ all -> 0x005c }
            L_0x004f:
                monitor-exit(r4)     // Catch:{ all -> 0x005c }
                goto L_0x005b
            L_0x0051:
                monitor-exit(r4)     // Catch:{ all -> 0x005c }
                r0.receiveHeaders(r15)
                if (r12 == 0) goto L_0x000e
                r0.receiveFin()
                return
            L_0x005b:
                return
            L_0x005c:
                r0 = move-exception
                monitor-exit(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.ReaderRunnable.headers(boolean, int, int, java.util.List):void");
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ping(boolean z, int i, int i2) {
            if (z) {
                Ping removePing = Http2Connection.this.removePing(i);
                if (removePing != null) {
                    removePing.receive();
                    return;
                }
                return;
            }
            Http2Connection.this.writePingLater(true, i, i2, null);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void pushPromise(int i, int i2, List<Header> list) {
            Http2Connection.this.pushRequestLater(i2, list);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void rstStream(int i, ErrorCode errorCode) {
            Http2Connection http2Connection = Http2Connection.this;
            if (http2Connection.pushedStream(i)) {
                http2Connection.pushResetLater(i, errorCode);
                return;
            }
            Http2Stream removeStream = http2Connection.removeStream(i);
            if (removeStream != null) {
                removeStream.receiveRstStream(errorCode);
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void settings(boolean z, Settings settings) {
            Http2Stream[] http2StreamArr;
            long j;
            int i;
            synchronized (Http2Connection.this) {
                Settings settings2 = Http2Connection.this.peerSettings;
                int initialWindowSize = settings2.getInitialWindowSize();
                if (z) {
                    settings2.clear();
                }
                Http2Connection.this.peerSettings.merge(settings);
                applyAndAckSettings(settings);
                Http2Connection http2Connection = Http2Connection.this;
                Http2Connection http2Connection2 = http2Connection;
                int initialWindowSize2 = http2Connection.peerSettings.getInitialWindowSize();
                http2StreamArr = null;
                if (initialWindowSize2 == -1 || initialWindowSize2 == initialWindowSize) {
                    j = 0;
                } else {
                    j = (long) (initialWindowSize2 - initialWindowSize);
                    if (!http2Connection.receivedInitialPeerSettings) {
                        http2Connection.addBytesToWriteWindow(j);
                        http2Connection2 = Http2Connection.this;
                        http2Connection2.receivedInitialPeerSettings = true;
                    }
                    if (!http2Connection2.streams.isEmpty()) {
                        http2StreamArr = (Http2Stream[]) Http2Connection.this.streams.values().toArray(new Http2Stream[Http2Connection.this.streams.size()]);
                    }
                }
                Http2Connection.executor.execute(new NamedRunnable("OkHttp %s settings", Http2Connection.this.hostname) {
                    /* class okhttp3.internal.http2.Http2Connection.ReaderRunnable.AnonymousClass2 */

                    @Override // okhttp3.internal.NamedRunnable
                    public void execute() {
                        Http2Connection http2Connection = Http2Connection.this;
                        http2Connection.listener.onSettings(http2Connection);
                    }
                });
            }
            if (!(http2StreamArr == null || j == 0)) {
                for (Http2Stream http2Stream : http2StreamArr) {
                    synchronized (http2Stream) {
                        http2Stream.addBytesToWriteWindow(j);
                    }
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void windowUpdate(int i, long j) {
            Http2Connection http2Connection = Http2Connection.this;
            if (i == 0) {
                synchronized (http2Connection) {
                    Http2Connection http2Connection2 = Http2Connection.this;
                    http2Connection2.bytesLeftInWriteWindow += j;
                    http2Connection2.notifyAll();
                }
                return;
            }
            Http2Stream stream = http2Connection.getStream(i);
            if (stream != null) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(j);
                }
            }
        }
    }

    public synchronized Http2Stream getStream(int i) {
        return this.streams.get(Integer.valueOf(i));
    }

    public synchronized boolean isShutdown() {
        return this.shutdown;
    }

    public synchronized int maxConcurrentStreams() {
        return this.peerSettings.getMaxConcurrentStreams(Integer.MAX_VALUE);
    }

    public synchronized int openStreamCount() {
        return this.streams.size();
    }

    public void pushHeadersLater(final int i, final List<Header> list, final boolean z) {
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[]{this.hostname, Integer.valueOf(i)}) {
            /* class okhttp3.internal.http2.Http2Connection.AnonymousClass5 */

            @Override // okhttp3.internal.NamedRunnable
            public void execute() {
                if (Http2Connection.this.pushObserver.onHeaders(i, list, z)) {
                    Http2Connection.this.writer.rstStream(i, ErrorCode.CANCEL);
                } else {
                    try {
                        if (!z) {
                            return;
                        }
                    } catch (IOException unused) {
                        return;
                    }
                }
                synchronized (Http2Connection.this) {
                    Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i));
                }
            }
        });
    }

    public void pushRequestLater(final int i, final List<Header> list) {
        synchronized (this) {
            Set<Integer> set = this.currentPushRequests;
            Integer valueOf = Integer.valueOf(i);
            if (set.contains(valueOf)) {
                writeSynResetLater(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.currentPushRequests.add(valueOf);
            this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Request[%s]", new Object[]{this.hostname, valueOf}) {
                /* class okhttp3.internal.http2.Http2Connection.AnonymousClass4 */

                @Override // okhttp3.internal.NamedRunnable
                public void execute() {
                    if (Http2Connection.this.pushObserver.onRequest(i, list)) {
                        try {
                            Http2Connection.this.writer.rstStream(i, ErrorCode.CANCEL);
                            synchronized (Http2Connection.this) {
                                Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i));
                            }
                        } catch (IOException unused) {
                        }
                    }
                }
            });
        }
    }

    public void pushResetLater(final int i, final ErrorCode errorCode) {
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[]{this.hostname, Integer.valueOf(i)}) {
            /* class okhttp3.internal.http2.Http2Connection.AnonymousClass7 */

            @Override // okhttp3.internal.NamedRunnable
            public void execute() {
                Http2Connection.this.pushObserver.onReset(i, errorCode);
                synchronized (Http2Connection.this) {
                    Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i));
                }
            }
        });
    }

    public boolean pushedStream(int i) {
        return i != 0 && (i & 1) == 0;
    }

    public synchronized Ping removePing(int i) {
        Ping ping;
        Map<Integer, Ping> map = this.pings;
        if (map != null) {
            ping = map.remove(Integer.valueOf(i));
        } else {
            ping = null;
        }
        return ping;
    }

    public synchronized Http2Stream removeStream(int i) {
        Http2Stream remove;
        remove = this.streams.remove(Integer.valueOf(i));
        notifyAll();
        return remove;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r4 = java.lang.Math.min((int) java.lang.Math.min(r11, r2), r7.writer.maxFrameSize);
        r0 = (long) r4;
        r7.bytesLeftInWriteWindow = r2 - r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeData(int r8, boolean r9, okio.Buffer r10, long r11) throws java.io.IOException {
        /*
            r7 = this;
            r1 = 0
            r5 = 0
            int r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r0 != 0) goto L_0x000d
            okhttp3.internal.http2.Http2Writer r0 = r7.writer
            r0.data(r9, r8, r10, r1)
        L_0x000c:
            return
        L_0x000d:
            int r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x000c
            monitor-enter(r7)
        L_0x0012:
            long r2 = r7.bytesLeftInWriteWindow     // Catch:{ InterruptedException -> 0x0051 }
            int r0 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r0 > 0) goto L_0x0028
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.Http2Stream> r1 = r7.streams     // Catch:{ InterruptedException -> 0x0051 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)     // Catch:{ InterruptedException -> 0x0051 }
            boolean r0 = r1.containsKey(r0)     // Catch:{ InterruptedException -> 0x0051 }
            if (r0 == 0) goto L_0x0049
            r7.wait()     // Catch:{ InterruptedException -> 0x0051 }
            goto L_0x0012
        L_0x0028:
            long r0 = java.lang.Math.min(r11, r2)     // Catch:{ all -> 0x0057 }
            int r4 = (int) r0     // Catch:{ all -> 0x0057 }
            okhttp3.internal.http2.Http2Writer r0 = r7.writer     // Catch:{ all -> 0x0057 }
            int r0 = r0.maxFrameSize     // Catch:{ all -> 0x0057 }
            int r4 = java.lang.Math.min(r4, r0)     // Catch:{ all -> 0x0057 }
            long r0 = (long) r4     // Catch:{ all -> 0x0057 }
            long r2 = r2 - r0
            r7.bytesLeftInWriteWindow = r2     // Catch:{ all -> 0x0057 }
            monitor-exit(r7)     // Catch:{ all -> 0x0057 }
            long r11 = r11 - r0
            okhttp3.internal.http2.Http2Writer r2 = r7.writer
            if (r9 == 0) goto L_0x0044
            int r1 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            r0 = 1
            if (r1 == 0) goto L_0x0045
        L_0x0044:
            r0 = 0
        L_0x0045:
            r2.data(r0, r8, r10, r4)
            goto L_0x000d
        L_0x0049:
            java.lang.String r1 = "stream closed"
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x0051:
            java.io.InterruptedIOException r0 = new java.io.InterruptedIOException
            r0.<init>()
            throw r0
        L_0x0057:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.writeData(int, boolean, okio.Buffer, long):void");
    }

    public void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public void flush() throws IOException {
        this.writer.flush();
    }

    public Ping ping() throws IOException {
        int i;
        Ping ping = new Ping();
        synchronized (this) {
            if (!this.shutdown) {
                i = this.nextPingId;
                this.nextPingId = i + 2;
                Map map = this.pings;
                if (map == null) {
                    map = new LinkedHashMap();
                    this.pings = map;
                }
                map.put(Integer.valueOf(i), ping);
            } else {
                throw new ConnectionShutdownException();
            }
        }
        writePing(false, i, 1330343787, ping);
        return ping;
    }

    public void pushDataLater(final int i, BufferedSource bufferedSource, final int i2, final boolean z) throws IOException {
        final Buffer buffer = new Buffer();
        long j = (long) i2;
        bufferedSource.require(j);
        bufferedSource.read(buffer, j);
        long j2 = buffer.size;
        if (j2 == j) {
            this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[]{this.hostname, Integer.valueOf(i)}) {
                /* class okhttp3.internal.http2.Http2Connection.AnonymousClass6 */

                @Override // okhttp3.internal.NamedRunnable
                public void execute() {
                    try {
                        if (Http2Connection.this.pushObserver.onData(i, buffer, i2, z)) {
                            Http2Connection.this.writer.rstStream(i, ErrorCode.CANCEL);
                        } else if (!z) {
                            return;
                        }
                        synchronized (Http2Connection.this) {
                            Http2Connection.this.currentPushRequests.remove(Integer.valueOf(i));
                        }
                    } catch (IOException unused) {
                    }
                }
            });
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(j2);
        sb.append(" != ");
        sb.append(i2);
        throw new IOException(sb.toString());
    }

    public Http2Stream pushStream(int i, List<Header> list, boolean z) throws IOException {
        if (!this.client) {
            return newStream(i, list, z);
        }
        throw new IllegalStateException("Client cannot push requests.");
    }

    public void setSettings(Settings settings) throws IOException {
        synchronized (this.writer) {
            synchronized (this) {
                if (!this.shutdown) {
                    this.okHttpSettings.merge(settings);
                    this.writer.settings(settings);
                } else {
                    throw new ConnectionShutdownException();
                }
            }
        }
    }

    public void shutdown(ErrorCode errorCode) throws IOException {
        synchronized (this.writer) {
            synchronized (this) {
                if (!this.shutdown) {
                    this.shutdown = true;
                    this.writer.goAway(this.lastGoodStreamId, errorCode, Util.EMPTY_BYTE_ARRAY);
                }
            }
        }
    }

    public void writePing(boolean z, int i, int i2, Ping ping) throws IOException {
        synchronized (this.writer) {
            if (ping != null) {
                ping.send();
            }
            this.writer.ping(z, i, i2);
        }
    }

    public void writePingLater(final boolean z, final int i, final int i2, final Ping ping) {
        executor.execute(new NamedRunnable("OkHttp %s ping %08x%08x", new Object[]{this.hostname, Integer.valueOf(i), Integer.valueOf(i2)}) {
            /* class okhttp3.internal.http2.Http2Connection.AnonymousClass3 */

            @Override // okhttp3.internal.NamedRunnable
            public void execute() {
                try {
                    Http2Connection.this.writePing(z, i, i2, ping);
                } catch (IOException unused) {
                }
            }
        });
    }

    public void writeSynReply(int i, boolean z, List<Header> list) throws IOException {
        this.writer.synReply(z, i, list);
    }

    public void writeSynReset(int i, ErrorCode errorCode) throws IOException {
        this.writer.rstStream(i, errorCode);
    }

    public void writeSynResetLater(final int i, final ErrorCode errorCode) {
        executor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[]{this.hostname, Integer.valueOf(i)}) {
            /* class okhttp3.internal.http2.Http2Connection.AnonymousClass1 */

            @Override // okhttp3.internal.NamedRunnable
            public void execute() {
                try {
                    Http2Connection.this.writeSynReset(i, errorCode);
                } catch (IOException unused) {
                }
            }
        });
    }

    public void writeWindowUpdateLater(final int i, final long j) {
        executor.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[]{this.hostname, Integer.valueOf(i)}) {
            /* class okhttp3.internal.http2.Http2Connection.AnonymousClass2 */

            @Override // okhttp3.internal.NamedRunnable
            public void execute() {
                try {
                    Http2Connection.this.writer.windowUpdate(i, j);
                } catch (IOException unused) {
                }
            }
        });
    }

    public Http2Connection(Builder builder) {
        this.pushObserver = builder.pushObserver;
        boolean z = builder.client;
        this.client = z;
        this.listener = builder.listener;
        int i = 2;
        int i2 = z ? 1 : 2;
        this.nextStreamId = i2;
        if (z) {
            this.nextStreamId = i2 + 2;
            i = 1;
        }
        this.nextPingId = i;
        if (z) {
            this.okHttpSettings.set(7, 16777216);
        }
        this.hostname = builder.hostname;
        this.pushExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), 
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0072: IPUT  
              (wrap: java.util.concurrent.ThreadPoolExecutor : 0x006f: CONSTRUCTOR  (r4v0 java.util.concurrent.ThreadPoolExecutor) = 
              (0 int)
              (1 int)
              (60 long)
              (wrap: java.util.concurrent.TimeUnit : 0x0053: SGET  (r9v0 java.util.concurrent.TimeUnit) =  java.util.concurrent.TimeUnit.SECONDS java.util.concurrent.TimeUnit)
              (wrap: java.util.concurrent.LinkedBlockingQueue : 0x0057: CONSTRUCTOR  (r10v0 java.util.concurrent.LinkedBlockingQueue) =  call: java.util.concurrent.LinkedBlockingQueue.<init>():void type: CONSTRUCTOR)
              (wrap: okhttp3.internal.Util$1 : 0x006a: CONSTRUCTOR  (r11v0 okhttp3.internal.Util$1) = 
              (wrap: java.lang.String : 0x0064: INVOKE  (r0v13 java.lang.String) = 
              (wrap: java.util.Locale : 0x0062: SGET  (r0v12 java.util.Locale) =  java.util.Locale.US java.util.Locale)
              ("OkHttp %s Push Observer")
              (wrap: java.lang.Object[] : 0x005c: FILLED_NEW_ARRAY  (r2v1 java.lang.Object[]) = 
              (wrap: java.lang.String : 0x005a: IGET  (r0v11 java.lang.String) = (r12v0 'this' okhttp3.internal.http2.Http2Connection A[IMMUTABLE_TYPE, THIS]) okhttp3.internal.http2.Http2Connection.hostname java.lang.String)
             elemType: java.lang.Object)
             type: STATIC call: java.lang.String.format(java.util.Locale, java.lang.String, java.lang.Object[]):java.lang.String)
              true
             call: okhttp3.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR)
             call: java.util.concurrent.ThreadPoolExecutor.<init>(int, int, long, java.util.concurrent.TimeUnit, java.util.concurrent.BlockingQueue, java.util.concurrent.ThreadFactory):void type: CONSTRUCTOR)
              (r12v0 'this' okhttp3.internal.http2.Http2Connection A[IMMUTABLE_TYPE, THIS])
             okhttp3.internal.http2.Http2Connection.pushExecutor java.util.concurrent.ExecutorService in method: okhttp3.internal.http2.Http2Connection.<init>(okhttp3.internal.http2.Http2Connection$Builder):void, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
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
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x006f: CONSTRUCTOR  (r4v0 java.util.concurrent.ThreadPoolExecutor) = 
              (0 int)
              (1 int)
              (60 long)
              (wrap: java.util.concurrent.TimeUnit : 0x0053: SGET  (r9v0 java.util.concurrent.TimeUnit) =  java.util.concurrent.TimeUnit.SECONDS java.util.concurrent.TimeUnit)
              (wrap: java.util.concurrent.LinkedBlockingQueue : 0x0057: CONSTRUCTOR  (r10v0 java.util.concurrent.LinkedBlockingQueue) =  call: java.util.concurrent.LinkedBlockingQueue.<init>():void type: CONSTRUCTOR)
              (wrap: okhttp3.internal.Util$1 : 0x006a: CONSTRUCTOR  (r11v0 okhttp3.internal.Util$1) = 
              (wrap: java.lang.String : 0x0064: INVOKE  (r0v13 java.lang.String) = 
              (wrap: java.util.Locale : 0x0062: SGET  (r0v12 java.util.Locale) =  java.util.Locale.US java.util.Locale)
              ("OkHttp %s Push Observer")
              (wrap: java.lang.Object[] : 0x005c: FILLED_NEW_ARRAY  (r2v1 java.lang.Object[]) = 
              (wrap: java.lang.String : 0x005a: IGET  (r0v11 java.lang.String) = (r12v0 'this' okhttp3.internal.http2.Http2Connection A[IMMUTABLE_TYPE, THIS]) okhttp3.internal.http2.Http2Connection.hostname java.lang.String)
             elemType: java.lang.Object)
             type: STATIC call: java.lang.String.format(java.util.Locale, java.lang.String, java.lang.Object[]):java.lang.String)
              true
             call: okhttp3.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR)
             call: java.util.concurrent.ThreadPoolExecutor.<init>(int, int, long, java.util.concurrent.TimeUnit, java.util.concurrent.BlockingQueue, java.util.concurrent.ThreadFactory):void type: CONSTRUCTOR in method: okhttp3.internal.http2.Http2Connection.<init>(okhttp3.internal.http2.Http2Connection$Builder):void, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:428)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
            	... 14 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x006a: CONSTRUCTOR  (r11v0 okhttp3.internal.Util$1) = 
              (wrap: java.lang.String : 0x0064: INVOKE  (r0v13 java.lang.String) = 
              (wrap: java.util.Locale : 0x0062: SGET  (r0v12 java.util.Locale) =  java.util.Locale.US java.util.Locale)
              ("OkHttp %s Push Observer")
              (wrap: java.lang.Object[] : 0x005c: FILLED_NEW_ARRAY  (r2v1 java.lang.Object[]) = 
              (wrap: java.lang.String : 0x005a: IGET  (r0v11 java.lang.String) = (r12v0 'this' okhttp3.internal.http2.Http2Connection A[IMMUTABLE_TYPE, THIS]) okhttp3.internal.http2.Http2Connection.hostname java.lang.String)
             elemType: java.lang.Object)
             type: STATIC call: java.lang.String.format(java.util.Locale, java.lang.String, java.lang.Object[]):java.lang.String)
              true
             call: okhttp3.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR in method: okhttp3.internal.http2.Http2Connection.<init>(okhttp3.internal.http2.Http2Connection$Builder):void, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:663)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 18 more
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: okhttp3.internal.Util, state: GENERATED_AND_UNLOADED
            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 24 more
            */
        /*
        // Method dump skipped, instructions count: 167
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.<init>(okhttp3.internal.http2.Http2Connection$Builder):void");
    }

    public Protocol getProtocol() {
        return Protocol.HTTP_2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        if (r7.bytesLeftInWriteWindow == 0) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.internal.http2.Http2Stream newStream(int r14, java.util.List<okhttp3.internal.http2.Header> r15, boolean r16) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 104
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Connection.newStream(int, java.util.List, boolean):okhttp3.internal.http2.Http2Stream");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    public void close(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        IOException e;
        Http2Stream[] http2StreamArr;
        Ping[] pingArr = null;
        try {
            shutdown(errorCode);
            e = null;
        } catch (IOException e2) {
            e = e2;
        }
        synchronized (this) {
            if (!this.streams.isEmpty()) {
                http2StreamArr = (Http2Stream[]) this.streams.values().toArray(new Http2Stream[this.streams.size()]);
                this.streams.clear();
            } else {
                http2StreamArr = null;
            }
            Map<Integer, Ping> map = this.pings;
            if (map != null) {
                Ping[] pingArr2 = (Ping[]) map.values().toArray(new Ping[this.pings.size()]);
                this.pings = null;
                pingArr = pingArr2;
            }
        }
        if (http2StreamArr != null) {
            for (Http2Stream http2Stream : http2StreamArr) {
                try {
                    http2Stream.close(errorCode2);
                } catch (IOException e3) {
                    if (e != null) {
                        e = e3;
                    }
                }
            }
        }
        if (pingArr != null) {
            for (Ping ping : pingArr) {
                ping.cancel();
            }
        }
        try {
            this.writer.close();
        } catch (IOException e4) {
            if (e == null) {
                e = e4;
            }
        }
        try {
            this.socket.close();
            if (e != null) {
                throw e;
            }
        } catch (IOException e5) {
            throw e5;
        }
    }

    public Http2Stream newStream(List<Header> list, boolean z) throws IOException {
        return newStream(0, list, z);
    }

    public void start() throws IOException {
        start(true);
    }

    public void start(boolean z) throws IOException {
        if (z) {
            this.writer.connectionPreface();
            this.writer.settings(this.okHttpSettings);
            int initialWindowSize = this.okHttpSettings.getInitialWindowSize();
            if (initialWindowSize != 65535) {
                this.writer.windowUpdate(0, (long) (initialWindowSize - Settings.DEFAULT_INITIAL_WINDOW_SIZE));
            }
        }
        new Thread(this.readerRunnable).start();
    }
}
