package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.AbstractC0481qo;
import X.AnonymousClass06;
import X.C0219We;
import X.C0477qK;
import X.C0478qP;
import X.Fe;
import X.HY;
import X.Mu;
import X.QC;
import X.Rc;
import X.Rd;
import X.SL;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.oculus.aidl.IUnifiedTelemetryService;
import com.oculus.android.os.internal.UserHandleInternal;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.library.utils.app.ImagesBuilder;
import com.oculus.logging.ExtraKeys;
import com.oculus.logging.OculusLoggingEvent;
import com.oculus.logging.analytics2.EventBuilder;
import com.oculus.logging.analytics2.EventBuilderConfig;
import com.oculus.logging.analytics2.EventFactoryWithAnalytics2Support;
import com.oculus.logging.analytics2.NoOpEvent;
import com.oculus.logging.analytics2.OculusExposureEventAnalytics2Impl;
import com.oculus.logging.funnel.OculusFunnelDefinition;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.unifiedtelemetry.unifiedlogging.EventMonitoring;
import com.oculus.unifiedtelemetry.unifiedlogging.SessionController;
import com.oculus.unifiedtelemetry.unifiedlogging.utils.SessionEventConstants;
import com.oculus.unifiedtelemetry.unifiedlogging.utils.SettingsManager;
import com.oculus.unifiedtelemetry.unifiedlogging.utils.TelemetryStateMonitor;
import com.oculus.util.constants.OculusConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"BadMethodUse-android.content.Context.getSharedPreferences", "SharedPreferencesUse"})
@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_UserMonitor_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_MobileConfigClient_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_utils_TelemetryStateMonitor_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_EventMonitoring_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_utils_SettingsManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_android_os_internal_UserHandleInternal_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_SessionController_ULSEP_BINDING_ID"})
@ApplicationScoped
public class LoggingHandler {
    public static final boolean DEBUG = false;
    public static final String DEFAULT_APP_ID = "0";
    public static final int DEFAULT_EVENT_TAG = -1;
    public static final long DEFAULT_TIME = -1;
    public static final int ENTERPRISE_MANAGED_DEVICE_MODE = 2;
    public static final int MSG_UPDATE_SAMPLED_EVENTS = 1;
    public static final ImmutableSet<String> PACKAGES_TO_ATTRIBUTE_TO_CURRENT_USER = ImmutableSet.A06(2, SYSTEM_UID_PACKAGE, "com.oculus.systemdriver");
    public static final int PACKAGE_MANAGER_GET_PACKAGE_INFO_NO_FLAGS = 0;
    public static final String SYSTEM_APP_ID = "1000";
    public static final String SYSTEM_UID_PACKAGE = "system";
    public static final String TAG = "LoggingHandler";
    public static final long TIME_TO_UPDATE_SAMPLED_EVENTS_MSEC = 14400000;
    public static volatile LoggingHandler _UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_INSTANCE;
    @Nullable
    public static HashMap<String, Double> mSampledEvents;
    public static final Map<String, String> sWhitelistedAppPackageNameToId;
    public QC _UL_mInjectionContext;
    public final IUnifiedTelemetryService.Stub binder = new IUnifiedTelemetryService.Stub() {
        /* class com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler.AnonymousClass2 */

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final void endFunnel(String str) {
            if (!((TelemetryStateMonitor) AbstractC0096Hu.A03(3, ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, LoggingHandler.this._UL_mInjectionContext)).A00()) {
                LoggingHandler loggingHandler = LoggingHandler.this;
                AbstractC0481qo A00 = LoggingHandler.A00(str);
                if (A00 != null) {
                    ((UserMonitor) AbstractC0096Hu.A03(1, 86, loggingHandler._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mFunnelLogger.A1w(A00);
                    LoggingHandler.A02(loggingHandler, str, EventMonitoring.FunnelEventType.END);
                }
            }
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final void endFunnelWithId(String str, long j) {
            if (!((TelemetryStateMonitor) AbstractC0096Hu.A03(3, ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, LoggingHandler.this._UL_mInjectionContext)).A00()) {
                LoggingHandler loggingHandler = LoggingHandler.this;
                AbstractC0481qo A00 = LoggingHandler.A00(str);
                if (A00 != null) {
                    ((UserMonitor) AbstractC0096Hu.A03(1, 86, loggingHandler._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mFunnelLogger.A1x(A00, j);
                    LoggingHandler.A02(loggingHandler, str, EventMonitoring.FunnelEventType.END);
                }
            }
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final void registerFunnel(String str, int i) {
            if (!((TelemetryStateMonitor) AbstractC0096Hu.A03(3, ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, LoggingHandler.this._UL_mInjectionContext)).A00()) {
                OculusFunnelDefinition oculusFunnelDefinition = new OculusFunnelDefinition(str);
                OculusFunnelDefinition.sFunnelDefinitions.put(oculusFunnelDefinition.name, oculusFunnelDefinition);
                oculusFunnelDefinition.mSecondsToEndSinceLastUpdate = i;
                oculusFunnelDefinition.mShouldEndOnUserLeavingTheApp = false;
            }
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final void reportEvents(PersistableBundle persistableBundle, boolean z) {
            try {
                LoggingHandler loggingHandler = LoggingHandler.this;
                if (persistableBundle == null) {
                    Mu.A01(LoggingHandler.TAG, "reportEvents events is null");
                    return;
                }
                for (String str : persistableBundle.keySet()) {
                    loggingHandler.A09(str, persistableBundle.getPersistableBundle(str), z);
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final void reportFunnelAction(String str, String str2, String str3, PersistableBundle persistableBundle) {
            if (!((TelemetryStateMonitor) AbstractC0096Hu.A03(3, ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, LoggingHandler.this._UL_mInjectionContext)).A00()) {
                LoggingHandler loggingHandler = LoggingHandler.this;
                AbstractC0481qo A00 = LoggingHandler.A00(str);
                if (A00 != null) {
                    ((UserMonitor) AbstractC0096Hu.A03(1, 86, loggingHandler._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mFunnelLogger.A1F(A00, str2, str3, LoggingHandler.A01(persistableBundle));
                    LoggingHandler.A02(loggingHandler, str, EventMonitoring.FunnelEventType.ACTION);
                }
            }
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final void reportFunnelActionWithId(String str, long j, String str2, String str3, PersistableBundle persistableBundle) {
            if (!((TelemetryStateMonitor) AbstractC0096Hu.A03(3, ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, LoggingHandler.this._UL_mInjectionContext)).A00()) {
                LoggingHandler loggingHandler = LoggingHandler.this;
                AbstractC0481qo A00 = LoggingHandler.A00(str);
                if (A00 != null) {
                    ((UserMonitor) AbstractC0096Hu.A03(1, 86, loggingHandler._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mFunnelLogger.A1E(A00, j, str2, str3, LoggingHandler.A01(persistableBundle));
                    LoggingHandler.A02(loggingHandler, str, EventMonitoring.FunnelEventType.ACTION);
                }
            }
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final boolean shouldDuplicateLogging(String str) {
            return LoggingHandler.A04(LoggingHandler.this, 36310271995674624L);
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final boolean shouldUseUnifiedTelemetryService(String str) {
            if (((SettingsManager) AbstractC0096Hu.A03(6, 20, LoggingHandler.this._UL_mInjectionContext)).mManagedMode == 2 || LoggingHandler.A04(LoggingHandler.this, 36310271995740161L)) {
                return true;
            }
            return false;
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final void startFunnel(String str) {
            if (!((TelemetryStateMonitor) AbstractC0096Hu.A03(3, ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, LoggingHandler.this._UL_mInjectionContext)).A00()) {
                LoggingHandler loggingHandler = LoggingHandler.this;
                AbstractC0481qo A00 = LoggingHandler.A00(str);
                if (A00 != null) {
                    ((UserMonitor) AbstractC0096Hu.A03(1, 86, loggingHandler._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mFunnelLogger.A5M(A00);
                    LoggingHandler.A02(loggingHandler, str, EventMonitoring.FunnelEventType.START);
                }
            }
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final void startFunnelWithId(String str, long j) {
            if (!((TelemetryStateMonitor) AbstractC0096Hu.A03(3, ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, LoggingHandler.this._UL_mInjectionContext)).A00()) {
                LoggingHandler loggingHandler = LoggingHandler.this;
                AbstractC0481qo A00 = LoggingHandler.A00(str);
                if (A00 != null) {
                    ((UserMonitor) AbstractC0096Hu.A03(1, 86, loggingHandler._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mFunnelLogger.A5N(A00, j);
                    LoggingHandler.A02(loggingHandler, str, EventMonitoring.FunnelEventType.START);
                }
            }
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final void startSession(String str, @Nullable String str2, int i) {
            SessionController sessionController = (SessionController) AbstractC0096Hu.A03(9, 14, LoggingHandler.this._UL_mInjectionContext);
            SessionController.A02(sessionController, "startSession()");
            String A00 = SessionController.A00(sessionController, str);
            UserHandle of = UserHandle.of(i);
            long serialNumberForUser = ((UserManager) AbstractC0096Hu.A03(2, 124, sessionController._UL_mInjectionContext)).getSerialNumberForUser(of);
            if (serialNumberForUser == -1) {
                Mu.A06(SessionController.TAG, "While attempting to start a new session, unable to get a valid Serial Number for userHandle: (%s)", of);
            } else if (str2 == null) {
                Mu.A01(SessionController.TAG, "Client supplied null sessionId");
            } else {
                SessionController.UserSessionStore userSessionStore = new SessionController.UserSessionStore();
                synchronized (sessionController.mSessionCache) {
                    SessionController.UserSessionStore putIfAbsent = sessionController.mSessionCache.putIfAbsent(Long.valueOf(serialNumberForUser), userSessionStore);
                    if (putIfAbsent != null) {
                        userSessionStore = putIfAbsent;
                    }
                    userSessionStore.mSessionMap.put(A00, str2);
                    SessionController.A01(sessionController);
                }
                Event A1h = ((EventManager) AbstractC0096Hu.A03(1, 106, sessionController._UL_mInjectionContext)).A1h("oculus_ut_session_event");
                A1h.A18(SessionEventConstants.KEY_EVENT_TYPE, SessionEventConstants.START_SESSION);
                A1h.A18("session_id", str2);
                A1h.A18(SessionEventConstants.KEY_SESSION_NAME, A00);
                A1h.A3Q();
            }
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final void stopSession(String str, int i) {
            SessionController sessionController = (SessionController) AbstractC0096Hu.A03(9, 14, LoggingHandler.this._UL_mInjectionContext);
            SessionController.A02(sessionController, "stopSession()");
            String A00 = SessionController.A00(sessionController, str);
            UserHandle of = UserHandle.of(i);
            if (UserHandleInternal.A01().equals(of)) {
                synchronized (sessionController.mSessionCache) {
                    boolean z = false;
                    for (Map.Entry<Long, SessionController.UserSessionStore> entry : sessionController.mSessionCache.entrySet()) {
                        ConcurrentHashMap<String, String> concurrentHashMap = entry.getValue().mSessionMap;
                        String remove = concurrentHashMap.remove(A00);
                        if (remove != null) {
                            Event A1h = ((EventManager) AbstractC0096Hu.A03(1, 106, sessionController._UL_mInjectionContext)).A1h("oculus_ut_session_event");
                            A1h.A18(SessionEventConstants.KEY_EVENT_TYPE, SessionEventConstants.STOP_SESSION);
                            A1h.A18(SessionEventConstants.KEY_SESSION_NAME, A00);
                            A1h.A18("session_id", remove);
                            A1h.A3Q();
                            if (concurrentHashMap.size() <= 0) {
                                sessionController.mSessionCache.remove(entry.getKey());
                            }
                            z = true;
                        }
                    }
                    if (z) {
                        SessionController.A01(sessionController);
                    } else {
                        Mu.A06(SessionController.TAG, "Did not find a session with sessionName: (%s). No sessions were unregistered.", A00);
                    }
                }
                return;
            }
            long serialNumberForUser = ((UserManager) AbstractC0096Hu.A03(2, 124, sessionController._UL_mInjectionContext)).getSerialNumberForUser(of);
            if (serialNumberForUser == -1) {
                Mu.A06(SessionController.TAG, "While attempting to stop a session, unable to get a valid Serial Number for userHandle: (%s)", of);
                return;
            }
            synchronized (sessionController.mSessionCache) {
                ConcurrentHashMap<Long, SessionController.UserSessionStore> concurrentHashMap2 = sessionController.mSessionCache;
                Long valueOf = Long.valueOf(serialNumberForUser);
                SessionController.UserSessionStore userSessionStore = concurrentHashMap2.get(valueOf);
                if (userSessionStore != null) {
                    ConcurrentHashMap<String, String> concurrentHashMap3 = userSessionStore.mSessionMap;
                    String remove2 = concurrentHashMap3.remove(A00);
                    if (remove2 != null) {
                        if (concurrentHashMap3.size() <= 0) {
                            sessionController.mSessionCache.remove(valueOf);
                        }
                        SessionController.A01(sessionController);
                        Event A1h2 = ((EventManager) AbstractC0096Hu.A03(1, 106, sessionController._UL_mInjectionContext)).A1h("oculus_ut_session_event");
                        A1h2.A18(SessionEventConstants.KEY_EVENT_TYPE, SessionEventConstants.STOP_SESSION);
                        A1h2.A18(SessionEventConstants.KEY_SESSION_NAME, A00);
                        A1h2.A18("session_id", remove2);
                        A1h2.A3Q();
                    } else {
                        Mu.A06(SessionController.TAG, "Failed to stop session (%s) because the sessionName does not exist.", A00);
                    }
                }
            }
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public final void logExposure(String str, String str2, PersistableBundle persistableBundle, PersistableBundle persistableBundle2, boolean z) {
            OculusLoggingEvent oculusExposureEventAnalytics2Impl;
            if (!((TelemetryStateMonitor) AbstractC0096Hu.A03(3, ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, LoggingHandler.this._UL_mInjectionContext)).A01(str2, null)) {
                EventBuilder eventBuilder = ((UserMonitor) AbstractC0096Hu.A03(1, 86, LoggingHandler.this._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mEventFactory.mEventBuilder;
                SharedPreferences A00 = EventBuilderConfig.A00((EventBuilderConfig) AbstractC0096Hu.A03(5, 37, eventBuilder._UL_mInjectionContext));
                if (A00 == null || A00.getBoolean(EventBuilderConfig.KEY_A2_ENABLED, true)) {
                    QC qc = eventBuilder._UL_mInjectionContext;
                    oculusExposureEventAnalytics2Impl = new OculusExposureEventAnalytics2Impl((Fe) AbstractC0096Hu.A03(0, 101, qc), (IErrorReporter) AbstractC0096Hu.A03(3, 135, qc), (C0219We) AbstractC0096Hu.A03(4, 73, qc), str, str2, z);
                } else {
                    oculusExposureEventAnalytics2Impl = new NoOpEvent();
                }
                if (oculusExposureEventAnalytics2Impl.A3I()) {
                    JSONObject jSONObject = new JSONObject();
                    for (String str3 : persistableBundle2.keySet()) {
                        LoggingHandler.A05(LoggingHandler.this, str2, str3, persistableBundle2, jSONObject);
                    }
                    oculusExposureEventAnalytics2Impl.A1A(jSONObject.toString());
                    for (String str4 : persistableBundle.keySet()) {
                        String string = persistableBundle.getString(str4, "");
                        if (!string.isEmpty()) {
                            oculusExposureEventAnalytics2Impl.A12(str4, string);
                        }
                    }
                    oculusExposureEventAnalytics2Impl.A3Q();
                }
            }
        }
    };
    public final EventRateHandler mEventRateHandler;
    public final HashMap<String, String> mPackageNameToAppIdMap;
    public final ReentrantReadWriteLock mPackageNameToAppIdMapLock;
    public final Random mRandom;

    public static final class EventRateHandler extends Handler {
        public final MobileConfigClient mMobileConfig;

        public final void handleMessage(Message message) {
            String A01;
            int i = message.what;
            if (i == 1) {
                MobileConfigClient mobileConfigClient = this.mMobileConfig;
                Rd A00 = Rd.A00(new Rd());
                A00.A00 = true;
                Rd A002 = Rd.A00(A00);
                A002.A02 = true;
                if (mobileConfigClient.mIsInitialized) {
                    A01 = ((Rc) AbstractC0096Hu.A03(0, 115, mobileConfigClient._UL_mInjectionContext)).A2u(36873221949423616L, A002);
                } else {
                    A01 = C0478qP.A01(36873221949423616L);
                }
                LoggingHandler.mSampledEvents = (HashMap) new HY().A06(A01, HashMap.class);
            } else {
                Mu.A06(LoggingHandler.TAG, "Unexpected message received in EventRateHandler. Msg: %d", Integer.valueOf(i));
            }
            sendEmptyMessageDelayed(1, LoggingHandler.TIME_TO_UPDATE_SAMPLED_EVENTS_MSEC);
        }

        public EventRateHandler(MobileConfigClient mobileConfigClient) {
            super(Looper.getMainLooper());
            this.mMobileConfig = mobileConfigClient;
        }
    }

    public static void A02(LoggingHandler loggingHandler, String str, EventMonitoring.FunnelEventType funnelEventType) {
        loggingHandler.A03(true, null, null, false, str, funnelEventType);
    }

    public static boolean A06(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
            return true;
        } catch (JSONException e) {
            Mu.A0B(TAG, e, "Failed to add %1$s", str);
            return false;
        }
    }

    public final void A07(String str, @Nullable PersistableBundle persistableBundle) {
        A08(str, persistableBundle, false);
    }

    static {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put("com.oculus.horizon", "826037204154824");
        builder.put(OculusConstants.PACKAGE_NAME_SYSTEM_UTILITIES, "939044786184977");
        builder.put("com.oculus.home", "1102518876432898");
        builder.put("com.oculus.updater", "1556285957737177");
        builder.put("com.oculus.vrshell", "1031607236937163");
        builder.put("com.oculus.ocms", OculusConstants.OCMS_OCULUS_APP_ID);
        builder.put("com.oculus.unifiedtelemetry", OculusConstants.UNIFIED_TELEMETRY_FB_APP_ID);
        builder.put("com.oculus.companion.server", SYSTEM_APP_ID);
        builder.put(SYSTEM_UID_PACKAGE, SYSTEM_APP_ID);
        sWhitelistedAppPackageNameToId = builder.build();
    }

    @Nullable
    public static AbstractC0481qo A00(String str) {
        AbstractC0481qo qoVar = OculusFunnelDefinition.sFunnelDefinitions.get(str);
        if (qoVar == null) {
            Mu.A05(TAG, "FunnelDefinition for funnel name %s not found. Did you register?", str);
        }
        return qoVar;
    }

    public static C0477qK A01(@Nullable PersistableBundle persistableBundle) {
        C0477qK qKVar = new C0477qK();
        if (persistableBundle != null) {
            for (String str : persistableBundle.keySet()) {
                Object obj = persistableBundle.get(str);
                if (obj instanceof String) {
                    synchronized (qKVar) {
                        qKVar.A00.put(str, obj);
                    }
                } else if (obj instanceof Integer) {
                    int intValue = ((Number) obj).intValue();
                    synchronized (qKVar) {
                        qKVar.A00.put(str, Integer.valueOf(intValue));
                    }
                } else if (obj instanceof Long) {
                    long longValue = ((Number) obj).longValue();
                    synchronized (qKVar) {
                        qKVar.A00.put(str, Long.valueOf(longValue));
                    }
                } else if (obj instanceof Double) {
                    double doubleValue = ((Number) obj).doubleValue();
                    synchronized (qKVar) {
                        qKVar.A00.put(str, Double.valueOf(doubleValue));
                    }
                } else if (obj instanceof Boolean) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    synchronized (qKVar) {
                        qKVar.A00.put(str, Boolean.valueOf(booleanValue));
                    }
                } else {
                    Mu.A00(TAG, "PersistableBundle value instance not recognized. Should never happen.");
                }
            }
        }
        return qKVar;
    }

    public static boolean A04(LoggingHandler loggingHandler, long j) {
        MobileConfigClient mobileConfigClient = (MobileConfigClient) AbstractC0096Hu.A03(2, 134, loggingHandler._UL_mInjectionContext);
        Rd A00 = Rd.A00(new Rd());
        A00.A00 = true;
        Rd A002 = Rd.A00(A00);
        A002.A02 = true;
        if (mobileConfigClient.mIsInitialized) {
            return ((Rc) AbstractC0096Hu.A03(0, 115, mobileConfigClient._UL_mInjectionContext)).A2M(j, A002);
        }
        return SL.A01(j);
    }

    public final void A08(String str, @Nullable PersistableBundle persistableBundle, boolean z) {
        if (!((TelemetryStateMonitor) AbstractC0096Hu.A03(3, ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, this._UL_mInjectionContext)).A01(str, "com.oculus.unifiedtelemetry") && persistableBundle != null) {
            persistableBundle.putString(ExtraKeys.UTL_LOGGING_PACKAGE, ((Context) AbstractC0096Hu.A03(0, 3, this._UL_mInjectionContext)).getPackageName());
        }
        A09(str, persistableBundle, z);
    }

    public final void A0A(String str, OculusLoggingEvent oculusLoggingEvent) {
        if (!((TelemetryStateMonitor) AbstractC0096Hu.A03(3, ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, this._UL_mInjectionContext)).A01(str, "com.oculus.unifiedtelemetry")) {
            oculusLoggingEvent.A3Q();
            A03(false, str, null, false, null, null);
        }
    }

    @Inject
    public LoggingHandler(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(10, xu);
        this.mPackageNameToAppIdMap = new HashMap<>();
        this.mPackageNameToAppIdMapLock = new ReentrantReadWriteLock();
        this.mRandom = new Random();
        mSampledEvents = new HashMap<>();
        EventRateHandler eventRateHandler = new EventRateHandler((MobileConfigClient) AbstractC0096Hu.A03(2, 134, this._UL_mInjectionContext));
        this.mEventRateHandler = eventRateHandler;
        eventRateHandler.sendEmptyMessage(1);
    }

    public static boolean A05(LoggingHandler loggingHandler, String str, String str2, PersistableBundle persistableBundle, Object obj) {
        JSONObject jSONObject;
        Object obj2;
        try {
            Object obj3 = persistableBundle.get(str2);
            boolean z = true;
            if (obj3 instanceof PersistableBundle) {
                PersistableBundle persistableBundle2 = (PersistableBundle) obj3;
                if (persistableBundle2.containsKey("utl_array_of_bundles")) {
                    persistableBundle2.remove("utl_array_of_bundles");
                    obj2 = new JSONArray();
                } else {
                    obj2 = new JSONObject();
                }
                for (String str3 : persistableBundle2.keySet()) {
                    z = A05(loggingHandler, str, str3, persistableBundle2, obj2);
                }
                if (obj instanceof JSONObject) {
                    jSONObject = (JSONObject) obj;
                } else if (!(obj instanceof JSONArray)) {
                    return z;
                } else {
                    ((JSONArray) obj).put(obj2);
                    return z;
                }
            } else if (obj instanceof JSONObject) {
                jSONObject = (JSONObject) obj;
                obj2 = persistableBundle.get(str2);
            } else if (!(obj instanceof JSONArray)) {
                return true;
            } else {
                ((JSONArray) obj).put(persistableBundle.get(str2));
                return true;
            }
            jSONObject.put(str2, obj2);
            return z;
        } catch (JSONException e) {
            Mu.A02(TAG, "JSONException", e);
            ((IErrorReporter) AbstractC0096Hu.A03(4, 135, loggingHandler._UL_mInjectionContext)).A5H("failed_to_parse_telemetry_data", AnonymousClass06.A05(str, "|", e.getMessage()));
            return false;
        }
    }

    private void A03(boolean z, @Nullable final String str, @Nullable ArrayList<String> arrayList, boolean z2, @Nullable String str2, @Nullable EventMonitoring.FunnelEventType funnelEventType) {
        EventMonitoring eventMonitoring = (EventMonitoring) AbstractC0096Hu.A03(5, 16, this._UL_mInjectionContext);
        String str3 = str;
        boolean z3 = false;
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            eventMonitoring.mReadWriteLock.readLock().lock();
            if (arrayList != null) {
                try {
                    if (!arrayList.isEmpty()) {
                        eventMonitoring.mIncompleteEventCount.incrementAndGet();
                    }
                } catch (Throwable th) {
                    eventMonitoring.mReadWriteLock.readLock().unlock();
                    throw th;
                }
            }
            if (z2) {
                eventMonitoring.mCorruptedEventCount.incrementAndGet();
            }
            if (funnelEventType == null) {
                funnelEventType = EventMonitoring.FunnelEventType.UNKNOWN;
            }
            if (z) {
                str3 = AnonymousClass06.A05(str2, "_", funnelEventType.name());
            }
            EventMonitoring.MonitoringData monitoringData = (EventMonitoring.MonitoringData) eventMonitoring.mEventMonitoringMap.get(str3);
            if (monitoringData == null) {
                monitoringData = new EventMonitoring.MonitoringData();
                EventMonitoring.MonitoringData monitoringData2 = (EventMonitoring.MonitoringData) eventMonitoring.mEventMonitoringMap.putIfAbsent(str3, monitoringData);
                if (monitoringData2 != null) {
                    monitoringData = monitoringData2;
                }
            }
            monitoringData.mCount.incrementAndGet();
            if (arrayList != null) {
                monitoringData.mUsingRestrictedKeys.addAll(arrayList);
            }
            if (eventMonitoring.mTotalEventCount.incrementAndGet() == eventMonitoring.mUploadThreshold) {
                z3 = true;
            }
            eventMonitoring.mReadWriteLock.readLock().unlock();
            if (z3) {
                final EventFactoryWithAnalytics2Support eventFactoryWithAnalytics2Support = ((UserMonitor) AbstractC0096Hu.A03(1, 86, this._UL_mInjectionContext)).A00(Binder.getCallingUserHandle()).mEventFactory;
                OculusThreadExecutor.A00().execute(new Runnable() {
                    /* class com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler.AnonymousClass1 */

                    public final void run() {
                        String A00 = EventMonitoring.A00((EventMonitoring) AbstractC0096Hu.A03(5, 16, LoggingHandler.this._UL_mInjectionContext), false);
                        OculusLoggingEvent A3T = eventFactoryWithAnalytics2Support.A3T(null, EventMonitoring.UT_TELEMETRY_MONITORING, false);
                        if (A3T.A3I()) {
                            if (A00 != null) {
                                A3T.A1A(A00);
                            }
                            LoggingHandler.this.A0A(str, A3T);
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x07b6, code lost:
        if (r2.getInt(r7) == 1) goto L_0x07bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x08e6, code lost:
        if (r1 != null) goto L_0x011f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A09(java.lang.String r103, @javax.annotation.Nullable android.os.PersistableBundle r104, boolean r105) {
        /*
        // Method dump skipped, instructions count: 2307
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler.A09(java.lang.String, android.os.PersistableBundle, boolean):void");
    }
}
