package com.facebook.mobileconfig;

import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Map;

@DoNotStrip
public interface MobileConfigCxxLogger {
    @DoNotStrip
    void logCounter(String str, int i);

    @DoNotStrip
    void logEvent(String str, Map<String, String> map);

    @DoNotStrip
    void logEventImmediately(String str, Map<String, String> map);
}
