package defpackage;

import android.content.res.ColorStateList;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.toolbar.TabSwitcherButtonView;

/* renamed from: Jc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0557Jc1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        TabSwitcherButtonView tabSwitcherButtonView = (TabSwitcherButtonView) obj2;
        KH0 kh0 = (KH0) obj3;
        SH0 sh0 = AbstractC0496Ic1.f8235a;
        if (sh0 == kh0) {
            int f = uh0.f(sh0);
            tabSwitcherButtonView.setContentDescription(tabSwitcherButtonView.getResources().getQuantityString(R.plurals.f42640_resource_name_obfuscated_RES_2131820556, f, Integer.valueOf(f)));
            tabSwitcherButtonView.P.g(f, false);
            return;
        }
        TH0 th0 = AbstractC0496Ic1.b;
        if (th0 == kh0) {
            tabSwitcherButtonView.setOnClickListener((View.OnClickListener) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC0496Ic1.c;
        if (th02 == kh0) {
            tabSwitcherButtonView.setOnLongClickListener((View.OnLongClickListener) uh0.g(th02));
            return;
        }
        TH0 th03 = AbstractC0496Ic1.d;
        if (th03 == kh0) {
            tabSwitcherButtonView.P.c((ColorStateList) uh0.g(th03));
            return;
        }
        QH0 qh0 = AbstractC0496Ic1.e;
        if (qh0 == kh0) {
            tabSwitcherButtonView.setEnabled(uh0.h(qh0));
        }
    }
}
