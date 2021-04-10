package X;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0vz  reason: invalid class name and case insensitive filesystem */
public final class C07770vz {
    public AlarmManager A00;
    public Context A01;
    public AnonymousClass0ux A02;
    public C08110wa A03;
    public C07800w2 A04;
    public Map<String, PendingIntent> A05;
    public RealtimeSinceBootClock A06;
    public final AbstractC04970iB A07 = new C07780w0(this);

    public final void A00(String str) {
        PendingIntent remove = this.A05.remove(str);
        if (remove != null) {
            this.A03.A03(this.A00, remove);
        }
        C07720vq A2E = this.A02.A2E();
        A2E.A00.putLong(str, 120000);
        A2E.A00();
    }

    public C07770vz(Context context, C08800xq r4, RealtimeSinceBootClock realtimeSinceBootClock, C07800w2 r6, C08110wa r7, C07710vp r8) {
        this.A01 = context;
        AbstractC09150yk A002 = r4.A00("alarm", AlarmManager.class);
        if (A002.A02()) {
            this.A00 = (AlarmManager) A002.A01();
            this.A02 = r8.A00(EnumC07690vn.RETRY);
            this.A06 = realtimeSinceBootClock;
            this.A04 = r6;
            this.A03 = r7;
            this.A05 = new HashMap();
            return;
        }
        throw new IllegalArgumentException("Cannot acquire Alarm service");
    }
}
