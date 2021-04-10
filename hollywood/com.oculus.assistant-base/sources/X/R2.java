package X;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class R2 {
    public final int A00;

    public final void A01(Status status) {
        Sl sl;
        Exception A2Q;
        if (!(this instanceof IR)) {
            sl = ((IS) this).A00;
            A2Q = new C0308Qe(status);
        } else {
            IR ir = (IR) this;
            sl = ir.A02;
            A2Q = ir.A00.A2Q(status);
        }
        sl.A00(A2Q);
    }

    public final void A02(Exception exc) {
        Sl sl;
        if (!(this instanceof IR)) {
            sl = ((IS) this).A00;
        } else {
            sl = ((IR) this).A02;
        }
        sl.A00(exc);
    }

    public R2(int i) {
        this.A00 = i;
    }

    public static Status A00(RemoteException remoteException) {
        return new Status(19, AnonymousClass08.A05(remoteException.getClass().getSimpleName(), ": ", remoteException.getLocalizedMessage()));
    }
}
