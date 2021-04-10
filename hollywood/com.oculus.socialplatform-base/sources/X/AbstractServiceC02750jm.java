package X;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.0jm  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractServiceC02750jm extends Service {
    public static final String SUB_ENDPOINT_ON_BIND = "onBind";
    public static final String SUB_ENDPOINT_ON_START_COMMAND = "onStartCommand";
    public boolean mCalledOnCreate = false;
    public boolean mCalledOnDestroy = false;
    public boolean mCalledOnStartCommand = false;
    public String mEndpointName;
    public AnonymousClass0kS mPermissionChecker = AnonymousClass0TP.A01;
    public final AbstractC02660jW mReporter = new C03000kj();

    public synchronized void addAdditionalPermissionChecker(AnonymousClass0kS r2) {
        this.mPermissionChecker = this.mPermissionChecker.A1R(r2);
    }

    @Nullable
    public IBinder doBind(Intent intent) {
        return null;
    }

    public final synchronized boolean shouldAllowIntent(Intent intent) {
        return this.mPermissionChecker.AAL(this, this, intent, this.mReporter);
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
            this.mEndpointName = String.format("%s/%s", getPackageName(), getClass().getName());
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
            super.onDestroy();
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
        } else if (C02780jp.A01().A00(this, this, intent) && shouldAllowIntent(intent)) {
            try {
                this.mCalledOnStartCommand = true;
                getIntentLogger().logIntent(this.mEndpointName, SUB_ENDPOINT_ON_START_COMMAND, "allow", intent);
                int onStartCommand = super.onStartCommand(intent, i, i2);
                this.mCalledOnStartCommand = false;
                return onStartCommand;
            } catch (Throwable th) {
                this.mCalledOnStartCommand = false;
                throw th;
            }
        }
        getIntentLogger().logIntent(this.mEndpointName, SUB_ENDPOINT_ON_START_COMMAND, "deny", intent);
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

    public AbstractC03010kk getIntentLogger() {
        return C02640jT.A00;
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        if (!C02780jp.A01().A00(this, this, intent) || !shouldAllowIntent(intent)) {
            getIntentLogger().logIntent(this.mEndpointName, "onBind", "deny", intent);
            return null;
        }
        getIntentLogger().logIntent(this.mEndpointName, "onBind", "allow", intent);
        return null;
    }
}
