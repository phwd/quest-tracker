package com.oculus.http.core;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class Boolean_com_oculus_http_core_annotations_ReportGraphBackendExceptionMethodAutoProvider extends AbstractProvider<Boolean> {
    @Override // javax.inject.Provider
    public Boolean get() {
        return ApiModule.provideReportGraphBackendException();
    }
}
