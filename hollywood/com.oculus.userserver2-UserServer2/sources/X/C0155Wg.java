package X;

import com.squareup.okhttp.internal.framed.Http2;
import java.io.Closeable;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: X.Wg  reason: case insensitive filesystem */
public final class C0155Wg implements Closeable {
    public static final Logger A06 = Logger.getLogger(C0161Wm.class.getName());
    public int A00 = Http2.INITIAL_MAX_FRAME_SIZE;
    public boolean A01;
    public final C0163Wo A02;
    public final AnonymousClass8k A03;
    public final Du A04;
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
            Du du = this.A04;
            du.A41((int) j);
            du.flush();
        }
    }

    public final synchronized void A03(int i, EnumC0166Wr wr) throws IOException {
        if (this.A01) {
            throw new IOException("closed");
        } else if (wr.httpCode != -1) {
            A00(this, i, 4, (byte) 3, (byte) 0);
            Du du = this.A04;
            du.A41(wr.httpCode);
            du.flush();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public final synchronized void A04(boolean z, int i, AnonymousClass8k r6, int i2) throws IOException {
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

    public static final void A00(C0155Wg wg, int i, int i2, byte b, byte b2) throws IOException {
        Object[] objArr;
        String str;
        Logger logger = A06;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(C0161Wm.A00(false, i, i2, b, b2));
        }
        int i3 = wg.A00;
        if (i2 > i3) {
            objArr = new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)};
            str = "FRAME_SIZE_ERROR length > %d: %d";
        } else if ((Integer.MIN_VALUE & i) == 0) {
            Du du = wg.A04;
            du.A3y((i2 >>> 16) & 255);
            du.A3y((i2 >>> 8) & 255);
            du.A3y(i2 & 255);
            du.A3y(b & 255);
            du.A3y(b2 & 255);
            du.A41(i & Integer.MAX_VALUE);
            return;
        } else {
            objArr = new Object[]{Integer.valueOf(i)};
            str = "reserved bit set: %s";
        }
        throw new IllegalArgumentException(String.format(Locale.US, str, objArr));
    }

    public C0155Wg(Du du, boolean z) {
        this.A04 = du;
        this.A05 = z;
        AnonymousClass8k r1 = new AnonymousClass8k();
        this.A03 = r1;
        this.A02 = new C0163Wo(r1);
    }
}
