package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MessengerCompat implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new C3530lI1();
    public Messenger F;

    public MessengerCompat(IBinder iBinder) {
        this.F = new Messenger(iBinder);
    }

    public final IBinder b() {
        Messenger messenger = this.F;
        Objects.requireNonNull(messenger);
        return messenger.getBinder();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return b().equals(((MessengerCompat) obj).b());
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public int hashCode() {
        return b().hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        Messenger messenger = this.F;
        Objects.requireNonNull(messenger);
        parcel.writeStrongBinder(messenger.getBinder());
    }
}
