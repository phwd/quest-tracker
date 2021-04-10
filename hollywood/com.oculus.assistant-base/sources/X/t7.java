package X;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

public class t7 extends C0610ct {
    public static t7 A03;
    public static final long A04;
    public static final long A05;
    public long A00;
    public t7 A01;
    public boolean A02;

    static {
        long millis = TimeUnit.SECONDS.toMillis(60);
        A04 = millis;
        A05 = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    public final IOException A07(IOException iOException) {
        IOException socketTimeoutException;
        if ((this instanceof AnonymousClass31) || (this instanceof AnonymousClass34)) {
            socketTimeoutException = new SocketTimeoutException("timeout");
        } else {
            socketTimeoutException = new InterruptedIOException("timeout");
        }
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x006f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A08() {
        /*
        // Method dump skipped, instructions count: 127
        */
        throw new UnsupportedOperationException("Method not decompiled: X.t7.A08():void");
    }

    public final boolean A0A() {
        if (!this.A02) {
            return false;
        }
        this.A02 = false;
        synchronized (t7.class) {
            t7 t7Var = A03;
            while (t7Var != null) {
                t7 t7Var2 = t7Var.A01;
                if (t7Var2 == this) {
                    t7Var.A01 = this.A01;
                    this.A01 = null;
                    return false;
                }
                t7Var = t7Var2;
            }
            return true;
        }
    }

    public final void A09(boolean z) {
        if (A0A() && z) {
            throw A07(null);
        }
    }
}
