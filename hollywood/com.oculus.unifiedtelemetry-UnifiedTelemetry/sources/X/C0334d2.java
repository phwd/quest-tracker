package X;

import com.squareup.okhttp.internal.framed.Http2;
import java.io.Closeable;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: X.d2  reason: case insensitive filesystem */
public final class C0334d2 implements Closeable {
    public static final Logger A06 = Logger.getLogger(C0340d8.class.getName());
    public int A00 = Http2.INITIAL_MAX_FRAME_SIZE;
    public boolean A01;
    public final dA A02;
    public final AnonymousClass98 A03;
    public final KJ A04;
    public final boolean A05;

    public final synchronized void A01() throws IOException {
        if (!this.A01) {
            this.A04.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void A02(int i, long j) throws IOException {
        if (this.A01) {
            throw new IOException("closed");
        } else if (j == 0 || j > 2147483647L) {
            throw new IllegalArgumentException(String.format(Locale.US, "windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j)));
        } else {
            A00(this, i, 4, (byte) 8, (byte) 0);
            KJ kj = this.A04;
            kj.A5t((int) j);
            kj.flush();
        }
    }

    public final synchronized void A03(int i, dD dDVar) throws IOException {
        if (this.A01) {
            throw new IOException("closed");
        } else if (dDVar.httpCode != -1) {
            A00(this, i, 4, (byte) 3, (byte) 0);
            KJ kj = this.A04;
            kj.A5t(dDVar.httpCode);
            kj.flush();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public final synchronized void A04(boolean z, int i, AnonymousClass98 r6, int i2) throws IOException {
        if (!this.A01) {
            byte b = 0;
            if (z) {
                b = (byte) 1;
            }
            A00(this, i, i2, (byte) 0, b);
            if (i2 > 0) {
                this.A04.write(r6, (long) i2);
            }
        } else {
            throw new IOException("closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        this.A01 = true;
        this.A04.close();
    }

    public static final void A00(C0334d2 d2Var, int i, int i2, byte b, byte b2) throws IOException {
        Object[] objArr;
        String str;
        Logger logger = A06;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(C0340d8.A00(false, i, i2, b, b2));
        }
        int i3 = d2Var.A00;
        if (i2 > i3) {
            objArr = new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)};
            str = "FRAME_SIZE_ERROR length > %d: %d";
        } else if ((Integer.MIN_VALUE & i) == 0) {
            KJ kj = d2Var.A04;
            kj.A5q((i2 >>> 16) & 255);
            kj.A5q((i2 >>> 8) & 255);
            kj.A5q(i2 & 255);
            kj.A5q(b & 255);
            kj.A5q(b2 & 255);
            kj.A5t(i & Integer.MAX_VALUE);
            return;
        } else {
            objArr = new Object[]{Integer.valueOf(i)};
            str = "reserved bit set: %s";
        }
        throw new IllegalArgumentException(String.format(Locale.US, str, objArr));
    }

    public C0334d2(KJ kj, boolean z) {
        this.A04 = kj;
        this.A05 = z;
        AnonymousClass98 r1 = new AnonymousClass98();
        this.A03 = r1;
        this.A02 = new dA(r1);
    }
}
