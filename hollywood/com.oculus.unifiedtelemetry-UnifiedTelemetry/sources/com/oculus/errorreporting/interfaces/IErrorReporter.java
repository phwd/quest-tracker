package com.oculus.errorreporting.interfaces;

import com.facebook.inject.RequiresBinding;

@RequiresBinding
public interface IErrorReporter {
    void A5H(String str, String str2);

    void A5I(String str, String str2, Throwable th);

    void A5J(String str, String str2, Throwable th);
}
