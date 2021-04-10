package android.support.v4.media;

import X.AnonymousClass14;
import X.C00000a;
import X.C0298aW;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
public final class MediaMetadataCompat implements Parcelable {
    public static final C0298aW<String, Integer> A01;
    public static final String[] A02 = {"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
    public static final String[] A03 = {"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
    public static final String[] A04 = {"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR = new C00000a();
    public final Bundle A00;

    public final int describeContents() {
        return 0;
    }

    static {
        C0298aW<String, Integer> aWVar = new C0298aW<>();
        A01 = aWVar;
        aWVar.put("android.media.metadata.TITLE", 1);
        C0298aW<String, Integer> aWVar2 = A01;
        aWVar2.put("android.media.metadata.ARTIST", 1);
        aWVar2.put("android.media.metadata.DURATION", 0);
        aWVar2.put("android.media.metadata.ALBUM", 1);
        aWVar2.put("android.media.metadata.AUTHOR", 1);
        aWVar2.put("android.media.metadata.WRITER", 1);
        aWVar2.put("android.media.metadata.COMPOSER", 1);
        aWVar2.put("android.media.metadata.COMPILATION", 1);
        aWVar2.put("android.media.metadata.DATE", 1);
        aWVar2.put("android.media.metadata.YEAR", 0);
        aWVar2.put("android.media.metadata.GENRE", 1);
        aWVar2.put("android.media.metadata.TRACK_NUMBER", 0);
        aWVar2.put("android.media.metadata.NUM_TRACKS", 0);
        aWVar2.put("android.media.metadata.DISC_NUMBER", 0);
        aWVar2.put("android.media.metadata.ALBUM_ARTIST", 1);
        aWVar2.put("android.media.metadata.ART", 2);
        aWVar2.put("android.media.metadata.ART_URI", 1);
        aWVar2.put("android.media.metadata.ALBUM_ART", 2);
        aWVar2.put("android.media.metadata.ALBUM_ART_URI", 1);
        aWVar2.put("android.media.metadata.USER_RATING", 3);
        aWVar2.put("android.media.metadata.RATING", 3);
        aWVar2.put("android.media.metadata.DISPLAY_TITLE", 1);
        aWVar2.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        aWVar2.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        aWVar2.put("android.media.metadata.DISPLAY_ICON", 2);
        aWVar2.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        aWVar2.put("android.media.metadata.MEDIA_ID", 1);
        aWVar2.put("android.media.metadata.BT_FOLDER_TYPE", 0);
        aWVar2.put("android.media.metadata.MEDIA_URI", 1);
        aWVar2.put("android.media.metadata.ADVERTISEMENT", 0);
        aWVar2.put("android.media.metadata.DOWNLOAD_STATUS", 0);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.A00);
    }

    public MediaMetadataCompat(Parcel parcel) {
        this.A00 = parcel.readBundle(AnonymousClass14.class.getClassLoader());
    }
}
