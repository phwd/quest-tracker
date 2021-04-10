package com.facebook.mobileconfig.impl;

public interface MobileConfigEmergencyPush {
    boolean onEpConfigChanged(EmergencyPushConfigsList emergencyPushConfigsList, MobileConfigFactoryImpl mobileConfigFactoryImpl);
}
