package defpackage;

import android.view.View;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Ts  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1203Ts extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1569Zs f8991a;

    public C1203Ts(C1569Zs zs) {
        this.f8991a = zs;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1569Zs zs = this.f8991a;
        View view = (View) obj;
        zs.f.onResult((Tab) zs.b.get());
    }
}
