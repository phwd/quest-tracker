package com.oculus.os;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class DeviceAuthToken implements Parcelable {
    public static final Parcelable.Creator<DeviceAuthToken> CREATOR = new Creator();
    private final long mExpirationTime;
    private final String mValue;

    private DeviceAuthToken(String value, long expirationTime) {
        if (value != null) {
            this.mValue = value;
            this.mExpirationTime = expirationTime;
            return;
        }
        throw new NullPointerException("value is null");
    }

    public static DeviceAuthToken of(String value, long expirationTime) {
        return new DeviceAuthToken(value, expirationTime);
    }

    public String value() {
        return this.mValue;
    }

    public long expirationTime() {
        return this.mExpirationTime;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mValue);
        dest.writeLong(this.mExpirationTime);
    }

    public boolean equals(Object other) {
        if (!(other instanceof DeviceAuthToken)) {
            return false;
        }
        DeviceAuthToken o = (DeviceAuthToken) other;
        if (!Objects.equals(this.mValue, o.mValue) || this.mExpirationTime != o.mExpirationTime) {
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
        public DeviceAuthToken createFromParcel(Parcel source) {
            return DeviceAuthToken.of(source.readString(), source.readLong());
        }

        @Override // android.os.Parcelable.Creator
        public DeviceAuthToken[] newArray(int size) {
            return new DeviceAuthToken[size];
        }
    }
}
