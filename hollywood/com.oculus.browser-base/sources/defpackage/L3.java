package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.keyboard_accessory.sheet_tabs.AddressAccessoryInfoView;

/* renamed from: L3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L3 extends AbstractC4329q0 {
    public final C5010u0 c;
    public final C4670s0 d;

    public L3(Context context, MK0 mk0) {
        super(context.getString(R.string.f46590_resource_name_obfuscated_RES_2131951976), AbstractC5544x8.a(context, R.drawable.f32410_resource_name_obfuscated_RES_2131231281), context.getString(R.string.f46600_resource_name_obfuscated_RES_2131951977), context.getString(R.string.f46580_resource_name_obfuscated_RES_2131951975), R.layout.f36830_resource_name_obfuscated_RES_2131623992, 3, mk0);
        C5010u0 u0Var = new C5010u0();
        this.c = u0Var;
        this.d = new C4670s0(u0Var, 3, 3, 4, null);
    }

    @Override // defpackage.AbstractC4329q0
    public C4670s0 a() {
        return this.d;
    }

    @Override // defpackage.AbstractC4329q0
    public void b(ViewGroup viewGroup) {
        super.b(viewGroup);
        RecyclerView recyclerView = (RecyclerView) viewGroup;
        recyclerView.q0(new C2000cL0(new MW0(this.c, new I3(), new J3()), new K3()));
        recyclerView.g(new GJ(AddressAccessoryInfoView.class));
    }
}
