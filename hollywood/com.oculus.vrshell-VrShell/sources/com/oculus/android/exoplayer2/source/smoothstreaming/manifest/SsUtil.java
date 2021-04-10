package com.oculus.android.exoplayer2.source.smoothstreaming.manifest;

import android.net.Uri;
import com.oculus.android.exoplayer2.util.Util;

public final class SsUtil {
    public static Uri fixManifestUri(Uri uri) {
        if (Util.toLowerInvariant(uri.getLastPathSegment()).matches("manifest(\\(.+\\))?")) {
            return uri;
        }
        return Uri.withAppendedPath(uri, "Manifest");
    }

    private SsUtil() {
    }
}
