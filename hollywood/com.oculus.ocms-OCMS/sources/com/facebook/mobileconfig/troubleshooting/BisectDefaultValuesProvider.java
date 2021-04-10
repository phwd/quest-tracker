package com.facebook.mobileconfig.troubleshooting;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface BisectDefaultValuesProvider {
    @DoNotStrip
    String getMobileConfigFieldValue(long j);
}
