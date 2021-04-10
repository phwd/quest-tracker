package okhttp3.internal.ws;

import com.adobe.xmp.options.PropertyOptions;
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

    WebSocketReader(boolean isClient2, BufferedSource source2, FrameCallback frameCallback2) {
        if (source2 == null) {
            throw new NullPointerException("source == null");
        } else if (frameCallback2 == null) {
            throw new NullPointerException("frameCallback == null");
        } else {
            this.isClient = isClient2;
            this.source = source2;
            this.frameCallback = frameCallback2;
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
        boolean z;
        boolean z2;
        boolean reservedFlag1;
        boolean reservedFlag2;
        boolean reservedFlag3;
        String str;
        boolean z3 = true;
        if (this.closed) {
            throw new IOException("closed");
        }
        long timeoutBefore = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            int b0 = this.source.readByte() & 255;
            this.source.timeout().timeout(timeoutBefore, TimeUnit.NANOSECONDS);
            this.opcode = b0 & 15;
            if ((b0 & PropertyOptions.HAS_TYPE) != 0) {
                z = true;
            } else {
                z = false;
            }
            this.isFinalFrame = z;
            if ((b0 & 8) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.isControlFrame = z2;
            if (!this.isControlFrame || this.isFinalFrame) {
                if ((b0 & 64) != 0) {
                    reservedFlag1 = true;
                } else {
                    reservedFlag1 = false;
                }
                if ((b0 & 32) != 0) {
                    reservedFlag2 = true;
                } else {
                    reservedFlag2 = false;
                }
                if ((b0 & 16) != 0) {
                    reservedFlag3 = true;
                } else {
                    reservedFlag3 = false;
                }
                if (reservedFlag1 || reservedFlag2 || reservedFlag3) {
                    throw new ProtocolException("Reserved flags are unsupported.");
                }
                int b1 = this.source.readByte() & 255;
                if ((b1 & PropertyOptions.HAS_TYPE) == 0) {
                    z3 = false;
                }
                this.isMasked = z3;
                if (this.isMasked == this.isClient) {
                    if (this.isClient) {
                        str = "Server-sent frames must not be masked.";
                    } else {
                        str = "Client-sent frames must be masked.";
                    }
                    throw new ProtocolException(str);
                }
                this.frameLength = (long) (b1 & 127);
                if (this.frameLength == 126) {
                    this.frameLength = ((long) this.source.readShort()) & 65535;
                } else if (this.frameLength == 127) {
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
            this.source.timeout().timeout(timeoutBefore, TimeUnit.NANOSECONDS);
            throw th;
        }
    }

    private void readControlFrame() throws IOException {
        Buffer buffer = new Buffer();
        if (this.frameBytesRead < this.frameLength) {
            if (this.isClient) {
                this.source.readFully(buffer, this.frameLength);
            } else {
                while (this.frameBytesRead < this.frameLength) {
                    int read = this.source.read(this.maskBuffer, 0, (int) Math.min(this.frameLength - this.frameBytesRead, (long) this.maskBuffer.length));
                    if (read == -1) {
                        throw new EOFException();
                    }
                    WebSocketProtocol.toggleMask(this.maskBuffer, (long) read, this.maskKey, this.frameBytesRead);
                    buffer.write(this.maskBuffer, 0, read);
                    this.frameBytesRead += (long) read;
                }
            }
        }
        switch (this.opcode) {
            case 8:
                int code = 1005;
                String reason = "";
                long bufferSize = buffer.size();
                if (bufferSize == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (bufferSize != 0) {
                    code = buffer.readShort();
                    reason = buffer.readUtf8();
                    String codeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(code);
                    if (codeExceptionMessage != null) {
                        throw new ProtocolException(codeExceptionMessage);
                    }
                }
                this.frameCallback.onReadClose(code, reason);
                this.closed = true;
                return;
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
        int opcode2 = this.opcode;
        if (opcode2 == 1 || opcode2 == 2) {
            Buffer message = new Buffer();
            readMessage(message);
            if (opcode2 == 1) {
                this.frameCallback.onReadMessage(message.readUtf8());
            } else {
                this.frameCallback.onReadMessage(message.readByteString());
            }
        } else {
            throw new ProtocolException("Unknown opcode: " + Integer.toHexString(opcode2));
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

    private void readMessage(Buffer sink) throws IOException {
        long read;
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
            long toRead = this.frameLength - this.frameBytesRead;
            if (this.isMasked) {
                read = (long) this.source.read(this.maskBuffer, 0, (int) Math.min(toRead, (long) this.maskBuffer.length));
                if (read == -1) {
                    throw new EOFException();
                }
                WebSocketProtocol.toggleMask(this.maskBuffer, read, this.maskKey, this.frameBytesRead);
                sink.write(this.maskBuffer, 0, (int) read);
            } else {
                read = this.source.read(sink, toRead);
                if (read == -1) {
                    throw new EOFException();
                }
            }
            this.frameBytesRead += read;
        }
        throw new IOException("closed");
    }
}
