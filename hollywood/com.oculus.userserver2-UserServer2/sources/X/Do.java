package X;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class Do implements WG {
    public boolean A00;
    public final Deflater A01;
    public final Du A02;

    @Override // X.WG, java.io.Flushable
    public final void flush() throws IOException {
        A00(true);
        this.A02.flush();
    }

    @Override // X.WG
    public final void write(AnonymousClass8k r10, long j) throws IOException {
        long j2 = j;
        WD.A00(r10.A00, 0, j2);
        while (j2 > 0) {
            WI wi = r10.A01;
            int i = wi.A00;
            int i2 = wi.A01;
            int min = (int) Math.min(j2, (long) (i - i2));
            this.A01.setInput(wi.A06, i2, min);
            A00(false);
            long j3 = (long) min;
            r10.A00 -= j3;
            int i3 = wi.A01 + min;
            wi.A01 = i3;
            if (i3 == wi.A00) {
                r10.A01 = wi.A00();
                WH.A01(wi);
            }
            j2 -= j3;
        }
    }

    public Do(WG wg, Deflater deflater) {
        this.A02 = new AnonymousClass8i(wg);
        this.A01 = deflater;
    }

    @IgnoreJRERequirement
    private void A00(boolean z) throws IOException {
        WI A07;
        int deflate;
        Du du = this.A02;
        AnonymousClass8k A16 = du.A16();
        while (true) {
            A07 = A16.A07(1);
            Deflater deflater = this.A01;
            byte[] bArr = A07.A06;
            int i = A07.A00;
            int i2 = 8192 - i;
            if (z) {
                deflate = deflater.deflate(bArr, i, i2, 2);
            } else {
                deflate = deflater.deflate(bArr, i, i2);
            }
            if (deflate > 0) {
                A07.A00 += deflate;
                A16.A00 += (long) deflate;
                du.A1T();
            } else if (deflater.needsInput()) {
                break;
            }
        }
        if (A07.A01 == A07.A00) {
            A16.A01 = A07.A00();
            WH.A01(A07);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.WG
    public final void close() throws IOException {
        if (!this.A00) {
            Throwable th = null;
            try {
                this.A01.finish();
                A00(false);
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.A01.end();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            try {
                this.A02.close();
            } catch (Throwable th4) {
                if (th == null) {
                    th = th4;
                }
            }
            this.A00 = true;
            if (th != null) {
                throw th;
            }
        }
    }

    @Override // X.WG
    public final WE timeout() {
        return this.A02.timeout();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeflaterSink(");
        sb.append(this.A02);
        sb.append(")");
        return sb.toString();
    }
}
