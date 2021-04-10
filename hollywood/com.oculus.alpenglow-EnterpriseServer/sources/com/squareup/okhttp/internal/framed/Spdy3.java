package com.squareup.okhttp.internal.framed;

import X.AnonymousClass006;
import X.AnonymousClass0HP;
import X.AnonymousClass0HR;
import X.AnonymousClass0Ob;
import X.AnonymousClass0Od;
import X.AnonymousClass0Oe;
import X.C04610h7;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.framed.FrameReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.util.List;
import java.util.zip.Deflater;

public final class Spdy3 implements Variant {
    public static final byte[] DICTIONARY;
    public static final int FLAG_FIN = 1;
    public static final int FLAG_UNIDIRECTIONAL = 2;
    public static final int TYPE_DATA = 0;
    public static final int TYPE_GOAWAY = 7;
    public static final int TYPE_HEADERS = 8;
    public static final int TYPE_PING = 6;
    public static final int TYPE_RST_STREAM = 3;
    public static final int TYPE_SETTINGS = 4;
    public static final int TYPE_SYN_REPLY = 2;
    public static final int TYPE_SYN_STREAM = 1;
    public static final int TYPE_WINDOW_UPDATE = 9;
    public static final int VERSION = 3;

    public static final class Reader implements FrameReader {
        public final boolean client;
        public final NameValueBlockReader headerBlockReader;
        public final AnonymousClass0Od source;

