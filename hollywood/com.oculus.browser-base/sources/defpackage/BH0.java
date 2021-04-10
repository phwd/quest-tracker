package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.widget.promo.PromoCardView;
import org.chromium.ui.widget.ButtonCompat;

/* renamed from: BH0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BH0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        PromoCardView promoCardView = (PromoCardView) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC5742yH0.b;
        if (kh0 == th0) {
            promoCardView.F.setImageDrawable((Drawable) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC5742yH0.g;
        if (kh0 == th02) {
            promoCardView.F.setImageTintList((ColorStateList) uh0.g(th02));
            return;
        }
        TH0 th03 = AbstractC5742yH0.c;
        if (kh0 == th03) {
            promoCardView.G.setText((CharSequence) uh0.g(th03));
            return;
        }
        TH0 th04 = AbstractC5742yH0.d;
        int i = 0;
        if (kh0 == th04) {
            TextView textView = promoCardView.I;
            if (textView == null) {
                AbstractC1220Ua0.f("PromoCardViewBinder", "Description does not exist in the promo card.", new Object[0]);
            } else {
                textView.setText((CharSequence) uh0.g(th04));
            }
        } else {
            TH0 th05 = AbstractC5742yH0.e;
            if (kh0 == th05) {
                promoCardView.H.setText((CharSequence) uh0.g(th05));
                return;
            }
            TH0 th06 = AbstractC5742yH0.f;
            if (kh0 == th06) {
                ButtonCompat buttonCompat = promoCardView.f10826J;
                if (buttonCompat == null) {
                    AbstractC1220Ua0.f("PromoCardViewBinder", "Description does not exist in the promo card.", new Object[0]);
                } else {
                    buttonCompat.setText((CharSequence) uh0.g(th06));
                }
            } else {
                QH0 qh0 = AbstractC5742yH0.f11674a;
                if (kh0 == qh0) {
                    ButtonCompat buttonCompat2 = promoCardView.f10826J;
                    if (buttonCompat2 == null) {
                        AbstractC1220Ua0.f("PromoCardViewBinder", "Secondary button does not exist in the promo card.", new Object[0]);
                        return;
                    }
                    if (!uh0.h(qh0)) {
                        i = 8;
                    }
                    buttonCompat2.setVisibility(i);
                    return;
                }
                TH0 th07 = AbstractC5742yH0.h;
                if (kh0 == th07) {
                    Callback callback = (Callback) uh0.g(th07);
                    ButtonCompat buttonCompat3 = promoCardView.H;
                    callback.getClass();
                    buttonCompat3.setOnClickListener(new View$OnClickListenerC5912zH0(callback));
                    return;
                }
                TH0 th08 = AbstractC5742yH0.i;
                if (kh0 != th08) {
                    return;
                }
                if (promoCardView.f10826J == null) {
                    AbstractC1220Ua0.f("PromoCardViewBinder", "Secondary button does not exist in the promo card.", new Object[0]);
                    return;
                }
                Callback callback2 = (Callback) uh0.g(th08);
                ButtonCompat buttonCompat4 = promoCardView.f10826J;
                callback2.getClass();
                buttonCompat4.setOnClickListener(new AH0(callback2));
            }
        }
    }
}
