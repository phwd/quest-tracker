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
import java.io.IOException;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_capture_SurfaceBroadcast_ULSEP_BINDING_ID"})
public class SurfaceCapture {
    public static final String TAG = "SurfaceCapture";
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    public boolean mLiftInhibit;
    public String mPackageName;
    public boolean mShowCaptureIndicator = true;
    @Nullable
    public Surface mSurface;
    @Inject
    @Eager
    public final SurfaceBroadcast mSurfaceBroadcast;
    public boolean mSurfaceCaptureStarted;

    public final void A01(String str) {
        if (str != null) {
            try {
                this.mPackageName = str;
            } catch (Throwable th) {
                this.mSurface = null;
                this.mPackageName = null;
                this.mSurfaceCaptureStarted = false;
                throw th;
            }
        }
        A00(this, this.mPackageName, null);
        this.mSurface = null;
        this.mPackageName = null;
        this.mSurfaceCaptureStarted = false;
    }

    public static void A00(SurfaceCapture surfaceCapture, @Nullable String str, Surface surface) {
        try {
            surfaceCapture.mSurfaceBroadcast.A00(surfaceCapture.mContext, str, surface, surfaceCapture.mLiftInhibit, surfaceCapture.mShowCaptureIndicator);
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "Failed to broadcast surface", e);
        }
    }

    public final void A02(String str, Surface surface) {
        String str2;
        String str3;
        if (str != null) {
            this.mPackageName = str;
        }
        String str4 = this.mPackageName;
        if (str4 == null) {
            str2 = TAG;
            str3 = "null package name, cannot start recording!";
        } else if (surface == null) {
            str2 = TAG;
            str3 = "null surface, cannot start recording!";
        } else {
            this.mSurface = surface;
            this.mSurfaceCaptureStarted = true;
            A00(this, str4, surface);
            return;
        }
        AnonymousClass0NO.A08(str2, str3);
    }

    @Inject
    public SurfaceCapture(AbstractC06640p5 r2) {
        this.mContext = C003108z.A02(r2);
        this.mSurfaceBroadcast = (SurfaceBroadcast) AnonymousClass117.A00(550, r2);
    }
}
