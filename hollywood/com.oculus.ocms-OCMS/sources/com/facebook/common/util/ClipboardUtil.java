package com.facebook.common.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.FileUriExposedException;
import com.facebook.common.string.StringUtil;
import com.facebook.debug.log.BLog;
import javax.annotation.Nullable;

public class ClipboardUtil {
    private static final String BLOG_TAG = "ClipboardUtil";

    public static void copyToClipboard(Context context, String str) {
        copyToClipboard(context, str, null);
    }

    public static void copyToClipboard(Context context, String str, @Nullable String str2) {
        try {
            if (StringUtil.isEmptyOrNull(str2)) {
                str2 = getAppLabel(context);
            }
            ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(str2, str));
        } catch (SecurityException e) {
            if (Build.VERSION.SDK_INT == 29) {
                BLog.e(BLOG_TAG, "Failed to copy to clipboard", e);
                return;
            }
            throw e;
        }
    }

    public static String getAppLabel(Context context) {
        CharSequence charSequence;
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        if (packageManager != null) {
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        if (applicationInfo != null) {
            charSequence = packageManager.getApplicationLabel(applicationInfo);
        } else {
            charSequence = context.getString(17039375);
        }
        return String.valueOf(charSequence);
    }

    @Nullable
    public static ClipData coerceClipboardToText(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
        if (!clipboardManager.hasPrimaryClip()) {
            return null;
        }
        ClipData primaryClip = clipboardManager.getPrimaryClip();
        clipboardManager.setPrimaryClip(ClipData.newPlainText(null, primaryClip.getItemAt(0).coerceToText(context).toString()));
        return primaryClip;
    }

    public static void restoreClipboard(Context context, @Nullable ClipData clipData) {
        if (clipData != null) {
            try {
                ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(clipData);
            } catch (SecurityException e) {
                BLog.e(BLOG_TAG, "Failed to restore clipboard", e);
            } catch (Exception e2) {
                if (Build.VERSION.SDK_INT < 24 || !(e2 instanceof FileUriExposedException)) {
                    throw e2;
                }
                BLog.e(BLOG_TAG, "Failed to restore clipboard with data ", clipData, e2);
            }
        }
    }
}
