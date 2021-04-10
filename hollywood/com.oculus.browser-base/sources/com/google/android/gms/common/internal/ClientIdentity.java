package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClientIdentity extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new YA1();
    public final int F;
    public final String G;

    public ClientIdentity(int i, String str) {
        this.F = i;
        this.G = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof ClientIdentity)) {
            ClientIdentity clientIdentity = (ClientIdentity) obj;
            return clientIdentity.F == this.F && AbstractC0895Oq0.a(clientIdentity.G, this.G);
        }
    }

    public int hashCode() {
        return this.F;
    }

    public String toString() {
        int i = this.F;
        String str = this.G;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
        sb.append(i);
        sb.append(":");
        sb.append(str);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.g(parcel, 2, this.G, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
