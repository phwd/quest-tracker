package com.oculus.mobileconfig.init;

import bolts.Continuation;
import bolts.Task;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.mobileconfig.impl.MobileConfigFactoryImpl;
import com.facebook.mobileconfig.impl.MobileConfigManagerSingletonHolder;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;
import com.facebook.tigon.iface.TigonServiceHolder;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.mobileconfig.init.MobileConfigInitModule;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Callable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_BINDING_ID"})
public class MobileConfigLoginHandler implements LoginHandler {
    private static final int SYNC_UPDATE_WAIT_TIME_MS = 1000;
    private static String TAG;
    private InjectionContext _UL_mInjectionContext;
    @Inject
    private final Provider<Credentials> mCredentialsProvider;
    @Inject
    private final Provider<MobileConfig> mMobileConfigFactoryProvider;
    @Inject
    private final Provider<MobileConfigManagerSingletonHolder> mMobileConfigManagerHolderProvider;
    @Inject
    private final Provider<OkTigonServiceHolder> mOkTigonServiceHolderProvider;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(MobileConfigInitModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final MobileConfigLoginHandler _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (MobileConfigLoginHandler) UL.factorymap.get(MobileConfigInitModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final MobileConfigLoginHandler _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new MobileConfigLoginHandler(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(MobileConfigInitModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public MobileConfigLoginHandler(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(3, injectorLike);
        this.mMobileConfigManagerHolderProvider = MobileConfigFactoryImplModule._UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULGT__ULSEP_ACCESS_METHOD(injectorLike);
        this.mMobileConfigFactoryProvider = MobileConfigFactoryModule._UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_factory_MobileConfig_ULGT__ULSEP_ACCESS_METHOD(injectorLike);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(injectorLike);
        this.mOkTigonServiceHolderProvider = OkTigonServiceHolder._UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULGT__ULSEP_ACCESS_METHOD(injectorLike);
        TAG = ((MobileConfigInitOptions) FbInjector.lazyInstance(1, MobileConfigInitModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID, this._UL_mInjectionContext)).mTagPrefix + MobileConfigLoginHandler.class.getSimpleName();
    }

    @Override // com.oculus.auth.handler.LoginHandler
    public Task<Void> afterLoginAsync() {
        return Task.call(new Callable<Void>() {
            /* class com.oculus.mobileconfig.init.MobileConfigLoginHandler.AnonymousClass2 */

            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                MobileConfigManagerSingletonHolder mobileConfigManagerSingletonHolder = (MobileConfigManagerSingletonHolder) MobileConfigLoginHandler.this.mMobileConfigManagerHolderProvider.get();
                BLog.v(MobileConfigLoginHandler.TAG, "Setting Tigon service");
                ((MobileConfigInit) FbInjector.lazyInstance(0, MobileConfigInitModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID, MobileConfigLoginHandler.this._UL_mInjectionContext)).login((Credentials) MobileConfigLoginHandler.this.mCredentialsProvider.get(), (TigonServiceHolder) MobileConfigLoginHandler.this.mOkTigonServiceHolderProvider.get(), true);
                ((MobileConfigFactoryImpl) MobileConfigLoginHandler.this.mMobileConfigFactoryProvider.get()).refreshSessionState();
                if (!((MobileConfigInitOptions) FbInjector.lazyInstance(1, MobileConfigInitModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID, MobileConfigLoginHandler.this._UL_mInjectionContext)).mShouldFetchOnLogin) {
                    return null;
                }
                BLog.v(MobileConfigLoginHandler.TAG, "Updating MobileConfig on login");
                boolean tryUpdateConfigsSynchronously = mobileConfigManagerSingletonHolder.tryUpdateConfigsSynchronously(1000);
                BLog.v(MobileConfigLoginHandler.TAG, "Update completed or timed out - sync fetched: %s", Boolean.valueOf(tryUpdateConfigsSynchronously));
                if (tryUpdateConfigsSynchronously || !mobileConfigManagerSingletonHolder.isFetchNeeded()) {
                    return null;
                }
                String format = String.format(Locale.US, "Unable to finish downloading config values after: %d ms", 1000);
                BLog.e(MobileConfigLoginHandler.TAG, format);
                throw new RuntimeException(format);
            }
        }, Task.BACKGROUND_EXECUTOR).continueWithTask(new Continuation<Void, Task<Void>>() {
            /* class com.oculus.mobileconfig.init.MobileConfigLoginHandler.AnonymousClass1 */

            @Override // bolts.Continuation
            public Task<Void> then(Task<Void> task) throws Exception {
                if (!task.isFaulted() || (task.getError() instanceof IOException)) {
                    return task;
                }
                Exception error = task.getError();
                ((IErrorReporter) FbInjector.lazyInstance(2, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, MobileConfigLoginHandler.this._UL_mInjectionContext)).softErrorWithCrash(MobileConfigLoginHandler.TAG, error.getMessage(), error);
                return null;
            }
        });
    }
}
