package com.oculus.horizon.api.profile;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import java.util.UUID;

public class ChangePasswordRequest extends ApiRequest<ChangePasswordResponse> {
    public String newPassword;
    public String password;

    public ChangePasswordRequest(String str, String str2) {
        this.password = str;
        this.newPassword = str2;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("client_mutation_id", UUID.randomUUID().toString());
        A01.put("current_password", this.password);
        A01.put("new_password", this.newPassword);
        A01.put("new_password_confirm", this.newPassword);
        return A01.build();
    }
}
