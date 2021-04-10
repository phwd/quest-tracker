package defpackage;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* renamed from: mZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC3735mZ extends DA1 {
    public AbstractBinderC3735mZ() {
        super("com.google.android.gms.common.api.internal.IStatusCallback");
    }

    @Override // defpackage.DA1
    public boolean y0(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        Status status = (Status) HB1.a(parcel, Status.CREATOR);
        BinderC3191jJ1 jj1 = (BinderC3191jJ1) this;
        if (status.K == 6) {
            jj1.f10199a.c(AbstractC3666m7.a(status));
        } else {
            AbstractC3241jf1.a(status, null, jj1.f10199a);
        }
        return true;
    }
}
