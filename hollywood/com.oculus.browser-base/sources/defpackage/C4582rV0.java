package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: rV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4582rV0 {

    /* renamed from: a  reason: collision with root package name */
    public final Set f11202a;
    public final Map b;
    public final PackageManager c;

    public C4582rV0(Map map, Set set, PackageManager packageManager) {
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (((Set) entry.getValue()).contains("*|all_packages|*")) {
                hashSet.add(entry.getKey());
            } else {
                Set set2 = (Set) hashMap.get(entry.getKey());
                if (set2 == null) {
                    set2 = new HashSet();
                    hashMap.put(entry.getKey(), set2);
                }
                set2.addAll((Collection) entry.getValue());
            }
        }
        this.f11202a = Collections.unmodifiableSet(hashSet);
        this.b = Collections.unmodifiableMap(hashMap);
        Collections.unmodifiableSet(set);
        this.c = packageManager;
    }

    public C4412qV0 a(int i) {
        HashSet hashSet = new HashSet();
        String[] packagesForUid = this.c.getPackagesForUid(i);
        if (packagesForUid == null) {
            Log.e("PanelVerifier", String.format("getUidPackageNames(%d): no packages associated with uid", Integer.valueOf(i)));
        } else {
            hashSet.addAll(Arrays.asList(packagesForUid));
        }
        Set<String> unmodifiableSet = Collections.unmodifiableSet(hashSet);
        Signature signature = null;
        for (String str : unmodifiableSet) {
            try {
                PackageInfo packageInfo = this.c.getPackageInfo(str, 64);
                if (str.equals(packageInfo.packageName)) {
                    Signature[] signatureArr = packageInfo.signatures;
                    if (signatureArr == null || signatureArr.length == 0) {
                        throw new SecurityException(AbstractC2531fV.f("Signatures are missing: ", str));
                    } else if (signatureArr.length <= 1) {
                        Signature signature2 = signatureArr[0];
                        if (signature == null) {
                            signature = signature2;
                        } else if (!signature.equals(signature2)) {
                            throw new SecurityException("Uid " + unmodifiableSet + " has inconsistent signatures across packages.");
                        }
                    } else {
                        throw new SecurityException(AbstractC2531fV.f("Multiple signatures not supported: ", str));
                    }
                } else {
                    StringBuilder k = AbstractC2531fV.k("Package name mismatch: expected=", str, ", was=");
                    k.append(packageInfo.packageName);
                    throw new SecurityException(k.toString());
                }
            } catch (PackageManager.NameNotFoundException unused) {
                throw new SecurityException(AbstractC2531fV.f("Name not found: ", str));
            }
        }
        if (signature == null) {
            throw new SecurityException("No uid signature.");
        } else if (this.f11202a.contains(signature)) {
            return new C4412qV0(true, signature, unmodifiableSet);
        } else {
            Set<String> set = (Set) this.b.get(signature);
            if (set == null) {
                set = Collections.emptySet();
            }
            HashSet hashSet2 = new HashSet();
            for (String str2 : set) {
                if (unmodifiableSet.contains(str2)) {
                    hashSet2.add(str2);
                }
            }
            if (hashSet2.isEmpty()) {
                return new C4412qV0(false, signature, unmodifiableSet);
            }
            return new C4412qV0(true, signature, hashSet2);
        }
    }
}
