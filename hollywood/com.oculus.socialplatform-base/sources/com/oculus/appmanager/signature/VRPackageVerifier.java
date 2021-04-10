package com.oculus.appmanager.signature;

import X.AnonymousClass006;
import android.content.pm.PackageManager;
import com.oculus.appmanager.vrsign.VRSignVerifier;
import com.oculus.common.serial.BuildSerialUtil;
import java.io.IOException;
import java.io.InputStream;

public class VRPackageVerifier {
    public final VRSignVerifier mVerifier = new VRSignVerifier();

    public boolean verifyVRSigFromPackage(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        try {
            return this.mVerifier.verifyVRSig(packageManager.getApplicationInfo(str, 128).publicSourceDir);
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean verifyOculusSigFromPackage(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        InputStream inputStream = null;
        try {
            inputStream = packageManager.getResourcesForApplication(str).getAssets().open(AnonymousClass006.A07("oculussig_", BuildSerialUtil.getSerial()));
            if (this.mVerifier.verifyOculusSig(BuildSerialUtil.getSerial(), inputStream)) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                return true;
            }
            if (inputStream == null) {
                return false;
            }
            try {
                inputStream.close();
                return false;
            } catch (IOException unused2) {
                return false;
            }
        } catch (IOException unused3) {
            if (0 == 0) {
                return false;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }
}
