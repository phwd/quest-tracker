package com.android.okhttp;

import com.android.okhttp.internal.URLFilter;

public class OkUrlFactories {
    public static void setUrlFilter(OkUrlFactory okUrlFactory, URLFilter uRLFilter) {
        okUrlFactory.setUrlFilter(uRLFilter);
    }
}
