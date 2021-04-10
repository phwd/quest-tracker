package com.oculus.vrshell.panelservice;

import X.AnonymousClass006;
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
    public final PackageManager mPackageManager;
    public final Set<Signature> mTrustedBundles;
    public final Map<Signature, Set<String>> mTrustedPackages;
    public final Set<Signature> mTrustedSignatures;

    public static class SigCertInfo {
        public final boolean isTrusted;
        public final Set<String> packageNames;
        public final Signature signature;

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

        public SigCertInfo(boolean z, Signature signature2, Set<String> set) {
            this.isTrusted = z;
            this.signature = signature2;
            this.packageNames = Collections.unmodifiableSet(set);
        }
    }

    public Set<String> getUidPackageNames(int i) {
        HashSet hashSet = new HashSet();
        String[] packagesForUid = this.mPackageManager.getPackagesForUid(i);
        if (packagesForUid == null) {
            Log.e("PanelVerifier", String.format("getUidPackageNames(%d): no packages associated with uid", Integer.valueOf(i)));
        } else {
            hashSet.addAll(Arrays.asList(packagesForUid));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public SigCertInfo checkAssetBundleSignature(int i) {
        Set<String> uidPackageNames = getUidPackageNames(i);
        Signature uidSignature = getUidSignature(uidPackageNames);
        if (this.mTrustedBundles.contains(uidSignature)) {
            return SigCertInfo.createTrusted(uidSignature, uidPackageNames);
        }
        return SigCertInfo.createUntrusted(uidSignature, uidPackageNames);
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

    public Signature getUidSignature(Set<String> set) {
        int length;
        Signature signature = null;
        for (String str : set) {
            try {
                PackageInfo packageInfo = this.mPackageManager.getPackageInfo(str, 64);
                String str2 = packageInfo.packageName;
                if (str.equals(str2)) {
                    Signature[] signatureArr = packageInfo.signatures;
                    if (signatureArr == null || (length = signatureArr.length) == 0) {
                        throw new SecurityException(AnonymousClass006.A07("Signatures are missing: ", str));
                    } else if (length <= 1) {
                        Signature signature2 = signatureArr[0];
                        if (signature == null) {
                            signature = signature2;
                        } else if (!signature.equals(signature2)) {
                            StringBuilder sb = new StringBuilder("Uid ");
                            sb.append(set);
                            sb.append(" has inconsistent signatures across packages.");
                            throw new SecurityException(sb.toString());
                        }
                    } else {
                        throw new SecurityException(AnonymousClass006.A07("Multiple signatures not supported: ", str));
                    }
                } else {
                    throw new SecurityException(AnonymousClass006.A0B("Package name mismatch: expected=", str, ", was=", str2));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                throw new SecurityException(AnonymousClass006.A07("Name not found: ", str));
            }
        }
        if (signature != null) {
            return signature;
        }
        throw new SecurityException("No uid signature.");
    }

    public SigCertVerifier(Context context) {
        this(VerifierConstants.getFirstPartyOculusStorePackages(false), VerifierConstants.getFirstPartyOculusStoreAssetBundles(), context, context.getPackageManager());
    }

    public SigCertVerifier(Map<Signature, Set<String>> map, Set<Signature> set, Context context, PackageManager packageManager) {
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (Map.Entry<Signature, Set<String>> entry : map.entrySet()) {
            if (entry.getValue().contains("*|all_packages|*")) {
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
}
