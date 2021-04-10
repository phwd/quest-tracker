package com.oculus.horizon.api.tos;

import com.oculus.horizon.api.common.user.User;

public class AcceptTermsOfServiceResponse {
    public TosAccept tos_accept;

    public static class TosAccept {
        public User user;
    }
}
