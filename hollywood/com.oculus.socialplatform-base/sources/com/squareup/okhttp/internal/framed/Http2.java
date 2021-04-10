package com.squareup.okhttp.internal.framed;

import X.AnonymousClass006;
import com.adobe.xmp.impl.Base64;
import com.oculus.localmedia.MediaProviderUtils;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.framed.FrameReader;
import com.squareup.okhttp.internal.framed.Hpack;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

public final class Http2 implements Variant {
    public static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    public static final byte FLAG_ACK = 1;
    public static final byte FLAG_COMPRESSED = 32;
    public static final byte FLAG_END_HEADERS = 4;
    public static final byte FLAG_END_PUSH_PROMISE = 4;
    public static final byte FLAG_END_STREAM = 1;
    public static final byte FLAG_NONE = 0;
    public static final byte FLAG_PADDED = 8;
    public static final byte FLAG_PRIORITY = 32;
    public static final int INITIAL_MAX_FRAME_SIZE = 16384;
    public static final byte TYPE_CONTINUATION = 9;
    public static final byte TYPE_DATA = 0;
    public static final byte TYPE_GOAWAY = 7;
    public static final byte TYPE_HEADERS = 1;
    public static final byte TYPE_PING = 6;
    public static final byte TYPE_PRIORITY = 2;
    public static final byte TYPE_PUSH_PROMISE = 5;
    public static final byte TYPE_RST_STREAM = 3;
    public static final byte TYPE_SETTINGS = 4;
    public static final byte TYPE_WINDOW_UPDATE = 8;
    public static final Logger logger = Logger.getLogger(FrameLogger.class.getName());

    public static final class ContinuationSource implements Source {
        public byte flags;
        public int left;
        public int length;
        public short padding;
        public final BufferedSource source;
        public int streamId;

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        private void readContinuationHeader() throws IOException {
            int i = this.streamId;
            int readMedium = Http2.readMedium(this.source);
            this.left = readMedium;
            this.length = readMedium;
            byte readByte = (byte) (this.source.readByte() & Base64.INVALID);
            this.flags = (byte) (this.source.readByte() & Base64.INVALID);
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.fine(FrameLogger.formatHeader(true, this.streamId, this.length, readByte, this.flags));
            }
            int readInt = this.source.readInt() & Integer.MAX_VALUE;
            this.streamId = readInt;
            if (readByte != 9) {
                Http2.access$200("%s != TYPE_CONTINUATION", new Object[]{Byte.valueOf(readByte)});
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else if (readInt != i) {
                Http2.access$200("TYPE_CONTINUATION streamId changed", new Object[0]);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            while (true) {
                int i = this.left;
                if (i == 0) {
                    this.source.skip((long) this.padding);
                    this.padding = 0;
                    if ((this.flags & 4) != 0) {
                        break;
                    }
                    readContinuationHeader();
                } else {
                    long read = this.source.read(buffer, Math.min(j, (long) i));
                    if (read != -1) {
                        this.left = (int) (((long) this.left) - read);
                        return read;
                    }
                }
            }
            return -1;
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.source.timeout();
        }

        public ContinuationSource(BufferedSource bufferedSource) {
            this.source = bufferedSource;
        }
    }

    public static final class FrameLogger {
        public static final String[] BINARY;
        public static final String[] FLAGS;
        public static final String[] TYPES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

        static {
            String[] strArr;
            String[] strArr2 = new String[64];
            FLAGS = strArr2;
            String[] strArr3 = new String[256];
            BINARY = strArr3;
            for (int i = 0; i < 256; i++) {
                strArr3[i] = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
            }
            strArr2[0] = "";
            strArr2[1] = "END_STREAM";
            int[] iArr = {1};
            strArr2[8] = "PADDED";
            for (int i2 = 0; i2 < 1; i2++) {
                int i3 = iArr[i2];
                strArr2[i3 | 8] = AnonymousClass006.A07(strArr2[i3], "|PADDED");
            }
            strArr2[4] = "END_HEADERS";
            strArr2[32] = "PRIORITY";
            strArr2[36] = "END_HEADERS|PRIORITY";
            int[] iArr2 = {4, 32, 36};
            int i4 = 0;
            do {
                int i5 = iArr2[i4];
                int i6 = iArr[0];
                strArr = FLAGS;
                int i7 = i6 | i5;
                strArr[i7] = AnonymousClass006.A02(strArr[i6], '|', strArr[i5]);
                StringBuilder sb = new StringBuilder();
                sb.append(strArr[i6]);
                sb.append('|');
                sb.append(strArr[i5]);
                sb.append("|PADDED");
                strArr[i7 | 8] = sb.toString();
                i4++;
            } while (i4 < 3);
            for (int i8 = 0; i8 < strArr.length; i8++) {
                if (strArr[i8] == null) {
                    strArr[i8] = BINARY[i8];
                }
            }
        }

