package X;

import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.facebook.mobileconfigservice.service.IMobileConfig;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0T5  reason: invalid class name */
public class AnonymousClass0T5 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient$2";
    public final /* synthetic */ AnonymousClass0T8 A00;
    public final /* synthetic */ String A01;

    public AnonymousClass0T5(AnonymousClass0T8 r1, String str) {
        this.A00 = r1;
        this.A01 = str;
    }

    public final void run() {
        Throwable e;
        String str;
        IMobileConfig iMobileConfig;
        try {
            AnonymousClass0T8 r6 = this.A00;
            int myPid = Process.myPid();
            AnonymousClass0T6 r7 = r6.A01;
            if (r7.A02 != null) {
                iMobileConfig = r7.A02;
            } else {
                try {
                    r7.A01.await(30, TimeUnit.SECONDS);
                    if (r7.A02 == null) {
                        Log.e("MobileConfigBaseClient", "Timed out trying to connect to MobileConfigService.");
                    }
                } catch (InterruptedException e2) {
                    Log.e("MobileConfigBaseClient", "Interrupted Exception during connecting to service", e2);
                    Thread.currentThread().interrupt();
                }
                if (r7.A02 != null) {
                    iMobileConfig = r7.A02;
                } else {
                    throw new RemoteException("Could not connect to MobileConfigService.");
                }
            }
            iMobileConfig.AAe(this.A01, AnonymousClass0T8.A02(r6), myPid);
        } catch (RemoteException e3) {
            e = e3;
            str = "Remote exception while connecting to mc service";
            AnonymousClass0MD.A07("MobileConfigBaseClient", str, e);
        } catch (Exception e4) {
            e = e4;
            str = "Generic exception while connecting to mc service";
            AnonymousClass0MD.A07("MobileConfigBaseClient", str, e);
        }
    }
}
