package com.oculus.ocms.library.provider.contract;

import android.net.Uri;
import android.text.TextUtils;
import java.util.List;

public class OCMSPublicLibraryContract {
    public static final String APPS_PATH = "apps";
    public static final String AUTHORITY = "com.oculus.ocms.publiclibrary";
    public static final String MATCHER_PATH_APP = "apps/*";
    public static final String MATCHER_PATH_APPS = "apps";
    public static final String PACKAGE_NAME = "com.oculus.ocms";

    public static Uri providerUri() {
        return new Uri.Builder().scheme("content").authority(AUTHORITY).build();
    }

    public static Uri uriForAllPackages() {
        return new Uri.Builder().scheme("content").authority(AUTHORITY).path("apps").build();
    }

    public static Uri uriForPackage(String str) {
        if (!TextUtils.isEmpty(str)) {
            return new Uri.Builder().scheme("content").authority(AUTHORITY).path("apps").appendPath(str).build();
        }
        throw new IllegalArgumentException("packageName cannot be null/empty");
    }

    public static String getPackageFromAppsUri(Uri uri) {
        return getUriPathSegment(uri, 1);
    }

    public static String getUriPathSegment(Uri uri, int i) {
        if (uri == null || i < 0) {
            throw new IllegalArgumentException("invalid args");
        }
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments == null || pathSegments.size() <= i) {
            return null;
        }
        return pathSegments.get(i);
    }
}
