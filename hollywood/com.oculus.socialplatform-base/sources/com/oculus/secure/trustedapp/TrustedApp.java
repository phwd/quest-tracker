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
    public final Map<AppSignatureHash, Set<String>> mTrustedPackages;
    public final Set<AppSignatureHash> mTrustedSignatures;

    public static class VerifiedCallerInfo {
        public final boolean isTrusted;
        public final Set<String> packageNames;
        public final AppSignatureHash signatureHash;
        public final int uid;

        public static VerifiedCallerInfo createTrusted(int i, AppSignatureHash appSignatureHash, Set<String> set) {
            return new VerifiedCallerInfo(true, i, appSignatureHash, set);
        }

        public static VerifiedCallerInfo createUntrusted(int i, AppSignatureHash appSignatureHash, Set<String> set) {
            return new VerifiedCallerInfo(false, i, appSignatureHash, set);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("VerifiedCallerInfo{isTrusted=");
            sb.append(this.isTrusted);
            sb.append(", uid=");
            sb.append(this.uid);
            sb.append(", sha1=");
            AppSignatureHash appSignatureHash = this.signatureHash;
            sb.append(appSignatureHash.sha1Hash);
            sb.append(", sha2=");
            sb.append(appSignatureHash.sha256Hash);
            sb.append(", packageNames=");
            sb.append(this.packageNames);
            sb.append('}');
            return sb.toString();
        }

        public VerifiedCallerInfo(boolean z, int i, AppSignatureHash appSignatureHash, Set<String> set) {
            this.isTrusted = z;
            this.uid = i;
            this.signatureHash = appSignatureHash;
            this.packageNames = Collections.unmodifiableSet(new HashSet(set));
        }
    }

    public static boolean checkDebugSignature(AppSignatureHash appSignatureHash) {
        if (AllFamilyTrustedSignatures.FB_DEBUG.contains(appSignatureHash) || AllFamilyTrustedSignatures.WHATSAPP_DEBUG.contains(appSignatureHash)) {
            return true;
        }
        return false;
    }

    public static boolean checkDebugSignatureFromPackageName(Context context, String str) {
        return checkDebugSignature(AppVerifier.getSignatureFromPackageName(context, str));
    }

    public static boolean checkSameSignatureCaller(Context context) {
        try {
            return AppVerifier.verifySameSignature(context, context.getApplicationInfo().uid, Binder.getCallingUid());
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static int getCallingUid() throws IllegalStateException {
        if (Binder.getCallingPid() != Process.myPid()) {
            return Binder.getCallingUid();
        }
        throw new IllegalStateException("This method should be called on behalf of an IPC transaction from binder thread.");
    }

    public boolean checkTrustedApp(int i, Context context) {
        return checkTrustedAppInfo(i, context).isTrusted;
    }

    public VerifiedCallerInfo checkTrustedAppInfo(int i, Context context) {
        String[] packageNamesFromUid = AppVerifier.getPackageNamesFromUid(context, i);
        HashSet hashSet = new HashSet(Arrays.asList(packageNamesFromUid));
        AppSignatureHash signatureHash = AppVerifier.getSignatureHash(AppVerifier.getSignatureFromUidPackageNames(context, packageNamesFromUid));
        if (this.mTrustedSignatures.contains(signatureHash)) {
            return VerifiedCallerInfo.createTrusted(i, signatureHash, hashSet);
        }
        HashSet hashSet2 = new HashSet();
        if (this.mTrustedPackages.containsKey(signatureHash)) {
            hashSet2.addAll(hashSet);
            hashSet2.retainAll(this.mTrustedPackages.get(signatureHash));
        }
        if (!hashSet2.isEmpty()) {
            return VerifiedCallerInfo.createTrusted(i, signatureHash, hashSet2);
        }
        return VerifiedCallerInfo.createUntrusted(i, signatureHash, hashSet);
    }

    public boolean checkTrustedCaller(Context context) {
        return checkTrustedCallerAppInfo(context).isTrusted;
    }

    public VerifiedCallerInfo checkTrustedCallerAppInfo(Context context) {
        return checkTrustedAppInfo(getCallingUid(), context);
    }

    public VerifiedCallerInfo enforceTrustedApp(int i, Context context) {
        VerifiedCallerInfo checkTrustedAppInfo = checkTrustedAppInfo(i, context);
        if (checkTrustedAppInfo.isTrusted) {
            return checkTrustedAppInfo;
        }
        throw new SecurityException("Access denied.");
    }

    public VerifiedCallerInfo enforceTrustedCaller(Context context) {
        VerifiedCallerInfo checkTrustedCallerAppInfo = checkTrustedCallerAppInfo(context);
        if (checkTrustedCallerAppInfo.isTrusted) {
            return checkTrustedCallerAppInfo;
        }
        throw new SecurityException("Access denied.");
    }

    public TrustedApp(Map<AppSignatureHash, Set<String>> map) {
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        for (AppSignatureHash appSignatureHash : map.keySet()) {
            if (map.get(appSignatureHash) == null || !map.get(appSignatureHash).contains("*|all_packages|*")) {
                if (!hashMap.containsKey(appSignatureHash)) {
                    hashMap.put(appSignatureHash, new HashSet());
                }
                ((Set) hashMap.get(appSignatureHash)).addAll(map.get(appSignatureHash));
            } else {
                hashSet.add(appSignatureHash);
            }
        }
        this.mTrustedSignatures = Collections.unmodifiableSet(hashSet);
        this.mTrustedPackages = Collections.unmodifiableMap(hashMap);
    }

    public TrustedApp(Set<String> set) {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
