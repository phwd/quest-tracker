package okhttp3.internal.ws;

import com.facebook.androidinternals.android.os.TraceInternal;
import com.facebook.systrace.Systrace;
import java.io.IOException;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

/* access modifiers changed from: package-private */
public final class WebSocketWriter {
    boolean activeWriter;
    final Buffer buffer = new Buffer();
    final FrameSink frameSink = new FrameSink();
    final boolean isClient;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    final Random random;
    final BufferedSink sink;
    final Buffer sinkBuffer;
    boolean writerClosed;

    WebSocketWriter(boolean isClient2, BufferedSink sink2, Random random2) {
        byte[] bArr;
        Buffer.UnsafeCursor unsafeCursor = null;
        if (sink2 == null) {
            throw new NullPointerException("sink == null");
        } else if (random2 == null) {
            throw new NullPointerException("random == null");
        } else {
            this.isClient = isClient2;
            this.sink = sink2;
            this.sinkBuffer = sink2.buffer();
            this.random = random2;
            if (isClient2) {
                bArr = new byte[4];
            } else {
                bArr = null;
            }
            this.maskKey = bArr;
            this.maskCursor = isClient2 ? new Buffer.UnsafeCursor() : unsafeCursor;
        }
    }

    /* access modifiers changed from: package-private */
    public void writePing(ByteString payload) throws IOException {
        writeControlFrame(9, payload);
    }

    /* access modifiers changed from: package-private */
    public void writePong(ByteString payload) throws IOException {
        writeControlFrame(10, payload);
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
        try {
            writeControlFrame(8, payload);
        } finally {
            this.writerClosed = true;
        }
    }

    private void writeControlFrame(int opcode, ByteString payload) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int length = payload.size();
        if (((long) length) > 125) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        this.sinkBuffer.writeByte(opcode | 128);
        if (this.isClient) {
            this.sinkBuffer.writeByte(length | 128);
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if (length > 0) {
                long payloadStart = this.sinkBuffer.size();
                this.sinkBuffer.write(payload);
                this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(payloadStart);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.writeByte(length);
            this.sinkBuffer.write(payload);
        }
        this.sink.flush();
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
    public void writeMessageFrame(int formatOpcode, long byteCount, boolean isFirstFrame, boolean isFinal) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int b0 = isFirstFrame ? formatOpcode : 0;
        if (isFinal) {
            b0 |= 128;
        }
        this.sinkBuffer.writeByte(b0);
        int b1 = 0;
        if (this.isClient) {
            b1 = 0 | 128;
        }
        if (byteCount <= 125) {
            this.sinkBuffer.writeByte(b1 | ((int) byteCount));
        } else if (byteCount <= 65535) {
            this.sinkBuffer.writeByte(b1 | 126);
            this.sinkBuffer.writeShort((int) byteCount);
        } else {
            this.sinkBuffer.writeByte(b1 | TraceInternal.MAX_SECTION_NAME_LEN);
            this.sinkBuffer.writeLong(byteCount);
        }
        if (this.isClient) {
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if (byteCount > 0) {
                long bufferStart = this.sinkBuffer.size();
                this.sinkBuffer.write(this.buffer, byteCount);
                this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(bufferStart);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.write(this.buffer, byteCount);
        }
        this.sink.emit();
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
            if (!this.isFirstFrame || this.contentLength == -1 || WebSocketWriter.this.buffer.size() <= this.contentLength - Systrace.TRACE_TAG_REACT_JAVA_BRIDGE) {
                deferWrite = false;
            } else {
                deferWrite = true;
            }
            long emitCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
            if (emitCount > 0 && !deferWrite) {
                WebSocketWriter.this.writeMessageFrame(this.formatOpcode, emitCount, this.isFirstFrame, false);
                this.isFirstFrame = false;
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.writeMessageFrame(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, false);
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
            WebSocketWriter.this.writeMessageFrame(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, true);
            this.closed = true;
            WebSocketWriter.this.activeWriter = false;
        }
    }
}
