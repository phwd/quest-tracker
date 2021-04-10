package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;

/* renamed from: X.10s  reason: invalid class name */
public class AnonymousClass10s implements Parcelable.Creator<SubscribeTopic> {
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
