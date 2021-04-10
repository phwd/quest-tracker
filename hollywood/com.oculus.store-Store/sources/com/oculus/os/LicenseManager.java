package com.oculus.os;

import android.os.RemoteException;
import com.oculus.license.Category;
import com.oculus.license.EvaluationResult;
import com.oculus.license.License;
import com.oculus.license.UserAction;
import com.oculus.license.VerificationResult;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Set;

public class LicenseManager {
    public static final int SKIP_FAILURES = 1;

    LicenseManager() {
        throw new RuntimeException("Stub!");
    }

    public static synchronized LicenseManager getInstance() {
        synchronized (LicenseManager.class) {
            throw new RuntimeException("Stub!");
        }
    }

    public License[] install(List<byte[]> list) {
        throw new RuntimeException("Stub!");
    }

    public License[] install(List<byte[]> list, int flags) {
        throw new RuntimeException("Stub!");
    }

    public License install(byte[] licenseBlob) {
        throw new RuntimeException("Stub!");
    }

    public VerificationResult verify(UserAction userAction, Set<String> set, PackageMetadata packageMetadata) {
        throw new RuntimeException("Stub!");
    }

    public VerificationResult verify(UserAction userAction, Set<String> set, String packageName) {
        throw new RuntimeException("Stub!");
    }

    public EvaluationResult evaluate(UserAction userAction, Set<String> set, PackageMetadata packageMetadata) throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public EvaluationResult evaluate(UserAction userAction, Set<String> set, String packageName) throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public boolean revoke(long[] fbids) {
        throw new RuntimeException("Stub!");
    }

    public boolean revoke(long fbid) {
        throw new RuntimeException("Stub!");
    }

    public boolean unrevoke(long[] fbids) {
        throw new RuntimeException("Stub!");
    }

    public boolean unrevoke(long fbid) {
        throw new RuntimeException("Stub!");
    }

    public License[] queryByCategoryAndSecurityPrincipals(Category category, Set<String> set) {
        throw new RuntimeException("Stub!");
    }

    public License[] queryLicenses(String selection, String[] selectionArgs, String sortOrder) {
        throw new RuntimeException("Stub!");
    }

    public License[] queryByPackage(PackageMetadata packageMetadata) {
        throw new RuntimeException("Stub!");
    }

    public boolean registerLicenseSigner(Certificate certificate) {
        throw new RuntimeException("Stub!");
    }

    public boolean reset() {
        throw new RuntimeException("Stub!");
    }

    public void restart() {
        throw new RuntimeException("Stub!");
    }
}
