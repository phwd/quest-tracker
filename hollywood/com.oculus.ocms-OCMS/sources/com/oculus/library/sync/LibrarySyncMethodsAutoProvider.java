package com.oculus.library.sync;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.http.core.ApiModule;

@Generated({"By: InjectorProcessor"})
public class LibrarySyncMethodsAutoProvider extends AbstractProvider<LibrarySyncMethods> {
    @Override // javax.inject.Provider
    public LibrarySyncMethods get() {
        return new LibrarySyncMethods(ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_ACCESS_METHOD(this));
    }
}
