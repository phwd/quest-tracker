package com.oculus.panellib;

import android.content.pm.Signature;
import com.oculus.common.build.BuildConfig;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VerifierConstants {
    public static Map<Signature, Set<String>> getFirstPartyOculusStorePackages(boolean trustAppsBranchDev) {
        Set<String> oculusPkgs = new HashSet<>();
        oculusPkgs.add("com.oculus.browser");
        oculusPkgs.add(BuildConfig.PACKAGE_NAME_HORIZON);
        oculusPkgs.add("com.oculus.systemutilities");
        oculusPkgs.add("com.oculus.systemux");
        oculusPkgs.add("com.oculus.venues");
        oculusPkgs.add("com.oculus.vrshell");
        oculusPkgs.add("com.oculus.vrshell.home");
        Set<String> oculusPkgs2 = Collections.unmodifiableSet(oculusPkgs);
        Set<String> oculusTvPkgs = new HashSet<>();
        oculusTvPkgs.add("com.oculus.tv");
        Set<String> oculusTvPkgs2 = Collections.unmodifiableSet(oculusTvPkgs);
        Set<String> aospPkgs = new HashSet<>();
        aospPkgs.add("com.oculus.os.vrmtpapp");
        Set<String> aospPkgs2 = Collections.unmodifiableSet(aospPkgs);
        Set<String> allPkgs = new HashSet<>();
        allPkgs.addAll(oculusPkgs2);
        allPkgs.addAll(oculusTvPkgs2);
        allPkgs.addAll(aospPkgs2);
        Set<String> allPkgs2 = Collections.unmodifiableSet(allPkgs);
        Map<Signature, Set<String>> m = new HashMap<>();
        m.put(TrustedCertificates.OCULUS_PROD_SIGNATURE, oculusPkgs2);
        m.put(TrustedCertificates.OCULUS_APPS_SIGNATURE, oculusPkgs2);
        m.put(TrustedCertificates.OCULUS_CORE_SIGNATURE, oculusPkgs2);
        m.put(TrustedCertificates.OCULUS_TV_SIGNATURE, oculusTvPkgs2);
        m.put(TrustedCertificates.OCULUS_OS_PLATFORM_SIGNATURE, aospPkgs2);
        if (trustAppsBranchDev) {
            m.put(TrustedCertificates.OCULUS_APPS_BRANCH_DEV_SIGNATURE, oculusPkgs2);
            m.put(TrustedCertificates.FBANDROID_DEV_SIGNATURE, allPkgs2);
        }
        return Collections.unmodifiableMap(m);
    }

    public static Set<Signature> getFirstPartyOculusStoreAssetBundles() {
        Set<Signature> signatures = new HashSet<>();
        signatures.add(TrustedCertificates.OCULUS_BUNDLES_SIGNATURE);
        return Collections.unmodifiableSet(signatures);
    }
}
