package X;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* renamed from: X.ct  reason: case insensitive filesystem */
public class C0610ct {
    public static final C0610ct A03 = new C1101sX();
    public long A00;
    public boolean A01;
    public long A02;

    public final long A00() {
        if (this instanceof t3) {
            return ((t3) this).A00.A00();
        }
        if (this.A01) {
            return this.A02;
        }
        throw new IllegalStateException("No deadline");
    }

    public final C0610ct A01() {
        if (this instanceof t3) {
            return ((t3) this).A00.A01();
        }
        this.A01 = false;
        return this;
    }

    public final C0610ct A02() {
        if (this instanceof t3) {
            return ((t3) this).A00.A02();
        }
        this.A00 = 0;
        return this;
    }

    public final C0610ct A03(long j) {
        if (!(this instanceof C1101sX)) {
            if (this instanceof t3) {
                return ((t3) this).A00.A03(j);
            }
            this.A01 = true;
            this.A02 = j;
        }
        return this;
    }

    public final C0610ct A04(long j, TimeUnit timeUnit) {
        if (!(this instanceof C1101sX)) {
            if (this instanceof t3) {
                return ((t3) this).A00.A04(j, timeUnit);
            }
            if (j < 0) {
                throw new IllegalArgumentException(AnonymousClass08.A03("timeout < 0: ", j));
            } else if (timeUnit != null) {
                this.A00 = timeUnit.toNanos(j);
            } else {
                throw new IllegalArgumentException("unit == null");
            }
        }
        return this;
    }

    public final void A05() {
        if (this instanceof C1101sX) {
            return;
        }
        if (this instanceof t3) {
            ((t3) this).A00.A05();
        } else if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.A01 && this.A02 - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public final boolean A06() {
        if (!(this instanceof t3)) {
            return this.A01;
        }
        return ((t3) this).A00.A06();
    }
}
