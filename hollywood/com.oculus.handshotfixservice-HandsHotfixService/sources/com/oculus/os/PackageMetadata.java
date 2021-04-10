package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Objects;

public final class PackageMetadata implements Parcelable {
    private static int ANDROID_MANIFEST_PRESENT = 4;
    public static final Parcelable.Creator<PackageMetadata> CREATOR = new Parcelable.Creator<PackageMetadata>() {
        /* class com.oculus.os.PackageMetadata.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public PackageMetadata createFromParcel(Parcel in) {
            int optionalSerializationMembers = in.readInt();
            Builder builder = new Builder();
            builder.setPackageIdentifier(in.readString());
            if ((PackageMetadata.PACKAGE_HASH_PRESENT & optionalSerializationMembers) != 0) {
                builder.setPackageHash(in.createByteArray());
            }
            if ((PackageMetadata.SIGNATURES_PRESENT & optionalSerializationMembers) != 0) {
                builder.setSignatures((Signature[]) in.createTypedArray(Signature.CREATOR));
            }
            if ((PackageMetadata.ANDROID_MANIFEST_PRESENT & optionalSerializationMembers) != 0) {
                builder.setAndroidManifest(in.createByteArray());
            }
            if ((PackageMetadata.PACKAGE_MANIFEST_PRESENT & optionalSerializationMembers) != 0) {
                builder.setPackageManifest(in.createByteArray());
            }
            if ((PackageMetadata.INSTALLER_PRESENT & optionalSerializationMembers) != 0) {
                builder.setInstallerPackage(in.readString());
            }
            builder.setFirstInstallTime(in.readLong());
            builder.setLastUpdateTime(in.readLong());
            builder.setSourceStampDisposition((SourceStampDisposition) in.readSerializable());
            return builder.build();
        }

        @Override // android.os.Parcelable.Creator
        public PackageMetadata[] newArray(int size) {
            return new PackageMetadata[size];
        }
    };
    private static int INSTALLER_PRESENT = 16;
    private static int PACKAGE_HASH_PRESENT = 1;
    private static int PACKAGE_MANIFEST_PRESENT = 8;
    private static int SIGNATURES_PRESENT = 2;
    public final byte[] androidManifest;
    public final long firstInstallTime;
    public final String installerPackage;
    public final long lastUpdateTime;
    public final byte[] packageHash;
    public final String packageIdentifier;
    public final byte[] packageManifest;
    public final Signature[] signatures;
    public final SourceStampDisposition sourceStampDisposition;

    public enum SourceStampDisposition {
        NOT_TESTED(-3),
        NOT_SUPPORTED(-2),
        INVALID(-1),
        NOT_PRESENT(0),
        VERIFIED(1);
        
        public final int value;

        private SourceStampDisposition(int value2) {
            this.value = value2;
        }

        public static SourceStampDisposition fromValue(int value2) {
            if (value2 == -3) {
                return NOT_TESTED;
            }
            if (value2 == -2) {
                return NOT_SUPPORTED;
            }
            if (value2 == -1) {
                return INVALID;
            }
            if (value2 == 0) {
                return NOT_PRESENT;
            }
            if (value2 == 1) {
                return VERIFIED;
            }
            throw new IllegalArgumentException("Invalid disposition");
        }
    }

    public PackageMetadata(String packageIdentifier2, byte[] packageHash2, Signature[] signatures2, byte[] androidManifest2, byte[] packageManifest2, String installerPackage2, long firstInstallTime2, long lastUpdateTime2, SourceStampDisposition sourceStampDisposition2) {
        this.packageIdentifier = packageIdentifier2;
        this.packageHash = packageHash2;
        this.signatures = signatures2;
        this.androidManifest = androidManifest2;
        this.packageManifest = packageManifest2;
        this.installerPackage = installerPackage2;
        this.firstInstallTime = firstInstallTime2;
        this.lastUpdateTime = lastUpdateTime2;
        this.sourceStampDisposition = sourceStampDisposition2;
    }

    public int size() {
        String str = this.packageIdentifier;
        int i = 0;
        int length = (str != null ? str.length() : 0) + 1;
        byte[] bArr = this.packageHash;
        int length2 = length + (bArr != null ? bArr.length : 0);
        Signature[] signatureArr = this.signatures;
        int sum = length2 + (signatureArr != null ? Arrays.stream(signatureArr).mapToInt($$Lambda$dYc4o9aEzx0Ok_PivAlLlp1DhHA.INSTANCE).sum() : 0);
        byte[] bArr2 = this.androidManifest;
        int length3 = sum + (bArr2 != null ? bArr2.length : 0);
        byte[] bArr3 = this.packageManifest;
        int length4 = length3 + (bArr3 != null ? bArr3.length : 0);
        String str2 = this.installerPackage;
        if (str2 != null) {
            i = str2.length();
        }
        return length4 + i;
    }

    public static final class Builder {
        private byte[] androidManifest;
        private long firstInstallTime = 0;
        private String installerPackage;
        private long lastUpdateTime = 0;
        private byte[] packageHash;
        private String packageIdentifier;
        private byte[] packageManifest;
        private Signature[] signatures;
        private SourceStampDisposition sourceStampDisposition = SourceStampDisposition.NOT_TESTED;

        public Builder setPackageIdentifier(String packageIdentifier2) {
            this.packageIdentifier = packageIdentifier2;
            return this;
        }

        public Builder setPackageHash(byte[] packageHash2) {
            this.packageHash = packageHash2;
            return this;
        }

        public Builder setSignatures(Signature[] signatures2) {
            this.signatures = signatures2;
            return this;
        }

        public Builder setAndroidManifest(byte[] androidManifest2) {
            this.androidManifest = androidManifest2;
            return this;
        }

        public Builder setPackageManifest(byte[] packageManifest2) {
            this.packageManifest = packageManifest2;
            return this;
        }

        public Builder setInstallerPackage(String installerPackage2) {
            this.installerPackage = installerPackage2;
            return this;
        }

        public Builder setFirstInstallTime(long firstInstallTime2) {
            this.firstInstallTime = firstInstallTime2;
            return this;
        }

        public Builder setLastUpdateTime(long lastUpdateTime2) {
            this.lastUpdateTime = lastUpdateTime2;
            return this;
        }

        public Builder setSourceStampDisposition(SourceStampDisposition sourceStampDisposition2) {
            this.sourceStampDisposition = sourceStampDisposition2;
            return this;
        }

        public PackageMetadata build() {
            return new PackageMetadata(this.packageIdentifier, this.packageHash, this.signatures, this.androidManifest, this.packageManifest, this.installerPackage, this.firstInstallTime, this.lastUpdateTime, this.sourceStampDisposition);
        }
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PackageMetadata)) {
            return false;
        }
        PackageMetadata o = (PackageMetadata) other;
        if (!Objects.equals(this.packageIdentifier, o.packageIdentifier) || !Arrays.equals(this.packageHash, o.packageHash) || !Arrays.equals(this.signatures, o.signatures) || !Arrays.equals(this.androidManifest, o.androidManifest) || !Arrays.equals(this.packageManifest, o.packageManifest) || !Objects.equals(this.installerPackage, o.installerPackage) || this.firstInstallTime != o.firstInstallTime || this.lastUpdateTime != o.lastUpdateTime || this.sourceStampDisposition != o.sourceStampDisposition) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.packageIdentifier);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt((this.packageHash != null ? PACKAGE_HASH_PRESENT : 0) | (this.signatures != null ? SIGNATURES_PRESENT : 0) | (this.androidManifest != null ? ANDROID_MANIFEST_PRESENT : 0) | (this.packageManifest != null ? PACKAGE_MANIFEST_PRESENT : 0) | (this.installerPackage != null ? INSTALLER_PRESENT : 0));
        out.writeString(this.packageIdentifier);
        byte[] bArr = this.packageHash;
        if (bArr != null) {
            out.writeByteArray(bArr);
        }
        Signature[] signatureArr = this.signatures;
        if (signatureArr != null) {
            out.writeTypedArray(signatureArr, 0);
        }
        byte[] bArr2 = this.androidManifest;
        if (bArr2 != null) {
            out.writeByteArray(bArr2);
        }
        byte[] bArr3 = this.packageManifest;
        if (bArr3 != null) {
            out.writeByteArray(bArr3);
        }
        String str = this.installerPackage;
        if (str != null) {
            out.writeString(str);
        }
        out.writeLong(this.firstInstallTime);
        out.writeLong(this.lastUpdateTime);
        out.writeSerializable(this.sourceStampDisposition);
    }

    public static class Signature implements Parcelable {
        public static final Parcelable.Creator<Signature> CREATOR = new Parcelable.Creator<Signature>() {
            /* class com.oculus.os.PackageMetadata.Signature.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public Signature createFromParcel(Parcel in) {
                try {
                    return new Signature((Scheme) in.readSerializable(), (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(in.createByteArray())), in.createByteArray(), in.readInt(), in.readString(), in.createByteArray());
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }

            @Override // android.os.Parcelable.Creator
            public Signature[] newArray(int size) {
                return new Signature[size];
            }
        };
        public final int algorithm;
        public final String algorithmName;
        public final X509Certificate certificate;
        private int certificateSize = -1;
        public final byte[] digest;
        public final byte[] fingerprint;
        public final Scheme scheme;

        public enum Scheme {
            V1(1),
            V2(2),
            V3(3);
            
            public final int value;

            private Scheme(int value2) {
                this.value = value2;
            }

            public static Scheme fromValue(int value2) {
                if (value2 == 1) {
                    return V1;
                }
                if (value2 == 2) {
                    return V2;
                }
                if (value2 == 3) {
                    return V3;
                }
                throw new IllegalArgumentException("Invalid signature scheme version");
            }
        }

        public static final class Algorithm {
            public static final int DSA_SHA_256 = 769;
            public static final int ECDSA_SHA_256 = 513;
            public static final int ECDSA_SHA_512 = 514;
            public static final int RSASSA_PKCS1_5_SHA_256 = 259;
            public static final int RSASSA_PKCS1_5_SHA_512 = 260;
            public static final int RSASSA_PSS_SHA_256 = 257;
            public static final int RSASSA_PSS_SHA_512 = 258;

            public static String getName(int algorithm) {
                if (algorithm == 513) {
                    return "SHA256withECDSA";
                }
                if (algorithm == 514) {
                    return "SHA512withECDSA";
                }
                if (algorithm == 769) {
                    return "SHA256withDSA";
                }
                switch (algorithm) {
                    case 257:
                        return "SHA256withRSA";
                    case RSASSA_PSS_SHA_512 /*{ENCODED_INT: 258}*/:
                        return "SHA512withRSA";
                    case RSASSA_PKCS1_5_SHA_256 /*{ENCODED_INT: 259}*/:
                        return "SHA256withRSA";
                    case RSASSA_PKCS1_5_SHA_512 /*{ENCODED_INT: 260}*/:
                        return "SHA512withRSA";
                    default:
                        return "unrecognized";
                }
            }

            public static String extractDigestName(String algorithmName) {
                int withStart = algorithmName.indexOf("with");
                if (withStart == -1) {
                    return null;
                }
                String digestName = algorithmName.substring(0, withStart);
                if (!digestName.startsWith("SHA")) {
                    return digestName;
                }
                return "SHA-" + digestName.substring(3);
            }
        }

        public Signature(Scheme scheme2, X509Certificate certificate2, byte[] fingerprint2, int algorithm2, String algorithmName2, byte[] digest2) {
            this.scheme = scheme2;
            this.certificate = certificate2;
            this.fingerprint = fingerprint2;
            this.algorithm = algorithm2;
            this.algorithmName = algorithmName2;
            this.digest = digest2;
        }

        public int size() {
            int i = 0;
            if (this.certificateSize == -1) {
                try {
                    this.certificateSize = this.certificate != null ? this.certificate.getEncoded().length : 0;
                } catch (CertificateEncodingException e) {
                    this.certificateSize = 0;
                }
            }
            int i2 = this.certificateSize + 1;
            byte[] bArr = this.fingerprint;
            int length = i2 + (bArr != null ? bArr.length : 0);
            String str = this.algorithmName;
            int length2 = length + (str != null ? str.length() : 0);
            byte[] bArr2 = this.digest;
            if (bArr2 != null) {
                i = bArr2.length;
            }
            return length2 + i;
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof Signature)) {
                return false;
            }
            Signature o = (Signature) other;
            if (this.scheme != o.scheme || !Objects.equals(this.certificate, o.certificate) || !Arrays.equals(this.fingerprint, o.fingerprint) || this.algorithm != o.algorithm || !Objects.equals(this.algorithmName, o.algorithmName) || !Arrays.equals(this.digest, o.digest)) {
                return false;
            }
            return true;
        }

        public static final class Builder {
            private int algorithm = 0;
            private String algorithmName = null;
            private X509Certificate certificate = null;
            private byte[] digest = null;
            private byte[] fingerprint = null;
            private Scheme scheme = null;

            public Builder setScheme(Scheme scheme2) {
                this.scheme = scheme2;
                return this;
            }

            public Builder setCertificate(X509Certificate certificate2) {
                this.certificate = certificate2;
                return this;
            }

            public Builder setCertificateFingerprint(byte[] fingerprint2) {
                this.fingerprint = fingerprint2;
                return this;
            }

            public Builder setAlgorithm(int algorithm2) {
                this.algorithm = algorithm2;
                return this;
            }

            public Builder setAlgorithmName(String algorithmName2) {
                this.algorithmName = algorithmName2;
                return this;
            }

            public Builder setDigest(byte[] digest2) {
                this.digest = digest2;
                return this;
            }

            public Signature build() {
                return new Signature(this.scheme, this.certificate, this.fingerprint, this.algorithm, this.algorithmName, this.digest);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            try {
                out.writeByteArray(this.certificate.getEncoded());
                out.writeSerializable(this.scheme);
                out.writeByteArray(this.fingerprint);
                out.writeInt(this.algorithm);
                out.writeString(this.algorithmName);
                out.writeByteArray(this.digest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
