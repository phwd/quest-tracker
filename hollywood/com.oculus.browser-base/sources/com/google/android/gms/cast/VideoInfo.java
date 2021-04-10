package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class VideoInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new NE1();
    public int F;
    public int G;
    public int H;

    public VideoInfo(int i, int i2, int i3) {
        this.F = i;
        this.G = i2;
        this.H = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoInfo)) {
            return false;
        }
        VideoInfo videoInfo = (VideoInfo) obj;
        return this.G == videoInfo.G && this.F == videoInfo.F && this.H == videoInfo.H;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.G), Integer.valueOf(this.F), Integer.valueOf(this.H)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i2);
        int i3 = this.G;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i3);
        int i4 = this.H;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(i4);
        AbstractC5758yO0.n(parcel, l);
    }
}
