package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.cert.X509Certificate;

public final class PackageMetadata implements Parcelable {
    public static final Parcelable.Creator<PackageMetadata> CREATOR = null;
    public final byte[] androidManifest = new byte[0];
    public final long firstInstallTime = 0;
    public final String installerPackage = null;
    public final long lastUpdateTime = 0;
    public final byte[] packageHash = new byte[0];
    public final String packageIdentifier = null;
    public final byte[] packageManifest = new byte[0];
    public final Signature[] signatures = new Signature[0];

    public PackageMetadata(String str, byte[] bArr, Signature[] signatureArr, byte[] bArr2, byte[] bArr3, String str2, long j, long j2) {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(Object obj) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }

    public static final class Builder {
        public Builder() {
            throw new RuntimeException("Stub!");
        }

        public Builder setPackageIdentifier(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder setPackageHash(byte[] bArr) {
            throw new RuntimeException("Stub!");
        }

        public Builder setSignatures(Signature[] signatureArr) {
            throw new RuntimeException("Stub!");
        }

        public Builder setAndroidManifest(byte[] bArr) {
            throw new RuntimeException("Stub!");
        }

        public Builder setPackageManifest(byte[] bArr) {
            throw new RuntimeException("Stub!");
        }

        public Builder setInstallerPackage(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder setFirstInstallTime(long j) {
            throw new RuntimeException("Stub!");
        }

        public Builder setLastUpdateTime(long j) {
            throw new RuntimeException("Stub!");
        }

        public PackageMetadata build() {
            throw new RuntimeException("Stub!");
        }
    }

    public static class Signature implements Parcelable {
        public static final Parcelable.Creator<Signature> CREATOR = null;
        public final int algorithm = 0;
        public final String algorithmName = null;
        public final X509Certificate certificate = null;
        public final byte[] digest = new byte[0];
        public final byte[] fingerprint = new byte[0];
        public final Scheme scheme = null;

        public enum Scheme {
            V1,
            V2,
            V3
        }

        public Signature(Scheme scheme2, X509Certificate x509Certificate, byte[] bArr, int i, String str, byte[] bArr2) {
            throw new RuntimeException("Stub!");
        }

        public boolean equals(Object obj) {
            throw new RuntimeException("Stub!");
        }

        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel parcel, int i) {
            throw new RuntimeException("Stub!");
        }

        public static final class Algorithm {
            public static final int DSA_SHA_256 = 769;
            public static final int ECDSA_SHA_256 = 513;
            public static final int ECDSA_SHA_512 = 514;
            public static final int RSASSA_PKCS1_5_SHA_256 = 259;
            public static final int RSASSA_PKCS1_5_SHA_512 = 260;
            public static final int RSASSA_PSS_SHA_256 = 257;
            public static final int RSASSA_PSS_SHA_512 = 258;

            public Algorithm() {
                throw new RuntimeException("Stub!");
            }

            public static String getName(int i) {
                throw new RuntimeException("Stub!");
            }

            public static String extractDigestName(String str) {
                throw new RuntimeException("Stub!");
            }
        }

        public static final class Builder {
            public Builder() {
                throw new RuntimeException("Stub!");
            }

            public Builder setScheme(Scheme scheme) {
                throw new RuntimeException("Stub!");
            }

            public Builder setCertificate(X509Certificate x509Certificate) {
                throw new RuntimeException("Stub!");
            }

            public Builder setCertificateFingerprint(byte[] bArr) {
                throw new RuntimeException("Stub!");
            }

            public Builder setAlgorithm(int i) {
                throw new RuntimeException("Stub!");
            }

            public Builder setAlgorithmName(String str) {
                throw new RuntimeException("Stub!");
            }

            public Builder setDigest(byte[] bArr) {
                throw new RuntimeException("Stub!");
            }

            public Signature build() {
                throw new RuntimeException("Stub!");
            }
        }
    }
}
