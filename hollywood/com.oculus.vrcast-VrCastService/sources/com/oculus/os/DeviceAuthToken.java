package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class DeviceAuthToken implements Parcelable {
    public static final Parcelable.Creator<DeviceAuthToken> CREATOR = new Creator();
    private final long mExpirationTime;
    private final String mValue;

    public int describeContents() {
        return 0;
    }

    private DeviceAuthToken(String str, long j) {
        if (str != null) {
            this.mValue = str;
            this.mExpirationTime = j;
            return;
        }
        throw new NullPointerException("value is null");
    }

    public static DeviceAuthToken of(String str, long j) {
        return new DeviceAuthToken(str, j);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mValue);
        parcel.writeLong(this.mExpirationTime);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceAuthToken)) {
            return false;
        }
        DeviceAuthToken deviceAuthToken = (DeviceAuthToken) obj;
        if (!Objects.equals(this.mValue, deviceAuthToken.mValue) || this.mExpirationTime != deviceAuthToken.mExpirationTime) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.mValue, Long.valueOf(this.mExpirationTime));
    }

    public String toString() {
        return "{value: x, expirationTime: " + this.mExpirationTime + "}";
    }

    private static final class Creator implements Parcelable.Creator<DeviceAuthToken> {
        private Creator() {
        }

        @Override // android.os.Parcelable.Creator
        public DeviceAuthToken createFromParcel(Parcel parcel) {
            return DeviceAuthToken.of(parcel.readString(), parcel.readLong());
        }

        @Override // android.os.Parcelable.Creator
        public DeviceAuthToken[] newArray(int i) {
            return new DeviceAuthToken[i];
        }
    }
}
