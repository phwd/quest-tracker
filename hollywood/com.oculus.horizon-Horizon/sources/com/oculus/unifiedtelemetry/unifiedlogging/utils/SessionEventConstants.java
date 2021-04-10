package com.oculus.unifiedtelemetry.unifiedlogging.utils;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SessionEventConstants {
    public static final String KEY_EVENT_TYPE = "event_type";
    public static final String KEY_SESSION_ID = "session_id";
    public static final String KEY_SESSION_NAME = "session_name";
    public static final String OCULUS_UT_SESSION_EVENT = "oculus_ut_session_event";
    public static final String START_SESSION = "START_SESSION";
    public static final String STOP_SESSION = "STOP_SESSION";
}
