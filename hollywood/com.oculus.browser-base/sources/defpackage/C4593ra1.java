package defpackage;

import J.N;
import com.oculus.browser.PanelApp;

/* renamed from: ra1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4593ra1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C5613xa1 f11204a;

    public C4593ra1(C5613xa1 xa1) {
        this.f11204a = xa1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Float f = (Float) obj;
        PanelApp c = this.f11204a.c();
        if (c != null) {
            N.M1nC8n1C(c.f9704J, f.floatValue());
        }
    }
}
