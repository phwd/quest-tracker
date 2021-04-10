package com.oculus.auth.receiver;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import com.oculus.auth.handler.AuthHandlerModule;
import com.oculus.time.TimeModule;
import javax.inject.Provider;

@InjectorModule
public abstract class ReceiverModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(BaseLoginHandler.class)));
        public static final int _UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LoginHandlersRunner.class)));
        public static final int _UL__ULSEP_com_oculus_auth_receiver_LogoutHandlersRunner_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_auth_receiver_LogoutHandlersRunner_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LogoutHandlersRunner.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_receiver_BaseLoginHandler_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final BaseLoginHandler _UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (BaseLoginHandler) UL.factorymap.get(UL_id._UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_receiver_BaseLoginHandler_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForReceiverModule {
        AutoGeneratedBindingsForReceiverModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.assertBindingInstalled(BaseLoginHandler.class);
                binder.require(BundledAndroidModule.class);
                binder.require(AuthHandlerModule.class);
                binder.require(TimeModule.class);
                binder.bind(LoginHandlersRunner.class).toProvider(new LoginHandlersRunnerAutoProvider()).in(ApplicationScoped.class);
                binder.bind(LogoutHandlersRunner.class).toProvider(new LogoutHandlersRunnerAutoProvider());
                binder.bindComponent(AuthBroadcastReceiver.class).toProvider(new AuthBroadcastReceiverAutoProvider());
                binder.bindComponent(LoginHandlersService.class).toProvider(new LoginHandlersServiceAutoProvider());
            }
        }
    }
}