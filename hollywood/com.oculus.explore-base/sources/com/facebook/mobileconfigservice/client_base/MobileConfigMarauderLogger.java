package com.facebook.mobileconfigservice.client_base;

import java.util.Map;

public interface MobileConfigMarauderLogger {
    void logEvent(String str, Map<String, String> map);
}
