package com.facebook.tigon.oktigon;

import X.C05400jG;
import com.facebook.inject.ApplicationScoped;
import com.facebook.jni.HybridData;
import com.facebook.tigon.iface.TigonServiceHolder;
import com.facebook.ultralight.Dependencies;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonService_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OkTigonServiceHolder extends TigonServiceHolder {
    public static volatile OkTigonServiceHolder _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_INSTANCE;

    public static native HybridData initHybrid(OkTigonService okTigonService);

    static {
        C05400jG.A00("oktigon");
    }

    @Inject
    public OkTigonServiceHolder(OkTigonService okTigonService) {
        super(initHybrid(okTigonService));
    }
}
