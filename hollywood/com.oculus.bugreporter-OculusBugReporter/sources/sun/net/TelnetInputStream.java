package sun.net;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TelnetInputStream extends FilterInputStream {
    public boolean binaryMode = false;
    boolean seenCR = false;
    boolean stickyCRLF = false;

    public TelnetInputStream(InputStream fd, boolean binary) {
        super(fd);
        this.binaryMode = binary;
    }

    public void setStickyCRLF(boolean on) {
        this.stickyCRLF = on;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.binaryMode) {
            return super.read();
        }
        if (this.seenCR) {
            this.seenCR = false;
            return 10;
        }
        int c = super.read();
        if (c != 13) {
            return c;
        }
        int read = super.read();
        if (read == 0) {
            return 13;
        }
        if (read != 10) {
            throw new TelnetProtocolException("misplaced CR in input");
        } else if (!this.stickyCRLF) {
            return 10;
        } else {
            this.seenCR = true;
            return 13;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bytes) throws IOException {
        return read(bytes, 0, bytes.length);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // java.io.FilterInputStream, java.io.InputStream
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(byte[] r5, int r6, int r7) throws java.io.IOException {
        /*
            r4 = this;
            boolean r0 = r4.binaryMode
            if (r0 == 0) goto L_0x0009
            int r0 = super.read(r5, r6, r7)
            return r0
        L_0x0009:
            r0 = r6
        L_0x000a:
            r1 = -1
            int r7 = r7 + r1
            if (r7 < 0) goto L_0x001c
            int r2 = r4.read()
            if (r2 != r1) goto L_0x0015
            goto L_0x001c
        L_0x0015:
            int r1 = r6 + 1
            byte r3 = (byte) r2
            r5[r6] = r3
            r6 = r1
            goto L_0x000a
        L_0x001c:
            if (r6 <= r0) goto L_0x0020
            int r1 = r6 - r0
        L_0x0020:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.net.TelnetInputStream.read(byte[], int, int):int");
    }
}
