package defpackage;

import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryModernView;
import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView;

/* renamed from: V40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class V40 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 fv1;
        C1175Tf1 a2;
        UH0 uh0 = (UH0) obj;
        KH0 kh0 = (KH0) obj3;
        KeyboardAccessoryModernView keyboardAccessoryModernView = (KeyboardAccessoryModernView) ((KeyboardAccessoryView) obj2);
        AbstractC2297e60.a(uh0, keyboardAccessoryModernView, kh0);
        QH0 qh0 = I50.f;
        int i = 0;
        if (kh0 == qh0) {
            boolean h = uh0.h(qh0);
            keyboardAccessoryModernView.M.setVisibility(h ? 0 : 8);
            keyboardAccessoryModernView.N.setVisibility(h ? 0 : 8);
            RecyclerView recyclerView = keyboardAccessoryModernView.G;
            if (h) {
                i = 8;
            }
            recyclerView.setVisibility(i);
            if (!h) {
                RecyclerView recyclerView2 = keyboardAccessoryModernView.G;
                recyclerView2.getClass();
                recyclerView2.post(new RunnableC5536x50(recyclerView2));
                return;
            }
            return;
        }
        TH0 th0 = I50.h;
        View$OnClickListenerC5706y50 y50 = null;
        if (kh0 == th0) {
            Runnable runnable = (Runnable) uh0.g(th0);
            ImageView imageView = keyboardAccessoryModernView.M;
            if (runnable != null) {
                y50 = new View$OnClickListenerC5706y50(runnable);
            }
            imageView.setOnClickListener(y50);
            return;
        }
        TH0 th02 = I50.e;
        if (kh0 == th02) {
            keyboardAccessoryModernView.N.setText((String) uh0.g(th02));
            return;
        }
        TH0 th03 = I50.j;
        if (kh0 == th03) {
            keyboardAccessoryModernView.O = (Callback) uh0.g(th03);
            return;
        }
        QH0 qh02 = I50.k;
        if (kh0 == qh02) {
            if (keyboardAccessoryModernView.d() == null) {
                fv1 = null;
            } else {
                fv1 = new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(keyboardAccessoryModernView.d());
                fv1.L = true;
            }
            if (uh0.h(qh02) && fv1 != null) {
                Tm1 b = AbstractC3832n50.b();
                if (b != null && (b.getTriggerState("IPH_KeyboardAccessoryAddressFilling") == 0 || b.getTriggerState("IPH_KeyboardAccessoryPasswordFilling") == 0 || b.getTriggerState("IPH_KeyboardAccessoryPaymentFilling") == 0)) {
                    i = 1;
                }
                if (i != 0 && (a2 = AbstractC3832n50.a("IPH_KeyboardAccessoryBarSwiping", fv1, keyboardAccessoryModernView.G, null)) != null) {
                    a2.f();
                }
            }
        }
    }
}
