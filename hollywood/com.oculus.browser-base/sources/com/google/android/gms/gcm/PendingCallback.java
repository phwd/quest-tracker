package com.google.android.gms.gcm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PendingCallback implements Parcelable, ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new C3015iH1();
    public final IBinder F;

    public PendingCallback(Parcel parcel) {
        this.F = parcel.readStrongBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.F);
    }
}
