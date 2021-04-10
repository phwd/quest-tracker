package com.oculus.secure.trustedapp;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrustedAppHelper {
    public static TrustedApp createAllFBFamilyTrustedApp() {
        return new TrustedApp(createTrustedPackages(AllFamilyTrustedSignatures.ALL_FAMILY_PROD, AllFamilyPackageNames.ALL_FAMILY_PACKAGE_NAMES));
    }

    public static TrustedApp createAllFBInHouseTrustedApp() {
        return new TrustedApp(createTrustedPackages(AllFamilyTrustedSignatures.ALL_INHOUSE, AllFamilyPackageNames.ALL_INHOUSE_PACKAGE_NAMES));
    }

    public static TrustedApp createAllFacebookTrustedApp() {
        return new TrustedApp(createTrustedPackages(AllFamilyTrustedSignatures.FB_PROD, AllFamilyPackageNames.ALL_FB_PACKAGE_NAMES));
    }

    public static TrustedApp createFacebookFamilyTrustedApp() {
        return new TrustedApp(createTrustedPackages(AllFamilyTrustedSignatures.FB_FAMILY_PROD, AllFamilyPackageNames.ALL_FB_PACKAGE_NAMES));
    }

    public static Map<AppSignatureHash, Set<String>> createTrustedPackages(Set<AppSignatureHash> set, Set<String> set2) {
        HashMap hashMap = new HashMap();
        for (AppSignatureHash appSignatureHash : set) {
            hashMap.put(appSignatureHash, Collections.unmodifiableSet(set2));
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public static Map<AppSignatureHash, Set<String>> createTrustedPackagesWithAnyPackage(Set<AppSignatureHash> set) {
        HashMap hashMap = new HashMap();
        for (AppSignatureHash appSignatureHash : set) {
            hashMap.put(appSignatureHash, Collections.unmodifiableSet(new HashSet(Collections.singletonList("*|all_packages|*"))));
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public static TrustedApp createTrustedApp(Set<AppSignatureHash> set) {
        return new TrustedApp(createTrustedPackagesWithAnyPackage(set));
    }

    public static TrustedApp createTrustedApp(Set<AppSignatureHash> set, Set<String> set2) {
        return new TrustedApp(createTrustedPackages(set, set2));
    }
}
