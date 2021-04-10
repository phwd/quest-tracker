package com.facebook.mobileconfigservice.client_ifaces;

public interface MobileConfigServiceSubscribeCallback {
    void onMobileConfigSubscribeFailure(String str);

    void onMobileConfigSubscribeSuccess();
}
