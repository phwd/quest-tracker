package org.chromium.content.browser;

import J.N;
import android.os.StrictMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.base.BuildInfo;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BrowserStartupControllerImpl implements AbstractC4621rk {

    /* renamed from: a  reason: collision with root package name */
    public static BrowserStartupControllerImpl f10909a;
    public static boolean b;
    public final List c = new ArrayList();
    public final List d = new ArrayList();
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public int j = 0;
    public boolean k;
    public boolean l;
    public TracingControllerAndroidImpl m;

    public BrowserStartupControllerImpl() {
        if (BuildInfo.a()) {
            PostTask.b(Zo1.f9374a, new RunnableC4961tk(this), 0);
        }
    }

    public static void browserStartupComplete(int i2) {
        BrowserStartupControllerImpl browserStartupControllerImpl = f10909a;
        if (browserStartupControllerImpl != null) {
            browserStartupControllerImpl.d(i2);
        }
    }

    public static void minimalBrowserStartupComplete() {
        BrowserStartupControllerImpl browserStartupControllerImpl = f10909a;
        if (browserStartupControllerImpl != null) {
            browserStartupControllerImpl.l = true;
            if (browserStartupControllerImpl.k) {
                browserStartupControllerImpl.j = 0;
                if (browserStartupControllerImpl.b() > 0) {
                    browserStartupControllerImpl.c(1);
                    return;
                }
                return;
            }
            if (browserStartupControllerImpl.j == 1) {
                browserStartupControllerImpl.e(-1);
            }
            browserStartupControllerImpl.h();
        }
    }

    public static boolean shouldStartGpuProcessOnBrowserStartup() {
        return b;
    }

    public void a(AbstractC4451qk qkVar) {
        Object obj = ThreadUtils.f10596a;
        if (this.h) {
            PostTask.b(Zo1.e, new RunnableC5471wk(this, qkVar), 0);
        } else {
            this.c.add(qkVar);
        }
    }

    public int b() {
        boolean z = this.j == 1;
        int M1Y_XVCN = N.M1Y_XVCN(z);
        if (!z) {
            this.k = false;
        }
        this.g = true;
        return M1Y_XVCN;
    }

    public final void c(int i2) {
        PostTask.b(Zo1.e, new RunnableC5301vk(this, i2), 0);
    }

    public final void d(int i2) {
        boolean z = true;
        this.h = true;
        if (i2 > 0) {
            z = false;
        }
        this.i = z;
        for (AbstractC4451qk qkVar : this.c) {
            if (this.i) {
                qkVar.onSuccess();
            } else {
                qkVar.a();
            }
        }
        this.c.clear();
        e(i2);
        h();
    }

    public final void e(int i2) {
        this.i = i2 <= 0;
        for (AbstractC4451qk qkVar : this.d) {
            if (this.i) {
                qkVar.onSuccess();
            } else {
                qkVar.a();
            }
        }
        this.d.clear();
    }

    public boolean f() {
        Object obj = ThreadUtils.f10596a;
        return this.h && this.i;
    }

    /* JADX INFO: finally extract failed */
    public void g(boolean z, Runnable runnable) {
        boolean z2;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            C2474f80.f9900a.b();
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            RunnableC5641xk xkVar = new RunnableC5641xk(this, z, runnable);
            C3881nM0.b().c = Zo1.e;
            if (runnable == null) {
                C3881nM0 b2 = C3881nM0.b();
                if (b2.b != null && !C3881nM0.e()) {
                    try {
                        b2.b.H.await();
                    } catch (Exception unused) {
                    }
                }
                xkVar.run();
                return;
            }
            C3881nM0 b3 = C3881nM0.b();
            Objects.requireNonNull(b3);
            Object obj = ThreadUtils.f10596a;
            if (C3881nM0.e()) {
                PostTask.b(b3.c, xkVar, 0);
                return;
            }
            RunnableC3710mM0 mm0 = b3.b;
            synchronized (mm0) {
                z2 = mm0.I;
            }
            if (z2) {
                PostTask.b(b3.c, xkVar, 0);
            } else {
                b3.b.F.add(xkVar);
            }
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
    }

    public void h() {
        int[] iArr;
        NS0 ns0 = NS0.f8547a;
        NS0 ns02 = NS0.f8547a;
        ns02.c = true;
        for (int i2 = 0; i2 < 4; i2++) {
            if (ns02.b[i2] > 0) {
                int i3 = 0;
                while (true) {
                    iArr = ns02.b;
                    if (i3 >= iArr[i2]) {
                        break;
                    }
                    AbstractC3364kK0.g("Servicification.Startup2", i2, 4);
                    i3++;
                }
                iArr[i2] = 0;
            }
        }
    }

    public void i(int i2, boolean z, boolean z2, AbstractC4451qk qkVar) {
        Objects.requireNonNull(C2474f80.f9900a);
        NS0 ns0 = NS0.f8547a;
        NS0.f8547a.b(NS0.a(this.h, this.l, z2));
        if (this.h || (z2 && this.l)) {
            PostTask.b(Zo1.e, new RunnableC5471wk(this, qkVar), 0);
            return;
        }
        if (z2) {
            this.d.add(qkVar);
        } else {
            this.c.add(qkVar);
        }
        boolean z3 = this.k | (this.j == 1 && !z2);
        this.k = z3;
        if (!this.e) {
            this.e = true;
            b = z;
            g(false, new RunnableC5131uk(this, z2));
        } else if (this.l && z3) {
            this.j = 0;
            if (b() > 0) {
                c(1);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void j(int r4, boolean r5) {
        /*
        // Method dump skipped, instructions count: 109
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.BrowserStartupControllerImpl.j(int, boolean):void");
    }
}
