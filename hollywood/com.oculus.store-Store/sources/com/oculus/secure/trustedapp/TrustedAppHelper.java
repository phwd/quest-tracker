package com.oculus.secure.trustedapp;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrustedAppHelper {
    public static TrustedApp createFacebookFamilyTrustedApp() {
        return new TrustedApp(createTrustedPackages(AllFamilyTrustedSignatures.FB_FAMILY_PROD, AllFamilyPackageNames.ALL_FB_PACKAGE_NAMES));
    }

    public static TrustedApp createAllFacebookTrustedApp() {
        return new TrustedApp(createTrustedPackages(AllFamilyTrustedSignatures.FB_PROD, AllFamilyPackageNames.ALL_FB_PACKAGE_NAMES));
    }

    public static TrustedApp createAllFBFamilyTrustedApp() {
        return new TrustedApp(createTrustedPackages(AllFamilyTrustedSignatures.ALL_FAMILY_PROD, AllFamilyPackageNames.ALL_FAMILY_PACKAGE_NAMES));
    }

    public static TrustedApp createAllFBInHouseTrustedApp() {
        return new TrustedApp(createTrustedPackages(AllFamilyTrustedSignatures.ALL_INHOUSE, AllFamilyPackageNames.ALL_INHOUSE_PACKAGE_NAMES));
    }

    public static TrustedApp createTrustedApp(Set<AppSignatureHash> trustedSignatures) {
        return new TrustedApp(createTrustedPackagesWithAnyPackage(trustedSignatures));
    }

    public static TrustedApp createTrustedApp(Set<AppSignatureHash> trustedSignatures, Set<String> trustedPackageNames) {
        return new TrustedApp(createTrustedPackages(trustedSignatures, trustedPackageNames));
    }

    private static Map<AppSignatureHash, Set<String>> createTrustedPackagesWithAnyPackage(Set<AppSignatureHash> trustedSignatures) {
        Map<AppSignatureHash, Set<String>> mutableTrustedPackages = new HashMap<>();
        for (AppSignatureHash trustedSignature : trustedSignatures) {
            mutableTrustedPackages.put(trustedSignature, Collections.unmodifiableSet(new HashSet(Collections.singletonList("*|all_packages|*"))));
        }
        return Collections.unmodifiableMap(mutableTrustedPackages);
    }

    private static Map<AppSignatureHash, Set<String>> createTrustedPackages(Set<AppSignatureHash> trustedSignatures, Set<String> trustedPackageNames) {
        Map<AppSignatureHash, Set<String>> mutableTrustedPackages = new HashMap<>();
        for (AppSignatureHash trustedSignature : trustedSignatures) {
            mutableTrustedPackages.put(trustedSignature, Collections.unmodifiableSet(trustedPackageNames));
        }
        return Collections.unmodifiableMap(mutableTrustedPackages);
    }
}
