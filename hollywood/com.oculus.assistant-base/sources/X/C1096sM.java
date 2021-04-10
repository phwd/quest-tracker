package X;

import com.google.android.gms.common.ConnectionResult;
import java.util.Collections;
import java.util.Set;

/* renamed from: X.sM  reason: case insensitive filesystem */
public final class C1096sM implements RK {
    public final /* synthetic */ RO A00;

    public C1096sM(RO ro) {
        this.A00 = ro;
    }

    @Override // X.RK
    public final void A4J(ConnectionResult connectionResult) {
        Set set;
        if (connectionResult.A00 == 0) {
            RO ro = this.A00;
            if (!(ro instanceof G3)) {
                set = Collections.emptySet();
            } else {
                set = ((G3) ro).A01;
            }
            ro.A2o(null, set);
            return;
        }
        RJ rj = this.A00.A0C;
        if (rj != null) {
            rj.A3y(connectionResult);
        }
    }
}
