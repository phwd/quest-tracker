package X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"CatchGeneralException", "DeprecatedMethod", "TodoWithoutTask"})
/* renamed from: X.0ix  reason: invalid class name and case insensitive filesystem */
public final class C05280ix {
    public final Map<C05140ii, Set<String>> A00;
    public final Set<C05140ii> A01;

    @Deprecated
    public final boolean A02(int i, Context context) {
        String[] A06 = C05180im.A06(context, i);
        return A03(new C05130ih(i, Collections.unmodifiableList(Arrays.asList(A06)), C05180im.A04(context, A06), null, null), C05120ig.A10.contains(C05180im.A03(context, context.getPackageName())));
    }

    public final boolean A03(@Nullable C05130ih r10, boolean z) {
        C05140ii r6;
        if (!(r10 == null || (r6 = r10.A01) == null)) {
            for (C05140ii r0 : this.A01) {
                if (A01(r6, r0, z)) {
                    return true;
                }
            }
            Map<C05140ii, Set<String>> map = this.A00;
            for (C05140ii r3 : map.keySet()) {
                if (A01(r6, r3, z)) {
                    for (String str : r10.A04) {
                        if (map.get(r3).contains(str)) {
                            return true;
                        }
                    }
                    continue;
                }
            }
        }
        return false;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C05280ix)) {
            return false;
        }
        C05280ix r5 = (C05280ix) obj;
        Set<C05140ii> set = r5.A01;
        if (set != null) {
            z = set.equals(this.A01);
        } else {
            z = false;
            if (this.A01 == null) {
                z = true;
            }
        }
        Map<C05140ii, Set<String>> map = r5.A00;
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

    public static boolean A00(C05140ii r1) {
        if (C05120ig.A14.contains(r1) || C05120ig.A1O.contains(r1) || C05120ig.A1E.contains(r1) || C05120ig.A1J.contains(r1) || C05120ig.A17.contains(r1)) {
            return true;
        }
        return false;
    }

    @SuppressLint({"ObjectsUse"})
    @TargetApi(19)
    public final int hashCode() {
        return Objects.hash(this.A01, this.A00);
    }

    public C05280ix(Map<C05140ii, Set<String>> map) {
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (Map.Entry<C05140ii, Set<String>> entry : map.entrySet()) {
            C05140ii key = entry.getKey();
            Set<String> value = entry.getValue();
            if (value == null || !value.contains("*|all_packages|*")) {
                if (!hashMap.containsKey(key)) {
                    hashMap.put(key, new HashSet());
                }
                ((Set) hashMap.get(key)).addAll(value);
            } else {
                hashSet.add(key);
            }
        }
        this.A01 = Collections.unmodifiableSet(hashSet);
        this.A00 = Collections.unmodifiableMap(hashMap);
    }

    public static boolean A01(C05140ii r2, C05140ii r3, boolean z) {
        if (!r2.equals(r3)) {
            if (!z) {
                return false;
            }
            Set<C05140ii> set = C05120ig.A0z.get(r3);
            if (set == null) {
                set = C05120ig.A00(C05120ig.A01);
            }
            if (set.contains(r2)) {
                return true;
            }
            return false;
        }
        return true;
    }
}
