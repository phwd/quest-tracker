package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Q61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q61 extends VK {
    public final /* synthetic */ U61 F;

    public Q61(U61 u61) {
        this.F = u61;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void c(TabModel tabModel, TabModel tabModel2) {
        ColorStateList colorStateList;
        boolean a2 = tabModel.a();
        int i = a2 ? R.drawable.f35100_resource_name_obfuscated_RES_2131231550 : R.drawable.f35090_resource_name_obfuscated_RES_2131231549;
        if (a2) {
            Context context = this.F.f9006a;
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            colorStateList = context.getColorStateList(R.color.f11330_resource_name_obfuscated_RES_2131099823);
        } else {
            Context context2 = this.F.f9006a;
            ThreadLocal threadLocal2 = AbstractC5544x8.f11592a;
            colorStateList = context2.getColorStateList(R.color.f11390_resource_name_obfuscated_RES_2131099829);
        }
        int i2 = a2 ? R.color.f15240_resource_name_obfuscated_RES_2131100214 : R.color.f15230_resource_name_obfuscated_RES_2131100213;
        int i3 = a2 ? R.color.f15160_resource_name_obfuscated_RES_2131100206 : R.color.f15150_resource_name_obfuscated_RES_2131100205;
        int i4 = a2 ? R.style.f72070_resource_name_obfuscated_RES_2132017780 : R.style.f72050_resource_name_obfuscated_RES_2132017778;
        this.F.b.l(AbstractC5033u71.k, i);
        this.F.b.m(AbstractC5033u71.f, colorStateList);
        this.F.b.l(AbstractC5033u71.l, i2);
        this.F.b.l(AbstractC5033u71.m, i3);
        this.F.b.l(AbstractC5033u71.n, i4);
    }
}
