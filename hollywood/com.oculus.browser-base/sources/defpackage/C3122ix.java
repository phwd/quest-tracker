package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.chrome.browser.password_check.CompromisedCredential;
import org.chromium.url.GURL;

/* renamed from: ix  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3122ix implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        String readString = parcel.readString();
        GURL a2 = GURL.a(parcel.readString());
        String readString2 = parcel.readString();
        String readString3 = parcel.readString();
        String readString4 = parcel.readString();
        String readString5 = parcel.readString();
        String readString6 = parcel.readString();
        String readString7 = parcel.readString();
        long readLong = parcel.readLong();
        boolean[] zArr = new boolean[4];
        parcel.readBooleanArray(zArr);
        return new CompromisedCredential(readString, a2, readString2, readString3, readString4, readString5, readString6, readString7, readLong, zArr[0], zArr[1], zArr[2], zArr[3]);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new CompromisedCredential[i];
    }
}
