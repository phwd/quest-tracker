package com.facebook.secure.logger;

import android.content.Intent;
import javax.annotation.Nullable;

public interface IntentLogger {
    void logIntent(String str, @Nullable String str2, @Nullable String str3, @Nullable Intent intent);
}
