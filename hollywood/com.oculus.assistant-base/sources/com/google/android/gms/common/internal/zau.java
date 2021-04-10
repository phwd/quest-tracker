package com.google.android.gms.common.internal;

import X.C0327Re;
import X.C0336Rq;
import X.RY;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class zau extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C0336Rq();
    public ConnectionResult A00;
    public IBinder A01;
    public boolean A02;
    public boolean A03;
    public final int A04;

    public final boolean equals(Object obj) {
        if (obj != null) {
            if (this != obj) {
                if (obj instanceof zau) {
                    zau zau = (zau) obj;
                    if (!this.A00.equals(zau.A00) || !RY.A00(A00(), zau.A00())) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public final IAccountAccessor A00() {
        IBinder iBinder = this.A01;
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
        if (queryLocalInterface instanceof IAccountAccessor) {
            return (IAccountAccessor) queryLocalInterface;
        }
        return new IAccountAccessor$Stub$zza(iBinder);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A04);
        IBinder iBinder = this.A01;
        if (iBinder != null) {
            int A003 = C0327Re.A00(parcel, 2);
            parcel.writeStrongBinder(iBinder);
            C0327Re.A01(parcel, A003);
        }
        C0327Re.A04(parcel, 3, this.A00, i);
        boolean z = this.A02;
        C0327Re.A03(parcel, 4, 4);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = this.A03;
        C0327Re.A03(parcel, 5, 4);
        parcel.writeInt(z2 ? 1 : 0);
        C0327Re.A01(parcel, A002);
    }

    public zau(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.A04 = i;
        this.A01 = iBinder;
        this.A00 = connectionResult;
        this.A02 = z;
        this.A03 = z2;
    }
}
