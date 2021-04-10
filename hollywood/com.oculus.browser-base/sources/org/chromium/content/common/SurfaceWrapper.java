package org.chromium.content.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SurfaceWrapper implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C2292e41();
    public final Surface F;
    public final boolean G;

    public SurfaceWrapper(Surface surface, boolean z) {
        this.F = surface;
        this.G = z;
    }

    public static SurfaceWrapper create(Surface surface, boolean z) {
        return new SurfaceWrapper(surface, z);
    }

    public boolean canBeUsedWithSurfaceControl() {
        return this.G;
    }

    public int describeContents() {
        return 0;
    }

    public Surface getSurface() {
        return this.F;
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.F.writeToParcel(parcel, 0);
        parcel.writeInt(this.G ? 1 : 0);
    }
}
