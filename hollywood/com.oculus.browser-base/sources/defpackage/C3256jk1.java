package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.homepage.settings.HomepageSettings;

/* renamed from: jk1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3256jk1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final QX f10233a;

    public C3256jk1(QX qx) {
        this.f10233a = qx;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f10233a.d.b((Context) obj, HomepageSettings.class, null);
    }
}
