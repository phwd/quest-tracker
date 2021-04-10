package com.oculus.unlockulus_helper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.oculus.appmanager.signature.VRPackageVerifier;
import com.oculus.signature.SignatureHelper;
import com.oculus.util.system.SystemProps;

public class UnlockulusHelper {
    private static final String PACKAGE_NAME = "com.oculus.unlockulus";
    private static final String SYSTEM_PROP_SIGN_TYPE = "ro.build.fingerprint";
    private static final String TAG = "UnlockulusHelper";
    private static final String TYPE_RELEASEKEY = "release-keys";
    private static final VRPackageVerifier sVerifier = new VRPackageVerifier();

    public static class Result {
        public final boolean developerVRSigned;
        public final boolean firstPartyCertValid;
        public final boolean isInstalled;
        public final boolean userdev;

        public Result(boolean z, boolean z2, boolean z3, boolean z4) {
            this.isInstalled = z;
            this.firstPartyCertValid = z2;
            this.developerVRSigned = z3;
            this.userdev = z4;
        }

        public boolean isUnlockulused() {
            return this.userdev || (this.isInstalled && this.firstPartyCertValid && this.developerVRSigned);
        }

        public String toString() {
            return String.format("Result[isInstalled: %b, firstPartyCertValid: %b, developerVRSigned: %b, userdev: %b]", Boolean.valueOf(this.isInstalled), Boolean.valueOf(this.firstPartyCertValid), Boolean.valueOf(this.developerVRSigned), Boolean.valueOf(this.userdev));
        }
    }

    public boolean isEmployeeWithWhitelistedDevice(Context context) {
        return isEmployeeWithWhitelistedDevice(context, false);
    }

    public boolean isEmployeeWithWhitelistedDevice(Context context, boolean z) {
        return getResult(context, z).isUnlockulused();
    }

    private static boolean isReleaseSystemImage() {
        String string = SystemProps.getString(SYSTEM_PROP_SIGN_TYPE, null);
        if (string == null) {
            return false;
        }
        return string.endsWith(TYPE_RELEASEKEY);
    }

    public static Result getResult(Context context, boolean z) {
        Result result;
        boolean z2 = !isReleaseSystemImage();
        try {
            result = new Result(true, SignatureHelper.verifyProductionFirstPartyApplication(context, PACKAGE_NAME), sVerifier.verifyOculusSigFromPackage(context.getPackageManager(), PACKAGE_NAME), z2);
        } catch (PackageManager.NameNotFoundException unused) {
            result = new Result(false, false, false, z2);
        }
        if (z) {
            Log.i(TAG, result.toString());
        }
        return result;
    }
}
