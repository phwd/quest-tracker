package com.oculus.appmanager.signature;

import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.oculus.appmanager.vrsign.VRSignVerifier;
import com.oculus.common.serial.BuildSerialUtil;
import java.io.IOException;
import java.io.InputStream;

public class VRPackageVerifier {
    private final VRSignVerifier mVerifier = new VRSignVerifier();

    public boolean verifyVRSigFromPackage(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        try {
            return this.mVerifier.verifyVRSig(packageManager.getApplicationInfo(str, 128).publicSourceDir);
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean verifyOculusSigFromPackage(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        Resources resourcesForApplication = packageManager.getResourcesForApplication(str);
        InputStream inputStream = null;
        try {
            AssetManager assets = resourcesForApplication.getAssets();
            inputStream = assets.open("oculussig_" + BuildSerialUtil.getSerial());
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
            if (inputStream == null) {
                return false;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }
}
