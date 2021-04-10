package X;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.facebook.GraphRequest;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.1U9  reason: invalid class name */
public abstract class AnonymousClass1U9 extends Service {
    public static final String SUB_ENDPOINT_ON_BIND = "onBind";
    public static final String SUB_ENDPOINT_ON_START_COMMAND = "onStartCommand";
    public boolean mCalledOnCreate = false;
    public boolean mCalledOnDestroy = false;
    public boolean mCalledOnStartCommand = false;
    public String mEndpointName;
    public AbstractC02910bj mPermissionChecker = AbstractC00900Hh.A01;
    public final AnonymousClass0b1 mReporter = new C04580iA();

    public synchronized void addAdditionalPermissionChecker(AbstractC02910bj r2) {
        this.mPermissionChecker = this.mPermissionChecker.A1E(r2);
    }

    @Nullable
    public IBinder doBind(Intent intent) {
        return null;
    }

    public final synchronized boolean shouldAllowIntent(Intent intent) {
        return this.mPermissionChecker.A8w(this, this, intent, this.mReporter);
    }

    /* JADX INFO: finally extract failed */
    public final void onCreate() {
        if (this.mCalledOnCreate) {
            onFail("Class %s called onCreate twice. This may be due to calling super.onCreate instead of super.onFbCreate", getClass().getName());
            super.onCreate();
            return;
        }
        try {
            this.mCalledOnCreate = true;
            this.mEndpointName = String.format(GraphRequest.GRAPH_PATH_FORMAT, getPackageName(), getClass().getName());
            doCreate();
            this.mCalledOnCreate = false;
        } catch (Throwable th) {
            this.mCalledOnCreate = false;
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public final void onDestroy() {
        if (this.mCalledOnDestroy) {
            onFail("Class %s called onDestroy twice. This may be due to calling super.onDestroy instead of super.onFbDestroy", getClass().getName());
            super.onDestroy();
            return;
        }
        try {
            this.mCalledOnDestroy = true;
            doDestroy();
            this.mCalledOnDestroy = false;
        } catch (Throwable th) {
            this.mCalledOnDestroy = false;
            throw th;
        }
    }

    public void onFail(String str, Object... objArr) {
        this.mReporter.report("PublicBaseServiceWithSwitchOff", String.format(Locale.US, str, objArr), new Throwable());
    }

    /* JADX INFO: finally extract failed */
    public final int onStartCommand(Intent intent, int i, int i2) {
        if (this.mCalledOnStartCommand) {
            onFail("Class %s called onStartCommand twice. This may be due to calling super.onStartCommand instead of super.onFbStartCommand", getClass().getName());
        } else if (C02670bA.A01().A00(this, this, intent) && shouldAllowIntent(intent)) {
            try {
                this.mCalledOnStartCommand = true;
                getIntentLogger().A00(this.mEndpointName, SUB_ENDPOINT_ON_START_COMMAND, "allow", intent);
                int doStartCommand = doStartCommand(intent, i, i2);
                this.mCalledOnStartCommand = false;
                return doStartCommand;
            } catch (Throwable th) {
                this.mCalledOnStartCommand = false;
                throw th;
            }
        }
        getIntentLogger().A00(this.mEndpointName, SUB_ENDPOINT_ON_START_COMMAND, "deny", intent);
        return super.onStartCommand(intent, i, i2);
    }

    public void doCreate() {
        super.onCreate();
    }

    public void doDestroy() {
        super.onDestroy();
    }

    public int doStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    public AbstractC04590iB getIntentLogger() {
        return C02650ay.A00;
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        if (!C02670bA.A01().A00(this, this, intent) || !shouldAllowIntent(intent)) {
            getIntentLogger().A00(this.mEndpointName, "onBind", "deny", intent);
            return null;
        }
        getIntentLogger().A00(this.mEndpointName, "onBind", "allow", intent);
        return doBind(intent);
    }
}
