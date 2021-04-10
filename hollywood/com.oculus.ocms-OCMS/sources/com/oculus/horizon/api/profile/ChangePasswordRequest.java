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
        return ImmutableMap.builder().put("client_mutation_id", UUID.randomUUID().toString()).put("current_password", this.password).put("new_password", this.newPassword).put("new_password_confirm", this.newPassword).build();
    }
}
