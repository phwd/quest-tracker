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

/* renamed from: X.Oa  reason: case insensitive filesystem */
public final class C0262Oa {
    public OJ A00;
    public C0267Oj A01;
    public Ok A02;
    public C0273Ou A03;
    public AnonymousClass0q A04;
    public HashMap A05;
    public HashSet A06;
    public List A07;
    public boolean A08;
    public final O4 A09;
    public final Map A0A = new LinkedHashMap();
    public final boolean A0B;

    public final JsonDeserializer A00() {
        Collection values = this.A0A.values();
        Oo oo = new Oo(values);
        oo.A02();
        boolean z = !this.A0B;
        if (!z) {
            Iterator it = values.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((AbstractC1034r7) it.next())._viewMatcher != null) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        C0273Ou ou = this.A03;
        if (ou != null) {
            oo = oo.A01(new C0351Tb(ou));
        }
        return new BeanDeserializer(this, this.A09, oo, this.A05, this.A06, this.A08, z);
    }

    public final void A01(AbstractC1034r7 r7Var) {
        Object put = this.A0A.put(r7Var._propName, r7Var);
        if (put != null && put != r7Var) {
            StringBuilder sb = new StringBuilder("Duplicate property '");
            sb.append(r7Var._propName);
            sb.append("' for ");
            sb.append(this.A09.A00);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public final void A02(String str) {
        HashSet hashSet = this.A06;
        if (hashSet == null) {
            hashSet = new HashSet();
            this.A06 = hashSet;
        }
        hashSet.add(str);
    }

    public C0262Oa(O4 o4, AnonymousClass2I r3) {
        this.A09 = o4;
        this.A0B = r3.A05(EnumC1027qy.DEFAULT_VIEW_INCLUSION);
    }
}
