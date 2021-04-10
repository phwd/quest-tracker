package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;
import org.chromium.chrome.browser.tasks.tab_management.TabSelectionEditorLayout;
import org.chromium.chrome.browser.tasks.tab_management.TabSelectionEditorToolbar;
import org.chromium.components.browser_ui.widget.LoadingView;

/* renamed from: Gb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0371Gb1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8096a;
    public final ViewGroup b;
    public final AbstractC0124Ca1 c;
    public final TabSelectionEditorLayout d;
    public final C1795b91 e;
    public final C3209jS0 f;
    public final UH0 g;
    public final ZH0 h;
    public final C0676Lb1 i;

    public C0371Gb1(Context context, ViewGroup viewGroup, AbstractC0124Ca1 ca1, TabContentManager tabContentManager, int i2) {
        C3209jS0 js0 = new C3209jS0();
        this.f = js0;
        this.f8096a = context;
        this.b = viewGroup;
        this.c = ca1;
        TabSelectionEditorLayout tabSelectionEditorLayout = (TabSelectionEditorLayout) LayoutInflater.from(context).inflate(R.layout.f41760_resource_name_obfuscated_RES_2131624485, viewGroup, false).findViewById(R.id.selectable_list);
        this.d = tabSelectionEditorLayout;
        tabContentManager.getClass();
        C1795b91 b91 = new C1795b91(i2, context, ca1, new C5786yb1(tabContentManager), null, false, null, null, 0, new C5956zb1(this), null, tabSelectionEditorLayout, false, "TabSelectionEditor");
        this.e = b91;
        b91.j(null);
        b91.k(4, new L70(R.layout.f38020_resource_name_obfuscated_RES_2131624111), new C0005Ab1());
        IK0 ik0 = b91.G.U;
        if (ik0 instanceof GridLayoutManager) {
            ((GridLayoutManager) ik0).M = new C0188Db1(this, ik0);
        }
        TabListRecyclerView tabListRecyclerView = b91.G;
        AbstractC5750yK0 yk0 = tabListRecyclerView.T;
        tabSelectionEditorLayout.U = true;
        tabSelectionEditorLayout.e(yk0, tabListRecyclerView);
        tabSelectionEditorLayout.S = (TabSelectionEditorToolbar) tabSelectionEditorLayout.f(R.layout.f41770_resource_name_obfuscated_RES_2131624486, js0, 0, 0, 0, null, false, true);
        tabSelectionEditorLayout.T = viewGroup;
        js0.b = true;
        js0.e();
        Map c2 = UH0.c(AbstractC0736Mb1.k);
        QH0 qh0 = AbstractC0736Mb1.f8486a;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = false;
        ((HashMap) c2).put(qh0, gh0);
        UH0 uh0 = new UH0(c2, null);
        this.g = uh0;
        this.h = new ZH0(uh0, tabSelectionEditorLayout, new C0066Bb1(), false);
        this.i = new C0676Lb1(context, ca1, new C0127Cb1(this), uh0, js0);
    }

    public void a() {
        this.e.destroy();
        TabSelectionEditorLayout tabSelectionEditorLayout = this.d;
        AbstractC5750yK0 yk0 = tabSelectionEditorLayout.G;
        yk0.F.unregisterObserver(tabSelectionEditorLayout.R);
        tabSelectionEditorLayout.M.w0.d.c(tabSelectionEditorLayout);
        AbstractView$OnClickListenerC2014cS0 cs0 = tabSelectionEditorLayout.M;
        cs0.X0 = true;
        C3209jS0 js0 = cs0.w0;
        if (js0 != null) {
            js0.d.c(cs0);
        }
        if (cs0.A0 != null) {
            cs0.N();
        }
        LoadingView loadingView = tabSelectionEditorLayout.K;
        loadingView.removeCallbacks(loadingView.I);
        loadingView.removeCallbacks(loadingView.K);
        loadingView.H.clear();
        tabSelectionEditorLayout.L.q0(null);
        C0676Lb1 lb1 = this.i;
        lb1.f.destroy();
        AbstractC0124Ca1 ca1 = lb1.b;
        if (ca1 != null) {
            ((AbstractC0246Ea1) ca1).f.c(lb1.g);
        }
        this.h.b();
    }
}
