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

/* renamed from: X.0pY  reason: invalid class name and case insensitive filesystem */
public final class C04210pY {
    public C04100pH A00;
    public C04290pj A01;
    public AbstractC04300pk A02;
    public C04390pu A03;
    public AnonymousClass0Cr A04;
    public HashMap<String, AbstractC01170Rz> A05;
    public HashSet<String> A06;
    public List<C00530Gr> A07;
    public boolean A08;
    public final AbstractC04010oz A09;
    public final Map<String, AbstractC01170Rz> A0A = new LinkedHashMap();
    public final boolean A0B;

    public final JsonDeserializer<?> A00() {
        Collection<AbstractC01170Rz> values = this.A0A.values();
        C04330po r4 = new C04330po(values);
        r4.A02();
        boolean z = !this.A0B;
        if (!z) {
            Iterator<AbstractC01170Rz> it = values.iterator();
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
        C04390pu r1 = this.A03;
        if (r1 != null) {
            r4 = r4.A01(new AnonymousClass0Gu(r1));
        }
        return new BeanDeserializer(this, this.A09, r4, this.A05, this.A06, this.A08, z);
    }

    public final void A01(AbstractC01170Rz r3) {
        AbstractC01170Rz put = this.A0A.put(r3._propName, r3);
        if (put != null && put != r3) {
            StringBuilder sb = new StringBuilder("Duplicate property '");
            sb.append(r3._propName);
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

    public C04210pY(AbstractC04010oz r2, AnonymousClass0HU r3) {
        this.A09 = r2;
        this.A0B = r3.A05(EnumC02160i9.DEFAULT_VIEW_INCLUSION);
    }
}
