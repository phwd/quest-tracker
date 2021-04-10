package defpackage;

import android.os.Parcel;

/* renamed from: Pn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0950Pn0 {
    public static long[] a(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == -1) {
            return null;
        }
        long[] jArr = new long[readInt];
        for (int i = 0; i < readInt; i++) {
            jArr[i] = parcel.readLong();
        }
        return jArr;
    }

    public static void b(Parcel parcel, long[] jArr, int i) {
        if (jArr == null) {
            parcel.writeInt(-1);
        } else if (i <= jArr.length) {
            parcel.writeInt(i);
            for (int i2 = 0; i2 < i; i2++) {
                parcel.writeLong(jArr[i2]);
            }
        } else {
            throw new IllegalArgumentException("size larger than length");
        }
    }
}
