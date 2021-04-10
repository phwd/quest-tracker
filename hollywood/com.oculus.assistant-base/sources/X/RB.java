package X;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zace;
import com.google.android.gms.common.internal.zau;
import com.google.android.gms.signin.internal.zak;

public final class RB implements Runnable {
    public static final String __redex_internal_original_name = "com.google.android.gms.common.api.internal.zacf";
    public final /* synthetic */ zace A00;
    public final /* synthetic */ zak A01;

    public RB(zace zace, zak zak) {
        this.A00 = zace;
        this.A01 = zak;
    }

    public final void run() {
        zace zace = this.A00;
        zak zak = this.A01;
        ConnectionResult connectionResult = zak.A00;
        if (connectionResult.A00 == 0) {
            zau zau = zak.A01;
            RZ.A01(zau);
            connectionResult = zau.A00;
            if (connectionResult.A00 == 0) {
                zace.A00.A67(zau.A00(), zace.A03);
                zace.A02.A1h();
            }
            String valueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(valueOf.length() + 48);
            sb.append("Sign-in succeeded with resolve account failure: ");
            sb.append(valueOf);
            Log.wtf("SignInCoordinator", sb.toString(), new Exception());
        }
        zace.A00.A63(connectionResult);
        zace.A02.A1h();
    }
}
