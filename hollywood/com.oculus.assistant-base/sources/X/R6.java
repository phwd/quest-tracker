package X;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;

public final class R6 implements Runnable {
    public static final String __redex_internal_original_name = "com.google.android.gms.common.api.internal.zabj";
    public final /* synthetic */ ConnectionResult A00;
    public final /* synthetic */ C1090sG A01;

    public R6(C1090sG sGVar, ConnectionResult connectionResult) {
        this.A01 = sGVar;
        this.A00 = connectionResult;
    }

    public final void run() {
        C1090sG sGVar = this.A01;
        C0675ex exVar = (C0675ex) sGVar.A05.A08.get(sGVar.A04);
        if (exVar != null) {
            ConnectionResult connectionResult = this.A00;
            if (connectionResult.A00 == 0) {
                sGVar.A02 = true;
                AbstractC1084s8 s8Var = sGVar.A03;
                if (s8Var.A4r()) {
                    IAccountAccessor iAccountAccessor = sGVar.A00;
                    if (iAccountAccessor != null) {
                        s8Var.A2o(iAccountAccessor, sGVar.A01);
                        return;
                    }
                    return;
                }
                try {
                    s8Var.A2o(null, s8Var.A2r());
                } catch (SecurityException e) {
                    Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
                    s8Var.A1i("Failed to get service from broker.");
                    exVar.A3y(new ConnectionResult(10));
                }
            } else {
                exVar.A3y(connectionResult);
            }
        }
    }
}
