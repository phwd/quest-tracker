package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Objects;

public final class PackageMetadata implements Parcelable {
    private static int ANDROID_MANIFEST_PRESENT = 4;
    public static final Parcelable.Creator<PackageMetadata> CREATOR = new Parcelable.Creator<PackageMetadata>() {
        /* class com.oculus.os.PackageMetadata.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public PackageMetadata createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            Builder builder = new Builder();
            builder.setPackageIdentifier(parcel.readString());
            if ((PackageMetadata.PACKAGE_HASH_PRESENT & readInt) != 0) {
                builder.setPackageHash(parcel.createByteArray());
            }
            if ((PackageMetadata.SIGNATURES_PRESENT & readInt) != 0) {
                builder.setSignatures((Signature[]) parcel.createTypedArray(Signature.CREATOR));
            }
            if ((PackageMetadata.ANDROID_MANIFEST_PRESENT & readInt) != 0) {
                builder.setAndroidManifest(parcel.createByteArray());
            }
            if ((PackageMetadata.PACKAGE_MANIFEST_PRESENT & readInt) != 0) {
                builder.setPackageManifest(parcel.createByteArray());
            }
            if ((readInt & PackageMetadata.INSTALLER_PRESENT) != 0) {
                builder.setInstallerPackage(parcel.readString());
            }
            builder.setFirstInstallTime(parcel.readLong());
            builder.setLastUpdateTime(parcel.readLong());
            builder.setSourceStampDisposition((SourceStampDisposition) parcel.readSerializable());
            return builder.build();
        }

        @Override // android.os.Parcelable.Creator
        public PackageMetadata[] newArray(int i) {
            return new PackageMetadata[i];
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

    public int describeContents() {
        return 0;
    }

    public enum SourceStampDisposition {
        NOT_TESTED(-3),
        NOT_SUPPORTED(-2),
        INVALID(-1),
        NOT_PRESENT(0),
        VERIFIED(1);
        
        public final int value;

        private SourceStampDisposition(int i) {
            this.value = i;
        }
    }

    public PackageMetadata(String str, byte[] bArr, Signature[] signatureArr, byte[] bArr2, byte[] bArr3, String str2, long j, long j2, SourceStampDisposition sourceStampDisposition2) {
        this.packageIdentifier = str;
        this.packageHash = bArr;
        this.signatures = signatureArr;
        this.androidManifest = bArr2;
        this.packageManifest = bArr3;
        this.installerPackage = str2;
        this.firstInstallTime = j;
        this.lastUpdateTime = j2;
        this.sourceStampDisposition = sourceStampDisposition2;
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

        public Builder setPackageIdentifier(String str) {
            this.packageIdentifier = str;
            return this;
        }

        public Builder setPackageHash(byte[] bArr) {
            this.packageHash = bArr;
            return this;
        }

        public Builder setSignatures(Signature[] signatureArr) {
            this.signatures = signatureArr;
            return this;
        }

        public Builder setAndroidManifest(byte[] bArr) {
            this.androidManifest = bArr;
            return this;
        }

        public Builder setPackageManifest(byte[] bArr) {
            this.packageManifest = bArr;
            return this;
        }

        public Builder setInstallerPackage(String str) {
            this.installerPackage = str;
            return this;
        }

        public Builder setFirstInstallTime(long j) {
            this.firstInstallTime = j;
            return this;
        }

        public Builder setLastUpdateTime(long j) {
            this.lastUpdateTime = j;
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

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PackageMetadata)) {
            return false;
        }
        PackageMetadata packageMetadata = (PackageMetadata) obj;
        return Objects.equals(this.packageIdentifier, packageMetadata.packageIdentifier) && Arrays.equals(this.packageHash, packageMetadata.packageHash) && Arrays.equals(this.signatures, packageMetadata.signatures) && Arrays.equals(this.androidManifest, packageMetadata.androidManifest) && Arrays.equals(this.packageManifest, packageMetadata.packageManifest) && Objects.equals(this.installerPackage, packageMetadata.installerPackage) && this.firstInstallTime == packageMetadata.firstInstallTime && this.lastUpdateTime == packageMetadata.lastUpdateTime && this.sourceStampDisposition == packageMetadata.sourceStampDisposition;
    }

    public int hashCode() {
        return Objects.hash(this.packageIdentifier);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt((this.packageHash != null ? PACKAGE_HASH_PRESENT : 0) | (this.signatures != null ? SIGNATURES_PRESENT : 0) | (this.androidManifest != null ? ANDROID_MANIFEST_PRESENT : 0) | (this.packageManifest != null ? PACKAGE_MANIFEST_PRESENT : 0) | (this.installerPackage != null ? INSTALLER_PRESENT : 0));
        parcel.writeString(this.packageIdentifier);
        byte[] bArr = this.packageHash;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
        Signature[] signatureArr = this.signatures;
        if (signatureArr != null) {
            parcel.writeTypedArray(signatureArr, 0);
        }
        byte[] bArr2 = this.androidManifest;
        if (bArr2 != null) {
            parcel.writeByteArray(bArr2);
        }
        byte[] bArr3 = this.packageManifest;
        if (bArr3 != null) {
            parcel.writeByteArray(bArr3);
        }
        String str = this.installerPackage;
        if (str != null) {
            parcel.writeString(str);
        }
        parcel.writeLong(this.firstInstallTime);
        parcel.writeLong(this.lastUpdateTime);
        parcel.writeSerializable(this.sourceStampDisposition);
    }

    public static class Signature implements Parcelable {
        public static final Parcelable.Creator<Signature> CREATOR = new Parcelable.Creator<Signature>() {
            /* class com.oculus.os.PackageMetadata.Signature.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public Signature createFromParcel(Parcel parcel) {
                try {
                    return new Signature((Scheme) parcel.readSerializable(), (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(parcel.createByteArray())), parcel.createByteArray(), parcel.readInt(), parcel.readString(), parcel.createByteArray());
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }

            @Override // android.os.Parcelable.Creator
            public Signature[] newArray(int i) {
                return new Signature[i];
            }
        };
        public final int algorithm;
        public final String algorithmName;
        public final X509Certificate certificate;
        private int certificateSize = -1;
        public final byte[] digest;
        public final byte[] fingerprint;
        public final Scheme scheme;

        public int describeContents() {
            return 0;
        }

        public enum Scheme {
            V1(1),
            V2(2),
            V3(3);
            
            public final int value;

            private Scheme(int i) {
                this.value = i;
            }
        }

        public Signature(Scheme scheme2, X509Certificate x509Certificate, byte[] bArr, int i, String str, byte[] bArr2) {
            this.scheme = scheme2;
            this.certificate = x509Certificate;
            this.fingerprint = bArr;
            this.algorithm = i;
            this.algorithmName = str;
            this.digest = bArr2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Signature)) {
                return false;
            }
            Signature signature = (Signature) obj;
            return this.scheme == signature.scheme && Objects.equals(this.certificate, signature.certificate) && Arrays.equals(this.fingerprint, signature.fingerprint) && this.algorithm == signature.algorithm && Objects.equals(this.algorithmName, signature.algorithmName) && Arrays.equals(this.digest, signature.digest);
        }

        public void writeToParcel(Parcel parcel, int i) {
            try {
                parcel.writeByteArray(this.certificate.getEncoded());
                parcel.writeSerializable(this.scheme);
                parcel.writeByteArray(this.fingerprint);
                parcel.writeInt(this.algorithm);
                parcel.writeString(this.algorithmName);
                parcel.writeByteArray(this.digest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
