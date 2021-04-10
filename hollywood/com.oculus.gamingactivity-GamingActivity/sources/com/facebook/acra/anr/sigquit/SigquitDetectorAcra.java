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
public class SigquitDetectorAcra implements SigquitDetector {
    private static final String TAG = SigquitDetectorAcra.class.getSimpleName();
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
        String vmVersion = System.getProperty("java.vm.version");
        sIsArt = vmVersion != null && !vmVersion.startsWith("1.") && !vmVersion.startsWith("0.");
    }

    public static SigquitDetector getInstance(SigquitDetectorListener listener) {
        if (sInstance == null) {
            sInstance = new SigquitDetectorAcra(listener);
        }
        return sInstance;
    }

    private SigquitDetectorAcra(SigquitDetectorListener listener) {
        this.mListener = listener;
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    @Initializer
    public void init(ANRDetectorConfig anrConfig, boolean shouldUploadAnrReports) {
        this.mMainThreadHandler = anrConfig.getMainThreadHandler();
        BLog.w(TAG, "nativeInit");
        nativeInit(this, sIsArt, Build.VERSION.SDK_INT, anrConfig.getAppVersionCode(), anrConfig.getAppVersionName(), anrConfig.getTracesPath(), anrConfig.getTracesExtension(), anrConfig.processShouldSendAnrReports(), shouldUploadAnrReports, anrConfig.shouldReportSoftErrors(), anrConfig.shouldLogOnSignalHandler(), anrConfig.shouldAvoidMutexOnSignalHandler(), anrConfig.getSigquitTimeFilePath(), anrConfig.shouldRecordSignalTime());
    }

    @DoNotStrip
    private void sigquitDetected(String logData, String tracesFileName, boolean inForegroundV1, boolean inForegroundV2) {
        BLog.w(TAG, "sigquitDetected inFgV1: %b inFgV2: %b", Boolean.valueOf(inForegroundV1), Boolean.valueOf(inForegroundV2));
        this.mListener.sigquitDetected(logData, tracesFileName, inForegroundV1, inForegroundV2);
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
    public void installSignalHandler(final Handler bkgThreadHandler, final boolean shouldStart) {
        final Runnable hookDetectorRunnable = new Runnable() {
            /* class com.facebook.acra.anr.sigquit.SigquitDetectorAcra.AnonymousClass1 */

            public void run() {
                boolean hookSuccessful = SigquitDetectorAcra.nativeHookMethods();
                SigquitDetectorAcra.this.mListener.onHookedMethods(hookSuccessful);
                if (hookSuccessful && shouldStart) {
                    SigquitDetectorAcra.this.startDetector();
                }
            }
        };
        this.mMainThreadHandler.post(new Runnable() {
            /* class com.facebook.acra.anr.sigquit.SigquitDetectorAcra.AnonymousClass2 */

            public void run() {
                SigquitDetectorAcra.nativeAddSignalHandler();
                bkgThreadHandler.post(hookDetectorRunnable);
            }
        });
    }
}
