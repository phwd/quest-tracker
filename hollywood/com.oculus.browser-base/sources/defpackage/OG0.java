package defpackage;

import android.content.Context;
import android.provider.Settings;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.DevToolsServer;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: OG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OG0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f8614a = new Object();
    public static OG0 b;
    public boolean c;
    public boolean d;
    public boolean e;
    public DevToolsServer f;

    public static OG0 a() {
        ThreadUtils.a();
        if (b == null) {
            Objects.requireNonNull(AppHooks.get());
            b = new OG0();
        }
        return b;
    }

    public void b() {
        C0151Ck.f();
        C0151Ck.b = CachedFeatureFlags.isEnabled("PrioritizeBootstrapTasks");
        Context applicationContext = ContextUtils.getApplicationContext();
        Objects.requireNonNull(AppHooks.get());
        C1769b1 b1Var = new C1769b1(new C5537x51());
        AtomicReference atomicReference = AccountManagerFacadeProvider.f10888a;
        Object obj = ThreadUtils.f10596a;
        if (AccountManagerFacadeProvider.b == null) {
            AccountManagerFacadeProvider.b = b1Var;
            if (AccountManagerFacadeProvider.c == null) {
                AccountManagerFacadeProvider.f10888a.set(b1Var);
            }
            C3451ks1 ks1 = new C3451ks1(applicationContext, "chromium.sync.sessions.id");
            synchronized (AbstractC5828yp1.f11702a) {
                Map map = AbstractC5828yp1.b;
                if (!map.containsKey("SYNC")) {
                    map.put("SYNC", ks1);
                }
            }
            boolean z = false;
            if (Settings.Global.getInt(ContextUtils.getApplicationContext().getContentResolver(), "enable_de_jelly_for_chrome", 0) != 0) {
                z = true;
            }
            if (z) {
                AbstractC1575Zv.e().a("enable-de-jelly");
                return;
            }
            return;
        }
        throw new IllegalStateException("AccountManagerFacade is already initialized!");
    }

    public final void c() {
        ThreadUtils.a();
        if (!this.e) {
            this.e = true;
            C2150dE b2 = C2150dE.b();
            b2.a(new EG0(this));
            b2.a(new FG0(this));
            b2.a(new GG0(this));
            b2.a(new HG0(this));
            b2.a(new IG0(this));
            b2.a(new JG0(this));
            b2.a(new MG0(this));
            b2.a(new NG0(this));
            b2.a(new RunnableC5909zG0(this));
            b2.a(new AG0(this));
            b2.a(new RunnableC4889tG0());
            b2.a(new RunnableC5059uG0());
            b2.a(new RunnableC5229vG0());
            b2.a(new RunnableC5399wG0(AbstractApplicationC3785mq.g().f()));
            b2.a(new RunnableC5569xG0());
            b2.a(new RunnableC5739yG0());
            b2.a(new RunnableC3181jG0());
            b2.a(new RunnableC3352kG0());
            b2.a(new RunnableC3523lG0());
            b2.a(new RunnableC3694mG0());
            b2.a(new RunnableC3865nG0());
        }
    }

    public final void d() {
        TraceEvent j0 = TraceEvent.j0("ProcessInitializationHandler.initializePreNative()");
        try {
            ThreadUtils.a();
            if (!this.c) {
                b();
                this.c = true;
                if (j0 != null) {
                    j0.close();
                    return;
                }
                return;
            } else if (j0 != null) {
                j0.close();
                return;
            } else {
                return;
            }
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
