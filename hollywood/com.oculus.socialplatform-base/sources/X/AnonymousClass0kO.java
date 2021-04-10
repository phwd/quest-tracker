package X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Binder;
import android.os.Process;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"CatchGeneralException", "DeprecatedMethod", "TodoWithoutTask"})
/* renamed from: X.0kO  reason: invalid class name */
public final class AnonymousClass0kO {
    public final Map<C02920k8, Set<String>> A00;
    public final Set<C02920k8> A01;

    @Deprecated
    public static AnonymousClass0k7 A00(int i, Context context) {
        String[] A07 = AnonymousClass0kC.A07(context, i);
        return new AnonymousClass0k7(i, Collections.unmodifiableList(Arrays.asList(A07)), AnonymousClass0kC.A04(context, A07), null, null);
    }

    public final boolean A05(@Nullable AnonymousClass0k7 r10, boolean z) {
        C02920k8 r6;
        if (!(r10 == null || (r6 = r10.A01) == null)) {
            for (C02920k8 r0 : this.A01) {
                if (A02(r6, r0, z)) {
                    return true;
                }
            }
            Map<C02920k8, Set<String>> map = this.A00;
            for (C02920k8 r3 : map.keySet()) {
                if (A02(r6, r3, z)) {
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
        if (!(obj instanceof AnonymousClass0kO)) {
            return false;
        }
        AnonymousClass0kO r5 = (AnonymousClass0kO) obj;
        Set<C02920k8> set = r5.A01;
        if (set != null) {
            z = set.equals(this.A01);
        } else {
            z = false;
            if (this.A01 == null) {
                z = true;
            }
        }
        Map<C02920k8, Set<String>> map = r5.A00;
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

    public static boolean A01(C02920k8 r1) {
        if (AnonymousClass0k6.A14.contains(r1) || AnonymousClass0k6.A1O.contains(r1) || AnonymousClass0k6.A1E.contains(r1) || AnonymousClass0k6.A1J.contains(r1) || AnonymousClass0k6.A17.contains(r1)) {
            return true;
        }
        return false;
    }

    @Deprecated
    public final boolean A03(@Nullable Context context) {
        if (context == null) {
            return false;
        }
        if (Binder.getCallingPid() != Process.myPid()) {
            return A04(A00(Binder.getCallingUid(), context), context);
        }
        throw new IllegalStateException("This method should be called on behalf of an IPC transaction from binder thread.");
    }

    @SuppressLint({"ObjectsUse"})
    @TargetApi(19)
    public final int hashCode() {
        return Objects.hash(this.A01, this.A00);
    }

    public AnonymousClass0kO(Map<C02920k8, Set<String>> map) {
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (Map.Entry<C02920k8, Set<String>> entry : map.entrySet()) {
            C02920k8 key = entry.getKey();
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

    public static boolean A02(C02920k8 r2, C02920k8 r3, boolean z) {
        if (!r2.equals(r3)) {
            if (!z) {
                return false;
            }
            Set<C02920k8> set = AnonymousClass0k6.A0z.get(r3);
            if (set == null) {
                set = AnonymousClass0k6.A00(AnonymousClass0k6.A01);
            }
            if (set.contains(r2)) {
                return true;
            }
            return false;
        }
        return true;
    }

    @Deprecated
    public final boolean A04(@Nullable AnonymousClass0k7 r3, Context context) {
        return A05(r3, AnonymousClass0k6.A10.contains(AnonymousClass0kC.A03(context, context.getPackageName())));
    }
}
