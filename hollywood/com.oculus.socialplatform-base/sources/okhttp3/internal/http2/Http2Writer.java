package okhttp3.internal.http2;

import com.adobe.xmp.impl.Base64;
import com.oculus.localmedia.MediaProviderUtils;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.http2.Hpack;
import okio.Buffer;
import okio.BufferedSink;

public final class Http2Writer implements Closeable {
    public static final Logger logger = Logger.getLogger(Http2.class.getName());
    public final boolean client;
    public boolean closed;
    public final Buffer hpackBuffer;
    public final Hpack.Writer hpackWriter;
    public int maxFrameSize = 16384;
    public final BufferedSink sink;

    public synchronized void applyAndAckSettings(Settings settings) throws IOException {
        if (!this.closed) {
            this.maxFrameSize = settings.getMaxFrameSize(this.maxFrameSize);
            int headerTableSize = settings.getHeaderTableSize();
            if (headerTableSize != -1) {
                this.hpackWriter.setHeaderTableSizeSetting(headerTableSize);
            }
            frameHeader(0, 0, (byte) 4, (byte) 1);
            this.sink.flush();
        } else {
            throw new IOException("closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.closed = true;
        this.sink.close();
    }

    public synchronized void connectionPreface() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (this.client) {
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(String.format(Locale.US, ">> CONNECTION %s", Http2.CONNECTION_PREFACE.hex()));
            }
            this.sink.write(Http2.CONNECTION_PREFACE.toByteArray());
            this.sink.flush();
        }
    }

    public synchronized void data(boolean z, int i, Buffer buffer, int i2) throws IOException {
        if (!this.closed) {
            byte b = 0;
            if (z) {
                b = (byte) 1;
            }
            dataFrame(i, b, buffer, i2);
        } else {
            throw new IOException("closed");
        }
    }

    public void dataFrame(int i, byte b, Buffer buffer, int i2) throws IOException {
        frameHeader(i, i2, (byte) 0, b);
        if (i2 > 0) {
            this.sink.write(buffer, (long) i2);
        }
    }

    public synchronized void flush() throws IOException {
        if (!this.closed) {
            this.sink.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void goAway(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (errorCode.httpCode != -1) {
            int length = bArr.length;
            frameHeader(0, length + 8, (byte) 7, (byte) 0);
            this.sink.writeInt(i);
            this.sink.writeInt(errorCode.httpCode);
            if (length > 0) {
                this.sink.write(bArr);
            }
            this.sink.flush();
        } else {
            Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public synchronized void ping(boolean z, int i, int i2) throws IOException {
        if (!this.closed) {
            byte b = 0;
            if (z) {
                b = 1;
            }
            frameHeader(0, 8, (byte) 6, b);
            this.sink.writeInt(i);
            this.sink.writeInt(i2);
            this.sink.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void pushPromise(int i, int i2, List<Header> list) throws IOException {
        if (!this.closed) {
            this.hpackWriter.writeHeaders(list);
            long j = this.hpackBuffer.size;
            int min = (int) Math.min((long) (this.maxFrameSize - 4), j);
            long j2 = (long) min;
            byte b = 0;
            if (j == j2) {
                b = 4;
            }
            frameHeader(i, min + 4, (byte) 5, b);
            this.sink.writeInt(i2 & Integer.MAX_VALUE);
            this.sink.write(this.hpackBuffer, j2);
            if (j > j2) {
                writeContinuationFrames(i, j - j2);
            }
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void rstStream(int i, ErrorCode errorCode) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (errorCode.httpCode != -1) {
            frameHeader(i, 4, (byte) 3, (byte) 0);
            this.sink.writeInt(errorCode.httpCode);
            this.sink.flush();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public synchronized void settings(Settings settings) throws IOException {
        if (!this.closed) {
            int i = 0;
            frameHeader(0, Integer.bitCount(settings.set) * 6, (byte) 4, (byte) 0);
            do {
                if (settings.isSet(i)) {
                    int i2 = 3;
                    if (i != 4) {
                        i2 = i;
                        if (i == 7) {
                            i2 = 4;
                        }
                    }
                    this.sink.writeShort(i2);
                    this.sink.writeInt(settings.values[i]);
                }
                i++;
            } while (i < 10);
            this.sink.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void synReply(boolean z, int i, List<Header> list) throws IOException {
        if (!this.closed) {
            headers(z, i, list);
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void synStream(boolean z, int i, int i2, List<Header> list) throws IOException {
        if (!this.closed) {
            headers(z, i, list);
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void windowUpdate(int i, long j) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (j == 0 || j > 2147483647L) {
            Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else {
            frameHeader(i, 4, (byte) 8, (byte) 0);
            this.sink.writeInt((int) j);
            this.sink.flush();
        }
    }

    private void writeContinuationFrames(int i, long j) throws IOException {
        while (j > 0) {
            int min = (int) Math.min((long) this.maxFrameSize, j);
            long j2 = (long) min;
            j -= j2;
            byte b = 0;
            if (j == 0) {
                b = 4;
            }
            frameHeader(i, min, (byte) 9, b);
            this.sink.write(this.hpackBuffer, j2);
        }
    }

    public static void writeMedium(BufferedSink bufferedSink, int i) throws IOException {
        bufferedSink.writeByte((i >>> 16) & MediaProviderUtils.JPEG_HEADER);
        bufferedSink.writeByte((i >>> 8) & MediaProviderUtils.JPEG_HEADER);
        bufferedSink.writeByte(i & MediaProviderUtils.JPEG_HEADER);
    }

    public void frameHeader(int i, int i2, byte b, byte b2) throws IOException {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Http2.frameLog(false, i, i2, b, b2));
        }
        int i3 = this.maxFrameSize;
        if (i2 > i3) {
            Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i3), Integer.valueOf(i2));
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if ((Integer.MIN_VALUE & i) == 0) {
            writeMedium(this.sink, i2);
            this.sink.writeByte(b & Base64.INVALID);
            this.sink.writeByte(b2 & Base64.INVALID);
            this.sink.writeInt(i & Integer.MAX_VALUE);
        } else {
            Http2.illegalArgument("reserved bit set: %s", Integer.valueOf(i));
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public Http2Writer(BufferedSink bufferedSink, boolean z) {
        this.sink = bufferedSink;
        this.client = z;
        Buffer buffer = new Buffer();
        this.hpackBuffer = buffer;
        this.hpackWriter = new Hpack.Writer(buffer);
    }

    public int maxDataLength() {
        return this.maxFrameSize;
    }

    public synchronized void headers(int i, List<Header> list) throws IOException {
        if (!this.closed) {
            headers(false, i, list);
        } else {
            throw new IOException("closed");
        }
    }

    public void headers(boolean z, int i, List<Header> list) throws IOException {
        if (!this.closed) {
            this.hpackWriter.writeHeaders(list);
            long j = this.hpackBuffer.size;
            int min = (int) Math.min((long) this.maxFrameSize, j);
            long j2 = (long) min;
            byte b = 0;
            if (j == j2) {
                b = 4;
            }
            if (z) {
                b = (byte) (b | 1);
            }
            frameHeader(i, min, (byte) 1, b);
            this.sink.write(this.hpackBuffer, j2);
            if (j > j2) {
                writeContinuationFrames(i, j - j2);
                return;
            }
            return;
        }
        throw new IOException("closed");
    }
}
