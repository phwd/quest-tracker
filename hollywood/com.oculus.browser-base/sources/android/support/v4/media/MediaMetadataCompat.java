package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C4434qe0();
    public static final C4931ta F;
    public static final String[] G = {"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
    public static final String[] H = {"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
    public static final String[] I = {"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};

    /* renamed from: J  reason: collision with root package name */
    public final Bundle f9451J;
    public MediaMetadata K;
    public MediaDescriptionCompat L;

    static {
        C4931ta taVar = new C4931ta();
        F = taVar;
        taVar.put("android.media.metadata.TITLE", 1);
        taVar.put("android.media.metadata.ARTIST", 1);
        taVar.put("android.media.metadata.DURATION", 0);
        taVar.put("android.media.metadata.ALBUM", 1);
        taVar.put("android.media.metadata.AUTHOR", 1);
        taVar.put("android.media.metadata.WRITER", 1);
        taVar.put("android.media.metadata.COMPOSER", 1);
        taVar.put("android.media.metadata.COMPILATION", 1);
        taVar.put("android.media.metadata.DATE", 1);
        taVar.put("android.media.metadata.YEAR", 0);
        taVar.put("android.media.metadata.GENRE", 1);
        taVar.put("android.media.metadata.TRACK_NUMBER", 0);
        taVar.put("android.media.metadata.NUM_TRACKS", 0);
        taVar.put("android.media.metadata.DISC_NUMBER", 0);
        taVar.put("android.media.metadata.ALBUM_ARTIST", 1);
        taVar.put("android.media.metadata.ART", 2);
        taVar.put("android.media.metadata.ART_URI", 1);
        taVar.put("android.media.metadata.ALBUM_ART", 2);
        taVar.put("android.media.metadata.ALBUM_ART_URI", 1);
        taVar.put("android.media.metadata.USER_RATING", 3);
        taVar.put("android.media.metadata.RATING", 3);
        taVar.put("android.media.metadata.DISPLAY_TITLE", 1);
        taVar.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        taVar.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        taVar.put("android.media.metadata.DISPLAY_ICON", 2);
        taVar.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        taVar.put("android.media.metadata.MEDIA_ID", 1);
        taVar.put("android.media.metadata.BT_FOLDER_TYPE", 0);
        taVar.put("android.media.metadata.MEDIA_URI", 1);
        taVar.put("android.media.metadata.ADVERTISEMENT", 0);
        taVar.put("android.media.metadata.DOWNLOAD_STATUS", 0);
    }

    public MediaMetadataCompat(Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        this.f9451J = bundle2;
        C0571Jh0.a(bundle2);
    }

    public static MediaMetadataCompat b(Object obj) {
        if (obj == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        mediaMetadata.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        MediaMetadataCompat mediaMetadataCompat = (MediaMetadataCompat) CREATOR.createFromParcel(obtain);
        obtain.recycle();
        mediaMetadataCompat.K = mediaMetadata;
        return mediaMetadataCompat;
    }

    public MediaDescriptionCompat c() {
        Bundle bundle;
        Bitmap bitmap;
        Uri uri;
        Bitmap bitmap2;
        MediaDescriptionCompat mediaDescriptionCompat = this.L;
        if (mediaDescriptionCompat != null) {
            return mediaDescriptionCompat;
        }
        String h = h("android.media.metadata.MEDIA_ID");
        CharSequence[] charSequenceArr = new CharSequence[3];
        CharSequence charSequence = this.f9451J.getCharSequence("android.media.metadata.DISPLAY_TITLE");
        if (TextUtils.isEmpty(charSequence)) {
            int i = 0;
            int i2 = 0;
            while (i < 3) {
                String[] strArr = G;
                if (i2 >= strArr.length) {
                    break;
                }
                int i3 = i2 + 1;
                CharSequence i4 = i(strArr[i2]);
                if (!TextUtils.isEmpty(i4)) {
                    charSequenceArr[i] = i4;
                    i++;
                }
                i2 = i3;
            }
        } else {
            charSequenceArr[0] = charSequence;
            charSequenceArr[1] = this.f9451J.getCharSequence("android.media.metadata.DISPLAY_SUBTITLE");
            charSequenceArr[2] = this.f9451J.getCharSequence("android.media.metadata.DISPLAY_DESCRIPTION");
        }
        int i5 = 0;
        while (true) {
            String[] strArr2 = H;
            bundle = null;
            if (i5 >= strArr2.length) {
                bitmap = null;
                break;
            }
            try {
                bitmap2 = (Bitmap) this.f9451J.getParcelable(strArr2[i5]);
            } catch (Exception e) {
                Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", e);
                bitmap2 = null;
            }
            if (bitmap2 != null) {
                bitmap = bitmap2;
                break;
            }
            i5++;
        }
        int i6 = 0;
        while (true) {
            String[] strArr3 = I;
            if (i6 >= strArr3.length) {
                uri = null;
                break;
            }
            String h2 = h(strArr3[i6]);
            if (!TextUtils.isEmpty(h2)) {
                uri = Uri.parse(h2);
                break;
            }
            i6++;
        }
        String h3 = h("android.media.metadata.MEDIA_URI");
        Uri parse = !TextUtils.isEmpty(h3) ? Uri.parse(h3) : null;
        CharSequence charSequence2 = charSequenceArr[0];
        CharSequence charSequence3 = charSequenceArr[1];
        CharSequence charSequence4 = charSequenceArr[2];
        Bundle bundle2 = new Bundle();
        if (this.f9451J.containsKey("android.media.metadata.BT_FOLDER_TYPE")) {
            bundle2.putLong("android.media.extra.BT_FOLDER_TYPE", this.f9451J.getLong("android.media.metadata.BT_FOLDER_TYPE", 0));
        }
        if (this.f9451J.containsKey("android.media.metadata.DOWNLOAD_STATUS")) {
            bundle2.putLong("android.media.extra.DOWNLOAD_STATUS", this.f9451J.getLong("android.media.metadata.DOWNLOAD_STATUS", 0));
        }
        if (!bundle2.isEmpty()) {
            bundle = bundle2;
        }
        MediaDescriptionCompat mediaDescriptionCompat2 = new MediaDescriptionCompat(h, charSequence2, charSequence3, charSequence4, bitmap, uri, bundle, parse);
        this.L = mediaDescriptionCompat2;
        return mediaDescriptionCompat2;
    }

    public int describeContents() {
        return 0;
    }

    public String h(String str) {
        CharSequence charSequence = this.f9451J.getCharSequence(str);
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public CharSequence i(String str) {
        return this.f9451J.getCharSequence(str);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f9451J);
    }

    public MediaMetadataCompat(Parcel parcel) {
        this.f9451J = parcel.readBundle(C0571Jh0.class.getClassLoader());
    }
}
