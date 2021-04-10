package X;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.common.time.RealtimeSinceBootClock;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0vI  reason: invalid class name */
public final class AnonymousClass0vI implements AbstractC07660vk {
    public double A00;
    public long A01;
    public final int A02;
    public final long A03;
    public final RealtimeSinceBootClock A04;

    @Override // X.AbstractC07660vk
    public final synchronized boolean A1h() {
        boolean z;
        RealtimeSinceBootClock realtimeSinceBootClock = this.A04;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.A01 = elapsedRealtime;
        double d = this.A00;
        double d2 = (double) (elapsedRealtime - this.A01);
        double d3 = (double) this.A02;
        double d4 = (double) this.A03;
        Double.isNaN(d3);
        Double.isNaN(d4);
        Double.isNaN(d2);
        this.A00 = d + ((d2 * (d3 / d4)) / 1000.0d);
        if (this.A00 > ((double) this.A02)) {
            this.A00 = (double) this.A02;
        }
        if (this.A00 < 1.0d) {
            z = false;
        } else {
            this.A00 -= 1.0d;
            z = true;
        }
        return z;
    }

    public AnonymousClass0vI(RealtimeSinceBootClock realtimeSinceBootClock, int i, long j) {
        this.A04 = realtimeSinceBootClock;
        this.A02 = i;
        this.A03 = j;
        this.A00 = (double) i;
    }
}
