package X;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import com.oculus.security.basecomponent.OculusPublicIntentService;
import com.oculus.userserver.managerservice.OculusUserManagerIntentService;
import javax.annotation.Nullable;

public abstract class fB extends IntentService {
    public static final String SUB_ENDPOINT_ON_BIND = "onBind";
    public static final String SUB_ENDPOINT_ON_HANDLE_INTENT = "onHandleIntent";
    public String mEndpointName;
    public AbstractC0224fr mPermissionChecker = BO.A01;
    public final String name = OculusUserManagerIntentService.TAG;

    public fB() {
        super(OculusUserManagerIntentService.TAG);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        if (r0 == false) goto L_0x00a6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onHandleIntent(@javax.annotation.Nullable android.content.Intent r6) {
        /*
        // Method dump skipped, instructions count: 184
        */
        throw new UnsupportedOperationException("Method not decompiled: X.fB.onHandleIntent(android.content.Intent):void");
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        SD sd;
        boolean A3f;
        SD sd2;
        if (fF.A01().A00(this, this, intent)) {
            synchronized (this) {
                A3f = this.mPermissionChecker.A3f(this, this, intent, null);
            }
            if (A3f) {
                if (!(this instanceof OculusPublicIntentService)) {
                    sd2 = C0200et.A00;
                } else {
                    sd2 = ((OculusPublicIntentService) this).mOculusIntentLogger;
                }
                sd2.A00(this.mEndpointName, "onBind", "allow", intent);
                return null;
            }
        }
        if (!(this instanceof OculusPublicIntentService)) {
            sd = C0200et.A00;
        } else {
            sd = ((OculusPublicIntentService) this).mOculusIntentLogger;
        }
        sd.A00(this.mEndpointName, "onBind", "deny", intent);
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.mEndpointName = String.format("%s/%s", getPackageName(), getClass().getName());
    }
}
