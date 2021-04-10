package X;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0am  reason: invalid class name and case insensitive filesystem */
public final class C02580am {
    public static final C02580am A02 = new C02580am();
    @GuardedBy("this")
    public long A00 = -1;
    @GuardedBy("this")
    public C02570al A01 = new C02570al();

    public final synchronized void A00(int i, boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.A00;
        long j2 = elapsedRealtime - j;
        C02570al r11 = this.A01;
        r11.A00++;
        long j3 = 14000;
        if (j < 0 || j2 > 14000) {
            r11.A03++;
        }
        if (z) {
            r11.A02 += i;
        } else {
            r11.A01 += i;
        }
        long j4 = r11.A04;
        if (j >= 0) {
            j3 = Math.min(14000L, j2);
        }
        r11.A04 = j4 + j3;
        this.A00 = elapsedRealtime;
    }
}
