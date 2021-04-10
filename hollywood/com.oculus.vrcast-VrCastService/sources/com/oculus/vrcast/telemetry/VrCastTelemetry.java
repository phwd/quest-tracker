package com.oculus.vrcast.telemetry;

import android.content.Context;
import com.google.common.collect.ImmutableList;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.vrcast.CastDevice;
import com.oculus.vrcast.DiscoverProtocol;
import java.util.List;
import java.util.UUID;
import oculus.internal.Gatekeeper;

public class VrCastTelemetry {
    private static final String EVENT_CHROMECAST_START_DISCOVERY = "oculus_mobile_vrcast_chromecast_start_discovery";
    private static final String EVENT_CHROMECAST_STOP_DISCOVERY = "oculus_mobile_vrcast_chromecast_stop_discovery";
    private static final String EVENT_ERROR = "oculus_mobile_vrcast_error";
    private static final String EVENT_PREFIX = "oculus_mobile_vrcast_";
    private static final String EVENT_STARTED_CASTING = "oculus_mobile_vrcast_started_casting";
    private static final String EVENT_START_CASTING = "oculus_mobile_vrcast_start_casting";
    private static final String EVENT_START_DISCOVERY = "oculus_mobile_vrcast_start_discovery";
    private static final String EVENT_STOP_CASTING = "oculus_mobile_vrcast_stop_casting";
    private static final String EVENT_STOP_DISCOVERY = "oculus_mobile_vrcast_stop_discovery";
    private static final String KEY_101_KEEP_CONNECTED = "casting_101_keep_connected";
    private static final String KEY_CALLER_CONTEXT = "device_name";
    private static final String KEY_CASTING_SESSION_ID = "casting_session_id";
    private static final String KEY_DEVICE_MODEL = "casting_device_model_name";
    private static final String KEY_DEVICE_TYPE = "type";
    private static final String KEY_DISCOVERY_DURATION_MS = "discovery_ms";
    private static final String KEY_ERROR = "error";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_INCREASE_PING_FAILURE_TOLERANCE = "increase_ping_failure_tolerance";
    private static final String KEY_LONGER_PING_INTERVAL = "longer_ping_interval";
    private static final String KEY_PEERS_LIST_SIZE = "peers_size";
    private static final String KEY_REMOVE_PING = "remove_ping";
    private static final String KEY_RETRY_COUNT = "retry_count";
    private static final String KEY_SESSION_DURATION_MS = "session_ms";
    private static final String KEY_WHAT = "what";
    private static final String KEY_WIDTH = "width";
    private static final List<Integer> NON_BLOCKING_ERROR_CODES = ImmutableList.of(101);
    private static final String NON_BLOCKING_EVENT_ERROR = "oculus_mobile_vrcast_non_blocking_error";
    private String mCallerContext;
    private String mCastingSessionId = "";
    private long mCastingStartedTimestamp = -1;
    private final Gatekeeper mEnable101KeepConnected = new Gatekeeper(30);
    private final Gatekeeper mEnableLongerPingIntervalGk = new Gatekeeper(19);
    private final Gatekeeper mEnableMorePingFailuresGk = new Gatekeeper(28);
    private final Gatekeeper mEnableRemoveChromecastPing = new Gatekeeper(29);
    private long mGoogleCastDiscoveryStartedTimestamp = -1;
    private final UnifiedTelemetryLogger mLogger;
    private long mWfdDiscoveryStartedTimestamp = -1;

    private boolean isStarted(long j) {
        return j > 0;
    }

    public VrCastTelemetry(Context context) {
        this.mLogger = UnifiedTelemetryLogger.getInstance(context);
    }

    public synchronized void onStartDiscovery(DiscoverProtocol discoverProtocol) {
        if (getDiscoveryStartedTimestamp(discoverProtocol) < 0) {
            resetDiscoveryStartedTimestamp(discoverProtocol, System.currentTimeMillis());
            AnalyticsEvent analyticsEvent = new AnalyticsEvent(EVENT_START_DISCOVERY);
            analyticsEvent.setExtra(KEY_CALLER_CONTEXT, this.mCallerContext);
            analyticsEvent.setExtra(KEY_DEVICE_TYPE, discoverProtocol.toString());
            logEvent(analyticsEvent);
        }
    }

