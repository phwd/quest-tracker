package defpackage;

import org.chromium.url.GURL;

/* renamed from: Ez0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0304Ez0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C0730Lz0 lz0 = (C0730Lz0) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC0670Kz0.f8397a;
        if (th0 == kh0) {
            lz0.c.setText(AbstractC1911br1.c((GURL) uh0.g(th0), 1));
            return;
        }
        TH0 th02 = AbstractC0670Kz0.b;
        if (th02 == kh0) {
            lz0.d.setText((CharSequence) uh0.g(th02));
            return;
        }
        RH0 rh0 = AbstractC0670Kz0.c;
        if (rh0 == kh0) {
            lz0.e.setProgress(Math.round(uh0.e(rh0) * 100.0f));
            return;
        }
        QH0 qh0 = AbstractC0670Kz0.d;
        if (qh0 == kh0) {
            lz0.e.setVisibility(uh0.h(qh0) ? 0 : 4);
            return;
        }
        SH0 sh0 = AbstractC0670Kz0.e;
        if (sh0 == kh0) {
            lz0.f.setImageResource(uh0.f(sh0));
            return;
        }
        TH0 th03 = AbstractC0670Kz0.f;
        if (th03 == kh0) {
            lz0.f.setContentDescription((String) uh0.g(th03));
        } else if (AbstractC0670Kz0.g == kh0) {
            lz0.f.setOnClickListener(new View$OnClickListenerC0791Mz0(uh0));
        } else if (AbstractC0670Kz0.h == kh0) {
            lz0.g.setOnClickListener(new View$OnClickListenerC0852Nz0(uh0));
        }
    }
}
