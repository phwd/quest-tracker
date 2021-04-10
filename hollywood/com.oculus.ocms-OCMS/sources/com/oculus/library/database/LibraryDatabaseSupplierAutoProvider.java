package com.oculus.library.database;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.errorreporting.interfaces.InterfacesModule;

@Generated({"By: InjectorProcessor"})
public class LibraryDatabaseSupplierAutoProvider extends AbstractProvider<LibraryDatabaseSupplier> {
    @Override // javax.inject.Provider
    public LibraryDatabaseSupplier get() {
        return new LibraryDatabaseSupplier(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this), InterfacesModule._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_ACCESS_METHOD(this), LibrarySchemaPart._UL__ULSEP_com_oculus_library_database_LibrarySchemaPart_ULSEP_ACCESS_METHOD(this));
    }
}
