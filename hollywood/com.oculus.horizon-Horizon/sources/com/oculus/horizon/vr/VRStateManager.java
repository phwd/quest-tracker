package com.oculus.horizon.vr;

import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import java.util.concurrent.atomic.AtomicLong;

@Dependencies({})
@ApplicationScoped
public class VRStateManager {
    public static volatile VRStateManager _UL__ULSEP_com_oculus_horizon_vr_VRStateManager_ULSEP_INSTANCE;
    public boolean mIsDocked;
    public final AtomicLong mLastUndockTime = new AtomicLong(0);
}
