package com.oculus.horizon.api.registration;

import com.google.common.collect.ImmutableMap;
import com.oculus.auth.service.contract.ServiceContract;
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
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("email", this.email);
        A04.put("alias", this.username);
        A04.put("password", this.password);
        A04.put("first_name", this.firstName);
        A04.put("last_name", this.lastName);
        A04.put(OculusContent.Profile.LOCALE, this.preferredLocale);
        A04.put("accepted_tos_id", this.tosAgreedId);
        A04.put(ServiceContract.EXTRA_PIN, this.pin);
        A04.put("email_marketing_opt_in_status", this.marketingEmailOptInStatus);
        return A04.build();
    }
}
