package com.android.okhttp.internal;

import java.net.URL;

public interface URLFilter {
    void checkURLPermitted(URL url);
}
