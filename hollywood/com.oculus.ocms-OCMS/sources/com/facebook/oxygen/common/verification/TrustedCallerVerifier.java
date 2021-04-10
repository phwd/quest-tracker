package com.facebook.oxygen.common.verification;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Process;
import androidx.annotation.VisibleForTesting;
import com.facebook.oxygen.installer.signatures.SignatureHash;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;

public class TrustedCallerVerifier {
    public static final String ALL_PACKAGES_MARKER = "*|all_packages|*";
    private final PackageManager mPackageManager;
    private final ImmutableSetMultimap<Signature, String> mTrustedPackages;
    private final ImmutableSet<Signature> mTrustedSignatures;

    public TrustedCallerVerifier(Multimap<Signature, String> multimap, PackageManager packageManager) {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        ImmutableSetMultimap.Builder builder2 = ImmutableSetMultimap.builder();
        for (Signature signature : multimap.keySet()) {
            if (multimap.containsEntry(signature, "*|all_packages|*")) {
                builder.add((Object) signature);
            } else {
                builder2.putAll((Object) signature, (Iterable) multimap.get(signature));
            }
        }
        this.mTrustedSignatures = builder.build();
        this.mTrustedPackages = builder2.build();
        this.mPackageManager = packageManager;
    }

    public TrustedCallerInfo checkTrustedCallerInfo() {
        if (Binder.getCallingPid() != Process.myPid()) {
            int callingUid = Binder.getCallingUid();
            int callingPid = Binder.getCallingPid();
            ImmutableSet<String> uidPackageNames = getUidPackageNames(callingUid);
            Signature uidSignature = getUidSignature(uidPackageNames);
            if (this.mTrustedSignatures.contains(uidSignature)) {
                return TrustedCallerInfo.createTrusted(callingUid, callingPid, uidSignature, uidPackageNames);
            }
            Sets.SetView intersection = Sets.intersection(uidPackageNames, this.mTrustedPackages.get(uidSignature));
            if (!intersection.isEmpty()) {
                return TrustedCallerInfo.createTrusted(callingUid, callingPid, uidSignature, intersection);
            }
            return TrustedCallerInfo.createUntrusted(callingUid, callingPid, uidSignature, uidPackageNames);
        }
        throw new IllegalStateException("This method should be called on behalf of an IPC transaction from binder thread.");
    }

    public boolean checkTrustedCaller() {
        return checkTrustedCallerInfo().isTrusted;
    }

    public TrustedCallerInfo enforceTrustedCaller() {
        TrustedCallerInfo checkTrustedCallerInfo = checkTrustedCallerInfo();
        if (checkTrustedCallerInfo.isTrusted) {
            return checkTrustedCallerInfo;
        }
        throw new SecurityException("Access denied. Caller is not trusted: " + checkTrustedCallerInfo);
    }

    public ImmutableSet<String> getUidPackageNames(int i) {
        String[] packagesForUid = this.mPackageManager.getPackagesForUid(i);
        if (packagesForUid != null && packagesForUid.length != 0) {
            return ImmutableSet.copyOf(packagesForUid);
        }
        throw new SecurityException("No packages associated with uid: " + i);
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public Signature getUidSignature(Set<String> set) {
        Signature signature = null;
        for (String str : set) {
            try {
                PackageInfo packageInfo = this.mPackageManager.getPackageInfo(str, 64);
                if (!str.equals(packageInfo.packageName)) {
                    throw new SecurityException("Package name mismatch: expected=" + str + ", was=" + packageInfo.packageName);
                } else if (packageInfo.signatures == null || packageInfo.signatures.length == 0) {
                    throw new SecurityException("Signatures are missing: " + str);
                } else if (packageInfo.signatures.length <= 1) {
                    Signature signature2 = packageInfo.signatures[0];
                    if (signature == null) {
                        signature = signature2;
                    } else if (!signature.equals(signature2)) {
                        throw new SecurityException("Uid " + set + " has inconsistent signatures across packages.");
                    }
                } else {
                    throw new SecurityException("Multiple signatures not supported: " + str);
                }
            } catch (PackageManager.NameNotFoundException unused) {
                throw new SecurityException("Name not found: " + str);
            }
        }
        if (signature != null) {
            return signature;
        }
        throw new SecurityException("No uid signature.");
    }

    public static class TrustedCallerInfo {
        public final boolean isTrusted;
        public final ImmutableSet<String> packageNames;
        public final int pid;
        public final Signature signature;
        public final int uid;

        private TrustedCallerInfo(boolean z, int i, int i2, Signature signature2, Set<String> set) {
            this.isTrusted = z;
            this.uid = i;
            this.pid = i2;
            this.signature = signature2;
            this.packageNames = ImmutableSet.copyOf((Collection) set);
        }

        public static TrustedCallerInfo createTrusted(int i, int i2, Signature signature2, Set<String> set) {
            return new TrustedCallerInfo(true, i, i2, signature2, set);
        }

        public static TrustedCallerInfo createUntrusted(int i, int i2, Signature signature2, Set<String> set) {
            return new TrustedCallerInfo(false, i, i2, signature2, set);
        }

        public String toString() {
            return "TrustedCallerInfo{isTrusted=" + this.isTrusted + ", uid=" + this.uid + ", signature=" + SignatureHash.hashBase64(this.signature) + ", packageNames=" + this.packageNames + '}';
        }
    }
}
