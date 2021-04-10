package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import org.chromium.chrome.browser.tasks.SingleTabView;

/* renamed from: lX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3561lX0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        SingleTabView singleTabView = (SingleTabView) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC5096uX0.f11416a;
        if (kh0 == th0) {
            singleTabView.setOnClickListener((View.OnClickListener) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC5096uX0.b;
        if (kh0 == th02) {
            singleTabView.F.setImageDrawable((Drawable) uh0.g(th02));
            return;
        }
        QH0 qh0 = AbstractC5096uX0.c;
        if (kh0 == qh0) {
            singleTabView.setVisibility(uh0.h(qh0) ? 0 : 8);
            return;
        }
        TH0 th03 = AbstractC5096uX0.d;
        if (kh0 == th03) {
            singleTabView.G.setText((String) uh0.g(th03));
        }
    }
}
