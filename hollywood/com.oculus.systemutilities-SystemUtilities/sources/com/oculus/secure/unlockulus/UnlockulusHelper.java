package com.oculus.secure.unlockulus;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.oculus.secure.trustedapp.AllFamilyTrustedSignatures;
import com.oculus.secure.trustedapp.AppVerifier;
import com.oculus.secure.trustedapp.exception.PackageNameNotFoundException;

public class UnlockulusHelper {
    private static Class<?> CLASS;
    private static final String TAG = UnlockulusHelper.class.getSimpleName();
    private static final VRPackageVerifier sVerifier = new VRPackageVerifier();

    static {
        try {
            CLASS = Class.forName("android.os.SystemProperties");
        } catch (ClassNotFoundException e) {
        }
    }

    public static class Result {
        public final boolean developerVRSigned;
        public final boolean firstPartyCertValid;
        public final boolean isInstalled;
        public final boolean userdev;

        public Result(boolean isInstalled2, boolean firstPartyCertValid2, boolean developerVRSigned2, boolean userdev2) {
            this.isInstalled = isInstalled2;
            this.firstPartyCertValid = firstPartyCertValid2;
            this.developerVRSigned = developerVRSigned2;
            this.userdev = userdev2;
        }

        public boolean isUnlockulused() {
            return this.userdev || (this.isInstalled && this.firstPartyCertValid && this.developerVRSigned);
        }

        public String toString() {
            return String.format("Result[isInstalled: %b, firstPartyCertValid: %b, developerVRSigned: %b, userdev: %b]", Boolean.valueOf(this.isInstalled), Boolean.valueOf(this.firstPartyCertValid), Boolean.valueOf(this.developerVRSigned), Boolean.valueOf(this.userdev));
        }
    }

    public static boolean isEmployeeWithWhitelistedDevice(Context context) {
        return isEmployeeWithWhitelistedDevice(context, false);
    }

    public static boolean isEmployeeWithWhitelistedDevice(Context context, boolean verbose) {
        return getResult(context, verbose).isUnlockulused();
    }

    private static boolean isReleaseSystemImage() {
        try {
            String propValue = (String) CLASS.getMethod("get", String.class, String.class).invoke(null, "ro.build.fingerprint", null);
            if (propValue == null) {
                return false;
            }
            return propValue.endsWith("release-keys");
        } catch (Exception e) {
            return false;
        }
    }

    private static Result getResult(Context context, boolean verbose) {
        Result result;
        boolean userdev = true;
        if (isReleaseSystemImage()) {
            userdev = false;
        }
        try {
            result = new Result(true, AllFamilyTrustedSignatures.OCULUS_APPS_SIGNATURE_HASH_RELEASE.equals(AppVerifier.getSignatureFromPackageName(context, "com.oculus.unlockulus")), sVerifier.verifyOculusSigFromPackage(context.getPackageManager(), "com.oculus.unlockulus"), userdev);
        } catch (PackageNameNotFoundException e) {
            result = new Result(false, false, false, userdev);
        } catch (PackageManager.NameNotFoundException e2) {
            result = new Result(false, false, false, userdev);
        }
        if (verbose) {
            Log.i(TAG, result.toString());
        }
        return result;
    }
}
