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

/* renamed from: X.0mz  reason: invalid class name and case insensitive filesystem */
public final class C06490mz {
    public C06380mi A00;
    public C06560nA A01;
    public AnonymousClass0nB A02;
    public AnonymousClass0nL A03;
    public AnonymousClass0EC A04;
    public HashMap<String, AbstractC01680Ku> A05;
    public HashSet<String> A06;
    public List<AnonymousClass0Er> A07;
    public boolean A08;
    public final AbstractC06260mR A09;
    public final Map<String, AbstractC01680Ku> A0A = new LinkedHashMap();
    public final boolean A0B;

    public final JsonDeserializer<?> A00() {
        Collection<AbstractC01680Ku> values = this.A0A.values();
        C06570nF r4 = new C06570nF(values);
        r4.A02();
        boolean z = !this.A0B;
        if (!z) {
            Iterator<AbstractC01680Ku> it = values.iterator();
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
        AnonymousClass0nL r1 = this.A03;
        if (r1 != null) {
            r4 = r4.A01(new AnonymousClass0Eu(r1));
        }
        return new BeanDeserializer(this, this.A09, r4, this.A05, this.A06, this.A08, z);
    }

    public final void A01(AbstractC01680Ku r3) {
        AbstractC01680Ku put = this.A0A.put(r3._propName, r3);
        if (put != null && put != r3) {
            throw new IllegalArgumentException("Duplicate property '" + r3._propName + "' for " + this.A09.A00);
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

    public C06490mz(AbstractC06260mR r2, C01260Fu r3) {
        this.A09 = r2;
        this.A0B = r3.A05(EnumC02540aC.DEFAULT_VIEW_INCLUSION);
    }
}
