package X;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.oculus.security.basecomponent.OculusFbPermissionSecureService;
import com.oculus.security.basecomponent.OculusPublicService;
import java.util.Locale;
import javax.annotation.Nullable;

public abstract class fC extends Service {
    public static final String SUB_ENDPOINT_ON_BIND = "onBind";
    public static final String SUB_ENDPOINT_ON_START_COMMAND = "onStartCommand";
    public boolean mCalledOnCreate = false;
    public boolean mCalledOnDestroy = false;
    public boolean mCalledOnStartCommand = false;
    public String mEndpointName;
    public AbstractC0224fr mPermissionChecker = BO.A01;
    public final AbstractC0201ew mReporter = new SC();

    private final void A00(String str, Object... objArr) {
        this.mReporter.A3M("PublicBaseServiceWithSwitchOff", String.format(Locale.US, str, objArr), new Throwable());
    }

    private final SD A01() {
        if (this instanceof OculusPublicService) {
            return ((OculusPublicService) this).mOculusIntentLogger;
        }
        if (!(this instanceof OculusFbPermissionSecureService)) {
            return C0200et.A00;
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
        boolean A3f;
        if (this.mCalledOnStartCommand) {
            A00("Class %s called onStartCommand twice. This may be due to calling super.onStartCommand instead of super.onFbStartCommand", getClass().getName());
        } else if (fF.A01().A00(this, this, intent)) {
            synchronized (this) {
                A3f = this.mPermissionChecker.A3f(this, this, intent, this.mReporter);
            }
            if (A3f) {
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

    public void A02() {
        super.onCreate();
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        boolean A3f;
        if (fF.A01().A00(this, this, intent)) {
            synchronized (this) {
                A3f = this.mPermissionChecker.A3f(this, this, intent, this.mReporter);
            }
            if (A3f) {
                A01().A00(this.mEndpointName, "onBind", "allow", intent);
                return null;
            }
        }
        A01().A00(this.mEndpointName, "onBind", "deny", intent);
        return null;
    }
}
