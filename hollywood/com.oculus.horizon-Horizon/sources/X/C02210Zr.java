package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;

/* renamed from: X.0Zr  reason: invalid class name and case insensitive filesystem */
public class C02210Zr implements Parcelable.Creator<SubscribeTopic> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final SubscribeTopic createFromParcel(Parcel parcel) {
        return new SubscribeTopic(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final SubscribeTopic[] newArray(int i) {
        return new SubscribeTopic[i];
    }
}
