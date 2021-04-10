package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.cert.X509Certificate;

public final class PackageMetadata implements Parcelable {
    public static final Parcelable.Creator CREATOR = null;
    public final byte[] androidManifest = new byte[0];
    public final long firstInstallTime = 0;
    public final String installerPackage = null;
    public final long lastUpdateTime = 0;
    public final byte[] packageHash = new byte[0];
    public final String packageIdentifier = null;
    public final byte[] packageManifest = new byte[0];
    public final Signature[] signatures = new Signature[0];

    public static final class Builder {
        public Builder() {
            throw new RuntimeException("Stub!");
        }

        public final PackageMetadata build() {
            throw new RuntimeException("Stub!");
        }

        public final Builder setAndroidManifest(byte[] bArr) {
            throw new RuntimeException("Stub!");
        }

        public final Builder setFirstInstallTime(long j) {
            throw new RuntimeException("Stub!");
        }

        public final Builder setInstallerPackage(String str) {
            throw new RuntimeException("Stub!");
        }

        public final Builder setLastUpdateTime(long j) {
            throw new RuntimeException("Stub!");
        }

        public final Builder setPackageHash(byte[] bArr) {
            throw new RuntimeException("Stub!");
        }

        public final Builder setPackageIdentifier(String str) {
            throw new RuntimeException("Stub!");
        }

        public final Builder setPackageManifest(byte[] bArr) {
            throw new RuntimeException("Stub!");
        }

        public final Builder setSignatures(Signature[] signatureArr) {
            throw new RuntimeException("Stub!");
        }
    }

    public static class Signature implements Parcelable {
        public static final Parcelable.Creator CREATOR = null;
        public final int algorithm = 0;
        public final String algorithmName = null;
        public final X509Certificate certificate = null;
        public final byte[] digest = new byte[0];
        public final byte[] fingerprint = new byte[0];
        public final Scheme scheme = null;

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

            public static String extractDigestName(String str) {
                throw new RuntimeException("Stub!");
            }

            public static String getName(int i) {
                throw new RuntimeException("Stub!");
            }
        }

        public static final class Builder {
            public Builder() {
                throw new RuntimeException("Stub!");
            }

            public final Signature build() {
                throw new RuntimeException("Stub!");
            }

            public final Builder setAlgorithm(int i) {
                throw new RuntimeException("Stub!");
            }

            public final Builder setAlgorithmName(String str) {
                throw new RuntimeException("Stub!");
            }

            public final Builder setCertificate(X509Certificate x509Certificate) {
                throw new RuntimeException("Stub!");
            }

            public final Builder setCertificateFingerprint(byte[] bArr) {
                throw new RuntimeException("Stub!");
            }

            public final Builder setDigest(byte[] bArr) {
                throw new RuntimeException("Stub!");
            }

            public final Builder setScheme(Scheme scheme) {
                throw new RuntimeException("Stub!");
            }
        }

        public enum Scheme {
            V1,
            V2,
            V3
        }

        public Signature(Scheme scheme2, X509Certificate x509Certificate, byte[] bArr, int i, String str, byte[] bArr2) {
            throw new RuntimeException("Stub!");
        }

        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        public boolean equals(Object obj) {
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel parcel, int i) {
            throw new RuntimeException("Stub!");
        }
    }

    public PackageMetadata(String str, byte[] bArr, Signature[] signatureArr, byte[] bArr2, byte[] bArr3, String str2, long j, long j2) {
        throw new RuntimeException("Stub!");
    }

    public final int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public final boolean equals(Object obj) {
        throw new RuntimeException("Stub!");
    }

    public final int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
