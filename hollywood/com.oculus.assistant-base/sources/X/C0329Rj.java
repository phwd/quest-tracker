package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zaaa;
import com.google.android.gms.common.internal.zao;
import java.util.ArrayList;

/* renamed from: X.Rj  reason: case insensitive filesystem */
public final class C0329Rj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        int i = 0;
        while (true) {
            ArrayList arrayList = null;
            while (true) {
                if (parcel.dataPosition() < A00) {
                    int readInt = parcel.readInt();
                    int i2 = readInt & 65535;
                    if (i2 == 1) {
                        i = C0326Rd.A01(parcel, readInt);
                    } else if (i2 != 2) {
                        C0326Rd.A06(parcel, readInt);
                    } else {
                        Parcelable.Creator creator = zao.CREATOR;
                        int A02 = C0326Rd.A02(parcel, readInt);
                        int dataPosition = parcel.dataPosition();
                        if (A02 != 0) {
                            arrayList = parcel.createTypedArrayList(creator);
                            parcel.setDataPosition(dataPosition + A02);
                        }
                    }
                } else {
                    C0326Rd.A05(parcel, A00);
                    return new zaaa(i, arrayList);
                }
            }
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zaaa[i];
    }
}
