package X;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import javax.annotation.Nullable;

/* renamed from: X.0jl  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractIntentServiceC02740jl extends IntentService {
    public static final String SUB_ENDPOINT_ON_BIND = "onBind";
    public static final String SUB_ENDPOINT_ON_HANDLE_INTENT = "onHandleIntent";
    public String mEndpointName;
    public AnonymousClass0kS mPermissionChecker = AnonymousClass0TP.A01;
    public final String name;

    public synchronized void addAdditionalPermissionChecker(AnonymousClass0kS r2) {
        this.mPermissionChecker = this.mPermissionChecker.A1R(r2);
    }

    @Nullable
    public IBinder doBind(Intent intent) {
        return null;
    }

    @Nullable
    public AbstractC02660jW getReporter() {
        return null;
    }

    public abstract void onSecuredHandleIntent(@Nullable Intent intent);

    public final synchronized boolean shouldAllowIntent(Intent intent) {
        return this.mPermissionChecker.AAL(this, this, intent, null);
    }

    public final void onHandleIntent(@Nullable Intent intent) {
        if (intent == null || (C02780jp.A01().A00(this, this, intent) && shouldAllowIntent(intent))) {
            getIntentLogger().logIntent(this.mEndpointName, SUB_ENDPOINT_ON_HANDLE_INTENT, "allow", intent);
            throw new NullPointerException("onSecuredHandleIntent");
        } else {
            getIntentLogger().logIntent(this.mEndpointName, SUB_ENDPOINT_ON_HANDLE_INTENT, "deny", intent);
        }
    }

    public AbstractIntentServiceC02740jl(String str) {
        super(str);
        this.name = str;
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

    public void onCreate() {
        super.onCreate();
        this.mEndpointName = String.format("%s/%s", getPackageName(), getClass().getName());
    }
}
