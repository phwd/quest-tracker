package com.oculus.vrshell.panelservice;

import android.content.Context;
import android.content.pm.PackageManager;
import com.oculus.vrshell.panelservice.SigCertVerifier;

public class PanelVerifier {
    public static final String TAG = "PanelVerifier";
    private final boolean mAreDebugSignaturesEnabled;
    private final SigCertVerifier mTrustedFirstPartyCertVerifier;

    private static class TrustedFirstPartyCertVerifier extends SigCertVerifier {
        public TrustedFirstPartyCertVerifier(Context context, PackageManager packageManager, boolean enableDebugSignatures) {
            super(VerifierConstants.getFirstPartyOculusStorePackages(enableDebugSignatures), VerifierConstants.getFirstPartyOculusStoreAssetBundles(), context, packageManager);
        }
    }

    public PanelVerifier(Context context, boolean enableDebugSignatures) {
        this.mTrustedFirstPartyCertVerifier = new TrustedFirstPartyCertVerifier(context, context.getPackageManager(), enableDebugSignatures);
        this.mAreDebugSignaturesEnabled = enableDebugSignatures;
    }

    public boolean areDebugSignaturesEnabled() {
        return this.mAreDebugSignaturesEnabled;
    }

    public SigCertVerifier.SigCertInfo checkApkSignature(int uid) {
        return this.mTrustedFirstPartyCertVerifier.checkSigCertInfo(uid);
    }

    public SigCertVerifier.SigCertInfo checkAssetBundleSignature(int uid) {
        return this.mTrustedFirstPartyCertVerifier.checkAssetBundleSignature(uid);
    }
}
