package defpackage;

import android.view.View;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Us  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1264Us extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1569Zs f9052a;

    public C1264Us(C1569Zs zs) {
        this.f9052a = zs;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1569Zs zs = this.f9052a;
        View view = (View) obj;
        new H80(zs.f9379a, (Tab) zs.b.get(), zs.k, zs.m, zs.e.c);
    }
}
