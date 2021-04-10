package defpackage;

import J.N;
import android.app.Activity;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.toolbar.bottom.ScrollingBottomViewResourceFrameLayout;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: fj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2568fj {

    /* renamed from: a  reason: collision with root package name */
    public final C2910hj f9944a;
    public L71 b;

    public C2568fj(Activity activity, WindowAndroid windowAndroid, AbstractC2642g70 g70, ResourceManager resourceManager, AbstractC1888bk bkVar, UT ut, ScrollingBottomViewResourceFrameLayout scrollingBottomViewResourceFrameLayout, L71 l71, AbstractC0956Pq0 pq0) {
        I61 i61;
        UH0 uh0 = new UH0(AbstractC3080ij.e);
        VP0 vp0 = new VP0(scrollingBottomViewResourceFrameLayout, scrollingBottomViewResourceFrameLayout.f10786J);
        ZH0.a(uh0, new C3251jj(scrollingBottomViewResourceFrameLayout, vp0), new C2056cj());
        D70 d70 = (D70) g70;
        new C0297Ew(uh0, vp0, new C2227dj(), d70.l0, true);
        scrollingBottomViewResourceFrameLayout.findViewById(R.id.bottom_container_slot).getLayoutParams().height = scrollingBottomViewResourceFrameLayout.getResources().getDimensionPixelOffset(R.dimen.f16850_resource_name_obfuscated_RES_2131165304);
        C2910hj hjVar = new C2910hj(windowAndroid, uh0, bkVar, ut, scrollingBottomViewResourceFrameLayout.getResources().getDimensionPixelOffset(R.dimen.f16850_resource_name_obfuscated_RES_2131165304), pq0);
        this.f9944a = hjVar;
        resourceManager.a().c.put(scrollingBottomViewResourceFrameLayout.getId(), scrollingBottomViewResourceFrameLayout.G);
        this.b = l71;
        C1184Ti1.f8977a = scrollingBottomViewResourceFrameLayout.getResources().getDimensionPixelSize(R.dimen.f16850_resource_name_obfuscated_RES_2131165304);
        hjVar.L = false;
        hjVar.i();
        hjVar.g();
        vp0.K = hjVar.f();
        d70.g(vp0);
        L71 l712 = this.b;
        if (l712 != null) {
            C2397ej ejVar = new C2397ej(hjVar);
            O71 o71 = (O71) l712;
            if (UmaSessionStats.b()) {
                N.MT4iKtWs("TabGroupsAndroidSyntheticTrial", "Downloaded_Enabled");
            }
            ChromeActivity chromeActivity = (ChromeActivity) activity;
            o71.R = chromeActivity;
            AbstractC0124Ca1 P = chromeActivity.P();
            TabContentManager tabContentManager = o71.R.x0;
            C1795b91 b91 = new C1795b91(1, o71.F, P, null, null, AbstractC4772sd1.a(), null, null, 2, null, null, o71.f8602J, true, "TabStrip");
            o71.O = b91;
            b91.j(o71.R.I0.i());
            o71.M = ZH0.a(o71.G, new C2988i81(o71.I, o71.O.G), new M71());
            if (!AbstractC4772sd1.g() || o71.K == null) {
                i61 = null;
            } else {
                I61 i612 = new I61(o71.F, P, tabContentManager, o71.R, (ViewGroup) activity.findViewById(R.id.coordinator), null, null, null, o71.R.K0, o71.K);
                o71.N = i612;
                i612.c(o71.F, P, tabContentManager, o71.O.F.F);
                I61 i613 = o71.N;
                Objects.requireNonNull(i613);
                i61 = i613;
            }
            UH0 uh02 = o71.G;
            ChromeActivity chromeActivity2 = o71.R;
            AbstractC1509Ys0 T0 = chromeActivity2.T0();
            AbstractC1117Sg1 sg1 = o71.H;
            ChromeActivity chromeActivity3 = o71.R;
            o71.P = new C2475f81(activity, ejVar, o71, uh02, P, chromeActivity2, T0, sg1, i61, chromeActivity3.Y, chromeActivity3, o71.L);
            if (AbstractC3842n81.f10474a == null) {
                Activity activity2 = ApplicationStatus.e;
                if (activity2 instanceof AbstractActivityC2601fu) {
                    AbstractC3842n81.f10474a = new C3671m81(((AbstractActivityC2601fu) activity2).P());
                }
            }
            if (!AbstractC4772sd1.a()) {
                M2 m2 = o71.R.Y;
                o71.Q = m2;
                m2.a(o71);
                AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) P;
                ea1.l(false).n(new N71(o71, ea1));
            }
        }
    }
}
