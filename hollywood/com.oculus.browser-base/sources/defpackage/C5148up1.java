package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.base.UnguessableToken;

/* renamed from: up1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5148up1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        long readLong = parcel.readLong();
        long readLong2 = parcel.readLong();
        if (readLong == 0 || readLong2 == 0) {
            return null;
        }
        return new UnguessableToken(readLong, readLong2, null);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new UnguessableToken[i];
    }
}
