package defpackage;

import android.content.Context;
import android.os.StrictMode;
import com.oculus.browser.PanelService;
import com.oculus.browser.components.OculusUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.BrowserStartupControllerImpl;
import org.chromium.net.NetworkChangeNotifier;

/* renamed from: Vq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1321Vq {

    /* renamed from: a  reason: collision with root package name */
    public static C1321Vq f9108a;
    public static AbstractC4621rk b;
    public final Locale c = Locale.getDefault();
    public List d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;

    public static C1321Vq b() {
        if (f9108a == null) {
            f9108a = new C1321Vq();
        }
        return f9108a;
    }

    public final AbstractC4621rk a() {
        if (b == null) {
            b = AbstractC4280pk.a();
        }
        return b;
    }

    /* JADX INFO: finally extract failed */
    public void c(boolean z, AbstractC3083ik ikVar, PanelService panelService) {
        if (panelService != null) {
            panelService.d();
        }
        if (this.f) {
            C4630rn rnVar = new C4630rn();
            if (panelService != null) {
                panelService.d();
            }
            if (!ikVar.N() && !OG0.a().d) {
                rnVar.a(Zo1.e, new RunnableC0651Kq());
            }
            if (panelService != null) {
                panelService.d();
            }
            if (!this.i) {
                rnVar.a(Zo1.e, new RunnableC0712Lq(this));
            }
            if (panelService != null) {
                panelService.d();
            }
            C3070if1 if1 = Zo1.e;
            rnVar.a(if1, new RunnableC0772Mq(this, ikVar));
            if (panelService != null) {
                panelService.d();
            }
            rnVar.a(if1, new RunnableC0833Nq(ikVar));
            if (panelService != null) {
                panelService.d();
            }
            rnVar.a(if1, new RunnableC0894Oq(ikVar));
            if (panelService != null) {
                panelService.d();
            }
            rnVar.a(if1, new RunnableC0955Pq(ikVar));
            if (panelService != null) {
                panelService.d();
            }
            if (!this.g) {
                rnVar.a(Zo1.f9374a, new RunnableC1016Qq(this));
            }
            if (panelService != null) {
                panelService.d();
            }
            if (!ikVar.N()) {
                rnVar.a(Zo1.f9374a, new RunnableC1077Rq(this));
            }
            if (panelService != null) {
                panelService.d();
            }
            AbstractC4621rk a2 = a();
            BrowserStartupControllerImpl browserStartupControllerImpl = (BrowserStartupControllerImpl) a2;
            rnVar.a(Zo1.f9374a, new RunnableC0529Iq(NS0.a(browserStartupControllerImpl.h, browserStartupControllerImpl.l, ikVar.N())));
            if (z) {
                if (panelService != null) {
                    panelService.d();
                }
                boolean w = ikVar.w();
                boolean N = ikVar.N();
                C1199Tq tq = new C1199Tq(this, ikVar, rnVar);
                try {
                    TraceEvent.Y("ChromeBrowserInitializer.startChromeBrowserProcessesAsync", null);
                    ((BrowserStartupControllerImpl) a()).i(1, w, N, tq);
                } finally {
                    TraceEvent.f0("ChromeBrowserInitializer.startChromeBrowserProcessesAsync");
                }
            } else {
                if (panelService != null) {
                    panelService.d();
                }
                try {
                    TraceEvent.Y("ChromeBrowserInitializer.startChromeBrowserProcessesSync", null);
                    Object obj = ThreadUtils.f10596a;
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    C2474f80.f9900a.b();
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    AbstractC2987i80.a();
                    ((BrowserStartupControllerImpl) a()).j(1, false);
                    AbstractC2022cW0.a();
                    TraceEvent.f0("ChromeBrowserInitializer.startChromeBrowserProcessesSync");
                    rnVar.b(true);
                } catch (Throwable th) {
                    TraceEvent.f0("ChromeBrowserInitializer.startChromeBrowserProcessesSync");
                    throw th;
                }
            }
        } else {
            throw new IllegalStateException("ChromeBrowserInitializer.handlePostNativeStartup called before ChromeBrowserInitializer.postInflationStartup has been run.");
        }
    }

    public void d(AbstractC3083ik ikVar) {
        ThreadUtils.a();
        if (!ikVar.v()) {
            OG0.a().d();
            TraceEvent j0 = TraceEvent.j0("ChromeBrowserInitializer.preInflationStartup");
            try {
                h();
                ikVar.k();
                if (j0 != null) {
                    j0.close();
                }
                if (!ikVar.v()) {
                    if (SysUtils.isLowEndDevice()) {
                        AbstractC1575Zv.e().a("disable-domain-reliability");
                    }
                    ikVar.H(new RunnableC0468Hq(this, ikVar));
                    return;
                }
                return;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }

    public void e() {
        f(false, null);
    }

    public final void f(boolean z, PanelService panelService) {
        if (panelService != null) {
            panelService.d();
        }
        ThreadUtils.a();
        if (panelService != null) {
            panelService.d();
        }
        Context applicationContext = ContextUtils.getApplicationContext();
        OculusUser oculusUser = OculusUser.f9719a;
        if (oculusUser == null || oculusUser.c.equals("Default")) {
            AbstractC1220Ua0.d("OculusUser", "Creating OculusUser on background ", new Object[0]);
            C2251dr0 dr0 = new C2251dr0(applicationContext);
            Executor executor = AbstractC2032cb.f9616a;
            dr0.f();
            ((ExecutorC1463Ya) executor).execute(dr0.e);
        }
        if (panelService != null) {
            panelService.d();
        }
        C1138Sq sq = new C1138Sq(this, z);
        if (panelService != null) {
            panelService.d();
        }
        d(sq);
        if (panelService != null) {
            panelService.d();
        }
        c(false, sq, panelService);
    }

    public void g() {
        if (!this.i) {
            this.i = true;
            Object obj = ThreadUtils.f10596a;
            TraceEvent.Y("NetworkChangeNotifier.init", null);
            NetworkChangeNotifier.init();
            NetworkChangeNotifier.k(true);
            TraceEvent.f0("NetworkChangeNotifier.init");
        }
    }

    public final void h() {
        Object obj = ThreadUtils.f10596a;
        if (!this.e) {
            if (!AbstractC2598ft.f9962a) {
                AbstractC2598ft.f9962a = true;
            }
            byte[] bArr = AbstractC3797mu.f10456a;
            byte[] bArr2 = AbstractC3797mu.b;
            if (AbstractC2612fx1.f9968a == null) {
                AbstractC2612fx1.f9968a = bArr;
            }
            if (AbstractC2612fx1.b == null) {
                AbstractC2612fx1.b = bArr2;
            }
            PostTask.b(C3070if1.b, new RunnableC0590Jq(), 0);
            GE.a();
            ApplicationStatus.g.b(new C1260Uq(this));
            this.e = true;
        }
    }

    public void i(Runnable runnable) {
        if (this.h) {
            runnable.run();
            return;
        }
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.add(runnable);
    }
}
