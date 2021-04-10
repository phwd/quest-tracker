package com.squareup.okhttp.internal.framed;

import X.AbstractC0312cb;
import X.AnonymousClass06;
import X.AnonymousClass98;
import X.KC;
import X.KJ;
import X.ca;
import X.ci;
import com.facebook.acra.util.HttpRequest;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.framed.FrameReader;
import com.squareup.okhttp.internal.framed.Hpack;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Http2 implements Variant {
    public static final ci CONNECTION_PREFACE = ci.A04("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
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

    public static final class ContinuationSource implements AbstractC0312cb {
        public byte flags;
        public int left;
        public int length;
        public short padding;
        public final KC source;
        public int streamId;

        @Override // java.io.Closeable, X.AbstractC0312cb, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        private void readContinuationHeader() throws IOException {
            Object[] objArr;
            String str;
            int i = this.streamId;
            int readMedium = Http2.readMedium(this.source);
            this.left = readMedium;
            this.length = readMedium;
            byte readByte = (byte) (this.source.readByte() & 255);
            this.flags = (byte) (this.source.readByte() & 255);
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.fine(FrameLogger.formatHeader(true, this.streamId, this.length, readByte, this.flags));
            }
            int readInt = this.source.readInt() & Integer.MAX_VALUE;
            this.streamId = readInt;
            if (readByte != 9) {
                objArr = new Object[]{Byte.valueOf(readByte)};
                str = "%s != TYPE_CONTINUATION";
            } else if (readInt != i) {
                objArr = new Object[0];
                str = "TYPE_CONTINUATION streamId changed";
            } else {
                return;
            }
            Http2.access$200(str, objArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        @Override // X.AbstractC0312cb
        public long read(AnonymousClass98 r8, long j) throws IOException {
            while (true) {
                int i = this.left;
                if (i == 0) {
                    this.source.A5F((long) this.padding);
                    this.padding = 0;
                    if ((this.flags & 4) != 0) {
                        break;
                    }
                    readContinuationHeader();
                } else {
                    long read = this.source.read(r8, Math.min(j, (long) i));
                    if (read != -1) {
                        this.left = (int) (((long) this.left) - read);
                        return read;
                    }
                }
            }
            return -1;
        }

        @Override // X.AbstractC0312cb
        public ca timeout() {
            return this.source.timeout();
        }

        public ContinuationSource(KC kc) {
            this.source = kc;
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
            String[] strArr3 = new String[HttpRequest.CHAR_ARRAY_BUFFER_SIZE];
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
                strArr2[i3 | 8] = AnonymousClass06.A04(strArr2[i3], "|PADDED");
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
                strArr[i7] = AnonymousClass06.A00(strArr[i6], '|', strArr[i5]);
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
        public final KC source;

        private void readHeaders(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            short s = 0;
            if (i2 != 0) {
                boolean z = false;
                if ((b & 1) != 0) {
                    z = true;
                }
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
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        private void readPing(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            Object[] objArr;
            String str;
            boolean z = false;
            if (i != 8) {
                objArr = new Object[]{Integer.valueOf(i)};
                str = "TYPE_PING length != 8: %s";
            } else if (i2 == 0) {
                int readInt = this.source.readInt();
                int readInt2 = this.source.readInt();
                if ((b & 1) != 0) {
                    z = true;
                }
                handler.ping(z, readInt, readInt2);
                return;
            } else {
                objArr = new Object[0];
                str = "TYPE_PING streamId != 0";
            }
            Http2.access$200(str, objArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        private void readPushPromise(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            short s = 0;
            if (i2 != 0) {
                if ((b & 8) != 0) {
                    s = (short) (this.source.readByte() & 255);
                }
                handler.pushPromise(i2, this.source.readInt() & Integer.MAX_VALUE, readHeaderBlock(Http2.lengthWithoutPadding(i - 4, b, s), s, b, i2));
                return;
            }
            Http2.access$200("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        private void readRstStream(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            Object[] objArr;
            String str;
            if (i != 4) {
                objArr = new Object[]{Integer.valueOf(i)};
                str = "TYPE_RST_STREAM length: %d != 4";
            } else if (i2 != 0) {
                int readInt = this.source.readInt();
                ErrorCode fromHttp2 = ErrorCode.fromHttp2(readInt);
                if (fromHttp2 != null) {
                    handler.rstStream(i2, fromHttp2);
                    return;
                } else {
                    objArr = new Object[]{Integer.valueOf(readInt)};
                    str = "TYPE_RST_STREAM unexpected error code: %d";
                }
            } else {
                objArr = new Object[0];
                str = "TYPE_RST_STREAM streamId == 0";
            }
            Http2.access$200(str, objArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        private void readSettings(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            Object[] objArr;
            String str;
            if (i2 != 0) {
                objArr = new Object[0];
                str = "TYPE_SETTINGS streamId != 0";
            } else if ((b & 1) != 0) {
                if (i == 0) {
                    handler.ackSettings();
                    return;
                } else {
                    objArr = new Object[0];
                    str = "FRAME_SIZE_ERROR ack frame should be empty!";
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
                                objArr = new Object[0];
                                str = "PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1";
                                break;
                            }
                        case 3:
                            readShort = 4;
                            break;
                        case 4:
                            readShort = 7;
                            if (readInt < 0) {
                                objArr = new Object[0];
                                str = "PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1";
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (readInt < 16384 || readInt > 16777215) {
                                objArr = new Object[]{Integer.valueOf(readInt)};
                                str = "PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s";
                                break;
                            } else {
                                break;
                            }
                            break;
                        default:
                            objArr = new Object[]{Short.valueOf(readShort)};
                            str = "PROTOCOL_ERROR invalid settings id: %s";
                            break;
                    }
                    settings.set(readShort, 0, readInt);
                }
                handler.settings(false, settings);
                int headerTableSize = settings.getHeaderTableSize();
                if (headerTableSize >= 0) {
                    this.hpackReader.headerTableSizeSetting(headerTableSize);
                    return;
                }
                return;
            } else {
                objArr = new Object[]{Integer.valueOf(i)};
                str = "TYPE_SETTINGS length %% 6 != 0: %s";
            }
            Http2.access$200(str, objArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        private void readWindowUpdate(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            Object[] objArr;
            String str;
            if (i == 4) {
                long readInt = ((long) this.source.readInt()) & 2147483647L;
                if (readInt != 0) {
                    handler.windowUpdate(i2, readInt);
                    return;
                } else {
                    objArr = new Object[]{Long.valueOf(readInt)};
                    str = "windowSizeIncrement was 0";
                }
            } else {
                objArr = new Object[]{Integer.valueOf(i)};
                str = "TYPE_WINDOW_UPDATE length !=4: %s";
            }
            Http2.access$200(str, objArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader
        public boolean nextFrame(FrameReader.Handler handler) throws IOException {
            try {
                this.source.A4l(9);
                int readMedium = Http2.readMedium(this.source);
                if (readMedium < 0 || readMedium > 16384) {
                    Http2.access$200("FRAME_SIZE_ERROR: %s", new Object[]{Integer.valueOf(readMedium)});
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                byte readByte = (byte) (this.source.readByte() & 255);
                byte readByte2 = (byte) (this.source.readByte() & 255);
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
                        this.source.A5F((long) readMedium);
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
                s = (short) (this.source.readByte() & 255);
            }
            handler.data(z, i2, this.source, Http2.lengthWithoutPadding(i, b, s));
            this.source.A5F((long) s);
        }

        private void readGoAway(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            Object[] objArr;
            String str;
            if (i < 8) {
                objArr = new Object[]{Integer.valueOf(i)};
                str = "TYPE_GOAWAY length < 8: %s";
            } else if (i2 == 0) {
                int readInt = this.source.readInt();
                int readInt2 = this.source.readInt();
                int i3 = i - 8;
                ErrorCode fromHttp2 = ErrorCode.fromHttp2(readInt2);
                if (fromHttp2 != null) {
                    ci ciVar = ci.A03;
                    if (i3 > 0) {
                        ciVar = this.source.A4U((long) i3);
                    }
                    handler.goAway(readInt, fromHttp2, ciVar);
                    return;
                }
                objArr = new Object[]{Integer.valueOf(readInt2)};
                str = "TYPE_GOAWAY unexpected error code: %d";
            } else {
                objArr = new Object[0];
                str = "TYPE_GOAWAY streamId != 0";
            }
            Http2.access$200(str, objArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
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
                ci A4U = this.source.A4U((long) Http2.CONNECTION_PREFACE.A07());
                if (Http2.logger.isLoggable(Level.FINE)) {
                    Http2.logger.fine(String.format("<< CONNECTION %s", A4U.A09()));
                }
                if (!Http2.CONNECTION_PREFACE.equals(A4U)) {
                    Http2.access$200("Expected a connection header but was %s", new Object[]{A4U.A0A()});
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }

        public Reader(KC kc, int i, boolean z) {
            this.source = kc;
            this.client = z;
            ContinuationSource continuationSource = new ContinuationSource(kc);
            this.continuation = continuationSource;
            this.hpackReader = new Hpack.Reader(i, continuationSource);
        }

        private void readPriority(FrameReader.Handler handler, int i) throws IOException {
            int readInt = this.source.readInt();
            boolean z = false;
            if ((Integer.MIN_VALUE & readInt) != 0) {
                z = true;
            }
            handler.priority(i, readInt & Integer.MAX_VALUE, (this.source.readByte() & 255) + 1, z);
        }

        private void readPriority(FrameReader.Handler handler, int i, byte b, int i2) throws IOException {
            Object[] objArr;
            String str;
            if (i != 5) {
                objArr = new Object[]{Integer.valueOf(i)};
                str = "TYPE_PRIORITY length: %d != 5";
            } else if (i2 != 0) {
                readPriority(handler, i2);
                return;
            } else {
                objArr = new Object[0];
                str = "TYPE_PRIORITY streamId == 0";
            }
            Http2.access$200(str, objArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public static final class Writer implements FrameWriter {
        public final boolean client;
        public boolean closed;
        public final AnonymousClass98 hpackBuffer;
        public final Hpack.Writer hpackWriter;
        public int maxFrameSize = Http2.INITIAL_MAX_FRAME_SIZE;
        public final KJ sink;

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
                    Http2.logger.fine(String.format(">> CONNECTION %s", Http2.CONNECTION_PREFACE.A09()));
                }
                this.sink.A5n(Http2.CONNECTION_PREFACE.A0I());
                this.sink.flush();
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void data(boolean z, int i, AnonymousClass98 r5, int i2) throws IOException {
            if (!this.closed) {
                byte b = 0;
                if (z) {
                    b = (byte) 1;
                }
                dataFrame(i, b, r5, i2);
            } else {
                throw new IOException("closed");
            }
        }

        public void dataFrame(int i, byte b, AnonymousClass98 r6, int i2) throws IOException {
            frameHeader(i, i2, (byte) 0, b);
            if (i2 > 0) {
                this.sink.write(r6, (long) i2);
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
                this.sink.A5t(i);
                this.sink.A5t(errorCode.httpCode);
                if (length > 0) {
                    this.sink.A5n(bArr);
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
                this.sink.A5t(i);
                this.sink.A5t(i2);
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void pushPromise(int i, int i2, List<Header> list) throws IOException {
            if (!this.closed) {
                this.hpackWriter.writeHeaders(list);
                long j = this.hpackBuffer.A00;
                int min = (int) Math.min((long) (this.maxFrameSize - 4), j);
                long j2 = (long) min;
                byte b = 0;
                if (j == j2) {
                    b = 4;
                }
                frameHeader(i, min + 4, (byte) 5, b);
                this.sink.A5t(i2 & Integer.MAX_VALUE);
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
                this.sink.A5t(errorCode.httpCode);
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
                        this.sink.A5x(i2);
                        this.sink.A5t(settings.values[i]);
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
                this.sink.A5t((int) j);
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
            Object[] objArr;
            String str;
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.fine(FrameLogger.formatHeader(false, i, i2, b, b2));
            }
            int i3 = this.maxFrameSize;
            if (i2 > i3) {
                objArr = new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)};
                str = "FRAME_SIZE_ERROR length > %d: %d";
            } else if ((Integer.MIN_VALUE & i) == 0) {
                Http2.writeMedium(this.sink, i2);
                this.sink.A5q(b & 255);
                this.sink.A5q(b2 & 255);
                this.sink.A5t(i & Integer.MAX_VALUE);
                return;
            } else {
                objArr = new Object[]{Integer.valueOf(i)};
                str = "reserved bit set: %s";
            }
            Http2.access$500(str, objArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        public Writer(KJ kj, boolean z) {
            this.sink = kj;
            this.client = z;
            AnonymousClass98 r1 = new AnonymousClass98();
            this.hpackBuffer = r1;
            this.hpackWriter = new Hpack.Writer(r1);
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
                long j = this.hpackBuffer.A00;
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

    public static void writeMedium(KJ kj, int i) throws IOException {
        kj.A5q((i >>> 16) & 255);
        kj.A5q((i >>> 8) & 255);
        kj.A5q(i & 255);
    }

    @Override // com.squareup.okhttp.internal.framed.Variant
    public FrameReader newReader(KC kc, boolean z) {
        return new Reader(kc, 4096, z);
    }

    @Override // com.squareup.okhttp.internal.framed.Variant
    public FrameWriter newWriter(KJ kj, boolean z) {
        return new Writer(kj, z);
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

    public static int readMedium(KC kc) throws IOException {
        return (kc.readByte() & 255) | ((kc.readByte() & 255) << 16) | ((kc.readByte() & 255) << 8);
    }

    @Override // com.squareup.okhttp.internal.framed.Variant
    public Protocol getProtocol() {
        return Protocol.HTTP_2;
    }
}
