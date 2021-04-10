package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C1046Rd0();
    public final String F;
    public final CharSequence G;
    public final CharSequence H;
    public final CharSequence I;

    /* renamed from: J  reason: collision with root package name */
    public final Bitmap f9450J;
    public final Uri K;
    public final Bundle L;
    public final Uri M;
    public MediaDescription N;

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.F = str;
        this.G = charSequence;
        this.H = charSequence2;
        this.I = charSequence3;
        this.f9450J = bitmap;
        this.K = uri;
        this.L = bundle;
        this.M = uri2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.v4.media.MediaDescriptionCompat b(java.lang.Object r13) {
        /*
            r0 = 0
            if (r13 == 0) goto L_0x005d
            android.media.MediaDescription r13 = (android.media.MediaDescription) r13
            java.lang.String r2 = r13.getMediaId()
            java.lang.CharSequence r3 = r13.getTitle()
            java.lang.CharSequence r4 = r13.getSubtitle()
            java.lang.CharSequence r5 = r13.getDescription()
            android.graphics.Bitmap r6 = r13.getIconBitmap()
            android.net.Uri r7 = r13.getIconUri()
            android.os.Bundle r1 = r13.getExtras()
            if (r1 == 0) goto L_0x0027
            android.os.Bundle r1 = defpackage.C0571Jh0.f(r1)
        L_0x0027:
            java.lang.String r8 = "android.support.v4.media.description.MEDIA_URI"
            if (r1 == 0) goto L_0x0032
            android.os.Parcelable r9 = r1.getParcelable(r8)
            android.net.Uri r9 = (android.net.Uri) r9
            goto L_0x0033
        L_0x0032:
            r9 = r0
        L_0x0033:
            if (r9 == 0) goto L_0x004c
            java.lang.String r10 = "android.support.v4.media.description.NULL_BUNDLE_FLAG"
            boolean r11 = r1.containsKey(r10)
            if (r11 == 0) goto L_0x0046
            int r11 = r1.size()
            r12 = 2
            if (r11 != r12) goto L_0x0046
            r8 = r0
            goto L_0x004d
        L_0x0046:
            r1.remove(r8)
            r1.remove(r10)
        L_0x004c:
            r8 = r1
        L_0x004d:
            if (r9 == 0) goto L_0x0050
            goto L_0x0055
        L_0x0050:
            android.net.Uri r0 = r13.getMediaUri()
            r9 = r0
        L_0x0055:
            android.support.v4.media.MediaDescriptionCompat r0 = new android.support.v4.media.MediaDescriptionCompat
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            r0.N = r13
        L_0x005d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaDescriptionCompat.b(java.lang.Object):android.support.v4.media.MediaDescriptionCompat");
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return ((Object) this.G) + ", " + ((Object) this.H) + ", " + ((Object) this.I);
    }

    public void writeToParcel(Parcel parcel, int i) {
        MediaDescription mediaDescription = this.N;
        if (mediaDescription == null) {
            MediaDescription.Builder builder = new MediaDescription.Builder();
            builder.setMediaId(this.F);
            builder.setTitle(this.G);
            builder.setSubtitle(this.H);
            builder.setDescription(this.I);
            builder.setIconBitmap(this.f9450J);
            builder.setIconUri(this.K);
            builder.setExtras(this.L);
            builder.setMediaUri(this.M);
            mediaDescription = builder.build();
            this.N = mediaDescription;
        }
        mediaDescription.writeToParcel(parcel, i);
    }
}
