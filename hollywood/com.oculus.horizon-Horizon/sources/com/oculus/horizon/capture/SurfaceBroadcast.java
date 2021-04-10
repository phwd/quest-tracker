package com.oculus.horizon.capture;

import X.AnonymousClass0NO;
import X.C02800bY;
import X.C02930bl;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Surface;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.oculus.horizon.service_media.contract.OVRMediaServiceContract;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Dependencies({})
@ApplicationScoped
public class SurfaceBroadcast {
    public static final String BEGIN_VIDEO_CAPTURE_ACTION = "com.oculus.systemactivities.BEGIN_VIDEO_CAPTURE";
    public static final String CAPTURE_KEY_INTENT_KEY = "capture_key";
    public static final String LIFT_INHIBIT_INTENT_KEY = "lift_inhibit";
    public static final String SHOW_CAPTURE_INDICATOR_INTENT_KEY = "show_capture_indicator";
    public static final String SURFACE_BROADCAST_VR_DRIVER_RESPONSE_THREAD = "SurfaceBroadcastVrDriverResponseThread";
    public static final String SURFACE_INTENT_KEY = "surface";
    public static final String TAG = "SurfaceBroadcast";
    public static volatile SurfaceBroadcast _UL__ULSEP_com_oculus_horizon_capture_SurfaceBroadcast_ULSEP_INSTANCE;
    public String mCaptureKey = "";
    @Nullable
    public Handler mHandler;
    public final Object mLock = new Object();

    public class BroadcastRequestRunnable implements Runnable {
        public final String mAction = OVRMediaServiceContract.BEGIN_VIDEO_CAPTURE_WITH_SURFACE_ACTION;
        public final Context mContext;
        public final boolean mLiftInhibit;
        public final String mPackageName;
        public final boolean mShowCaptureIndicator;
        public final CountDownLatch mSignal;
        @Nullable
        public final Surface mSurface;

        public BroadcastRequestRunnable(Context context, String str, @Nullable Surface surface, CountDownLatch countDownLatch, boolean z, boolean z2) {
            this.mContext = context;
            this.mPackageName = str;
            this.mSurface = surface;
            this.mSignal = countDownLatch;
            this.mLiftInhibit = z;
            this.mShowCaptureIndicator = z2;
        }

        public final void run() {
            SurfaceBroadcast surfaceBroadcast = SurfaceBroadcast.this;
            Context context = this.mContext;
            AnonymousClass1 r10 = new BroadcastReceiver() {
                /* class com.oculus.horizon.capture.SurfaceBroadcast.BroadcastRequestRunnable.AnonymousClass1 */

                public final void onReceive(Context context, Intent intent) {
                    BroadcastRequestRunnable.this.mSignal.countDown();
                }
            };
            String str = this.mPackageName;
            String str2 = this.mAction;
            Surface surface = this.mSurface;
            boolean z = this.mLiftInhibit;
            boolean z2 = this.mShowCaptureIndicator;
            Intent intent = new Intent(str2);
            intent.putExtra("surface", surface);
            intent.putExtra("capture_key", surfaceBroadcast.mCaptureKey);
            intent.putExtra(SurfaceBroadcast.LIFT_INHIBIT_INTENT_KEY, z);
            intent.putExtra(SurfaceBroadcast.SHOW_CAPTURE_INDICATOR_INTENT_KEY, z2);
            try {
                intent.setPackage(str);
                C02800bY.A02(intent, context, "com.oculus.capture.SurfaceBroadcast:sendBroadcast");
                context.sendOrderedBroadcast(intent, null, r10, null, -1, null, null);
                intent.setPackage("com.oculus.companion.server");
                C02800bY.A02(intent, context, "com.oculus.capture.SurfaceBroadcast:sendBroadcast");
                context.sendOrderedBroadcast(intent, null, r10, null, -1, null, null);
            } catch (C02930bl e) {
                AnonymousClass0NO.A0B(SurfaceBroadcast.TAG, "Failed to attach caller info to capture intent", e);
            }
        }
    }

    public final void A00(Context context, String str, Surface surface, @Nullable boolean z, boolean z2) throws IOException {
        Handler handler;
        if (context == null || str == null) {
            throw new IOException("Missing context or packageName for broadcast request!");
        }
        synchronized (this.mLock) {
            if (this.mHandler == null) {
                HandlerThread handlerThread = new HandlerThread(SURFACE_BROADCAST_VR_DRIVER_RESPONSE_THREAD);
                handlerThread.start();
                this.mHandler = new Handler(handlerThread.getLooper());
            }
        }
        synchronized (this.mLock) {
            handler = this.mHandler;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        handler.post(new BroadcastRequestRunnable(context, str, surface, countDownLatch, z, z2));
        try {
            countDownLatch.await(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            synchronized (this.mLock) {
                Handler handler2 = this.mHandler;
                if (handler2 != null) {
                    Looper looper = handler2.getLooper();
                    if (looper != null) {
                        looper.quit();
                    }
                    this.mHandler = null;
                }
                AnonymousClass0NO.A0B(TAG, "Failed to wait for broadcast to VrDriver to finish", e);
                throw new IOException("broadCastSignal.await() got interrupted", e);
            }
        }
    }
}
