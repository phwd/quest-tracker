package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;
import com.oculus.os.Version;

/* renamed from: tF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4887tF1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        String str = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 3:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 4:
                    i3 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 5:
                    i4 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 6:
                    i5 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_7:
                    i6 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_8:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case Version.VERSION_9:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Barcode.CalendarDateTime(i, i2, i3, i4, i5, i6, z, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.CalendarDateTime[i];
    }
}
