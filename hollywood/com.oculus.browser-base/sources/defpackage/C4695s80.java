package defpackage;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

/* renamed from: s80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4695s80 {

    /* renamed from: a  reason: collision with root package name */
    public EnumC3328k80 f11253a;
    public AbstractC3841n80 b;

    public C4695s80(AbstractC4354q80 q80, EnumC3328k80 k80) {
        AbstractC3841n80 n80;
        AbstractC3841n80 n802;
        Map map = AbstractC5035u80.f11389a;
        if (q80 instanceof AbstractC3841n80) {
            n80 = (AbstractC3841n80) q80;
        } else {
            Class<?> cls = q80.getClass();
            if (AbstractC5035u80.c(cls) == 2) {
                List list = (List) AbstractC5035u80.b.get(cls);
                if (list.size() == 1) {
                    n802 = new C2365eX0(AbstractC5035u80.a((Constructor) list.get(0), q80));
                } else {
                    ZU[] zuArr = new ZU[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        zuArr[i] = AbstractC5035u80.a((Constructor) list.get(i), q80);
                    }
                    n80 = new C3632lw(zuArr);
                }
            } else {
                n802 = new C2854hL0(q80);
            }
            n80 = n802;
        }
        this.b = n80;
        this.f11253a = k80;
    }

    public void a(AbstractC4524r80 r80, EnumC3157j80 j80) {
        EnumC3328k80 d = C4865t80.d(j80);
        this.f11253a = C4865t80.f(this.f11253a, d);
        this.b.a(r80, j80);
        this.f11253a = d;
    }
}
