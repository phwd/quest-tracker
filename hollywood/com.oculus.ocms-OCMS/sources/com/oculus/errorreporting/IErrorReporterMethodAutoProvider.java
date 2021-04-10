package com.oculus.errorreporting;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.errorreporting.interfaces.IErrorReporter;

@Generated({"By: InjectorProcessor"})
public class IErrorReporterMethodAutoProvider extends AbstractProvider<IErrorReporter> {
    @Override // javax.inject.Provider
    public IErrorReporter get() {
        return ErrorReportingModule.provideFbErrorReporter();
    }
}
