package com.oculus.panellib;

import android.content.Context;
import android.content.pm.PackageManager;
import com.oculus.panellib.SigCertVerifier;

public class PanelVerifier {
    public static final String TAG = "PanelVerifier";
    public final boolean mAreDebugSignaturesEnabled;
    public final SigCertVerifier mTrustedFirstPartyCertVerifier;

    public static class TrustedFirstPartyCertVerifier extends SigCertVerifier {
        public TrustedFirstPartyCertVerifier(Context context, PackageManager packageManager, boolean z) {
            super(VerifierConstants.getFirstPartyOculusStorePackages(z), VerifierConstants.getFirstPartyOculusStoreAssetBundles(), context, packageManager);
        }
    }

    public SigCertVerifier.SigCertInfo checkApkSignature(int i) {
        return this.mTrustedFirstPartyCertVerifier.checkSigCertInfo(i);
    }

    public SigCertVerifier.SigCertInfo checkAssetBundleSignature(int i) {
        return this.mTrustedFirstPartyCertVerifier.checkAssetBundleSignature(i);
    }

    public PanelVerifier(Context context, boolean z) {
        this.mTrustedFirstPartyCertVerifier = new TrustedFirstPartyCertVerifier(context, context.getPackageManager(), z);
        this.mAreDebugSignaturesEnabled = z;
    }

    public boolean areDebugSignaturesEnabled() {
        return this.mAreDebugSignaturesEnabled;
    }
}
