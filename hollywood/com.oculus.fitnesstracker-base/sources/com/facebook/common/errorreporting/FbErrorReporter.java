package com.facebook.common.errorreporting;

public interface FbErrorReporter {
    void softReport(SoftError softError);
}
