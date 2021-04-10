package defpackage;

import android.content.SharedPreferences;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;

/* renamed from: FV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FV extends AbstractC3270jp0 implements AbstractC1678aa {

    /* renamed from: a  reason: collision with root package name */
    public final C1322Vq0 f8018a = new C1322Vq0();
    public final O51 b;
    public final PU0 c;
    public final RE0 d;
    public final Runnable e = new DV(this);
    public Boolean f;
    public OU0 g;
    public boolean h;

    public FV(O51 o51, RE0 re0, PU0 pu0) {
        this.b = o51;
        this.c = pu0;
        this.d = re0;
        this.g = new EV(this);
        f();
        int stateForApplication = ApplicationStatus.getStateForApplication();
        if (stateForApplication == 1 || stateForApplication == 2) {
            e();
        }
        ApplicationStatus.h.b(this);
    }

    @Override // defpackage.AbstractC1678aa
    public void a(int i) {
        if (i == 1) {
            e();
        } else if (i == 3 && this.h) {
            this.h = false;
            this.b.b.c(this);
            RE0 re0 = this.d;
            re0.b.c(this.e);
            PU0 pu0 = this.c;
            SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = (SharedPreferences.OnSharedPreferenceChangeListener) pu0.b.get(this.g);
            if (onSharedPreferenceChangeListener != null) {
                AbstractC3983nz.f10523a.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
            }
        }
    }

    @Override // defpackage.AbstractC3270jp0
    public void b(AbstractC3441kp0 kp0) {
        this.f8018a.b(kp0);
    }

    @Override // defpackage.AbstractC3270jp0
    public boolean c() {
        Boolean bool = this.f;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @Override // defpackage.AbstractC3270jp0
    public void d(AbstractC3441kp0 kp0) {
        this.f8018a.c(kp0);
    }

    public final void e() {
        if (!this.h) {
            this.h = true;
            this.b.b.b(this);
            RE0 re0 = this.d;
            re0.b.b(this.e);
            this.c.a(this.g);
            f();
        }
    }

    public final void f() {
        boolean z = this.d.e;
        int a2 = AbstractC3612lp0.a();
        int i = 0;
        boolean z2 = (a2 == 0 && (z || this.b.c)) || a2 == 2;
        Boolean bool = this.f;
        if (bool == null || z2 != bool.booleanValue()) {
            Boolean valueOf = Boolean.valueOf(z2);
            this.f = valueOf;
            Q7.o(valueOf.booleanValue() ? 2 : 1);
            Iterator it = this.f8018a.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((AbstractC3441kp0) uq0.next()).C();
            }
            AbstractC3100ip1.f10165a.a("Android.DarkTheme.EnabledState", this.f.booleanValue());
            AbstractC3364kK0.g("Android.DarkTheme.Preference.State", a2, 3);
            if (this.f.booleanValue()) {
                if (a2 != 2) {
                    i = z ? 1 : 2;
                }
                AbstractC3364kK0.g("Android.DarkTheme.EnabledReason", i, 3);
            }
        }
    }
}
