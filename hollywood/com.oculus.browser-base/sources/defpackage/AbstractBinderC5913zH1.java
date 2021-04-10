package defpackage;

import android.os.Parcel;

/* renamed from: zH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC5913zH1 extends AbstractBinderC2658gC1 implements TG1 {
    public AbstractBinderC5913zH1() {
        super("com.google.android.gms.common.internal.ICertData");
    }

    @Override // defpackage.AbstractBinderC2658gC1
    public final boolean c(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            BinderC0773Mq0 mq0 = new BinderC0773Mq0(((AbstractBinderC5737yF1) this).d());
            parcel2.writeNoException();
            AbstractC4546rF1.b(parcel2, mq0);
        } else if (i != 2) {
            return false;
        } else {
            int i3 = ((AbstractBinderC5737yF1) this).f11672a;
            parcel2.writeNoException();
            parcel2.writeInt(i3);
        }
        return true;
    }
}
