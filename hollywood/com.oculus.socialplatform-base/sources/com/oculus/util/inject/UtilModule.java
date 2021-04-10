package com.oculus.util.inject;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0Qr;
import X.AnonymousClass0VC;
import X.AnonymousClass0VI;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C00610Hs;
import X.C01150Rm;
import android.content.Context;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.util.vr.VRUtils;
import javax.inject.Provider;

@InjectorModule
public class UtilModule extends AnonymousClass0VI {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_util_vr_VRUtils_ULSEP_BINDING_ID = 2107;
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_util_vr_VRUtils_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(2107, r1);
    }

    @AutoGeneratedAccessMethod
    public static final VRUtils _UL__ULSEP_com_oculus_util_vr_VRUtils_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (VRUtils) AnonymousClass1TK.A00(2107, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_util_vr_VRUtils_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(2107, r1);
    }

    @ProviderMethod(asDefault = true)
    public static VRUtils provideVRUtils(@UnsafeContextInjection Context context) {
        return new VRUtils(context);
    }

    @AutoGeneratedFactoryMethod
    public static final VRUtils _UL__ULSEP_com_oculus_util_vr_VRUtils_ULSEP_FACTORY_METHOD(AnonymousClass0lg r2, Object obj) {
        VRUtils vRUtils = new VRUtils(C00610Hs.A02(r2));
        C01150Rm.A00(vRUtils, r2);
        return vRUtils;
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForUtilModule {
        public static void bind(AnonymousClass0Qr r0) {
        }
    }
}
