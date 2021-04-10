package X;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import android.util.Log;

/* renamed from: X.00X  reason: invalid class name */
public class AnonymousClass00X implements Parcelable.Creator<MediaDescriptionCompat> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaDescriptionCompat createFromParcel(Parcel parcel) {
        Uri uri;
        Object createFromParcel = MediaDescription.CREATOR.createFromParcel(parcel);
        Bundle bundle = null;
        if (createFromParcel == null) {
            return null;
        }
        MediaDescription mediaDescription = (MediaDescription) createFromParcel;
        String mediaId = mediaDescription.getMediaId();
        CharSequence title = mediaDescription.getTitle();
        CharSequence subtitle = mediaDescription.getSubtitle();
        CharSequence description = mediaDescription.getDescription();
        Bitmap iconBitmap = mediaDescription.getIconBitmap();
        Uri iconUri = mediaDescription.getIconUri();
        Bundle extras = mediaDescription.getExtras();
        if (extras != null) {
            extras.setClassLoader(AnonymousClass014.class.getClassLoader());
            try {
                extras.isEmpty();
            } catch (BadParcelableException unused) {
                Log.e("MediaSessionCompat", "Could not unparcel the data.");
                extras = null;
            }
        }
        if (extras != null) {
            uri = (Uri) extras.getParcelable("android.support.v4.media.description.MEDIA_URI");
            if (uri != null) {
                if (!extras.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") || extras.size() != 2) {
                    extras.remove("android.support.v4.media.description.MEDIA_URI");
                    extras.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
                }
            }
            bundle = extras;
        } else {
            uri = null;
            bundle = extras;
        }
        if (uri == null) {
            uri = mediaDescription.getMediaUri();
        }
        MediaDescriptionCompat mediaDescriptionCompat = new MediaDescriptionCompat(mediaId, title, subtitle, description, iconBitmap, iconUri, bundle, uri);
        mediaDescriptionCompat.A00 = mediaDescription;
        return mediaDescriptionCompat;
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final MediaDescriptionCompat[] newArray(int i) {
        return new MediaDescriptionCompat[i];
    }
}
