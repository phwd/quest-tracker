package com.facebook.tigon.oktigon;

import X.AbstractC0247Xu;
import X.C0431hn;
import X.Pj;
import com.facebook.inject.ApplicationScoped;
import com.facebook.jni.HybridData;
import com.facebook.tigon.iface.TigonServiceHolder;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonService_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OkTigonServiceHolder extends TigonServiceHolder {
    public static volatile OkTigonServiceHolder _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_INSTANCE;

    public static native HybridData initHybrid(OkTigonService okTigonService);

    static {
        C0431hn.A00("oktigon");
    }

    @AutoGeneratedFactoryMethod
    public static final OkTigonServiceHolder _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_FACTORY_METHOD(AbstractC0247Xu xu) {
        if (_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_INSTANCE == null) {
            synchronized (OkTigonServiceHolder.class) {
                Pj A00 = Pj.A00(_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_INSTANCE, xu);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_INSTANCE = new OkTigonServiceHolder(OkTigonService._UL__ULSEP_com_facebook_tigon_oktigon_OkTigonService_ULSEP_ACCESS_METHOD(xu.getApplicationInjector()));
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_INSTANCE;
    }

    @Inject
    public OkTigonServiceHolder(OkTigonService okTigonService) {
        super(initHybrid(okTigonService));
    }
}
