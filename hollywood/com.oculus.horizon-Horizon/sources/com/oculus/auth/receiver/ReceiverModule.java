package com.oculus.auth.receiver;

import X.AbstractC06640p5;
import X.AnonymousClass0J5;
import X.AnonymousClass0Pp;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import javax.inject.Provider;

@InjectorModule
public abstract class ReceiverModule extends AnonymousClass0J5 {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_BINDING_ID = 321;
        public static final int _UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_BINDING_ID = 329;
        public static final int _UL__ULSEP_com_oculus_auth_receiver_LogoutHandlersRunner_ULSEP_BINDING_ID = 308;
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_receiver_BaseLoginHandler_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(321, r2);
    }

    @AutoGeneratedAccessMethod
    public static final BaseLoginHandler _UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (BaseLoginHandler) AnonymousClass117.A00(321, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_receiver_BaseLoginHandler_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(321, r2);
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForReceiverModule {
        public static void bind(AnonymousClass0Pp r0) {
        }
    }
}
