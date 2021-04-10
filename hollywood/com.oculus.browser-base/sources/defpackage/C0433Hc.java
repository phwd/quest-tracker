package defpackage;

import android.view.View;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: Hc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0433Hc implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        View view = (View) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = V6.b;
        if (th0 == kh0) {
            ((TextView) view.findViewById(R.id.omnibox_answer_line_1)).setText((CharSequence) uh0.g(th0));
            return;
        }
        TH0 th02 = V6.e;
        if (th02 == kh0) {
            ((TextView) view.findViewById(R.id.omnibox_answer_line_2)).setText((CharSequence) uh0.g(th02));
            return;
        }
        TH0 th03 = V6.c;
        if (th03 == kh0) {
            ((TextView) view.findViewById(R.id.omnibox_answer_line_1)).setContentDescription((CharSequence) uh0.g(th03));
            return;
        }
        TH0 th04 = V6.f;
        if (th04 == kh0) {
            ((TextView) view.findViewById(R.id.omnibox_answer_line_2)).setContentDescription((CharSequence) uh0.g(th04));
            return;
        }
        SH0 sh0 = V6.f9064a;
        if (sh0 == kh0) {
            ((TextView) view.findViewById(R.id.omnibox_answer_line_1)).setMaxLines(uh0.f(sh0));
            return;
        }
        SH0 sh02 = V6.d;
        if (sh02 == kh0) {
            ((TextView) view.findViewById(R.id.omnibox_answer_line_2)).setMaxLines(uh0.f(sh02));
        }
    }
}
