package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MediaSessionCompat$QueueItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0388Gh0();
    public final MediaDescriptionCompat F;
    public final long G;

    public MediaSessionCompat$QueueItem(MediaSession.QueueItem queueItem, MediaDescriptionCompat mediaDescriptionCompat, long j) {
        if (mediaDescriptionCompat == null) {
            throw new IllegalArgumentException("Description cannot be null");
        } else if (j != -1) {
            this.F = mediaDescriptionCompat;
            this.G = j;
        } else {
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
        }
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("MediaSession.QueueItem {Description=");
        i.append(this.F);
        i.append(", Id=");
        i.append(this.G);
        i.append(" }");
        return i.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.F.writeToParcel(parcel, i);
        parcel.writeLong(this.G);
    }

    public MediaSessionCompat$QueueItem(Parcel parcel) {
        this.F = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        this.G = parcel.readLong();
    }
}
