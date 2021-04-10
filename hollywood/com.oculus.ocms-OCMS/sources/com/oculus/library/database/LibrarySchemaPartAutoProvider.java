package com.oculus.library.database;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LibrarySchemaPartAutoProvider extends AbstractProvider<LibrarySchemaPart> {
    @Override // javax.inject.Provider
    public LibrarySchemaPart get() {
        return new LibrarySchemaPart(LibraryTable._UL__ULSEP_com_oculus_library_database_LibraryTable_ULSEP_ACCESS_METHOD(this));
    }
}
