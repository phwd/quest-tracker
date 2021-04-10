package com.oculus.vrapi;

import android.content.Context;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Log;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TrustedSignatureVerifier {
    private static final String[] FirstPartySignatures = {"fac61745dc0903786fb9ede62a962b399f7348f0bb6f899b8332667591033b9c", "86c8c709964343f9d803ba4575980b96eec28f9b0ffe579dbbeb82a01c46b110", "dc2f834e3c1942378b14f076c82cd1ab59ea6566bff4730b2047e98efc9f9daf"};
    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
    private static final String OCULUS_ASSISTANT_PACKAGE_NAME = "com.oculus.assistant";
    private static final String OCULUS_HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    private static final String OCULUS_SYSTEMUTILS_PACKAGE_NAME = "com.oculus.systemutilities";
    private static final String OCULUS_SYSTEMUX_PACKAGE_NAME = "com.oculus.systemux";
    private static final String OCULUS_VRSHELL_HOME_PACKAGE_NAME = "com.oculus.vrshell.home";
    private static final String OCULUS_VRSHELL_PACKAGE_NAME = "com.oculus.vrshell";
    private static final String OVRMETRICSERVICE_PACKAGE_NAME = "com.oculus.ovrmonitormetricsservice";
    private static final String TAG = "TrustedSignatureVerifier";
    public static final String[] TrustedSignedApps = {OCULUS_HORIZON_PACKAGE_NAME, "com.oculus.vrshell", "com.oculus.systemux", OCULUS_SYSTEMUTILS_PACKAGE_NAME, OCULUS_VRSHELL_HOME_PACKAGE_NAME, OVRMETRICSERVICE_PACKAGE_NAME, OCULUS_ASSISTANT_PACKAGE_NAME};
    private static Map<String, Map<String, String>> mSignatureMap;

    public static void GenerateSignatureMap() {
        if (mSignatureMap == null) {
            mSignatureMap = new HashMap();
            Map<String, String> OculusHorizonSigs = new HashMap<>();
            OculusHorizonSigs.put("DEBUG_SHA1", "Xo8WBi6jzSxKDVR4drqm84yr9iU");
            OculusHorizonSigs.put("DEBUG_SHA256", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
            OculusHorizonSigs.put("RELEASE_SHA1", "Sr9mhPKOEwo6NysnYn803dZ3UiY");
            OculusHorizonSigs.put("RELEASE_SHA256", "ZSEpCPBvG4p97Oj7FYJ_pqcOLvWpiwfdZ4BcNkPF08M");
            mSignatureMap.put(OCULUS_HORIZON_PACKAGE_NAME, Collections.unmodifiableMap(OculusHorizonSigs));
            Map<String, String> OculusVrShellSigs = new HashMap<>();
            OculusVrShellSigs.put("DEBUG_SHA1", "Xo8WBi6jzSxKDVR4drqm84yr9iU");
            OculusVrShellSigs.put("DEBUG_SHA256", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
            OculusVrShellSigs.put("RELEASE_SHA1", "MxZgtt071YLz39PLrkVGckZooCE");
            OculusVrShellSigs.put("RELEASE_SHA256", "3C-DTjwZQjeLFPB2yCzRq1nqZWa_9HMLIEfpjvyfna8");
            mSignatureMap.put("com.oculus.vrshell", Collections.unmodifiableMap(OculusVrShellSigs));
            Map<String, String> OculusSystemUXSigs = new HashMap<>();
            OculusSystemUXSigs.put("DEBUG_SHA1", "Xo8WBi6jzSxKDVR4drqm84yr9iU");
            OculusSystemUXSigs.put("DEBUG_SHA256", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
            OculusSystemUXSigs.put("RELEASE_SHA1", "mKGAnzw-eRcOXfgTW1AOz6Od8b4");
            OculusSystemUXSigs.put("RELEASE_SHA256", "AS-BZcGR7-j7DW1GBqpKusFNd9HZfVNAhgyfgQRaoVc");
            mSignatureMap.put("com.oculus.systemux", Collections.unmodifiableMap(OculusSystemUXSigs));
            Map<String, String> OculusSystemUtilsSigs = new HashMap<>();
            OculusSystemUtilsSigs.put("DEBUG_SHA1", "Xo8WBi6jzSxKDVR4drqm84yr9iU");
            OculusSystemUtilsSigs.put("DEBUG_SHA256", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
            OculusSystemUtilsSigs.put("RELEASE_SHA1", "MxZgtt071YLz39PLrkVGckZooCE");
            OculusSystemUtilsSigs.put("RELEASE_SHA256", "3C-DTjwZQjeLFPB2yCzRq1nqZWa_9HMLIEfpjvyfna8");
            mSignatureMap.put(OCULUS_SYSTEMUTILS_PACKAGE_NAME, Collections.unmodifiableMap(OculusSystemUtilsSigs));
            Map<String, String> OculusVrShellHomeSigs = new HashMap<>();
            OculusVrShellHomeSigs.put("DEBUG_SHA1", "Xo8WBi6jzSxKDVR4drqm84yr9iU");
            OculusVrShellHomeSigs.put("DEBUG_SHA256", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
            OculusVrShellHomeSigs.put("RELEASE_SHA1", "MxZgtt071YLz39PLrkVGckZooCE");
            OculusVrShellHomeSigs.put("RELEASE_SHA256", "3C-DTjwZQjeLFPB2yCzRq1nqZWa_9HMLIEfpjvyfna8");
            mSignatureMap.put(OCULUS_VRSHELL_HOME_PACKAGE_NAME, Collections.unmodifiableMap(OculusVrShellHomeSigs));
            Map<String, String> OVRMetricsServiceSigs = new HashMap<>();
            OVRMetricsServiceSigs.put("DEBUG_SHA1", "Xo8WBi6jzSxKDVR4drqm84yr9iU");
            OVRMetricsServiceSigs.put("DEBUG_SHA256", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
            OVRMetricsServiceSigs.put("RELEASE_SHA1", "WVCNemkgnZBWaGouh-tq4VQRepg");
            OVRMetricsServiceSigs.put("RELEASE_SHA256", "rbPBrH2K54ycBnaWuvI8pTxd7CIb-amyVDLrqB97NBo");
            mSignatureMap.put(OVRMETRICSERVICE_PACKAGE_NAME, Collections.unmodifiableMap(OVRMetricsServiceSigs));
            Map<String, String> OculusAssistantSigs = new HashMap<>();
            OculusAssistantSigs.put("DEBUG_SHA1", "Xo8WBi6jzSxKDVR4drqm84yr9iU");
            OculusAssistantSigs.put("DEBUG_SHA256", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
            OculusAssistantSigs.put("RELEASE_SHA1", "eBLxbTAHR6nuEIur96W48dDc7Io");
            OculusAssistantSigs.put("RELEASE_SHA256", "rO1i0-x-Hhu8_RdBiQjfrXe1WnMMVVkuOoFfs8ri2eE");
            mSignatureMap.put(OCULUS_ASSISTANT_PACKAGE_NAME, Collections.unmodifiableMap(OculusAssistantSigs));
            mSignatureMap = Collections.unmodifiableMap(mSignatureMap);
        }
    }

    public static boolean isSignatureValid(Context context, String callerPackageName) {
        Signature callerSignature = AppVerifier.getRawSignature(context, callerPackageName);
        try {
            Log.d(TAG, "Checking app signature for " + callerPackageName);
            String sha1 = HashHelper.getBase64Hash(callerSignature.toByteArray(), HashHelper.SHA1);
            String sha256 = HashHelper.getBase64Hash(callerSignature.toByteArray(), "SHA-256");
            Map<String, String> packageSignatures = mSignatureMap.get(callerPackageName);
            if (packageSignatures != null) {
                if (!Build.TYPE.equals("user") && sha1.equals(packageSignatures.get("DEBUG_SHA1")) && sha256.equals(packageSignatures.get("DEBUG_SHA256"))) {
                    return true;
                }
                if (!sha1.equals(packageSignatures.get("RELEASE_SHA1")) || !sha256.equals(packageSignatures.get("RELEASE_SHA256"))) {
                    return false;
                }
                return true;
            }
            return false;
        } catch (NoSuchAlgorithmException e) {
            Log.w(TAG, "Couldn't verify app signature");
            return false;
        } catch (NullPointerException e2) {
            Log.w(TAG, "Null pointer exception");
            return false;
        }
    }

    public static String bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[(bytes.length * 2)];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            char[] cArr = HEX_ARRAY;
            hexChars[j * 2] = cArr[v >>> 4];
            hexChars[(j * 2) + 1] = cArr[v & 15];
        }
        return new String(hexChars);
    }

    public static boolean isFirstParty(Context context) {
        String pkgName = context.getPackageName();
        try {
            String sha256 = bytesToHexString(HashHelper.getHash(AppVerifier.getRawSignature(context, pkgName).toByteArray(), "SHA-256"));
            for (int i = 0; i < FirstPartySignatures.length; i++) {
                if (sha256.equals(FirstPartySignatures[i])) {
                    Log.d(TAG, "isFirstParty() passed for " + pkgName);
                    return true;
                }
            }
            Log.d(TAG, "isFirstParty() failed for " + pkgName);
            return false;
        } catch (NoSuchAlgorithmException e) {
            Log.w(TAG, "Couldn't verify app signature");
            return false;
        } catch (NullPointerException e2) {
            Log.w(TAG, "Null pointer exception");
            return false;
        }
    }
}
