package com.oculus.appmanager.signature;

import X.AnonymousClass006;
import android.content.pm.PackageManager;
import com.oculus.appmanager.vrsign.VRSignVerifier;
import com.oculus.common.serial.BuildSerialUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class VRPackageVerifier {
    public final VRSignVerifier mVerifier = new VRSignVerifier();

    public final boolean A00(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        InputStream inputStream = null;
        try {
            inputStream = packageManager.getResourcesForApplication(str).getAssets().open(AnonymousClass006.A05("oculussig_", BuildSerialUtil.A00()));
            VRSignVerifier vRSignVerifier = this.mVerifier;
            if (vRSignVerifier.mVerifier.A00(new ByteArrayInputStream(AnonymousClass006.A05("﻿", BuildSerialUtil.A00()).getBytes("UTF-16LE")), inputStream)) {
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
