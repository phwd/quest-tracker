package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import org.chromium.chrome.browser.search_engines.settings.SearchEngineSettings;

/* renamed from: vQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5252vQ0 extends AbstractC4758sY0 {

    /* renamed from: a  reason: collision with root package name */
    public Context f11479a;

    public C5252vQ0(Context context, AbstractC5082uQ0 uq0) {
        this.f11479a = context;
    }

    @Override // defpackage.AbstractC4758sY0
    public void c(Object obj) {
        Context context = this.f11479a;
        String name = SearchEngineSettings.class.getName();
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        if (name != null) {
            l.putExtra("show_fragment", name);
        }
        U20.q(context, l);
        AbstractC4912tQ0.b(1);
        int a2 = AbstractC4912tQ0.a();
        AbstractC3364kK0.g("Android.SearchEngineChoice.SearchEngineBeforeChoicePrompt", a2, 60);
        NU0.f8549a.n("search_engine_choice_default_type_before", a2);
    }
}
