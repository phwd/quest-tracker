package com.facebook.common.errorreporting;

import com.facebook.common.util.TriState;

public interface ErrorReportingGkReader {
    TriState isFailHarderFatalDisabled();

    TriState isSoftErrorReportingDisabled();

    TriState shouldReportNativeSoftErrors();
}
