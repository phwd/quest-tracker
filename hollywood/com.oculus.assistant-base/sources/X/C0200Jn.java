package X;

import android.content.Context;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* renamed from: X.Jn  reason: case insensitive filesystem */
public final class C0200Jn {
    public final Map A00;
    public final Set A01;

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0200Jn)) {
            return false;
        }
        C0200Jn jn = (C0200Jn) obj;
        Set set = jn.A01;
        if (set != null) {
            z = set.equals(this.A01);
        } else {
            z = false;
            if (this.A01 == null) {
                z = true;
            }
        }
        Map map = jn.A00;
        if (map != null) {
            z2 = map.equals(this.A00);
        } else {
            z2 = false;
            if (this.A00 == null) {
                z2 = true;
            }
        }
        return z && z2;
    }

    public final int hashCode() {
        return Objects.hash(this.A01, this.A00);
    }

    public static boolean A01(C0195Ji ji) {
        if (C0193Jg.A14.contains(ji) || C0193Jg.A1O.contains(ji) || C0193Jg.A1E.contains(ji) || C0193Jg.A1J.contains(ji) || C0193Jg.A17.contains(ji)) {
            return true;
        }
        return false;
    }

    public C0200Jn(Map map) {
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Set set = (Set) entry.getValue();
            if (set == null || !set.contains("*|all_packages|*")) {
                if (!hashMap.containsKey(key)) {
                    hashMap.put(key, new HashSet());
                }
                ((Set) hashMap.get(key)).addAll(set);
            } else {
                hashSet.add(key);
            }
        }
        this.A01 = Collections.unmodifiableSet(hashSet);
        this.A00 = Collections.unmodifiableMap(hashMap);
    }

    public static C0194Jh A00(int i, Context context) {
        try {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
            if (packagesForUid != null && packagesForUid.length != 0) {
                return new C0194Jh(i, Collections.unmodifiableList(Arrays.asList(packagesForUid)), C0198Jl.A02(context, packagesForUid), null, null);
            }
            throw new C0206Jt(AnonymousClass08.A00("No packageName associated with uid=", i));
        } catch (RuntimeException e) {
            throw new SecurityException(e);
        }
    }

    public static boolean A02(C0195Ji ji, C0195Ji ji2, boolean z) {
        if (!ji.equals(ji2)) {
            if (!z) {
                return false;
            }
            Set set = (Set) C0193Jg.A0z.get(ji2);
            if (set == null) {
                set = C0193Jg.A00(C0193Jg.A01);
            }
            if (set.contains(ji)) {
                return true;
            }
            return false;
        }
        return true;
    }
}
