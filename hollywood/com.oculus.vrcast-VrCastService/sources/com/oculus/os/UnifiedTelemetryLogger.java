package com.oculus.os;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.Log;
import com.apple.dnssd.DNSSD;
import com.oculus.aidl.IUnifiedTelemetryService;
import com.oculus.os.FunnelData;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class UnifiedTelemetryLogger implements IBinder.DeathRecipient {
    private static final String TAG = "UnifiedTelemetryLogger";
    private static UnifiedTelemetryLogger mUnifiedTelemetryLogger;
    private volatile Handler mBackgroundHandler;
    private Context mContext;
    private final AtomicBoolean mDuplicateLogging = new AtomicBoolean(false);
    private final ConcurrentLinkedQueue<AnalyticsEvent> mEvents = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<AnalyticsEvent> mExposureEvents = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<FunnelData> mFunnelDataQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<String, Integer> mFunnelRegistry = new ConcurrentHashMap<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private HandlerThread mHandlerThread;
    private volatile boolean mIsInitialized;
    private final ConcurrentLinkedQueue<AnalyticsEvent> mLowLatencyEvents = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<AnalyticsEvent> mLowLatencyExposureEvents = new ConcurrentLinkedQueue<>();
    private String mPackageName;
    private final ConcurrentLinkedQueue<SessionRegistrationData> mSessionsToStart = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<SessionRegistrationData> mSessionsToStop = new ConcurrentLinkedQueue<>();
    private volatile IUnifiedTelemetryService mUnifiedTelemetryService = null;
    private final AtomicBoolean mUseUnifiedTelemetryService = new AtomicBoolean(false);

    /* access modifiers changed from: private */
    public enum MessageType {
        FETCH_GATEKEEPERS,
        REPORT_LOW_LATENCY_EVENTS,
        REPORT_EVENTS,
        REPORT_LOW_LATENCY_EXPOSURE,
        REPORT_EXPOSURE,
        REGISTER_FUNNEL,
        REPORT_FUNNEL_DATA,
        START_SESSION,
        STOP_SESSION
    }

    /* access modifiers changed from: private */
    public static class SessionRegistrationData {
        private final String mSessionId;
        private final String mSessionName;
        private final int mUserId;
    }

    private UnifiedTelemetryLogger() {
    }

    public static UnifiedTelemetryLogger getInstance(Context context) {
        if (mUnifiedTelemetryLogger == null) {
            Log.d(TAG, "UnifiedTelemetryLogger instance created");
            mUnifiedTelemetryLogger = new UnifiedTelemetryLogger();
            mUnifiedTelemetryLogger.initInternal(context);
        }
        return mUnifiedTelemetryLogger;
    }

    private synchronized void initInternal(Context context) {
        if (this.mIsInitialized) {
            Log.d(TAG, "Already initialized.");
        } else if (context != null) {
            this.mContext = context.getApplicationContext();
            this.mPackageName = context.getPackageName();
            this.mIsInitialized = true;
            bindToService();
        } else {
            throw new NullPointerException("Context is null");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void bindToService() {
        try {
            this.mContext.getPackageManager().getPackageInfo("com.oculus.unifiedtelemetry", 0);
            Log.d(TAG, "binding with UnifiedTelemetryService");
            IBinder service = ServiceManager.getService("UnifiedTelemetryService");
            this.mUnifiedTelemetryService = IUnifiedTelemetryService.Stub.asInterface(service);
            if (this.mUnifiedTelemetryService != null) {
                try {
                    service.linkToDeath(this, 0);
                } catch (RemoteException e) {
                    Log.e(TAG, "linkToDeath failed", e);
                }
                this.mHandlerThread = new HandlerThread(TAG);
                this.mHandlerThread.start();
                this.mBackgroundHandler = new MessageHandler(this.mHandlerThread.getLooper());
                post(MessageType.FETCH_GATEKEEPERS);
                flushData();
                return;
            }
            Log.e(TAG, "Failed to get UnifiedTelemetryService");
            this.mHandler.postDelayed(new Runnable() {
                /* class com.oculus.os.UnifiedTelemetryLogger.AnonymousClass1 */

                public void run() {
                    UnifiedTelemetryLogger.this.bindToService();
                }
            }, 30000);
        } catch (PackageManager.NameNotFoundException e2) {
            Log.d(TAG, "UnifiedTelemetry package com.oculus.unifiedtelemetry does not exist", e2);
        }
    }

    public void binderDied() {
        Log.i(TAG, "Connection to service is broken");
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        this.mUnifiedTelemetryService = null;
        bindToService();
    }

    private void post(MessageType messageType) {
        postWithDelay(messageType, 0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void postWithDelay(MessageType messageType, long j) {
        Handler handler = this.mBackgroundHandler;
        if (handler != null) {
            handler.sendMessageDelayed(handler.obtainMessage(messageType.ordinal()), j);
        }
    }

    private void flushData() {
        post(MessageType.REPORT_LOW_LATENCY_EVENTS);
        post(MessageType.REPORT_LOW_LATENCY_EXPOSURE);
        post(MessageType.REPORT_EVENTS);
        post(MessageType.REPORT_EXPOSURE);
        post(MessageType.REGISTER_FUNNEL);
        post(MessageType.REPORT_FUNNEL_DATA);
        post(MessageType.START_SESSION);
        post(MessageType.STOP_SESSION);
    }

    private void postReportEvents(List<AnalyticsEvent> list, boolean z, ConcurrentLinkedQueue<AnalyticsEvent> concurrentLinkedQueue) {
        for (AnalyticsEvent analyticsEvent : list) {
            if (analyticsEvent != null) {
                analyticsEvent.setExtra("utl_logging_package", this.mPackageName);
                concurrentLinkedQueue.add(analyticsEvent);
            }
        }
        post(z ? MessageType.REPORT_LOW_LATENCY_EVENTS : MessageType.REPORT_EVENTS);
    }

    public void reportEvent(AnalyticsEvent analyticsEvent, boolean z) {
        postReportEvents(Collections.singletonList(analyticsEvent), z, z ? this.mLowLatencyEvents : this.mEvents);
    }

    /* access modifiers changed from: private */
    public class MessageHandler extends Handler {
        MessageHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            IUnifiedTelemetryService iUnifiedTelemetryService = UnifiedTelemetryLogger.this.mUnifiedTelemetryService;
            if (iUnifiedTelemetryService == null) {
                Log.e(UnifiedTelemetryLogger.TAG, "WARNING: unifiedTelemetryService cannot be empty, dropping message");
                return;
            }
            try {
                switch (AnonymousClass2.$SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType[MessageType.values()[message.what].ordinal()]) {
                    case 1:
                        fetchGatekeepers(iUnifiedTelemetryService);
                        return;
                    case 2:
                        reportEvents(iUnifiedTelemetryService, UnifiedTelemetryLogger.this.mLowLatencyEvents, true);
                        return;
                    case 3:
                        reportEvents(iUnifiedTelemetryService, UnifiedTelemetryLogger.this.mEvents, false);
                        return;
                    case 4:
                        reportExposure(iUnifiedTelemetryService, UnifiedTelemetryLogger.this.mLowLatencyExposureEvents, true);
                        return;
                    case 5:
                        reportExposure(iUnifiedTelemetryService, UnifiedTelemetryLogger.this.mExposureEvents, false);
                        return;
                    case 6:
                        registerFunnels(iUnifiedTelemetryService);
                        return;
                    case 7:
                        reportFunnelData(iUnifiedTelemetryService);
                        return;
                    case DNSSD.NO_AUTO_RENAME:
                        startSessions(iUnifiedTelemetryService);
                        return;
                    case 9:
                        stopSessions(iUnifiedTelemetryService);
                        return;
                    default:
                        return;
                }
            } catch (RemoteException e) {
                Log.e(UnifiedTelemetryLogger.TAG, "RemoteException", e);
            }
        }

        private void fetchGatekeepers(IUnifiedTelemetryService iUnifiedTelemetryService) throws RemoteException {
            UnifiedTelemetryLogger.this.mDuplicateLogging.set(iUnifiedTelemetryService.shouldDuplicateLogging(UnifiedTelemetryLogger.this.mPackageName));
            UnifiedTelemetryLogger.this.mUseUnifiedTelemetryService.set(iUnifiedTelemetryService.shouldUseUnifiedTelemetryService(UnifiedTelemetryLogger.this.mPackageName));
            UnifiedTelemetryLogger.this.postWithDelay(MessageType.FETCH_GATEKEEPERS, 60000 - ((long) (Calendar.getInstance().get(13) * 1000)));
        }

        private int getBundleSize(PersistableBundle persistableBundle) {
            Parcel obtain = Parcel.obtain();
            obtain.writePersistableBundle(persistableBundle);
            int dataSize = obtain.dataSize();
            obtain.recycle();
            return dataSize;
        }

        private void reportEvents(IUnifiedTelemetryService iUnifiedTelemetryService, ConcurrentLinkedQueue<AnalyticsEvent> concurrentLinkedQueue, boolean z) throws RemoteException {
            PersistableBundle persistableBundle = new PersistableBundle();
            int i = 0;
            while (true) {
                AnalyticsEvent poll = concurrentLinkedQueue.poll();
                if (poll == null) {
                    break;
                }
                int length = poll.getName().getBytes(StandardCharsets.UTF_8).length + getBundleSize(poll.getExtras());
                if ((i + length >= 512000 || persistableBundle.getPersistableBundle(poll.getName()) != null) && persistableBundle.size() > 0) {
                    iUnifiedTelemetryService.reportEvents(persistableBundle, z);
                    persistableBundle = new PersistableBundle();
                    i = 0;
                }
                i += length;
                persistableBundle.putPersistableBundle(poll.getName(), poll.getExtras());
            }
            if (persistableBundle.size() > 0) {
                iUnifiedTelemetryService.reportEvents(persistableBundle, z);
            }
        }

        private void reportExposure(IUnifiedTelemetryService iUnifiedTelemetryService, ConcurrentLinkedQueue<AnalyticsEvent> concurrentLinkedQueue, boolean z) throws RemoteException {
            AnalyticsEvent peek = concurrentLinkedQueue.peek();
            while (peek != null) {
                iUnifiedTelemetryService.logExposure(peek.getModuleName(), peek.getName(), peek.getInternalUseParams(), peek.getExtras(), z);
                concurrentLinkedQueue.poll();
                peek = concurrentLinkedQueue.peek();
            }
        }

        private void registerFunnels(IUnifiedTelemetryService iUnifiedTelemetryService) throws RemoteException {
            for (Map.Entry entry : UnifiedTelemetryLogger.this.mFunnelRegistry.entrySet()) {
                iUnifiedTelemetryService.registerFunnel((String) entry.getKey(), ((Integer) entry.getValue()).intValue());
            }
        }

        private void reportFunnelData(IUnifiedTelemetryService iUnifiedTelemetryService) throws RemoteException {
            FunnelData funnelData = (FunnelData) UnifiedTelemetryLogger.this.mFunnelDataQueue.peek();
            while (funnelData != null) {
                String str = funnelData.mFunnelName;
                if (TextUtils.isEmpty(str)) {
                    Log.e(UnifiedTelemetryLogger.TAG, "Funnel data with no funnel name found, discarding data.");
                    UnifiedTelemetryLogger.this.mFunnelDataQueue.poll();
                    funnelData = (FunnelData) UnifiedTelemetryLogger.this.mFunnelDataQueue.peek();
                } else {
                    int i = AnonymousClass2.$SwitchMap$com$oculus$os$FunnelData$Type[funnelData.mType.ordinal()];
                    if (i == 1) {
                        Long l = funnelData.mInstanceId;
                        if (l != null) {
                            iUnifiedTelemetryService.startFunnelWithId(str, l.longValue());
                        } else {
                            iUnifiedTelemetryService.startFunnel(str);
                        }
                    } else if (i != 2) {
                        if (i == 3) {
                            Long l2 = funnelData.mInstanceId;
                            if (l2 != null) {
                                iUnifiedTelemetryService.endFunnelWithId(str, l2.longValue());
                            } else {
                                iUnifiedTelemetryService.endFunnel(str);
                            }
                        } else {
                            throw new IllegalStateException("Unknown funnel data type. Should never happen.");
                        }
                    } else if (TextUtils.isEmpty(funnelData.mActionName)) {
                        String str2 = UnifiedTelemetryLogger.TAG;
                        Log.e(str2, "Funnel data for funnel " + str + " with type action with no action name found, discarding data.");
                    } else {
                        Long l3 = funnelData.mInstanceId;
                        if (l3 != null) {
                            iUnifiedTelemetryService.reportFunnelActionWithId(str, l3.longValue(), funnelData.mActionName, funnelData.mTag, funnelData.mActionData);
                        } else {
                            iUnifiedTelemetryService.reportFunnelAction(str, funnelData.mActionName, funnelData.mTag, funnelData.mActionData);
                        }
                    }
                    UnifiedTelemetryLogger.this.mFunnelDataQueue.poll();
                    funnelData = (FunnelData) UnifiedTelemetryLogger.this.mFunnelDataQueue.peek();
                }
            }
        }

        private void startSessions(IUnifiedTelemetryService iUnifiedTelemetryService) throws RemoteException {
            while (!UnifiedTelemetryLogger.this.mSessionsToStart.isEmpty()) {
                SessionRegistrationData sessionRegistrationData = (SessionRegistrationData) UnifiedTelemetryLogger.this.mSessionsToStart.poll();
                iUnifiedTelemetryService.startSession(sessionRegistrationData.mSessionName, sessionRegistrationData.mSessionId, sessionRegistrationData.mUserId);
            }
        }

        private void stopSessions(IUnifiedTelemetryService iUnifiedTelemetryService) throws RemoteException {
            while (!UnifiedTelemetryLogger.this.mSessionsToStop.isEmpty()) {
                SessionRegistrationData sessionRegistrationData = (SessionRegistrationData) UnifiedTelemetryLogger.this.mSessionsToStop.poll();
                iUnifiedTelemetryService.stopSession(sessionRegistrationData.mSessionName, sessionRegistrationData.mUserId);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.os.UnifiedTelemetryLogger$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$FunnelData$Type = new int[FunnelData.Type.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType = new int[MessageType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0051 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0067 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0072 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0089 */
        static {
            /*
            // Method dump skipped, instructions count: 150
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.UnifiedTelemetryLogger.AnonymousClass2.<clinit>():void");
        }
    }
}
