package X;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* renamed from: X.Re  reason: case insensitive filesystem */
public final class C0327Re {
    public static void A02(Parcel parcel, int i, int i2) {
        A03(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void A06(Parcel parcel, int i, List list) {
        if (list != null) {
            int A00 = A00(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Parcelable parcelable = (Parcelable) list.get(i2);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    int dataPosition = parcel.dataPosition();
                    parcel.writeInt(1);
                    int dataPosition2 = parcel.dataPosition();
                    parcelable.writeToParcel(parcel, 0);
                    int dataPosition3 = parcel.dataPosition();
                    parcel.setDataPosition(dataPosition);
                    parcel.writeInt(dataPosition3 - dataPosition2);
                    parcel.setDataPosition(dataPosition3);
                }
            }
            A01(parcel, A00);
        }
    }

    public static void A07(Parcel parcel, int i, Parcelable[] parcelableArr, int i2) {
        if (parcelableArr != null) {
            int A00 = A00(parcel, i);
            int length = parcelableArr.length;
            parcel.writeInt(length);
            for (Parcelable parcelable : parcelableArr) {
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    int dataPosition = parcel.dataPosition();
                    parcel.writeInt(1);
                    int dataPosition2 = parcel.dataPosition();
                    parcelable.writeToParcel(parcel, i2);
                    int dataPosition3 = parcel.dataPosition();
                    parcel.setDataPosition(dataPosition);
                    parcel.writeInt(dataPosition3 - dataPosition2);
                    parcel.setDataPosition(dataPosition3);
                }
            }
            A01(parcel, A00);
        }
    }

    public static int A00(Parcel parcel, int i) {
        parcel.writeInt(i | -65536);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    public static void A04(Parcel parcel, int i, Parcelable parcelable, int i2) {
        if (parcelable != null) {
            int A00 = A00(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            A01(parcel, A00);
        }
    }

    public static void A05(Parcel parcel, int i, String str) {
        if (str != null) {
            int A00 = A00(parcel, i);
            parcel.writeString(str);
            A01(parcel, A00);
        }
    }

    public static void A01(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(dataPosition - i);
        parcel.setDataPosition(dataPosition);
    }

    public static void A03(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(i | -65536);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt(i | (i2 << 16));
    }
}
