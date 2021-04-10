package X;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* renamed from: X.0Lv  reason: invalid class name */
public final class AnonymousClass0Lv implements AbstractC07640v7 {
    public boolean A00;
    public final Deflater A01;
    public final AnonymousClass0Lx A02;

    @Override // X.AbstractC07640v7, java.io.Flushable
    public final void flush() throws IOException {
        A00(true);
        this.A02.flush();
    }

    @Override // X.AbstractC07640v7
    public final void write(AnonymousClass0B3 r10, long j) throws IOException {
        long j2 = j;
        C07610v4.A00(r10.A00, 0, j2);
        while (j2 > 0) {
            C07660v9 r6 = r10.A01;
            int i = r6.A01;
            int i2 = r6.A02;
            int min = (int) Math.min(j2, (long) (i - i2));
            this.A01.setInput(r6.A06, i2, min);
            A00(false);
            long j3 = (long) min;
            r10.A00 -= j3;
            int i3 = r6.A02 + min;
            r6.A02 = i3;
            if (i3 == r6.A01) {
                r10.A01 = r6.A00();
                C07650v8.A01(r6);
            }
            j2 -= j3;
        }
    }

    public AnonymousClass0Lv(AbstractC07640v7 r2, Deflater deflater) {
        this.A02 = new C00570Av(r2);
        this.A01 = deflater;
    }

    @IgnoreJRERequirement
    private void A00(boolean z) throws IOException {
        C07660v9 A07;
        int deflate;
        AnonymousClass0Lx r6 = this.A02;
        AnonymousClass0B3 A1V = r6.A1V();
        while (true) {
            A07 = A1V.A07(1);
            Deflater deflater = this.A01;
            byte[] bArr = A07.A06;
            int i = A07.A01;
            int i2 = 8192 - i;
            if (z) {
                deflate = deflater.deflate(bArr, i, i2, 2);
            } else {
                deflate = deflater.deflate(bArr, i, i2);
            }
            if (deflate > 0) {
                A07.A01 += deflate;
                A1V.A00 += (long) deflate;
                r6.A2P();
            } else if (deflater.needsInput()) {
                break;
            }
        }
        if (A07.A02 == A07.A01) {
            A1V.A01 = A07.A00();
            C07650v8.A01(A07);
        }
    }

    @Override // X.AbstractC07640v7, java.io.Closeable, java.lang.AutoCloseable
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

    @Override // X.AbstractC07640v7
    public final C07620v5 timeout() {
        return this.A02.timeout();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeflaterSink(");
        sb.append(this.A02);
        sb.append(")");
        return sb.toString();
    }
}
