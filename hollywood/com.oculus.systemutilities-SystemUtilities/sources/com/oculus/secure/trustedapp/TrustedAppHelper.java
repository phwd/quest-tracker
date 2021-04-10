package com.oculus.secure.trustedapp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TrustedAppHelper {
    public static TrustedApp createTrustedApp(Set<AppSignatureHash> trustedSignatures, Set<String> trustedPackageNames) {
        return new TrustedApp(createTrustedPackages(trustedSignatures, trustedPackageNames));
    }

    private static Map<AppSignatureHash, Set<String>> createTrustedPackages(Set<AppSignatureHash> trustedSignatures, Set<String> trustedPackageNames) {
        Map<AppSignatureHash, Set<String>> mutableTrustedPackages = new HashMap<>();
        for (AppSignatureHash trustedSignature : trustedSignatures) {
            mutableTrustedPackages.put(trustedSignature, Collections.unmodifiableSet(trustedPackageNames));
        }
        return Collections.unmodifiableMap(mutableTrustedPackages);
    }
}
