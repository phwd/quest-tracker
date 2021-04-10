package com.oculus.logging.utils;

import javax.annotation.Nullable;

public interface EventManager {
    Event createEvent(String str);

    Event createEvent(String str, @Nullable String str2, boolean z);
}
