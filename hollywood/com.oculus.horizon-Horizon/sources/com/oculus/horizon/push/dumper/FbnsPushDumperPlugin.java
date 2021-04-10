package com.oculus.horizon.push.dumper;

import X.AbstractC06640p5;
import X.AnonymousClass0dM;
import X.AnonymousClass117;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.oculus.horizon.push.FbnsPushProcessor;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_push_FbnsPushProcessor_ULSEP_BINDING_ID"})
public class FbnsPushDumperPlugin implements AnonymousClass0dM {
    public static final String CMD_PUSH = "push";
    public static final String EXTRA_DATA = "data";
    public static final String NAME = "fbns";
    @Inject
    @Eager
    public final FbnsPushProcessor mFbnsPushProcessor;

    @Inject
    public FbnsPushDumperPlugin(AbstractC06640p5 r2) {
        this.mFbnsPushProcessor = (FbnsPushProcessor) AnonymousClass117.A00(362, r2);
    }
}
