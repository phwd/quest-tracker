package com.facebook.tigon.oktigon;

import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.jni.HybridData;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.tigon.iface.TigonServiceHolder;
import com.facebook.tigon.oktigon.OkTigonModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies
@ApplicationScoped
public class OkTigonServiceHolder extends TigonServiceHolder {
    private static volatile OkTigonServiceHolder _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_INSTANCE;

    private static native HybridData initHybrid(OkTigonService okTigonService);

    @AutoGeneratedFactoryMethod
    public static final OkTigonServiceHolder _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_INSTANCE == null) {
            synchronized (OkTigonServiceHolder.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_INSTANCE = new OkTigonServiceHolder(OkTigonService._UL__ULSEP_com_facebook_tigon_oktigon_OkTigonService_ULSEP_ACCESS_METHOD(injectorLike.getApplicationInjector()));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(OkTigonModule.UL_id._UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_BINDING_ID, injectorLike);
    }

    static {
        NativeLoader.loadLibrary("oktigon");
    }

    @Inject
    public OkTigonServiceHolder(OkTigonService okTigonService) {
        super(initHybrid(okTigonService));
    }
}