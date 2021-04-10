package android.support.v4.media.session;

import X.AnonymousClass0y;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;

@SuppressLint({"BanParcelableUsage"})
public final class MediaSessionCompat$QueueItem implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$QueueItem> CREATOR = new AnonymousClass0y();
    public final long A00;
    public final MediaDescriptionCompat A01;

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MediaSession.QueueItem {Description=");
        sb.append(this.A01);
        sb.append(", Id=");
        sb.append(this.A00);
        sb.append(" }");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        this.A01.writeToParcel(parcel, i);
        parcel.writeLong(this.A00);
    }

    public MediaSessionCompat$QueueItem(Parcel parcel) {
        this.A01 = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        this.A00 = parcel.readLong();
    }
}
