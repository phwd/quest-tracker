package X;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.gr  reason: case insensitive filesystem */
public abstract class AbstractServiceC0390gr extends Service {
    public static final String SUB_ENDPOINT_ON_BIND = "onBind";
    public static final String SUB_ENDPOINT_ON_START_COMMAND = "onStartCommand";
    public boolean mCalledOnCreate = false;
    public boolean mCalledOnDestroy = false;
    public boolean mCalledOnStartCommand = false;
    public String mEndpointName;
    public AbstractC0421hW mPermissionChecker = FY.A01;
    public final AbstractC0382ge mReporter = new XS();

    private final void A00(String str, Object... objArr) {
        this.mReporter.A4i("PublicBaseServiceWithSwitchOff", String.format(Locale.US, str, objArr), new Throwable());
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
        boolean A5D;
        if (this.mCalledOnStartCommand) {
            A00("Class %s called onStartCommand twice. This may be due to calling super.onStartCommand instead of super.onFbStartCommand", getClass().getName());
        } else if (C0392gu.A01().A00(this, this, intent)) {
            synchronized (this) {
                A5D = this.mPermissionChecker.A5D(this, this, intent, this.mReporter);
            }
            if (A5D) {
                try {
                    this.mCalledOnStartCommand = true;
                    A01().A02(this.mEndpointName, SUB_ENDPOINT_ON_START_COMMAND, "allow", intent);
                    int onStartCommand = super.onStartCommand(intent, i, i2);
                    this.mCalledOnStartCommand = false;
                    return onStartCommand;
                } catch (Throwable th) {
                    this.mCalledOnStartCommand = false;
                    throw th;
                }
            }
        }
        A01().A02(this.mEndpointName, SUB_ENDPOINT_ON_START_COMMAND, "deny", intent);
        return super.onStartCommand(intent, i, i2);
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        boolean A5D;
        if (C0392gu.A01().A00(this, this, intent)) {
            synchronized (this) {
                A5D = this.mPermissionChecker.A5D(this, this, intent, this.mReporter);
            }
            if (A5D) {
                A01().A02(this.mEndpointName, "onBind", "allow", intent);
                return null;
            }
        }
        A01().A02(this.mEndpointName, "onBind", "deny", intent);
        return null;
    }

    public XT A01() {
        return C0381gb.A00;
    }

    public void A02() {
        super.onCreate();
    }
}
