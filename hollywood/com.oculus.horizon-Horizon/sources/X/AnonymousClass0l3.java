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

/* renamed from: X.0l3  reason: invalid class name */
public final class AnonymousClass0l3 {
    public C05310km A00;
    public C05480lF A01;
    public AnonymousClass0lG A02;
    public C05530lQ A03;
    public AnonymousClass07O A04;
    public HashMap<String, AnonymousClass0HD> A05;
    public HashSet<String> A06;
    public List<AnonymousClass08I> A07;
    public boolean A08;
    public final AbstractC05180kU A09;
    public final Map<String, AnonymousClass0HD> A0A = new LinkedHashMap();
    public final boolean A0B;

    public final JsonDeserializer<?> A00() {
        Collection<AnonymousClass0HD> values = this.A0A.values();
        AnonymousClass0lK r4 = new AnonymousClass0lK(values);
        r4.A02();
        boolean z = !this.A0B;
        if (!z) {
            Iterator<AnonymousClass0HD> it = values.iterator();
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
        C05530lQ r1 = this.A03;
        if (r1 != null) {
            r4 = r4.A01(new AnonymousClass08L(r1));
        }
        return new BeanDeserializer(this, this.A09, r4, this.A05, this.A06, this.A08, z);
    }

    public final void A01(AnonymousClass0HD r3) {
        AnonymousClass0HD put = this.A0A.put(r3._propName, r3);
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

    public AnonymousClass0l3(AbstractC05180kU r2, AnonymousClass08X r3) {
        this.A09 = r2;
        this.A0B = r3.A05(EnumC03960gV.DEFAULT_VIEW_INCLUSION);
    }
}
