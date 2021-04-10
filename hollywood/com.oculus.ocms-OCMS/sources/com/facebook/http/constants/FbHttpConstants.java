package com.facebook.http.constants;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FbHttpConstants {
    public static final String FB_HTTP_ENGINE_HEADER = "X-FB-HTTP-Engine";
    public static final String FB_HTTP_FLOW_STATISTICS = "fb_http_flow_statistics";
    public static final String FB_HTTP_RETRIED_EXCEPTIONS = "fb_http_retried_exceptions";
    public static final String FB_HTTP_SESSION_PERFORMANCE_LOGGER = "fb_http_session_performance_logger";
    public static final String FB_REQUEST_CALL_PATH = "fb_request_call_path";
    public static final String FB_REQUEST_CATEGORY = "fb_request_category";
    public static final String FB_REQUEST_CREATION_TIME = "fb_request_creation_time";
    public static final String FB_REQUEST_HTTP2_WEIGHT = "http2_weight";
    public static final String FB_REQUEST_PRIORITY = "priority";
    public static final String FB_REQUEST_REPLAY_SAFE = "replay_safe";
    public static final String FB_USER_REQUEST_IDENTIFIER = "fb_user_request_identifier";
    public static final String HTTP_STACK_NAME = "HttpClient";
    public static final int NETWORK_STATUS_MONITOR_WINDOW_SECOND = 12;
    public static final String STATE_CANCELLED = "cancelled";
    public static final String STATE_DONE = "done";
    public static final String STATE_ERROR = "error";

    public interface PerfConstants {
        public static final String REQUEST_METHOD = "request_method";
    }
}
