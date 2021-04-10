package com.oculus.gatekeeperservice;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import com.oculus.aidl.IGkService;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.concurrent.TimeUnit;
import oculus.internal.Constants;

public class CacheUpdaterJobService extends JobService {
    private static final long BACKOFF_MILLIS = TimeUnit.MINUTES.toMillis(2);
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String EXTRA_USER_ID = "user_id";
    private static final long FLEX_MILLIS = (PERIOD_MILLIS - TimeUnit.SECONDS.toMillis(15));
    private static final String GKSERVICE_SERVICE_NAME = "com.oculus.config.service.GkService";
    private static final int JOB_ID = 42;
    private static final long PERIOD_MILLIS = TimeUnit.HOURS.toMillis(2);
    private static final String TAG = "GatekeeperService";
    private UnifiedTelemetryLogger mLogger;

    public boolean onStartJob(JobParameters params) {
        int userId = getUserId(params.getExtras());
        UserHandle handle = UserHandle.of(userId);
        GkServiceConnection conn = new GkServiceConnection(userId, new GkCacheStorage(this, userId), params);
        this.mLogger = UnifiedTelemetryLogger.getInstance(this);
        Intent gkService = new Intent();
        gkService.setComponent(new ComponentName(Constants.HORIZON_PACKAGE, GKSERVICE_SERVICE_NAME));
        if (bindServiceAsUser(gkService, conn, 1, handle)) {
            return true;
        }
        jobFinished(params, true);
        return DEBUG;
    }

    public boolean onStopJob(JobParameters params) {
        return DEBUG;
    }

    private class GkServiceConnection implements ServiceConnection {
        private final GkCacheStorage mGkCacheStorage;
        private final JobParameters mJobParams;
        private final int mUserId;

        GkServiceConnection(int userId, GkCacheStorage storage, JobParameters jobParams) {
            this.mUserId = userId;
            this.mGkCacheStorage = storage;
            this.mJobParams = jobParams;
        }

        public void onServiceConnected(ComponentName name, IBinder binder) {
            try {
                IGkService gkService = IGkService.Stub.asInterface(binder);
                for (String gk : this.mGkCacheStorage.getDynamicGksList()) {
                    gkService.registerGatekeeper(gk);
                }
                gkService.fetchGatekeepers(TimeUnit.SECONDS.toMillis(5));
                String[] gksList = this.mGkCacheStorage.getGksList();
                for (String gk2 : gksList) {
                    if (!CacheUpdaterJobService.doesGkExist(gkService, gk2)) {
                        this.mGkCacheStorage.remove(gk2);
                    } else {
                        this.mGkCacheStorage.add(gk2, gkService.getGatekeeper(gk2));
                    }
                }
                CacheUpdaterJobService.this.unbindService(this);
                CacheUpdaterJobService.this.jobFinished(this.mJobParams, CacheUpdaterJobService.DEBUG);
                if (CacheUpdaterJobService.DEBUG) {
                    Log.d(CacheUpdaterJobService.TAG, "GK cache updated. user=" + this.mUserId);
                }
            } catch (Exception e) {
                Log.w(CacheUpdaterJobService.TAG, "Failed to fetch gks from Horizon.", e);
                CacheUpdaterJobService.this.logTelemetry(e.getMessage());
                CacheUpdaterJobService.this.unbindService(this);
                CacheUpdaterJobService.this.jobFinished(this.mJobParams, CacheUpdaterJobService.DEBUG);
            } catch (Throwable th) {
                CacheUpdaterJobService.this.unbindService(this);
                CacheUpdaterJobService.this.jobFinished(this.mJobParams, CacheUpdaterJobService.DEBUG);
                throw th;
            }
        }

        public void onServiceDisconnected(ComponentName name) {
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logTelemetry(String msg) {
        if (this.mLogger != null) {
            AnalyticsEvent event = new AnalyticsEvent("oculus_mobile_os_exception");
            event.setExtra("message", msg).setExtra("id", "GK_FETCH");
            this.mLogger.reportEvent(event, true);
        }
    }

    /* access modifiers changed from: private */
    public static boolean doesGkExist(IGkService service, String gk) throws RemoteException {
        if (!service.getGatekeeperDef(gk, true) || service.getGatekeeperDef(gk, DEBUG)) {
            return true;
        }
        return DEBUG;
    }

    private static int getUserId(PersistableBundle extras) {
        if (extras == null) {
            return -1;
        }
        return extras.getInt(EXTRA_USER_ID, -1);
    }

    public static void scheduleJob(Context context, int userId) {
        PersistableBundle extras = new PersistableBundle();
        extras.putInt(EXTRA_USER_ID, userId);
        ((JobScheduler) context.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(42, new ComponentName(context, CacheUpdaterJobService.class)).setPeriodic(PERIOD_MILLIS, FLEX_MILLIS).setBackoffCriteria(BACKOFF_MILLIS, 1).setExtras(extras).build());
    }
}
