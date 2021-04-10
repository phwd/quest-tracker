package com.oculus.mobileconfig.init;

import com.google.common.collect.ImmutableMap;
import java.util.Locale;

public class MobileConfigManagerUtil {
    static ImmutableMap<String, String> createExtraURLParams(Locale locale) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        builder.put("locale", locale.toString());
        return builder.build();
    }
}
