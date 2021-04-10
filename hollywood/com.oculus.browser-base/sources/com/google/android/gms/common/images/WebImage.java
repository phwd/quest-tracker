package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class WebImage extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new LB1();
    public final int F;
    public final Uri G;
    public final int H;
    public final int I;

    public WebImage(int i, Uri uri, int i2, int i3) {
        this.F = i;
        this.G = uri;
        this.H = i2;
        this.I = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof WebImage)) {
            WebImage webImage = (WebImage) obj;
            return AbstractC0895Oq0.a(this.G, webImage.G) && this.H == webImage.H && this.I == webImage.I;
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.G, Integer.valueOf(this.H), Integer.valueOf(this.I)});
    }

    public final String toString() {
        return String.format(Locale.US, "Image %dx%d %s", Integer.valueOf(this.H), Integer.valueOf(this.I), this.G.toString());
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.f(parcel, 2, this.G, i, false);
        int i3 = this.H;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i3);
        int i4 = this.I;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(i4);
        AbstractC5758yO0.n(parcel, l);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WebImage(org.json.JSONObject r5) {
        /*
            r4 = this;
            java.lang.String r0 = "url"
            boolean r1 = r5.has(r0)
            if (r1 == 0) goto L_0x0011
            java.lang.String r0 = r5.getString(r0)     // Catch:{ JSONException -> 0x0011 }
            android.net.Uri r0 = android.net.Uri.parse(r0)     // Catch:{ JSONException -> 0x0011 }
            goto L_0x0012
        L_0x0011:
            r0 = 0
        L_0x0012:
            java.lang.String r1 = "width"
            r2 = 0
            int r1 = r5.optInt(r1, r2)
            java.lang.String r3 = "height"
            int r5 = r5.optInt(r3, r2)
            r4.<init>()
            r2 = 1
            r4.F = r2
            r4.G = r0
            r4.H = r1
            r4.I = r5
            if (r0 == 0) goto L_0x003a
            if (r1 < 0) goto L_0x0032
            if (r5 < 0) goto L_0x0032
            return
        L_0x0032:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "width and height must not be negative"
            r5.<init>(r0)
            throw r5
        L_0x003a:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "url cannot be null"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.WebImage.<init>(org.json.JSONObject):void");
    }
}
