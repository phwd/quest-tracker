package com.facebook.quicklog.utils;

import com.facebook.infer.annotation.ThreadSafe;
import javax.annotation.Nullable;

@ThreadSafe
public interface LogProxy {
    void d(String str, String str2);

    void d(String str, String str2, @Nullable Object obj);

    void d(String str, String str2, @Nullable Object obj, @Nullable Object obj2);

    void d(String str, String str2, Throwable th);

    void e(String str, String str2);

    void e(String str, String str2, @Nullable Object obj);

    void e(String str, String str2, @Nullable Object obj, @Nullable Object obj2);

    void e(String str, String str2, Throwable th);

    void i(String str, String str2);

    void i(String str, String str2, @Nullable Object obj);

    void i(String str, String str2, @Nullable Object obj, @Nullable Object obj2);

    void i(String str, String str2, Throwable th);

    void log(int i, String str, String str2);

    void v(String str, String str2);

    void v(String str, String str2, @Nullable Object obj);

    void v(String str, String str2, @Nullable Object obj, @Nullable Object obj2);

    void v(String str, String str2, Throwable th);

    void w(String str, String str2);

    void w(String str, String str2, @Nullable Object obj);

    void w(String str, String str2, @Nullable Object obj, @Nullable Object obj2);

    void w(String str, String str2, Throwable th);

    void wtf(String str, String str2);

    void wtf(String str, String str2, Throwable th);
}
