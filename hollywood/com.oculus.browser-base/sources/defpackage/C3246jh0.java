package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat$Token;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* renamed from: jh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3246jh0 {

    /* renamed from: a  reason: collision with root package name */
    public static C1543Zg0 f10229a;
    public final Context b;
    public final ArrayList c = new ArrayList();

    public C3246jh0(Context context) {
        this.b = context;
    }

    public static void b() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
        }
    }

    public static C3246jh0 e(Context context) {
        if (context != null) {
            b();
            if (f10229a == null) {
                C1543Zg0 zg0 = new C1543Zg0(context.getApplicationContext());
                f10229a = zg0;
                zg0.a(zg0.l);
                C1537Ze0 ze0 = zg0.c;
                if (ze0 != null) {
                    zg0.a(ze0);
                }
                C5412wL0 wl0 = new C5412wL0(zg0.f9359a, zg0);
                zg0.o = wl0;
                if (!wl0.f) {
                    wl0.f = true;
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
                    intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
                    intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
                    intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
                    intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
                    intentFilter.addDataScheme("package");
                    wl0.f11542a.registerReceiver(wl0.g, intentFilter, null, wl0.c);
                    wl0.c.post(wl0.h);
                }
            }
            C1543Zg0 zg02 = f10229a;
            int size = zg02.d.size();
            while (true) {
                size--;
                if (size >= 0) {
                    C3246jh0 jh0 = (C3246jh0) ((WeakReference) zg02.d.get(size)).get();
                    if (jh0 == null) {
                        zg02.d.remove(size);
                    } else if (jh0.b == context) {
                        return jh0;
                    }
                } else {
                    C3246jh0 jh02 = new C3246jh0(context);
                    zg02.d.add(new WeakReference(jh02));
                    return jh02;
                }
            }
        } else {
            throw new IllegalArgumentException("context must not be null");
        }
    }

    public void a(C0629Kg0 kg0, AbstractC0750Mg0 mg0, int i) {
        C0811Ng0 ng0;
        if (kg0 == null) {
            throw new IllegalArgumentException("selector must not be null");
        } else if (mg0 != null) {
            b();
            int c2 = c(mg0);
            if (c2 < 0) {
                ng0 = new C0811Ng0(this, mg0);
                this.c.add(ng0);
            } else {
                ng0 = (C0811Ng0) this.c.get(c2);
            }
            boolean z = false;
            boolean z2 = true;
            if (i != ng0.d) {
                ng0.d = i;
                z = true;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if ((i & 1) != 0) {
                z = true;
            }
            ng0.e = elapsedRealtime;
            C0629Kg0 kg02 = ng0.c;
            Objects.requireNonNull(kg02);
            kg02.a();
            kg0.a();
            if (!kg02.c.containsAll(kg0.c)) {
                C0568Jg0 jg0 = new C0568Jg0(ng0.c);
                kg0.a();
                jg0.a(kg0.c);
                ng0.c = jg0.c();
            } else {
                z2 = z;
            }
            if (z2) {
                f10229a.m();
            }
        } else {
            throw new IllegalArgumentException("callback must not be null");
        }
    }

    public final int c(AbstractC0750Mg0 mg0) {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            if (((C0811Ng0) this.c.get(i)).b == mg0) {
                return i;
            }
        }
        return -1;
    }

    public C2392eh0 d() {
        b();
        return f10229a.f();
    }

    public MediaSessionCompat$Token f() {
        C1543Zg0 zg0 = f10229a;
        C1299Vg0 vg0 = zg0.A;
        if (vg0 != null) {
            C0571Jh0 jh0 = vg0.f9098a;
            if (jh0 != null) {
                return jh0.b();
            }
            return null;
        }
        C0571Jh0 jh02 = zg0.B;
        if (jh02 != null) {
            return jh02.b();
        }
        return null;
    }

    public List g() {
        b();
        return f10229a.e;
    }

    public C2392eh0 h() {
        b();
        return f10229a.g();
    }

    public boolean i(C0629Kg0 kg0, int i) {
        if (kg0 != null) {
            b();
            C1543Zg0 zg0 = f10229a;
            Objects.requireNonNull(zg0);
            if (kg0.c()) {
                return false;
            }
            if ((i & 2) != 0 || !zg0.m) {
                int size = zg0.e.size();
                for (int i2 = 0; i2 < size; i2++) {
                    C2392eh0 eh0 = (C2392eh0) zg0.e.get(i2);
                    if (((i & 1) != 0 && eh0.e()) || !eh0.i(kg0)) {
                    }
                }
                return false;
            }
            return true;
        }
        throw new IllegalArgumentException("selector must not be null");
    }

    public void j(AbstractC0750Mg0 mg0) {
        if (mg0 != null) {
            b();
            int c2 = c(mg0);
            if (c2 >= 0) {
                this.c.remove(c2);
                f10229a.m();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("callback must not be null");
    }

    public void k(C2392eh0 eh0) {
        if (eh0 != null) {
            b();
            f10229a.k(eh0, 3);
            return;
        }
        throw new IllegalArgumentException("route must not be null");
    }

    public void l(C0571Jh0 jh0) {
        C1543Zg0 zg0 = f10229a;
        zg0.B = jh0;
        C1299Vg0 vg0 = jh0 != null ? new C1299Vg0(zg0, jh0) : null;
        C1299Vg0 vg02 = zg0.A;
        if (vg02 != null) {
            vg02.a();
        }
        zg0.A = vg0;
        if (vg0 != null) {
            zg0.n();
        }
    }

    public void m(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Unsupported reason to unselect route");
        }
        b();
        C2392eh0 c2 = f10229a.c();
        if (f10229a.g() != c2) {
            f10229a.k(c2, i);
            return;
        }
        C1543Zg0 zg0 = f10229a;
        zg0.k(zg0.f(), i);
    }
}
