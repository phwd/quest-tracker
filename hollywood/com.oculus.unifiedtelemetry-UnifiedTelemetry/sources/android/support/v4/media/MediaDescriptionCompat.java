package android.support.v4.media;

import X.AnonymousClass0X;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new AnonymousClass0X();
    public MediaDescription A00;
    public final Bitmap A01;
    public final Uri A02;
    public final Uri A03;
    public final Bundle A04;
    public final CharSequence A05;
    public final CharSequence A06;
    public final CharSequence A07;
    public final String A08;

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) this.A07);
        sb.append(", ");
        sb.append((Object) this.A06);
        sb.append(", ");
        sb.append((Object) this.A05);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        MediaDescription mediaDescription = this.A00;
        if (mediaDescription == null) {
            MediaDescription.Builder builder = new MediaDescription.Builder();
            builder.setMediaId(this.A08);
            builder.setTitle(this.A07);
            builder.setSubtitle(this.A06);
            builder.setDescription(this.A05);
            builder.setIconBitmap(this.A01);
            builder.setIconUri(this.A02);
            builder.setExtras(this.A04);
            builder.setMediaUri(this.A03);
            mediaDescription = builder.build();
            this.A00 = mediaDescription;
        }
        mediaDescription.writeToParcel(parcel, i);
    }

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.A08 = str;
        this.A07 = charSequence;
        this.A06 = charSequence2;
        this.A05 = charSequence3;
        this.A01 = bitmap;
        this.A02 = uri;
        this.A04 = bundle;
        this.A03 = uri2;
    }
}
