package X;

import androidx.viewpager.widget.ViewPager;
import com.oculus.http.core.base.ApiErrorCodes;
import com.oculus.http.core.interceptor.AuthorizationInterceptor;
import com.squareup.okhttp.internal.http.StatusLine;

/* renamed from: X.1Xz  reason: invalid class name */
public enum AnonymousClass1Xz implements AnonymousClass1iB {
    SWITCH_PROTOCOL(101, "Switching Protocols"),
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NO_CONTENT(204, "No Content"),
    PARTIAL_CONTENT(206, "Partial Content"),
    MULTI_STATUS(207, "Multi-Status"),
    REDIRECT(301, "Moved Permanently"),
    FOUND(302, "Found"),
    REDIRECT_SEE_OTHER(303, "See Other"),
    NOT_MODIFIED(304, "Not Modified"),
    TEMPORARY_REDIRECT(StatusLine.HTTP_TEMP_REDIRECT, "Temporary Redirect"),
    BAD_REQUEST(ViewPager.MIN_FLING_VELOCITY, "Bad Request"),
    UNAUTHORIZED(AuthorizationInterceptor.HTTP_STATUS_UNAUTHORIZED, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    REQUEST_TIMEOUT(408, "Request Timeout"),
    CONFLICT(409, "Conflict"),
    GONE(410, "Gone"),
    LENGTH_REQUIRED(411, "Length Required"),
    PRECONDITION_FAILED(412, "Precondition Failed"),
    PAYLOAD_TOO_LARGE(ApiErrorCodes.ERROR_CODE_FAILED_AUTHENTICATION_WRONG_PASSWORD, "Payload Too Large"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),
    EXPECTATION_FAILED(417, "Expectation Failed"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    INTERNAL_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    UNSUPPORTED_HTTP_VERSION(505, "HTTP Version Not Supported");
    
    public final String description;
    public final int requestStatus;

    @Override // X.AnonymousClass1iB
    public String getDescription() {
        StringBuilder sb = new StringBuilder("");
        sb.append(this.requestStatus);
        sb.append(" ");
        sb.append(this.description);
        return sb.toString();
    }

    /* access modifiers changed from: public */
    AnonymousClass1Xz(int i, String str) {
        this.requestStatus = i;
        this.description = str;
    }

    public static AnonymousClass1Xz lookup(int i) {
        AnonymousClass1Xz[] values = values();
        for (AnonymousClass1Xz r1 : values) {
            if (r1.getRequestStatus() == i) {
                return r1;
            }
        }
        return null;
    }

    public int getRequestStatus() {
        return this.requestStatus;
    }
}
