package com.facebook.mobileconfig;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface MobileConfigEmergencyPushChangeListener {
    @DoNotStrip
    boolean onEpConfigChanged(String[] strArr, String[] strArr2);
}
