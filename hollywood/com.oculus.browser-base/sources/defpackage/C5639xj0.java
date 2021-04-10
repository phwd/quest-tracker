package defpackage;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: xj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5639xj0 {

    /* renamed from: a  reason: collision with root package name */
    public final C0150Cj0 f11629a;
    public final List b = new ArrayList();

    public C5639xj0(Context context, Q31 q31, AbstractC0211Dj0 dj0) {
        this.f11629a = new C0150Cj0(context, q31, dj0);
    }

    public List a() {
        C0150Cj0 cj0 = this.f11629a;
        Iterator it = cj0.c.keySet().iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            if (!cj0.d.containsKey(Integer.valueOf(intValue))) {
                List list = (List) cj0.c.get(Integer.valueOf(intValue));
                cj0.d.put(Integer.valueOf(intValue), (C0089Bj0) list.remove(0));
                if (list.size() == 0) {
                    it.remove();
                }
            }
        }
        for (C0089Bj0 bj0 : cj0.d.values()) {
            bj0.b.j(AbstractC0516Ij0.m, ((Boolean) cj0.b.get()).booleanValue());
        }
        return new ArrayList(cj0.d.values());
    }
}
