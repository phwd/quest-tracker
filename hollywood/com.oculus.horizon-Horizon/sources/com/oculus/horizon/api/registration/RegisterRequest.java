package com.oculus.horizon.api.registration;

import com.facebook.Profile;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import com.oculus.provider.OculusContent;

public class RegisterRequest extends ApiRequest<RegisterResponse> {
    public final String email;
    public final String firstName;
    public final String lastName;
    public final String marketingEmailOptInStatus;
    public final String password;
    public final String pin;
    public final String preferredLocale;
    public final String tosAgreedId;
    public final String username;

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("email", this.email);
        A01.put(OculusContent.FriendList.ALIAS_COLUMN, this.username);
        A01.put("password", this.password);
        A01.put(Profile.FIRST_NAME_KEY, this.firstName);
        A01.put(Profile.LAST_NAME_KEY, this.lastName);
        A01.put("locale", this.preferredLocale);
        A01.put("accepted_tos_id", this.tosAgreedId);
        A01.put("pin", this.pin);
        A01.put("email_marketing_opt_in_status", this.marketingEmailOptInStatus);
        return A01.build();
    }
}
