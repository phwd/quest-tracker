package X;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Process;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;

public final class R3 {
    public final PackageManager A00;
    public final ImmutableSet<Signature> A01;
    public final ImmutableSetMultimap<Signature, String> A02;

    public R3(Qy<Signature, String> qy, PackageManager packageManager) {
        C00179c r4 = new C00179c();
        N1 n1 = new N1();
        for (Signature signature : qy.keySet()) {
            if (qy.A1C(signature, "*|all_packages|*")) {
                r4.A00(signature);
            } else {
                n1.A04(signature, qy.A1b(signature));
            }
        }
        this.A01 = r4.build();
        this.A02 = n1.A02();
        this.A00 = packageManager;
    }

    public final R2 A00() {
        String str;
        String str2;
        boolean z;
        StringBuilder sb;
        String str3;
        int length;
        if (Binder.getCallingPid() != Process.myPid()) {
            int callingUid = Binder.getCallingUid();
            Binder.getCallingPid();
            PackageManager packageManager = this.A00;
            String[] packagesForUid = packageManager.getPackagesForUid(callingUid);
            if (packagesForUid == null || packagesForUid.length == 0) {
                str = AnonymousClass06.A01("No packages associated with uid: ", callingUid);
            } else {
                ImmutableSet<String> A07 = ImmutableSet.A07(packagesForUid);
                Signature signature = null;
                for (String str4 : A07) {
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
                                    sb2.append(A07);
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
                        throw new SecurityException(AnonymousClass06.A03("Name not found: ", str4));
                    }
                }
                if (signature != null) {
                    if (this.A01.contains(signature)) {
                        z = true;
                    } else {
                        ImmutableSetMultimap<Signature, String> immutableSetMultimap = this.A02;
                        ImmutableSet immutableSet = (ImmutableSet) MoreObjects.firstNonNull(((ImmutableMultimap) immutableSetMultimap).A00.get(signature), immutableSetMultimap.A00);
                        Preconditions.checkNotNull(A07, "set1");
                        Preconditions.checkNotNull(immutableSet, "set2");
                        MS ms = new MS(A07, immutableSet);
                        z = false;
                        if (!ms.isEmpty()) {
                            return new R2(true, callingUid, signature, ms);
                        }
                    }
                    return new R2(z, callingUid, signature, A07);
                }
                str2 = "No uid signature.";
                throw new SecurityException(str2);
            }
            throw new SecurityException(str);
        }
        throw new IllegalStateException("This method should be called on behalf of an IPC transaction from binder thread.");
    }
}
