package com.oculus.panellib;

import android.content.Context;
import android.content.pm.PackageManager;
import com.oculus.panellib.SigCertVerifier;

public class PanelVerifier {
    private final boolean mAreDebugSignaturesEnabled;
    private final SigCertVerifier mTrustedFirstPartyCertVerifier;

    static class TrustedFirstPartyCertVerifier extends SigCertVerifier {
        public TrustedFirstPartyCertVerifier(Context context, PackageManager packageManager, boolean z) {
            super(VerifierConstants.getFirstPartyOculusStorePackages(z), VerifierConstants.getFirstPartyOculusStoreAssetBundles(), context, packageManager);
        }
    }

    public PanelVerifier(Context context, boolean z) {
        this.mTrustedFirstPartyCertVerifier = new TrustedFirstPartyCertVerifier(context, context.getPackageManager(), z);
        this.mAreDebugSignaturesEnabled = z;
    }

    public boolean areDebugSignaturesEnabled() {
        return this.mAreDebugSignaturesEnabled;
    }

    public SigCertVerifier.SigCertInfo checkApkSignature(int i) {
        return this.mTrustedFirstPartyCertVerifier.checkSigCertInfo(i);
    }
}
