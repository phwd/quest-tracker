package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class Boolean_com_oculus_http_core_annotations_ReportGraphBackendExceptionMethodAutoProvider extends AbstractProvider<Boolean> {
    @Override // javax.inject.Provider
    public Boolean get() {
        return OCMSAppModule.provideReportGraphBackendException(OCMSConfigValueProvider._UL__ULSEP_com_oculus_ocms_app_OCMSConfigValueProvider_ULSEP_ACCESS_METHOD(this));
    }
}
