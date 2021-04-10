package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.oculus.os.Version;
import java.util.ArrayList;

/* renamed from: qI1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4385qI1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        ArrayList arrayList = null;
        int[] iArr = null;
        String str = null;
        IBinder iBinder = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        int i26 = 0;
        int i27 = 0;
        long j = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    arrayList = AbstractC5588xO0.g(parcel, readInt);
                    break;
                case 3:
                    int s = AbstractC5588xO0.s(parcel, readInt);
                    int dataPosition = parcel.dataPosition();
                    if (s != 0) {
                        int[] createIntArray = parcel.createIntArray();
                        parcel.setDataPosition(dataPosition + s);
                        iArr = createIntArray;
                        break;
                    } else {
                        iArr = null;
                        break;
                    }
                case 4:
                    j = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case 5:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 6:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_7:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_8:
                    i3 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_9:
                    i4 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_10:
                    i5 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_11:
                    i6 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_12:
                    i7 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_13:
                    i8 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_14:
                    i9 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_15:
                    i10 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_16:
                    i11 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_17:
                    i12 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_18:
                    i13 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_19:
                    i14 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_20:
                    i15 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_21:
                    i16 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_22:
                    i17 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_23:
                    i18 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_24:
                    i19 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_25:
                    i20 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_26:
                    i21 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_27:
                    i22 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_28:
                    i23 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_29:
                    i24 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_30:
                    i25 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_31:
                    i26 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_32:
                    i27 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 33:
                    iBinder = AbstractC5588xO0.o(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new NotificationOptions(arrayList, iArr, j, str, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, i27, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new NotificationOptions[i];
    }
}
