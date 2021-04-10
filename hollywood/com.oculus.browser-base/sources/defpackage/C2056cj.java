package defpackage;

import com.oculus.browser.R;

/* renamed from: cj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2056cj implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C3251jj jjVar = (C3251jj) obj2;
        KH0 kh0 = (KH0) obj3;
        SH0 sh0 = AbstractC3080ij.f10157a;
        if (sh0 == kh0) {
            jjVar.f10231a.findViewById(R.id.bottom_controls_wrapper).getLayoutParams().height = uh0.f(sh0);
            return;
        }
        SH0 sh02 = AbstractC3080ij.b;
        if (sh02 == kh0) {
            jjVar.b.f9081J = uh0.f(sh02);
            return;
        }
        QH0 qh0 = AbstractC3080ij.c;
        if (qh0 == kh0) {
            jjVar.f10231a.setVisibility(uh0.h(qh0) ? 0 : 4);
            return;
        }
        QH0 qh02 = AbstractC3080ij.d;
        if (qh02 == kh0) {
            jjVar.b.K = uh0.h(qh02);
        }
    }
}
