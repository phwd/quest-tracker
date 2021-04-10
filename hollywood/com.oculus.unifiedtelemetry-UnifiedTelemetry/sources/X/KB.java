package X;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class KB implements AbstractC0313cc {
    public boolean A00;
    public final Deflater A01;
    public final KJ A02;

    @Override // X.AbstractC0313cc, java.io.Flushable
    public final void flush() throws IOException {
        A00(this, true);
        this.A02.flush();
    }

    @Override // X.AbstractC0313cc
    public final void write(AnonymousClass98 r10, long j) throws IOException {
        long j2 = j;
        C0311cZ.A00(r10.A00, 0, j2);
        while (j2 > 0) {
            C0315ce ceVar = r10.A01;
            int i = ceVar.A00;
            int i2 = ceVar.A01;
            int min = (int) Math.min(j2, (long) (i - i2));
            this.A01.setInput(ceVar.A06, i2, min);
            A00(this, false);
            long j3 = (long) min;
            r10.A00 -= j3;
            int i3 = ceVar.A01 + min;
            ceVar.A01 = i3;
            if (i3 == ceVar.A00) {
                r10.A01 = ceVar.A00();
                C0314cd.A01(ceVar);
            }
            j2 -= j3;
        }
    }

    @IgnoreJRERequirement
    public static void A00(KB kb, boolean z) throws IOException {
        C0315ce A07;
        int deflate;
        KJ kj = kb.A02;
        AnonymousClass98 A1V = kj.A1V();
        while (true) {
            A07 = A1V.A07(1);
            Deflater deflater = kb.A01;
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
                A1V.A00 += (long) deflate;
                kj.A1v();
            } else if (deflater.needsInput()) {
                break;
            }
        }
        if (A07.A01 == A07.A00) {
            A1V.A01 = A07.A00();
            C0314cd.A01(A07);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0313cc
    public final void close() throws IOException {
        if (!this.A00) {
            Throwable th = null;
            try {
                this.A01.finish();
                A00(this, false);
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

    @Override // X.AbstractC0313cc
    public final ca timeout() {
        return this.A02.timeout();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeflaterSink(");
        sb.append(this.A02);
        sb.append(")");
        return sb.toString();
    }

    public KB(KJ kj, Deflater deflater) {
        this.A02 = kj;
        this.A01 = deflater;
    }
}
