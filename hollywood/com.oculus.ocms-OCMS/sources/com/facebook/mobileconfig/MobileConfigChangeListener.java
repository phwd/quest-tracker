package com.facebook.mobileconfig;

public interface MobileConfigChangeListener {
    public static final int ANY = -1;
    public static final String ANY_CONFIG = "MOBILE_CONFIG_ANY";

    int getConfigSpecifier();

    void onConfigChanged(int i);
}
