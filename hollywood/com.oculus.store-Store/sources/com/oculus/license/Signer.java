package com.oculus.license;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.cert.Certificate;
import java.util.List;

public final class Signer implements Parcelable {
    public static final Parcelable.Creator<Signer> CREATOR = null;
    public final Certificate certificate = null;
    public final List<Digest> digests = null;

    public Signer(Certificate certificate2, List<Digest> list) {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(Object other) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel out, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static final class Digest implements Parcelable {
        public static final Parcelable.Creator<Digest> CREATOR = null;
        public final String algorithm;
        public final byte[] digest;

        public Digest(String algorithm2, byte[] digest2) {
            this.algorithm = null;
            this.digest = new byte[0];
            throw new RuntimeException("Stub!");
        }

        public Digest(Parcel in) {
            this.algorithm = null;
            this.digest = new byte[0];
            throw new RuntimeException("Stub!");
        }

        public boolean equals(Object other) {
            throw new RuntimeException("Stub!");
        }

        public int hashCode() {
            throw new RuntimeException("Stub!");
        }

        public int describeContents() {
            throw new RuntimeException("Stub!");
        }

        public void writeToParcel(Parcel out, int flags) {
            throw new RuntimeException("Stub!");
        }
    }
}
