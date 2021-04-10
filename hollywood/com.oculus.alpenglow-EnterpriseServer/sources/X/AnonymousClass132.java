package X;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.oculus.security.basecomponent.OculusFbPermissionSecureService;
import com.oculus.security.basecomponent.OculusPublicService;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.132  reason: invalid class name */
public abstract class AnonymousClass132 extends Service {
    public static final String SUB_ENDPOINT_ON_BIND = "onBind";
    public static final String SUB_ENDPOINT_ON_START_COMMAND = "onStartCommand";
    public boolean mCalledOnCreate = false;
    public boolean mCalledOnDestroy = false;
    public boolean mCalledOnStartCommand = false;
    public String mEndpointName;
    public AnonymousClass0j1 mPermissionChecker = AnonymousClass0LS.A01;
    public final AbstractC04970iB mReporter = new C02810ar();

    private final void A00(String str, Object... objArr) {
        this.mReporter.A7Q("PublicBaseServiceWithSwitchOff", String.format(Locale.US, str, objArr), new Throwable());
    }

    private final AbstractC02820as A01() {
        if (this instanceof OculusPublicService) {
            return ((OculusPublicService) this).mOculusIntentLogger;
        }
        if (!(this instanceof OculusFbPermissionSecureService)) {
            return C04950i8.A00;
        }
        return ((OculusFbPermissionSecureService) this).mOculusIntentLogger;
    }

    /* JADX INFO: finally extract failed */
    public final void onCreate() {
        if (this.mCalledOnCreate) {
            A00("Class %s called onCreate twice. This may be due to calling super.onCreate instead of super.onFbCreate", getClass().getName());
            super.onCreate();
            return;
        }
        try {
            this.mCalledOnCreate = true;
            this.mEndpointName = String.format("%s/%s", getPackageName(), getClass().getName());
            A02();
            this.mCalledOnCreate = false;
        } catch (Throwable th) {
            this.mCalledOnCreate = false;
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public final void onDestroy() {
        if (this.mCalledOnDestroy) {
            A00("Class %s called onDestroy twice. This may be due to calling super.onDestroy instead of super.onFbDestroy", getClass().getName());
            super.onDestroy();
            return;
        }
        try {
            this.mCalledOnDestroy = true;
            super.onDestroy();
            this.mCalledOnDestroy = false;
        } catch (Throwable th) {
            this.mCalledOnDestroy = false;
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public final int onStartCommand(Intent intent, int i, int i2) {
        boolean A8K;
        if (this.mCalledOnStartCommand) {
            A00("Class %s called onStartCommand twice. This may be due to calling super.onStartCommand instead of super.onFbStartCommand", getClass().getName());
        } else if (C05040iP.A01().A00(this, this, intent)) {
            synchronized (this) {
                A8K = this.mPermissionChecker.A8K(this, this, intent, this.mReporter);
            }
            if (A8K) {
                try {
                    this.mCalledOnStartCommand = true;
                    A01().A00(this.mEndpointName, SUB_ENDPOINT_ON_START_COMMAND, "allow", intent);
                    int onStartCommand = super.onStartCommand(intent, i, i2);
                    this.mCalledOnStartCommand = false;
                    return onStartCommand;
                } catch (Throwable th) {
                    this.mCalledOnStartCommand = false;
                    throw th;
                }
            }
        }
        A01().A00(this.mEndpointName, SUB_ENDPOINT_ON_START_COMMAND, "deny", intent);
        return super.onStartCommand(intent, i, i2);
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        boolean A8K;
        if (C05040iP.A01().A00(this, this, intent)) {
            synchronized (this) {
                A8K = this.mPermissionChecker.A8K(this, this, intent, this.mReporter);
            }
            if (A8K) {
                A01().A00(this.mEndpointName, "onBind", "allow", intent);
                return null;
            }
        }
        A01().A00(this.mEndpointName, "onBind", "deny", intent);
        return null;
    }

    public void A02() {
        super.onCreate();
    }
}
