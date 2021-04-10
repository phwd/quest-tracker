package com.oculus.errorreporting;

import com.facebook.inject.AbstractProvider;
import com.oculus.errorreporting.interfaces.IErrorReporter;

public class IErrorReporterMethodAutoProvider extends AbstractProvider<IErrorReporter> {
    @Override // javax.inject.Provider
    public IErrorReporter get() {
        return ErrorReportingModule.provideFbErrorReporter();
    }
}
