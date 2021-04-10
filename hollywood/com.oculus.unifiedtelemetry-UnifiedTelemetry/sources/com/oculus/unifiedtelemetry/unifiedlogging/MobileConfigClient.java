package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0247Xu;
import X.QC;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.INeedInit;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID"})
@ApplicationScoped
@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigClient implements INeedInit {
    public static volatile MobileConfigClient _UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_MobileConfigClient_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public volatile boolean mIsInitialized;

    @Override // com.oculus.common.init.INeedInit
    public final void A36() {
        this.mIsInitialized = true;
    }

    @Inject
    public MobileConfigClient(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(1, xu);
    }
}
