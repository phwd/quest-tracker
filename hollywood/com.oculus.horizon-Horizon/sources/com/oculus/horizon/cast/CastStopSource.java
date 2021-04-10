package com.oculus.horizon.cast;

public enum CastStopSource {
    TWILIGHT("twilight"),
    HMD("hmd"),
    BROADCAST("broadcast"),
    STANDBY("standby"),
    NEW_START("new_start"),
    CLIENT_ERROR("client_error"),
    HMD_ERROR("hmd_error"),
    OTHER_CAPTURE_STARTING("other_capture_starting"),
    WEBSOCKET_EXCEPTION(CastAnalytics.ACTION_WEBSOCKET_EXCEPTION),
    UNKNOWN("unknown");
    
    public String mName;

    /* access modifiers changed from: public */
    CastStopSource(String str) {
        this.mName = str;
    }

    public static CastStopSource getStopSource(String str) {
        CastStopSource[] values = values();
        for (CastStopSource castStopSource : values) {
            if (castStopSource.getName().equals(str)) {
                return castStopSource;
            }
        }
        return UNKNOWN;
    }

    public String getName() {
        return this.mName;
    }
}
