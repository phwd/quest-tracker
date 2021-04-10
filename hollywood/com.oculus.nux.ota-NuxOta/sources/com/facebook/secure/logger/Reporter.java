package com.facebook.secure.logger;

public interface Reporter {
    void report(String str, String str2, Throwable th);
}
