package com.facebook.http.constants;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class HttpHeaders {
    public static final String ACCEPT = "Accept";
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_PREFIX = "OAuth ";
    public static final String CONNECTION_BANDWIDTH = "X-FB-Connection-Bandwidth";
    public static final String CONNECTION_QUALITY = "X-FB-Connection-Quality";
    public static final String CONNECTION_TYPE = "X-FB-Connection-Type";
    public static final String COOKIE = "Cookie";
    public static final String DOMAIN = "X-Domain";
    public static final String FBTRACE_HTTP_HEADER = "X-FBTrace-Meta";
    public static final String FBTRACE_HTTP_SAMPLED_HEADER = "X-FBTrace-Sampled";
    public static final String[] FORWARDABLE_HEADERS = {"Accept", "Accept-Encoding", com.google.common.net.HttpHeaders.ACCEPT_LANGUAGE, com.google.common.net.HttpHeaders.CACHE_CONTROL, com.google.common.net.HttpHeaders.IF_MODIFIED_SINCE, com.google.common.net.HttpHeaders.IF_NONE_MATCH, "Range", "Referer", "User-Agent", "X-Purpose", TINCAN_ATTACHMENT_DOWNLOAD_HEADER};
    public static final String FRIENDLY_NAME = "X-FB-Friendly-Name";
    public static final String INTEGRITY_REQUIRED = "X-FB-Integrity-Required";
    public static final String INTEGRITY_SESSION_ID = "X-FB-Integrity-Session-ID";
    public static final String INTEGRITY_TOKEN = "X-FB-Integrity-Enrollment";
    public static final String IS_NATIVE_PLATFORM_LOGIN = "X-FB-Is-Native-Platform-Login";
    public static final String RANGE = "Range";
    public static final String REFERER = "Referer";
    public static final String SSO_REAUTH_REQUIRED_HEADER = "X-FB-SSO-Reauth";
    public static final String TINCAN_ATTACHMENT_DOWNLOAD_HEADER = "X-MxA0QVGVEJw";
    public static final String TRIGGER_FLOW_HEADER = "X-FB-Trigger-Flow";
    public static final String TRIGGER_FLOW_HEADER_USER = "X-FB-Trigger-Flow-User";
    public static final String UPDATED_ACCESS_TOKEN_HEADER = "X-FB-Updated-Access-Token";
    public static final String USER_AGENT = "User-Agent";
    public static final String USER_IN_ACTOR_GATEWAY = "X-FB-ActorGatewayEnrollment";
    public static final String USER_IN_ACTOR_GATEWAY_FLOW_ID = "X-FB-ActorGatewayEnrollment-FlowID";
    public static final String USER_IN_FEATURE_LIMIT = "X-FB-Featurelimit";
    public static final String USER_IN_LOGGED_IN_CHECKPOINT_HEADER = "X-FB-Blocking-Checkpoint";
    public static final String USER_IN_LOGGED_IN_CHECKPOINT_IS_NATIVE = "X-FB-Blocking-Checkpoint-Is-Native";
    public static final String VIDEO_UPLOAD_METHOD = "X-FB-Video-Upload-Method";
    public static final String VIDEO_WATERFALL_ID = "X_FB_VIDEO_WATERFALL_ID";
}
