package com.facebook.acra.anr.sigquit;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.Nullable;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Initializer;
import com.facebook.infer.annotation.Nullsafe;

@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class SigquitDetectorLacrima implements SigquitDetector {
    private static final String TAG = "SigquitDetectorLacrima";
    private static SigquitDetector sInstance;
    private static boolean sIsArt;
    private SigquitDetectorListener mListener;
    private Handler mMainThreadHandler;

    /* access modifiers changed from: private */
    public static native void nativeAddSignalHandler();

    private static native void nativeCleanupAppStateFile();

    /* access modifiers changed from: private */
    public static native boolean nativeHookMethods();

    private static native void nativeInit(Object obj, boolean z, int i, String str, String str2, @Nullable String str3, @Nullable String str4, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, @Nullable String str5, boolean z7);

    private static native void nativeStartDetector();

    private static native void nativeStopDetector();

    static {
        String property = System.getProperty("java.vm.version");
        sIsArt = property != null && !property.startsWith("1.") && !property.startsWith("0.");
    }

    public static SigquitDetector getInstance(SigquitDetectorListener sigquitDetectorListener) {
        if (sInstance == null) {
            sInstance = new SigquitDetectorLacrima(sigquitDetectorListener);
        }
        return sInstance;
    }

    private SigquitDetectorLacrima(SigquitDetectorListener sigquitDetectorListener) {
        this.mListener = sigquitDetectorListener;
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    @Initializer
    public void init(ANRDetectorConfig aNRDetectorConfig, boolean z) {
        BLog.w(TAG, "nativeInit");
        this.mMainThreadHandler = aNRDetectorConfig.getMainThreadHandler();
        nativeInit(this, sIsArt, Build.VERSION.SDK_INT, aNRDetectorConfig.getAppVersionCode(), aNRDetectorConfig.getAppVersionName(), aNRDetectorConfig.getTracesPath(), aNRDetectorConfig.getTracesExtension(), aNRDetectorConfig.processShouldSendAnrReports(), z, aNRDetectorConfig.shouldReportSoftErrors(), aNRDetectorConfig.shouldLogOnSignalHandler(), aNRDetectorConfig.shouldAvoidMutexOnSignalHandler(), aNRDetectorConfig.getSigquitTimeFilePath(), aNRDetectorConfig.shouldRecordSignalTime());
    }

    @DoNotStrip
    private void sigquitDetected(String str, String str2, boolean z, boolean z2) {
        BLog.w(TAG, "sigquitDetected inFgV1: %b inFgV2: %b", Boolean.valueOf(z), Boolean.valueOf(z2));
        this.mListener.sigquitDetected(str, str2, z, z2);
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    public void startDetector() {
        nativeStartDetector();
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    public void stopDetector() {
        nativeStopDetector();
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    public void cleanupAppStateFile() {
        nativeCleanupAppStateFile();
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    public void installSignalHandler(final Handler handler, final boolean z) {
        final AnonymousClass1 r0 = new Runnable() {
            /* class com.facebook.acra.anr.sigquit.SigquitDetectorLacrima.AnonymousClass1 */

            public void run() {
                boolean nativeHookMethods = SigquitDetectorLacrima.nativeHookMethods();
                SigquitDetectorLacrima.this.mListener.onHookedMethods(nativeHookMethods);
                if (nativeHookMethods && z) {
                    SigquitDetectorLacrima.this.startDetector();
                }
            }
        };
        this.mMainThreadHandler.post(new Runnable() {
            /* class com.facebook.acra.anr.sigquit.SigquitDetectorLacrima.AnonymousClass2 */

            public void run() {
                SigquitDetectorLacrima.nativeAddSignalHandler();
                handler.post(r0);
            }
        });
    }
}
