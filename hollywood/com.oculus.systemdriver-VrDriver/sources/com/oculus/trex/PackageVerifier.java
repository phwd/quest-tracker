package com.oculus.trex;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Log;
import com.oculus.vrapi.HashHelper;
import com.oculus.vrapi.SystemProps;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PackageVerifier {
    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
    private static final String KEY_DB_SHA1 = "DEBUG_SHA1";
    private static final String KEY_DB_SHA256 = "DEBUG_SHA256";
    private static final String KEY_RL_SHA1 = "RELEASE_SHA1";
    private static final String KEY_RL_SHA256 = "RELEASE_SHA256";
    private static final String TAG = "TREX";
    private static Map<String, Map<String, String>> signatureMap_;

    private static boolean isUserDevBuild() {
        String propValue = SystemProps.getString("ro.build.fingerprint", null);
        if (propValue == null) {
            return false;
        }
        return !propValue.endsWith("release-keys");
    }

    public static String BytesToHexString(byte[] bytes) {
        char[] hexChars = new char[(bytes.length * 2)];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            char[] cArr = HEX_ARRAY;
            hexChars[j * 2] = cArr[v >>> 4];
            hexChars[(j * 2) + 1] = cArr[v & 15];
        }
        return new String(hexChars);
    }

    private static void addAppSignatures(Map<String, Map<String, String>> sigMap, String packageName, String debugSHA1, String debugSHA256, String releaseSHA1, String releaseSHA256) {
        Map<String, String> map = new HashMap<>();
        map.put(KEY_DB_SHA1, debugSHA1.toLowerCase());
        map.put(KEY_DB_SHA256, debugSHA256.toLowerCase());
        map.put(KEY_RL_SHA1, releaseSHA1.toLowerCase());
        map.put(KEY_RL_SHA256, releaseSHA256.toLowerCase());
        sigMap.put(packageName, Collections.unmodifiableMap(map));
    }

    private static void generateSignatureMap() {
        if (signatureMap_ == null) {
            signatureMap_ = new HashMap();
            addAppSignatures(signatureMap_, "com.oculus.bodyapiservice", "5e8f16062ea3cd2c4a0d547876baa6f38cabf625", "fac61745dc0903786fb9ede62a962b399f7348f0bb6f899b8332667591033b9c", "21aff94a6b0c3e5d6282b5d4b787a23c6179f786", "86c8c709964343f9d803ba4575980b96eec28f9b0ffe579dbbeb82a01c46b110");
            addAppSignatures(signatureMap_, "com.oculus.fitnesstracker", "5e8f16062ea3cd2c4a0d547876baa6f38cabf625", "fac61745dc0903786fb9ede62a962b399f7348f0bb6f899b8332667591033b9c", "21aff94a6b0c3e5d6282b5d4b787a23c6179f786", "86c8c709964343f9d803ba4575980b96eec28f9b0ffe579dbbeb82a01c46b110");
            addAppSignatures(signatureMap_, "com.facebook.spatial_persistence_service", "5e8f16062ea3cd2c4a0d547876baa6f38cabf625", "fac61745dc0903786fb9ede62a962b399f7348f0bb6f899b8332667591033b9c", "21aff94a6b0c3e5d6282b5d4b787a23c6179f786", "86c8c709964343f9d803ba4575980b96eec28f9b0ffe579dbbeb82a01c46b110");
            signatureMap_ = Collections.unmodifiableMap(signatureMap_);
        }
    }

    private static Signature getPackageSignature(PackageInfo packageInfo) {
        if (packageInfo.signatures == null || packageInfo.signatures.length == 0) {
            throw new RuntimeException(packageInfo.packageName);
        } else if (packageInfo.signatures.length > 1) {
            throw new RuntimeException(packageInfo.packageName);
        } else if (packageInfo.signatures[0] != null) {
            return packageInfo.signatures[0];
        } else {
            throw new RuntimeException(packageInfo.packageName);
        }
    }

    private static boolean isTrustedSignature(Context ctx, String packageName, Signature sig) {
        try {
            String sha1 = BytesToHexString(HashHelper.getHash(sig.toByteArray(), HashHelper.SHA1));
            String sha256 = BytesToHexString(HashHelper.getHash(sig.toByteArray(), "SHA-256"));
            generateSignatureMap();
            Map<String, String> packageSigs = signatureMap_.get(packageName);
            if (packageSigs == null) {
                return false;
            }
            if (!Build.TYPE.equals("user")) {
                Log.d(TAG, "Verifying debug signature... ");
                if (sha1.equals(packageSigs.get(KEY_DB_SHA1)) && sha256.equals(packageSigs.get(KEY_DB_SHA256))) {
                    Log.d(TAG, "Debug signature verified!");
                    return true;
                }
            }
            Log.d(TAG, "Verifying release signature... ");
            if (sha1.equals(packageSigs.get(KEY_RL_SHA1)) && sha256.equals(packageSigs.get(KEY_RL_SHA256))) {
                Log.d(TAG, "Release signature verified!");
                return true;
            }
            Log.e(TAG, "Signature verification failed!");
            return false;
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "Couldn't verify app signature!");
        } catch (NullPointerException e2) {
            Log.e(TAG, "Null pointer exception!");
        } catch (Exception ex) {
            Log.e(TAG, "Exception: '" + ex.getMessage() + "'");
        }
    }

    private static boolean isPackageAllowed(Context ctx, PackageInfo pi) {
        try {
            return isTrustedSignature(ctx, pi.packageName, getPackageSignature(pi));
        } catch (RuntimeException ex) {
            Log.e(TAG, "Runtime exception '" + ex.getMessage() + "'");
            return false;
        }
    }

    public static boolean isPackageAllowed(Context ctx, String packageName) {
        if (isUserDevBuild()) {
            Log.d(TAG, "Package " + packageName + " verified because build is userdev");
            return true;
        }
        try {
            PackageInfo pi = ctx.getPackageManager().getPackageInfo(packageName, 64);
            if (pi != null) {
                return isPackageAllowed(ctx, pi);
            }
            throw new RuntimeException(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Package '" + packageName + "' was not found!");
            return false;
        } catch (RuntimeException ex) {
            Log.e(TAG, "Runtime exception: " + ex.getMessage());
            return false;
        }
    }
}
