package X;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.0wb  reason: invalid class name and case insensitive filesystem */
public final class C08120wb {
    public static final String A0E = AnonymousClass006.A05(C08120wb.class.getCanonicalName(), ".ACTION_ALARM.");
    @GuardedBy("this")
    public boolean A00;
    public boolean A01;
    public final int A02;
    public final AlarmManager A03;
    public final PendingIntent A04;
    public final BroadcastReceiver A05;
    public final Context A06;
    public final Handler A07;
    public final C08110wa A08;
    public final String A09;
    public final int A0A;
    public final RealtimeSinceBootClock A0B;
    public final C07540vS A0C;
    public volatile Runnable A0D;

    public final synchronized void A00() {
        if (this.A00) {
            this.A00 = false;
            this.A08.A03(this.A03, this.A04);
        }
    }

    public final synchronized void A01() {
        if (!this.A01) {
            this.A01 = this.A08.A06(this.A06, this.A05, new IntentFilter(this.A09), this.A07);
        }
        if (!this.A00) {
            long j = (long) ((((AnonymousClass0v5) this.A0C.A00).A01.A0C + this.A0A) * 1000);
            this.A00 = true;
            long elapsedRealtime = SystemClock.elapsedRealtime() + j;
            try {
                if (this.A02 >= 19) {
                    this.A08.A00(this.A03, elapsedRealtime, this.A04);
                } else {
                    this.A03.set(2, elapsedRealtime, this.A04);
                }
            } catch (Throwable th) {
                this.A00 = false;
                AnonymousClass0NK.A0D("PingUnreceivedAlarm", th, "alarm_failed; intervalSec=%s", Long.valueOf(j / 1000));
            }
        }
    }

    public C08120wb(Context context, C08800xq r6, String str, RealtimeSinceBootClock realtimeSinceBootClock, Handler handler, C07540vS r10, C08110wa r11) {
        this.A06 = context;
        StringBuilder sb = new StringBuilder(A0E);
        sb.append(str);
        String packageName = context.getPackageName();
        if (!TextUtils.isEmpty(packageName)) {
            sb.append('.');
            sb.append(packageName);
        }
        this.A09 = sb.toString();
        AbstractC09150yk A002 = r6.A00("alarm", AlarmManager.class);
        if (A002.A02()) {
            this.A03 = (AlarmManager) A002.A01();
            this.A0B = realtimeSinceBootClock;
            this.A02 = Build.VERSION.SDK_INT;
            this.A07 = handler;
            this.A0C = r10;
            this.A08 = r11;
            this.A0A = ((AnonymousClass0v5) r10.A00).A01.A0J;
            this.A05 = new C08030wS(this);
            Intent intent = new Intent(this.A09);
            intent.setPackage(this.A06.getPackageName());
            this.A04 = PendingIntent.getBroadcast(this.A06, 0, intent, 134217728);
            return;
        }
        throw new IllegalArgumentException("Cannot acquire Alarm service");
    }
}
