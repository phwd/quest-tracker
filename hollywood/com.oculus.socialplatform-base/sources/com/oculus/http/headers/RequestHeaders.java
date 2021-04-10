package com.oculus.http.headers;

import android.os.Build;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public class RequestHeaders {
    public static final String BUILD_VERSION_REQUEST_HEADER_NAME = "X-Build-Version-Incremental";
    public static final String BUILD_VERSION_REQUEST_HEADER_VALUE;
    public static final Map<String, String> DEFAULT_REQUEST_HEADERS;

    static {
        String str = Build.VERSION.INCREMENTAL;
        BUILD_VERSION_REQUEST_HEADER_VALUE = str;
        DEFAULT_REQUEST_HEADERS = ImmutableMap.A05(BUILD_VERSION_REQUEST_HEADER_NAME, str);
    }
}