        public static String formatFlags(byte b, byte b2) {
            String str;
            String str2;
            if (b2 == 0) {
                return "";
            }
            if (!(b == 2 || b == 3)) {
                if (b == 4 || b == 6) {
                    if (b2 == 1) {
                        return "ACK";
                    }
                } else if (!(b == 7 || b == 8)) {
                    String[] strArr = FLAGS;
                    if (b2 >= strArr.length) {
                        strArr = BINARY;
                    }
                    String str3 = strArr[b2];
                    if (b == 5) {
                        if ((b2 & 4) != 0) {
                            str = "HEADERS";
                            str2 = "PUSH_PROMISE";
                        }
                        return str3;
                    }
                    if (b == 0 && (b2 & 32) != 0) {
                        str = "PRIORITY";
                        str2 = "COMPRESSED";
                    }
                    return str3;
                    return str3.replace(str, str2);
                }
            }
            return BINARY[b2];
        }

        public static String formatHeader(boolean z, int i, int i2, byte b, byte b2) {
            String format;
            String str;
            String[] strArr = TYPES;
            if (b < strArr.length) {
                format = strArr[b];
            } else {
                format = String.format("0x%02x", Byte.valueOf(b));
            }
            String formatFlags = formatFlags(b, b2);
            if (z) {
                str = "<<";
            } else {
                str = ">>";
            }
            return String.format("%s 0x%08x %5d %-13s %s", str, Integer.valueOf(i), Integer.valueOf(i2), format, formatFlags);
        }
    }

    public static final class Reader implements FrameReader {
        public final boolean client;
        public final ContinuationSource continuation;
        public final Hpack.Reader hpackReader;
        public final BufferedSource source;

