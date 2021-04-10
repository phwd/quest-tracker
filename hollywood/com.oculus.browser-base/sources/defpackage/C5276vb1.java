package defpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: vb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5276vb1 {

    /* renamed from: a  reason: collision with root package name */
    public final VL0 f11488a;
    public final C1280Va b;

    public C5276vb1(VL0 vl0, C1280Va va) {
        this.f11488a = vl0;
        this.b = va;
    }

    public static void a(TabModel tabModel, List list) {
        N81 j = tabModel.j();
        for (int i = 0; i < j.getCount(); i++) {
            list.add(j.getTabAt(i));
        }
    }

    public void b() {
        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) this.f11488a.f9078a;
        ea1.l(false).d();
        ea1.l(true).d();
        ArrayList arrayList = new ArrayList(ea1.p());
        a(ea1.l(false), arrayList);
        a(ea1.l(true), arrayList);
        ((AbstractC0246Ea1) this.f11488a.f9078a).j = true;
        for (int i = 0; i < arrayList.size(); i++) {
            Tab tab = (Tab) arrayList.get(i);
            if (tab.d()) {
                tab.t();
                tab.l().f().g();
            }
            if (!this.b.b(tab.getId())) {
                VL0 vl0 = this.f11488a;
                String s = tab.s();
                Objects.requireNonNull(vl0);
                if (!AbstractC5154ur1.g(s)) {
                    this.b.a(tab.getId(), new C5446wb1(tab, null));
                    WL0.e(tab).c();
                }
            }
        }
    }
}
