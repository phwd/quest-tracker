package defpackage;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: DM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DM implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        RecyclerView recyclerView;
        UH0 uh0 = (UH0) obj;
        ViewGroup viewGroup = (ViewGroup) obj2;
        KH0 kh0 = (KH0) obj3;
        QH0 qh0 = N01.e;
        boolean z = true;
        if (kh0 == qh0) {
            if (!uh0.h(qh0) || !uh0.h(N01.g)) {
                z = false;
            }
            IM.a(viewGroup, uh0, z);
            return;
        }
        QH0 qh02 = N01.g;
        if (kh0 == qh02) {
            if (!uh0.h(qh0) || !uh0.h(qh02)) {
                z = false;
            }
            IM.a(viewGroup, uh0, z);
            return;
        }
        SH0 sh0 = N01.j;
        if (kh0 == sh0) {
            TH0 th0 = N01.i;
            if (uh0.g(th0) != null && uh0.h(N01.d)) {
                FrameLayout frameLayout = ((XO) uh0.g(th0)).o;
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.topMargin = uh0.f(sh0);
                    frameLayout.setLayoutParams(layoutParams);
                }
            }
        } else if (kh0 == N01.k) {
            TH0 th02 = N01.i;
            if (uh0.g(th02) != null && (recyclerView = ((FO) ((XO) uh0.g(th02)).t).c) != null) {
                recyclerView.p0(0);
            }
        }
    }
}
