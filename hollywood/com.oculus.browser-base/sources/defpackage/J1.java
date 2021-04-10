package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: J1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class J1 {

    /* renamed from: a  reason: collision with root package name */
    public final C4935tb0 f8264a;
    public final AbstractC5523x1 b;
    public final WG0 c;
    public final boolean d;
    public String e;
    public final AccountManagerFacade f;
    public final W1 g;
    public final VG0 h;

    public J1(Context context, C4935tb0 tb0, AbstractC5523x1 x1Var, String str, boolean z) {
        E1 e1 = new E1(this);
        this.g = e1;
        F1 f1 = new F1(this);
        this.h = f1;
        this.f8264a = tb0;
        this.b = x1Var;
        WG0 wg0 = new WG0(context, context.getResources().getDimensionPixelSize(R.dimen.f26670_resource_name_obfuscated_RES_2131166286));
        this.c = wg0;
        this.d = z;
        this.e = str;
        AccountManagerFacade instance = AccountManagerFacadeProvider.getInstance();
        this.f = instance;
        instance.a(e1);
        wg0.U(f1);
        b();
    }

    public final C4765sb0 a(C3522lG lGVar, boolean z) {
        I1 i1 = new I1(this, z);
        boolean equals = lGVar.f10337a.equals(this.e);
        Map c2 = UH0.c(M1.d);
        TH0 th0 = M1.f8455a;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = lGVar;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(th0, lh0);
        QH0 qh0 = M1.b;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = equals;
        hashMap.put(qh0, gh0);
        OH0 oh0 = M1.c;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = i1;
        return new C4765sb0(1, AbstractC2531fV.o(hashMap, oh0, lh02, c2, null));
    }

    public final void b() {
        List d2 = V1.d(this.f.n());
        this.c.Z(d2);
        this.f8264a.clear();
        ArrayList arrayList = (ArrayList) d2;
        if (arrayList.size() > 0) {
            this.f8264a.q(a(this.c.W((String) arrayList.get(0)), true));
            for (int i = 1; i < arrayList.size(); i++) {
                this.f8264a.q(a(this.c.W((String) arrayList.get(i)), false));
            }
        }
        AbstractC5523x1 x1Var = this.b;
        x1Var.getClass();
        G1 g1 = new G1(x1Var);
        Map c2 = UH0.c(L1.b);
        OH0 oh0 = L1.f8401a;
        K1 k1 = new K1(g1);
        LH0 lh0 = new LH0(null);
        lh0.f8415a = k1;
        this.f8264a.q(new C4765sb0(2, AbstractC2531fV.o((HashMap) c2, oh0, lh0, c2, null)));
        if (this.d) {
            AbstractC5523x1 x1Var2 = this.b;
            x1Var2.getClass();
            H1 h1 = new H1(x1Var2);
            Map c3 = UH0.c(O1.b);
            OH0 oh02 = O1.f8593a;
            N1 n1 = new N1(h1);
            LH0 lh02 = new LH0(null);
            lh02.f8415a = n1;
            this.f8264a.q(new C4765sb0(3, AbstractC2531fV.o((HashMap) c3, oh02, lh02, c3, null)));
        }
    }
}
