package com.oculus.http.headers;

import android.os.Build;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public class RequestHeaders {
    private static final String BUILD_VERSION_REQUEST_HEADER_NAME = "X-Build-Version-Incremental";
    private static final String BUILD_VERSION_REQUEST_HEADER_VALUE = Build.VERSION.INCREMENTAL;
    public static final Map<String, String> DEFAULT_REQUEST_HEADERS = ImmutableMap.of(BUILD_VERSION_REQUEST_HEADER_NAME, BUILD_VERSION_REQUEST_HEADER_VALUE);
}
