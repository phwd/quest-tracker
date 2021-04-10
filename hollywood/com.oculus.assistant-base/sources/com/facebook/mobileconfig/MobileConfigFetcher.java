package com.facebook.mobileconfig;

import java.util.Map;

public interface MobileConfigFetcher {
    void fetch(String str, String str2, Map map, MobileConfigFetcherHandler mobileConfigFetcherHandler, String str3);

    int getApiVersion();

    void setExtraURLRequestParams(Map map);
}
