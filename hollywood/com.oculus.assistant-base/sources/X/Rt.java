package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;

public final class Rt implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        RootTelemetryConfiguration rootTelemetryConfiguration = null;
        int[] iArr = null;
        boolean z = false;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i2 = readInt & 65535;
            if (i2 == 1) {
                rootTelemetryConfiguration = (RootTelemetryConfiguration) C0326Rd.A03(parcel, readInt, RootTelemetryConfiguration.CREATOR);
            } else if (i2 == 2) {
                z = C0326Rd.A08(parcel, readInt);
            } else if (i2 == 3) {
                z2 = C0326Rd.A08(parcel, readInt);
            } else if (i2 == 4) {
                int A02 = C0326Rd.A02(parcel, readInt);
                int dataPosition = parcel.dataPosition();
                if (A02 == 0) {
                    iArr = null;
                } else {
                    iArr = parcel.createIntArray();
                    parcel.setDataPosition(dataPosition + A02);
                }
            } else if (i2 != 5) {
                C0326Rd.A06(parcel, readInt);
            } else {
                i = C0326Rd.A01(parcel, readInt);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new ConnectionTelemetryConfiguration(rootTelemetryConfiguration, z, z2, iArr, i);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new ConnectionTelemetryConfiguration[i];
    }
}
