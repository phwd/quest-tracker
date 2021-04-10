package okhttp3.internal.ws;

import X.AnonymousClass006;
import com.adobe.xmp.impl.Base64;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

public final class WebSocketReader {
    public boolean closed;
    public long frameBytesRead;
    public final FrameCallback frameCallback;
    public long frameLength;
    public final boolean isClient;
    public boolean isControlFrame;
    public boolean isFinalFrame;
    public boolean isMasked;
    public final byte[] maskBuffer = new byte[8192];
    public final byte[] maskKey = new byte[4];
    public int opcode;
    public final BufferedSource source;

    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(String str) throws IOException;

        void onReadMessage(ByteString byteString) throws IOException;

        void onReadPing(ByteString byteString);

        void onReadPong(ByteString byteString);
    }

    private void readControlFrame() throws IOException {
        String str;
        Buffer buffer = new Buffer();
        long j = this.frameBytesRead;
        long j2 = this.frameLength;
        if (j < j2) {
            if (!this.isClient) {
                while (true) {
                    long j3 = this.frameLength;
                    if (j >= j3) {
                        break;
                    }
                    byte[] bArr = this.maskBuffer;
                    int read = this.source.read(bArr, 0, (int) Math.min(j3 - j, (long) bArr.length));
                    if (read != -1) {
                        byte[] bArr2 = this.maskBuffer;
                        long j4 = (long) read;
                        WebSocketProtocol.toggleMask(bArr2, j4, this.maskKey, this.frameBytesRead);
                        buffer.write(bArr2, 0, read);
                        j = this.frameBytesRead + j4;
                        this.frameBytesRead = j;
                    } else {
                        throw new EOFException();
                    }
                }
            } else {
                this.source.readFully(buffer, j2);
            }
        }
        int i = this.opcode;
        switch (i) {
            case 8:
                short s = 1005;
                long j5 = buffer.size;
                if (j5 != 1) {
                    if (j5 != 0) {
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
                throw new ProtocolException(AnonymousClass006.A07("Unknown control opcode: ", Integer.toHexString(i)));
        }
    }

    /* JADX INFO: finally extract failed */
    private void readHeader() throws IOException {
        String str;
        if (!this.closed) {
            long timeoutNanos = this.source.timeout().timeoutNanos();
            this.source.timeout().clearTimeout();
            try {
                int readByte = this.source.readByte() & Base64.INVALID;
                this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                this.opcode = readByte & 15;
                boolean z = true;
                boolean z2 = false;
                if ((readByte & 128) != 0) {
                    z2 = true;
                }
                this.isFinalFrame = z2;
                boolean z3 = false;
                if ((readByte & 8) != 0) {
                    z3 = true;
                }
                this.isControlFrame = z3;
                if (!z3 || z2) {
                    boolean z4 = false;
                    if ((readByte & 64) != 0) {
                        z4 = true;
                    }
                    boolean z5 = false;
                    if ((readByte & 32) != 0) {
                        z5 = true;
                    }
                    boolean z6 = false;
                    if ((readByte & 16) != 0) {
                        z6 = true;
                    }
                    if (z4 || z5 || z6) {
                        throw new ProtocolException("Reserved flags are unsupported.");
                    }
                    int readByte2 = this.source.readByte() & Base64.INVALID;
                    if ((readByte2 & 128) == 0) {
                        z = false;
                    }
                    this.isMasked = z;
                    boolean z7 = this.isClient;
                    if (z == z7) {
                        if (z7) {
                            str = "Server-sent frames must not be masked.";
                        } else {
                            str = "Client-sent frames must be masked.";
                        }
                        throw new ProtocolException(str);
                    }
                    long j = (long) (readByte2 & 127);
                    this.frameLength = j;
                    if (j == 126) {
                        j = ((long) this.source.readShort()) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
                        this.frameLength = j;
                    } else if (j == 127) {
                        j = this.source.readLong();
                        this.frameLength = j;
                        if (j < 0) {
                            throw new ProtocolException(AnonymousClass006.A09("Frame length 0x", Long.toHexString(j), " > 0x7FFFFFFFFFFFFFFF"));
                        }
                    }
                    this.frameBytesRead = 0;
                    if (this.isControlFrame && j > 125) {
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

    private void readMessage(Buffer buffer) throws IOException {
        long read;
        while (!this.closed) {
            if (this.frameBytesRead == this.frameLength) {
                if (!this.isFinalFrame) {
                    readUntilNonControlFrame();
                    int i = this.opcode;
                    if (i != 0) {
                        throw new ProtocolException(AnonymousClass006.A07("Expected continuation opcode. Got: ", Integer.toHexString(i)));
                    } else if (this.isFinalFrame && this.frameLength == 0) {
                        return;
                    }
                } else {
                    return;
                }
            }
            long j = this.frameLength - this.frameBytesRead;
            if (this.isMasked) {
                byte[] bArr = this.maskBuffer;
                read = (long) this.source.read(bArr, 0, (int) Math.min(j, (long) bArr.length));
                if (read != -1) {
                    byte[] bArr2 = this.maskBuffer;
                    WebSocketProtocol.toggleMask(bArr2, read, this.maskKey, this.frameBytesRead);
                    buffer.write(bArr2, 0, (int) read);
                } else {
                    throw new EOFException();
                }
            } else {
                read = this.source.read(buffer, j);
                if (read == -1) {
                    throw new EOFException();
                }
            }
            this.frameBytesRead += read;
        }
        throw new IOException("closed");
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
            throw new ProtocolException(AnonymousClass006.A07("Unknown opcode: ", Integer.toHexString(i)));
        }
    }

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

    public WebSocketReader(boolean z, BufferedSource bufferedSource, FrameCallback frameCallback2) {
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

    public void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }
}
