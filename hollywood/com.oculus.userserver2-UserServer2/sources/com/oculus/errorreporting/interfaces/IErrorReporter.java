package com.oculus.errorreporting.interfaces;

import com.facebook.inject.RequiresBinding;

@RequiresBinding
public interface IErrorReporter {
    void A3k(String str, String str2);

    void A3l(String str, String str2, Throwable th);
}
