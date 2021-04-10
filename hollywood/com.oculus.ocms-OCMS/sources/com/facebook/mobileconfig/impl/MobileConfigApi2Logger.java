package com.facebook.mobileconfig.impl;

import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.mobileconfig.factory.MobileConfigContext;

public interface MobileConfigApi2Logger {
    @ThreadSafe
    void log(int i, MobileConfigContext mobileConfigContext);
}
