package defpackage;

import J.N;
import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.keyboard_accessory.sheet_tabs.PasswordAccessoryInfoView;

/* renamed from: xx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5681xx0 extends AbstractC4329q0 {
    public final C5010u0 c;
    public final C5851yx0 d;
    public final Context e;

    public C5681xx0(Context context, MK0 mk0) {
        super(context.getString(R.string.f58060_resource_name_obfuscated_RES_2131953123), AbstractC5544x8.a(context, R.drawable.f33060_resource_name_obfuscated_RES_2131231346), context.getString(R.string.f57400_resource_name_obfuscated_RES_2131953057), context.getString(R.string.f57390_resource_name_obfuscated_RES_2131953056), R.layout.f40240_resource_name_obfuscated_RES_2131624333, 1, mk0);
        C5010u0 u0Var = new C5010u0();
        this.c = u0Var;
        this.d = new C5851yx0(u0Var, 1, 2, 1, new C4491qx0(this));
        this.e = context;
    }

    @Override // defpackage.AbstractC4329q0
    public C4670s0 a() {
        return this.d;
    }

    @Override // defpackage.AbstractC4329q0
    public void b(ViewGroup viewGroup) {
        super.b(viewGroup);
        if (N.M09VlOh_("AutofillKeyboardAccessory")) {
            RecyclerView recyclerView = (RecyclerView) viewGroup;
            recyclerView.q0(new C2000cL0(new MW0(this.c, new C5171ux0(), new C5341vx0()), new C5511wx0()));
            recyclerView.g(new GJ(PasswordAccessoryInfoView.class));
            return;
        }
        RecyclerView recyclerView2 = (RecyclerView) viewGroup;
        recyclerView2.q0(new C2000cL0(new MW0(this.c, new C4661rx0(), new C4831sx0()), new C5001tx0()));
        recyclerView2.g(new C0239Dx0());
    }
}
