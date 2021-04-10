package android.support.v4.media;

import X.AnonymousClass014;
import X.C000000a;
import X.C07490ss;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
public final class MediaMetadataCompat implements Parcelable {
    public static final C07490ss<String, Integer> A01;
    public static final String[] A02 = {"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
    public static final String[] A03 = {"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
    public static final String[] A04 = {"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR = new C000000a();
    public final Bundle A00;

    public final int describeContents() {
        return 0;
    }

    static {
        C07490ss<String, Integer> r1 = new C07490ss<>();
        A01 = r1;
        r1.put("android.media.metadata.TITLE", 1);
        C07490ss<String, Integer> r14 = A01;
        r14.put("android.media.metadata.ARTIST", 1);
        r14.put("android.media.metadata.DURATION", 0);
        r14.put("android.media.metadata.ALBUM", 1);
        r14.put("android.media.metadata.AUTHOR", 1);
        r14.put("android.media.metadata.WRITER", 1);
        r14.put("android.media.metadata.COMPOSER", 1);
        r14.put("android.media.metadata.COMPILATION", 1);
        r14.put("android.media.metadata.DATE", 1);
        r14.put("android.media.metadata.YEAR", 0);
        r14.put("android.media.metadata.GENRE", 1);
        r14.put("android.media.metadata.TRACK_NUMBER", 0);
        r14.put("android.media.metadata.NUM_TRACKS", 0);
        r14.put("android.media.metadata.DISC_NUMBER", 0);
        r14.put("android.media.metadata.ALBUM_ARTIST", 1);
        r14.put("android.media.metadata.ART", 2);
        r14.put("android.media.metadata.ART_URI", 1);
        r14.put("android.media.metadata.ALBUM_ART", 2);
        r14.put("android.media.metadata.ALBUM_ART_URI", 1);
        r14.put("android.media.metadata.USER_RATING", 3);
        r14.put("android.media.metadata.RATING", 3);
        r14.put("android.media.metadata.DISPLAY_TITLE", 1);
        r14.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        r14.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        r14.put("android.media.metadata.DISPLAY_ICON", 2);
        r14.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        r14.put("android.media.metadata.MEDIA_ID", 1);
        r14.put("android.media.metadata.BT_FOLDER_TYPE", 0);
        r14.put("android.media.metadata.MEDIA_URI", 1);
        r14.put("android.media.metadata.ADVERTISEMENT", 0);
        r14.put("android.media.metadata.DOWNLOAD_STATUS", 0);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.A00);
    }

    public MediaMetadataCompat(Parcel parcel) {
        this.A00 = parcel.readBundle(AnonymousClass014.class.getClassLoader());
    }
}
