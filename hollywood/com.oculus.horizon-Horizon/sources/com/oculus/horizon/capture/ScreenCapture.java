package com.oculus.horizon.capture;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass1X9;
import android.content.Context;
import android.view.Surface;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import java.io.File;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_capture_CaptureDelegateFactory_ULSEP_BINDING_ID"})
public class ScreenCapture {
    public static final String TAG = "ScreenCapture";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Nullable
    public CaptureDelegate mCaptureDelegate;
    public boolean mIsCapturing;

    public interface VideoStateListener {
        void A2z();

        void A60(Exception exc);

        void A6z(Surface surface);

        void A72(File file);
    }

    public final void A00(VideoStateListener videoStateListener, AnonymousClass1X9 r5) {
        this.mCaptureDelegate = new VideoCapture(videoStateListener, (Context) AnonymousClass0J2.A03(0, 80, ((CaptureDelegateFactory) AnonymousClass0J2.A03(0, 145, this._UL_mInjectionContext))._UL_mInjectionContext), r5);
    }

    @Inject
    public ScreenCapture(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
