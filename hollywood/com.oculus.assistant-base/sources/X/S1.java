package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;

public final class S1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i4 = readInt & 65535;
            if (i4 == 1) {
                i = C0326Rd.A01(parcel, readInt);
            } else if (i4 == 2) {
                z = C0326Rd.A08(parcel, readInt);
            } else if (i4 == 3) {
                z2 = C0326Rd.A08(parcel, readInt);
            } else if (i4 == 4) {
                i2 = C0326Rd.A01(parcel, readInt);
            } else if (i4 != 5) {
                C0326Rd.A06(parcel, readInt);
            } else {
                i3 = C0326Rd.A01(parcel, readInt);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new RootTelemetryConfiguration(i, z, z2, i2, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new RootTelemetryConfiguration[i];
    }
}
