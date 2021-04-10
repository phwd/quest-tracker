package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;
import org.chromium.content.common.SurfaceWrapper;

/* renamed from: e41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2292e41 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        Surface surface = (Surface) Surface.CREATOR.createFromParcel(parcel);
        boolean z = true;
        if (parcel.readInt() != 1) {
            z = false;
        }
        return new SurfaceWrapper(surface, z);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new SurfaceWrapper[i];
    }
}
