package defpackage;

import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: K61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class K61 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final U61 f8343a;

    public K61(U61 u61) {
        this.f8343a = u61;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        U61 u61 = this.f8343a;
        Integer num = (Integer) obj;
        Objects.requireNonNull(u61);
        int i = 0;
        if (num.intValue() == R.id.ungroup_tab) {
            if (!AbstractC4772sd1.d()) {
                u61.b.j(AbstractC5033u71.v, false);
            }
            u61.b.j(AbstractC5033u71.u, false);
            List f = u61.f(u61.q);
            AbstractC0249Eb1 eb1 = u61.o;
            if (eb1 != null) {
                ((C0676Lb1) eb1).c(f, 0);
            }
        } else if (num.intValue() == R.id.share_tab_group) {
            WindowAndroid i2 = ((AbstractC0246Ea1) u61.c).o(u61.q).i();
            String str = (String) u61.b.g(AbstractC5033u71.c);
            String str2 = "";
            StringBuilder sb = new StringBuilder();
            List f2 = u61.f(u61.q);
            while (i < f2.size()) {
                int i3 = i + 1;
                sb.append(i3);
                sb.append(". ");
                sb.append(((Tab) f2.get(i)).s());
                sb.append("\n");
                i = i3;
            }
            String sb2 = sb.toString();
            R61 r61 = new R61(u61);
            if (!TextUtils.isEmpty(str2)) {
                str2 = HG.a(str2);
            }
            ((GT0) ((C1078Rq0) u61.k).H).b(new C2189dU0(i2, str, sb2, str2, null, null, null, null, r61, null), new C1915bt(true, false, false, null, false, true, null), 6);
        }
        if (AbstractC4772sd1.d() && num.intValue() == R.id.edit_group_name) {
            u61.b.j(AbstractC5033u71.u, true);
        }
    }
}
