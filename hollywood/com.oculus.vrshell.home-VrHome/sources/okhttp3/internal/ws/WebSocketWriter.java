package okhttp3.internal.ws;

import com.adobe.xmp.options.PropertyOptions;
import java.io.IOException;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

/* access modifiers changed from: package-private */
public final class WebSocketWriter {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketWriter.class.desiredAssertionStatus());
    boolean activeWriter;
    final Buffer buffer = new Buffer();
    final FrameSink frameSink = new FrameSink();
    final boolean isClient;
    final byte[] maskBuffer;
    final byte[] maskKey;
    final Random random;
    final BufferedSink sink;
    boolean writerClosed;

    WebSocketWriter(boolean isClient2, BufferedSink sink2, Random random2) {
        byte[] bArr;
        byte[] bArr2 = null;
        if (sink2 == null) {
            throw new NullPointerException("sink == null");
        } else if (random2 == null) {
            throw new NullPointerException("random == null");
        } else {
            this.isClient = isClient2;
            this.sink = sink2;
            this.random = random2;
            if (isClient2) {
                bArr = new byte[4];
            } else {
                bArr = null;
            }
            this.maskKey = bArr;
            this.maskBuffer = isClient2 ? new byte[8192] : bArr2;
        }
    }

    /* access modifiers changed from: package-private */
    public void writePing(ByteString payload) throws IOException {
        synchronized (this) {
            writeControlFrameSynchronized(9, payload);
        }
    }

    /* access modifiers changed from: package-private */
    public void writePong(ByteString payload) throws IOException {
        synchronized (this) {
            writeControlFrameSynchronized(10, payload);
        }
    }

    /* access modifiers changed from: package-private */
    public void writeClose(int code, ByteString reason) throws IOException {
        ByteString payload = ByteString.EMPTY;
        if (!(code == 0 && reason == null)) {
            if (code != 0) {
                WebSocketProtocol.validateCloseCode(code);
            }
            Buffer buffer2 = new Buffer();
            buffer2.writeShort(code);
            if (reason != null) {
                buffer2.write(reason);
            }
            payload = buffer2.readByteString();
        }
        synchronized (this) {
            try {
                writeControlFrameSynchronized(8, payload);
                this.writerClosed = true;
            } catch (Throwable th) {
                this.writerClosed = true;
                throw th;
            }
        }
    }

    private void writeControlFrameSynchronized(int opcode, ByteString payload) throws IOException {
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (this.writerClosed) {
            throw new IOException("closed");
        } else {
            int length = payload.size();
            if (((long) length) > 125) {
                throw new IllegalArgumentException("Payload size must be less than or equal to 125");
            }
            this.sink.writeByte(opcode | PropertyOptions.HAS_TYPE);
            if (this.isClient) {
                this.sink.writeByte(length | PropertyOptions.HAS_TYPE);
                this.random.nextBytes(this.maskKey);
                this.sink.write(this.maskKey);
                byte[] bytes = payload.toByteArray();
                WebSocketProtocol.toggleMask(bytes, (long) bytes.length, this.maskKey, 0);
                this.sink.write(bytes);
            } else {
                this.sink.writeByte(length);
                this.sink.write(payload);
            }
            this.sink.flush();
        }
    }

    /* access modifiers changed from: package-private */
    public Sink newMessageSink(int formatOpcode, long contentLength) {
        if (this.activeWriter) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.activeWriter = true;
        this.frameSink.formatOpcode = formatOpcode;
        this.frameSink.contentLength = contentLength;
        this.frameSink.isFirstFrame = true;
        this.frameSink.closed = false;
        return this.frameSink;
    }

    /* access modifiers changed from: package-private */
    public void writeMessageFrameSynchronized(int formatOpcode, long byteCount, boolean isFirstFrame, boolean isFinal) throws IOException {
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (this.writerClosed) {
            throw new IOException("closed");
        } else {
            int b0 = isFirstFrame ? formatOpcode : 0;
            if (isFinal) {
                b0 |= PropertyOptions.HAS_TYPE;
            }
            this.sink.writeByte(b0);
            int b1 = 0;
            if (this.isClient) {
                b1 = 0 | PropertyOptions.HAS_TYPE;
            }
            if (byteCount <= 125) {
                this.sink.writeByte(b1 | ((int) byteCount));
            } else if (byteCount <= 65535) {
                this.sink.writeByte(b1 | 126);
                this.sink.writeShort((int) byteCount);
            } else {
                this.sink.writeByte(b1 | 127);
                this.sink.writeLong(byteCount);
            }
            if (this.isClient) {
                this.random.nextBytes(this.maskKey);
                this.sink.write(this.maskKey);
                long written = 0;
                while (written < byteCount) {
                    int read = this.buffer.read(this.maskBuffer, 0, (int) Math.min(byteCount, (long) this.maskBuffer.length));
                    if (read == -1) {
                        throw new AssertionError();
                    }
                    WebSocketProtocol.toggleMask(this.maskBuffer, (long) read, this.maskKey, written);
                    this.sink.write(this.maskBuffer, 0, read);
                    written += (long) read;
                }
            } else {
                this.sink.write(this.buffer, byteCount);
            }
            this.sink.emit();
        }
    }

    /* access modifiers changed from: package-private */
    public final class FrameSink implements Sink {
        boolean closed;
        long contentLength;
        int formatOpcode;
        boolean isFirstFrame;

        FrameSink() {
        }

        @Override // okio.Sink
        public void write(Buffer source, long byteCount) throws IOException {
            boolean deferWrite;
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.buffer.write(source, byteCount);
            if (!this.isFirstFrame || this.contentLength == -1 || WebSocketWriter.this.buffer.size() <= this.contentLength - 8192) {
                deferWrite = false;
            } else {
                deferWrite = true;
            }
            long emitCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
            if (emitCount > 0 && !deferWrite) {
                synchronized (WebSocketWriter.this) {
                    WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, emitCount, this.isFirstFrame, false);
                }
                this.isFirstFrame = false;
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, false);
            }
            this.isFirstFrame = false;
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
        public void close() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, true);
            }
            this.closed = true;
            WebSocketWriter.this.activeWriter = false;
        }
    }
}
