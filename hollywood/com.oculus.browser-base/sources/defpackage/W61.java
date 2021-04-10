package defpackage;

import android.view.View;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: W61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class W61 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        View view = (View) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC1960c71.f9585a;
        if (kh0 == th0) {
            ((TextView) view.findViewById(R.id.menu_item_text)).setText((CharSequence) uh0.g(th0));
        }
    }
}
