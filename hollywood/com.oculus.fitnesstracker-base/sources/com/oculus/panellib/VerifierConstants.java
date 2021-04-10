package com.oculus.panellib;

import android.content.pm.Signature;
import com.oculus.common.build.BuildConfig;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VerifierConstants {
    public static Map<Signature, Set<String>> getFirstPartyOculusStorePackages(boolean z) {
        HashSet hashSet = new HashSet();
        hashSet.add("com.oculus.browser");
        hashSet.add(BuildConfig.PACKAGE_NAME_HORIZON);
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
        hashSet3.add("com.oculus.os.vrmtpapp");
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

    public static Set<Signature> getFirstPartyOculusStoreAssetBundles() {
        HashSet hashSet = new HashSet();
        hashSet.add(TrustedCertificates.OCULUS_BUNDLES_SIGNATURE);
        return Collections.unmodifiableSet(hashSet);
    }
}
