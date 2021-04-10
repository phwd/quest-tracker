package com.oculus.ocms.library.provider.contract;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.secure.context.SecureContext;
import com.facebook.secure.uriparser.SecureUriParser;
import com.oculus.common.build.BuildConstants;
import com.oculus.common.vrshell.Constants;
import com.oculus.common.vrshell.SystemUXRoute;
import java.util.List;

public class TrustedBinaryContract {
    public static final String ACTION_BLOCK = "BLOCK";
    public static final String ACTION_NONE = "NONE";
    public static final String ACTION_WARN = "WARN";
    public static final String AUTHORITY = "com.oculus.ocms.trustedbinaryaction";
    public static final String CURSOR_ACTION_COLUMN = "action";
    private static final boolean DEBUG = false;
    public static final String PACKAGE_NAME = "com.oculus.ocms";
    public static final String STATUS_EMPTY = "";
    public static final String STATUS_TRUSTED = "TRUSTED";
    public static final String STATUS_UNTRUSTED = "UNTRUSTED";
    private static final String TAG = "TrustedBinaryContract";

    @Nullable
    public static String getPackageNameFromUri(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments == null || pathSegments.isEmpty()) {
            return null;
        }
        return pathSegments.get(0);
    }

    private static Uri buildDialogUri(String str, String str2, @Nullable List<String> list) {
        Uri parseStrict = SecureUriParser.parseStrict(SystemUXRoute.UNOFFICIAL_APP_LAUNCHED_DIALOG.path() + "?package_name=" + str + "&action=" + str2);
        return (list == null || list.isEmpty()) ? parseStrict : parseStrict.buildUpon().appendQueryParameter("suspended_users", TextUtils.join(",", list)).build();
    }

    public static void showUnofficialAppDialog(Context context, String str, String str2, @Nullable List<String> list) {
        Intent intent = new Intent(Constants.ACTION_LAUNCH);
        intent.setData(buildDialogUri(str, str2, list)).setComponent(new ComponentName("com.oculus.vrshell", BuildConstants.ACTIVITY_NAME_SHELL)).setFlags(268435456);
        SecureContext.launchFamilyActivity(intent, context);
    }
}
