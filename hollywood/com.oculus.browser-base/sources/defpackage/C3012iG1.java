package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;
import com.oculus.os.Version;

/* renamed from: iG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3012iG1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        Barcode.PersonName personName = null;
        String str = null;
        String str2 = null;
        Barcode.Phone[] phoneArr = null;
        Barcode.Email[] emailArr = null;
        String[] strArr = null;
        Barcode.Address[] addressArr = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    personName = (Barcode.PersonName) AbstractC5588xO0.d(parcel, readInt, Barcode.PersonName.CREATOR);
                    break;
                case 3:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 4:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 5:
                    phoneArr = (Barcode.Phone[]) AbstractC5588xO0.h(parcel, readInt, Barcode.Phone.CREATOR);
                    break;
                case 6:
                    emailArr = (Barcode.Email[]) AbstractC5588xO0.h(parcel, readInt, Barcode.Email.CREATOR);
                    break;
                case Version.VERSION_7:
                    strArr = AbstractC5588xO0.f(parcel, readInt);
                    break;
                case Version.VERSION_8:
                    addressArr = (Barcode.Address[]) AbstractC5588xO0.h(parcel, readInt, Barcode.Address.CREATOR);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Barcode.ContactInfo(personName, str, str2, phoneArr, emailArr, strArr, addressArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.ContactInfo[i];
    }
}
