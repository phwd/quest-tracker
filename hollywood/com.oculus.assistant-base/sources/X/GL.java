package X;

import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.facebook.mobileconfigservice.service.IMobileConfig;
import java.util.concurrent.TimeUnit;

public final class GL implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient$2";
    public final /* synthetic */ GO A00;
    public final /* synthetic */ String A01;

    public GL(GO go, String str) {
        this.A00 = go;
        this.A01 = str;
    }

    public final void run() {
        Throwable e;
        String str;
        IMobileConfig iMobileConfig;
        try {
            GO go = this.A00;
            int myPid = Process.myPid();
            Log.d("MobileConfigBaseClient", "getService()");
            GM gm = go.A01;
            if (gm.A01 != null) {
                iMobileConfig = gm.A01;
            } else {
                Log.d("MobileConfigBaseClient", "awaitService()");
                try {
                    gm.A00.await(30, TimeUnit.SECONDS);
                    if (gm.A01 == null) {
                        Log.e("MobileConfigBaseClient", "Timed out trying to connect to MobileConfigService.");
                    }
                } catch (InterruptedException e2) {
                    Log.e("MobileConfigBaseClient", "Interrupted Exception during connecting to service", e2);
                    Thread.currentThread().interrupt();
                }
                if (gm.A01 != null) {
                    iMobileConfig = gm.A01;
                } else {
                    throw new RemoteException("Could not connect to MobileConfigService.");
                }
            }
            iMobileConfig.A5E(this.A01, GO.A00(go), myPid);
        } catch (RemoteException e3) {
            e = e3;
            str = "Remote exception while connecting to mc service";
            C0139Dd.A0L("MobileConfigBaseClient", str, e);
        } catch (Exception e4) {
            e = e4;
            str = "Generic exception while connecting to mc service";
            C0139Dd.A0L("MobileConfigBaseClient", str, e);
        }
    }
}
