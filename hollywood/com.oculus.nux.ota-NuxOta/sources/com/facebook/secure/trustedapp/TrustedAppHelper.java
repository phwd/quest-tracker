package com.facebook.secure.trustedapp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TrustedAppHelper {
    public static TrustedApp createTrustedApp(Set<AppSignatureHash> set, Set<String> set2) {
        return new TrustedApp(createTrustedPackages(set, set2));
    }

    private static Map<AppSignatureHash, Set<String>> createTrustedPackages(Set<AppSignatureHash> set, Set<String> set2) {
        HashMap hashMap = new HashMap();
        for (AppSignatureHash appSignatureHash : set) {
            hashMap.put(appSignatureHash, Collections.unmodifiableSet(set2));
        }
        return Collections.unmodifiableMap(hashMap);
    }
}
