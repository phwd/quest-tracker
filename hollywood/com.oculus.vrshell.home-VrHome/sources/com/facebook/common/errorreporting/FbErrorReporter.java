package com.facebook.common.errorreporting;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface FbErrorReporter {
    void putCurrentUserId(String str);

    void softReport(SoftError softError);
}
