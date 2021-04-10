package com.facebook.mobileconfig.interfaces;

import com.facebook.mobileconfig.MobileConfigManagerHolder;

public interface MobileConfigInitInterface {
    void init();

    boolean initNetwork(MobileConfigManagerHolder mobileConfigManagerHolder);

    void login(String str);

    void logout();

    boolean shouldEnableMobileConfig(String str);

    boolean updateMobileConfigManagerHolderIfNecessary();
}
