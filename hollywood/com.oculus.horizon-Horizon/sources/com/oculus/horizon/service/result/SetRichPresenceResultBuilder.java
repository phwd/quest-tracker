package com.oculus.horizon.service.result;

import X.AbstractC06640p5;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.platform.PresenceManager;
import com.oculus.horizon.service.OVRService;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_BINDING_ID"})
public class SetRichPresenceResultBuilder extends ResultBuilder {
    public static final String RECEIVER_TAG_KEY = "receiver_tag";
    public static final String RICH_PRESENCE_JSON_ARG_KEY = "rich_presence_json";
    @Inject
    @Eager
    public final PresenceManager mPresenceManager;

    @Inject
    public SetRichPresenceResultBuilder(AbstractC06640p5 r2, @Assisted OVRService oVRService) {
        super(oVRService);
        this.mPresenceManager = PresenceManager._UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_ACCESS_METHOD(r2);
    }
}
