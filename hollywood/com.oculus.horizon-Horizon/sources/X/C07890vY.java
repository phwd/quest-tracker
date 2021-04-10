package X;

import com.squareup.okhttp.internal.framed.Http2;
import java.io.Closeable;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: X.0vY  reason: invalid class name and case insensitive filesystem */
public final class C07890vY implements Closeable {
    public static final Logger A06 = Logger.getLogger(C07950ve.class.getName());
    public int A00 = Http2.INITIAL_MAX_FRAME_SIZE;
    public boolean A01;
    public final C07970vg A02;
    public final AnonymousClass0B3 A03;
    public final AnonymousClass0Lx A04;
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
            AnonymousClass0Lx r1 = this.A04;
            r1.AAF((int) j);
            r1.flush();
        }
    }

    public final synchronized void A03(int i, EnumC08000vj r5) throws IOException {
        if (this.A01) {
            throw new IOException("closed");
        } else if (r5.httpCode != -1) {
            A00(this, i, 4, (byte) 3, (byte) 0);
            AnonymousClass0Lx r1 = this.A04;
            r1.AAF(r5.httpCode);
            r1.flush();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public final synchronized void A04(boolean z, int i, AnonymousClass0B3 r6, int i2) throws IOException {
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

    public static final void A00(C07890vY r5, int i, int i2, byte b, byte b2) throws IOException {
        Object[] objArr;
        String str;
        Logger logger = A06;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(C07950ve.A00(false, i, i2, b, b2));
        }
        int i3 = r5.A00;
        if (i2 > i3) {
            objArr = new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)};
            str = "FRAME_SIZE_ERROR length > %d: %d";
        } else if ((Integer.MIN_VALUE & i) == 0) {
            AnonymousClass0Lx r1 = r5.A04;
            r1.AAC((i2 >>> 16) & 255);
            r1.AAC((i2 >>> 8) & 255);
            r1.AAC(i2 & 255);
            r1.AAC(b & 255);
            r1.AAC(b2 & 255);
            r1.AAF(i & Integer.MAX_VALUE);
            return;
        } else {
            objArr = new Object[]{Integer.valueOf(i)};
            str = "reserved bit set: %s";
        }
        throw new IllegalArgumentException(String.format(Locale.US, str, objArr));
    }

    public C07890vY(AnonymousClass0Lx r3, boolean z) {
        this.A04 = r3;
        this.A05 = z;
        AnonymousClass0B3 r1 = new AnonymousClass0B3();
        this.A03 = r1;
        this.A02 = new C07970vg(r1);
    }
}
