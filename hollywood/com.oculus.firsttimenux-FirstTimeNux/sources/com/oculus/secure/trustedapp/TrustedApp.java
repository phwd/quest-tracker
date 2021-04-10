package com.oculus.secure.trustedapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Binder;
import android.os.Process;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SuppressLint({"CatchGeneralException", "TodoWithoutTask"})
public class TrustedApp {
    public static final String ALL_PACKAGES_MARKER = "*|all_packages|*";
    private final Map<AppSignatureHash, Set<String>> mTrustedPackages;
    private final Set<AppSignatureHash> mTrustedSignatures;

    public TrustedApp(Map<AppSignatureHash, Set<String>> trustedPackages) {
        Set<AppSignatureHash> mutableTrustedSignatures = new HashSet<>();
        Map<AppSignatureHash, Set<String>> mutableTrustedPackages = new HashMap<>();
        for (AppSignatureHash signature : trustedPackages.keySet()) {
            if (trustedPackages.get(signature) == null || !trustedPackages.get(signature).contains("*|all_packages|*")) {
                if (!mutableTrustedPackages.containsKey(signature)) {
                    mutableTrustedPackages.put(signature, new HashSet<>());
                }
                mutableTrustedPackages.get(signature).addAll(trustedPackages.get(signature));
            } else {
                mutableTrustedSignatures.add(signature);
            }
        }
        this.mTrustedSignatures = Collections.unmodifiableSet(mutableTrustedSignatures);
        this.mTrustedPackages = Collections.unmodifiableMap(mutableTrustedPackages);
    }

    public TrustedApp(Set<String> set) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public static int getCallingUid() throws IllegalStateException {
        if (Binder.getCallingPid() != Process.myPid()) {
            return Binder.getCallingUid();
        }
        throw new IllegalStateException("This method should be called on behalf of an IPC transaction from binder thread.");
    }

    public VerifiedCallerInfo checkTrustedCallerAppInfo(Context context) {
        return checkTrustedAppInfo(getCallingUid(), context);
    }

    public static boolean checkSameSignatureCaller(Context context) {
        try {
            return AppVerifier.verifySameSignature(context, context.getApplicationInfo().uid, Binder.getCallingUid());
        } catch (SecurityException e) {
            return false;
        }
    }

    public boolean checkTrustedCaller(Context context) {
        return checkTrustedCallerAppInfo(context).isTrusted;
    }

    public VerifiedCallerInfo enforceTrustedCaller(Context context) {
        VerifiedCallerInfo verifiedCallerInfo = checkTrustedCallerAppInfo(context);
        if (verifiedCallerInfo.isTrusted) {
            return verifiedCallerInfo;
        }
        throw new SecurityException("Access denied.");
    }

    public VerifiedCallerInfo checkTrustedAppInfo(int uid, Context context) {
        String[] packageNames = AppVerifier.getPackageNamesFromUid(context, uid);
        HashSet hashSet = new HashSet(Arrays.asList(packageNames));
        AppSignatureHash uidSignatureHash = AppVerifier.getSignatureHash(AppVerifier.getSignatureFromUidPackageNames(context, packageNames));
        if (this.mTrustedSignatures.contains(uidSignatureHash)) {
            return VerifiedCallerInfo.createTrusted(uid, uidSignatureHash, hashSet);
        }
        Set<String> uidTrustedPackageNames = new HashSet<>();
        if (this.mTrustedPackages.containsKey(uidSignatureHash)) {
            uidTrustedPackageNames.addAll(hashSet);
            uidTrustedPackageNames.retainAll(this.mTrustedPackages.get(uidSignatureHash));
        }
        if (!uidTrustedPackageNames.isEmpty()) {
            return VerifiedCallerInfo.createTrusted(uid, uidSignatureHash, uidTrustedPackageNames);
        }
        return VerifiedCallerInfo.createUntrusted(uid, uidSignatureHash, hashSet);
    }

    public boolean checkTrustedApp(int uid, Context context) {
        return checkTrustedAppInfo(uid, context).isTrusted;
    }

    public VerifiedCallerInfo enforceTrustedApp(int uid, Context context) {
        VerifiedCallerInfo verifiedCallerInfo = checkTrustedAppInfo(uid, context);
        if (verifiedCallerInfo.isTrusted) {
            return verifiedCallerInfo;
        }
        throw new SecurityException("Access denied.");
    }

    public static boolean checkDebugSignature(AppSignatureHash appSignature) {
        return AllFamilyTrustedSignatures.FB_DEBUG.contains(appSignature) || AllFamilyTrustedSignatures.WHATSAPP_DEBUG.contains(appSignature);
    }

    public static boolean checkDebugSignatureFromPackageName(Context context, String packageName) {
        return checkDebugSignature(AppVerifier.getSignatureFromPackageName(context, packageName));
    }

    public static class VerifiedCallerInfo {
        public final boolean isTrusted;
        public final Set<String> packageNames;
        public final AppSignatureHash signatureHash;
        public final int uid;

        private VerifiedCallerInfo(boolean isTrusted2, int uid2, AppSignatureHash signatureHash2, Set<String> packageNames2) {
            this.isTrusted = isTrusted2;
            this.uid = uid2;
            this.signatureHash = signatureHash2;
            this.packageNames = Collections.unmodifiableSet(new HashSet(packageNames2));
        }

        public static VerifiedCallerInfo createTrusted(int uid2, AppSignatureHash signature, Set<String> packageNames2) {
            return new VerifiedCallerInfo(true, uid2, signature, packageNames2);
        }

        public static VerifiedCallerInfo createUntrusted(int uid2, AppSignatureHash signature, Set<String> packageNames2) {
            return new VerifiedCallerInfo(false, uid2, signature, packageNames2);
        }

        public String toString() {
            return "VerifiedCallerInfo{isTrusted=" + this.isTrusted + ", uid=" + this.uid + ", sha1=" + this.signatureHash.getSha1Hash() + ", sha2=" + this.signatureHash.getSha256Hash() + ", packageNames=" + this.packageNames + '}';
        }
    }
}
