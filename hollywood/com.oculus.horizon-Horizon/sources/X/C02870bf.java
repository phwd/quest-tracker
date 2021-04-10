package X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"CatchGeneralException", "DeprecatedMethod", "TodoWithoutTask"})
/* renamed from: X.0bf  reason: invalid class name and case insensitive filesystem */
public final class C02870bf {
    public final Map<AnonymousClass0bQ, Set<String>> A00;
    public final Set<AnonymousClass0bQ> A01;

    @Deprecated
    public static C02790bO A00(int i, Context context) {
        String[] A06 = AnonymousClass0bU.A06(context, i);
        return new C02790bO(i, Collections.unmodifiableList(Arrays.asList(A06)), AnonymousClass0bU.A04(context, A06), null, null);
    }

    @Deprecated
    public static boolean A05(String str, Context context, @Nullable Intent intent, @Nullable AnonymousClass0b1 r6) {
        C02790bO A002 = AnonymousClass0bV.A00(context, intent, r6, false);
        if (A002 == null) {
            if (r6 != null) {
                r6.report(String.format("AppIdentity not found for caller when checking %s permission", str));
            }
            throw new SecurityException("Invalid Caller Identity (null)");
        }
        if (r6 != null) {
            C02840bc.A01(r6);
        } else {
            C02840bc.A00();
        }
        if (C02840bc.A05(context, A002.A01(), str)) {
            return true;
        }
        C02890bh r1 = new C02890bh();
        if (!TextUtils.isEmpty(str)) {
            r1.A03.add(str);
            return r1.A00().A00(context, intent, r6);
        }
        throw new IllegalArgumentException();
    }

    public final boolean A08(@Nullable C02790bO r10, boolean z) {
        AnonymousClass0bQ r6;
        if (!(r10 == null || (r6 = r10.A01) == null)) {
            for (AnonymousClass0bQ r0 : this.A01) {
                if (A04(r6, r0, z)) {
                    return true;
                }
            }
            Map<AnonymousClass0bQ, Set<String>> map = this.A00;
            for (AnonymousClass0bQ r3 : map.keySet()) {
                if (A04(r6, r3, z)) {
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
        if (!(obj instanceof C02870bf)) {
            return false;
        }
        C02870bf r5 = (C02870bf) obj;
        Set<AnonymousClass0bQ> set = r5.A01;
        if (set != null) {
            z = set.equals(this.A01);
        } else {
            z = false;
            if (this.A01 == null) {
                z = true;
            }
        }
        Map<AnonymousClass0bQ, Set<String>> map = r5.A00;
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

    @SuppressLint({"ObjectsUse"})
    @TargetApi(19)
    public final int hashCode() {
        return Objects.hash(this.A01, this.A00);
    }

    public static boolean A03(AnonymousClass0bQ r1) {
        if (C02780bN.A14.contains(r1) || C02780bN.A1O.contains(r1) || C02780bN.A1E.contains(r1) || C02780bN.A1J.contains(r1) || C02780bN.A17.contains(r1)) {
            return true;
        }
        return false;
    }

    @Deprecated
    public final boolean A06(@Nullable Context context) {
        if (context == null) {
            return false;
        }
        return A07(A01(context), context);
    }

    public C02870bf(Map<AnonymousClass0bQ, Set<String>> map) {
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (Map.Entry<AnonymousClass0bQ, Set<String>> entry : map.entrySet()) {
            AnonymousClass0bQ key = entry.getKey();
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

    @Deprecated
    public static C02790bO A01(Context context) {
        if (Binder.getCallingPid() != Process.myPid()) {
            return A00(Binder.getCallingUid(), context);
        }
        throw new IllegalStateException("This method should be called on behalf of an IPC transaction from binder thread.");
    }

    public static boolean A02(Context context) {
        try {
            int i = context.getApplicationInfo().uid;
            int callingUid = Binder.getCallingUid();
            try {
                int checkSignatures = context.getPackageManager().checkSignatures(i, callingUid);
                if (i == callingUid || checkSignatures == 0) {
                    return true;
                }
                return false;
            } catch (RuntimeException e) {
                throw new SecurityException(e);
            }
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static boolean A04(AnonymousClass0bQ r4, AnonymousClass0bQ r5, boolean z) {
        if (!r4.equals(r5)) {
            if (!z) {
                return false;
            }
            Set<AnonymousClass0bQ> set = C02780bN.A0z.get(r5);
            if (set == null) {
                set = C02780bN.A00(C02780bN.A01);
            }
            if (set.contains(r4)) {
                return true;
            }
            return false;
        }
        return true;
    }

    @Deprecated
    public final boolean A07(@Nullable C02790bO r3, Context context) {
        return A08(r3, C02780bN.A10.contains(AnonymousClass0bU.A03(context, context.getPackageName())));
    }
}
