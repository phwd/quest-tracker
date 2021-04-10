package defpackage;

import org.chromium.chrome.browser.toolbar.NewTabButton;

/* renamed from: rk1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4623rk1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Vl1 f11218a;

    public C4623rk1(Vl1 vl1) {
        this.f11218a = vl1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Vl1 vl1 = this.f11218a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        C4261pd1 pd1 = vl1.b;
        if (pd1 != null) {
            NewTabButton newTabButton = pd1.i.M;
            if (newTabButton != null) {
                if (booleanValue) {
                    AbstractC3628lu1.c(newTabButton);
                } else {
                    AbstractC3628lu1.b(newTabButton);
                }
            }
        } else {
            T01 t01 = vl1.c;
            if (t01 != null) {
                t01.f8931a.f9248a.j(Z01.o, booleanValue);
            }
        }
    }
}
