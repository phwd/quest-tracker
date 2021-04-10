package com.android.volley;

public class VolleyError extends Exception {
    public final NetworkResponse networkResponse;
    private long networkTimeMs;

    public VolleyError() {
        this.networkResponse = null;
    }

    public VolleyError(NetworkResponse response) {
        this.networkResponse = response;
    }

    public VolleyError(String exceptionMessage) {
        super(exceptionMessage);
        this.networkResponse = null;
    }

    public VolleyError(String exceptionMessage, Throwable reason) {
        super(exceptionMessage, reason);
        this.networkResponse = null;
    }

    public VolleyError(Throwable cause) {
        super(cause);
        this.networkResponse = null;
    }

    /* access modifiers changed from: package-private */
    public void setNetworkTimeMs(long networkTimeMs2) {
        this.networkTimeMs = networkTimeMs2;
    }

    public long getNetworkTimeMs() {
        return this.networkTimeMs;
    }
}
