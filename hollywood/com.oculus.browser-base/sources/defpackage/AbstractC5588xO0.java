package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* renamed from: xO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5588xO0 {
    public static Bundle a(Parcel parcel, int i) {
        int s = s(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (s == 0) {
            return null;
        }
        Bundle readBundle = parcel.readBundle();
        parcel.setDataPosition(dataPosition + s);
        return readBundle;
    }

    public static byte[] b(Parcel parcel, int i) {
        int s = s(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (s == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(dataPosition + s);
        return createByteArray;
    }

    public static long[] c(Parcel parcel, int i) {
        int s = s(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (s == 0) {
            return null;
        }
        long[] createLongArray = parcel.createLongArray();
        parcel.setDataPosition(dataPosition + s);
        return createLongArray;
    }

    public static Parcelable d(Parcel parcel, int i, Parcelable.Creator creator) {
        int s = s(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (s == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(dataPosition + s);
        return parcelable;
    }

    public static String e(Parcel parcel, int i) {
        int s = s(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (s == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(dataPosition + s);
        return readString;
    }

    public static String[] f(Parcel parcel, int i) {
        int s = s(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (s == 0) {
            return null;
        }
        String[] createStringArray = parcel.createStringArray();
        parcel.setDataPosition(dataPosition + s);
        return createStringArray;
    }

    public static ArrayList g(Parcel parcel, int i) {
        int s = s(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (s == 0) {
            return null;
        }
        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(dataPosition + s);
        return createStringArrayList;
    }

    public static Object[] h(Parcel parcel, int i, Parcelable.Creator creator) {
        int s = s(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (s == 0) {
            return null;
        }
        Object[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(dataPosition + s);
        return createTypedArray;
    }

    public static ArrayList i(Parcel parcel, int i, Parcelable.Creator creator) {
        int s = s(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (s == 0) {
            return null;
        }
        ArrayList createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(dataPosition + s);
        return createTypedArrayList;
    }

    public static void j(Parcel parcel, int i) {
        if (parcel.dataPosition() != i) {
            throw new C5418wO0(AbstractC2531fV.s(37, "Overread allowed size end=", i), parcel);
        }
    }

    public static boolean k(Parcel parcel, int i) {
        v(parcel, i, 4);
        return parcel.readInt() != 0;
    }

    public static double l(Parcel parcel, int i) {
        v(parcel, i, 8);
        return parcel.readDouble();
    }

    public static Double m(Parcel parcel, int i) {
        int s = s(parcel, i);
        if (s == 0) {
            return null;
        }
        w(parcel, s, 8);
        return Double.valueOf(parcel.readDouble());
    }

    public static float n(Parcel parcel, int i) {
        v(parcel, i, 4);
        return parcel.readFloat();
    }

    public static IBinder o(Parcel parcel, int i) {
        int s = s(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (s == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(dataPosition + s);
        return readStrongBinder;
    }

    public static int p(Parcel parcel, int i) {
        v(parcel, i, 4);
        return parcel.readInt();
    }

    public static Integer q(Parcel parcel, int i) {
        int s = s(parcel, i);
        if (s == 0) {
            return null;
        }
        w(parcel, s, 4);
        return Integer.valueOf(parcel.readInt());
    }

    public static long r(Parcel parcel, int i) {
        v(parcel, i, 8);
        return parcel.readLong();
    }

    public static int s(Parcel parcel, int i) {
        return (i & -65536) != -65536 ? (i >> 16) & 65535 : parcel.readInt();
    }

    public static void t(Parcel parcel, int i) {
        parcel.setDataPosition(parcel.dataPosition() + s(parcel, i));
    }

    public static int u(Parcel parcel) {
        int readInt = parcel.readInt();
        int s = s(parcel, readInt);
        int dataPosition = parcel.dataPosition();
        if ((65535 & readInt) != 20293) {
            String valueOf = String.valueOf(Integer.toHexString(readInt));
            throw new C5418wO0(valueOf.length() != 0 ? "Expected object header. Got 0x".concat(valueOf) : new String("Expected object header. Got 0x"), parcel);
        }
        int i = s + dataPosition;
        if (i >= dataPosition && i <= parcel.dataSize()) {
            return i;
        }
        throw new C5418wO0(AbstractC2531fV.t(54, "Size read is invalid start=", dataPosition, " end=", i), parcel);
    }

    public static void v(Parcel parcel, int i, int i2) {
        int s = s(parcel, i);
        if (s != i2) {
            String hexString = Integer.toHexString(s);
            StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 46);
            sb.append("Expected size ");
            sb.append(i2);
            sb.append(" got ");
            sb.append(s);
            sb.append(" (0x");
            sb.append(hexString);
            sb.append(")");
            throw new C5418wO0(sb.toString(), parcel);
        }
    }

    public static void w(Parcel parcel, int i, int i2) {
        if (i != i2) {
            String hexString = Integer.toHexString(i);
            StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 46);
            sb.append("Expected size ");
            sb.append(i2);
            sb.append(" got ");
            sb.append(i);
            sb.append(" (0x");
            sb.append(hexString);
            sb.append(")");
            throw new C5418wO0(sb.toString(), parcel);
        }
    }
}
