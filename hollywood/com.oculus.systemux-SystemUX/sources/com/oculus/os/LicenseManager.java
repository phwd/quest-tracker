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

    public License[] install(List<byte[]> list, int i) {
        throw new RuntimeException("Stub!");
    }

    public License install(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public VerificationResult verify(UserAction userAction, Set<String> set, PackageMetadata packageMetadata) {
        throw new RuntimeException("Stub!");
    }

    public VerificationResult verify(UserAction userAction, Set<String> set, String str) {
        throw new RuntimeException("Stub!");
    }

    public EvaluationResult evaluate(UserAction userAction, Set<String> set, PackageMetadata packageMetadata) throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public EvaluationResult evaluate(UserAction userAction, Set<String> set, String str) throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public boolean revoke(long[] jArr) {
        throw new RuntimeException("Stub!");
    }

    public boolean revoke(long j) {
        throw new RuntimeException("Stub!");
    }

    public boolean unrevoke(long[] jArr) {
        throw new RuntimeException("Stub!");
    }

    public boolean unrevoke(long j) {
        throw new RuntimeException("Stub!");
    }

    public License[] queryByCategoryAndSecurityPrincipals(Category category, Set<String> set) {
        throw new RuntimeException("Stub!");
    }

    public License[] queryLicenses(String str, String[] strArr, String str2) {
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
