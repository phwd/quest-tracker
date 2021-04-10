package com.oculus.errorreporting;

import X.AnonymousClass0VG;
import com.facebook.annotations.Generated;
import com.oculus.errorreporting.interfaces.IErrorReporter;

@Generated({"By: InjectorProcessor"})
public class IErrorReporterMethodAutoProvider extends AnonymousClass0VG<IErrorReporter> {
    public IErrorReporter get() {
        return ErrorReporter.getInstance(null);
    }
}
