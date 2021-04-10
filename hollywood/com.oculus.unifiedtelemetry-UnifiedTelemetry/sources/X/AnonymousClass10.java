package X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper;

/* renamed from: X.10  reason: invalid class name */
public class AnonymousClass10 implements Parcelable.Creator<MediaSessionCompat$ResultReceiverWrapper> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaSessionCompat$ResultReceiverWrapper createFromParcel(Parcel parcel) {
        return new MediaSessionCompat$ResultReceiverWrapper(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaSessionCompat$ResultReceiverWrapper[] newArray(int i) {
        return new MediaSessionCompat$ResultReceiverWrapper[i];
    }
}
