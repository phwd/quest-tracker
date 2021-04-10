package com.oculus.vrshell.panelservice;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SigCertVerifier {
    public static final String ALL_PACKAGES_MARKER = "*|all_packages|*";
    private final PackageManager mPackageManager;
    private final Set<Signature> mTrustedBundles;
    private final Map<Signature, Set<String>> mTrustedPackages;
    private final Set<Signature> mTrustedSignatures;

    public SigCertVerifier(Context context) {
        this(VerifierConstants.getFirstPartyOculusStorePackages(false), VerifierConstants.getFirstPartyOculusStoreAssetBundles(), context, context.getPackageManager());
    }

    public SigCertVerifier(Map<Signature, Set<String>> trustSpec, Set<Signature> assetBundleTrustSpec, Context context, PackageManager packageManager) {
        Set<Signature> trustedSignatures = new HashSet<>();
        Map<Signature, Set<String>> trustedPackages = new HashMap<>();
        for (Map.Entry<Signature, Set<String>> entry : trustSpec.entrySet()) {
            if (entry.getValue().contains(ALL_PACKAGES_MARKER)) {
                trustedSignatures.add(entry.getKey());
            } else {
                Set<String> packages = trustedPackages.get(entry.getKey());
                if (packages == null) {
                    packages = new HashSet<>();
                    trustedPackages.put(entry.getKey(), packages);
                }
                packages.addAll(entry.getValue());
            }
        }
        this.mTrustedSignatures = Collections.unmodifiableSet(trustedSignatures);
        this.mTrustedPackages = Collections.unmodifiableMap(trustedPackages);
        this.mTrustedBundles = Collections.unmodifiableSet(assetBundleTrustSpec);
        this.mPackageManager = packageManager;
    }

    public SigCertInfo checkSigCertInfo(int uid) {
        Set<String> uidPackageNames = getUidPackageNames(uid);
        Signature uidSignature = getUidSignature(uidPackageNames);
        if (this.mTrustedSignatures.contains(uidSignature)) {
            return SigCertInfo.createTrusted(uidSignature, uidPackageNames);
        }
        Set<String> trustedPackageNames = this.mTrustedPackages.get(uidSignature);
        if (trustedPackageNames == null) {
            trustedPackageNames = Collections.emptySet();
        }
        Set<String> uidTrustedPackageNames = new HashSet<>();
        for (String pn : trustedPackageNames) {
            if (uidPackageNames.contains(pn)) {
                uidTrustedPackageNames.add(pn);
            }
        }
        if (uidTrustedPackageNames.isEmpty()) {
            return SigCertInfo.createUntrusted(uidSignature, uidPackageNames);
        }
        return SigCertInfo.createTrusted(uidSignature, uidTrustedPackageNames);
    }

    public SigCertInfo checkAssetBundleSignature(int uid) {
        Set<String> uidPackageNames = getUidPackageNames(uid);
        Signature uidSignature = getUidSignature(uidPackageNames);
        if (this.mTrustedBundles.contains(uidSignature)) {
            return SigCertInfo.createTrusted(uidSignature, uidPackageNames);
        }
        return SigCertInfo.createUntrusted(uidSignature, uidPackageNames);
    }

    /* access modifiers changed from: protected */
    public Set<String> getUidPackageNames(int uid) {
        Set<String> packages = new HashSet<>();
        String[] uidPackageNames = this.mPackageManager.getPackagesForUid(uid);
        if (uidPackageNames == null) {
            Log.e(PanelVerifier.TAG, String.format("getUidPackageNames(%d): no packages associated with uid", Integer.valueOf(uid)));
        } else {
            packages.addAll(Arrays.asList(uidPackageNames));
        }
        return Collections.unmodifiableSet(packages);
    }

    /* access modifiers changed from: protected */
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

    public static class SigCertInfo {
        public final boolean isTrusted;
        public final Set<String> packageNames;
        public final Signature signature;

        private SigCertInfo(boolean isTrusted2, Signature signature2, Set<String> packageNames2) {
            this.isTrusted = isTrusted2;
            this.signature = signature2;
            this.packageNames = Collections.unmodifiableSet(packageNames2);
        }

        public static SigCertInfo createTrusted(Signature signature2, Set<String> packageNames2) {
            return new SigCertInfo(true, signature2, packageNames2);
        }

        public static SigCertInfo createUntrusted(Signature signature2, Set<String> packageNames2) {
            return new SigCertInfo(false, signature2, packageNames2);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("SigCertInfo(");
            sb.append("isTrusted=");
            sb.append(this.isTrusted);
            sb.append(", packageNames=[");
            int cnt = 0;
            for (String pn : this.packageNames) {
                cnt++;
                if (cnt > 1) {
                    sb.append(",");
                }
                sb.append(pn);
            }
            sb.append("]");
            sb.append(", signature=");
            sb.append(this.signature.toCharsString());
            sb.append(")");
            return sb.toString();
        }
    }
}
