package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.ByteArrayInputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Signer implements Parcelable {
    public static final Parcelable.Creator<Signer> CREATOR = new Parcelable.Creator<Signer>() {
        /* class com.oculus.license.Signer.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Signer createFromParcel(Parcel in) {
            try {
                return new Signer(CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(in.createByteArray())), in.createTypedArrayList(Digest.CREATOR));
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

        @Override // android.os.Parcelable.Creator
        public Signer[] newArray(int size) {
            return new Signer[size];
        }
    };
    public final Certificate certificate;
    private int certificateSize = -1;
    public final List<Digest> digests;

    public Signer(Certificate certificate2, List<Digest> digests2) {
        this.certificate = certificate2;
        this.digests = Collections.unmodifiableList(digests2);
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
        List<Digest> list = this.digests;
        if (list != null) {
            i = list.stream().mapToInt($$Lambda$jztWNyFcgKp5r5saRLEfaCEv90.INSTANCE).sum();
        }
        return i2 + i;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Signer)) {
            return false;
        }
        Signer o = (Signer) other;
        if (!Objects.equals(this.certificate, o.certificate) || !Objects.equals(this.digests, o.digests)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.certificate, this.digests);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        try {
            out.writeByteArray(this.certificate.getEncoded());
            out.writeTypedList(this.digests);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final class Digest implements Parcelable {
        public static final Parcelable.Creator<Digest> CREATOR = new Parcelable.Creator<Digest>() {
            /* class com.oculus.license.Signer.Digest.AnonymousClass1 */

            @Override // android.os.Parcelable.Creator
            public Digest createFromParcel(Parcel in) {
                return new Digest(in);
            }

            @Override // android.os.Parcelable.Creator
            public Digest[] newArray(int size) {
                return new Digest[size];
            }
        };
        public final String algorithm;
        public final byte[] digest;

        public Digest(String algorithm2, byte[] digest2) {
            this.algorithm = algorithm2;
            this.digest = digest2;
        }

        public Digest(Parcel in) {
            this(in.readString(), in.createByteArray());
        }

        public int size() {
            String str = this.algorithm;
            int i = 0;
            int length = (str != null ? str.length() : 0) + 1;
            byte[] bArr = this.digest;
            if (bArr != null) {
                i = bArr.length;
            }
            return length + i;
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof Digest)) {
                return false;
            }
            Digest o = (Digest) other;
            if (!Objects.equals(this.algorithm, o.algorithm) || !Arrays.equals(this.digest, o.digest)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(this.algorithm, Integer.valueOf(Arrays.hashCode(this.digest)));
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeString(this.algorithm);
            out.writeByteArray(this.digest);
        }
    }
}
