package X;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0x7  reason: invalid class name and case insensitive filesystem */
public final class C08370x7 {
    public final AnonymousClass0x2 A00;
    public final C08310x0 A01;
    public final ExecutorServiceC08580xU A02;
    @GuardedBy("itself")
    public final Map<Integer, C08510xM> A03 = new HashMap();
    public final RealtimeSinceBootClock A04;
    public final AbstractExecutorServiceC07550vT A05;

    public C08370x7(AbstractExecutorServiceC07550vT r2, ExecutorServiceC08580xU r3, AnonymousClass0x2 r4, C08310x0 r5, RealtimeSinceBootClock realtimeSinceBootClock) {
        this.A05 = r2;
        this.A02 = r3;
        this.A00 = r4;
        this.A01 = r5;
        this.A04 = realtimeSinceBootClock;
    }

    public final C08510xM A00(C08300wz r15, String str, EnumC08830xt r17, int i, int i2) {
        C08510xM put;
        C08510xM r7 = new C08510xM(r15, str, r17, i, SystemClock.elapsedRealtime());
        Map<Integer, C08510xM> map = this.A03;
        synchronized (map) {
            put = map.put(Integer.valueOf(r7.A01), r7);
        }
        if (put != null) {
            put.A00();
            AnonymousClass0NK.A06("MqttOperationManager", "operation/add/duplicate; id=%d, name=%s", Integer.valueOf(put.A01), put.A04.name());
        }
        ExecutorServiceC08580xU r6 = this.A02;
        RunnableC09230yu r3 = new RunnableC09230yu(this, r7);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        RunnableC08760xm r4 = new RunnableC08760xm(r6, r3, null);
        ExecutorServiceC08580xU.A02(r6, r4, SystemClock.elapsedRealtime() + timeUnit.toMillis((long) i2));
        boolean z = false;
        if (r7.A06 == null) {
            z = true;
        }
        C08170wh.A01(z);
        r7.A06 = r4;
        return r7;
    }
}
