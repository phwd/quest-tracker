package android.support.v4.media.session;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import java.util.ArrayList;
import java.util.List;

public final class MediaSessionCompat$QueueItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        /* class android.support.v4.media.session.MediaSessionCompat$QueueItem.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public MediaSessionCompat$QueueItem createFromParcel(Parcel parcel) {
            return new MediaSessionCompat$QueueItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public MediaSessionCompat$QueueItem[] newArray(int i) {
            return new MediaSessionCompat$QueueItem[i];
        }
    };
    private final MediaDescriptionCompat mDescription;
    private final long mId;
    private Object mItem;

    public int describeContents() {
        return 0;
    }

    private MediaSessionCompat$QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j) {
        if (mediaDescriptionCompat == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        } else if (j != -1) {
            this.mDescription = mediaDescriptionCompat;
            this.mId = j;
            this.mItem = obj;
        } else {
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
        }
    }

    MediaSessionCompat$QueueItem(Parcel parcel) {
        this.mDescription = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        this.mId = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.mDescription.writeToParcel(parcel, i);
        parcel.writeLong(this.mId);
    }

    public static MediaSessionCompat$QueueItem fromQueueItem(Object obj) {
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        return new MediaSessionCompat$QueueItem(obj, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21$QueueItem.getDescription(obj)), MediaSessionCompatApi21$QueueItem.getQueueId(obj));
    }

    public static List fromQueueItemList(List list) {
        if (list == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            arrayList.add(fromQueueItem(obj));
        }
        return arrayList;
    }

    @Override // java.lang.Object
    public String toString() {
        return "MediaSession.QueueItem {Description=" + this.mDescription + ", Id=" + this.mId + " }";
    }
}
