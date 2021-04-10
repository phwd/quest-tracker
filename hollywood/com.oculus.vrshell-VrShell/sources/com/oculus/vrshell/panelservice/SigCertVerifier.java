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

    public SigCertVerifier(Map<Signature, Set<String>> map, Set<Signature> set, Context context, PackageManager packageManager) {
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (Map.Entry<Signature, Set<String>> entry : map.entrySet()) {
            if (entry.getValue().contains(ALL_PACKAGES_MARKER)) {
                hashSet.add(entry.getKey());
            } else {
                Set set2 = (Set) hashMap.get(entry.getKey());
                if (set2 == null) {
                    set2 = new HashSet();
                    hashMap.put(entry.getKey(), set2);
                }
                set2.addAll(entry.getValue());
            }
        }
        this.mTrustedSignatures = Collections.unmodifiableSet(hashSet);
        this.mTrustedPackages = Collections.unmodifiableMap(hashMap);
        this.mTrustedBundles = Collections.unmodifiableSet(set);
        this.mPackageManager = packageManager;
    }

    public SigCertInfo checkSigCertInfo(int i) {
        Set<String> uidPackageNames = getUidPackageNames(i);
        Signature uidSignature = getUidSignature(uidPackageNames);
        if (this.mTrustedSignatures.contains(uidSignature)) {
            return SigCertInfo.createTrusted(uidSignature, uidPackageNames);
        }
        Set<String> set = this.mTrustedPackages.get(uidSignature);
        if (set == null) {
            set = Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (String str : set) {
            if (uidPackageNames.contains(str)) {
                hashSet.add(str);
            }
        }
        if (hashSet.isEmpty()) {
            return SigCertInfo.createUntrusted(uidSignature, uidPackageNames);
        }
        return SigCertInfo.createTrusted(uidSignature, hashSet);
    }

    public SigCertInfo checkAssetBundleSignature(int i) {
        Set<String> uidPackageNames = getUidPackageNames(i);
        Signature uidSignature = getUidSignature(uidPackageNames);
        if (this.mTrustedBundles.contains(uidSignature)) {
            return SigCertInfo.createTrusted(uidSignature, uidPackageNames);
        }
        return SigCertInfo.createUntrusted(uidSignature, uidPackageNames);
    }

    /* access modifiers changed from: protected */
    public Set<String> getUidPackageNames(int i) {
        HashSet hashSet = new HashSet();
        String[] packagesForUid = this.mPackageManager.getPackagesForUid(i);
        if (packagesForUid == null) {
            Log.e(PanelVerifier.TAG, String.format("getUidPackageNames(%d): no packages associated with uid", Integer.valueOf(i)));
        } else {
            hashSet.addAll(Arrays.asList(packagesForUid));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    /* access modifiers changed from: protected */
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

    public static class SigCertInfo {
        public final boolean isTrusted;
        public final Set<String> packageNames;
        public final Signature signature;

        private SigCertInfo(boolean z, Signature signature2, Set<String> set) {
            this.isTrusted = z;
            this.signature = signature2;
            this.packageNames = Collections.unmodifiableSet(set);
        }

        public static SigCertInfo createTrusted(Signature signature2, Set<String> set) {
            return new SigCertInfo(true, signature2, set);
        }

        public static SigCertInfo createUntrusted(Signature signature2, Set<String> set) {
            return new SigCertInfo(false, signature2, set);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("SigCertInfo(");
            sb.append("isTrusted=");
            sb.append(this.isTrusted);
            sb.append(", packageNames=[");
            int i = 0;
            for (String str : this.packageNames) {
                i++;
                if (i > 1) {
                    sb.append(",");
                }
                sb.append(str);
            }
            sb.append("]");
            sb.append(", signature=");
            sb.append(this.signature.toCharsString());
            sb.append(")");
            return sb.toString();
        }
    }
}
