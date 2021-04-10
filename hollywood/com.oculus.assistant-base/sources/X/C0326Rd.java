package X;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: X.Rd  reason: case insensitive filesystem */
public final class C0326Rd {
    public static int A01(Parcel parcel, int i) {
        A07(parcel, i, 4);
        return parcel.readInt();
    }

    public static boolean A08(Parcel parcel, int i) {
        A07(parcel, i, 4);
        if (parcel.readInt() != 0) {
            return true;
        }
        return false;
    }

    public static int A02(Parcel parcel, int i) {
        if ((i & -65536) != -65536) {
            return (i >> 16) & 65535;
        }
        return parcel.readInt();
    }

    public static int A00(Parcel parcel) {
        String str;
        int readInt = parcel.readInt();
        int A02 = A02(parcel, readInt);
        int dataPosition = parcel.dataPosition();
        if ((readInt & 65535) != 20293) {
            String valueOf = String.valueOf(Integer.toHexString(readInt));
            if (valueOf.length() != 0) {
                str = "Expected object header. Got 0x".concat(valueOf);
            } else {
                str = new String("Expected object header. Got 0x");
            }
            throw new C0325Rc(str, parcel);
        }
        int i = A02 + dataPosition;
        if (i >= dataPosition && i <= parcel.dataSize()) {
            return i;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("Size read is invalid start=");
        sb.append(dataPosition);
        sb.append(" end=");
        sb.append(i);
        throw new C0325Rc(sb.toString(), parcel);
    }

    public static Parcelable A03(Parcel parcel, int i, Parcelable.Creator creator) {
        int A02 = A02(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (A02 == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(dataPosition + A02);
        return parcelable;
    }

    public static String A04(Parcel parcel, int i) {
        int A02 = A02(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (A02 == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(dataPosition + A02);
        return readString;
    }

    public static void A05(Parcel parcel, int i) {
        if (parcel.dataPosition() != i) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Overread allowed size end=");
            sb.append(i);
            throw new C0325Rc(sb.toString(), parcel);
        }
    }

    public static void A06(Parcel parcel, int i) {
        parcel.setDataPosition(parcel.dataPosition() + A02(parcel, i));
    }

    public static void A07(Parcel parcel, int i, int i2) {
        int A02 = A02(parcel, i);
        if (A02 != i2) {
            String hexString = Integer.toHexString(A02);
            StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 46);
            sb.append("Expected size ");
            sb.append(i2);
            sb.append(" got ");
            sb.append(A02);
            sb.append(" (0x");
            sb.append(hexString);
            sb.append(")");
            throw new C0325Rc(sb.toString(), parcel);
        }
    }

    public static Object[] A09(Parcel parcel, int i, Parcelable.Creator creator) {
        int A02 = A02(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (A02 == 0) {
            return null;
        }
        Object[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(dataPosition + A02);
        return createTypedArray;
    }
}
