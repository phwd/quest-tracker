package defpackage;

import android.text.Spannable;
import com.oculus.browser.R;

/* renamed from: Lc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0677Lc implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C5965ze1 ze1 = (C5965ze1) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC0014Ae1.c;
        if (th0 == kh0) {
            C2803h4 h4Var = (C2803h4) uh0.g(th0);
            ze1.F = h4Var;
            h4Var.f10046a.add(ze1);
            return;
        }
        TH0 th02 = AbstractC0014Ae1.f7684a;
        if (kh0 == th02) {
            Spannable spannable = (Spannable) uh0.g(th02);
            ze1.H = (int) ze1.getPaint().measureText(spannable, 0, spannable.length());
            ze1.setText(spannable);
            return;
        }
        TH0 th03 = AbstractC0014Ae1.b;
        if (kh0 == th03) {
            String str = (String) uh0.g(th03);
            ze1.G = (int) ze1.getPaint().measureText(str, 0, str.length());
            return;
        }
        SH0 sh0 = AbstractC4851t31.f11318a;
        if (kh0 == sh0) {
            ze1.setTextColor(ze1.getResources().getColor(AbstractC4476qs0.a(uh0.f(sh0)) ^ true ? R.color.f11470_resource_name_obfuscated_RES_2131099837 : R.color.f11570_resource_name_obfuscated_RES_2131099847));
        }
    }
}
