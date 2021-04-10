package com.facebook.secure.logger;

import javax.annotation.Nullable;

public interface Reporter {
    void report(String str);

    void report(String str, String str2, @Nullable Throwable th);
}
