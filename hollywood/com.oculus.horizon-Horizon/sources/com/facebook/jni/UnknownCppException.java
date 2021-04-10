package com.facebook.jni;

import com.facebook.internal.AnalyticsEvents;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class UnknownCppException extends CppException {
    @DoNotStrip
    public UnknownCppException() {
        super(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
    }

    @DoNotStrip
    public UnknownCppException(String str) {
        super(str);
    }
}
