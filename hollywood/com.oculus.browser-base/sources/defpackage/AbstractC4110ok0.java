package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* renamed from: ok0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4110ok0 {

    /* renamed from: a  reason: collision with root package name */
    public C1322Vq0 f10572a = new C1322Vq0();
    public int b;

    public AbstractC4110ok0(int i) {
        this.b = i;
    }

    public void a(AbstractC3939nk0 nk0) {
        this.f10572a.b(nk0);
    }

    public void b(AbstractC3768mk0 mk0) {
        UH0 uh0;
        int i;
        String str;
        Iterator it = this.f10572a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                int i2 = this.b;
                C0150Cj0 cj0 = (C0150Cj0) ((AbstractC3939nk0) uq0.next());
                Objects.requireNonNull(cj0);
                if (i2 == 1) {
                    Context context = cj0.f7834a;
                    C5809yj0 yj0 = new C5809yj0(cj0);
                    C2720gc1 gc1 = (C2720gc1) mk0;
                    int i3 = gc1.f10007a.b;
                    if (i3 != 0) {
                        i = 1;
                        if (i3 != 1) {
                            str = "";
                        } else {
                            str = context.getString(R.string.f63220_resource_name_obfuscated_RES_2131953639);
                        }
                    } else {
                        i = 1;
                        str = context.getString(R.string.f63240_resource_name_obfuscated_RES_2131953641);
                    }
                    Locale locale = Locale.getDefault();
                    Object[] objArr = new Object[i];
                    objArr[0] = Integer.valueOf(gc1.f10007a.f9352a.size());
                    String format = String.format(locale, "%d", objArr);
                    String string = context.getString(R.string.f63250_resource_name_obfuscated_RES_2131953642);
                    String string2 = context.getString(R.string.f45920_resource_name_obfuscated_RES_2131951909);
                    HH0 hh0 = new HH0(AbstractC0516Ij0.p);
                    hh0.c(AbstractC0516Ij0.f8246a, 1);
                    hh0.e(AbstractC0516Ij0.e, new C1866bc1());
                    hh0.e(AbstractC0516Ij0.g, yj0);
                    hh0.e(AbstractC0516Ij0.i, new C2549fc1(gc1));
                    hh0.e(AbstractC0516Ij0.h, new C2378ec1(gc1));
                    hh0.e(AbstractC0516Ij0.d, str);
                    hh0.e(AbstractC0516Ij0.c, format);
                    hh0.e(AbstractC0516Ij0.b, string);
                    hh0.e(AbstractC0516Ij0.j, string2);
                    hh0.b(AbstractC0516Ij0.l, true);
                    hh0.b(AbstractC0516Ij0.m, false);
                    hh0.c(J91.f8274a, 1);
                    hh0.f(J91.b, 1.0f);
                    uh0 = hh0.a();
                } else if (i2 == 2) {
                    Context context2 = cj0.f7834a;
                    C5979zj0 zj0 = new C5979zj0(cj0);
                    T30 t30 = (T30) mk0;
                    String string3 = context2.getString(R.string.f53320_resource_name_obfuscated_RES_2131952649);
                    String string4 = context2.getString(R.string.f53330_resource_name_obfuscated_RES_2131952650);
                    String string5 = context2.getString(R.string.f45920_resource_name_obfuscated_RES_2131951909);
                    HH0 hh02 = new HH0(AbstractC0516Ij0.p);
                    hh02.c(AbstractC0516Ij0.f8246a, 2);
                    hh02.e(AbstractC0516Ij0.e, new P30());
                    hh02.e(AbstractC0516Ij0.g, zj0);
                    hh02.e(AbstractC0516Ij0.i, t30.b);
                    hh02.e(AbstractC0516Ij0.h, t30.f8933a);
                    hh02.e(AbstractC0516Ij0.c, string3);
                    hh02.e(AbstractC0516Ij0.d, null);
                    hh02.e(AbstractC0516Ij0.b, string4);
                    hh02.e(AbstractC0516Ij0.j, string5);
                    hh02.b(AbstractC0516Ij0.k, true);
                    hh02.b(AbstractC0516Ij0.l, false);
                    hh02.b(AbstractC0516Ij0.m, false);
                    hh02.c(J91.f8274a, 1);
                    hh02.f(J91.b, 1.0f);
                    uh0 = hh02.a();
                } else if (i2 != 3) {
                    Map c = UH0.c(AbstractC0516Ij0.p);
                    QH0 qh0 = AbstractC0516Ij0.m;
                    GH0 gh0 = new GH0(null);
                    gh0.f8081a = false;
                    ((HashMap) c).put(qh0, gh0);
                    uh0 = new UH0(c, null);
                } else {
                    Context context3 = cj0.f7834a;
                    C0028Aj0 aj0 = new C0028Aj0(cj0);
                    IF0 if0 = (IF0) mk0;
                    String string6 = context3.getString(R.string.f59430_resource_name_obfuscated_RES_2131953260);
                    String string7 = context3.getString(R.string.f59400_resource_name_obfuscated_RES_2131953257);
                    String string8 = context3.getString(R.string.f59420_resource_name_obfuscated_RES_2131953259);
                    String string9 = context3.getString(R.string.f45920_resource_name_obfuscated_RES_2131951909);
                    HH0 hh03 = new HH0(AbstractC0516Ij0.p);
                    hh03.c(AbstractC0516Ij0.f8246a, 3);
                    hh03.e(AbstractC0516Ij0.g, aj0);
                    hh03.e(AbstractC0516Ij0.i, if0.c);
                    hh03.e(AbstractC0516Ij0.h, if0.b);
                    hh03.e(AbstractC0516Ij0.c, string7);
                    hh03.e(AbstractC0516Ij0.d, null);
                    hh03.e(AbstractC0516Ij0.b, string8);
                    hh03.e(AbstractC0516Ij0.j, string9);
                    hh03.b(AbstractC0516Ij0.k, false);
                    hh03.b(AbstractC0516Ij0.l, false);
                    hh03.b(AbstractC0516Ij0.m, false);
                    hh03.e(AbstractC0516Ij0.n, string6);
                    hh03.e(AbstractC0516Ij0.o, if0.f8212a);
                    hh03.c(J91.f8274a, 1);
                    hh03.f(J91.b, 1.0f);
                    uh0 = hh03.a();
                }
                C0089Bj0 bj0 = new C0089Bj0(cj0, i2, uh0);
                if (cj0.c.containsKey(Integer.valueOf(i2))) {
                    ((List) cj0.c.get(Integer.valueOf(i2))).add(bj0);
                } else {
                    cj0.c.put(Integer.valueOf(i2), new ArrayList(Arrays.asList(bj0)));
                }
            } else {
                return;
            }
        }
    }

    public void c() {
        Iterator it = this.f10572a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                int i = this.b;
                C0150Cj0 cj0 = (C0150Cj0) ((AbstractC3939nk0) uq0.next());
                if (cj0.c.containsKey(Integer.valueOf(i))) {
                    cj0.c.remove(Integer.valueOf(i));
                }
                if (cj0.d.containsKey(Integer.valueOf(i))) {
                    cj0.d.remove(Integer.valueOf(i));
                    cj0.e.a(i);
                }
            } else {
                return;
            }
        }
    }
}
