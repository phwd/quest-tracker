package com.oculus.horizon.api.profile;

import com.oculus.http.core.base.ApiRequest;

public class ChangePinRequest extends ApiRequest<ChangePinResponse> {
    public final String password;
    public final String pin;

    public ChangePinRequest(String str, String str2) {
        this.password = str;
        this.pin = str2;
    }
}
