package com.android.okhttp.internal.framed;

import com.android.okhttp.internal.framed.FrameReader;
import com.android.okhttp.internal.framed.Hpack;
import com.android.okhttp.okio.Buffer;
import com.android.okhttp.okio.BufferedSink;
import com.android.okhttp.okio.BufferedSource;
import com.android.okhttp.okio.ByteString;
import com.android.okhttp.okio.Source;
import com.android.okhttp.okio.Timeout;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Http2 implements Variant {
    private static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    private static final Logger logger = Logger.getLogger(FrameLogger.class.getName());

    static /* synthetic */ IOException access$200(String str, Object[] objArr) {
        ioException(str, objArr);
        throw null;
    }

    static /* synthetic */ IllegalArgumentException access$500(String str, Object[] objArr) {
        illegalArgument(str, objArr);
        throw null;
    }

    @Override // com.android.okhttp.internal.framed.Variant
    public FrameReader newReader(BufferedSource bufferedSource, boolean z) {
        return new Reader(bufferedSource, 4096, z);
    }

    @Override // com.android.okhttp.internal.framed.Variant
    public FrameWriter newWriter(BufferedSink bufferedSink, boolean z) {
        return new Writer(bufferedSink, z);
    }

    static final class Reader implements FrameReader {
        private final boolean client;
        private final ContinuationSource continuation = new ContinuationSource(this.source);
        final Hpack.Reader hpackReader;
        private final BufferedSource source;

        Reader(BufferedSource bufferedSource, int i, boolean z) {
            this.source = bufferedSource;
            this.client = z;
            this.hpackReader = new Hpack.Reader(i, this.continuation);
        }

        @Override // com.android.okhttp.internal.framed.FrameReader
        public void readConnectionPreface() {
            if (!this.client) {
                ByteString readByteString = this.source.readByteString((long) Http2.CONNECTION_PREFACE.size());
                if (Http2.logger.isLoggable(Level.FINE)) {
                    Http2.logger.fine(String.format("<< CONNECTION %s", readByteString.hex()));
                }
                if (!Http2.CONNECTION_PREFACE.equals(readByteString)) {
                    Http2.access$200("Expected a connection header but was %s", new Object[]{readByteString.utf8()});
                    throw null;
                }
            }
        }

        @Override // com.android.okhttp.internal.framed.FrameReader
        public boolean nextFrame(FrameReader.Handler handler) {
            try {
                this.source.require(9);
                int readMedium = Http2.readMedium(this.source);
                if (readMedium < 0 || readMedium > 16384) {
                    Http2.access$200("FRAME_SIZE_ERROR: %s", new Object[]{Integer.valueOf(readMedium)});
                    throw null;
                }
                byte readByte = (byte) (this.source.readByte() & 255);
                byte readByte2 = (byte) (this.source.readByte() & 255);
                int readInt = this.source.readInt() & Integer.MAX_VALUE;
                if (Http2.logger.isLoggable(Level.FINE)) {
                    Http2.logger.fine(FrameLogger.formatHeader(true, readInt, readMedium, readByte, readByte2));
                }
                switch (readByte) {
                    case 0:
                        readData(handler, readMedium, readByte2, readInt);
                        break;
                    case 1:
                        readHeaders(handler, readMedium, readByte2, readInt);
                        break;
                    case 2:
                        readPriority(handler, readMedium, readByte2, readInt);
                        break;
                    case 3:
                        readRstStream(handler, readMedium, readByte2, readInt);
                        break;
                    case 4:
                        readSettings(handler, readMedium, readByte2, readInt);
                        break;
                    case 5:
                        readPushPromise(handler, readMedium, readByte2, readInt);
                        break;
                    case 6:
                        readPing(handler, readMedium, readByte2, readInt);
                        break;
                    case 7:
                        readGoAway(handler, readMedium, readByte2, readInt);
                        break;
                    case 8:
                        readWindowUpdate(handler, readMedium, readByte2, readInt);
                        break;
                    default:
                        this.source.skip((long) readMedium);
                        break;
                }
                return true;
            } catch (IOException unused) {
                return false;
            }
        }

        private void readHeaders(FrameReader.Handler handler, int i, byte b, int i2) {
            short s = 0;
            if (i2 != 0) {
                boolean z = (b & 1) != 0;
                if ((b & 8) != 0) {
                    s = (short) (this.source.readByte() & 255);
                }
                if ((b & 32) != 0) {
                    readPriority(handler, i2);
                    i -= 5;
                }
                handler.headers(false, z, i2, -1, readHeaderBlock(Http2.lengthWithoutPadding(i, b, s), s, b, i2), HeadersMode.HTTP_20_HEADERS);
                return;
            }
            Http2.access$200("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            throw null;
        }

        private List readHeaderBlock(int i, short s, byte b, int i2) {
            ContinuationSource continuationSource = this.continuation;
            continuationSource.left = i;
            continuationSource.length = i;
            continuationSource.padding = s;
            continuationSource.flags = b;
            continuationSource.streamId = i2;
            this.hpackReader.readHeaders();
            return this.hpackReader.getAndResetHeaderList();
        }

        private void readData(FrameReader.Handler handler, int i, byte b, int i2) {
            boolean z = true;
            short s = 0;
            boolean z2 = (b & 1) != 0;
            if ((b & 32) == 0) {
                z = false;
            }
            if (!z) {
                if ((b & 8) != 0) {
                    s = (short) (this.source.readByte() & 255);
                }
                handler.data(z2, i2, this.source, Http2.lengthWithoutPadding(i, b, s));
                this.source.skip((long) s);
                return;
            }
            Http2.access$200("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
            throw null;
        }

        private void readPriority(FrameReader.Handler handler, int i, byte b, int i2) {
            if (i != 5) {
                Http2.access$200("TYPE_PRIORITY length: %d != 5", new Object[]{Integer.valueOf(i)});
                throw null;
            } else if (i2 != 0) {
                readPriority(handler, i2);
            } else {
                Http2.access$200("TYPE_PRIORITY streamId == 0", new Object[0]);
                throw null;
            }
        }

        private void readPriority(FrameReader.Handler handler, int i) {
            int readInt = this.source.readInt();
            handler.priority(i, readInt & Integer.MAX_VALUE, (this.source.readByte() & 255) + 1, (Integer.MIN_VALUE & readInt) != 0);
        }

        private void readRstStream(FrameReader.Handler handler, int i, byte b, int i2) {
            if (i != 4) {
                Http2.access$200("TYPE_RST_STREAM length: %d != 4", new Object[]{Integer.valueOf(i)});
                throw null;
            } else if (i2 != 0) {
                int readInt = this.source.readInt();
                ErrorCode fromHttp2 = ErrorCode.fromHttp2(readInt);
                if (fromHttp2 != null) {
                    handler.rstStream(i2, fromHttp2);
                    return;
                }
                Http2.access$200("TYPE_RST_STREAM unexpected error code: %d", new Object[]{Integer.valueOf(readInt)});
                throw null;
            } else {
                Http2.access$200("TYPE_RST_STREAM streamId == 0", new Object[0]);
                throw null;
            }
        }

        private void readSettings(FrameReader.Handler handler, int i, byte b, int i2) {
            if (i2 != 0) {
                Http2.access$200("TYPE_SETTINGS streamId != 0", new Object[0]);
                throw null;
            } else if ((b & 1) != 0) {
                if (i == 0) {
                    handler.ackSettings();
                } else {
                    Http2.access$200("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                    throw null;
                }
            } else if (i % 6 == 0) {
                Settings settings = new Settings();
                for (int i3 = 0; i3 < i; i3 += 6) {
                    short readShort = this.source.readShort();
                    int readInt = this.source.readInt();
                    switch (readShort) {
                        case 1:
                        case 6:
                            break;
                        case 2:
                            if (!(readInt == 0 || readInt == 1)) {
                                Http2.access$200("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                                throw null;
                            }
                        case 3:
                            readShort = 4;
                            break;
                        case 4:
                            readShort = 7;
                            if (readInt >= 0) {
                                break;
                            } else {
                                Http2.access$200("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                                throw null;
                            }
                        case 5:
                            if (readInt >= 16384 && readInt <= 16777215) {
                                break;
                            } else {
                                Http2.access$200("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", new Object[]{Integer.valueOf(readInt)});
                                throw null;
                            }
                            break;
                        default:
                            Http2.access$200("PROTOCOL_ERROR invalid settings id: %s", new Object[]{Short.valueOf(readShort)});
                            throw null;
                    }
                    settings.set(readShort, 0, readInt);
                }
                handler.settings(false, settings);
                if (settings.getHeaderTableSize() >= 0) {
                    this.hpackReader.headerTableSizeSetting(settings.getHeaderTableSize());
                }
            } else {
                Http2.access$200("TYPE_SETTINGS length %% 6 != 0: %s", new Object[]{Integer.valueOf(i)});
                throw null;
            }
        }

        private void readPushPromise(FrameReader.Handler handler, int i, byte b, int i2) {
            short s = 0;
            if (i2 != 0) {
                if ((b & 8) != 0) {
                    s = (short) (this.source.readByte() & 255);
                }
                handler.pushPromise(i2, this.source.readInt() & Integer.MAX_VALUE, readHeaderBlock(Http2.lengthWithoutPadding(i - 4, b, s), s, b, i2));
                return;
            }
            Http2.access$200("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            throw null;
        }

        private void readPing(FrameReader.Handler handler, int i, byte b, int i2) {
            boolean z = false;
            if (i != 8) {
                Http2.access$200("TYPE_PING length != 8: %s", new Object[]{Integer.valueOf(i)});
                throw null;
            } else if (i2 == 0) {
                int readInt = this.source.readInt();
                int readInt2 = this.source.readInt();
                if ((b & 1) != 0) {
                    z = true;
                }
                handler.ping(z, readInt, readInt2);
            } else {
                Http2.access$200("TYPE_PING streamId != 0", new Object[0]);
                throw null;
            }
        }

        private void readGoAway(FrameReader.Handler handler, int i, byte b, int i2) {
            if (i < 8) {
                Http2.access$200("TYPE_GOAWAY length < 8: %s", new Object[]{Integer.valueOf(i)});
                throw null;
            } else if (i2 == 0) {
                int readInt = this.source.readInt();
                int readInt2 = this.source.readInt();
                int i3 = i - 8;
                ErrorCode fromHttp2 = ErrorCode.fromHttp2(readInt2);
                if (fromHttp2 != null) {
                    ByteString byteString = ByteString.EMPTY;
                    if (i3 > 0) {
                        byteString = this.source.readByteString((long) i3);
                    }
                    handler.goAway(readInt, fromHttp2, byteString);
                    return;
                }
                Http2.access$200("TYPE_GOAWAY unexpected error code: %d", new Object[]{Integer.valueOf(readInt2)});
                throw null;
            } else {
                Http2.access$200("TYPE_GOAWAY streamId != 0", new Object[0]);
                throw null;
            }
        }

        private void readWindowUpdate(FrameReader.Handler handler, int i, byte b, int i2) {
            if (i == 4) {
                long readInt = ((long) this.source.readInt()) & 2147483647L;
                if (readInt != 0) {
                    handler.windowUpdate(i2, readInt);
                    return;
                }
                Http2.access$200("windowSizeIncrement was 0", new Object[]{Long.valueOf(readInt)});
                throw null;
            }
            Http2.access$200("TYPE_WINDOW_UPDATE length !=4: %s", new Object[]{Integer.valueOf(i)});
            throw null;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.source.close();
        }
    }

    static final class Writer implements FrameWriter {
        private final boolean client;
        private boolean closed;
        private final Buffer hpackBuffer = new Buffer();
        private final Hpack.Writer hpackWriter = new Hpack.Writer(this.hpackBuffer);
        private int maxFrameSize = 16384;
        private final BufferedSink sink;

        Writer(BufferedSink bufferedSink, boolean z) {
            this.sink = bufferedSink;
            this.client = z;
        }

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public synchronized void flush() {
            if (!this.closed) {
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public synchronized void ackSettings(Settings settings) {
            if (!this.closed) {
                this.maxFrameSize = settings.getMaxFrameSize(this.maxFrameSize);
                frameHeader(0, 0, (byte) 4, (byte) 1);
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public synchronized void connectionPreface() {
            if (this.closed) {
                throw new IOException("closed");
            } else if (this.client) {
                if (Http2.logger.isLoggable(Level.FINE)) {
                    Http2.logger.fine(String.format(">> CONNECTION %s", Http2.CONNECTION_PREFACE.hex()));
                }
                this.sink.write(Http2.CONNECTION_PREFACE.toByteArray());
                this.sink.flush();
            }
        }

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public synchronized void synStream(boolean z, boolean z2, int i, int i2, List list) {
            if (z2) {
                throw new UnsupportedOperationException();
            } else if (!this.closed) {
                headers(z, i, list);
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public synchronized void pushPromise(int i, int i2, List list) {
            if (!this.closed) {
                this.hpackWriter.writeHeaders(list);
                long size = this.hpackBuffer.size();
                int min = (int) Math.min((long) (this.maxFrameSize - 4), size);
                long j = (long) min;
                int i3 = (size > j ? 1 : (size == j ? 0 : -1));
                frameHeader(i, min + 4, (byte) 5, i3 == 0 ? (byte) 4 : 0);
                this.sink.writeInt(i2 & Integer.MAX_VALUE);
                this.sink.write(this.hpackBuffer, j);
                if (i3 > 0) {
                    writeContinuationFrames(i, size - j);
                }
            } else {
                throw new IOException("closed");
            }
        }

        /* access modifiers changed from: package-private */
        public void headers(boolean z, int i, List list) {
            if (!this.closed) {
                this.hpackWriter.writeHeaders(list);
                long size = this.hpackBuffer.size();
                int min = (int) Math.min((long) this.maxFrameSize, size);
                long j = (long) min;
                int i2 = (size > j ? 1 : (size == j ? 0 : -1));
                byte b = i2 == 0 ? (byte) 4 : 0;
                if (z) {
                    b = (byte) (b | 1);
                }
                frameHeader(i, min, (byte) 1, b);
                this.sink.write(this.hpackBuffer, j);
                if (i2 > 0) {
                    writeContinuationFrames(i, size - j);
                    return;
                }
                return;
            }
            throw new IOException("closed");
        }

        private void writeContinuationFrames(int i, long j) {
            while (j > 0) {
                int min = (int) Math.min((long) this.maxFrameSize, j);
                long j2 = (long) min;
                j -= j2;
                frameHeader(i, min, (byte) 9, j == 0 ? (byte) 4 : 0);
                this.sink.write(this.hpackBuffer, j2);
            }
        }

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public synchronized void rstStream(int i, ErrorCode errorCode) {
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

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public int maxDataLength() {
            return this.maxFrameSize;
        }

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public synchronized void data(boolean z, int i, Buffer buffer, int i2) {
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

        /* access modifiers changed from: package-private */
        public void dataFrame(int i, byte b, Buffer buffer, int i2) {
            frameHeader(i, i2, (byte) 0, b);
            if (i2 > 0) {
                this.sink.write(buffer, (long) i2);
            }
        }

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public synchronized void settings(Settings settings) {
            if (!this.closed) {
                int i = 0;
                frameHeader(0, settings.size() * 6, (byte) 4, (byte) 0);
                while (i < 10) {
                    if (settings.isSet(i)) {
                        this.sink.writeShort(i == 4 ? 3 : i == 7 ? 4 : i);
                        this.sink.writeInt(settings.get(i));
                    }
                    i++;
                }
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public synchronized void ping(boolean z, int i, int i2) {
            if (!this.closed) {
                frameHeader(0, 8, (byte) 6, z ? (byte) 1 : 0);
                this.sink.writeInt(i);
                this.sink.writeInt(i2);
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public synchronized void goAway(int i, ErrorCode errorCode, byte[] bArr) {
            if (this.closed) {
                throw new IOException("closed");
            } else if (errorCode.httpCode != -1) {
                frameHeader(0, bArr.length + 8, (byte) 7, (byte) 0);
                this.sink.writeInt(i);
                this.sink.writeInt(errorCode.httpCode);
                if (bArr.length > 0) {
                    this.sink.write(bArr);
                }
                this.sink.flush();
            } else {
                Http2.access$500("errorCode.httpCode == -1", new Object[0]);
                throw null;
            }
        }

        @Override // com.android.okhttp.internal.framed.FrameWriter
        public synchronized void windowUpdate(int i, long j) {
            if (this.closed) {
                throw new IOException("closed");
            } else if (j == 0 || j > 2147483647L) {
                Http2.access$500("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", new Object[]{Long.valueOf(j)});
                throw null;
            } else {
                frameHeader(i, 4, (byte) 8, (byte) 0);
                this.sink.writeInt((int) j);
                this.sink.flush();
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            this.closed = true;
            this.sink.close();
        }

        /* access modifiers changed from: package-private */
        public void frameHeader(int i, int i2, byte b, byte b2) {
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.fine(FrameLogger.formatHeader(false, i, i2, b, b2));
            }
            int i3 = this.maxFrameSize;
            if (i2 > i3) {
                Http2.access$500("FRAME_SIZE_ERROR length > %d: %d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)});
                throw null;
            } else if ((Integer.MIN_VALUE & i) == 0) {
                Http2.writeMedium(this.sink, i2);
                this.sink.writeByte(b & 255);
                this.sink.writeByte(b2 & 255);
                this.sink.writeInt(i & Integer.MAX_VALUE);
            } else {
                Http2.access$500("reserved bit set: %s", new Object[]{Integer.valueOf(i)});
                throw null;
            }
        }
    }

    private static IllegalArgumentException illegalArgument(String str, Object... objArr) {
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    private static IOException ioException(String str, Object... objArr) {
        throw new IOException(String.format(str, objArr));
    }

    /* access modifiers changed from: package-private */
    public static final class ContinuationSource implements Source {
        byte flags;
        int left;
        int length;
        short padding;
        private final BufferedSource source;
        int streamId;

        @Override // java.io.Closeable, com.android.okhttp.okio.Source, java.lang.AutoCloseable
        public void close() {
        }

        public ContinuationSource(BufferedSource bufferedSource) {
            this.source = bufferedSource;
        }

        @Override // com.android.okhttp.okio.Source
        public long read(Buffer buffer, long j) {
            while (true) {
                int i = this.left;
                if (i == 0) {
                    this.source.skip((long) this.padding);
                    this.padding = 0;
                    if ((this.flags & 4) != 0) {
                        return -1;
                    }
                    readContinuationHeader();
                } else {
                    long read = this.source.read(buffer, Math.min(j, (long) i));
                    if (read == -1) {
                        return -1;
                    }
                    this.left = (int) (((long) this.left) - read);
                    return read;
                }
            }
        }

        @Override // com.android.okhttp.okio.Source
        public Timeout timeout() {
            return this.source.timeout();
        }

        private void readContinuationHeader() {
            int i = this.streamId;
            int readMedium = Http2.readMedium(this.source);
            this.left = readMedium;
            this.length = readMedium;
            byte readByte = (byte) (this.source.readByte() & 255);
            this.flags = (byte) (this.source.readByte() & 255);
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.fine(FrameLogger.formatHeader(true, this.streamId, this.length, readByte, this.flags));
            }
            this.streamId = this.source.readInt() & Integer.MAX_VALUE;
            if (readByte != 9) {
                Http2.access$200("%s != TYPE_CONTINUATION", new Object[]{Byte.valueOf(readByte)});
                throw null;
            } else if (this.streamId != i) {
                Http2.access$200("TYPE_CONTINUATION streamId changed", new Object[0]);
                throw null;
            }
        }
    }

    /* access modifiers changed from: private */
    public static int lengthWithoutPadding(int i, byte b, short s) {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        ioException("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw null;
    }

    /* access modifiers changed from: package-private */
    public static final class FrameLogger {
        private static final String[] BINARY = new String[256];
        private static final String[] FLAGS = new String[64];
        private static final String[] TYPES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

        FrameLogger() {
        }

        static String formatHeader(boolean z, int i, int i2, byte b, byte b2) {
            String[] strArr = TYPES;
            String format = b < strArr.length ? strArr[b] : String.format("0x%02x", Byte.valueOf(b));
            String formatFlags = formatFlags(b, b2);
            Object[] objArr = new Object[5];
            objArr[0] = z ? "<<" : ">>";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = Integer.valueOf(i2);
            objArr[3] = format;
            objArr[4] = formatFlags;
            return String.format("%s 0x%08x %5d %-13s %s", objArr);
        }

        static String formatFlags(byte b, byte b2) {
            if (b2 == 0) {
                return "";
            }
            if (!(b == 2 || b == 3)) {
                if (b == 4 || b == 6) {
                    return b2 == 1 ? "ACK" : BINARY[b2];
                }
                if (!(b == 7 || b == 8)) {
                    String[] strArr = FLAGS;
                    String str = b2 < strArr.length ? strArr[b2] : BINARY[b2];
                    if (b == 5 && (b2 & 4) != 0) {
                        return str.replace("HEADERS", "PUSH_PROMISE");
                    }
                    if (b != 0 || (b2 & 32) == 0) {
                        return str;
                    }
                    return str.replace("PRIORITY", "COMPRESSED");
                }
            }
            return BINARY[b2];
        }

        static {
            int i = 0;
            if (BINARY.length <= 0) {
                String[] strArr = FLAGS;
                strArr[0] = "";
                strArr[1] = "END_STREAM";
                int[] iArr = {1};
                strArr[8] = "PADDED";
                for (int i2 : iArr) {
                    FLAGS[i2 | 8] = FLAGS[i2] + "|PADDED";
                }
                String[] strArr2 = FLAGS;
                strArr2[4] = "END_HEADERS";
                strArr2[32] = "PRIORITY";
                strArr2[36] = "END_HEADERS|PRIORITY";
                int[] iArr2 = {4, 32, 36};
                for (int i3 : iArr2) {
                    for (int i4 : iArr) {
                        int i5 = i4 | i3;
                        FLAGS[i5] = FLAGS[i4] + '|' + FLAGS[i3];
                        FLAGS[i5 | 8] = FLAGS[i4] + '|' + FLAGS[i3] + "|PADDED";
                    }
                }
                while (true) {
                    String[] strArr3 = FLAGS;
                    if (i < strArr3.length) {
                        if (strArr3[i] == null) {
                            strArr3[i] = BINARY[i];
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            } else {
                Integer.toBinaryString(0);
                throw null;
            }
        }
    }

    /* access modifiers changed from: private */
    public static int readMedium(BufferedSource bufferedSource) {
        return (bufferedSource.readByte() & 255) | ((bufferedSource.readByte() & 255) << 16) | ((bufferedSource.readByte() & 255) << 8);
    }

    /* access modifiers changed from: private */
    public static void writeMedium(BufferedSink bufferedSink, int i) {
        bufferedSink.writeByte((i >>> 16) & 255);
        bufferedSink.writeByte((i >>> 8) & 255);
        bufferedSink.writeByte(i & 255);
    }
}
