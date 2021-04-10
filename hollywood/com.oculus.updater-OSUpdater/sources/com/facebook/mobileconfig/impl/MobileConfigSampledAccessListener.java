package com.facebook.mobileconfig.impl;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadSafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public interface MobileConfigSampledAccessListener {
    @ThreadSafe
    void onMobileConfigAccess(long j, int i, boolean z, @Nullable String str, @Nullable String str2, String str3, boolean z2);
}
