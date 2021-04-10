package com.facebook.mobileconfig.troubleshooting;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface BisectCallback {
    @DoNotStrip
    void onResponse(boolean z, String str);
}
