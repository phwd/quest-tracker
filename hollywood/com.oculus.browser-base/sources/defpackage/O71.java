package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.List;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.TabGroupUiToolbarView;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;

/* renamed from: O71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O71 implements AbstractC2304e81, L71, AbstractC0850Ny0 {
    public final Context F;
    public final UH0 G = new UH0(AbstractC2646g81.j);
    public final AbstractC1117Sg1 H;
    public final TabGroupUiToolbarView I;

    /* renamed from: J  reason: collision with root package name */
    public final ViewGroup f8602J;
    public final GP0 K;
    public final AbstractC0956Pq0 L;
    public ZH0 M;
    public I61 N;
    public C1795b91 O;
    public C2475f81 P;
    public M2 Q;
    public ChromeActivity R;

    public O71(ViewGroup viewGroup, AbstractC1117Sg1 sg1, GP0 gp0, AbstractC0956Pq0 pq0) {
        Context context = viewGroup.getContext();
        this.F = context;
        this.H = sg1;
        this.K = gp0;
        this.L = pq0;
        TabGroupUiToolbarView tabGroupUiToolbarView = (TabGroupUiToolbarView) LayoutInflater.from(context).inflate(R.layout.f37200_resource_name_obfuscated_RES_2131624029, viewGroup, false);
        this.I = tabGroupUiToolbarView;
        this.f8602J = tabGroupUiToolbarView.K;
        viewGroup.addView(tabGroupUiToolbarView);
    }

    @Override // defpackage.AbstractC0850Ny0
    public void b() {
        if (this.R.j0) {
            g();
            f();
        }
    }

    @Override // defpackage.AbstractC0850Ny0
    public void c() {
    }

    public final void f() {
        Tab j;
        if (AbstractC4772sd1.d()) {
            AbstractC3568la1 g = ((AbstractC0246Ea1) this.R.P()).c.g(false);
            if (!(g instanceof I71)) {
                if (g != null) {
                    g.getClass().getName();
                    return;
                }
                return;
            }
        }
        AbstractC2260du0 du0 = (AbstractC2260du0) this.R.T0().get();
        if ((du0 == null || !((AbstractC3838n70) du0).C()) && (j = ((AbstractC0246Ea1) this.R.P()).j()) != null) {
            ((I71) ((AbstractC0246Ea1) this.R.P()).c.d()).a0(j);
        }
    }

    public final void g() {
        C3910na1 na1 = ((AbstractC0246Ea1) this.R.P()).c;
        if (AbstractC4772sd1.d()) {
            AbstractC3568la1 g = na1.g(false);
            if (!(g instanceof I71)) {
                if (g != null) {
                    g.getClass().getName();
                    return;
                }
                return;
            }
        }
        I71 i71 = (I71) na1.g(false);
        I71 i712 = (I71) na1.g(true);
        AbstractC3364kK0.d("TabGroups.UserGroupCount", i71.k + i712.k);
        if (AbstractC4772sd1.f()) {
            int i = 0;
            for (int i2 = 0; i2 < i71.k; i2++) {
                if (AbstractC3842n81.c(C5383wB.q(i71.getTabAt(i2)).R) != null) {
                    i++;
                }
            }
            for (int i3 = 0; i3 < i712.k; i3++) {
                if (AbstractC3842n81.c(C5383wB.q(i712.getTabAt(i3)).R) != null) {
                    i++;
                }
            }
            AbstractC3364kK0.d("TabGroups.UserNamedGroupCount", i);
        }
    }

    public void h(List list) {
        AbstractC4448qj a2 = AbstractC5978zj.a(this.R.b0);
        if (list != null && list.size() > 1) {
            C5638xj xjVar = (C5638xj) a2;
            if (xjVar.o() == 0) {
                TabListRecyclerView tabListRecyclerView = this.O.G;
                if (!AbstractC4772sd1.c()) {
                    xjVar = null;
                }
                AbstractC3842n81.d("IPH_TabGroupsTapToSeeAnotherTab", tabListRecyclerView, xjVar);
            }
        }
        this.O.m(list);
    }
}
