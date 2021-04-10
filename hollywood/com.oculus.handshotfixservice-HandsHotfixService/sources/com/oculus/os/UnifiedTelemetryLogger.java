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
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.aidl.IUnifiedTelemetryService;
import com.oculus.os.FunnelData;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class UnifiedTelemetryLogger implements IBinder.DeathRecipient {
    private static final long GATEKEEPER_REFRESH_INTERVAL = 60000;
    private static final int MAX_BUNDLE_SIZE = 512000;
    private static final long RETRY_BIND_INTERVAL = 30000;
    private static final String TAG = UnifiedTelemetryLogger.class.getSimpleName();
    private static final String UNIFIED_TELEMETRY_PACKAGE_NAME = "com.oculus.unifiedtelemetry";
    private static final String UNIFIED_TELEMETRY_SERVICE = "UnifiedTelemetryService";
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

    @Deprecated
    public static synchronized UnifiedTelemetryLogger getInstance() {
        UnifiedTelemetryLogger unifiedTelemetryLogger;
        synchronized (UnifiedTelemetryLogger.class) {
            if (mUnifiedTelemetryLogger == null) {
                Log.d(TAG, "UnifiedTelemetryLogger instance created");
                mUnifiedTelemetryLogger = new UnifiedTelemetryLogger();
            }
            unifiedTelemetryLogger = mUnifiedTelemetryLogger;
        }
        return unifiedTelemetryLogger;
    }

    @Deprecated
    public synchronized void init(Context context) {
        initInternal(context);
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
            this.mContext.getPackageManager().getPackageInfo(UNIFIED_TELEMETRY_PACKAGE_NAME, 0);
            Log.d(TAG, "binding with UnifiedTelemetryService");
            IBinder binderInterface = ServiceManager.getService(UNIFIED_TELEMETRY_SERVICE);
            this.mUnifiedTelemetryService = IUnifiedTelemetryService.Stub.asInterface(binderInterface);
            if (this.mUnifiedTelemetryService != null) {
                try {
                    binderInterface.linkToDeath(this, 0);
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
            }, RETRY_BIND_INTERVAL);
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
    private void postWithDelay(MessageType messageType, long delay) {
        Handler handler = this.mBackgroundHandler;
        if (handler != null) {
            handler.sendMessageDelayed(handler.obtainMessage(messageType.ordinal()), delay);
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

    private void postReportEvents(List<AnalyticsEvent> analyticsEvents, boolean lowLatency, ConcurrentLinkedQueue<AnalyticsEvent> queue) {
        for (AnalyticsEvent analyticsEvent : analyticsEvents) {
            if (analyticsEvent != null) {
                analyticsEvent.setExtra("utl_logging_package", this.mPackageName);
                queue.add(analyticsEvent);
            }
        }
        post(lowLatency ? MessageType.REPORT_LOW_LATENCY_EVENTS : MessageType.REPORT_EVENTS);
    }

    public void reportEvent(AnalyticsEvent analyticsEvent, boolean lowLatency) {
        postReportEvents(Collections.singletonList(analyticsEvent), lowLatency, lowLatency ? this.mLowLatencyEvents : this.mEvents);
    }

    public void reportEvents(List<AnalyticsEvent> analyticsEvents, boolean lowLatency) {
        postReportEvents(analyticsEvents, lowLatency, lowLatency ? this.mLowLatencyEvents : this.mEvents);
    }

    private void postLogExposure(AnalyticsEvent analyticsEvent, boolean lowLatency, ConcurrentLinkedQueue<AnalyticsEvent> queue) {
        if (analyticsEvent != null) {
            queue.add(analyticsEvent);
        }
        post(lowLatency ? MessageType.REPORT_LOW_LATENCY_EXPOSURE : MessageType.REPORT_EXPOSURE);
    }

    public void logExposure(AnalyticsEvent analyticsEvent, boolean lowLatency) {
        if (this.mIsInitialized) {
            postLogExposure(analyticsEvent, lowLatency, lowLatency ? this.mLowLatencyExposureEvents : this.mExposureEvents);
        }
    }

    public void registerFunnel(String funnelName, int secondsToEndSinceLastUpdate) {
        if (this.mIsInitialized) {
            this.mFunnelRegistry.put(funnelName, Integer.valueOf(secondsToEndSinceLastUpdate));
            post(MessageType.REGISTER_FUNNEL);
        }
    }

    private String generateSessionId() {
        return UUID.randomUUID().toString();
    }

    public String startSession(String sessionName) {
        if (!this.mIsInitialized) {
            return null;
        }
        if (sessionName != null) {
            String sessionId = generateSessionId();
            this.mSessionsToStart.add(new SessionRegistrationData(sessionName, UserHandle.myUserId(), sessionId));
            post(MessageType.START_SESSION);
            return sessionId;
        }
        throw new NullPointerException("sessionName is null");
    }

    public String startSessionAsUser(String sessionName, UserHandle user) {
        if (!this.mIsInitialized) {
            return null;
        }
        if (sessionName == null) {
            throw new NullPointerException("sessionName is null");
        } else if (user != null) {
            String sessionId = generateSessionId();
            this.mSessionsToStart.add(new SessionRegistrationData(sessionName, user.getIdentifier(), sessionId));
            post(MessageType.START_SESSION);
            return sessionId;
        } else {
            throw new NullPointerException("user is null");
        }
    }

    public void stopSession(String sessionName) {
        if (this.mIsInitialized) {
            if (sessionName != null) {
                this.mSessionsToStop.add(new SessionRegistrationData(sessionName, UserHandle.myUserId()));
                post(MessageType.STOP_SESSION);
                return;
            }
            throw new NullPointerException("sessionName is null");
        }
    }

    public void stopSessionAsUser(String sessionName, UserHandle user) {
        if (this.mIsInitialized) {
            if (sessionName == null) {
                throw new NullPointerException("sessionName is null");
            } else if (user != null) {
                this.mSessionsToStop.add(new SessionRegistrationData(sessionName, user.getIdentifier()));
                post(MessageType.STOP_SESSION);
            } else {
                throw new NullPointerException("user is null");
            }
        }
    }

    public void stopSessionForAllUsers(String sessionName) {
        stopSessionAsUser(sessionName, UserHandle.ALL);
    }

    private void postFunnelData(FunnelData funnelData) {
        if (funnelData != null) {
            this.mFunnelDataQueue.add(funnelData);
        }
        post(MessageType.REPORT_FUNNEL_DATA);
    }

    public void startFunnel(String funnelName) {
        if (this.mIsInitialized) {
            postFunnelData(new FunnelData(FunnelData.Type.START, funnelName));
        }
    }

    public void startFunnel(String funnelName, long instanceId) {
        if (this.mIsInitialized) {
            postFunnelData(new FunnelData(FunnelData.Type.START, funnelName, instanceId));
        }
    }

    public void reportFunnelAction(String funnelName, String actionName, String tag, PersistableBundle actionData) {
        if (this.mIsInitialized) {
            postFunnelData(new FunnelData(funnelName, actionName, tag, actionData));
        }
    }

    public void reportFunnelAction(String funnelName, long instanceId, String actionName, String tag, PersistableBundle actionData) {
        if (this.mIsInitialized) {
            postFunnelData(new FunnelData(funnelName, instanceId, actionName, tag, actionData));
        }
    }

    public void endFunnel(String funnelName) {
        if (this.mIsInitialized) {
            postFunnelData(new FunnelData(FunnelData.Type.END, funnelName));
        }
    }

    public void endFunnel(String funnelName, long instanceId) {
        if (this.mIsInitialized) {
            postFunnelData(new FunnelData(FunnelData.Type.END, funnelName, instanceId));
        }
    }

    public boolean shouldDuplicateLogging() {
        if (!this.mIsInitialized) {
            return false;
        }
        return this.mDuplicateLogging.get();
    }

    public boolean shouldUseUnifiedTelemetryService() {
        if (!this.mIsInitialized) {
            return false;
        }
        return this.mUseUnifiedTelemetryService.get();
    }

    /* access modifiers changed from: private */
    public class MessageHandler extends Handler {
        MessageHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            IUnifiedTelemetryService unifiedTelemetryService = UnifiedTelemetryLogger.this.mUnifiedTelemetryService;
            if (unifiedTelemetryService == null) {
                Log.e(UnifiedTelemetryLogger.TAG, "WARNING: unifiedTelemetryService cannot be empty, dropping message");
                return;
            }
            try {
                switch (MessageType.values()[msg.what]) {
                    case FETCH_GATEKEEPERS:
                        fetchGatekeepers(unifiedTelemetryService);
                        return;
                    case REPORT_LOW_LATENCY_EVENTS:
                        reportEvents(unifiedTelemetryService, UnifiedTelemetryLogger.this.mLowLatencyEvents, true);
                        return;
                    case REPORT_EVENTS:
                        reportEvents(unifiedTelemetryService, UnifiedTelemetryLogger.this.mEvents, false);
                        return;
                    case REPORT_LOW_LATENCY_EXPOSURE:
                        reportExposure(unifiedTelemetryService, UnifiedTelemetryLogger.this.mLowLatencyExposureEvents, true);
                        return;
                    case REPORT_EXPOSURE:
                        reportExposure(unifiedTelemetryService, UnifiedTelemetryLogger.this.mExposureEvents, false);
                        return;
                    case REGISTER_FUNNEL:
                        registerFunnels(unifiedTelemetryService);
                        return;
                    case REPORT_FUNNEL_DATA:
                        reportFunnelData(unifiedTelemetryService);
                        return;
                    case START_SESSION:
                        startSessions(unifiedTelemetryService);
                        return;
                    case STOP_SESSION:
                        stopSessions(unifiedTelemetryService);
                        return;
                    default:
                        return;
                }
            } catch (RemoteException e) {
                Log.e(UnifiedTelemetryLogger.TAG, "RemoteException", e);
            }
        }

        private void fetchGatekeepers(IUnifiedTelemetryService unifiedTelemetryService) throws RemoteException {
            UnifiedTelemetryLogger.this.mDuplicateLogging.set(unifiedTelemetryService.shouldDuplicateLogging(UnifiedTelemetryLogger.this.mPackageName));
            UnifiedTelemetryLogger.this.mUseUnifiedTelemetryService.set(unifiedTelemetryService.shouldUseUnifiedTelemetryService(UnifiedTelemetryLogger.this.mPackageName));
            UnifiedTelemetryLogger.this.postWithDelay(MessageType.FETCH_GATEKEEPERS, UnifiedTelemetryLogger.GATEKEEPER_REFRESH_INTERVAL - ((long) (Calendar.getInstance().get(13) * 1000)));
        }

        private int getBundleSize(PersistableBundle bundle) {
            Parcel parcel = Parcel.obtain();
            parcel.writePersistableBundle(bundle);
            int size = parcel.dataSize();
            parcel.recycle();
            return size;
        }

        private void reportEvents(IUnifiedTelemetryService unifiedTelemetryService, ConcurrentLinkedQueue<AnalyticsEvent> queue, boolean lowLatency) throws RemoteException {
            PersistableBundle bundle = new PersistableBundle();
            int totalSize = 0;
            while (true) {
                AnalyticsEvent event = queue.poll();
                if (event == null) {
                    break;
                }
                int sizeAddition = event.getName().getBytes(StandardCharsets.UTF_8).length + getBundleSize(event.getExtras());
                if ((totalSize + sizeAddition >= UnifiedTelemetryLogger.MAX_BUNDLE_SIZE || bundle.getPersistableBundle(event.getName()) != null) && bundle.size() > 0) {
                    unifiedTelemetryService.reportEvents(bundle, lowLatency);
                    totalSize = 0;
                    bundle = new PersistableBundle();
                }
                totalSize += sizeAddition;
                bundle.putPersistableBundle(event.getName(), event.getExtras());
            }
            if (bundle.size() > 0) {
                unifiedTelemetryService.reportEvents(bundle, lowLatency);
            }
        }

        private void reportExposure(IUnifiedTelemetryService unifiedTelemetryService, ConcurrentLinkedQueue<AnalyticsEvent> queue, boolean lowLatency) throws RemoteException {
            AnalyticsEvent exposureEvent = queue.peek();
            while (exposureEvent != null) {
                unifiedTelemetryService.logExposure(exposureEvent.getModuleName(), exposureEvent.getName(), exposureEvent.getInternalUseParams(), exposureEvent.getExtras(), lowLatency);
                queue.poll();
                exposureEvent = queue.peek();
            }
        }

        private void registerFunnels(IUnifiedTelemetryService unifiedTelemetryService) throws RemoteException {
            for (Map.Entry funnelRegistryEntry : UnifiedTelemetryLogger.this.mFunnelRegistry.entrySet()) {
                unifiedTelemetryService.registerFunnel((String) funnelRegistryEntry.getKey(), ((Integer) funnelRegistryEntry.getValue()).intValue());
            }
        }

        private void reportFunnelData(IUnifiedTelemetryService unifiedTelemetryService) throws RemoteException {
            FunnelData funnelData = (FunnelData) UnifiedTelemetryLogger.this.mFunnelDataQueue.peek();
            while (funnelData != null) {
                String funnelName = funnelData.mFunnelName;
                if (TextUtils.isEmpty(funnelName)) {
                    Log.e(UnifiedTelemetryLogger.TAG, "Funnel data with no funnel name found, discarding data.");
                    UnifiedTelemetryLogger.this.mFunnelDataQueue.poll();
                    funnelData = (FunnelData) UnifiedTelemetryLogger.this.mFunnelDataQueue.peek();
                } else {
                    int i = AnonymousClass2.$SwitchMap$com$oculus$os$FunnelData$Type[funnelData.mType.ordinal()];
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3) {
                                throw new IllegalStateException("Unknown funnel data type. Should never happen.");
                            } else if (funnelData.mInstanceId != null) {
                                unifiedTelemetryService.endFunnelWithId(funnelName, funnelData.mInstanceId.longValue());
                            } else {
                                unifiedTelemetryService.endFunnel(funnelName);
                            }
                        } else if (TextUtils.isEmpty(funnelData.mActionName)) {
                            String str = UnifiedTelemetryLogger.TAG;
                            Log.e(str, "Funnel data for funnel " + funnelName + " with type action with no action name found, discarding data.");
                        } else if (funnelData.mInstanceId != null) {
                            unifiedTelemetryService.reportFunnelActionWithId(funnelName, funnelData.mInstanceId.longValue(), funnelData.mActionName, funnelData.mTag, funnelData.mActionData);
                        } else {
                            unifiedTelemetryService.reportFunnelAction(funnelName, funnelData.mActionName, funnelData.mTag, funnelData.mActionData);
                        }
                    } else if (funnelData.mInstanceId != null) {
                        unifiedTelemetryService.startFunnelWithId(funnelName, funnelData.mInstanceId.longValue());
                    } else {
                        unifiedTelemetryService.startFunnel(funnelName);
                    }
                    UnifiedTelemetryLogger.this.mFunnelDataQueue.poll();
                    funnelData = (FunnelData) UnifiedTelemetryLogger.this.mFunnelDataQueue.peek();
                }
            }
        }

        private void startSessions(IUnifiedTelemetryService unifiedTelemetryService) throws RemoteException {
            while (!UnifiedTelemetryLogger.this.mSessionsToStart.isEmpty()) {
                SessionRegistrationData registrationData = (SessionRegistrationData) UnifiedTelemetryLogger.this.mSessionsToStart.poll();
                unifiedTelemetryService.startSession(registrationData.mSessionName, registrationData.mSessionId, registrationData.mUserId);
            }
        }

        private void stopSessions(IUnifiedTelemetryService unifiedTelemetryService) throws RemoteException {
            while (!UnifiedTelemetryLogger.this.mSessionsToStop.isEmpty()) {
                SessionRegistrationData registrationData = (SessionRegistrationData) UnifiedTelemetryLogger.this.mSessionsToStop.poll();
                unifiedTelemetryService.stopSession(registrationData.mSessionName, registrationData.mUserId);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.os.UnifiedTelemetryLogger$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$FunnelData$Type = new int[FunnelData.Type.values().length];

        static {
            try {
                $SwitchMap$com$oculus$os$FunnelData$Type[FunnelData.Type.START.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$os$FunnelData$Type[FunnelData.Type.ACTION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$oculus$os$FunnelData$Type[FunnelData.Type.END.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType = new int[MessageType.values().length];
            try {
                $SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType[MessageType.FETCH_GATEKEEPERS.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType[MessageType.REPORT_LOW_LATENCY_EVENTS.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType[MessageType.REPORT_EVENTS.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType[MessageType.REPORT_LOW_LATENCY_EXPOSURE.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType[MessageType.REPORT_EXPOSURE.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType[MessageType.REGISTER_FUNNEL.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType[MessageType.REPORT_FUNNEL_DATA.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType[MessageType.START_SESSION.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$oculus$os$UnifiedTelemetryLogger$MessageType[MessageType.STOP_SESSION.ordinal()] = 9;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    /* access modifiers changed from: private */
    public static class SessionRegistrationData {
        private final String mSessionId;
        private final String mSessionName;
        private final int mUserId;

        SessionRegistrationData(String sessionName, int userId, String sessionId) {
            this.mSessionName = sessionName;
            this.mUserId = userId;
            this.mSessionId = sessionId;
        }

        SessionRegistrationData(String sessionName, int userId) {
            this(sessionName, userId, null);
        }
    }
}
