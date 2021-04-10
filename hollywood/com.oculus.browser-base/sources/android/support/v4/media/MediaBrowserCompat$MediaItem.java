package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaBrowserCompat$MediaItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C4601rd0();
    public final int F;
    public final MediaDescriptionCompat G;

    public MediaBrowserCompat$MediaItem(Parcel parcel) {
        this.F = parcel.readInt();
        this.G = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "MediaItem{" + "mFlags=" + this.F + ", mDescription=" + this.G + '}';
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.F);
        this.G.writeToParcel(parcel, i);
    }
}
