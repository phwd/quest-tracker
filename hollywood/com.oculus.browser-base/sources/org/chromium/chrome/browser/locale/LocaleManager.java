package org.chromium.chrome.browser.locale;

import J.N;
import android.app.Activity;
import android.content.Context;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.AppHooks;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LocaleManager {

    /* renamed from: a  reason: collision with root package name */
    public static LocaleManager f10693a;
    public boolean b;
    public WeakReference c = new WeakReference(null);
    public C2884ha0 d;
    public AbstractC4758sY0 e = new C2542fa0(this);

    public LocaleManager() {
        NU0.f8549a.f("com.android.chrome.SEARCH_ENGINE_PROMO_SHOWN", -1);
    }

    public static LocaleManager getInstance() {
        if (f10693a == null) {
            Objects.requireNonNull(AppHooks.get());
            f10693a = new LocaleManager();
        }
        return f10693a;
    }

    public final C2884ha0 a() {
        if (this.d == null) {
            this.d = new C2884ha0("US");
        }
        return this.d;
    }

    public boolean b() {
        if (AbstractC4226pO.a() && !N.M09VlOh_("SearchEnginePromo.ExistingDevice")) {
            return false;
        }
        int f = NU0.f8549a.f("com.android.chrome.SEARCH_ENGINE_PROMO_SHOWN", -1);
        if (this.b || f != -1) {
            return false;
        }
        return true;
    }

    public void c() {
    }

    public void d() {
    }

    public void e(boolean z) {
        NU0.f8549a.m("LocaleManager_PREF_AUTO_SWITCH", z);
    }

    public void f(Activity activity, Callback callback) {
        AbstractC0444Hf1.a().j(new RunnableC2713ga0(this, activity, callback));
    }

    public final void g(CharSequence charSequence) {
        View$OnClickListenerC5098uY0 uy0 = (View$OnClickListenerC5098uY0) this.c.get();
        if (uy0 != null) {
            Context applicationContext = ContextUtils.getApplicationContext();
            C4076oY0 c2 = C4076oY0.c(charSequence, this.e, 1, 14);
            c2.j = 6000;
            c2.d = applicationContext.getString(R.string.f61350_resource_name_obfuscated_RES_2131953452);
            c2.e = null;
            uy0.l(c2);
        }
    }

    public String getMailRUReferralId() {
        return "";
    }

    public String getYandexReferralId() {
        return "";
    }

    public void recordUserTypeMetrics() {
    }
}
