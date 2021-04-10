package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.signin.internal.zai;
import java.util.ArrayList;

public final class Sd implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
        ArrayList<String> arrayList = null;
        String str = null;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            int i = readInt & 65535;
            if (i == 1) {
                int A02 = C0326Rd.A02(parcel, readInt);
                int dataPosition = parcel.dataPosition();
                if (A02 == 0) {
                    arrayList = null;
                } else {
                    arrayList = parcel.createStringArrayList();
                    parcel.setDataPosition(dataPosition + A02);
                }
            } else if (i != 2) {
                C0326Rd.A06(parcel, readInt);
            } else {
                str = C0326Rd.A04(parcel, readInt);
            }
        }
        C0326Rd.A05(parcel, A00);
        return new zai(arrayList, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zai[i];
    }
}
