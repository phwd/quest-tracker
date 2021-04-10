package defpackage;

import android.content.res.ColorStateList;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import org.chromium.chrome.browser.tasks.tab_management.TabGroupUiToolbarView;

/* renamed from: M71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class M71 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C2988i81 i81 = (C2988i81) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC2646g81.f9979a;
        if (th0 == kh0) {
            i81.f10123a.G.setOnClickListener((View.OnClickListener) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC2646g81.b;
        if (th02 == kh0) {
            i81.f10123a.F.setOnClickListener((View.OnClickListener) uh0.g(th02));
            return;
        }
        QH0 qh0 = AbstractC2646g81.c;
        if (qh0 == kh0) {
            TabGroupUiToolbarView tabGroupUiToolbarView = i81.f10123a;
            boolean h = uh0.h(qh0);
            if (tabGroupUiToolbarView.K != null) {
                for (int i = 0; i < tabGroupUiToolbarView.K.getChildCount(); i++) {
                    tabGroupUiToolbarView.K.getChildAt(i).setVisibility(h ? 0 : 4);
                }
                return;
            }
            throw new IllegalStateException("Current Toolbar doesn't have a container view");
        }
        SH0 sh0 = AbstractC2646g81.d;
        if (sh0 == kh0) {
            i81.f10123a.a(uh0.f(sh0));
            return;
        }
        TH0 th03 = AbstractC2646g81.e;
        if (th03 == kh0) {
            i81.f10123a.b((ColorStateList) uh0.g(th03));
            return;
        }
        SH0 sh02 = AbstractC2646g81.f;
        if (sh02 == kh0) {
            i81.f10123a.G.setImageResource(uh0.f(sh02));
            return;
        }
        TH0 th04 = AbstractC2646g81.g;
        if (th04 == kh0) {
            int intValue = ((Integer) uh0.g(th04)).intValue();
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) i81.b.U;
            linearLayoutManager.C1(intValue - ((linearLayoutManager.l1() - linearLayoutManager.k1()) / 2), 0);
            return;
        }
        TH0 th05 = AbstractC2646g81.h;
        if (th05 == kh0) {
            i81.f10123a.G.setContentDescription((String) uh0.g(th05));
            return;
        }
        TH0 th06 = AbstractC2646g81.i;
        if (th06 == kh0) {
            i81.f10123a.F.setContentDescription((String) uh0.g(th06));
        }
    }
}
