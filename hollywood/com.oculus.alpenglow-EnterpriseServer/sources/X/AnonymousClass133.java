package X;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import com.oculus.alpenglow.remotewipe.RemoteWipeService;
import com.oculus.security.basecomponent.OculusPublicIntentService;
import javax.annotation.Nullable;

/* renamed from: X.133  reason: invalid class name */
public abstract class AnonymousClass133 extends IntentService {
    public static final String SUB_ENDPOINT_ON_BIND = "onBind";
    public static final String SUB_ENDPOINT_ON_HANDLE_INTENT = "onHandleIntent";
    public String mEndpointName;
    public AnonymousClass0j1 mPermissionChecker = AnonymousClass0LS.A01;
    public final String name = RemoteWipeService.TAG;

    public AnonymousClass133() {
        super(RemoteWipeService.TAG);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        if (r0 == false) goto L_0x00a2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onHandleIntent(@javax.annotation.Nullable android.content.Intent r10) {
        /*
        // Method dump skipped, instructions count: 180
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass133.onHandleIntent(android.content.Intent):void");
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        AbstractC02820as r1;
        boolean A8K;
        AbstractC02820as r2;
        if (C05040iP.A01().A00(this, this, intent)) {
            synchronized (this) {
                A8K = this.mPermissionChecker.A8K(this, this, intent, null);
            }
            if (A8K) {
                if (!(this instanceof OculusPublicIntentService)) {
                    r2 = C04950i8.A00;
                } else {
                    r2 = ((OculusPublicIntentService) this).mOculusIntentLogger;
                }
                r2.A00(this.mEndpointName, "onBind", "allow", intent);
                return null;
            }
        }
        if (!(this instanceof OculusPublicIntentService)) {
            r1 = C04950i8.A00;
        } else {
            r1 = ((OculusPublicIntentService) this).mOculusIntentLogger;
        }
        r1.A00(this.mEndpointName, "onBind", "deny", intent);
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.mEndpointName = String.format("%s/%s", getPackageName(), getClass().getName());
    }
}
