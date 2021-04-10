package com.oculus.os;

import com.oculus.a.a;
import com.oculus.a.b;
import com.oculus.a.c;
import com.oculus.a.d;
import com.oculus.a.e;
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

    public b evaluate(d dVar, Set set, PackageMetadata packageMetadata) {
        throw new RuntimeException("Stub!");
    }

    public b evaluate(d dVar, Set set, String str) {
        throw new RuntimeException("Stub!");
    }

    public c install(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public c[] install(List list) {
        throw new RuntimeException("Stub!");
    }

    public c[] install(List list, int i) {
        throw new RuntimeException("Stub!");
    }

    public c[] queryByCategoryAndSecurityPrincipals(a aVar, Set set) {
        throw new RuntimeException("Stub!");
    }

    public c[] queryByPackage(PackageMetadata packageMetadata) {
        throw new RuntimeException("Stub!");
    }

    public c[] queryLicenses(String str, String[] strArr, String str2) {
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

    public boolean revoke(long j) {
        throw new RuntimeException("Stub!");
    }

    public boolean revoke(long[] jArr) {
        throw new RuntimeException("Stub!");
    }

    public boolean unrevoke(long j) {
        throw new RuntimeException("Stub!");
    }

    public boolean unrevoke(long[] jArr) {
        throw new RuntimeException("Stub!");
    }

    public e verify(d dVar, Set set, PackageMetadata packageMetadata) {
        throw new RuntimeException("Stub!");
    }

    public e verify(d dVar, Set set, String str) {
        throw new RuntimeException("Stub!");
    }
}
