package X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaBrowserCompat$MediaItem;

/* renamed from: X.0Q  reason: invalid class name */
public class AnonymousClass0Q implements Parcelable.Creator<MediaBrowserCompat$MediaItem> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaBrowserCompat$MediaItem createFromParcel(Parcel parcel) {
        return new MediaBrowserCompat$MediaItem(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaBrowserCompat$MediaItem[] newArray(int i) {
        return new MediaBrowserCompat$MediaItem[i];
    }
}
