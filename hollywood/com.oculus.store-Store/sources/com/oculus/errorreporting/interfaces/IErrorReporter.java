package com.oculus.errorreporting.interfaces;

import com.facebook.inject.RequiresBinding;

@RequiresBinding
public interface IErrorReporter {
    void putCurrentUserId(String str);

    void softError(String str, String str2);

    void softError(String str, String str2, Throwable th);

    void softErrorWithCrash(String str, String str2);

    void softErrorWithCrash(String str, String str2, Throwable th);
}
