package X;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class RQ {
    public Integer A00;
    public final C0644dk A01;
    public final String A02;
    public final String A03;
    public final Set A04;
    public final Set A05;
    public final Map A06;

    public RQ(Set set, String str, String str2, C0644dk dkVar) {
        Set unmodifiableSet;
        if (set == null) {
            unmodifiableSet = Collections.emptySet();
        } else {
            unmodifiableSet = Collections.unmodifiableSet(set);
        }
        this.A04 = unmodifiableSet;
        this.A06 = Collections.emptyMap();
        this.A02 = str;
        this.A03 = str2;
        this.A01 = dkVar;
        HashSet hashSet = new HashSet(this.A04);
        Iterator it = this.A06.values().iterator();
        if (it.hasNext()) {
            it.next();
            throw new NullPointerException("zaa");
        } else {
            this.A05 = Collections.unmodifiableSet(hashSet);
        }
    }
}
