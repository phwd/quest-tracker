package com.facebook.mobileconfig;

import com.facebook.infer.annotation.ThreadSafe;

@ThreadSafe
public interface MobileConfigUserIdProvider {
    String getUserId();
}
