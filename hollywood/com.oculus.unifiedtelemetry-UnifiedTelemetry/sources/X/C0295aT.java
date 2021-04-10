package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.aT  reason: case insensitive filesystem */
public final class C0295aT {
    public C0410hE A00;
    public C0263Yn A01;
    public AbstractC0262Ym A02;
    public C0204Vm A03;
    public AnonymousClass7P A04;
    public HashMap<String, AbstractC0073Cr> A05;
    public HashSet<String> A06;
    public List<AnonymousClass87> A07;
    public boolean A08;
    public final jm A09;
    public final Map<String, AbstractC0073Cr> A0A = new LinkedHashMap();
    public final boolean A0B;

    public final JsonDeserializer<?> A00() {
        Collection<AbstractC0073Cr> values = this.A0A.values();
        C0245Xs xs = new C0245Xs(values);
        xs.A02();
        boolean z = !this.A0B;
        if (!z) {
            Iterator<AbstractC0073Cr> it = values.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next()._viewMatcher != null) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        C0204Vm vm = this.A03;
        if (vm != null) {
            xs = xs.A01(new AnonymousClass8A(vm));
        }
        return new BeanDeserializer(this, this.A09, xs, this.A05, this.A06, this.A08, z);
    }

    public final void A01(AbstractC0073Cr cr) {
        AbstractC0073Cr put = this.A0A.put(cr._propName, cr);
        if (put != null && put != cr) {
            StringBuilder sb = new StringBuilder("Duplicate property '");
            sb.append(cr._propName);
            sb.append("' for ");
            sb.append(this.A09.A00);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public final void A02(String str) {
        HashSet<String> hashSet = this.A06;
        if (hashSet == null) {
            hashSet = new HashSet<>();
            this.A06 = hashSet;
        }
        hashSet.add(str);
    }

    public C0295aT(jm jmVar, AnonymousClass8M r3) {
        this.A09 = jmVar;
        this.A0B = r3.A05(EnumC0220Wf.DEFAULT_VIEW_INCLUSION);
    }
}
