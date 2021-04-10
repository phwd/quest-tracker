package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.cert.Certificate;
import java.util.List;

public final class Signer implements Parcelable {
    public static final Parcelable.Creator<Signer> CREATOR = null;
    public final Certificate certificate = null;
    public final List<Digest> digests = null;

    public static final class Digest implements Parcelable {
        public static final Parcelable.Creator<Digest> CREATOR = null;
        public final String algorithm;
        public final byte[] digest;

        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        public boolean equals(Object obj) {
            throw new RuntimeException("Stub!");
        }

        public int hashCode() {
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel parcel, int i) {
            throw new RuntimeException("Stub!");
        }

        public Digest(Parcel parcel) {
            this.algorithm = null;
            this.digest = new byte[0];
            throw new RuntimeException("Stub!");
        }

        public Digest(String str, byte[] bArr) {
            this.algorithm = null;
            this.digest = new byte[0];
            throw new RuntimeException("Stub!");
        }
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(Object obj) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }

    public Signer(Certificate certificate2, List<Digest> list) {
        throw new RuntimeException("Stub!");
    }
}