        private void readPing(FrameReader.Handler handler, int i, int i2) throws IOException {
            boolean z = true;
            if (i2 == 4) {
                int readInt = this.source.readInt();
                boolean z2 = this.client;
                boolean z3 = false;
                if ((readInt & 1) == 1) {
                    z3 = true;
                }
                if (z2 != z3) {
                    z = false;
                }
                handler.ping(z, readInt, 0);
                return;
            }
            ioException("TYPE_PING length: %d != 4", Integer.valueOf(i2));
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader
        public boolean nextFrame(FrameReader.Handler handler) throws IOException {
            boolean z = false;
            try {
                int readInt = this.source.readInt();
                int readInt2 = this.source.readInt();
                boolean z2 = false;
                if ((Integer.MIN_VALUE & readInt) != 0) {
                    z2 = true;
                }
                int i = (-16777216 & readInt2) >>> 24;
                int i2 = readInt2 & 16777215;
                if (z2) {
                    int i3 = (2147418112 & readInt) >>> 16;
                    int i4 = readInt & 65535;
                    if (i3 == 3) {
                        switch (i4) {
                            case 1:
                                readSynStream(handler, i, i2);
                                return true;
                            case 2:
                                readSynReply(handler, i, i2);
                                return true;
                            case 3:
                                readRstStream(handler, i, i2);
                                return true;
                            case 4:
                                readSettings(handler, i, i2);
                                return true;
                            case 5:
                            default:
                                this.source.A8T((long) i2);
                                return true;
                            case 6:
                                readPing(handler, i, i2);
                                return true;
                            case 7:
                                readGoAway(handler, i, i2);
                                return true;
                            case 8:
                                readHeaders(handler, i, i2);
                                return true;
                            case 9:
                                readWindowUpdate(handler, i, i2);
                                return true;
                        }
                    } else {
                        throw new ProtocolException(AnonymousClass006.A01("version != 3: ", i3));
                    }
                } else {
                    int i5 = readInt & Integer.MAX_VALUE;
                    if ((i & 1) != 0) {
                        z = true;
                    }
                    handler.data(z, i5, this.source, i2);
                    return true;
                }
            } catch (IOException unused) {
                return false;
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameReader
        public void readConnectionPreface() {
        }

        private void readGoAway(FrameReader.Handler handler, int i, int i2) throws IOException {
            Object[] objArr;
            String str;
            if (i2 == 8) {
                int readInt = this.source.readInt() & Integer.MAX_VALUE;
                int readInt2 = this.source.readInt();
                ErrorCode fromSpdyGoAway = ErrorCode.fromSpdyGoAway(readInt2);
                if (fromSpdyGoAway != null) {
                    handler.goAway(readInt, fromSpdyGoAway, C04610h7.A03);
                    return;
                } else {
                    objArr = new Object[]{Integer.valueOf(readInt2)};
                    str = "TYPE_GOAWAY unexpected error code: %d";
                }
            } else {
                objArr = new Object[]{Integer.valueOf(i2)};
                str = "TYPE_GOAWAY length: %d != 8";
            }
            ioException(str, objArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        private void readHeaders(FrameReader.Handler handler, int i, int i2) throws IOException {
            handler.headers(false, false, this.source.readInt() & Integer.MAX_VALUE, -1, this.headerBlockReader.readNameValueBlock(i2 - 4), HeadersMode.SPDY_HEADERS);
        }

        private void readRstStream(FrameReader.Handler handler, int i, int i2) throws IOException {
            Object[] objArr;
            String str;
            if (i2 == 8) {
                int readInt = this.source.readInt() & Integer.MAX_VALUE;
                int readInt2 = this.source.readInt();
                ErrorCode fromSpdy3Rst = ErrorCode.fromSpdy3Rst(readInt2);
                if (fromSpdy3Rst != null) {
                    handler.rstStream(readInt, fromSpdy3Rst);
                    return;
                } else {
                    objArr = new Object[]{Integer.valueOf(readInt2)};
                    str = "TYPE_RST_STREAM unexpected error code: %d";
                }
            } else {
                objArr = new Object[]{Integer.valueOf(i2)};
                str = "TYPE_RST_STREAM length: %d != 8";
            }
            ioException(str, objArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        private void readSettings(FrameReader.Handler handler, int i, int i2) throws IOException {
            int readInt = this.source.readInt();
            boolean z = false;
            if (i2 == (readInt << 3) + 4) {
                Settings settings = new Settings();
                for (int i3 = 0; i3 < readInt; i3++) {
                    int readInt2 = this.source.readInt();
                    int i4 = readInt2 & 16777215;
                    settings.set(i4, (-16777216 & readInt2) >>> 24, this.source.readInt());
                }
                if ((i & 1) != 0) {
                    z = true;
                }
                handler.settings(z, settings);
                return;
            }
            ioException("TYPE_SETTINGS length: %d != 4 + 8 * %d", Integer.valueOf(i2), Integer.valueOf(readInt));
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        private void readSynReply(FrameReader.Handler handler, int i, int i2) throws IOException {
            int readInt = this.source.readInt() & Integer.MAX_VALUE;
            List<Header> readNameValueBlock = this.headerBlockReader.readNameValueBlock(i2 - 4);
            boolean z = false;
            if ((i & 1) != 0) {
                z = true;
            }
            handler.headers(false, z, readInt, -1, readNameValueBlock, HeadersMode.SPDY_REPLY);
        }

        private void readSynStream(FrameReader.Handler handler, int i, int i2) throws IOException {
            int readInt = this.source.readInt() & Integer.MAX_VALUE;
            int readInt2 = this.source.readInt() & Integer.MAX_VALUE;
            this.source.readShort();
            List<Header> readNameValueBlock = this.headerBlockReader.readNameValueBlock(i2 - 10);
            boolean z = false;
            if ((i & 1) != 0) {
                z = true;
            }
            boolean z2 = false;
            if ((i & 2) != 0) {
                z2 = true;
            }
            handler.headers(z2, z, readInt, readInt2, readNameValueBlock, HeadersMode.SPDY_SYN_STREAM);
        }

        private void readWindowUpdate(FrameReader.Handler handler, int i, int i2) throws IOException {
            Object[] objArr;
            String str;
            if (i2 == 8) {
                int readInt = this.source.readInt() & Integer.MAX_VALUE;
                long readInt2 = (long) (this.source.readInt() & Integer.MAX_VALUE);
                if (readInt2 != 0) {
                    handler.windowUpdate(readInt, readInt2);
                    return;
                } else {
                    objArr = new Object[]{Long.valueOf(readInt2)};
                    str = "windowSizeIncrement was 0";
                }
            } else {
                objArr = new Object[]{Integer.valueOf(i2)};
                str = "TYPE_WINDOW_UPDATE length: %d != 8";
            }
            ioException(str, objArr);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.headerBlockReader.close();
        }

        public Reader(AnonymousClass0Od r2, boolean z) {
            this.source = r2;
            this.headerBlockReader = new NameValueBlockReader(r2);
            this.client = z;
        }

        public static IOException ioException(String str, Object... objArr) throws IOException {
            throw new IOException(String.format(str, objArr));
        }
    }

    public static final class Writer implements FrameWriter {
        public final boolean client;
        public boolean closed;
        public final AnonymousClass0HR headerBlockBuffer;
        public final AnonymousClass0Oe headerBlockOut;
        public final AnonymousClass0Oe sink;

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public void ackSettings(Settings settings) {
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            this.closed = true;
            Util.closeAll(this.sink, this.headerBlockOut);
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void connectionPreface() {
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void data(boolean z, int i, AnonymousClass0HR r4, int i2) throws IOException {
            int i3 = 0;
            if (z) {
                i3 = 1;
            }
            sendDataFrame(i, i3, r4, i2);
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
            } else if (errorCode.spdyGoAwayCode != -1) {
                this.sink.A97(-2147287033);
                this.sink.A97(8);
                this.sink.A97(i);
                this.sink.A97(errorCode.spdyGoAwayCode);
                this.sink.flush();
            } else {
                throw new IllegalArgumentException("errorCode.spdyGoAwayCode == -1");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void headers(int i, List<Header> list) throws IOException {
            if (!this.closed) {
                writeNameValueBlockToBuffer(list);
                this.sink.A97(-2147287032);
                this.sink.A97((((int) (this.headerBlockBuffer.A00 + 4)) & 16777215) | 0);
                this.sink.A97(i & Integer.MAX_VALUE);
                this.sink.A8y(this.headerBlockBuffer);
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public int maxDataLength() {
            return 16383;
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void ping(boolean z, int i, int i2) throws IOException {
            if (!this.closed) {
                boolean z2 = this.client;
                boolean z3 = false;
                boolean z4 = false;
                if ((i & 1) == 1) {
                    z4 = true;
                }
                if (z2 != z4) {
                    z3 = true;
                }
                if (z == z3) {
                    this.sink.A97(-2147287034);
                    this.sink.A97(4);
                    this.sink.A97(i);
                    this.sink.flush();
                } else {
                    throw new IllegalArgumentException("payload != reply");
                }
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public void pushPromise(int i, int i2, List<Header> list) throws IOException {
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void rstStream(int i, ErrorCode errorCode) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            } else if (errorCode.spdyRstCode != -1) {
                this.sink.A97(-2147287037);
                this.sink.A97(8);
                this.sink.A97(i & Integer.MAX_VALUE);
                this.sink.A97(errorCode.spdyRstCode);
                this.sink.flush();
            } else {
                throw new IllegalArgumentException();
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void settings(Settings settings) throws IOException {
            if (!this.closed) {
                int bitCount = Integer.bitCount(settings.set);
                this.sink.A97(-2147287036);
                int i = 0;
                this.sink.A97((((bitCount << 3) + 4) & 16777215) | 0);
                this.sink.A97(bitCount);
                do {
                    if (settings.isSet(i)) {
                        this.sink.A97(((settings.flags(i) & 255) << 24) | (i & 16777215));
                        this.sink.A97(settings.values[i]);
                    }
                    i++;
                } while (i <= 10);
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void synReply(boolean z, int i, List<Header> list) throws IOException {
            if (!this.closed) {
                writeNameValueBlockToBuffer(list);
                int i2 = 0;
                if (z) {
                    i2 = 1;
                }
                this.sink.A97(-2147287038);
                this.sink.A97(((i2 & 255) << 24) | (((int) (this.headerBlockBuffer.A00 + 4)) & 16777215));
                this.sink.A97(i & Integer.MAX_VALUE);
                this.sink.A8y(this.headerBlockBuffer);
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void synStream(boolean z, boolean z2, int i, int i2, List<Header> list) throws IOException {
            if (!this.closed) {
                writeNameValueBlockToBuffer(list);
                int i3 = (int) (this.headerBlockBuffer.A00 + 10);
                int i4 = 0;
                if (z2) {
                    i4 = 2;
                }
                this.sink.A97(-2147287039);
                this.sink.A97(((((z ? 1 : 0) | i4) & 255) << 24) | (i3 & 16777215));
                this.sink.A97(i & Integer.MAX_VALUE);
                this.sink.A97(Integer.MAX_VALUE & i2);
                this.sink.A9C(0);
                this.sink.A8y(this.headerBlockBuffer);
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // com.squareup.okhttp.internal.framed.FrameWriter
        public synchronized void windowUpdate(int i, long j) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            } else if (j == 0 || j > 2147483647L) {
                throw new IllegalArgumentException(AnonymousClass006.A04("windowSizeIncrement must be between 1 and 0x7fffffff: ", j));
            } else {
                this.sink.A97(-2147287031);
                this.sink.A97(8);
                this.sink.A97(i);
                this.sink.A97((int) j);
                this.sink.flush();
            }
        }

        private void writeNameValueBlockToBuffer(List<Header> list) throws IOException {
            this.headerBlockOut.A97(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                C04610h7 r2 = list.get(i).name;
                this.headerBlockOut.A97(r2.A07());
                this.headerBlockOut.A8v(r2);
                C04610h7 r22 = list.get(i).value;
                this.headerBlockOut.A97(r22.A07());
                this.headerBlockOut.A8v(r22);
            }
            this.headerBlockOut.flush();
        }

        public void sendDataFrame(int i, int i2, AnonymousClass0HR r8, int i3) throws IOException {
            if (!this.closed) {
                long j = (long) i3;
                if (j <= 16777215) {
                    this.sink.A97(i & Integer.MAX_VALUE);
                    this.sink.A97(((i2 & 255) << 24) | (16777215 & i3));
                    if (i3 > 0) {
                        this.sink.write(r8, j);
                        return;
                    }
                    return;
                }
                throw new IllegalArgumentException(AnonymousClass006.A01("FRAME_TOO_LARGE max size is 16Mib: ", i3));
            }
            throw new IOException("closed");
        }

        public Writer(AnonymousClass0Oe r4, boolean z) {
            this.sink = r4;
            this.client = z;
            Deflater deflater = new Deflater();
            deflater.setDictionary(Spdy3.DICTIONARY);
            AnonymousClass0HR r0 = new AnonymousClass0HR();
            this.headerBlockBuffer = r0;
            this.headerBlockOut = new AnonymousClass0HP(new AnonymousClass0Ob(r0, deflater));
        }
    }

    static {
        try {
            DICTIONARY = "\u0000\u0000\u0000\u0007options\u0000\u0000\u0000\u0004head\u0000\u0000\u0000\u0004post\u0000\u0000\u0000\u0003put\u0000\u0000\u0000\u0006delete\u0000\u0000\u0000\u0005trace\u0000\u0000\u0000\u0006accept\u0000\u0000\u0000\u000eaccept-charset\u0000\u0000\u0000\u000faccept-encoding\u0000\u0000\u0000\u000faccept-language\u0000\u0000\u0000\raccept-ranges\u0000\u0000\u0000\u0003age\u0000\u0000\u0000\u0005allow\u0000\u0000\u0000\rauthorization\u0000\u0000\u0000\rcache-control\u0000\u0000\u0000\nconnection\u0000\u0000\u0000\fcontent-base\u0000\u0000\u0000\u0010content-encoding\u0000\u0000\u0000\u0010content-language\u0000\u0000\u0000\u000econtent-length\u0000\u0000\u0000\u0010content-location\u0000\u0000\u0000\u000bcontent-md5\u0000\u0000\u0000\rcontent-range\u0000\u0000\u0000\fcontent-type\u0000\u0000\u0000\u0004date\u0000\u0000\u0000\u0004etag\u0000\u0000\u0000\u0006expect\u0000\u0000\u0000\u0007expires\u0000\u0000\u0000\u0004from\u0000\u0000\u0000\u0004host\u0000\u0000\u0000\bif-match\u0000\u0000\u0000\u0011if-modified-since\u0000\u0000\u0000\rif-none-match\u0000\u0000\u0000\bif-range\u0000\u0000\u0000\u0013if-unmodified-since\u0000\u0000\u0000\rlast-modified\u0000\u0000\u0000\blocation\u0000\u0000\u0000\fmax-forwards\u0000\u0000\u0000\u0006pragma\u0000\u0000\u0000\u0012proxy-authenticate\u0000\u0000\u0000\u0013proxy-authorization\u0000\u0000\u0000\u0005range\u0000\u0000\u0000\u0007referer\u0000\u0000\u0000\u000bretry-after\u0000\u0000\u0000\u0006server\u0000\u0000\u0000\u0002te\u0000\u0000\u0000\u0007trailer\u0000\u0000\u0000\u0011transfer-encoding\u0000\u0000\u0000\u0007upgrade\u0000\u0000\u0000\nuser-agent\u0000\u0000\u0000\u0004vary\u0000\u0000\u0000\u0003via\u0000\u0000\u0000\u0007warning\u0000\u0000\u0000\u0010www-authenticate\u0000\u0000\u0000\u0006method\u0000\u0000\u0000\u0003get\u0000\u0000\u0000\u0006status\u0000\u0000\u0000\u0006200 OK\u0000\u0000\u0000\u0007version\u0000\u0000\u0000\bHTTP/1.1\u0000\u0000\u0000\u0003url\u0000\u0000\u0000\u0006public\u0000\u0000\u0000\nset-cookie\u0000\u0000\u0000\nkeep-alive\u0000\u0000\u0000\u0006origin100101201202205206300302303304305306307402405406407408409410411412413414415416417502504505203 Non-Authoritative Information204 No Content301 Moved Permanently400 Bad Request401 Unauthorized403 Forbidden404 Not Found500 Internal Server Error501 Not Implemented503 Service UnavailableJan Feb Mar Apr May Jun Jul Aug Sept Oct Nov Dec 00:00:00 Mon, Tue, Wed, Thu, Fri, Sat, Sun, GMTchunked,text/html,image/png,image/jpg,image/gif,application/xml,application/xhtml+xml,text/plain,text/javascript,publicprivatemax-age=gzip,deflate,sdchcharset=utf-8charset=iso-8859-1,utf-,*,enq=0.".getBytes(Util.UTF_8.name());
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError();
        }
    }

    @Override // com.squareup.okhttp.internal.framed.Variant
    public Protocol getProtocol() {
        return Protocol.SPDY_3;
    }

    @Override // com.squareup.okhttp.internal.framed.Variant
    public FrameReader newReader(AnonymousClass0Od r2, boolean z) {
        return new Reader(r2, z);
    }

    @Override // com.squareup.okhttp.internal.framed.Variant
    public FrameWriter newWriter(AnonymousClass0Oe r2, boolean z) {
        return new Writer(r2, z);
    }
}
