package X;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.oculus.horizon.abuse_prevention.VideoUploaderCleanerServiceManager;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0ac  reason: invalid class name and case insensitive filesystem */
public final class C02490ac {
    public AnonymousClass0WD A00;
    public AlarmManager A01;
    public Context A02;
    public RealtimeSinceBootClock A03;
    public AnonymousClass0Wc A04;
    public C01890Xx A05;
    public Map<String, PendingIntent> A06;
    public final AnonymousClass0b1 A07 = new AnonymousClass0jd(this);

    public final void A00(String str) {
        PendingIntent remove = this.A06.remove(str);
        if (remove != null) {
            this.A04.A04(this.A01, remove);
        }
        C06520nY A2L = this.A00.A2L();
        A2L.A00.putLong(str, 120000);
        A2L.A00();
    }

    public final void A01(String str, String str2, String str3) {
        Intent intent = new Intent("com.facebook.rti.fbns.intent.REGISTER_RETRY");
        intent.setClassName(this.A02, str3);
        intent.putExtra("pkg_name", str);
        intent.putExtra("appid", str2);
        this.A05.A02(intent);
        AnonymousClass0b4 r5 = new AnonymousClass0b4();
        r5.A04(intent, this.A02.getClassLoader());
        r5.A01 |= 1;
        r5.A08 = this.A07;
        Context context = this.A02;
        PendingIntent service = PendingIntent.getService(context, 0, AnonymousClass0b4.A01(r5, context), AnonymousClass0b4.A00(r5, 134217728));
        this.A06.put(str, service);
        long A3l = this.A00.A3l(str, 120000);
        long elapsedRealtime = SystemClock.elapsedRealtime() + A3l;
        int i = Build.VERSION.SDK_INT;
        AnonymousClass0Wc r1 = this.A04;
        AlarmManager alarmManager = this.A01;
        if (i >= 23) {
            r1.A03(alarmManager, elapsedRealtime, service);
        } else {
            r1.A01(alarmManager, elapsedRealtime, service);
        }
        long j = A3l * 2;
        if (j > VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS) {
            j = VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS;
        }
        C06520nY A2L = this.A00.A2L();
        A2L.A00.putLong(str, j);
        A2L.A00();
    }

    public C02490ac(Context context, C01630Wg r4, RealtimeSinceBootClock realtimeSinceBootClock, C01890Xx r6, AnonymousClass0Wc r7, C06510nV r8) {
        this.A02 = context;
        AnonymousClass0W8 A002 = r4.A00("alarm", AlarmManager.class);
        if (A002.A02()) {
            this.A01 = (AlarmManager) A002.A01();
            this.A00 = r8.A00(AnonymousClass0WE.RETRY);
            this.A03 = realtimeSinceBootClock;
            this.A05 = r6;
            this.A04 = r7;
            this.A06 = new HashMap();
            return;
        }
        throw new IllegalArgumentException("Cannot acquire Alarm service");
    }
}
