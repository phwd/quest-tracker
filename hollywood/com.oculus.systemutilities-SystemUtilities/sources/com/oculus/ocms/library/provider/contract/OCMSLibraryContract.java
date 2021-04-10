package com.oculus.ocms.library.provider.contract;

import android.content.pm.Signature;
import android.net.Uri;
import android.text.TextUtils;
import com.oculus.vrshell.panel.TrustedCertificates;
import java.util.List;

public class OCMSLibraryContract {
    public static final Signature OCULUS_SIGNATURE = new Signature(TrustedCertificates.OCULUS_PROD_CERTIFICATE);

    public static Uri providerUri() {
        return new Uri.Builder().scheme("content").authority("com.oculus.ocms.library").build();
    }

    public static Uri uriForAllPackages() {
        return new Uri.Builder().scheme("content").authority("com.oculus.ocms.library").path("apps").build();
    }

    public static Uri uriForPackage(String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            return new Uri.Builder().scheme("content").authority("com.oculus.ocms.library").path("apps").appendPath(packageName).build();
        }
        throw new IllegalArgumentException("packageName cannot be null/empty");
    }

    public static String getPackageFromAppsUri(Uri uri) {
        return getUriPathSegment(uri, 1);
    }

    public static String getUriPathSegment(Uri uri, int segment) {
        if (uri == null || segment < 0) {
            throw new IllegalArgumentException("invalid args");
        }
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments == null || pathSegments.size() <= segment) {
            return null;
        }
        return pathSegments.get(segment);
    }
}
