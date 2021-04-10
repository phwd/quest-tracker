package defpackage;

import android.view.View;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: JQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class JQ0 {
    public static void a(UH0 uh0, View view, KH0 kh0) {
        TH0 th0 = IQ0.f8225a;
        if (th0 == kh0) {
            ((TextView) view.findViewById(R.id.continuous_search_list_item_text)).setText((CharSequence) uh0.g(th0));
            return;
        }
        QH0 qh0 = IQ0.c;
        if (qh0 == kh0) {
            view.setSelected(uh0.h(qh0));
            return;
        }
        TH0 th02 = IQ0.d;
        if (th02 == kh0) {
            view.setOnClickListener((View.OnClickListener) uh0.g(th02));
        }
    }
}
