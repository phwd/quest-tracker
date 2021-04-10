package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.keyboard_accessory.sheet_tabs.CreditCardAccessoryInfoView;

/* renamed from: kB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3336kB extends AbstractC4329q0 {
    public C5010u0 c;
    public final C4670s0 d;

    public C3336kB(Context context, MK0 mk0) {
        super(context.getString(R.string.f47530_resource_name_obfuscated_RES_2131952070), AbstractC5544x8.a(context, R.drawable.f33200_resource_name_obfuscated_RES_2131231360), context.getString(R.string.f50240_resource_name_obfuscated_RES_2131952341), context.getString(R.string.f50230_resource_name_obfuscated_RES_2131952340), R.layout.f37610_resource_name_obfuscated_RES_2131624070, 2, mk0);
        C5010u0 u0Var = new C5010u0();
        this.c = u0Var;
        this.d = new C4670s0(u0Var, 2, 4, 3, null);
    }

    @Override // defpackage.AbstractC4329q0
    public C4670s0 a() {
        return this.d;
    }

    @Override // defpackage.AbstractC4329q0
    public void b(ViewGroup viewGroup) {
        super.b(viewGroup);
        RecyclerView recyclerView = (RecyclerView) viewGroup;
        recyclerView.q0(new C2000cL0(new MW0(this.c, new C3507lB(), new C3678mB()), new C3849nB()));
        recyclerView.g(new GJ(CreditCardAccessoryInfoView.class));
    }
}
