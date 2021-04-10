package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BinderWrapper implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C5054uE1();
    public IBinder F = null;

    public BinderWrapper(IBinder iBinder) {
        this.F = iBinder;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.F);
    }

    public BinderWrapper(Parcel parcel, C5054uE1 ue1) {
        this.F = parcel.readStrongBinder();
    }
}
