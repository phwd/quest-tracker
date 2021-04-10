package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Kb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0615Kb1 extends VK {
    public final /* synthetic */ C0676Lb1 F;

    public C0615Kb1(C0676Lb1 lb1) {
        this.F = lb1;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void c(TabModel tabModel, TabModel tabModel2) {
        boolean a2 = tabModel.a();
        int color = this.F.f8427a.getResources().getColor(a2 ? R.color.f10880_resource_name_obfuscated_RES_2131099778 : R.color.f10840_resource_name_obfuscated_RES_2131099774);
        int color2 = this.F.f8427a.getResources().getColor(a2 ? R.color.f13330_resource_name_obfuscated_RES_2131100023 : R.color.f11100_resource_name_obfuscated_RES_2131099800);
        Context context = this.F.f8427a;
        int i = a2 ? R.color.f10800_resource_name_obfuscated_RES_2131099770 : R.color.f11560_resource_name_obfuscated_RES_2131099846;
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        ColorStateList colorStateList = context.getColorStateList(i);
        int i2 = a2 ? R.style.f71310_resource_name_obfuscated_RES_2132017704 : R.style.f71320_resource_name_obfuscated_RES_2132017705;
        this.F.d.l(AbstractC0736Mb1.f, color);
        this.F.d.l(AbstractC0736Mb1.g, color2);
        this.F.d.m(AbstractC0736Mb1.h, colorStateList);
        this.F.d.l(AbstractC0736Mb1.i, i2);
    }
}
