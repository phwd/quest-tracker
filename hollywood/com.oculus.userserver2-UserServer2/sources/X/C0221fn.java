package X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;

@SuppressLint({"CatchGeneralException", "DeprecatedMethod", "TodoWithoutTask"})
/* renamed from: X.fn  reason: case insensitive filesystem */
public final class C0221fn {
    public final Map<C0210fY, Set<String>> A00;
    public final Set<C0210fY> A01;

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0221fn)) {
            return false;
        }
        C0221fn fnVar = (C0221fn) obj;
        Set<C0210fY> set = fnVar.A01;
        if (set != null) {
            z = set.equals(this.A01);
        } else {
            z = false;
            if (this.A01 == null) {
                z = true;
            }
        }
        Map<C0210fY, Set<String>> map = fnVar.A00;
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

    @Nullable
    public static fX A00(Context context, @Nullable Intent intent, @Nullable AbstractC0201ew ewVar) {
        int callingUid;
        List unmodifiableList;
        String[] strArr;
        ComponentName callingActivity;
        fX A002;
        int i;
        if (!(intent == null || (A002 = C0215ff.A00(context, intent, ewVar)) == null)) {
            if (Binder.getCallingPid() == Process.myPid() || Binder.getCallingUid() == (i = A002.A00)) {
                return A002;
            }
            String format = String.format(Locale.US, "Uid %d from PI not equal to uid %d from binder data", Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()));
            if (ewVar != null && !format.isEmpty()) {
                ewVar.A3L(format);
            }
        }
        if ((context instanceof Activity) && (callingActivity = ((Activity) context).getCallingActivity()) != null) {
            String packageName = callingActivity.getPackageName();
            callingUid = C0214fc.A01(context, packageName).applicationInfo.uid;
            unmodifiableList = Collections.unmodifiableList(Arrays.asList(packageName));
            strArr = new String[0];
        } else if (Binder.getCallingPid() == Process.myPid()) {
            if (ewVar != null && !"This method must be called on behalf of an IPC transaction from binder thread.".isEmpty()) {
                ewVar.A3L("This method must be called on behalf of an IPC transaction from binder thread.");
            }
            if (ewVar == null || "AppIdentity not found for caller".isEmpty()) {
                return null;
            }
            ewVar.A3L("AppIdentity not found for caller");
            return null;
        } else {
            callingUid = Binder.getCallingUid();
            unmodifiableList = Collections.unmodifiableList(Arrays.asList(C0214fc.A06(context, callingUid)));
            strArr = new String[0];
        }
        return new fX(callingUid, unmodifiableList, C0214fc.A04(context, (String[]) unmodifiableList.toArray(strArr)), null, null);
    }

    public static boolean A02(C0210fY fYVar) {
        if (C0209fW.A14.contains(fYVar) || C0209fW.A1O.contains(fYVar) || C0209fW.A1E.contains(fYVar) || C0209fW.A1J.contains(fYVar) || C0209fW.A17.contains(fYVar)) {
            return true;
        }
        return false;
    }

    @SuppressLint({"ObjectsUse"})
    @TargetApi(19)
    public final int hashCode() {
        return Objects.hash(this.A01, this.A00);
    }

    public C0221fn(Map<C0210fY, Set<String>> map) {
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (Map.Entry<C0210fY, Set<String>> entry : map.entrySet()) {
            C0210fY key = entry.getKey();
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

    public static boolean A01(Context context) {
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

    public static boolean A03(C0210fY fYVar, C0210fY fYVar2, boolean z) {
        if (!fYVar.equals(fYVar2)) {
            if (!z) {
                return false;
            }
            Set<C0210fY> set = C0209fW.A0z.get(fYVar2);
            if (set == null) {
                set = C0209fW.A00(C0209fW.A01);
            }
            if (set.contains(fYVar)) {
                return true;
            }
            return false;
        }
        return true;
    }
}
