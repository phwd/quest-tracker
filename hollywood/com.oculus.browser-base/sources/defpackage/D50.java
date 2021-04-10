package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChipView;

/* renamed from: D50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D50 extends AbstractC2127d60 {
    public final View Z;

    public D50(ViewGroup viewGroup) {
        super(viewGroup, R.layout.f39030_resource_name_obfuscated_RES_2131624212);
        this.Z = viewGroup.getRootView();
    }

    @Override // defpackage.AbstractC2127d60
    public void x(G50 g50, View view) {
        F50 f50 = (F50) g50;
        ChipView chipView = (ChipView) view;
        int i = f50.c.d;
        String str = f50.d;
        if (str != null) {
            if (!str.equals("IPH_KeyboardAccessoryPaymentOffer")) {
                AbstractC3832n50.c(f50.d, chipView, this.Z, null);
            } else if (i != 0) {
                C1175Tf1 a2 = AbstractC3832n50.a(f50.d, new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(chipView.G), this.Z, f50.c.c);
                if (a2 != null) {
                    a2.f();
                }
            } else {
                AbstractC3832n50.c(f50.d, chipView, this.Z, f50.c.c);
            }
        }
        chipView.F.setText(f50.c.f10810a);
        if (!f50.c.c.isEmpty()) {
            TextView textView = chipView.F;
            textView.setContentDescription(f50.c.f10810a + " " + f50.c.c);
        }
        chipView.b().setText(f50.c.b);
        chipView.b().setVisibility(f50.c.b.isEmpty() ? 8 : 0);
        if (i == 0) {
            i = -1;
        }
        chipView.c(i, false);
        chipView.setOnClickListener(new C50(f50, f50.b));
    }
}
