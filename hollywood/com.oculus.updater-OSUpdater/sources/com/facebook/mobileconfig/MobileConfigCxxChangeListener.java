package com.facebook.mobileconfig;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface MobileConfigCxxChangeListener {
    @DoNotStrip
    void onConfigChanged(String[] strArr);
}
