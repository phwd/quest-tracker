package com.oculus.horizon.service_media;

import X.AbstractC06640p5;
import X.AnonymousClass0NO;
import X.AnonymousClass117;
import X.C003108z;
import android.content.Context;
import android.view.Surface;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.capture.SurfaceBroadcast;
import com.oculus.horizon.service_media.contract.OVRMediaServiceContract;
import java.io.IOException;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_capture_SurfaceBroadcast_ULSEP_BINDING_ID"})
public class WebRTCCapture {
    public static final String TAG = "WebRTCCapture";
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Nullable
    public Surface mSurface;
    @Inject
    @Eager
    public final SurfaceBroadcast mSurfaceBroadcast;

    public final boolean A00(String str, Surface surface) {
        try {
            this.mSurfaceBroadcast.A00(this.mContext, str, surface, false, true);
            return true;
        } catch (IOException e) {
            AnonymousClass0NO.A0E(TAG, "Failed to send SurfaceBroadcast for package: %s with action: %s", str, OVRMediaServiceContract.BEGIN_VIDEO_CAPTURE_WITH_SURFACE_ACTION, e);
            return false;
        }
    }

    @Inject
    public WebRTCCapture(AbstractC06640p5 r2) {
        this.mContext = C003108z.A02(r2);
        this.mSurfaceBroadcast = (SurfaceBroadcast) AnonymousClass117.A00(550, r2);
    }
}
