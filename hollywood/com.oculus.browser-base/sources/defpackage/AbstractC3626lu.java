package defpackage;

import android.content.Context;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* renamed from: lu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3626lu {

    /* renamed from: a  reason: collision with root package name */
    public static AbstractC1678aa f10384a;

    public static void a(String str, Hw1 hw1) {
        Object obj = ThreadUtils.f10596a;
        if (f10384a == null) {
            C3455ku kuVar = new C3455ku();
            f10384a = kuVar;
            ApplicationStatus.h.b(kuVar);
        }
        C3070if1 if1 = Zo1.f9374a;
        if (Iw1.f8259a == null) {
            Iw1.f8259a = new Iw1(if1);
        }
        Iw1 iw1 = Iw1.f8259a;
        Context applicationContext = ContextUtils.getApplicationContext();
        iw1.b.a(applicationContext, str, new Gw1(iw1, applicationContext, str, hw1));
    }

    public static void b() {
        Context applicationContext = ContextUtils.getApplicationContext();
        Iw1 iw1 = Iw1.f8259a;
        if (iw1 != null) {
            iw1.b.b(applicationContext);
        }
        Qw1 qw1 = Qw1.f8796a;
        if (qw1 != null) {
            qw1.b.b(ContextUtils.getApplicationContext());
        }
    }
}
