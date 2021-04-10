package com.squareup.okhttp.internal.framed;

import X.AnonymousClass0HO;
import X.AnonymousClass0HP;
import X.AnonymousClass0HR;
import X.AnonymousClass0Od;
import X.AnonymousClass0Oe;
import X.C04600h6;
import X.C04610h7;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.framed.FrameReader;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class FramedConnection implements Closeable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    public static final ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), ;
    public long bytesLeftInWriteWindow;
    public final boolean client;
    public final Set<Integer> currentPushRequests;
    public final FrameWriter frameWriter;
    public final String hostName;
    public long idleStartTimeNs;
    public int lastGoodStreamId;
    public final Listener listener;
    public int nextPingId;
    public int nextStreamId;
    public Settings okHttpSettings;
    public final Settings peerSettings;
    public Map<Integer, Ping> pings;
    public final Protocol protocol;
    public final ExecutorService pushExecutor;
    public final PushObserver pushObserver;
    public final Reader readerRunnable;
    public boolean receivedInitialPeerSettings;
    public boolean shutdown;
    public final Socket socket;
    public final Map<Integer, FramedStream> streams;
    public long unacknowledgedBytesRead;
    public final Variant variant;

    public static class Builder {
        public boolean client;
        public String hostName;
        public Listener listener = Listener.REFUSE_INCOMING_STREAMS;
        public Protocol protocol = Protocol.SPDY_3;
        public PushObserver pushObserver = PushObserver.CANCEL;
        public AnonymousClass0Oe sink;
        public Socket socket;
        public AnonymousClass0Od source;

        public FramedConnection build() throws IOException {
            return new FramedConnection(this);
        }

        public Builder(boolean z) throws IOException {
            this.client = z;
        }

        public Builder listener(Listener listener2) {
            this.listener = listener2;
            return this;
        }

        public Builder protocol(Protocol protocol2) {
            this.protocol = protocol2;
            return this;
        }

        public Builder pushObserver(PushObserver pushObserver2) {
            this.pushObserver = pushObserver2;
            return this;
        }

        public Builder socket(Socket socket2) throws IOException {
            socket(socket2, ((InetSocketAddress) socket2.getRemoteSocketAddress()).getHostName(), new AnonymousClass0HO(C04600h6.A01(socket2)), new AnonymousClass0HP(C04600h6.A00(socket2)));
            return this;
        }

        public Builder socket(Socket socket2, String str, AnonymousClass0Od r3, AnonymousClass0Oe r4) {
            this.socket = socket2;
            this.hostName = str;
            this.source = r3;
            this.sink = r4;
            return this;
        }
    }

    public static abstract class Listener {
        public static final Listener REFUSE_INCOMING_STREAMS = new Listener() {
            /* class com.squareup.okhttp.internal.framed.FramedConnection.Listener.AnonymousClass1 */

            @Override // com.squareup.okhttp.internal.framed.FramedConnection.Listener
            public void onStream(FramedStream framedStream) throws IOException {
                framedStream.close(ErrorCode.REFUSED_STREAM);
            }
        };

        public void onSettings(FramedConnection framedConnection) {
        }

        public abstract void onStream(FramedStream framedStream) throws IOException;
    }

    public class Reader extends NamedRunnable implements FrameReader.Handler {
        public final FrameReader frameReader;

        @Override // com.squareup.okhttp.internal.framed.FrameReader.Handler
        public void ackSettings() {
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader.Handler
        public void alternateService(int i, String str, C04610h7 r3, String str2, int i2, long j) {
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader.Handler
        public void priority(int i, int i2, int i3, boolean z) {
        }

        private void ackSettingsLater(final Settings settings) {
            FramedConnection.executor.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[]{FramedConnection.this.hostName}) {
                /* class com.squareup.okhttp.internal.framed.FramedConnection.Reader.AnonymousClass3 */

                @Override // com.squareup.okhttp.internal.NamedRunnable
                public void execute() {
                    try {
                        FramedConnection.this.frameWriter.ackSettings(settings);
                    } catch (IOException unused) {
                    }
                }
            });
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader.Handler
        public void data(boolean z, int i, AnonymousClass0Od r5, int i2) throws IOException {
            FramedConnection framedConnection = FramedConnection.this;
            if (framedConnection.pushedStream(i)) {
                framedConnection.pushDataLater(i, r5, i2, z);
                return;
            }
            FramedStream stream = framedConnection.getStream(i);
            if (stream == null) {
                FramedConnection.this.writeSynResetLater(i, ErrorCode.INVALID_STREAM);
                r5.A8T((long) i2);
                return;
            }
            stream.receiveData(r5, i2);
            if (z) {
                stream.receiveFin();
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(3:10|11|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            r3 = com.squareup.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR;
            r1 = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
            r0 = r4.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002b, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            r4.this$0.close(r3, r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
            com.squareup.okhttp.internal.Util.closeQuietly(r4.frameReader);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0036, code lost:
            throw r1;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001d */
        @Override // com.squareup.okhttp.internal.NamedRunnable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void execute() {
            /*
                r4 = this;
                com.squareup.okhttp.internal.framed.ErrorCode r3 = com.squareup.okhttp.internal.framed.ErrorCode.INTERNAL_ERROR
                r2 = r3
                com.squareup.okhttp.internal.framed.FramedConnection r0 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ IOException -> 0x001d }
                boolean r0 = r0.client     // Catch:{ IOException -> 0x001d }
                if (r0 != 0) goto L_0x000e
                com.squareup.okhttp.internal.framed.FrameReader r0 = r4.frameReader     // Catch:{ IOException -> 0x001d }
                r0.readConnectionPreface()     // Catch:{ IOException -> 0x001d }
            L_0x000e:
                com.squareup.okhttp.internal.framed.FrameReader r0 = r4.frameReader     // Catch:{ IOException -> 0x001d }
                boolean r0 = r0.nextFrame(r4)     // Catch:{ IOException -> 0x001d }
                if (r0 != 0) goto L_0x000e
                com.squareup.okhttp.internal.framed.ErrorCode r3 = com.squareup.okhttp.internal.framed.ErrorCode.NO_ERROR     // Catch:{ IOException -> 0x001d }
                com.squareup.okhttp.internal.framed.ErrorCode r1 = com.squareup.okhttp.internal.framed.ErrorCode.CANCEL     // Catch:{ IOException -> 0x001d }
                com.squareup.okhttp.internal.framed.FramedConnection r0 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ IOException -> 0x0025 }
                goto L_0x0022
            L_0x001d:
                com.squareup.okhttp.internal.framed.ErrorCode r3 = com.squareup.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR     // Catch:{ all -> 0x002b }
                r1 = r3
                com.squareup.okhttp.internal.framed.FramedConnection r0 = com.squareup.okhttp.internal.framed.FramedConnection.this
            L_0x0022:
                com.squareup.okhttp.internal.framed.FramedConnection.access$1200(r0, r3, r1)
            L_0x0025:
                com.squareup.okhttp.internal.framed.FrameReader r0 = r4.frameReader
                com.squareup.okhttp.internal.Util.closeQuietly(r0)
                return
            L_0x002b:
                r1 = move-exception
                com.squareup.okhttp.internal.framed.FramedConnection r0 = com.squareup.okhttp.internal.framed.FramedConnection.this     // Catch:{ IOException -> 0x0031 }
                com.squareup.okhttp.internal.framed.FramedConnection.access$1200(r0, r3, r2)     // Catch:{ IOException -> 0x0031 }
            L_0x0031:
                com.squareup.okhttp.internal.framed.FrameReader r0 = r4.frameReader
                com.squareup.okhttp.internal.Util.closeQuietly(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.framed.FramedConnection.Reader.execute():void");
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader.Handler
        public void goAway(int i, ErrorCode errorCode, C04610h7 r9) {
            FramedStream[] framedStreamArr;
            synchronized (FramedConnection.this) {
                framedStreamArr = (FramedStream[]) FramedConnection.this.streams.values().toArray(new FramedStream[FramedConnection.this.streams.size()]);
                FramedConnection.this.shutdown = true;
            }
            for (FramedStream framedStream : framedStreamArr) {
                if (framedStream.id > i && framedStream.isLocallyInitiated()) {
                    framedStream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    FramedConnection.this.removeStream(framedStream.id);
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0067, code lost:
            if (r17.failIfStreamPresent() == false) goto L_0x0074;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0069, code lost:
            r1.closeLater(com.squareup.okhttp.internal.framed.ErrorCode.PROTOCOL_ERROR);
            r11.this$0.removeStream(r14);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0073, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0074, code lost:
            r1.receiveHeaders(r16, r17);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0077, code lost:
            if (r13 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0079, code lost:
            r1.receiveFin();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x007c, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        @Override // com.squareup.okhttp.internal.framed.FrameReader.Handler
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void headers(boolean r12, boolean r13, int r14, int r15, java.util.List<com.squareup.okhttp.internal.framed.Header> r16, com.squareup.okhttp.internal.framed.HeadersMode r17) {
            /*
            // Method dump skipped, instructions count: 129
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.framed.FramedConnection.Reader.headers(boolean, boolean, int, int, java.util.List, com.squareup.okhttp.internal.framed.HeadersMode):void");
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader.Handler
        public void ping(boolean z, int i, int i2) {
            if (z) {
                Ping removePing = FramedConnection.this.removePing(i);
                if (removePing != null) {
                    removePing.receive();
                    return;
                }
                return;
            }
            FramedConnection.this.writePingLater(true, i, i2, null);
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader.Handler
        public void pushPromise(int i, int i2, List<Header> list) {
            FramedConnection.this.pushRequestLater(i2, list);
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader.Handler
        public void rstStream(int i, ErrorCode errorCode) {
            FramedConnection framedConnection = FramedConnection.this;
            if (framedConnection.pushedStream(i)) {
                framedConnection.pushResetLater(i, errorCode);
                return;
            }
            FramedStream removeStream = framedConnection.removeStream(i);
            if (removeStream != null) {
                removeStream.receiveRstStream(errorCode);
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader.Handler
        public void settings(boolean z, Settings settings) {
            FramedStream[] framedStreamArr;
            long j;
            int i;
            synchronized (FramedConnection.this) {
                Settings settings2 = FramedConnection.this.peerSettings;
                int initialWindowSize = settings2.getInitialWindowSize(65536);
                if (z) {
                    settings2.clear();
                }
                FramedConnection framedConnection = FramedConnection.this;
                framedConnection.peerSettings.merge(settings);
                if (framedConnection.protocol == Protocol.HTTP_2) {
                    ackSettingsLater(settings);
                }
                FramedConnection framedConnection2 = FramedConnection.this;
                FramedConnection framedConnection3 = framedConnection2;
                int initialWindowSize2 = framedConnection2.peerSettings.getInitialWindowSize(65536);
                framedStreamArr = null;
                if (initialWindowSize2 == -1 || initialWindowSize2 == initialWindowSize) {
                    j = 0;
                } else {
                    j = (long) (initialWindowSize2 - initialWindowSize);
                    if (!framedConnection2.receivedInitialPeerSettings) {
                        framedConnection2.addBytesToWriteWindow(j);
                        framedConnection3 = FramedConnection.this;
                        framedConnection3.receivedInitialPeerSettings = true;
                    }
                    if (!framedConnection3.streams.isEmpty()) {
                        framedStreamArr = (FramedStream[]) FramedConnection.this.streams.values().toArray(new FramedStream[FramedConnection.this.streams.size()]);
                    }
                }
                FramedConnection.executor.execute(new NamedRunnable("OkHttp %s settings", FramedConnection.this.hostName) {
                    /* class com.squareup.okhttp.internal.framed.FramedConnection.Reader.AnonymousClass2 */

                    @Override // com.squareup.okhttp.internal.NamedRunnable
                    public void execute() {
                    }
                });
            }
            if (!(framedStreamArr == null || j == 0)) {
                for (FramedStream framedStream : framedStreamArr) {
                    synchronized (framedStream) {
                        framedStream.addBytesToWriteWindow(j);
                    }
                }
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader.Handler
        public void windowUpdate(int i, long j) {
            FramedConnection framedConnection = FramedConnection.this;
            if (i == 0) {
                synchronized (framedConnection) {
                    FramedConnection framedConnection2 = FramedConnection.this;
                    framedConnection2.bytesLeftInWriteWindow += j;
                    framedConnection2.notifyAll();
                }
                return;
            }
            FramedStream stream = framedConnection.getStream(i);
            if (stream != null) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(j);
                }
            }
        }

        public Reader(FrameReader frameReader2) {
            super("OkHttp %s", FramedConnection.this.hostName);
            this.frameReader = frameReader2;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void pushHeadersLater(final int i, final List<Header> list, final boolean z) {
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[]{this.hostName, Integer.valueOf(i)}) {
            /* class com.squareup.okhttp.internal.framed.FramedConnection.AnonymousClass5 */

            @Override // com.squareup.okhttp.internal.NamedRunnable
            public void execute() {
                if (FramedConnection.this.pushObserver.onHeaders(i, list, z)) {
                    FramedConnection.this.frameWriter.rstStream(i, ErrorCode.CANCEL);
                } else {
                    try {
                        if (!z) {
                            return;
                        }
                    } catch (IOException unused) {
                        return;
                    }
                }
                synchronized (FramedConnection.this) {
                    FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void pushRequestLater(final int i, final List<Header> list) {
        synchronized (this) {
            Set<Integer> set = this.currentPushRequests;
            Integer valueOf = Integer.valueOf(i);
            if (set.contains(valueOf)) {
                writeSynResetLater(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.currentPushRequests.add(valueOf);
            this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Request[%s]", new Object[]{this.hostName, valueOf}) {
                /* class com.squareup.okhttp.internal.framed.FramedConnection.AnonymousClass4 */

                @Override // com.squareup.okhttp.internal.NamedRunnable
                public void execute() {
                    if (FramedConnection.this.pushObserver.onRequest(i, list)) {
                        try {
                            FramedConnection.this.frameWriter.rstStream(i, ErrorCode.CANCEL);
                            synchronized (FramedConnection.this) {
                                FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i));
                            }
                        } catch (IOException unused) {
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void pushResetLater(final int i, final ErrorCode errorCode) {
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[]{this.hostName, Integer.valueOf(i)}) {
            /* class com.squareup.okhttp.internal.framed.FramedConnection.AnonymousClass7 */

            @Override // com.squareup.okhttp.internal.NamedRunnable
            public void execute() {
                FramedConnection.this.pushObserver.onReset(i, errorCode);
                synchronized (FramedConnection.this) {
                    FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized Ping removePing(int i) {
        Ping ping;
        Map<Integer, Ping> map = this.pings;
        if (map != null) {
            ping = map.remove(Integer.valueOf(i));
        } else {
            ping = null;
        }
        return ping;
    }

    private synchronized void setIdle(boolean z) {
        long j;
        if (z) {
            j = System.nanoTime();
        } else {
            j = Long.MAX_VALUE;
        }
        this.idleStartTimeNs = j;
    }

    public synchronized long getIdleStartTimeNs() {
        return this.idleStartTimeNs;
    }

    public synchronized FramedStream getStream(int i) {
        return this.streams.get(Integer.valueOf(i));
    }

    public synchronized boolean isIdle() {
        boolean z;
        z = false;
        if (this.idleStartTimeNs != Long.MAX_VALUE) {
            z = true;
        }
        return z;
    }

    public synchronized int maxConcurrentStreams() {
        return this.peerSettings.getMaxConcurrentStreams(Integer.MAX_VALUE);
    }

    public synchronized int openStreamCount() {
        return this.streams.size();
    }

    public synchronized FramedStream removeStream(int i) {
        FramedStream remove;
        remove = this.streams.remove(Integer.valueOf(i));
        if (remove != null && this.streams.isEmpty()) {
            setIdle(true);
        }
        notifyAll();
        return remove;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r4 = java.lang.Math.min((int) java.lang.Math.min(r11, r0), r7.frameWriter.maxDataLength());
        r0 = (long) r4;
        r7.bytesLeftInWriteWindow -= r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeData(int r8, boolean r9, X.AnonymousClass0HR r10, long r11) throws java.io.IOException {
        /*
            r7 = this;
            r1 = 0
            r5 = 0
            int r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r0 != 0) goto L_0x000d
            com.squareup.okhttp.internal.framed.FrameWriter r0 = r7.frameWriter
            r0.data(r9, r8, r10, r1)
        L_0x000c:
            return
        L_0x000d:
            int r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x000c
            monitor-enter(r7)
        L_0x0012:
            long r0 = r7.bytesLeftInWriteWindow     // Catch:{ InterruptedException -> 0x0055 }
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 > 0) goto L_0x0028
            java.util.Map<java.lang.Integer, com.squareup.okhttp.internal.framed.FramedStream> r1 = r7.streams     // Catch:{ InterruptedException -> 0x0055 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)     // Catch:{ InterruptedException -> 0x0055 }
            boolean r0 = r1.containsKey(r0)     // Catch:{ InterruptedException -> 0x0055 }
            if (r0 == 0) goto L_0x004d
            r7.wait()     // Catch:{ InterruptedException -> 0x0055 }
            goto L_0x0012
        L_0x0028:
            long r0 = java.lang.Math.min(r11, r0)     // Catch:{ all -> 0x005b }
            int r2 = (int) r0     // Catch:{ all -> 0x005b }
            com.squareup.okhttp.internal.framed.FrameWriter r0 = r7.frameWriter     // Catch:{ all -> 0x005b }
            int r0 = r0.maxDataLength()     // Catch:{ all -> 0x005b }
            int r4 = java.lang.Math.min(r2, r0)     // Catch:{ all -> 0x005b }
            long r2 = r7.bytesLeftInWriteWindow     // Catch:{ all -> 0x005b }
            long r0 = (long) r4     // Catch:{ all -> 0x005b }
            long r2 = r2 - r0
            r7.bytesLeftInWriteWindow = r2     // Catch:{ all -> 0x005b }
            monitor-exit(r7)     // Catch:{ all -> 0x005b }
            long r11 = r11 - r0
            com.squareup.okhttp.internal.framed.FrameWriter r2 = r7.frameWriter
            if (r9 == 0) goto L_0x0048
            int r1 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            r0 = 1
            if (r1 == 0) goto L_0x0049
        L_0x0048:
            r0 = 0
        L_0x0049:
            r2.data(r0, r8, r10, r4)
            goto L_0x000d
        L_0x004d:
            java.lang.String r1 = "stream closed"
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x0055:
            java.io.InterruptedIOException r0 = new java.io.InterruptedIOException
            r0.<init>()
            throw r0
        L_0x005b:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.framed.FramedConnection.writeData(int, boolean, X.0HR, long):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void pushDataLater(final int i, AnonymousClass0Od r13, final int i2, final boolean z) throws IOException {
        final AnonymousClass0HR r8 = new AnonymousClass0HR();
        long j = (long) i2;
        r13.A7R(j);
        r13.read(r8, j);
        long j2 = r8.A00;
        if (j2 == j) {
            this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[]{this.hostName, Integer.valueOf(i)}) {
                /* class com.squareup.okhttp.internal.framed.FramedConnection.AnonymousClass6 */

                @Override // com.squareup.okhttp.internal.NamedRunnable
                public void execute() {
                    try {
                        if (FramedConnection.this.pushObserver.onData(i, r8, i2, z)) {
                            FramedConnection.this.frameWriter.rstStream(i, ErrorCode.CANCEL);
                        } else if (!z) {
                            return;
                        }
                        synchronized (FramedConnection.this) {
                            FramedConnection.this.currentPushRequests.remove(Integer.valueOf(i));
                        }
                    } catch (IOException unused) {
                    }
                }
            });
            return;
        }
        throw new IOException(j2 + " != " + i2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean pushedStream(int i) {
        if (this.protocol == Protocol.HTTP_2 && i != 0 && (i & 1) == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void writePing(boolean z, int i, int i2, Ping ping) throws IOException {
        synchronized (this.frameWriter) {
            if (ping != null) {
                ping.send();
            }
            this.frameWriter.ping(z, i, i2);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void writePingLater(final boolean z, final int i, final int i2, final Ping ping) {
        executor.execute(new NamedRunnable("OkHttp %s ping %08x%08x", new Object[]{this.hostName, Integer.valueOf(i), Integer.valueOf(i2)}) {
            /* class com.squareup.okhttp.internal.framed.FramedConnection.AnonymousClass3 */

            @Override // com.squareup.okhttp.internal.NamedRunnable
            public void execute() {
                try {
                    FramedConnection.this.writePing(z, i, i2, ping);
                } catch (IOException unused) {
                }
            }
        });
    }

    public void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public void flush() throws IOException {
        this.frameWriter.flush();
    }

    public Protocol getProtocol() {
        return this.protocol;
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
                    map = new HashMap();
                    this.pings = map;
                }
                map.put(Integer.valueOf(i), ping);
            } else {
                throw new IOException("shutdown");
            }
        }
        writePing(false, i, 1330343787, ping);
        return ping;
    }

    public FramedStream pushStream(int i, List<Header> list, boolean z) throws IOException {
        String str;
        if (this.client) {
            str = "Client cannot push requests.";
        } else if (this.protocol == Protocol.HTTP_2) {
            return newStream(i, list, z, false);
        } else {
            str = "protocol != HTTP_2";
        }
        throw new IllegalStateException(str);
    }

    public void sendConnectionPreface() throws IOException {
        this.frameWriter.connectionPreface();
        this.frameWriter.settings(this.okHttpSettings);
        int initialWindowSize = this.okHttpSettings.getInitialWindowSize(65536);
        if (initialWindowSize != 65536) {
            this.frameWriter.windowUpdate(0, (long) (initialWindowSize - 65536));
        }
    }

    public void setSettings(Settings settings) throws IOException {
        synchronized (this.frameWriter) {
            synchronized (this) {
                if (!this.shutdown) {
                    this.okHttpSettings.merge(settings);
                    this.frameWriter.settings(settings);
                } else {
                    throw new IOException("shutdown");
                }
            }
        }
    }

    public void shutdown(ErrorCode errorCode) throws IOException {
        synchronized (this.frameWriter) {
            synchronized (this) {
                if (!this.shutdown) {
                    this.shutdown = true;
                    this.frameWriter.goAway(this.lastGoodStreamId, errorCode, Util.EMPTY_BYTE_ARRAY);
                }
            }
        }
    }

    public void writeSynReply(int i, boolean z, List<Header> list) throws IOException {
        this.frameWriter.synReply(z, i, list);
    }

    public void writeSynReset(int i, ErrorCode errorCode) throws IOException {
        this.frameWriter.rstStream(i, errorCode);
    }

    public void writeSynResetLater(final int i, final ErrorCode errorCode) {
        executor.submit(new NamedRunnable("OkHttp %s stream %d", new Object[]{this.hostName, Integer.valueOf(i)}) {
            /* class com.squareup.okhttp.internal.framed.FramedConnection.AnonymousClass1 */

            @Override // com.squareup.okhttp.internal.NamedRunnable
            public void execute() {
                try {
                    FramedConnection.this.writeSynReset(i, errorCode);
                } catch (IOException unused) {
                }
            }
        });
    }

    public void writeWindowUpdateLater(final int i, final long j) {
        executor.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[]{this.hostName, Integer.valueOf(i)}) {
            /* class com.squareup.okhttp.internal.framed.FramedConnection.AnonymousClass2 */

            @Override // com.squareup.okhttp.internal.NamedRunnable
            public void execute() {
                try {
                    FramedConnection.this.frameWriter.windowUpdate(i, j);
                } catch (IOException unused) {
                }
            }
        });
    }

    public FramedConnection(Builder builder) throws IOException {
        this.streams = new HashMap();
        this.idleStartTimeNs = System.nanoTime();
        this.unacknowledgedBytesRead = 0;
        this.okHttpSettings = new Settings();
        this.peerSettings = new Settings();
        this.receivedInitialPeerSettings = false;
        this.currentPushRequests = new LinkedHashSet();
        Protocol protocol2 = builder.protocol;
        this.protocol = protocol2;
        this.pushObserver = builder.pushObserver;
        boolean z = builder.client;
        this.client = z;
        this.listener = builder.listener;
        int i = 2;
        int i2 = z ? 1 : 2;
        this.nextStreamId = i2;
        if (z) {
            if (protocol2 == Protocol.HTTP_2) {
                this.nextStreamId = i2 + 2;
            }
            i = 1;
        }
        this.nextPingId = i;
        if (z) {
            this.okHttpSettings.set(7, 0, 16777216);
        }
        this.hostName = builder.hostName;
        if (protocol2 == Protocol.HTTP_2) {
            this.variant = new Http2();
            this.pushExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), 
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0089: IPUT  
                  (wrap: java.util.concurrent.ThreadPoolExecutor : 0x0086: CONSTRUCTOR  (r5v1 java.util.concurrent.ThreadPoolExecutor) = 
                  (0 int)
                  (1 int)
                  (60 long)
                  (wrap: java.util.concurrent.TimeUnit : 0x006c: SGET  (r10v0 java.util.concurrent.TimeUnit) =  java.util.concurrent.TimeUnit.SECONDS java.util.concurrent.TimeUnit)
                  (wrap: java.util.concurrent.LinkedBlockingQueue : 0x0070: CONSTRUCTOR  (r11v0 java.util.concurrent.LinkedBlockingQueue) =  call: java.util.concurrent.LinkedBlockingQueue.<init>():void type: CONSTRUCTOR)
                  (wrap: com.squareup.okhttp.internal.Util$1 : 0x0081: CONSTRUCTOR  (r12v0 com.squareup.okhttp.internal.Util$1) = 
                  (wrap: java.lang.String : 0x007b: INVOKE  (r0v25 java.lang.String) = 
                  ("OkHttp %s Push Observer")
                  (wrap: java.lang.Object[] : 0x0075: FILLED_NEW_ARRAY  (r1v7 java.lang.Object[]) = 
                  (wrap: java.lang.String : 0x0073: IGET  (r0v23 java.lang.String) = (r13v0 'this' com.squareup.okhttp.internal.framed.FramedConnection A[IMMUTABLE_TYPE, THIS]) com.squareup.okhttp.internal.framed.FramedConnection.hostName java.lang.String)
                 elemType: java.lang.Object)
                 type: STATIC call: java.lang.String.format(java.lang.String, java.lang.Object[]):java.lang.String)
                  true
                 call: com.squareup.okhttp.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR)
                 call: java.util.concurrent.ThreadPoolExecutor.<init>(int, int, long, java.util.concurrent.TimeUnit, java.util.concurrent.BlockingQueue, java.util.concurrent.ThreadFactory):void type: CONSTRUCTOR)
                  (r13v0 'this' com.squareup.okhttp.internal.framed.FramedConnection A[IMMUTABLE_TYPE, THIS])
                 com.squareup.okhttp.internal.framed.FramedConnection.pushExecutor java.util.concurrent.ExecutorService in method: com.squareup.okhttp.internal.framed.FramedConnection.<init>(com.squareup.okhttp.internal.framed.FramedConnection$Builder):void, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:143)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
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
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0086: CONSTRUCTOR  (r5v1 java.util.concurrent.ThreadPoolExecutor) = 
                  (0 int)
                  (1 int)
                  (60 long)
                  (wrap: java.util.concurrent.TimeUnit : 0x006c: SGET  (r10v0 java.util.concurrent.TimeUnit) =  java.util.concurrent.TimeUnit.SECONDS java.util.concurrent.TimeUnit)
                  (wrap: java.util.concurrent.LinkedBlockingQueue : 0x0070: CONSTRUCTOR  (r11v0 java.util.concurrent.LinkedBlockingQueue) =  call: java.util.concurrent.LinkedBlockingQueue.<init>():void type: CONSTRUCTOR)
                  (wrap: com.squareup.okhttp.internal.Util$1 : 0x0081: CONSTRUCTOR  (r12v0 com.squareup.okhttp.internal.Util$1) = 
                  (wrap: java.lang.String : 0x007b: INVOKE  (r0v25 java.lang.String) = 
                  ("OkHttp %s Push Observer")
                  (wrap: java.lang.Object[] : 0x0075: FILLED_NEW_ARRAY  (r1v7 java.lang.Object[]) = 
                  (wrap: java.lang.String : 0x0073: IGET  (r0v23 java.lang.String) = (r13v0 'this' com.squareup.okhttp.internal.framed.FramedConnection A[IMMUTABLE_TYPE, THIS]) com.squareup.okhttp.internal.framed.FramedConnection.hostName java.lang.String)
                 elemType: java.lang.Object)
                 type: STATIC call: java.lang.String.format(java.lang.String, java.lang.Object[]):java.lang.String)
                  true
                 call: com.squareup.okhttp.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR)
                 call: java.util.concurrent.ThreadPoolExecutor.<init>(int, int, long, java.util.concurrent.TimeUnit, java.util.concurrent.BlockingQueue, java.util.concurrent.ThreadFactory):void type: CONSTRUCTOR in method: com.squareup.okhttp.internal.framed.FramedConnection.<init>(com.squareup.okhttp.internal.framed.FramedConnection$Builder):void, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:428)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                	... 19 more
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0081: CONSTRUCTOR  (r12v0 com.squareup.okhttp.internal.Util$1) = 
                  (wrap: java.lang.String : 0x007b: INVOKE  (r0v25 java.lang.String) = 
                  ("OkHttp %s Push Observer")
                  (wrap: java.lang.Object[] : 0x0075: FILLED_NEW_ARRAY  (r1v7 java.lang.Object[]) = 
                  (wrap: java.lang.String : 0x0073: IGET  (r0v23 java.lang.String) = (r13v0 'this' com.squareup.okhttp.internal.framed.FramedConnection A[IMMUTABLE_TYPE, THIS]) com.squareup.okhttp.internal.framed.FramedConnection.hostName java.lang.String)
                 elemType: java.lang.Object)
                 type: STATIC call: java.lang.String.format(java.lang.String, java.lang.Object[]):java.lang.String)
                  true
                 call: com.squareup.okhttp.internal.Util.1.<init>(java.lang.String, boolean):void type: CONSTRUCTOR in method: com.squareup.okhttp.internal.framed.FramedConnection.<init>(com.squareup.okhttp.internal.framed.FramedConnection$Builder):void, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:663)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                	... 23 more
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.squareup.okhttp.internal.Util, state: GENERATED_AND_UNLOADED
                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                	... 29 more
                */
            /*
            // Method dump skipped, instructions count: 226
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.framed.FramedConnection.<init>(com.squareup.okhttp.internal.framed.FramedConnection$Builder):void");
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void close(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
            IOException e;
            int i;
            FramedStream[] framedStreamArr;
            Ping[] pingArr = null;
            try {
                shutdown(errorCode);
                e = null;
            } catch (IOException e2) {
                e = e2;
            }
            synchronized (this) {
                if (!this.streams.isEmpty()) {
                    framedStreamArr = (FramedStream[]) this.streams.values().toArray(new FramedStream[this.streams.size()]);
                    this.streams.clear();
                    setIdle(false);
                } else {
                    framedStreamArr = null;
                }
                Map<Integer, Ping> map = this.pings;
                if (map != null) {
                    Ping[] pingArr2 = (Ping[]) map.values().toArray(new Ping[this.pings.size()]);
                    this.pings = null;
                    pingArr = pingArr2;
                }
            }
            if (framedStreamArr != null) {
                for (FramedStream framedStream : framedStreamArr) {
                    try {
                        framedStream.close(errorCode2);
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
                this.frameWriter.close();
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

        private FramedStream newStream(int i, List<Header> list, boolean z, boolean z2) throws IOException {
            int i2;
            FramedStream framedStream;
            boolean z3 = !z;
            boolean z4 = !z2;
            synchronized (this.frameWriter) {
                synchronized (this) {
                    if (!this.shutdown) {
                        i2 = this.nextStreamId;
                        this.nextStreamId = i2 + 2;
                        framedStream = new FramedStream(i2, this, z3, z4, list);
                        if (framedStream.isOpen()) {
                            this.streams.put(Integer.valueOf(i2), framedStream);
                            setIdle(false);
                        }
                    } else {
                        throw new IOException("shutdown");
                    }
                }
                if (i == 0) {
                    this.frameWriter.synStream(z3, z4, i2, i, list);
                } else if (!this.client) {
                    this.frameWriter.pushPromise(i, i2, list);
                } else {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
                }
            }
            if (!z) {
                this.frameWriter.flush();
            }
            return framedStream;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
        }

        public FramedStream newStream(List<Header> list, boolean z, boolean z2) throws IOException {
            return newStream(0, list, z, z2);
        }
    }
