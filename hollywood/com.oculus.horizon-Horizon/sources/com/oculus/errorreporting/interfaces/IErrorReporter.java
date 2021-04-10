package com.oculus.errorreporting.interfaces;

import com.facebook.inject.RequiresBinding;

@RequiresBinding
public interface IErrorReporter {
    void A7i(String str);

    void A96(String str, String str2);

    void A97(String str, String str2, Throwable th);

    void A98(String str, String str2);

    void A99(String str, String str2, Throwable th);
}
