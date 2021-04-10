package com.facebook.secure.trustedapp;

import android.content.Context;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppIdentityUtil {
    protected static AppIdentity getAppIdentityFromUid(Context context, int i) {
        List unmodifiableList = Collections.unmodifiableList(Arrays.asList(AppVerifier.getPackageNamesFromUid(context, i)));
        return new AppIdentity(i, unmodifiableList, AppVerifier.getSignatureHashFromPackageNames(context, (String[]) unmodifiableList.toArray(new String[0])), (String) null, (String) null);
    }

    protected static AppIdentity getAppIdentityFromPackageName(Context context, String str) {
        int uidFromPackageName = AppVerifier.getUidFromPackageName(context, str);
        List unmodifiableList = Collections.unmodifiableList(Arrays.asList(str));
        return new AppIdentity(uidFromPackageName, unmodifiableList, AppVerifier.getSignatureHashFromPackageNames(context, (String[]) unmodifiableList.toArray(new String[0])), (String) null, (String) null);
    }
}
