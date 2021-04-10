package com.oculus.horizon.abuse_prevention;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_abuse_ULUNDERSCORE_prevention_VideoUploaderMethods_ULSEP_BINDING_ID"})
public class VideoUploaderServiceManager {
    public static final String TAG = "VideoUploaderServiceManager";
    @Inject
    @Eager
    public final VideoUploaderMethods mRestMethods;

    @Inject
    public VideoUploaderServiceManager(AbstractC06640p5 r2) {
        this.mRestMethods = (VideoUploaderMethods) AnonymousClass117.A00(241, r2);
    }
}
