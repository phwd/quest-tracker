package defpackage;

import com.oculus.browser.R;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: P61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P61 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View$OnClickListenerC5098uY0 f8668a;
    public final /* synthetic */ U61 b;

    public P61(U61 u61, View$OnClickListenerC5098uY0 uy0) {
        this.b = u61;
        this.f8668a = uy0;
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        if (i == 3) {
            this.b.h(false);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void F(Tab tab) {
        this.f8668a.k(this.b, Integer.valueOf(tab.getId()));
    }

    @Override // defpackage.AbstractC5783ya1
    public void G(Tab tab) {
        this.b.j();
        U61.e(this.b);
        this.f8668a.k(this.b, Integer.valueOf(tab.getId()));
    }

    @Override // defpackage.AbstractC5783ya1
    public void H(Tab tab) {
        if (this.b.b.h(AbstractC5033u71.g)) {
            View$OnClickListenerC5098uY0 uy0 = this.f8668a;
            C4076oY0 c = C4076oY0.c(tab.getTitle(), this.b, 0, 11);
            c.c = this.b.f9006a.getString(R.string.f63950_resource_name_obfuscated_RES_2131953712);
            String string = this.b.f9006a.getString(R.string.f63930_resource_name_obfuscated_RES_2131953710);
            Integer valueOf = Integer.valueOf(tab.getId());
            c.d = string;
            c.e = valueOf;
            uy0.l(c);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        List f = this.b.f(tab.getId());
        if (f.size() == 0) {
            this.b.h(false);
            return;
        }
        int id = tab.getId();
        U61 u61 = this.b;
        if (id == u61.q) {
            u61.q = ((Tab) f.get(0)).getId();
        }
        this.b.j();
        U61.e(this.b);
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        U61 u61 = this.b;
        if (((AbstractC0246Ea1) u61.c).h) {
            u61.h(false);
        }
    }
}
