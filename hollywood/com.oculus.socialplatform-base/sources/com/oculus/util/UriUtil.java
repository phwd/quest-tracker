package com.oculus.util;

import android.net.Uri;

public class UriUtil {
    public static Uri appendKeyValue(Uri uri, String str, String str2) {
        return uri.buildUpon().appendQueryParameter(str, str2).build();
    }

    public static Uri appendKeyValue(String str, String str2, String str3) {
        return appendKeyValue(Uri.parse(str), str2, str3);
    }
}
