package defpackage;

import J.N;
import android.content.Context;
import android.os.Bundle;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.offlinepages.TriggerConditions;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* renamed from: gr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2764gr0 extends AbstractC4798sm0 {
    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
        Bundle bundle = new Bundle();
        bundle.putLong("ScheduleTime", System.currentTimeMillis());
        bundle.putBoolean("PowerConnected", false);
        bundle.putInt("BatteryPercentage", 0);
        bundle.putBoolean("UnmeteredNetwork", false);
        C1111Se1 a2 = TaskInfo.a(77, 300000, 604800000);
        a2.c = 0 != 0 ? 2 : 1;
        a2.f = false;
        a2.e = true;
        a2.b = bundle;
        a2.d = false;
        AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), a2.a());
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0035 A[RETURN] */
    @Override // defpackage.AbstractC4798sm0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int e(android.content.Context r3, defpackage.C2046cf1 r4, defpackage.AbstractC0804Ne r5) {
        /*
            r2 = this;
            android.os.Bundle r4 = r4.b
            org.chromium.chrome.browser.offlinepages.TriggerConditions r4 = defpackage.AbstractC0989Qe1.a(r4)
            vE r3 = defpackage.C5222vE.b(r3)
            boolean r5 = r3.f11469a
            r0 = 0
            r1 = 1
            if (r5 != 0) goto L_0x0019
            int r3 = r3.b
            int r4 = r4.b
            if (r3 < r4) goto L_0x0017
            goto L_0x0019
        L_0x0017:
            r3 = r0
            goto L_0x001a
        L_0x0019:
            r3 = r1
        L_0x001a:
            if (r3 != 0) goto L_0x001e
        L_0x001c:
            r3 = r0
            goto L_0x0032
        L_0x001e:
            boolean r3 = org.chromium.base.SysUtils.isLowEndDevice()
            if (r3 == 0) goto L_0x002d
            boolean r3 = org.chromium.base.ApplicationStatus.hasVisibleActivities()
            if (r3 != 0) goto L_0x002b
            goto L_0x002d
        L_0x002b:
            r3 = r0
            goto L_0x002e
        L_0x002d:
            r3 = r1
        L_0x002e:
            if (r3 != 0) goto L_0x0031
            goto L_0x001c
        L_0x0031:
            r3 = r1
        L_0x0032:
            if (r3 != 0) goto L_0x0035
            return r1
        L_0x0035:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2764gr0.e(android.content.Context, cf1, Ne):int");
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        if (C0561Je.f8301a == null) {
            C0561Je.f8301a = new C0561Je();
        }
        C0561Je je = C0561Je.f8301a;
        Bundle bundle = cf1.b;
        C2593fr0 fr0 = new C2593fr0(this, ne);
        C5222vE b = C5222vE.b(context);
        Objects.requireNonNull(je);
        if (!N.MrvK6$zt(b.f11469a, b.b, b.d, fr0)) {
            ne.a(true);
            return;
        }
        TriggerConditions a2 = AbstractC0989Qe1.a(cf1.b);
        Bundle bundle2 = new Bundle();
        bundle2.putLong("ScheduleTime", System.currentTimeMillis());
        bundle2.putBoolean("PowerConnected", a2.f10721a);
        bundle2.putInt("BatteryPercentage", a2.b);
        bundle2.putBoolean("UnmeteredNetwork", a2.c);
        C1111Se1 a3 = TaskInfo.a(77, 300000, 604800000);
        a3.c = a2.c ? 2 : 1;
        a3.f = false;
        a3.e = true;
        a3.b = bundle2;
        a3.d = a2.f10721a;
        AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), a3.a());
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        return true;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        if (C0561Je.f8301a == null) {
            C0561Je.f8301a = new C0561Je();
        }
        Objects.requireNonNull(C0561Je.f8301a);
        N.MMBt6JJr();
        return true;
    }
}
