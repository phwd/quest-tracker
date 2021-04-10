package com.oculus.auth.receiver;

import com.facebook.inject.RequiresBinding;

@RequiresBinding
public interface BaseLoginHandler {
    void onLogin();

    void onLogout();

    void onRelogin();
}
