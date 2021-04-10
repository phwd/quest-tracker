package android.support.v4.media;

import android.media.MediaMetadata;
import android.os.Parcel;

/* access modifiers changed from: package-private */
public class MediaMetadataCompatApi21 {
    public static void writeToParcel(Object obj, Parcel parcel, int i) {
        ((MediaMetadata) obj).writeToParcel(parcel, i);
    }
}
