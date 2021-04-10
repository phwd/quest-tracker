package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: op1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4126op1 extends AbstractC4758sY0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0124Ca1 f10579a;
    public final AbstractC5783ya1 b;
    public final AbstractC4928tY0 c;
    public final Context d;
    public C1128Sl e;
    public AbstractC2260du0 f;

    public C4126op1(Context context, AbstractC0124Ca1 ca1, AbstractC4928tY0 ty0, AbstractC1509Ys0 ys0, Q31 q31) {
        C1128Sl sl = new C1128Sl();
        this.e = sl;
        this.c = ty0;
        this.f10579a = ca1;
        this.d = context;
        Callback b2 = sl.b(new C3784mp1(this));
        C1570Zs0 zs0 = (C1570Zs0) ys0;
        Objects.requireNonNull(zs0.G);
        zs0.F.g(b2);
        zs0.get();
        this.b = new C3955np1(this, q31);
    }

    @Override // defpackage.AbstractC4758sY0
    public void c(Object obj) {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            TabModel m = ((AbstractC0246Ea1) this.f10579a).m(intValue);
            if (m != null) {
                m.s(intValue);
                return;
            }
            return;
        }
        for (Tab tab : (List) obj) {
            int id = tab.getId();
            TabModel m2 = ((AbstractC0246Ea1) this.f10579a).m(id);
            if (m2 != null) {
                m2.s(id);
            }
        }
    }

    @Override // defpackage.AbstractC4758sY0
    public void d(Object obj) {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            TabModel m = ((AbstractC0246Ea1) this.f10579a).m(intValue);
            if (m != null) {
                m.v(intValue);
                return;
            }
            return;
        }
        for (Tab tab : (List) obj) {
            int id = tab.getId();
            TabModel m2 = ((AbstractC0246Ea1) this.f10579a).m(id);
            if (m2 != null) {
                m2.v(id);
            }
        }
    }

    public final String e(String str, boolean z) {
        if (z) {
            return this.d.getString(R.string.f46340_resource_name_obfuscated_RES_2131951951, str);
        }
        return this.d.getString(R.string.f46330_resource_name_obfuscated_RES_2131951950, str);
    }
}
