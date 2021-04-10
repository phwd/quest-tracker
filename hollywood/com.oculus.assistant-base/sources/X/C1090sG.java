package X;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;

/* renamed from: X.sG  reason: case insensitive filesystem */
public final class C1090sG implements RD, RK {
    public IAccountAccessor A00 = null;
    public Set A01 = null;
    public boolean A02 = false;
    public final AbstractC1084s8 A03;
    public final C0315Qm A04;
    public final /* synthetic */ C0319Qs A05;

    public C1090sG(C0319Qs qs, AbstractC1084s8 s8Var, C0315Qm qm) {
        this.A05 = qs;
        this.A03 = s8Var;
        this.A04 = qm;
    }

    @Override // X.RK
    public final void A4J(ConnectionResult connectionResult) {
        this.A05.A05.post(new R6(this, connectionResult));
    }

    @Override // X.RD
    public final void A63(ConnectionResult connectionResult) {
        C0675ex exVar = (C0675ex) this.A05.A08.get(this.A04);
        if (exVar != null) {
            RZ.A00(exVar.A0B.A05);
            AbstractC1084s8 s8Var = exVar.A03;
            String name = s8Var.getClass().getName();
            String valueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(name.length() + 25 + valueOf.length());
            sb.append("onSignInFailed for ");
            sb.append(name);
            sb.append(" with ");
            sb.append(valueOf);
            s8Var.A1i(sb.toString());
            exVar.A3y(connectionResult);
        }
    }

    @Override // X.RD
    public final void A67(IAccountAccessor iAccountAccessor, Set set) {
        if (iAccountAccessor == null || set == null) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            A63(new ConnectionResult(4));
            return;
        }
        this.A00 = iAccountAccessor;
        this.A01 = set;
        if (this.A02) {
            this.A03.A2o(iAccountAccessor, set);
        }
    }
}
