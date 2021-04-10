package com.oculus.secure.unlockulus;

import android.content.pm.PackageManager;
import android.os.Build;
import com.adobe.xmp.options.PropertyOptions;
import java.io.IOException;
import java.io.InputStream;

public class VRPackageVerifier {
    private final VRSignVerifier mVerifier = new VRSignVerifier();

    public boolean verifyVRSigFromPackage(PackageManager packageManager, String packageName) throws PackageManager.NameNotFoundException {
        try {
            return this.mVerifier.verifyVRSig(packageManager.getApplicationInfo(packageName, PropertyOptions.HAS_TYPE).publicSourceDir);
        } catch (IOException e) {
            return false;
        }
    }

    public boolean verifyOculusSigFromPackage(PackageManager packageManager, String packageName) throws PackageManager.NameNotFoundException {
        InputStream signatureStream = null;
        try {
            signatureStream = packageManager.getResourcesForApplication(packageName).getAssets().open("oculussig_" + Build.SERIAL);
            if (!this.mVerifier.verifyOculusSig(Build.SERIAL, signatureStream)) {
                if (signatureStream != null) {
                    try {
                        signatureStream.close();
                    } catch (IOException e) {
                    }
                }
                return false;
            } else if (signatureStream == null) {
                return true;
            } else {
                try {
                    signatureStream.close();
                    return true;
                } catch (IOException e2) {
                    return true;
                }
            }
        } catch (IOException e3) {
            if (signatureStream != null) {
                try {
                    signatureStream.close();
                } catch (IOException e4) {
                }
            }
        } catch (Throwable th) {
            if (signatureStream != null) {
                try {
                    signatureStream.close();
                } catch (IOException e5) {
                }
            }
            throw th;
        }
    }
}
