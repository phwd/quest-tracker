package okhttp3.internal.ws;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* access modifiers changed from: package-private */
public final class WebSocketReader {
    boolean closed;
    long frameBytesRead;
    final FrameCallback frameCallback;
    long frameLength;
    final boolean isClient;
    boolean isControlFrame;
    boolean isFinalFrame;
    boolean isMasked;
    final byte[] maskBuffer = new byte[8192];
    final byte[] maskKey = new byte[4];
    int opcode;
    final BufferedSource source;

    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(String str) throws IOException;

        void onReadMessage(ByteString byteString) throws IOException;

        void onReadPing(ByteString byteString);

        void onReadPong(ByteString byteString);
    }

    WebSocketReader(boolean z, BufferedSource bufferedSource, FrameCallback frameCallback2) {
        if (bufferedSource == null) {
            throw new NullPointerException("source == null");
        } else if (frameCallback2 != null) {
            this.isClient = z;
            this.source = bufferedSource;
            this.frameCallback = frameCallback2;
        } else {
            throw new NullPointerException("frameCallback == null");
        }
    }

    /* access modifiers changed from: package-private */
    public void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }

    /* JADX INFO: finally extract failed */
    private void readHeader() throws IOException {
        if (!this.closed) {
            long timeoutNanos = this.source.timeout().timeoutNanos();
            this.source.timeout().clearTimeout();
            try {
                int readByte = this.source.readByte() & 255;
                this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                this.opcode = readByte & 15;
                boolean z = true;
                this.isFinalFrame = (readByte & 128) != 0;
                this.isControlFrame = (readByte & 8) != 0;
                if (!this.isControlFrame || this.isFinalFrame) {
                    boolean z2 = (readByte & 64) != 0;
                    boolean z3 = (readByte & 32) != 0;
                    boolean z4 = (readByte & 16) != 0;
                    if (z2 || z3 || z4) {
                        throw new ProtocolException("Reserved flags are unsupported.");
                    }
                    int readByte2 = this.source.readByte() & 255;
                    if ((readByte2 & 128) == 0) {
                        z = false;
                    }
                    this.isMasked = z;
                    boolean z5 = this.isMasked;
                    boolean z6 = this.isClient;
                    if (z5 == z6) {
                        throw new ProtocolException(z6 ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
                    }
                    this.frameLength = (long) (readByte2 & 127);
                    long j = this.frameLength;
                    if (j == 126) {
                        this.frameLength = ((long) this.source.readShort()) & 65535;
                    } else if (j == 127) {
                        this.frameLength = this.source.readLong();
                        if (this.frameLength < 0) {
                            throw new ProtocolException("Frame length 0x" + Long.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
                        }
                    }
                    this.frameBytesRead = 0;
                    if (this.isControlFrame && this.frameLength > 125) {
                        throw new ProtocolException("Control frame must be less than 125B.");
                    } else if (this.isMasked) {
                        this.source.readFully(this.maskKey);
                    }
                } else {
                    throw new ProtocolException("Control frames must be final.");
                }
            } catch (Throwable th) {
                this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                throw th;
            }
        } else {
            throw new IOException("closed");
        }
    }

    private void readControlFrame() throws IOException {
        String str;
        Buffer buffer = new Buffer();
        long j = this.frameBytesRead;
        long j2 = this.frameLength;
        if (j < j2) {
            if (!this.isClient) {
                while (true) {
                    long j3 = this.frameBytesRead;
                    long j4 = this.frameLength;
                    if (j3 >= j4) {
                        break;
                    }
                    int read = this.source.read(this.maskBuffer, 0, (int) Math.min(j4 - j3, (long) this.maskBuffer.length));
                    if (read != -1) {
                        long j5 = (long) read;
                        WebSocketProtocol.toggleMask(this.maskBuffer, j5, this.maskKey, this.frameBytesRead);
                        buffer.write(this.maskBuffer, 0, read);
                        this.frameBytesRead += j5;
                    } else {
                        throw new EOFException();
                    }
                }
            } else {
                this.source.readFully(buffer, j2);
            }
        }
        switch (this.opcode) {
            case 8:
                short s = 1005;
                long size = buffer.size();
                if (size != 1) {
                    if (size != 0) {
                        s = buffer.readShort();
                        str = buffer.readUtf8();
                        String closeCodeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(s);
                        if (closeCodeExceptionMessage != null) {
                            throw new ProtocolException(closeCodeExceptionMessage);
                        }
                    } else {
                        str = "";
                    }
                    this.frameCallback.onReadClose(s, str);
                    this.closed = true;
                    return;
                }
                throw new ProtocolException("Malformed close payload length of 1.");
            case 9:
                this.frameCallback.onReadPing(buffer.readByteString());
                return;
            case 10:
                this.frameCallback.onReadPong(buffer.readByteString());
                return;
            default:
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.opcode));
        }
    }

    private void readMessageFrame() throws IOException {
        int i = this.opcode;
        if (i == 1 || i == 2) {
            Buffer buffer = new Buffer();
            readMessage(buffer);
            if (i == 1) {
                this.frameCallback.onReadMessage(buffer.readUtf8());
            } else {
                this.frameCallback.onReadMessage(buffer.readByteString());
            }
        } else {
            throw new ProtocolException("Unknown opcode: " + Integer.toHexString(i));
        }
    }

    /* access modifiers changed from: package-private */
    public void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (this.isControlFrame) {
                readControlFrame();
            } else {
                return;
            }
        }
    }

    private void readMessage(Buffer buffer) throws IOException {
        long j;
        while (!this.closed) {
            if (this.frameBytesRead == this.frameLength) {
                if (!this.isFinalFrame) {
                    readUntilNonControlFrame();
                    if (this.opcode != 0) {
                        throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(this.opcode));
                    } else if (this.isFinalFrame && this.frameLength == 0) {
                        return;
                    }
                } else {
                    return;
                }
            }
            long j2 = this.frameLength - this.frameBytesRead;
            if (this.isMasked) {
                j = (long) this.source.read(this.maskBuffer, 0, (int) Math.min(j2, (long) this.maskBuffer.length));
                if (j != -1) {
                    WebSocketProtocol.toggleMask(this.maskBuffer, j, this.maskKey, this.frameBytesRead);
                    buffer.write(this.maskBuffer, 0, (int) j);
                } else {
                    throw new EOFException();
                }
            } else {
                j = this.source.read(buffer, j2);
                if (j == -1) {
                    throw new EOFException();
                }
            }
            this.frameBytesRead += j;
        }
        throw new IOException("closed");
    }
}
