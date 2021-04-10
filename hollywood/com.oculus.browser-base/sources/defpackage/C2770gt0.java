package defpackage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: gt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2770gt0 {

    /* renamed from: a  reason: collision with root package name */
    public final Vr1 f10029a;
    public final Map b;
    public AbstractC0639Kk c;
    public final List d;
    public final Wj1 e;
    public final Q31 f;

    public C2770gt0(List list, Vr1 vr1, Wj1 wj1, Q31 q31) {
        this.d = list;
        this.f10029a = vr1;
        this.e = wj1;
        this.f = q31;
        this.b = new HashMap(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AbstractC0639Kk kk = (AbstractC0639Kk) it.next();
            C2599ft0 ft0 = new C2599ft0(this, kk);
            kk.O(ft0);
            this.b.put(kk, ft0);
        }
    }

    public final void a(AbstractC0639Kk kk, C0517Ik ik) {
        this.c = kk;
        this.e.W(ik);
        ZY zy = ik.g;
        if (zy != null) {
            Vr1 vr1 = this.f10029a;
            zy.k = this.e.j();
            vr1.a(zy.a());
        }
    }

    public final void b() {
        List<AbstractC0639Kk> list = this.d;
        if (list != null) {
            for (AbstractC0639Kk kk : list) {
                C0517Ik r = kk.r((Tab) this.f.get());
                if (r != null && r.f8247a) {
                    if (kk != this.c) {
                        a(kk, r);
                        return;
                    }
                    return;
                }
            }
            this.e.n();
            this.c = null;
        }
    }
}
