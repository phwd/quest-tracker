package com.facebook.secure.trustedapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public class AppIdentityUtil {
    AppIdentityUtil() {
    }

    @Nullable
    public static AppIdentity getAppIdentityFromProviderAuthority(Context context, String str) {
        ProviderInfo resolveContentProvider;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || (resolveContentProvider = packageManager.resolveContentProvider(str, 0)) == null) {
            return null;
        }
        return getAppIdentityFromPackageName(context, resolveContentProvider.packageName);
    }

    public static AppIdentity getAppIdentityFromUid(Context context, int i) {
        List unmodifiableList = Collections.unmodifiableList(Arrays.asList(AppVerifier.getPackageNamesFromUid(context, i)));
        return new AppIdentity(i, unmodifiableList, AppVerifier.getSignatureHashFromPackageNames(context, (String[]) unmodifiableList.toArray(new String[0])), (String) null, (String) null);
    }

    public static AppIdentity getAppIdentityFromPackageName(Context context, String str) {
        int uidFromPackageName = AppVerifier.getUidFromPackageName(context, str);
        List unmodifiableList = Collections.unmodifiableList(Arrays.asList(str));
        return new AppIdentity(uidFromPackageName, unmodifiableList, AppVerifier.getSignatureHashFromPackageNames(context, (String[]) unmodifiableList.toArray(new String[0])), (String) null, (String) null);
    }
}
