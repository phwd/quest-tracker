package com.facebook.secure.fileprovider;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.facebook.annotations.DoNotOptimize;
import com.facebook.common.util.UriUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Nullable;

public class SecureShareableFileSender {
    public static void addShareableFileCopyToIntent(Context context, File file, Intent intent) throws IOException {
        addShareableFileCopyToIntent(context, file, null, intent, false);
    }

    public static void addShareableFileCopyToIntent(Context context, File file, @Nullable String str, Intent intent, boolean z) throws IOException {
        embedTemporaryURIPermissionsInIntent(intent, z, SecureFileProvider.getTempUriForFile(context, file, str, null));
    }

    public static void addShareableFileToIntent(Context context, File file, Intent intent) throws IOException {
        addShareableFileToIntent(context, file, intent, false);
    }

    public static void addShareableFileToIntent(Context context, File file, Intent intent, boolean z) throws IOException {
        embedTemporaryURIPermissionsInIntent(intent, z, SecureFileProvider.getUriForFile(context, file));
    }

    public static Uri addNewTempFileToIntent(Context context, Intent intent, String str, String str2) throws IOException {
        Uri uriForNewShareableFile = SecureFileProvider.getUriForNewShareableFile(context, str, str2, null);
        embedTemporaryURIPermissionsInIntent(intent, true, uriForNewShareableFile);
        return uriForNewShareableFile;
    }

    public static void embedTemporaryURIPermissionsInIntent(Intent intent, boolean z, Uri... uriArr) {
        embedTemporaryURIPermissionsInIntent(intent, z, getDefaultExtraLabelForIntent(intent), uriArr);
    }

    public static void embedTemporaryURIPermissionsInIntent(Intent intent, boolean z, String str, Uri... uriArr) {
        if (uriArr != null) {
            for (Uri uri : uriArr) {
                if (uri != null && UriUtil.LOCAL_FILE_SCHEME.equals(uri.getScheme())) {
                    throw new SecurityException("Attempted to bypass content providers with file:// URI");
                }
            }
            intent.addFlags(1);
            if (z) {
                intent.addFlags(2);
            }
            if (Build.VERSION.SDK_INT >= 16) {
                embedTemporaryURIPermissionsInIntentWithAPIAtLeast16(intent, str, uriArr);
            }
            if (uriArr.length > 1) {
                intent.putParcelableArrayListExtra(str, new ArrayList<>(Arrays.asList(uriArr)));
                return;
            }
            intent.putExtra(str, uriArr[0]);
            if (Build.VERSION.SDK_INT < 16) {
                intent.setData(uriArr[0]);
            }
        }
    }

    private static String getDefaultExtraLabelForIntent(Intent intent) {
        return intentActionRequiresOutputExtra(intent) ? "output" : "android.intent.extra.STREAM";
    }

    public static Uri convertFileUriToContentUri(Context context, Uri uri) throws IOException {
        if (!UriUtil.LOCAL_FILE_SCHEME.equals(uri.getScheme())) {
            return uri;
        }
        return SecureFileProvider.getUriForFile(context, new File(uri.getPath()));
    }

    private static boolean intentActionRequiresOutputExtra(Intent intent) {
        String action = intent.getAction();
        return "android.media.action.IMAGE_CAPTURE".equals(action) || "android.media.action.VIDEO_CAPTURE".equals(action);
    }

    @TargetApi(16)
    @DoNotOptimize
    private static void embedTemporaryURIPermissionsInIntentWithAPIAtLeast16(Intent intent, String str, Uri... uriArr) {
        if (uriArr != null) {
            ClipData clipData = new ClipData(new ClipDescription(str, new String[]{"text/uri-list"}), new ClipData.Item(uriArr[0]));
            for (int i = 1; i < uriArr.length; i++) {
                clipData.addItem(new ClipData.Item(uriArr[i]));
            }
            intent.setClipData(clipData);
        }
    }
}
