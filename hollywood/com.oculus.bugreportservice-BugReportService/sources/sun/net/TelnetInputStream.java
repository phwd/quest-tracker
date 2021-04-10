package sun.net;

import java.io.FilterInputStream;
import java.io.InputStream;

public class TelnetInputStream extends FilterInputStream {
    public boolean binaryMode = false;
    boolean seenCR = false;
    boolean stickyCRLF = false;

    public TelnetInputStream(InputStream inputStream, boolean z) {
        super(inputStream);
        this.binaryMode = z;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        if (this.binaryMode) {
            return super.read();
        }
        if (this.seenCR) {
            this.seenCR = false;
            return 10;
        }
        int read = super.read();
        if (read != 13) {
            return read;
        }
        int read2 = super.read();
        if (read2 == 0) {
            return 13;
        }
        if (read2 != 10) {
            throw new TelnetProtocolException("misplaced CR in input");
        } else if (!this.stickyCRLF) {
            return 10;
        } else {
            this.seenCR = true;
            return 13;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // java.io.FilterInputStream, java.io.InputStream
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(byte[] r4, int r5, int r6) {
        /*
            r3 = this;
            boolean r0 = r3.binaryMode
            if (r0 == 0) goto L_0x0009
            int r3 = super.read(r4, r5, r6)
            return r3
        L_0x0009:
            r0 = r5
        L_0x000a:
            r1 = -1
            int r6 = r6 + r1
            if (r6 < 0) goto L_0x001c
            int r2 = r3.read()
            if (r2 != r1) goto L_0x0015
            goto L_0x001c
        L_0x0015:
            int r1 = r0 + 1
            byte r2 = (byte) r2
            r4[r0] = r2
            r0 = r1
            goto L_0x000a
        L_0x001c:
            if (r0 <= r5) goto L_0x0020
            int r1 = r0 - r5
        L_0x0020:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.net.TelnetInputStream.read(byte[], int, int):int");
    }
}