    public synchronized void onStartChromecastDiscovery(String str, int i) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(EVENT_CHROMECAST_START_DISCOVERY);
        analyticsEvent.setExtra(KEY_CALLER_CONTEXT, this.mCallerContext);
        analyticsEvent.setExtra(KEY_DEVICE_TYPE, DiscoverProtocol.GOOGLE_CAST.toString());
        analyticsEvent.setExtra(KEY_CASTING_SESSION_ID, str);
        analyticsEvent.setExtra(KEY_RETRY_COUNT, Integer.valueOf(i));
        logEvent(analyticsEvent);
    }

    public synchronized void onStopDiscovery(DiscoverProtocol discoverProtocol) {
        long discoveryStartedTimestamp = getDiscoveryStartedTimestamp(discoverProtocol);
        if (isStarted(discoveryStartedTimestamp)) {
            long durationMs = getDurationMs(discoveryStartedTimestamp);
            resetDiscoveryStartedTimestamp(discoverProtocol, -1);
            AnalyticsEvent analyticsEvent = new AnalyticsEvent(EVENT_STOP_DISCOVERY);
            analyticsEvent.setExtra(KEY_CALLER_CONTEXT, this.mCallerContext);
            analyticsEvent.setExtra(KEY_DEVICE_TYPE, discoverProtocol.toString());
            analyticsEvent.setExtra(KEY_DISCOVERY_DURATION_MS, Long.valueOf(durationMs));
            logEvent(analyticsEvent);
        }
    }

    public synchronized void onStopChromecastDiscovery(String str, int i) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(EVENT_CHROMECAST_STOP_DISCOVERY);
        analyticsEvent.setExtra(KEY_CALLER_CONTEXT, this.mCallerContext);
        analyticsEvent.setExtra(KEY_DEVICE_TYPE, DiscoverProtocol.GOOGLE_CAST.toString());
        analyticsEvent.setExtra(KEY_CASTING_SESSION_ID, str);
        analyticsEvent.setExtra(KEY_RETRY_COUNT, Integer.valueOf(i));
        logEvent(analyticsEvent);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.vrcast.telemetry.VrCastTelemetry$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrcast$DiscoverProtocol = new int[DiscoverProtocol.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.vrcast.DiscoverProtocol[] r0 = com.oculus.vrcast.DiscoverProtocol.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.vrcast.telemetry.VrCastTelemetry.AnonymousClass1.$SwitchMap$com$oculus$vrcast$DiscoverProtocol = r0
                int[] r0 = com.oculus.vrcast.telemetry.VrCastTelemetry.AnonymousClass1.$SwitchMap$com$oculus$vrcast$DiscoverProtocol     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.vrcast.DiscoverProtocol r1 = com.oculus.vrcast.DiscoverProtocol.WIFI_P2P     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.vrcast.telemetry.VrCastTelemetry.AnonymousClass1.$SwitchMap$com$oculus$vrcast$DiscoverProtocol     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.vrcast.DiscoverProtocol r1 = com.oculus.vrcast.DiscoverProtocol.GOOGLE_CAST     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrcast.telemetry.VrCastTelemetry.AnonymousClass1.<clinit>():void");
        }
    }

    private synchronized long getDiscoveryStartedTimestamp(DiscoverProtocol discoverProtocol) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$vrcast$DiscoverProtocol[discoverProtocol.ordinal()];
        if (i == 1) {
            return this.mWfdDiscoveryStartedTimestamp;
        } else if (i != 2) {
            return -1;
        } else {
            return this.mGoogleCastDiscoveryStartedTimestamp;
        }
    }

    private synchronized void resetDiscoveryStartedTimestamp(DiscoverProtocol discoverProtocol, long j) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$vrcast$DiscoverProtocol[discoverProtocol.ordinal()];
        if (i == 1) {
            this.mWfdDiscoveryStartedTimestamp = j;
        } else if (i == 2) {
            this.mGoogleCastDiscoveryStartedTimestamp = j;
        }
    }

    public synchronized void onCastingRequested(CastDevice castDevice, int i) {
        this.mCastingStartedTimestamp = System.currentTimeMillis();
        this.mCastingSessionId = UUID.randomUUID().toString();
        DiscoverProtocol discoverProtocol = castDevice.mDiscoverProtocol;
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(EVENT_START_CASTING);
        addCastingSessionBaseEventData(analyticsEvent, castDevice);
        analyticsEvent.setExtra(KEY_DEVICE_TYPE, discoverProtocol.toString());
        analyticsEvent.setExtra(KEY_PEERS_LIST_SIZE, Integer.valueOf(i));
        logEvent(analyticsEvent);
    }

    public synchronized void onStopRequested(CastDevice castDevice) {
        long durationMs = getDurationMs(this.mCastingStartedTimestamp);
        this.mCastingStartedTimestamp = -1;
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(EVENT_STOP_CASTING);
        addCastingSessionBaseEventData(analyticsEvent, castDevice);
        analyticsEvent.setExtra(KEY_SESSION_DURATION_MS, Long.valueOf(durationMs));
        logEvent(analyticsEvent);
    }

    private long getDurationMs(long j) {
        if (!isStarted(j)) {
            return 0;
        }
        return Long.max(0, System.currentTimeMillis() - j);
    }

    public void onStopCastingFailed() {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(EVENT_ERROR);
        addCastingSessionBaseEventData(analyticsEvent, null);
        analyticsEvent.setExtra(KEY_WHAT, "stop_casting");
        logEvent(analyticsEvent);
    }

    public void onSurfaceReceived(CastDevice castDevice, int i, int i2) {
        this.mCastingStartedTimestamp = System.currentTimeMillis();
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(EVENT_STARTED_CASTING);
        addCastingSessionBaseEventData(analyticsEvent, castDevice);
        analyticsEvent.setExtra(KEY_WIDTH, Integer.valueOf(i));
        analyticsEvent.setExtra(KEY_HEIGHT, Integer.valueOf(i2));
        logEvent(analyticsEvent);
    }

    public synchronized void onConnectionError(CastDevice castDevice, DiscoverProtocol discoverProtocol, int i, String str) {
        long durationMs = getDurationMs(this.mCastingStartedTimestamp);
        this.mCastingStartedTimestamp = -1;
        String str2 = EVENT_ERROR;
        if (NON_BLOCKING_ERROR_CODES.contains(Integer.valueOf(i))) {
            str2 = NON_BLOCKING_EVENT_ERROR;
        }
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(str2);
        addCastingSessionBaseEventData(analyticsEvent, castDevice);
        analyticsEvent.setExtra(KEY_DEVICE_TYPE, discoverProtocol.toString());
        if (str == null) {
            str = "connection_error";
        }
        analyticsEvent.setExtra(KEY_WHAT, str);
        analyticsEvent.setExtra(KEY_ERROR, Integer.valueOf(i));
        analyticsEvent.setExtra(KEY_SESSION_DURATION_MS, Long.valueOf(durationMs));
        logEvent(analyticsEvent);
    }

    public synchronized void onWfdConnectionError(int i, String str) {
        DiscoverProtocol discoverProtocol = DiscoverProtocol.WIFI_P2P;
        if (str == null) {
            str = "wfd_error";
        }
        onConnectionError(null, discoverProtocol, i, str);
    }

    public synchronized void onGoogleCastConnectionError(CastDevice castDevice, int i, String str) {
        DiscoverProtocol discoverProtocol = DiscoverProtocol.GOOGLE_CAST;
        if (str == null) {
            str = "gcast_error";
        }
        onConnectionError(castDevice, discoverProtocol, i, str);
    }

    public synchronized void onGoogleCastDNSSDError(String str, String str2, int i) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(EVENT_ERROR);
        analyticsEvent.setExtra(KEY_CALLER_CONTEXT, this.mCallerContext);
        analyticsEvent.setExtra(KEY_WHAT, "gcast_dnssd_error");
        analyticsEvent.setExtra(KEY_CASTING_SESSION_ID, str2);
        analyticsEvent.setExtra(KEY_RETRY_COUNT, Integer.valueOf(i));
        analyticsEvent.setExtra(KEY_DEVICE_TYPE, DiscoverProtocol.GOOGLE_CAST.toString());
        analyticsEvent.setExtra(KEY_ERROR, str);
        analyticsEvent.setExtra(KEY_CASTING_SESSION_ID, this.mCastingSessionId);
        logEvent(analyticsEvent);
    }

    public synchronized void setCallerContext(String str) {
        this.mCallerContext = str;
    }

    private void logEvent(AnalyticsEvent analyticsEvent) {
        this.mLogger.reportEvent(analyticsEvent, true);
    }

    private void addCastingSessionBaseEventData(AnalyticsEvent analyticsEvent, CastDevice castDevice) {
        analyticsEvent.setExtra(KEY_CASTING_SESSION_ID, this.mCastingSessionId);
        analyticsEvent.setExtra(KEY_CALLER_CONTEXT, this.mCallerContext);
        if (castDevice != null) {
            analyticsEvent.setExtra(KEY_DEVICE_MODEL, castDevice.getModelName());
            analyticsEvent.setExtra(KEY_DEVICE_TYPE, castDevice.mDiscoverProtocol.toString());
        }
        analyticsEvent.setExtra(KEY_LONGER_PING_INTERVAL, Boolean.valueOf(this.mEnableLongerPingIntervalGk.isEnabled()));
        analyticsEvent.setExtra(KEY_INCREASE_PING_FAILURE_TOLERANCE, Boolean.valueOf(this.mEnableMorePingFailuresGk.isEnabled()));
        analyticsEvent.setExtra(KEY_REMOVE_PING, Boolean.valueOf(this.mEnableRemoveChromecastPing.isEnabled()));
        analyticsEvent.setExtra(KEY_101_KEEP_CONNECTED, Boolean.valueOf(this.mEnable101KeepConnected.isEnabled()));
    }
}
