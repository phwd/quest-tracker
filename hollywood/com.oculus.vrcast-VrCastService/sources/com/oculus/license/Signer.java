package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.ByteArrayInputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Signer implements Parcelable {
    public static final Parcelable.Creator<Signer> CREATOR = new Parcelable.Creator<Signer>() {
        /* class com.oculus.license.Signer.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Signer createFromParcel(Parcel parcel) {
            try {
                return new Signer(CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(parcel.createByteArray())), parcel.createTypedArrayList(Digest.CREATOR));
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

        @Override // android.os.Parcelable.Creator
        public Signer[] newArray(int i) {
            return new Signer[i];
        }
    };
    public final Certificate certificate;
    private int certificateSize = -1;
    public final List<Digest> digests;

    public int describeContents() {
        return 0;
    }

    public Signer(Certificate certificate2, List<Digest> list) {
        this.certificate = certificate2;
        this.digests = Collections.unmodifiableList(list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Signer)) {
            return false;
        }
        Signer signer = (Signer) obj;
        return Objects.equals(this.certificate, signer.certificate) && Objects.equals(this.digests, signer.digests);
    }

    public int hashCode() {
        return Objects.hash(this.certificate, this.digests);
    }

    public void writeToParcel(Parcel parcel, int i) {
        try {
            parcel.writeByteArray(this.certificate.getEncoded());
            parcel.writeTypedList(this.digests);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final class Digest implements Parcelable {
        public static final Parcelable.Creator<Digest> CREATOR = new Parcelable.Creator<Digest>() {
            /* class com.oculus.license.Signer.Digest.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public Digest createFromParcel(Parcel parcel) {
                return new Digest(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public Digest[] newArray(int i) {
                return new Digest[i];
            }
        };
        public final String algorithm;
        public final byte[] digest;

        public int describeContents() {
            return 0;
        }

        public Digest(String str, byte[] bArr) {
            this.algorithm = str;
            this.digest = bArr;
        }

        public Digest(Parcel parcel) {
            this(parcel.readString(), parcel.createByteArray());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Digest)) {
                return false;
            }
            Digest digest2 = (Digest) obj;
            return Objects.equals(this.algorithm, digest2.algorithm) && Arrays.equals(this.digest, digest2.digest);
        }

        public int hashCode() {
            return Objects.hash(this.algorithm, Integer.valueOf(Arrays.hashCode(this.digest)));
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.algorithm);
            parcel.writeByteArray(this.digest);
        }
    }
}
