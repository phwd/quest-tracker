package com.oculus.horizon.api.registration;

import com.facebook.ipc.activity.BaseActivityConstants;
import com.google.common.collect.ImmutableMap;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.http.core.base.ApiRequest;
import com.oculus.provider.OculusContent;

public class RegisterRequest extends ApiRequest<RegisterResponse> {
    public String email;
    public String firstName;
    public String lastName;
    public String marketingEmailOptInStatus;
    public String password;
    public String pin;
    public String preferredLocale;
    public String tosAgreedId;
    public String username;

    public ImmutableMap<String, String> getParams() {
        return ImmutableMap.builder().put("email", this.email).put(OculusContent.FriendList.ALIAS_COLUMN, this.username).put(ServiceContract.EXTRA_PASSWORD, this.password).put(BaseActivityConstants.Extras.FIRST_NAME, this.firstName).put("last_name", this.lastName).put(OculusContent.Profile.LOCALE, this.preferredLocale).put("accepted_tos_id", this.tosAgreedId).put(ServiceContract.EXTRA_PIN, this.pin).put("email_marketing_opt_in_status", this.marketingEmailOptInStatus).build();
    }
}
