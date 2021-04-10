package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.promo.PromoCardView;

/* renamed from: xH0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5572xH0 {

    /* renamed from: a  reason: collision with root package name */
    public PromoCardView f11602a;
    public ZH0 b;
    public ViewTreeObserver$OnPreDrawListenerC1587a00 c;

    public C5572xH0(Context context, UH0 uh0, String str) {
        PromoCardView promoCardView = (PromoCardView) LayoutInflater.from(context).inflate(R.layout.f40930_resource_name_obfuscated_RES_2131624402, (ViewGroup) null, false);
        this.f11602a = promoCardView;
        this.b = ZH0.a(uh0, promoCardView, new BH0());
        Runnable runnable = (Runnable) uh0.g(AbstractC5742yH0.k);
        if (runnable != null) {
            ViewTreeObserver$OnPreDrawListenerC1587a00 a00 = new ViewTreeObserver$OnPreDrawListenerC1587a00(uh0.h(AbstractC5742yH0.j) ? this.f11602a.H : this.f11602a);
            this.c = a00;
            a00.H = 0.75d;
            a00.a(new C1387Ws0(new C5402wH0(runnable)));
        }
    }
}
