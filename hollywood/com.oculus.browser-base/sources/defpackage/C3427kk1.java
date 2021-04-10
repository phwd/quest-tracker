package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: kk1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3427kk1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Uk1 f10299a;

    public C3427kk1(Uk1 uk1) {
        this.f10299a = uk1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        ((ChromeActivity) this.f10299a.u0).l1(((Integer) obj).intValue(), null);
    }
}
