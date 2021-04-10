package com.facebook.secure.logger;

import android.content.Intent;
import javax.annotation.Nullable;

public interface IntentLogger {

    public @interface Status {
        public static final String ALLOW = "allow";
        public static final String DENY = "deny";
    }

    void logIntent(String str, @Nullable Intent intent);

    void logIntent(String str, @Nullable String str2, @Status @Nullable String str3, @Nullable Intent intent);
}
