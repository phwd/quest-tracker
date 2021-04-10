package com.oculus.trustedcallerverifier;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Process;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;

public class TrustedCallerVerifier {
    public static final String ALL_PACKAGES_MARKER = "*|all_packages|*";
    private final Context mContext;
    private final PackageManager mPackageManager;
    private final ImmutableSetMultimap<Signature, String> mTrustedPackages;
    private final ImmutableSet<Signature> mTrustedSignatures;

    public TrustedCallerVerifier(Multimap<Signature, String> trustedPackages, Context context, PackageManager packageManager) {
        ImmutableSet.Builder<Signature> trustedSignatureBuilder = ImmutableSet.builder();
        ImmutableSetMultimap.Builder<Signature, String> trustedPackagesBuilder = ImmutableSetMultimap.builder();
        for (Signature signature : trustedPackages.keySet()) {
            if (trustedPackages.containsEntry(signature, ALL_PACKAGES_MARKER)) {
                trustedSignatureBuilder.add(signature);
            } else {
                trustedPackagesBuilder.putAll(signature, trustedPackages.get(signature));
            }
        }
        this.mTrustedSignatures = trustedSignatureBuilder.build();
        this.mTrustedPackages = trustedPackagesBuilder.build();
        this.mContext = context;
        this.mPackageManager = packageManager;
    }

    public TrustedCallerInfo checkTrustedCallerInfo() {
        if (Binder.getCallingPid() != Process.myPid()) {
            int uid = Binder.getCallingUid();
            int pid = Binder.getCallingPid();
            Set<String> uidPackageNames = getUidPackageNames(uid);
            Signature uidSignature = getUidSignature(uidPackageNames);
            if (this.mTrustedSignatures.contains(uidSignature)) {
                return TrustedCallerInfo.createTrusted(uid, pid, uidSignature, uidPackageNames);
            }
            Set<String> uidTrustedPackageNames = Sets.intersection(uidPackageNames, this.mTrustedPackages.get(uidSignature));
            if (!uidTrustedPackageNames.isEmpty()) {
                return TrustedCallerInfo.createTrusted(uid, pid, uidSignature, uidTrustedPackageNames);
            }
            return TrustedCallerInfo.createUntrusted(uid, pid, uidSignature, uidPackageNames);
        }
        throw new IllegalStateException("This method should be called on behalf of an IPC transaction from binder thread.");
    }

    public boolean checkTrustedCaller() {
        return checkTrustedCallerInfo().isTrusted;
    }

    public TrustedCallerInfo enforceTrustedCaller() {
        TrustedCallerInfo trustedCallerInfo = checkTrustedCallerInfo();
        if (trustedCallerInfo.isTrusted) {
            return trustedCallerInfo;
        }
        throw new SecurityException("Access denied. Caller is not trusted: " + trustedCallerInfo);
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public ImmutableSet<String> getUidPackageNames(int uid) {
        String[] uidPackageNames = this.mPackageManager.getPackagesForUid(uid);
        if (uidPackageNames != null && uidPackageNames.length != 0) {
            return ImmutableSet.copyOf(uidPackageNames);
        }
        throw new SecurityException("No packages associated with uid: " + uid);
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public Signature getUidSignature(Set<String> uidPackageNames) {
        Signature uidSignature = null;
        for (String packageName : uidPackageNames) {
            try {
                PackageInfo packageInfo = this.mPackageManager.getPackageInfo(packageName, 64);
                if (!packageName.equals(packageInfo.packageName)) {
                    throw new SecurityException("Package name mismatch: expected=" + packageName + ", was=" + packageInfo.packageName);
                } else if (packageInfo.signatures == null || packageInfo.signatures.length == 0) {
                    throw new SecurityException("Signatures are missing: " + packageName);
                } else if (packageInfo.signatures.length <= 1) {
                    Signature packageSignature = packageInfo.signatures[0];
                    if (uidSignature == null) {
                        uidSignature = packageSignature;
                    } else if (!uidSignature.equals(packageSignature)) {
                        throw new SecurityException("Uid " + uidPackageNames + " has inconsistent signatures across packages.");
                    }
                } else {
                    throw new SecurityException("Multiple signatures not supported: " + packageName);
                }
            } catch (PackageManager.NameNotFoundException e) {
                throw new SecurityException("Name not found: " + packageName);
            }
        }
        if (uidSignature != null) {
            return uidSignature;
        }
        throw new SecurityException("No uid signature.");
    }

    public static class TrustedCallerInfo {
        public final boolean isTrusted;
        public final ImmutableSet<String> packageNames;
        public final int pid;
        public final Signature signature;
        public final int uid;

        private TrustedCallerInfo(boolean isTrusted2, int uid2, int pid2, Signature signature2, Set<String> packageNames2) {
            this.isTrusted = isTrusted2;
            this.uid = uid2;
            this.pid = pid2;
            this.signature = signature2;
            this.packageNames = ImmutableSet.copyOf((Collection) packageNames2);
        }

        public static TrustedCallerInfo createTrusted(int uid2, int pid2, Signature signature2, Set<String> packageNames2) {
            return new TrustedCallerInfo(true, uid2, pid2, signature2, packageNames2);
        }

        public static TrustedCallerInfo createUntrusted(int uid2, int pid2, Signature signature2, Set<String> packageNames2) {
            return new TrustedCallerInfo(false, uid2, pid2, signature2, packageNames2);
        }

        public String toString() {
            return "TrustedCallerInfo{isTrusted=" + this.isTrusted + ", uid=" + this.uid + ", signature=" + this.signature + ", packageNames=" + this.packageNames + '}';
        }
    }
}
