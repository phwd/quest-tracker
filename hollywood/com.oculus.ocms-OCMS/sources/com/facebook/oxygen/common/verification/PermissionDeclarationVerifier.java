package com.facebook.oxygen.common.verification;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.Signature;
import com.facebook.oxygen.common.android.packagemanager.OxpPackageManager;
import com.facebook.oxygen.installer.signatures.SignatureHash;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;

public class PermissionDeclarationVerifier {
    private final PackageManager mPackageManager;
    private final String mPermissionName;
    private final ImmutableSet<Signature> mTrustedSignatures;

    public PermissionDeclarationVerifier(String str, Set<Signature> set, PackageManager packageManager) {
        this.mPermissionName = str;
        this.mTrustedSignatures = ImmutableSet.copyOf((Collection) set);
        this.mPackageManager = packageManager;
    }

    public boolean checkPermissionDeclaration() {
        return getDeclaratonInfo().isTrusted;
    }

    public void enforcePermissionDeclaration() {
        PermissionDeclarationInfo declaratonInfo = getDeclaratonInfo();
        if (!declaratonInfo.isTrusted) {
            throw new SecurityException(declaratonInfo.errorMessage);
        }
    }

    private PermissionDeclarationInfo getDeclaratonInfo() {
        try {
            PermissionInfo permissionInfo = this.mPackageManager.getPermissionInfo(this.mPermissionName, 0);
            if ((permissionInfo.protectionLevel & 15) != 2) {
                return PermissionDeclarationInfo.createUntrusted(String.format(null, "Access denied: permission '%s' is not of signature protection level.", this.mPermissionName));
            }
            try {
                PackageInfo packageInfo = this.mPackageManager.getPackageInfo(permissionInfo.packageName, 64);
                if (packageInfo.signatures == null || packageInfo.signatures.length == 0) {
                    return PermissionDeclarationInfo.createUntrusted(String.format(null, "Access denied: permission '%s' owner package '%s' has no signatures.", this.mPermissionName, permissionInfo.packageName));
                } else if (packageInfo.signatures.length > 1) {
                    return PermissionDeclarationInfo.createUntrusted(String.format(null, "Access denied: permission '%s' owner package '%s' has multiple signatures.", this.mPermissionName, permissionInfo.packageName));
                } else {
                    Signature signature = packageInfo.signatures[0];
                    if (this.mTrustedSignatures.contains(signature)) {
                        return PermissionDeclarationInfo.createTrusted();
                    }
                    return PermissionDeclarationInfo.createUntrusted(String.format(null, "Access denied: permission '%s' is declared by an untrusted package '%s'  (version=%d, signature=%s).", this.mPermissionName, permissionInfo.packageName, Integer.valueOf(packageInfo.versionCode), SignatureHash.hashBase64(signature)));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                return checkPermissionWhenSourcePackageIsNotPresent();
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            return PermissionDeclarationInfo.createUntrusted(String.format(null, "Access denied: '%s' permission is missing.", this.mPermissionName));
        }
    }

    private PermissionDeclarationInfo checkPermissionWhenSourcePackageIsNotPresent() {
        OxpPackageManager.ScopeGuard suppress = OxpPackageManager.suppress();
        try {
            for (PackageInfo packageInfo : this.mPackageManager.getInstalledPackages(0)) {
                try {
                    PackageInfo packageInfo2 = this.mPackageManager.getPackageInfo(packageInfo.packageName, 64);
                    if (packageInfo2.signatures != null && packageInfo2.signatures.length == 1 && this.mTrustedSignatures.contains(packageInfo2.signatures[0]) && this.mPackageManager.checkPermission(this.mPermissionName, packageInfo2.packageName) == 0) {
                        PermissionDeclarationInfo createTrusted = PermissionDeclarationInfo.createTrusted();
                        if (suppress != null) {
                            suppress.close();
                        }
                        return createTrusted;
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            if (suppress != null) {
                suppress.close();
            }
            return PermissionDeclarationInfo.createUntrusted(String.format(null, "Access denied: permission '%s' is declared in trusted packages.", this.mPermissionName));
        } catch (Throwable unused2) {
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public static class PermissionDeclarationInfo {
        @Nullable
        public final String errorMessage;
        public final boolean isTrusted;

        private PermissionDeclarationInfo(boolean z, @Nullable String str) {
            this.isTrusted = z;
            this.errorMessage = str;
        }

        public static PermissionDeclarationInfo createTrusted() {
            return new PermissionDeclarationInfo(true, null);
        }

        public static PermissionDeclarationInfo createUntrusted(String str) {
            return new PermissionDeclarationInfo(false, str);
        }
    }
}