        private void readHeaders(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            short s = 0;
            if (i2 != 0) {
                boolean z = false;
                if ((b & 1) != 0) {
                    z = true;
                }
                if ((b & 8) != 0) {
                    s = (short) (this.source.readByte() & Base64.INVALID);
                }
                if ((b & 32) != 0) {
                    readPriority(handler, i2);
                    i -= 5;
                }
                handler.headers(false, z, i2, -1, readHeaderBlock(Http2.lengthWithoutPadding(i, b, s), s, b, i2), HeadersMode.HTTP_20_HEADERS);
                return;
            }
            Http2.access$200("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        private void readPing(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            boolean z = false;
            if (i != 8) {
                Http2.access$200("TYPE_PING length != 8: %s", new Object[]{Integer.valueOf(i)});
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else if (i2 == 0) {
                int readInt = this.source.readInt();
                int readInt2 = this.source.readInt();
                if ((b & 1) != 0) {
                    z = true;
                }
                handler.ping(z, readInt, readInt2);
            } else {
                Http2.access$200("TYPE_PING streamId != 0", new Object[0]);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        private void readPushPromise(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            short s = 0;
            if (i2 != 0) {
                if ((b & 8) != 0) {
                    s = (short) (this.source.readByte() & Base64.INVALID);
                }
                handler.pushPromise(i2, this.source.readInt() & Integer.MAX_VALUE, readHeaderBlock(Http2.lengthWithoutPadding(i - 4, b, s), s, b, i2));
                return;
            }
            Http2.access$200("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        private void readRstStream(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            if (i != 4) {
                Http2.access$200("TYPE_RST_STREAM length: %d != 4", new Object[]{Integer.valueOf(i)});
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else if (i2 != 0) {
                int readInt = this.source.readInt();
                ErrorCode fromHttp2 = ErrorCode.fromHttp2(readInt);
                if (fromHttp2 != null) {
                    handler.rstStream(i2, fromHttp2);
                } else {
                    Http2.access$200("TYPE_RST_STREAM unexpected error code: %d", new Object[]{Integer.valueOf(readInt)});
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                Http2.access$200("TYPE_RST_STREAM streamId == 0", new Object[0]);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        private void readSettings(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            if (i2 != 0) {
                Http2.access$200("TYPE_SETTINGS streamId != 0", new Object[0]);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else if ((b & 1) != 0) {
                if (i == 0) {
                    handler.ackSettings();
                } else {
                    Http2.access$200("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
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
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
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
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        case 5:
                            if (readInt >= 16384 && readInt <= 16777215) {
                                break;
                            } else {
                                Http2.access$200("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", new Object[]{Integer.valueOf(readInt)});
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        default:
                            Http2.access$200("PROTOCOL_ERROR invalid settings id: %s", new Object[]{Short.valueOf(readShort)});
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    settings.set(readShort, 0, readInt);
                }
                handler.settings(false, settings);
                int headerTableSize = settings.getHeaderTableSize();
                if (headerTableSize >= 0) {
                    this.hpackReader.headerTableSizeSetting(headerTableSize);
                }
            } else {
                Http2.access$200("TYPE_SETTINGS length %% 6 != 0: %s", new Object[]{Integer.valueOf(i)});
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        private void readWindowUpdate(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            if (i == 4) {
                long readInt = ((long) this.source.readInt()) & 2147483647L;
                if (readInt != 0) {
                    handler.windowUpdate(i2, readInt);
                } else {
                    Http2.access$200("windowSizeIncrement was 0", new Object[]{Long.valueOf(readInt)});
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                Http2.access$200("TYPE_WINDOW_UPDATE length !=4: %s", new Object[]{Integer.valueOf(i)});
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader
        public boolean nextFrame(FrameReader.Handler handler) throws IOException {
            try {
                this.source.require(9);
                int readMedium = Http2.readMedium(this.source);
                if (readMedium < 0 || readMedium > 16384) {
                    Http2.access$200("FRAME_SIZE_ERROR: %s", new Object[]{Integer.valueOf(readMedium)});
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                byte readByte = (byte) (this.source.readByte() & Base64.INVALID);
                byte readByte2 = (byte) (this.source.readByte() & Base64.INVALID);
                int readInt = this.source.readInt() & Integer.MAX_VALUE;
                Logger logger = Http2.logger;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(FrameLogger.formatHeader(true, readInt, readMedium, readByte, readByte2));
                }
                switch (readByte) {
                    case 0:
                        readData(handler, readMedium, readByte2, readInt);
                        return true;
                    case 1:
                        readHeaders(handler, readMedium, readByte2, readInt);
                        return true;
                    case 2:
                        readPriority(handler, readMedium, readByte2, readInt);
                        return true;
                    case 3:
                        readRstStream(handler, readMedium, readByte2, readInt);
                        return true;
                    case 4:
                        readSettings(handler, readMedium, readByte2, readInt);
                        return true;
                    case 5:
                        readPushPromise(handler, readMedium, readByte2, readInt);
                        return true;
                    case 6:
                        readPing(handler, readMedium, readByte2, readInt);
                        return true;
                    case 7:
                        readGoAway(handler, readMedium, readByte2, readInt);
                        return true;
                    case 8:
                        readWindowUpdate(handler, readMedium, readByte2, readInt);
                        return true;
                    default:
                        this.source.skip((long) readMedium);
                        return true;
                }
            } catch (IOException unused) {
                return false;
            }
        }

        private void readData(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            short s = 0;
            boolean z = false;
            if ((b & 1) != 0) {
                z = true;
            }
            if ((b & 32) != 0) {
                Http2.access$200("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
            if ((b & 8) != 0) {
                s = (short) (this.source.readByte() & Base64.INVALID);
            }
            handler.data(z, i2, this.source, Http2.lengthWithoutPadding(i, b, s));
            this.source.skip((long) s);
        }

        private void readGoAway(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            if (i < 8) {
                Http2.access$200("TYPE_GOAWAY length < 8: %s", new Object[]{Integer.valueOf(i)});
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
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
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else {
                Http2.access$200("TYPE_GOAWAY streamId != 0", new Object[0]);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        private List<Header> readHeaderBlock(int i, short s, byte b, int i2) throws IOException {
            ContinuationSource continuationSource = this.continuation;
            continuationSource.left = i;
            continuationSource.length = i;
            continuationSource.padding = s;
            continuationSource.flags = b;
            continuationSource.streamId = i2;
            this.hpackReader.readHeaders();
            return this.hpackReader.getAndResetHeaderList();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.source.close();
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader
        public void readConnectionPreface() throws IOException {
            if (!this.client) {
                ByteString readByteString = this.source.readByteString((long) Http2.CONNECTION_PREFACE.size());
                if (Http2.logger.isLoggable(Level.FINE)) {
                    Http2.logger.fine(String.format("<< CONNECTION %s", readByteString.hex()));
                }
                if (!Http2.CONNECTION_PREFACE.equals(readByteString)) {
                    Http2.access$200("Expected a connection header but was %s", new Object[]{readByteString.utf8()});
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }

        public Reader(BufferedSource bufferedSource, int i, boolean z) {
            this.source = bufferedSource;
            this.client = z;
            ContinuationSource continuationSource = new ContinuationSource(bufferedSource);
            this.continuation = continuationSource;
            this.hpackReader = new Hpack.Reader(i, continuationSource);
        }

        private void readPriority(FrameReader.Handler handler, int i) throws IOException {
            int readInt = this.source.readInt();
            boolean z = false;
            if ((Integer.MIN_VALUE & readInt) != 0) {
                z = true;
            }
            handler.priority(i, readInt & Integer.MAX_VALUE, (this.source.readByte() & Base64.INVALID) + 1, z);
        }

        private void readPriority(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            if (i != 5) {
                Http2.access$200("TYPE_PRIORITY length: %d != 5", new Object[]{Integer.valueOf(i)});
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else if (i2 != 0) {
                readPriority(handler, i2);
            } else {
                Http2.access$200("TYPE_PRIORITY streamId == 0", new Object[0]);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    public static final class Writer implements FrameWriter {
        public final boolean client;
        public boolean closed;
        public final Buffer hpackBuffer;
        public final Hpack.Writer hpackWriter;
        public int maxFrameSize = 16384;
        public final BufferedSink sink;

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void ackSettings(Settings settings) throws IOException {
            if (!this.closed) {
                this.maxFrameSize = settings.getMaxFrameSize(this.maxFrameSize);
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

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void connectionPreface() throws IOException {
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

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
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

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void flush() throws IOException {
            if (!this.closed) {
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
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
                Http2.access$500("errorCode.httpCode == -1", new Object[0]);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
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

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
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

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
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

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
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

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void synReply(boolean z, int i, List<Header> list) throws IOException {
            if (!this.closed) {
                headers(z, i, list);
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void synStream(boolean z, boolean z2, int i, int i2, List<Header> list) throws IOException {
            if (z2) {
                throw new UnsupportedOperationException();
            } else if (!this.closed) {
                headers(z, i, list);
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void windowUpdate(int i, long j) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            } else if (j == 0 || j > 2147483647L) {
                Http2.access$500("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", new Object[]{Long.valueOf(j)});
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

        public void frameHeader(int i, int i2, byte b, byte b2) throws IOException {
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.fine(FrameLogger.formatHeader(false, i, i2, b, b2));
            }
            int i3 = this.maxFrameSize;
            if (i2 > i3) {
                Http2.access$500("FRAME_SIZE_ERROR length > %d: %d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)});
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else if ((Integer.MIN_VALUE & i) == 0) {
                Http2.writeMedium(this.sink, i2);
                this.sink.writeByte(b & Base64.INVALID);
                this.sink.writeByte(b2 & Base64.INVALID);
                this.sink.writeInt(i & Integer.MAX_VALUE);
            } else {
                Http2.access$500("reserved bit set: %s", new Object[]{Integer.valueOf(i)});
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        public Writer(BufferedSink bufferedSink, boolean z) {
            this.sink = bufferedSink;
            this.client = z;
            Buffer buffer = new Buffer();
            this.hpackBuffer = buffer;
            this.hpackWriter = new Hpack.Writer(buffer);
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public int maxDataLength() {
            return this.maxFrameSize;
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
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

    public static int lengthWithoutPadding(int i, byte b, short s) throws IOException {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        ioException("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public static void writeMedium(BufferedSink bufferedSink, int i) throws IOException {
        bufferedSink.writeByte((i >>> 16) & MediaProviderUtils.JPEG_HEADER);
        bufferedSink.writeByte((i >>> 8) & MediaProviderUtils.JPEG_HEADER);
        bufferedSink.writeByte(i & MediaProviderUtils.JPEG_HEADER);
    }

    @Override // com.squareup.okhttp.internal.framed.Variant
    public FrameReader newReader(BufferedSource bufferedSource, boolean z) {
        return new Reader(bufferedSource, 4096, z);
    }

    @Override // com.squareup.okhttp.internal.framed.Variant
    public FrameWriter newWriter(BufferedSink bufferedSink, boolean z) {
        return new Writer(bufferedSink, z);
    }

    public static /* synthetic */ IOException access$200(String str, Object[] objArr) throws IOException {
        ioException(str, objArr);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public static /* synthetic */ IllegalArgumentException access$500(String str, Object[] objArr) {
        illegalArgument(str, objArr);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public static IllegalArgumentException illegalArgument(String str, Object... objArr) {
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    public static IOException ioException(String str, Object... objArr) throws IOException {
        throw new IOException(String.format(str, objArr));
    }

    public static int readMedium(BufferedSource bufferedSource) throws IOException {
        return (bufferedSource.readByte() & Base64.INVALID) | ((bufferedSource.readByte() & Base64.INVALID) << 16) | ((bufferedSource.readByte() & Base64.INVALID) << 8);
    }

    @Override // com.squareup.okhttp.internal.framed.Variant
    public Protocol getProtocol() {
        return Protocol.HTTP_2;
    }
}
