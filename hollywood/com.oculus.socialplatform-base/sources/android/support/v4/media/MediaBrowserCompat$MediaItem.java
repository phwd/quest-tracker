package android.support.v4.media;

import X.AnonymousClass00Q;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

@SuppressLint({"BanParcelableUsage"})
public class MediaBrowserCompat$MediaItem implements Parcelable {
    public static final Parcelable.Creator<MediaBrowserCompat$MediaItem> CREATOR = new AnonymousClass00Q();
    public final int A00;
    public final MediaDescriptionCompat A01;

    public final int describeContents() {
        return 0;
    }

    @NonNull
    public final String toString() {
        StringBuilder sb = new StringBuilder("MediaItem{");
        sb.append("mFlags=");
        sb.append(this.A00);
        sb.append(", mDescription=");
        sb.append(this.A01);
        sb.append('}');
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.A00);
        this.A01.writeToParcel(parcel, i);
    }

    public MediaBrowserCompat$MediaItem(Parcel parcel) {
        this.A00 = parcel.readInt();
        this.A01 = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
    }
}
