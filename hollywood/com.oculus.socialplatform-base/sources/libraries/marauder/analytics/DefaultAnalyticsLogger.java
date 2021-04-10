package libraries.marauder.analytics;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.TextView;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import libraries.debug.log.BLog;
import libraries.marauder.analytics.utils.concurrent.SerialExecutor;
import libraries.marauder.analytics.utils.lifecycle.BackgroundDetectorListener;
import libraries.marauder.analytics.utils.lifecycle.DelayedBackgroundDetector;

public class DefaultAnalyticsLogger implements AnalyticsLogger, BackgroundDetectorListener {
    public static final String EMPTY_ID = "0";
    public static final long LOW_LATENCY_DELAY_MS = 5000;
    public static final int MSG_SCHEDULE_BACKGROUND_WORKER = 1;
    public static final int MSG_STORE_CURRENT_BATCH = 2;
    public static final long STORE_BATCH_DELAY_MS = 15000;
    public static final String TAG = "DefaultAnalyticsLogger";
    public static final long UPLOAD_RETRY_DELAY_MS = 7200000;
    public final AlarmManager mAlarmManager;
    public final Runnable mAnalyticsBackgroundWorker;
    public final AtomicBoolean mAnalyticsWorkerScheduled = new AtomicBoolean(false);
    public final String mAppId;
    public final String mAppVersion;
    public final String mBuildNumber;
    public final Context mContext;
    public final String mDeviceId;
    public final SerialExecutor mExecutorService = new SerialExecutor(SerialExecutor.Builder.getInstance());
    public String mFacebookUserId;
    public boolean mFirstForegroundEventSeen;
    public final Handler mHandler = new Handler() {
        /* class libraries.marauder.analytics.DefaultAnalyticsLogger.AnonymousClass1 */

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                DefaultAnalyticsLogger.this.maybeScheduleBackgroundWorker();
            } else if (i == 2) {
                DefaultAnalyticsLogger defaultAnalyticsLogger = DefaultAnalyticsLogger.this;
                defaultAnalyticsLogger.queueEventRunnable(new StoreBatchRunnable());
                DefaultAnalyticsLogger defaultAnalyticsLogger2 = DefaultAnalyticsLogger.this;
                defaultAnalyticsLogger2.queueEventRunnable(new UploadRunnable());
            }
        }
    };
    public AnalyticsSession mLowLatencySession;
    public final Queue<Runnable> mPendingEventRunnables = new ConcurrentLinkedQueue();
    public AnalyticsSession mSession;
    public final AnalyticsStorage mStorage;
    public final AnalyticsUploader mUploader;
    public String mUserId;

    public class AnalyticsBackgroundWorker implements Runnable {
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0037  */
        /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void maybeUploadLowLatencySession() {
            /*
                r6 = this;
                libraries.marauder.analytics.DefaultAnalyticsLogger r0 = libraries.marauder.analytics.DefaultAnalyticsLogger.this
                libraries.marauder.analytics.AnalyticsSession r0 = r0.mLowLatencySession
                if (r0 == 0) goto L_0x0048
                long r4 = java.lang.System.currentTimeMillis()
                long r2 = r0.mTime
                r0 = 5000(0x1388, double:2.4703E-320)
                long r2 = r2 + r0
                int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                if (r0 < 0) goto L_0x0048
                java.lang.String r4 = "DefaultAnalyticsLogger"
                java.lang.String r0 = "Sending low-latency session"
                libraries.debug.log.BLog.v(r4, r0)
                r3 = 0
                libraries.marauder.analytics.DefaultAnalyticsLogger r0 = libraries.marauder.analytics.DefaultAnalyticsLogger.this     // Catch:{ IOException -> 0x002a }
                libraries.marauder.analytics.AnalyticsStorage r1 = r0.mStorage     // Catch:{ IOException -> 0x002a }
                libraries.marauder.analytics.AnalyticsSession r0 = r0.mLowLatencySession     // Catch:{ IOException -> 0x002a }
                java.io.File r2 = r1.store(r0)     // Catch:{ IOException -> 0x002a }
                libraries.marauder.analytics.DefaultAnalyticsLogger r0 = libraries.marauder.analytics.DefaultAnalyticsLogger.this     // Catch:{ IOException -> 0x002c }
                r0.mLowLatencySession = r3     // Catch:{ IOException -> 0x002c }
                goto L_0x0034
            L_0x002a:
                r1 = move-exception
                goto L_0x002e
            L_0x002c:
                r1 = move-exception
                r3 = r2
            L_0x002e:
                java.lang.String r0 = "Unable to store batch"
                libraries.debug.log.BLog.e(r4, r0, r1)
                goto L_0x0035
            L_0x0034:
                r3 = r2
            L_0x0035:
                if (r3 == 0) goto L_0x0048
                libraries.marauder.analytics.DefaultAnalyticsLogger r0 = libraries.marauder.analytics.DefaultAnalyticsLogger.this
                libraries.marauder.analytics.AnalyticsUploader r0 = r0.mUploader
                boolean r0 = r0.uploadBatch(r3)
                r1 = 0
                if (r0 == 0) goto L_0x0043
                r1 = 1
            L_0x0043:
                libraries.marauder.analytics.DefaultAnalyticsLogger r0 = libraries.marauder.analytics.DefaultAnalyticsLogger.this
                r0.afterUpload(r1)
            L_0x0048:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: libraries.marauder.analytics.DefaultAnalyticsLogger.AnalyticsBackgroundWorker.maybeUploadLowLatencySession():void");
        }

        public void run() {
            DefaultAnalyticsLogger.this.mAnalyticsWorkerScheduled.set(false);
            while (!DefaultAnalyticsLogger.this.mPendingEventRunnables.isEmpty()) {
                DefaultAnalyticsLogger.this.mPendingEventRunnables.remove().run();
            }
            maybeUploadLowLatencySession();
        }

        public AnalyticsBackgroundWorker() {
        }
    }

    public class EventRunnable implements Runnable {
        public IAnalyticsEvent mEvent;

        public void run() {
            DefaultAnalyticsLogger.this.preProcessEvent(this.mEvent);
            BLog.v(DefaultAnalyticsLogger.TAG, "New event %s.", this.mEvent);
            DefaultAnalyticsLogger.this.mSession.addEvent(this.mEvent);
            DefaultAnalyticsLogger.this.mHandler.removeMessages(2);
            if (DefaultAnalyticsLogger.this.mSession.mCurrentBatch.size() >= 50) {
                DefaultAnalyticsLogger.this.maybeStoreCurrentBatch();
            } else {
                DefaultAnalyticsLogger.this.mHandler.sendEmptyMessageDelayed(2, DefaultAnalyticsLogger.STORE_BATCH_DELAY_MS);
            }
        }

        public EventRunnable(IAnalyticsEvent iAnalyticsEvent) {
            this.mEvent = iAnalyticsEvent;
        }
    }

    public class FacebookEventRunnable implements Runnable {
        public final String mFacebookUserId;

        public FacebookEventRunnable(String str) {
            this.mFacebookUserId = str;
        }

        public void run() {
            DefaultAnalyticsLogger.this.setFacebookUserId(this.mFacebookUserId);
            DefaultAnalyticsLogger.this.startNewSession();
        }
    }

    public class LoginEventRunnable implements Runnable {
        public final String mFacebookUserId;
        public final String mUserId;

        public void run() {
            DefaultAnalyticsLogger.this.setUserId(this.mUserId);
            DefaultAnalyticsLogger.this.setFacebookUserId(this.mFacebookUserId);
            DefaultAnalyticsLogger.this.startNewSession();
        }

        public LoginEventRunnable(String str, String str2) {
            this.mUserId = str;
            this.mFacebookUserId = str2;
        }
    }

    public class LowLatencyEventRunnable implements Runnable {
        public final IAnalyticsEvent mEvent;

        public void run() {
            DefaultAnalyticsLogger.this.preProcessEvent(this.mEvent);
            BLog.v(DefaultAnalyticsLogger.TAG, "New low-latency event %s.", this.mEvent);
            if (DefaultAnalyticsLogger.this.mLowLatencySession == null) {
                BLog.v(DefaultAnalyticsLogger.TAG, "Starting new low-latency session");
                DefaultAnalyticsLogger defaultAnalyticsLogger = DefaultAnalyticsLogger.this;
                AnalyticsSession createSession = defaultAnalyticsLogger.createSession();
                defaultAnalyticsLogger.mLowLatencySession = createSession;
                createSession.mTime = System.currentTimeMillis();
                defaultAnalyticsLogger.mHandler.sendEmptyMessageDelayed(1, 5000);
            }
            DefaultAnalyticsLogger.this.mLowLatencySession.addEvent(this.mEvent);
        }

        public LowLatencyEventRunnable(IAnalyticsEvent iAnalyticsEvent) {
            this.mEvent = iAnalyticsEvent;
        }
    }

    public class NewSessionEventRunnable implements Runnable {
        public void run() {
            DefaultAnalyticsLogger.this.startNewSession();
        }

        public NewSessionEventRunnable() {
        }
    }

    public class StoreBatchRunnable implements Runnable {
        public void run() {
            DefaultAnalyticsLogger.this.maybeStoreCurrentBatch();
        }

        public StoreBatchRunnable() {
        }
    }

    public class UploadRunnable implements Runnable {
        public void run() {
            DefaultAnalyticsLogger.this.cancelUploadAlarm();
            if (DelayedBackgroundDetector.BackgroundDetectorHolder.INSTANCE.mIsAppBackgrounded) {
                boolean z = false;
                if (DefaultAnalyticsLogger.this.mUploader.uploadAllBatches()) {
                    z = true;
                }
                DefaultAnalyticsLogger.this.afterUpload(z);
            }
        }

        public UploadRunnable() {
        }
    }

    public void beforeUpload() {
    }

    public boolean isUploadAllowed() {
        return true;
    }

    @Override // libraries.marauder.analytics.AnalyticsLogger
    public void registerTextView(TextView textView) {
    }

    @Override // libraries.marauder.analytics.AnalyticsLogger
    public void reportLogout() {
        queueEventRunnable(new LoginEventRunnable(null, null));
    }

    @Override // libraries.marauder.analytics.AnalyticsLogger
    public void reportTouchEvent(MotionEvent motionEvent) {
    }

    @Override // libraries.marauder.analytics.AnalyticsLogger
    public void unregisterTextView(TextView textView) {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private AnalyticsSession createSession() {
        AnalyticsSession analyticsSession = new AnalyticsSession();
        analyticsSession.mAppVersion = this.mAppVersion;
        analyticsSession.mBuildNumber = this.mBuildNumber;
        analyticsSession.mFacebookUserId = this.mFacebookUserId;
        analyticsSession.mCustomUserId = this.mUserId;
        analyticsSession.mAppId = this.mAppId;
        analyticsSession.mDeviceId = this.mDeviceId;
        return analyticsSession;
    }

    private PendingIntent getPendingUploadIntent() {
        return PendingIntent.getBroadcast(this.mContext, 0, new Intent(this.mContext, AnalyticsUploadAlarmReceiver.class), 0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeScheduleBackgroundWorker() {
        if (this.mAnalyticsWorkerScheduled.compareAndSet(false, true)) {
            this.mExecutorService.execute(this.mAnalyticsBackgroundWorker);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void preProcessEvent(IAnalyticsEvent iAnalyticsEvent) {
        iAnalyticsEvent.setUserId(this.mUserId);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void queueEventRunnable(Runnable runnable) {
        this.mPendingEventRunnables.add(runnable);
        maybeScheduleBackgroundWorker();
    }

    private void queueNewSession() {
        queueEventRunnable(new NewSessionEventRunnable());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void startNewSession() {
        if (this.mSession != null) {
            maybeStoreCurrentBatch();
        }
        BLog.v(TAG, "Starting new session");
        this.mSession = createSession();
    }

    public void afterUpload(boolean z) {
        if (!z) {
            scheduleUploadAlarm();
        }
    }

    @Override // libraries.marauder.analytics.AnalyticsLogger
    public void flush() {
        queueEventRunnable(new StoreBatchRunnable());
    }

    public void maybeStoreCurrentBatch() {
        if (!this.mSession.mCurrentBatch.isEmpty()) {
            try {
                BLog.v(TAG, "Storing batch %s", this.mSession);
                this.mStorage.store(this.mSession);
                this.mSession.startNewBatch();
            } catch (IOException e) {
                BLog.e(TAG, "Unable to store batch", e);
            }
        }
    }

    @Override // libraries.marauder.analytics.utils.lifecycle.BackgroundDetectorListener
    public void onAppForegrounded() {
        if (!this.mFirstForegroundEventSeen) {
            this.mFirstForegroundEventSeen = true;
        } else {
            queueNewSession();
        }
    }

    @Override // libraries.marauder.analytics.AnalyticsLogger
    public void queueUpload() {
        queueEventRunnable(new UploadRunnable());
    }

    @Override // libraries.marauder.analytics.AnalyticsLogger
    public void reportEvent(IAnalyticsEvent iAnalyticsEvent) {
        queueEventRunnable(new EventRunnable(iAnalyticsEvent));
    }

    @Override // libraries.marauder.analytics.AnalyticsLogger
    public void reportLogin(String str, String str2) {
        queueEventRunnable(new LoginEventRunnable(str, str2));
    }

    @Override // libraries.marauder.analytics.AnalyticsLogger
    public void reportStarredEvent(IAnalyticsEvent iAnalyticsEvent) {
        queueEventRunnable(new LowLatencyEventRunnable(iAnalyticsEvent));
    }

    public DefaultAnalyticsLogger(Context context, String str, String str2, String str3, String str4, AnalyticsUploader analyticsUploader, String str5, String str6) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mAlarmManager = (AlarmManager) applicationContext.getSystemService("alarm");
        this.mDeviceId = str;
        this.mBuildNumber = str3;
        this.mAppVersion = str2;
        this.mAppId = str4;
        setUserId(str5);
        setFacebookUserId(str6);
        this.mAnalyticsBackgroundWorker = new AnalyticsBackgroundWorker();
        this.mStorage = new AnalyticsStorage(context.getApplicationContext());
        this.mUploader = analyticsUploader;
        DelayedBackgroundDetector.BackgroundDetectorHolder.INSTANCE.addListener(this);
        startNewSession();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void cancelUploadAlarm() {
        PendingIntent pendingUploadIntent = getPendingUploadIntent();
        if (pendingUploadIntent != null) {
            this.mAlarmManager.cancel(pendingUploadIntent);
        }
    }

    public static String normalizeUserId(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        return str;
    }

    private void scheduleUploadAlarm() {
        this.mAlarmManager.set(2, SystemClock.elapsedRealtime() + UPLOAD_RETRY_DELAY_MS, getPendingUploadIntent());
        BLog.d(DefaultAnalyticsLogger.class, "scheduleUploadAlarm()");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setFacebookUserId(String str) {
        this.mFacebookUserId = normalizeUserId(str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setUserId(String str) {
        this.mUserId = normalizeUserId(str);
    }

    @Override // libraries.marauder.analytics.utils.lifecycle.BackgroundDetectorListener
    public void onAppBackgrounded() {
        queueNewSession();
        queueUpload();
    }
}
