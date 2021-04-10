package defpackage;

import com.oculus.browser.PanelApp;
import java.util.Objects;

/* renamed from: fw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2608fw0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final PanelApp f9967a;

    public C2608fw0(PanelApp panelApp) {
        this.f9967a = panelApp;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        PanelApp panelApp = this.f9967a;
        Objects.requireNonNull(panelApp);
        panelApp.A(((Integer) obj).intValue());
    }
}
