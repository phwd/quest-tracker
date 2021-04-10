package com.facebook.common.errorreporting;

import com.facebook.common.util.TriState;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface ErrorReportingGkReader {
    TriState isFailHarderFatalDisabled();

    TriState isSoftErrorReportingDisabled();

    TriState shouldReportNativeSoftErrors();
}
