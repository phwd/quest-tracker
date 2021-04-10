package defpackage;

import J.N;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView;

/* renamed from: e60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2297e60 {
    public static boolean a(UH0 uh0, KeyboardAccessoryView keyboardAccessoryView, KH0 kh0) {
        OH0 oh0 = I50.f8198a;
        if (kh0 == oh0) {
            C1794b90 b90 = (C1794b90) uh0.g(oh0);
            AbstractC1829bL0 x40 = new X40();
            if (N.M09VlOh_("AutofillKeyboardAccessory")) {
                x40 = new Y40();
            }
            C2000cL0 cl0 = new C2000cL0(new J50(b90, new Z40(), new C1602a50(), new C1782b50()), x40);
            Objects.requireNonNull(keyboardAccessoryView);
            cl0.F.registerObserver(new Z50(keyboardAccessoryView));
            keyboardAccessoryView.G.q0(cl0);
        } else {
            MH0 mh0 = I50.i;
            if (kh0 != mh0) {
                QH0 qh0 = I50.b;
                if (kh0 == qh0) {
                    keyboardAccessoryView.c(uh0.h(qh0));
                } else {
                    QH0 qh02 = I50.c;
                    if (kh0 == qh02) {
                        keyboardAccessoryView.f10689J = uh0.h(qh02);
                        if (!uh0.h(qh0)) {
                            keyboardAccessoryView.c(false);
                        }
                    } else {
                        SH0 sh0 = I50.d;
                        if (kh0 == sh0) {
                            int f = uh0.f(sh0);
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) keyboardAccessoryView.getLayoutParams();
                            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, f);
                            keyboardAccessoryView.setLayoutParams(marginLayoutParams);
                        } else if (kh0 == I50.h || kh0 == I50.f || kh0 == I50.e || kh0 == I50.g || kh0 == I50.j || kh0 == I50.k) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            } else if (uh0.h(mh0)) {
                keyboardAccessoryView.K = true;
            }
        }
        return true;
    }

    public static AbstractC2127d60 b(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new C1956c60(viewGroup, R.layout.f38910_resource_name_obfuscated_RES_2131624200);
        }
        if (i != 1) {
            return null;
        }
        return new C1956c60(viewGroup, R.layout.f38930_resource_name_obfuscated_RES_2131624202);
    }
}
