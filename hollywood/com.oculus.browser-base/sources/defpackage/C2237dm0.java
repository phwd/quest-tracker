package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: dm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2237dm0 extends VK {
    public final /* synthetic */ Context F;
    public final /* synthetic */ C2920hm0 G;

    public C2237dm0(C2920hm0 hm0, Context context) {
        this.G = hm0;
        this.F = context;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void c(TabModel tabModel, TabModel tabModel2) {
        boolean a2 = tabModel.a();
        this.G.h.setColor(this.F.getResources().getColor(a2 ? R.color.f15270_resource_name_obfuscated_RES_2131100217 : R.color.f15260_resource_name_obfuscated_RES_2131100216));
        this.G.i.setColor(this.F.getResources().getColor(a2 ? R.color.f15140_resource_name_obfuscated_RES_2131100204 : R.color.f15130_resource_name_obfuscated_RES_2131100203));
        this.G.j.setColor(this.F.getResources().getColor(a2 ? R.color.f15200_resource_name_obfuscated_RES_2131100210 : R.color.f15190_resource_name_obfuscated_RES_2131100209));
        this.G.k.setColor(this.F.getResources().getColor(a2 ? R.color.f12260_resource_name_obfuscated_RES_2131099916 : R.color.f12250_resource_name_obfuscated_RES_2131099915));
    }
}
