package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.base.library_loader.Linker;

/* renamed from: I80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I80 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new Linker.LibInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new Linker.LibInfo[i];
    }
}
