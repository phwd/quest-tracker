package X;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Yh  reason: invalid class name and case insensitive filesystem */
public final class C01970Yh {
    public final AnonymousClass0Wo A00;
    public final AnonymousClass0Wu A01;
    public final AnonymousClass08r A02;
    @GuardedBy("itself")
    public final Map<Integer, C06050mQ> A03 = new HashMap();
    public final RealtimeSinceBootClock A04;
    public final AnonymousClass08u A05;

    public C01970Yh(AnonymousClass08u r2, AnonymousClass08r r3, AnonymousClass0Wo r4, AnonymousClass0Wu r5, RealtimeSinceBootClock realtimeSinceBootClock) {
        this.A05 = r2;
        this.A02 = r3;
        this.A00 = r4;
        this.A01 = r5;
        this.A04 = realtimeSinceBootClock;
    }

    public final C06050mQ A00(AnonymousClass0ZF r15, String str, EnumC02120Zg r17, int i, int i2) {
        C06050mQ put;
        C06050mQ r7 = new C06050mQ(r15, str, r17, i, SystemClock.elapsedRealtime());
        Map<Integer, C06050mQ> map = this.A03;
        synchronized (map) {
            put = map.put(Integer.valueOf(r7.A01), r7);
        }
        if (put != null) {
            put.A00();
            AnonymousClass0NO.A0E("MqttOperationManager", "operation/add/duplicate; id=%d, name=%s", Integer.valueOf(put.A01), put.A04.name());
        }
        AnonymousClass08r r6 = this.A02;
        RunnableC01950Ye r3 = new RunnableC01950Ye(this, r7);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        RunnableC002708s r4 = new RunnableC002708s(r6, r3, null);
        AnonymousClass08r.A02(r6, r4, SystemClock.elapsedRealtime() + timeUnit.toMillis((long) i2));
        boolean z = false;
        if (r7.A06 == null) {
            z = true;
        }
        AnonymousClass0W9.A01(z);
        r7.A06 = r4;
        return r7;
    }
}
