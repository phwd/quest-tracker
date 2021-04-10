package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.cert.X509Certificate;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class PackageMetadata implements Parcelable {
    public static final Parcelable.Creator CREATOR = null;
    public final byte[] androidManifest = null;
    public final String installerPackage;
    public final byte[] packageHash = null;
    public final String packageIdentifier;
    public final byte[] packageManifest = null;
    public final Signature[] signatures = null;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public final class Builder {
        public Builder() {
            throw new RuntimeException("Stub!");
        }

        public PackageMetadata build() {
            throw new RuntimeException("Stub!");
        }

        public Builder setAndroidManifest(byte[] bArr) {
            throw new RuntimeException("Stub!");
        }

        public Builder setInstallerPackage(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder setPackageHash(byte[] bArr) {
            throw new RuntimeException("Stub!");
        }

        public Builder setPackageIdentifier(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder setPackageManifest(byte[] bArr) {
            throw new RuntimeException("Stub!");
        }

        public Builder setSignatures(Signature[] signatureArr) {
            throw new RuntimeException("Stub!");
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class Signature implements Parcelable {
        public static final Parcelable.Creator CREATOR = null;
        public final int algorithm;
        public final String algorithmName;
        public final X509Certificate certificate;
        public final byte[] digest = null;
        public final byte[] fingerprint = null;
        public final Scheme scheme;
        public final byte[] signature = null;

        /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
        public final class Algorithm {
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
        }

        /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
        public final class Builder {
            public Builder() {
                throw new RuntimeException("Stub!");
            }

            public Signature build() {
                throw new RuntimeException("Stub!");
            }

            public Builder setAlgorithm(int i) {
                throw new RuntimeException("Stub!");
            }

            public Builder setAlgorithmName(String str) {
                throw new RuntimeException("Stub!");
            }

            public Builder setCertificate(X509Certificate x509Certificate) {
                throw new RuntimeException("Stub!");
            }

            public Builder setCertificateFingerprint(byte[] bArr) {
                throw new RuntimeException("Stub!");
            }

            public Builder setDigest(byte[] bArr) {
                throw new RuntimeException("Stub!");
            }

            public Builder setScheme(Scheme scheme) {
                throw new RuntimeException("Stub!");
            }

            public Builder setSignature(byte[] bArr) {
                throw new RuntimeException("Stub!");
            }
        }

        /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
        public enum Scheme {
            V1,
            V2,
            V3
        }

        public Signature(Scheme scheme2, X509Certificate x509Certificate, byte[] bArr, int i, String str, byte[] bArr2, byte[] bArr3) {
            throw new RuntimeException("Stub!");
        }

        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel parcel, int i) {
            throw new RuntimeException("Stub!");
        }
    }

    public PackageMetadata(String str, byte[] bArr, Signature[] signatureArr, byte[] bArr2, byte[] bArr3, String str2) {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }

    public PackageMetadata(Parcel parcel) {
        throw new RuntimeException("Stub!");
    }
}
