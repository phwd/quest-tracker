package com.oculus.horizon.api.logout;

import com.oculus.http.core.base.ApiRequest;

public class LogoutRequest extends ApiRequest<LogoutResponse> {
    public final String device_id;

    public LogoutRequest(String str) {
        this.device_id = str;
    }
}
