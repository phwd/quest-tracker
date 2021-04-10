package com.oculus.os;

import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.oculus.license.Category;
import com.oculus.license.EvaluationResult;
import com.oculus.license.License;
import com.oculus.license.UserAction;
import com.oculus.license.VerificationResult;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import oculus.internal.license.ILicenseManager;
import oculus.internal.license.LicenseCollection;

public class LicenseManager {
    private static final boolean DEBUG = false;
    public static final int SKIP_FAILURES = 1;
    private static final String TAG = "LicenseManager";
    private static LicenseManager sLicenseManager = null;
    private ILicenseManager mService = ILicenseManager.Stub.asInterface(ServiceManager.getService("LicenseManager"));

    public static synchronized LicenseManager getInstance() {
        LicenseManager licenseManager;
        synchronized (LicenseManager.class) {
            if (sLicenseManager == null) {
                sLicenseManager = new LicenseManager();
            }
            licenseManager = sLicenseManager;
        }
        return licenseManager;
    }

    private LicenseManager() {
    }

    public License[] install(List<byte[]> licenseBlobs) {
        return install(licenseBlobs, 0);
    }

    public License[] install(List<byte[]> licenseBlobs, int flags) {
        try {
            return this.mService.installLicenses(new LicenseCollection(licenseBlobs), flags);
        } catch (RemoteException e) {
            Log.e("LicenseManager", "license install failed", e);
            return null;
        }
    }

    public License install(byte[] licenseBlob) {
        try {
            return this.mService.installLicense(licenseBlob);
        } catch (RemoteException e) {
            Log.e("LicenseManager", "license install failed", e);
            return null;
        }
    }

    public VerificationResult verify(UserAction userAction, Set<String> activeSecurityPrincipals, PackageMetadata packageMetadata) {
        try {
            ArrayList<String> principals = new ArrayList<>();
            if (activeSecurityPrincipals != null) {
                principals.addAll(activeSecurityPrincipals);
            }
            return this.mService.verifyActionAgainstPackageMetadata(userAction, principals, packageMetadata);
        } catch (RemoteException e) {
            Log.e("LicenseManager", "could not verify user action", e);
            return VerificationResult.USER_ACTION_DENIED;
        }
    }

    public VerificationResult verify(UserAction userAction, Set<String> activeSecurityPrincipals, String packageName) {
        try {
            ArrayList<String> principals = new ArrayList<>();
            if (activeSecurityPrincipals != null) {
                principals.addAll(activeSecurityPrincipals);
            }
            return this.mService.verifyActionAgainstPackageId(userAction, principals, packageName);
        } catch (RemoteException e) {
            Log.e("LicenseManager", "could not verify user action", e);
            return VerificationResult.USER_ACTION_DENIED;
        }
    }

    public EvaluationResult evaluate(UserAction userAction, Set<String> activeSecurityPrincipals, PackageMetadata packageMetadata) throws RemoteException {
        ArrayList<String> principals = new ArrayList<>();
        if (activeSecurityPrincipals != null) {
            principals.addAll(activeSecurityPrincipals);
        }
        return this.mService.evaluateActionAgainstPackageMetadata(userAction, principals, packageMetadata);
    }

    public EvaluationResult evaluate(UserAction userAction, Set<String> activeSecurityPrincipals, String packageName) throws RemoteException {
        ArrayList<String> principals = new ArrayList<>();
        if (activeSecurityPrincipals != null) {
            principals.addAll(activeSecurityPrincipals);
        }
        return this.mService.evaluateActionAgainstPackageId(userAction, principals, packageName);
    }

    public boolean revoke(long[] fbids) {
        try {
            return this.mService.revokeLicenses(fbids);
        } catch (RemoteException e) {
            return DEBUG;
        }
    }

    public boolean revoke(long fbid) {
        return revoke(new long[]{fbid});
    }

    public boolean unrevoke(long[] fbids) {
        try {
            return this.mService.unrevokeLicenses(fbids);
        } catch (RemoteException e) {
            return DEBUG;
        }
    }

    public boolean unrevoke(long fbid) {
        return unrevoke(new long[]{fbid});
    }

    public License[] queryByCategoryAndSecurityPrincipals(Category category, Set<String> activeSecurityPrincipals) {
        try {
            ArrayList<String> principals = new ArrayList<>();
            if (activeSecurityPrincipals != null) {
                principals.addAll(activeSecurityPrincipals);
            }
            return this.mService.queryByCategoryAndSecurityPrincipals(category, principals);
        } catch (RemoteException e) {
            Log.e("LicenseManager", "failed to call queryByCategoryAndSecurityPrincipals", e);
            return null;
        }
    }

    public License[] queryLicenses(String selection, String[] selectionArgs, String sortOrder) {
        try {
            return this.mService.queryLicenses(selection, selectionArgs, sortOrder);
        } catch (RemoteException e) {
            Log.e("LicenseManager", "failed to query license store", e);
            return null;
        }
    }

    public License[] queryByPackage(PackageMetadata packageMetadata) {
        try {
            return this.mService.queryByPackage(packageMetadata);
        } catch (RemoteException e) {
            Log.e("LicenseManager", "Failed to query license store", e);
            return null;
        }
    }

    public boolean registerLicenseSigner(Certificate certificate) {
        try {
            return this.mService.registerLicenseSigner(certificate.getEncoded());
        } catch (RemoteException | CertificateEncodingException e) {
            Log.e("LicenseManager", "failed to call registerLicenseSigner", e);
            return DEBUG;
        }
    }

    public boolean reset() {
        try {
            return this.mService.reset();
        } catch (RemoteException e) {
            Log.e("LicenseManager", "failed to reset LicenseManager", e);
            return DEBUG;
        }
    }

    public void restart() {
        try {
            this.mService.restart();
        } catch (RemoteException e) {
            Log.e("LicenseManager", "failed to restart LicenseManager", e);
        }
    }
}
