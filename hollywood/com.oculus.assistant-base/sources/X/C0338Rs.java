package X;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.zzc;

/* renamed from: X.Rs  reason: case insensitive filesystem */
public final class C0338Rs implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        Bundle bundle = null;
        Feature[] featureArr = null;
        ConnectionTelemetryConfiguration connectionTelemetryConfiguration = null;
        int i = 0;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i2 = readInt & 65535;
            if (i2 == 1) {
                int A02 = C0326Rd.A02(parcel, readInt);
                int dataPosition = parcel.dataPosition();
                if (A02 == 0) {
                    bundle = null;
                } else {
                    bundle = parcel.readBundle();
                    parcel.setDataPosition(dataPosition + A02);
                }
            } else if (i2 == 2) {
                featureArr = (Feature[]) C0326Rd.A09(parcel, readInt, Feature.CREATOR);
            } else if (i2 == 3) {
                i = C0326Rd.A01(parcel, readInt);
            } else if (i2 != 4) {
                C0326Rd.A06(parcel, readInt);
            } else {
                connectionTelemetryConfiguration = (ConnectionTelemetryConfiguration) C0326Rd.A03(parcel, readInt, ConnectionTelemetryConfiguration.CREATOR);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new zzc(bundle, featureArr, i, connectionTelemetryConfiguration);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzc[i];
    }
}
