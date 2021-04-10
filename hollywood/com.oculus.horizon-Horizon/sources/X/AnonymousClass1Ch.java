package X;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Process;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.EmptyImmutableSetMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: X.1Ch  reason: invalid class name */
public final class AnonymousClass1Ch {
    public final PackageManager A00;
    public final ImmutableSet<Signature> A01;
    public final ImmutableSetMultimap<Signature, String> A02;

    public AnonymousClass1Ch(AbstractC07090r4<Signature, String> r6, PackageManager packageManager) {
        ImmutableSetMultimap<Signature, String> immutableSetMultimap;
        AnonymousClass0CC r4 = new AnonymousClass0CC();
        C03690dw r3 = new C03690dw();
        for (Signature signature : r6.keySet()) {
            if (r6.A1s(signature, "*|all_packages|*")) {
                r4.A04(signature);
            } else {
                r3.A02(signature, r6.A2u(signature));
            }
        }
        this.A01 = r4.build();
        Set<Map.Entry<K, Collection<V>>> entrySet = r3.A00.entrySet();
        if (entrySet.isEmpty()) {
            immutableSetMultimap = EmptyImmutableSetMultimap.A00;
        } else {
            ImmutableMap.Builder builder = new ImmutableMap.Builder(entrySet.size());
            for (Map.Entry<K, Collection<V>> entry : entrySet) {
                K key = entry.getKey();
                ImmutableSet A08 = ImmutableSet.A08(entry.getValue());
                if (!A08.isEmpty()) {
                    builder.put(key, A08);
                    A08.size();
                }
            }
            immutableSetMultimap = new ImmutableSetMultimap<>(builder.build());
        }
        this.A02 = immutableSetMultimap;
        this.A00 = packageManager;
    }

    public static final AnonymousClass1E5 A00(AnonymousClass1Ch r9) {
        String str;
        String str2;
        boolean z;
        StringBuilder sb;
        String str3;
        int length;
        if (Binder.getCallingPid() != Process.myPid()) {
            int callingUid = Binder.getCallingUid();
            Binder.getCallingPid();
            PackageManager packageManager = r9.A00;
            String[] packagesForUid = packageManager.getPackagesForUid(callingUid);
            if (packagesForUid == null || packagesForUid.length == 0) {
                str = AnonymousClass006.A01("No packages associated with uid: ", callingUid);
            } else {
                ImmutableSet<String> A09 = ImmutableSet.A09(packagesForUid);
                Signature signature = null;
                for (String str4 : A09) {
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(str4, 64);
                        String str5 = packageInfo.packageName;
                        if (str4.equals(str5)) {
                            Signature[] signatureArr = packageInfo.signatures;
                            if (signatureArr == null || (length = signatureArr.length) == 0) {
                                str3 = "Signatures are missing: ";
                            } else if (length <= 1) {
                                Signature signature2 = signatureArr[0];
                                if (signature == null) {
                                    signature = signature2;
                                } else if (!signature.equals(signature2)) {
                                    StringBuilder sb2 = new StringBuilder("Uid ");
                                    sb2.append(A09);
                                    sb2.append(" has inconsistent signatures across packages.");
                                    str = sb2.toString();
                                }
                            } else {
                                str3 = "Multiple signatures not supported: ";
                            }
                            sb = new StringBuilder(str3);
                            sb.append(str4);
                        } else {
                            sb = new StringBuilder("Package name mismatch: expected=");
                            sb.append(str4);
                            sb.append(", was=");
                            sb.append(str5);
                        }
                        str2 = sb.toString();
                        break;
                    } catch (PackageManager.NameNotFoundException unused) {
                        throw new SecurityException(AnonymousClass006.A05("Name not found: ", str4));
                    }
                }
                if (signature != null) {
                    if (r9.A01.contains(signature)) {
                        z = true;
                    } else {
                        ImmutableSetMultimap<Signature, String> immutableSetMultimap = r9.A02;
                        ImmutableSet immutableSet = (ImmutableSet) MoreObjects.firstNonNull(((ImmutableMultimap) immutableSetMultimap).A00.get(signature), immutableSetMultimap.A00);
                        Preconditions.checkNotNull(A09, "set1");
                        Preconditions.checkNotNull(immutableSet, "set2");
                        C03530dA r1 = new C03530dA(A09, immutableSet);
                        z = false;
                        if (!r1.isEmpty()) {
                            return new AnonymousClass1E5(true, callingUid, signature, r1);
                        }
                    }
                    return new AnonymousClass1E5(z, callingUid, signature, A09);
                }
                str2 = "No uid signature.";
                throw new SecurityException(str2);
            }
            throw new SecurityException(str);
        }
        throw new IllegalStateException("This method should be called on behalf of an IPC transaction from binder thread.");
    }
}
