package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.List;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;
import org.chromium.chrome.browser.tasks.tab_management.TabGroupUiToolbarView;
import org.chromium.chrome.browser.tasks.tab_management.TabListRecyclerView;

/* renamed from: I61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I61 {

    /* renamed from: a  reason: collision with root package name */
    public final String f8200a;
    public final C1795b91 b;
    public final U61 c;
    public final UH0 d;
    public final ZH0 e;
    public C0371Gb1 f;
    public TabGridDialogView g;

    public I61(Context context, AbstractC0124Ca1 ca1, TabContentManager tabContentManager, AbstractC3226ja1 ja1, ViewGroup viewGroup, AbstractC3748md1 md1, AbstractC5209v91 v91, C0861Oc1 oc1, AbstractC0956Pq0 pq0, GP0 gp0) {
        String str = oc1 == null ? "TabGridDialogFromStrip" : "TabGridDialogInSwitcher";
        this.f8200a = str;
        UH0 uh0 = new UH0(AbstractC5033u71.x);
        this.d = uh0;
        TabGridDialogView tabGridDialogView = (TabGridDialogView) viewGroup.findViewById(R.id.dialog_parent_view);
        this.g = tabGridDialogView;
        if (tabGridDialogView == null) {
            LayoutInflater.from(context).inflate(R.layout.f41720_resource_name_obfuscated_RES_2131624481, viewGroup, true);
            TabGridDialogView tabGridDialogView2 = (TabGridDialogView) viewGroup.findViewById(R.id.dialog_parent_view);
            this.g = tabGridDialogView2;
            tabGridDialogView2.S = gp0;
        }
        U61 u61 = new U61(context, this, uh0, ca1, ja1, md1, oc1, pq0, new View$OnClickListenerC5098uY0((Activity) context, this.g.N, null), str);
        this.c = u61;
        int i = (!AbstractC4772sd1.f() || !SysUtils.isLowEndDevice()) ? 0 : 3;
        tabContentManager.getClass();
        C1795b91 b91 = new C1795b91(i, context, ca1, new G61(tabContentManager), null, false, v91, u61.j, 1, null, null, viewGroup, false, str);
        this.b = b91;
        TabListRecyclerView tabListRecyclerView = b91.G;
        TabGroupUiToolbarView tabGroupUiToolbarView = (TabGroupUiToolbarView) LayoutInflater.from(context).inflate(R.layout.f37190_resource_name_obfuscated_RES_2131624028, (ViewGroup) tabListRecyclerView, false);
        Context context2 = tabGroupUiToolbarView.getContext();
        tabGroupUiToolbarView.G.setImageResource(R.drawable.f29540_resource_name_obfuscated_RES_2131230994);
        ((ViewGroup.MarginLayoutParams) tabGroupUiToolbarView.L.getLayoutParams()).setMarginStart((int) context2.getResources().getDimension(R.dimen.f25570_resource_name_obfuscated_RES_2131166176));
        tabGroupUiToolbarView.L.setGravity(8388627);
        tabGroupUiToolbarView.L.setTextAppearance(R.style.f71300_resource_name_obfuscated_RES_2132017703);
        if (!AbstractC4772sd1.f()) {
            tabGroupUiToolbarView.L.setFocusable(false);
            tabGroupUiToolbarView.L.setBackgroundColor(0);
            tabGroupUiToolbarView.M.removeView(tabGroupUiToolbarView.H);
        }
        this.e = ZH0.a(uh0, new C5203v71(tabGroupUiToolbarView, tabListRecyclerView, this.g), new H61());
    }

    public void a() {
        this.b.destroy();
        U61 u61 = this.c;
        AbstractC5783ya1 ya1 = u61.e;
        if (ya1 != null) {
            ((AbstractC0246Ea1) u61.c).c.h(ya1);
        }
        AbstractC0124Ca1 ca1 = u61.c;
        ((AbstractC0246Ea1) ca1).f.c(u61.d);
        C3493l60.F.h(u61.p);
        this.e.b();
        C0371Gb1 gb1 = this.f;
        if (gb1 != null) {
            gb1.a();
        }
    }

    public boolean b() {
        if (!d()) {
            return false;
        }
        this.c.h(true);
        AbstractC3535lK0.a("TabGridDialog.Exit");
        return true;
    }

    public void c(Context context, AbstractC0124Ca1 ca1, TabContentManager tabContentManager, C5039u91 u91) {
        C0676Lb1 lb1;
        if (AbstractC4772sd1.f()) {
            C0371Gb1 gb1 = new C0371Gb1(context, (ViewGroup) this.g.findViewById(R.id.dialog_container_view), ca1, tabContentManager, SysUtils.isLowEndDevice() ? 3 : 0);
            this.f = gb1;
            lb1 = gb1.i;
        } else {
            this.f = null;
            lb1 = null;
        }
        U61 u61 = this.c;
        u61.o = lb1;
        u61.n = u91;
        ((AbstractC0246Ea1) u61.c).c.a(u61.e);
        u61.t = new K61(u61);
        u61.b.m(AbstractC5033u71.f11387a, new N61(u61));
        u61.b.m(AbstractC5033u71.b, new O61(u61));
        if (AbstractC4772sd1.f()) {
            L61 l61 = new L61(u61);
            u61.p = l61;
            C3493l60.F.a(l61);
            u61.b.m(AbstractC5033u71.r, new S61(u61));
            u61.b.m(AbstractC5033u71.s, new M61(u61));
            ((C0676Lb1) u61.o).a(u61.f9006a.getString(R.string.f63110_resource_name_obfuscated_RES_2131953628), Integer.valueOf((int) R.plurals.f42610_resource_name_obfuscated_RES_2131820553), new C5616xb1(u61.o, 2), 1, null);
            u61.b.m(AbstractC5033u71.q, new V61(u61.t));
        }
        this.b.j(null);
    }

    public boolean d() {
        return this.c.b.h(AbstractC5033u71.g);
    }

    public void e(List list) {
        this.b.m(list);
        U61 u61 = this.c;
        if (list == null) {
            u61.q = -1;
        } else {
            AbstractC3568la1 d2 = ((AbstractC0246Ea1) u61.c).c.d();
            u61.q = d2.getTabAt(d2.i((Tab) list.get(0))).getId();
        }
        int i = u61.q;
        if (i != -1) {
            C0861Oc1 oc1 = u61.i;
            if (oc1 != null) {
                u61.b.m(AbstractC5033u71.i, oc1.a(i));
            } else {
                u61.b.m(AbstractC5033u71.i, null);
            }
            u61.j();
            if (u61.q != ((AbstractC0246Ea1) u61.c).k()) {
                u61.b.m(AbstractC5033u71.o, 0);
            } else {
                u61.b.m(AbstractC5033u71.o, Integer.valueOf(Math.max(u61.f(u61.q).indexOf(((AbstractC0246Ea1) u61.c).o(u61.q)) - 2, 0)));
            }
            u61.b.m(AbstractC5033u71.h, u61.l);
            u61.b.j(AbstractC5033u71.g, true);
            return;
        }
        u61.b.j(AbstractC5033u71.g, false);
    }
}
