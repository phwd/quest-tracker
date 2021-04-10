package defpackage;

import java.util.Collection;

/* renamed from: Bc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0067Bc implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C1478Yf yf = (C1478Yf) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC1539Zf.f9356a;
        if (kh0 == th0) {
            Collection collection = (Collection) uh0.g(th0);
            JW0 jw0 = (JW0) yf.G.T;
            if (collection != null) {
                jw0.I.t(collection);
            } else {
                jw0.I.clear();
            }
        } else {
            TH0 th02 = AbstractC1539Zf.b;
            if (kh0 == th02) {
                yf.F.F.setText((CharSequence) uh0.g(th02));
            }
        }
    }
}
