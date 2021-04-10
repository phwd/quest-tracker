package com.facebook.common.errorreporting;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface FbErrorReporter {
    void softReport(SoftError softError);

    void softReport(String str, String str2);

    void softReport(String str, String str2, int i);
}
