package com.oculus.vrshell.panel;

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

    public static Map<Signature, Set<String>> getFirstPartyOculusStorePackages(boolean trustAppsBranchDev) {
        Set<String> oculusPkgs = new HashSet<>();
        oculusPkgs.add("com.oculus.browser");
        oculusPkgs.add("com.oculus.horizon");
        oculusPkgs.add(OCULUS_SYSTEM_UTILITIES_PACKAGE_NAME);
        oculusPkgs.add("com.oculus.systemux");
        oculusPkgs.add(OCULUS_VENUES_PACKAGE_NAME);
        oculusPkgs.add("com.oculus.vrshell");
        oculusPkgs.add("com.oculus.vrshell.home");
        Set<String> oculusPkgs2 = Collections.unmodifiableSet(oculusPkgs);
        Set<String> oculusTvPkgs = new HashSet<>();
        oculusTvPkgs.add(OCULUS_TV_PACKAGE_NAME);
        Set<String> oculusTvPkgs2 = Collections.unmodifiableSet(oculusTvPkgs);
        Set<String> aospPkgs = new HashSet<>();
        aospPkgs.add(OCULUS_MTPAPP_PACKAGE_NAME);
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
