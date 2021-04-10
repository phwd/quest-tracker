package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UvmEntry extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C1807bD1();
    public int F;
    public short G;
    public short H;

    public UvmEntry(int i, short s, short s2) {
        this.F = i;
        this.G = s;
        this.H = s2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof UvmEntry)) {
            return false;
        }
        UvmEntry uvmEntry = (UvmEntry) obj;
        if (this.F == uvmEntry.F && this.G == uvmEntry.G && this.H == uvmEntry.H) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.F), Short.valueOf(this.G), Short.valueOf(this.H)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        short s = this.G;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(s);
        short s2 = this.H;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(s2);
        AbstractC5758yO0.n(parcel, l);
    }
}
