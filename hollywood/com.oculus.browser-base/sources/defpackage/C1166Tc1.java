package defpackage;

import com.oculus.browser.R;
import org.chromium.chrome.browser.tasks.tab_management.PriceTrackingDialogView;

/* renamed from: Tc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1166Tc1 implements AbstractC1244Ui0 {
    public final /* synthetic */ C1349Wc1 F;

    public C1166Tc1(C1349Wc1 wc1) {
        this.F = wc1;
    }

    @Override // defpackage.AbstractC1244Ui0
    public boolean f(int i, boolean z) {
        if (i == R.id.menu_group_tabs) {
            C1349Wc1 wc1 = this.F;
            C0676Lb1 lb1 = wc1.Q.i;
            String str = wc1.R.f9095a;
            Integer valueOf = Integer.valueOf((int) R.plurals.f42620_resource_name_obfuscated_RES_2131820554);
            C1288Vc1 vc1 = this.F.R;
            lb1.a(str, valueOf, vc1.b, 2, vc1.c);
            C1349Wc1 wc12 = this.F;
            wc12.Q.i.c(((AbstractC0246Ea1) wc12.M).c.d().O(), 0);
            AbstractC3535lK0.a("MobileMenuGroupTabs");
            return true;
        } else if (i != R.id.track_prices_row_menu_id) {
            return false;
        } else {
            BF0 bf0 = this.F.d0;
            PriceTrackingDialogView priceTrackingDialogView = bf0.H;
            priceTrackingDialogView.F.setChecked(CF0.e());
            priceTrackingDialogView.G.setChecked(CF0.b());
            bf0.G.i(bf0.F, 0, false);
            return true;
        }
    }
}
