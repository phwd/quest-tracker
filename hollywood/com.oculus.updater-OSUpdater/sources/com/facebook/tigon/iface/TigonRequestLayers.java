package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonRequestLayers {
    static final LayerInfo<?>[] ALL_LAYERS = {FACEBOOK_LOGGING, LIGER, REDIRECT, SAMPLING_CONFIG, XPROCESS_TRAFFIC_SHAPING, BACKUP_HOST_SERVICE_REQUEST_INFO, TRANSIENT_ANALYZER_TRACING_INFO, TRIGGERED_LOGGING_INFO, APP_NET_SESSION_ID, RESTRICTIVE_LOGGING_STATUS};
    public static final LayerInfo<Object> APP_NET_SESSION_ID = new LayerInfo<>();
    public static final LayerInfo<Object> BACKUP_HOST_SERVICE_REQUEST_INFO = new LayerInfo<>();
    public static final LayerInfo<FacebookLoggingRequestInfo> FACEBOOK_LOGGING = new LayerInfo<>();
    public static final LayerInfo<Object> LIGER = new LayerInfo<>();
    public static final LayerInfo<RedirectRequestInfo> REDIRECT = new LayerInfo<>();
    public static final LayerInfo<Object> RESTRICTIVE_LOGGING_STATUS = new LayerInfo<>();
    public static final LayerInfo<Object> SAMPLING_CONFIG = new LayerInfo<>();
    public static final LayerInfo<Object> TRANSIENT_ANALYZER_TRACING_INFO = new LayerInfo<>();
    public static final LayerInfo<Object> TRIGGERED_LOGGING_INFO = new LayerInfo<>();
    public static final LayerInfo<Object> XPROCESS_TRAFFIC_SHAPING = new LayerInfo<>();

    public static class LayerInfo<T> {
    }
}
