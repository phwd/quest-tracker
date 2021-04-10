package defpackage;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* renamed from: qg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4439qg extends BasePendingResult implements AbstractC4609rg {
    public final AbstractC1607a7 o;
    public final C2470f7 p;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AbstractC4439qg(C2470f7 f7Var, YV yv) {
        super(yv);
        SE0.i(yv, "GoogleApiClient must not be null");
        SE0.i(f7Var, "Api must not be null");
        this.o = f7Var.a();
        this.p = f7Var;
    }

    public abstract void j(Z6 z6);

    public final void k(Z6 z6) {
        try {
            j(z6);
        } catch (DeadObjectException e) {
            l(new Status(1, 8, e.getLocalizedMessage(), null));
            throw e;
        } catch (RemoteException e2) {
            l(new Status(1, 8, e2.getLocalizedMessage(), null));
        }
    }

    public final void l(Status status) {
        SE0.b(!status.x(), "Failed result must not be success");
        f(c(status));
    }
}
