package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class GzipSource implements Source {
    public static final byte FCOMMENT = 4;
    public static final byte FEXTRA = 2;
    public static final byte FHCRC = 1;
    public static final byte FNAME = 3;
    public static final byte SECTION_BODY = 1;
    public static final byte SECTION_DONE = 3;
    public static final byte SECTION_HEADER = 0;
    public static final byte SECTION_TRAILER = 2;
    public final CRC32 crc = new CRC32();
    public final Inflater inflater;
    public final InflaterSource inflaterSource;
    public int section = 0;
    public final BufferedSource source;

    private void checkEqual(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }

    private void consumeHeader() throws IOException {
        this.source.require(10);
        byte b = this.source.buffer().getByte(3);
        boolean z = false;
        if (((b >> 1) & 1) == 1) {
            z = true;
            updateCrc(this.source.buffer(), 0, 10);
        }
        checkEqual("ID1ID2", 8075, this.source.readShort());
        this.source.skip(8);
        if (((b >> 2) & 1) == 1) {
            this.source.require(2);
            if (z) {
                updateCrc(this.source.buffer(), 0, 2);
            }
            long readShortLe = (long) this.source.buffer().readShortLe();
            this.source.require(readShortLe);
            if (z) {
                updateCrc(this.source.buffer(), 0, readShortLe);
            }
            this.source.skip(readShortLe);
        }
        if (((b >> 3) & 1) == 1) {
            long indexOf = this.source.indexOf((byte) 0);
            if (indexOf != -1) {
                if (z) {
                    updateCrc(this.source.buffer(), 0, indexOf + 1);
                }
                this.source.skip(indexOf + 1);
            } else {
                throw new EOFException();
            }
        }
        if (((b >> 4) & 1) == 1) {
            long indexOf2 = this.source.indexOf((byte) 0);
            if (indexOf2 != -1) {
                if (z) {
                    updateCrc(this.source.buffer(), 0, indexOf2 + 1);
                }
                this.source.skip(indexOf2 + 1);
            } else {
                throw new EOFException();
            }
        }
        if (z) {
            checkEqual("FHCRC", this.source.readShortLe(), (short) ((int) this.crc.getValue()));
            this.crc.reset();
        }
    }

    private void consumeTrailer() throws IOException {
        checkEqual("CRC", this.source.readIntLe(), (int) this.crc.getValue());
        checkEqual("ISIZE", this.source.readIntLe(), (int) this.inflater.getBytesWritten());
    }

    private void updateCrc(Buffer buffer, long j, long j2) {
        Segment segment = buffer.head;
        while (j >= ((long) (segment.limit - segment.pos))) {
            j -= (long) (segment.limit - segment.pos);
            segment = segment.next;
        }
        while (j2 > 0) {
            int i = (int) (((long) segment.pos) + j);
            int min = (int) Math.min((long) (segment.limit - i), j2);
            this.crc.update(segment.data, i, min);
            j2 -= (long) min;
            segment = segment.next;
            j = 0;
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inflaterSource.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        if (r4 == 2) goto L_0x0032;
     */
    @Override // okio.Source
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long read(okio.Buffer r12, long r13) throws java.io.IOException {
        /*
            r11 = this;
            r1 = 0
            int r0 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r0 < 0) goto L_0x0049
            int r0 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x000b
            return r1
        L_0x000b:
            r5 = r11
            int r4 = r11.section
            r0 = 1
            if (r4 != 0) goto L_0x0017
            r11.consumeHeader()
            r11.section = r0
            r4 = 1
        L_0x0017:
            r2 = -1
            r1 = 2
            if (r4 != r0) goto L_0x0030
            r6 = r12
            long r7 = r12.size
            okio.InflaterSource r0 = r11.inflaterSource
            long r9 = r0.read(r12, r13)
            int r0 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x002d
            r5.updateCrc(r6, r7, r9)
            return r9
        L_0x002d:
            r11.section = r1
            goto L_0x0032
        L_0x0030:
            if (r4 != r1) goto L_0x0048
        L_0x0032:
            r11.consumeTrailer()
            r0 = 3
            r11.section = r0
            okio.BufferedSource r0 = r11.source
            boolean r0 = r0.exhausted()
            if (r0 != 0) goto L_0x0048
            java.lang.String r1 = "gzip finished without exhausting source"
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x0048:
            return r2
        L_0x0049:
            java.lang.String r0 = "byteCount < 0: "
            java.lang.String r1 = X.AnonymousClass006.A06(r0, r13)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.GzipSource.read(okio.Buffer, long):long");
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public GzipSource(Source source2) {
        if (source2 != null) {
            Inflater inflater2 = new Inflater(true);
            this.inflater = inflater2;
            BufferedSource buffer = Okio.buffer(source2);
            this.source = buffer;
            this.inflaterSource = new InflaterSource(buffer, inflater2);
            return;
        }
        throw new IllegalArgumentException("source == null");
    }
}
