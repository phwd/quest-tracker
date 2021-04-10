package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: GQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GQ0 extends Pr1 {
    public Tab F;
    public KQ0 G;
    public HQ0 H;
    public RecyclerView I = new RecyclerView(this.F.getContext(), null);

    public GQ0(Tab tab) {
        this.F = tab;
        C4935tb0 tb0 = new C4935tb0();
        JW0 jw0 = new JW0(tb0);
        jw0.v(0, new C5932zQ0(this), new AQ0());
        jw0.v(1, new BQ0(this), new CQ0());
        jw0.v(2, new DQ0(this), new EQ0());
        this.I.q0(jw0);
        this.H = new HQ0(tb0, new FQ0(tab));
        KQ0 c = KQ0.c(this.F);
        this.G = c;
        c.F.add(this.H);
    }

    public final View c(ViewGroup viewGroup, int i) {
        return AbstractC2531fV.r(viewGroup, i != 1 ? i != 2 ? R.layout.f37570_resource_name_obfuscated_RES_2131624066 : R.layout.f37560_resource_name_obfuscated_RES_2131624065 : R.layout.f37580_resource_name_obfuscated_RES_2131624067, viewGroup, false);
    }

    @Override // defpackage.Qr1, defpackage.Pr1
    public void destroy() {
        KQ0 kq0 = this.G;
        kq0.F.remove(this.H);
    }
}
