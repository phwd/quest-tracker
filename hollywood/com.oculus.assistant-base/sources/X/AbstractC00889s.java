package X;

import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import com.facebook.quicklog.QuickPerformanceLogger;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* renamed from: X.9s  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00889s {
    public static C1396yX A03;
    public QuickPerformanceLogger A00;
    public boolean A01 = true;
    public final int A02;

    public static String A00(Map map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append((String) entry.getKey());
            sb.append(":");
            sb.append((String) entry.getValue());
        }
        return sb.toString();
    }

    public final void A02() {
        String str = "warm";
        if (this.A01) {
            if (Build.VERSION.SDK_INT >= 24 && SystemClock.uptimeMillis() - Process.getStartUptimeMillis() < TimeUnit.SECONDS.toMillis(10)) {
                str = "cold";
            }
            this.A01 = false;
        }
        this.A00.markerAnnotate(this.A02, "start_type", str);
    }

    public final void A03(EnumC00899t r4, String str) {
        this.A00.markerAnnotate(this.A02, r4.getExtraName(), str);
    }

    public final void A04(EnumC00899t r4, boolean z) {
        this.A00.markerAnnotate(this.A02, r4.getExtraName(), z);
    }

    public final void A05(EnumC00909u r4) {
        this.A00.markerPoint(this.A02, r4.getPointName());
    }

    public AbstractC00889s(int i) {
        this.A02 = i;
        this.A00 = A3.A00;
    }
}
