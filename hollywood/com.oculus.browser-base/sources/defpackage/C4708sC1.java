package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.common.Transport;

/* renamed from: sC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4708sC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        String readString = parcel.readString();
        try {
            Transport[] values = Transport.values();
            for (Transport transport : values) {
                if (readString.equals(transport.M)) {
                    return transport;
                }
            }
            throw new C5992zn1(String.format("Transport %s not supported", readString));
        } catch (C5992zn1 e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Transport[i];
    }
}
