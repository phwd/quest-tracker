package X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat$QueueItem;

/* renamed from: X.0y  reason: invalid class name */
public class AnonymousClass0y implements Parcelable.Creator<MediaSessionCompat$QueueItem> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaSessionCompat$QueueItem createFromParcel(Parcel parcel) {
        return new MediaSessionCompat$QueueItem(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaSessionCompat$QueueItem[] newArray(int i) {
        return new MediaSessionCompat$QueueItem[i];
    }
}
