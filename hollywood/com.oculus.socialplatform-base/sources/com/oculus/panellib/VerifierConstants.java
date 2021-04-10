package com.oculus.panellib;

import android.content.pm.Signature;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VerifierConstants {
    public static final String OCULUS_BROWSER_PACKAGE_NAME = "com.oculus.browser";
    public static final String OCULUS_HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    public static final String OCULUS_MTPAPP_PACKAGE_NAME = "com.oculus.os.vrmtpapp";
    public static final String OCULUS_SYSTEM_UTILITIES_PACKAGE_NAME = "com.oculus.systemutilities";
    public static final String OCULUS_SYSTEM_UX_PACKAGE_NAME = "com.oculus.systemux";
    public static final String OCULUS_TV_PACKAGE_NAME = "com.oculus.tv";
    public static final String OCULUS_VENUES_PACKAGE_NAME = "com.oculus.venues";
    public static final String OCULUS_VRSHELL_HOME_PACKAGE_NAME = "com.oculus.vrshell.home";
    public static final String OCULUS_VRSHELL_PACKAGE_NAME = "com.oculus.vrshell";

    public static Set<Signature> getFirstPartyOculusStoreAssetBundles() {
        HashSet hashSet = new HashSet();
        hashSet.add(TrustedCertificates.OCULUS_BUNDLES_SIGNATURE);
        return Collections.unmodifiableSet(hashSet);
    }

    public static Map<Signature, Set<String>> getFirstPartyOculusStorePackages(boolean z) {
        HashSet hashSet = new HashSet();
        hashSet.add("com.oculus.browser");
        hashSet.add("com.oculus.horizon");
        hashSet.add("com.oculus.systemutilities");
        hashSet.add("com.oculus.systemux");
        hashSet.add("com.oculus.venues");
        hashSet.add("com.oculus.vrshell");
        hashSet.add("com.oculus.vrshell.home");
        Set unmodifiableSet = Collections.unmodifiableSet(hashSet);
        HashSet hashSet2 = new HashSet();
        hashSet2.add("com.oculus.tv");
        Set unmodifiableSet2 = Collections.unmodifiableSet(hashSet2);
        HashSet hashSet3 = new HashSet();
        hashSet3.add(OCULUS_MTPAPP_PACKAGE_NAME);
        Set unmodifiableSet3 = Collections.unmodifiableSet(hashSet3);
        HashSet hashSet4 = new HashSet();
        hashSet4.addAll(unmodifiableSet);
        hashSet4.addAll(unmodifiableSet2);
        hashSet4.addAll(unmodifiableSet3);
        Set unmodifiableSet4 = Collections.unmodifiableSet(hashSet4);
        HashMap hashMap = new HashMap();
        hashMap.put(TrustedCertificates.OCULUS_PROD_SIGNATURE, unmodifiableSet);
        hashMap.put(TrustedCertificates.OCULUS_APPS_SIGNATURE, unmodifiableSet);
        hashMap.put(TrustedCertificates.OCULUS_CORE_SIGNATURE, unmodifiableSet);
        hashMap.put(TrustedCertificates.OCULUS_TV_SIGNATURE, unmodifiableSet2);
        hashMap.put(TrustedCertificates.OCULUS_OS_PLATFORM_SIGNATURE, unmodifiableSet3);
        if (z) {
            hashMap.put(TrustedCertificates.OCULUS_APPS_BRANCH_DEV_SIGNATURE, unmodifiableSet);
            hashMap.put(TrustedCertificates.FBANDROID_DEV_SIGNATURE, unmodifiableSet4);
        }
        return Collections.unmodifiableMap(hashMap);
    }
}
