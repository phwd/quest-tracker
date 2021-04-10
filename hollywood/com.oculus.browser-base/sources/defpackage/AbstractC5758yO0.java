package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* renamed from: yO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5758yO0 {
    public static void a(Parcel parcel, int i, Bundle bundle, boolean z) {
        if (bundle != null) {
            int l = l(parcel, i);
            parcel.writeBundle(bundle);
            n(parcel, l);
        } else if (z) {
            o(parcel, i, 0);
        }
    }

    public static void b(Parcel parcel, int i, byte[] bArr, boolean z) {
        if (bArr != null) {
            int l = l(parcel, i);
            parcel.writeByteArray(bArr);
            n(parcel, l);
        } else if (z) {
            o(parcel, i, 0);
        }
    }

    public static void c(Parcel parcel, int i, Double d, boolean z) {
        if (d != null) {
            o(parcel, i, 8);
            parcel.writeDouble(d.doubleValue());
        } else if (z) {
            o(parcel, i, 0);
        }
    }

    public static void d(Parcel parcel, int i, IBinder iBinder, boolean z) {
        if (iBinder != null) {
            int l = l(parcel, i);
            parcel.writeStrongBinder(iBinder);
            n(parcel, l);
        } else if (z) {
            o(parcel, i, 0);
        }
    }

    public static void e(Parcel parcel, int i, Integer num, boolean z) {
        if (num != null) {
            o(parcel, i, 4);
            parcel.writeInt(num.intValue());
        } else if (z) {
            o(parcel, i, 0);
        }
    }

    public static void f(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable != null) {
            int l = l(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            n(parcel, l);
        } else if (z) {
            o(parcel, i, 0);
        }
    }

    public static void g(Parcel parcel, int i, String str, boolean z) {
        if (str != null) {
            int l = l(parcel, i);
            parcel.writeString(str);
            n(parcel, l);
        } else if (z) {
            o(parcel, i, 0);
        }
    }

    public static void h(Parcel parcel, int i, String[] strArr, boolean z) {
        if (strArr != null) {
            int l = l(parcel, i);
            parcel.writeStringArray(strArr);
            n(parcel, l);
        } else if (z) {
            o(parcel, i, 0);
        }
    }

    public static void i(Parcel parcel, int i, List list, boolean z) {
        if (list != null) {
            int l = l(parcel, i);
            parcel.writeStringList(list);
            n(parcel, l);
        } else if (z) {
            o(parcel, i, 0);
        }
    }

    public static void j(Parcel parcel, int i, Parcelable[] parcelableArr, int i2, boolean z) {
        if (parcelableArr != null) {
            int l = l(parcel, i);
            int length = parcelableArr.length;
            parcel.writeInt(length);
            for (Parcelable parcelable : parcelableArr) {
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    m(parcel, parcelable, i2);
                }
            }
            n(parcel, l);
        } else if (z) {
            o(parcel, i, 0);
        }
    }

    public static void k(Parcel parcel, int i, List list, boolean z) {
        if (list != null) {
            int l = l(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Parcelable parcelable = (Parcelable) list.get(i2);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    m(parcel, parcelable, 0);
                }
            }
            n(parcel, l);
        } else if (z) {
            o(parcel, i, 0);
        }
    }

    public static int l(Parcel parcel, int i) {
        parcel.writeInt(i | -65536);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    public static void m(Parcel parcel, Parcelable parcelable, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    public static void n(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(dataPosition - i);
        parcel.setDataPosition(dataPosition);
    }

    public static void o(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(i | -65536);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt(i | (i2 << 16));
    }
}
