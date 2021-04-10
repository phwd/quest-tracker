package defpackage;

import com.oculus.browser.R;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: np1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3955np1 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Q31 f10514a;
    public final /* synthetic */ C4126op1 b;

    public C3955np1(C4126op1 op1, Q31 q31) {
        this.b = op1;
        this.f10514a = q31;
    }

    @Override // defpackage.AbstractC5783ya1
    public void D(List list, boolean z) {
        if (!L(true)) {
            if (list.size() == 1) {
                H((Tab) list.get(0));
                return;
            }
            C4126op1 op1 = this.b;
            Objects.requireNonNull(op1);
            String format = String.format(Locale.getDefault(), "%d", Integer.valueOf(list.size()));
            View$OnClickListenerC5098uY0 U = op1.c.U();
            C4076oY0 c = C4076oY0.c(format, op1, 0, z ? 12 : 30);
            c.c = op1.d.getString(R.string.f63940_resource_name_obfuscated_RES_2131953711);
            c.d = op1.d.getString(R.string.f63930_resource_name_obfuscated_RES_2131953710);
            c.e = list;
            c.f = op1.e(format, true);
            U.l(c);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void F(Tab tab) {
        if (!L(false)) {
            this.b.c.U().k(this.b, Integer.valueOf(tab.getId()));
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void G(Tab tab) {
        if (!L(false)) {
            this.b.c.U().k(this.b, Integer.valueOf(tab.getId()));
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void H(Tab tab) {
        if (!L(true)) {
            C4126op1 op1 = this.b;
            int id = tab.getId();
            String title = tab.getTitle();
            View$OnClickListenerC5098uY0 U = op1.c.U();
            C4076oY0 c = C4076oY0.c(title, op1, 0, 11);
            c.c = op1.d.getString(R.string.f63950_resource_name_obfuscated_RES_2131953712);
            String string = op1.d.getString(R.string.f63930_resource_name_obfuscated_RES_2131953710);
            Integer valueOf = Integer.valueOf(id);
            c.d = string;
            c.e = valueOf;
            c.f = op1.e(title, false);
            U.l(c);
        }
    }

    public final boolean L(boolean z) {
        AbstractC2260du0 du0;
        if (AbstractC4772sd1.a() && AbstractC3293jx.a() == 1 && (du0 = this.b.f) != null && !((AbstractC3838n70) du0).C()) {
            return false;
        }
        Q31 q31 = this.f10514a;
        if (q31 != null && z) {
            return ((Boolean) q31.get()).booleanValue();
        }
        if (AbstractC4772sd1.b()) {
            return false;
        }
        return C0283Ep.h().d() || C5052uE.a();
    }

    @Override // defpackage.AbstractC5783ya1
    public void y() {
        if (!L(false)) {
            this.b.c.U().j(this.b);
        }
    }
}
