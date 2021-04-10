package defpackage;

import android.widget.TextView;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Dc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0189Dc implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C1672aX aXVar = (C1672aX) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC2194dX.c;
        if (th0 == kh0) {
            aXVar.F.setText((CharSequence) uh0.g(th0));
            return;
        }
        SH0 sh0 = AbstractC4851t31.f11318a;
        if (kh0 == sh0) {
            TextView textView = aXVar.F;
            boolean z = !(!AbstractC4476qs0.a(uh0.f(sh0)));
            textView.setTextAppearance(z ? R.style.f72150_resource_name_obfuscated_RES_2132017788 : R.style.f72130_resource_name_obfuscated_RES_2132017786);
            aXVar.G.setImageTintList(AbstractC2934hr.c(aXVar.getContext(), z));
            return;
        }
        SH0 sh02 = AbstractC4851t31.b;
        if (kh0 == sh02) {
            int f = uh0.f(sh02);
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            aXVar.setLayoutDirection(f);
            return;
        }
        QH0 qh0 = AbstractC2194dX.b;
        if (kh0 == qh0) {
            boolean h = uh0.h(qh0);
            aXVar.G.setImageResource(h ? R.drawable.f30150_resource_name_obfuscated_RES_2131231055 : R.drawable.f30140_resource_name_obfuscated_RES_2131231054);
            aXVar.H = h;
            return;
        }
        TH0 th02 = AbstractC2194dX.f9787a;
        if (kh0 == th02) {
            XW xw = (XW) uh0.g(th02);
            if (xw != null) {
                aXVar.setOnClickListener(new View$OnClickListenerC1852bX(xw));
                aXVar.I = new RunnableC2023cX(xw);
                return;
            }
            aXVar.setOnClickListener(null);
            aXVar.I = null;
        }
    }
}
