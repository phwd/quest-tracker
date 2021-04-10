package com.facebook.rti.mqtt.protocol.messages;

import X.AnonymousClass0W7;
import X.C02210Zr;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import javax.annotation.Nullable;

public final class SubscribeTopic implements Parcelable {
    public static final Parcelable.Creator<SubscribeTopic> CREATOR = new C02210Zr();
    public final int A00;
    public final String A01;
    @Nullable
    public volatile TopicExtraInfo A02;

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SubscribeTopic subscribeTopic = (SubscribeTopic) obj;
            if (!AnonymousClass0W7.A00(this.A01, subscribeTopic.A01) || this.A00 != subscribeTopic.A00) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A01, Integer.valueOf(this.A00)});
    }

    public final String toString() {
        return String.format("{ name='%s', qos='%s', extra='%s' }", this.A01, Integer.valueOf(this.A00), null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.A01);
        parcel.writeInt(this.A00);
        parcel.writeParcelable(null, i);
    }

    public SubscribeTopic(Parcel parcel) {
        this.A01 = parcel.readString();
        this.A00 = parcel.readInt();
        parcel.readParcelable(TopicExtraInfo.class.getClassLoader());
    }

    public SubscribeTopic(String str, int i) {
        if (str != null) {
            this.A01 = str;
            Integer valueOf = Integer.valueOf(i);
            if (valueOf != null) {
                this.A00 = valueOf.intValue();
                return;
            }
        }
        throw null;
    }
}
