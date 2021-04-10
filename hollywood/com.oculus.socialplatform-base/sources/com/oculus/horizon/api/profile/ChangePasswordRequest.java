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
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("client_mutation_id", UUID.randomUUID().toString());
        A04.put("current_password", this.password);
        A04.put("new_password", this.newPassword);
        A04.put("new_password_confirm", this.newPassword);
        return A04.build();
    }
}
