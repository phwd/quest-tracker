package com.facebook.mobileconfig;

import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Map;

@DoNotStrip
public interface MobileConfigFetcher {
    @DoNotStrip
    void fetch(String str, String str2, Map<String, String> map, MobileConfigFetcherHandler mobileConfigFetcherHandler, String str3);

    @DoNotStrip
    int getApiVersion();

    @DoNotStrip
    void setExtraURLRequestParams(Map<String, String> map);
}
