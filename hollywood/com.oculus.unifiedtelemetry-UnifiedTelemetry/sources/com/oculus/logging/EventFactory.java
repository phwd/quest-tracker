package com.oculus.logging;

import javax.annotation.Nullable;

public interface EventFactory {
    OculusLoggingEvent A3S(String str);

    OculusLoggingEvent A3T(@Nullable String str, String str2, boolean z);
}
