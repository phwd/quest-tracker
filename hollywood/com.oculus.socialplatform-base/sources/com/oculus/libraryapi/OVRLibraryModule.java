package com.oculus.libraryapi;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0Qj;
import X.AnonymousClass0Qr;
import X.AnonymousClass0VB;
import X.AnonymousClass0VC;
import X.AnonymousClass0VI;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C00610Hs;
import X.C01150Rm;
import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import javax.inject.Provider;

@InjectorModule
public class OVRLibraryModule extends AnonymousClass0VI {
    public static volatile OVRLibraryInternal _UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_INSTANCE;
    public static volatile OVRLibrary _UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_INSTANCE;

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_BINDING_ID = 2118;
        public static final int _UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID = 106;
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_libraryapi_OVRLibraryInternal_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(2118, r1);
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_libraryapi_OVRLibrary_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(106, r2);
    }

    @AutoGeneratedAccessMethod
    public static final OVRLibraryInternal _UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (OVRLibraryInternal) AnonymousClass1TK.A00(2118, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final OVRLibraryInternal _UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_FACTORY_METHOD(AnonymousClass0lg r5, Object obj) {
        if (_UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_INSTANCE == null) {
            synchronized (OVRLibraryInternal.class) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_INSTANCE, r5);
                if (A00 != null) {
                    try {
                        AnonymousClass0lg applicationInjector = r5.getApplicationInjector();
                        OVRLibraryInternal oVRLibraryInternal = new OVRLibraryInternal(C00610Hs.A00(applicationInjector));
                        C01150Rm.A00(oVRLibraryInternal, applicationInjector);
                        _UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_INSTANCE = oVRLibraryInternal;
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final OVRLibrary _UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (OVRLibrary) AnonymousClass1TK.A00(106, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final OVRLibrary _UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_FACTORY_METHOD(AnonymousClass0lg r5, Object obj) {
        if (_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_INSTANCE == null) {
            synchronized (OVRLibrary.class) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_INSTANCE, r5);
                if (A00 != null) {
                    try {
                        AnonymousClass0lg applicationInjector = r5.getApplicationInjector();
                        OVRLibrary oVRLibrary = new OVRLibrary(C00610Hs.A00(applicationInjector));
                        C01150Rm.A00(oVRLibrary, applicationInjector);
                        _UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_INSTANCE = oVRLibrary;
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_libraryapi_OVRLibraryInternal_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(2118, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_libraryapi_OVRLibrary_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(106, r2);
    }

    @ApplicationScoped
    @ProviderMethod
    public static OVRLibrary provideOVRLibrary(@ForAppContext Context context) {
        return new OVRLibrary(context);
    }

    @ApplicationScoped
    @ProviderMethod(asDefault = true)
    @Deprecated
    public static OVRLibraryInternal provideOVRLibraryInternal(@ForAppContext Context context) {
        return new OVRLibraryInternal(context);
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForOVRLibraryModule {
        public static void bind(AnonymousClass0Qr r0) {
        }
    }
}