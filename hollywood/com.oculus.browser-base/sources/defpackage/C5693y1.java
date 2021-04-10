package defpackage;

import J.N;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;

/* renamed from: y1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5693y1 {

    /* renamed from: a  reason: collision with root package name */
    public final J1 f11654a;

    public C5693y1(RecyclerView recyclerView, AbstractC5523x1 x1Var, String str, boolean z) {
        C4935tb0 tb0 = new C4935tb0();
        JW0 jw0 = new JW0(tb0);
        boolean M09VlOh_ = N.M09VlOh_("MobileIdentityConsistency");
        jw0.v(2, new L70(M09VlOh_ ? R.layout.f36680_resource_name_obfuscated_RES_2131623977 : R.layout.f36690_resource_name_obfuscated_RES_2131623978), new C0595Js0(L1.f8401a));
        jw0.v(1, new L70(M09VlOh_ ? R.layout.f36700_resource_name_obfuscated_RES_2131623979 : R.layout.f36710_resource_name_obfuscated_RES_2131623980), new C4222pM());
        jw0.v(3, new L70(R.layout.f36670_resource_name_obfuscated_RES_2131623976), new C0595Js0(O1.f8593a));
        recyclerView.q0(jw0);
        this.f11654a = new J1(recyclerView.getContext(), tb0, x1Var, str, z);
    }
}
