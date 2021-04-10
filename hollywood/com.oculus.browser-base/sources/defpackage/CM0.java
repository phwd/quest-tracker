package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ResultReceiver;

/* renamed from: CM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CM0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new ResultReceiver(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new ResultReceiver[i];
    }
}
