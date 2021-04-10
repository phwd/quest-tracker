package okhttp3.internal.ws;

import java.io.IOException;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

public final class WebSocketWriter {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public boolean activeWriter;
    public final Buffer buffer = new Buffer();
    public final FrameSink frameSink = new FrameSink();
    public final boolean isClient;
    public final byte[] maskBuffer;
    public final byte[] maskKey;
    public final Random random;
    public final BufferedSink sink;
    public boolean writerClosed;

    public final class FrameSink implements Sink {
        public boolean closed;
        public long contentLength;
        public int formatOpcode;
        public boolean isFirstFrame;

        public FrameSink() {
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
        public void close() throws IOException {
            if (!this.closed) {
                synchronized (WebSocketWriter.this) {
                    WebSocketWriter webSocketWriter = WebSocketWriter.this;
                    webSocketWriter.writeMessageFrameSynchronized(this.formatOpcode, webSocketWriter.buffer.size, this.isFirstFrame, true);
                }
                this.closed = true;
                WebSocketWriter.this.activeWriter = false;
                return;
            }
            throw new IOException("closed");
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (!this.closed) {
                synchronized (WebSocketWriter.this) {
                    WebSocketWriter webSocketWriter = WebSocketWriter.this;
                    webSocketWriter.writeMessageFrameSynchronized(this.formatOpcode, webSocketWriter.buffer.size, this.isFirstFrame, false);
                }
                this.isFirstFrame = false;
                return;
            }
            throw new IOException("closed");
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
            if (r0 <= 0) goto L_0x0026;
         */
        @Override // okio.Sink
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void write(okio.Buffer r11, long r12) throws java.io.IOException {
            /*
                r10 = this;
                boolean r0 = r10.closed
                if (r0 != 0) goto L_0x0049
                okhttp3.internal.ws.WebSocketWriter r0 = okhttp3.internal.ws.WebSocketWriter.this
                okio.Buffer r0 = r0.buffer
                r0.write(r11, r12)
                boolean r0 = r10.isFirstFrame
                r9 = 0
                if (r0 == 0) goto L_0x0026
                long r4 = r10.contentLength
                r1 = -1
                int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
                if (r0 == 0) goto L_0x0026
                okhttp3.internal.ws.WebSocketWriter r0 = okhttp3.internal.ws.WebSocketWriter.this
                okio.Buffer r0 = r0.buffer
                long r2 = r0.size
                r0 = 8192(0x2000, double:4.0474E-320)
                long r4 = r4 - r0
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                r4 = 1
                if (r0 > 0) goto L_0x0027
            L_0x0026:
                r4 = 0
            L_0x0027:
                okhttp3.internal.ws.WebSocketWriter r3 = okhttp3.internal.ws.WebSocketWriter.this
                okio.Buffer r0 = r3.buffer
                long r6 = r0.completeSegmentByteCount()
                r1 = 0
                int r0 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                if (r0 <= 0) goto L_0x0048
                if (r4 != 0) goto L_0x0048
                monitor-enter(r3)
                okhttp3.internal.ws.WebSocketWriter r4 = okhttp3.internal.ws.WebSocketWriter.this     // Catch:{ all -> 0x0043 }
                int r5 = r10.formatOpcode     // Catch:{ all -> 0x0043 }
                boolean r8 = r10.isFirstFrame     // Catch:{ all -> 0x0043 }
                r4.writeMessageFrameSynchronized(r5, r6, r8, r9)     // Catch:{ all -> 0x0043 }
                monitor-exit(r3)     // Catch:{ all -> 0x0043 }
                goto L_0x0046
            L_0x0043:
                r0 = move-exception
                monitor-exit(r3)
                throw r0
            L_0x0046:
                r10.isFirstFrame = r9
            L_0x0048:
                return
            L_0x0049:
                java.lang.String r1 = "closed"
                java.io.IOException r0 = new java.io.IOException
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.WebSocketWriter.FrameSink.write(okio.Buffer, long):void");
        }
    }

    public void writePing(ByteString byteString) throws IOException {
        synchronized (this) {
            writeControlFrameSynchronized(9, byteString);
        }
    }

    public void writePong(ByteString byteString) throws IOException {
        synchronized (this) {
            writeControlFrameSynchronized(10, byteString);
        }
    }

    private void writeControlFrameSynchronized(int i, ByteString byteString) throws IOException {
        if (!this.writerClosed) {
            int size = byteString.size();
            if (((long) size) <= 125) {
                this.sink.writeByte(i | 128);
                if (this.isClient) {
                    this.sink.writeByte(size | 128);
                    this.random.nextBytes(this.maskKey);
                    this.sink.write(this.maskKey);
                    byte[] byteArray = byteString.toByteArray();
                    WebSocketProtocol.toggleMask(byteArray, (long) byteArray.length, this.maskKey, 0);
                    this.sink.write(byteArray);
                } else {
                    this.sink.writeByte(size);
                    this.sink.write(byteString);
                }
                this.sink.flush();
                return;
            }
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        throw new IOException("closed");
    }

    public Sink newMessageSink(int i, long j) {
        if (!this.activeWriter) {
            this.activeWriter = true;
            FrameSink frameSink2 = this.frameSink;
            frameSink2.formatOpcode = i;
            frameSink2.contentLength = j;
            frameSink2.isFirstFrame = true;
            frameSink2.closed = false;
            return frameSink2;
        }
        throw new IllegalStateException("Another message writer is active. Did you call close()?");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        if (r5 != null) goto L_0x0006;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeClose(int r4, okio.ByteString r5) throws java.io.IOException {
        /*
            r3 = this;
            okio.ByteString r2 = okio.ByteString.EMPTY
            if (r4 != 0) goto L_0x001c
            if (r5 == 0) goto L_0x0017
        L_0x0006:
            okio.Buffer r0 = new okio.Buffer
            r0.<init>()
            r0.writeShort(r4)
            if (r5 == 0) goto L_0x0013
            r5.write(r0)
        L_0x0013:
            okio.ByteString r2 = r0.readByteString()
        L_0x0017:
            monitor-enter(r3)
            r0 = 8
            r1 = 1
            goto L_0x0020
        L_0x001c:
            okhttp3.internal.ws.WebSocketProtocol.validateCloseCode(r4)
            goto L_0x0006
        L_0x0020:
            r3.writeControlFrameSynchronized(r0, r2)     // Catch:{ all -> 0x0027 }
            r3.writerClosed = r1     // Catch:{ all -> 0x002b }
            monitor-exit(r3)     // Catch:{ all -> 0x002b }
            return
        L_0x0027:
            r0 = move-exception
            r3.writerClosed = r1     // Catch:{ all -> 0x002b }
            throw r0     // Catch:{ all -> 0x002b }
        L_0x002b:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.WebSocketWriter.writeClose(int, okio.ByteString):void");
    }

    public void writeMessageFrameSynchronized(int i, long j, boolean z, boolean z2) throws IOException {
        if (!this.writerClosed) {
            if (!z) {
                i = 0;
            }
            if (z2) {
                i |= 128;
            }
            this.sink.writeByte(i);
            int i2 = 0;
            if (this.isClient) {
                i2 = 128;
            }
            if (j <= 125) {
                this.sink.writeByte(i2 | ((int) j));
            } else if (j <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                this.sink.writeByte(i2 | 126);
                this.sink.writeShort((int) j);
            } else {
                this.sink.writeByte(i2 | 127);
                this.sink.writeLong(j);
            }
            if (this.isClient) {
                this.random.nextBytes(this.maskKey);
                this.sink.write(this.maskKey);
                long j2 = 0;
                while (j2 < j) {
                    byte[] bArr = this.maskBuffer;
                    int read = this.buffer.read(bArr, 0, (int) Math.min(j, (long) bArr.length));
                    if (read != -1) {
                        byte[] bArr2 = this.maskBuffer;
                        long j3 = (long) read;
                        WebSocketProtocol.toggleMask(bArr2, j3, this.maskKey, j2);
                        this.sink.write(bArr2, 0, read);
                        j2 += j3;
                    } else {
                        throw new AssertionError();
                    }
                }
            } else {
                this.sink.write(this.buffer, j);
            }
            this.sink.emit();
            return;
        }
        throw new IOException("closed");
    }

    public WebSocketWriter(boolean z, BufferedSink bufferedSink, Random random2) {
        if (bufferedSink == null) {
            throw new NullPointerException("sink == null");
        } else if (random2 != null) {
            this.isClient = z;
            this.sink = bufferedSink;
            this.random = random2;
            byte[] bArr = null;
            this.maskKey = z ? new byte[4] : null;
            this.maskBuffer = z ? new byte[8192] : bArr;
        } else {
            throw new NullPointerException("random == null");
        }
    }
}
