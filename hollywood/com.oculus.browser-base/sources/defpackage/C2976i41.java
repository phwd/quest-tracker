package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: i41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2976i41 extends WK implements Qr1, AbstractC0072Bd1 {
    public final Tab F;
    public View G;
    public String H;

    public C2976i41(Tab tab) {
        this.F = tab;
    }

    public static C2976i41 W(Tab tab) {
        C2976i41 i41 = (C2976i41) tab.M().c(C2976i41.class);
        return i41 == null ? (C2976i41) tab.M().e(C2976i41.class, new C2976i41(tab)) : i41;
    }

    public final void V() {
        View inflate = LayoutInflater.from(this.F.getContext()).inflate(R.layout.f41650_resource_name_obfuscated_RES_2131624474, (ViewGroup) null);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.G = inflate;
        ((C0011Ad1) this.F.C()).a(this);
        X();
    }

    public final void X() {
        ((TextView) this.G.findViewById(R.id.suspended_tab_explanation)).setText(this.F.getContext().getString(R.string.f64210_resource_name_obfuscated_RES_2131953738, this.H));
        this.G.findViewById(R.id.suspended_tab_settings_button).setOnClickListener(new View$OnClickListenerC2805h41(this, this.F.getContext()));
    }

    @Override // defpackage.AbstractC0072Bd1
    public View b() {
        return this.G;
    }

    @Override // defpackage.AbstractC0072Bd1
    public int c() {
        return 0;
    }

    @Override // defpackage.AbstractC0072Bd1
    public void d() {
    }

    @Override // defpackage.Qr1
    public void destroy() {
        this.F.I(this);
    }

    @Override // defpackage.AbstractC0072Bd1
    public void e() {
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            ((C0011Ad1) this.F.C()).c(this);
            this.G = null;
            return;
        }
        V();
    }
}
