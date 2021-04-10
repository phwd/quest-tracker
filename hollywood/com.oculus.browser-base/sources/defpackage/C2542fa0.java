package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.search_engines.settings.SearchEngineSettings;

/* renamed from: fa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2542fa0 extends AbstractC4758sY0 {
    public C2542fa0(LocaleManager localeManager) {
    }

    @Override // defpackage.AbstractC4758sY0
    public void c(Object obj) {
        Context applicationContext = ContextUtils.getApplicationContext();
        String name = SearchEngineSettings.class.getName();
        Intent l = AbstractC2531fV.l(applicationContext, XS0.class);
        if (!(applicationContext instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        if (name != null) {
            l.putExtra("show_fragment", name);
        }
        U20.q(applicationContext, l);
    }

    @Override // defpackage.AbstractC4758sY0
    public void d(Object obj) {
    }
}
