package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.chromium.base.task.PostTask;
import org.chromium.components.payments.PaymentDetailsUpdateService;

/* renamed from: lz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BinderC3642lz0 extends Binder implements IInterface {
    public BinderC3642lz0(PaymentDetailsUpdateService paymentDetailsUpdateService) {
        attachInterface(this, "org.chromium.components.payments.IPaymentDetailsUpdateService");
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        Bundle bundle = null;
        if (i == 1) {
            parcel.enforceInterface("org.chromium.components.payments.IPaymentDetailsUpdateService");
            if (parcel.readInt() != 0) {
                bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
            }
            AbstractC2710gZ c = AbstractBinderC2539fZ.c(parcel.readStrongBinder());
            PostTask.c(Zo1.f9374a, new RunnableC3129iz0(Binder.getCallingUid(), bundle, c));
            return true;
        } else if (i == 2) {
            parcel.enforceInterface("org.chromium.components.payments.IPaymentDetailsUpdateService");
            String readString = parcel.readString();
            AbstractC2710gZ c2 = AbstractBinderC2539fZ.c(parcel.readStrongBinder());
            PostTask.c(Zo1.f9374a, new RunnableC3300jz0(Binder.getCallingUid(), readString, c2));
            return true;
        } else if (i == 3) {
            parcel.enforceInterface("org.chromium.components.payments.IPaymentDetailsUpdateService");
            if (parcel.readInt() != 0) {
                bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
            }
            AbstractC2710gZ c3 = AbstractBinderC2539fZ.c(parcel.readStrongBinder());
            PostTask.c(Zo1.f9374a, new RunnableC3471kz0(Binder.getCallingUid(), bundle, c3));
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("org.chromium.components.payments.IPaymentDetailsUpdateService");
            return true;
        }
    }
}
