package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.base.process_launcher.FileDescriptorInfo;

/* renamed from: EP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EP implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new FileDescriptorInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new FileDescriptorInfo[i];
    }
}
