package com.facebook.mobileconfig.troubleshooting;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface MobileConfigResponseCallback {
    @DoNotStrip
    void onResponse(boolean z, String str);
}
