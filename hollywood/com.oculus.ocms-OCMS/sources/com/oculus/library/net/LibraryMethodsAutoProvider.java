package com.oculus.library.net;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.http.core.ApiModule;

@Generated({"By: InjectorProcessor"})
public class LibraryMethodsAutoProvider extends AbstractProvider<LibraryMethods> {
    @Override // javax.inject.Provider
    public LibraryMethods get() {
        return new LibraryMethods(this, ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(this));
    }
}
