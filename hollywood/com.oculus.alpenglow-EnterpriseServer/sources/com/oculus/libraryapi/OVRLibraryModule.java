package com.oculus.libraryapi;

import X.AbstractC01750Lk;
import X.AbstractC02990bJ;
import X.AnonymousClass0Qe;
import X.C01340Gg;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;

@InjectorModule
public class OVRLibraryModule extends AbstractC01750Lk {
    public static volatile OVRLibraryInternal _UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_INSTANCE;
    public static volatile OVRLibrary _UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_INSTANCE;

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForOVRLibraryModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_BINDING_ID = 2099;
        public static final int _UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID = 119;
    }

    @AutoGeneratedFactoryMethod
    public static final OVRLibrary A00(AbstractC02990bJ r4) {
        if (_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_INSTANCE == null) {
            synchronized (OVRLibrary.class) {
                AnonymousClass0Qe A00 = AnonymousClass0Qe.A00(_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_INSTANCE = new OVRLibrary(C01340Gg.A00(r4.getApplicationInjector()));
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_INSTANCE;
    }
}