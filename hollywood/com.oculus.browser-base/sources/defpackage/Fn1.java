package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChipView;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* renamed from: Fn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Fn1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        KH0 kh0 = (KH0) obj3;
        ChipView chipView = (ChipView) ((ViewLookupCachingFrameLayout) obj2).d(R.id.trendy_term_chip);
        TH0 th0 = Kn1.f8388a;
        if (kh0 == th0) {
            chipView.F.setText((CharSequence) uh0.g(th0));
            return;
        }
        SH0 sh0 = Kn1.b;
        if (kh0 == sh0) {
            chipView.c(uh0.f(sh0), true);
            return;
        }
        TH0 th02 = Kn1.c;
        if (kh0 == th02) {
            chipView.setOnClickListener((View.OnClickListener) uh0.g(th02));
        }
    }
}
