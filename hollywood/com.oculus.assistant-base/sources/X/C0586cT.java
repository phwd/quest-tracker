package X;

import java.io.Closeable;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: X.cT  reason: case insensitive filesystem */
public final class C0586cT implements Closeable {
    public static final Logger A06 = Logger.getLogger(C0582cN.class.getName());
    public int A00 = 16384;
    public boolean A01;
    public final C0580cL A02;
    public final AnonymousClass33 A03;
    public final t6 A04;
    public final boolean A05;

    public final synchronized void A01() {
        if (!this.A01) {
            this.A04.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public final synchronized void A02(int i, long j) {
        if (this.A01) {
            throw new IOException("closed");
        } else if (j == 0 || j > 2147483647L) {
            throw new IllegalArgumentException(String.format(Locale.US, "windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j)));
        } else {
            A00(this, i, 4, (byte) 8, (byte) 0);
            t6 t6Var = this.A04;
            t6Var.A5r((int) j);
            t6Var.flush();
        }
    }

    public final synchronized void A03(int i, cI cIVar) {
        if (this.A01) {
            throw new IOException("closed");
        } else if (cIVar.httpCode != -1) {
            A00(this, i, 4, (byte) 3, (byte) 0);
            t6 t6Var = this.A04;
            t6Var.A5r(cIVar.httpCode);
            t6Var.flush();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public final synchronized void A04(boolean z, int i, AnonymousClass33 r6, int i2) {
        if (!this.A01) {
            byte b = 0;
            if (z) {
                b = (byte) 1;
            }
            A00(this, i, i2, (byte) 0, b);
            if (i2 > 0) {
                this.A04.A5k(r6, (long) i2);
            }
        } else {
            throw new IOException("closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.A01 = true;
        this.A04.close();
    }

    public static final void A00(C0586cT cTVar, int i, int i2, byte b, byte b2) {
        Logger logger = A06;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(C0582cN.A00(false, i, i2, b, b2));
        }
        int i3 = cTVar.A00;
        if (i2 > i3) {
            throw new IllegalArgumentException(String.format(Locale.US, "FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i3), Integer.valueOf(i2)));
        } else if ((Integer.MIN_VALUE & i) == 0) {
            t6 t6Var = cTVar.A04;
            t6Var.A5m((i2 >>> 16) & 255);
            t6Var.A5m((i2 >>> 8) & 255);
            t6Var.A5m(i2 & 255);
            t6Var.A5m(b & 255);
            t6Var.A5m(b2 & 255);
            t6Var.A5r(i & Integer.MAX_VALUE);
        } else {
            throw new IllegalArgumentException(String.format(Locale.US, "reserved bit set: %s", Integer.valueOf(i)));
        }
    }

    public C0586cT(t6 t6Var, boolean z) {
        this.A04 = t6Var;
        this.A05 = z;
        AnonymousClass33 r1 = new AnonymousClass33();
        this.A03 = r1;
        this.A02 = new C0580cL(r1);
    }
}
