package com.facebook.acra.anr.sigquit;

import X.C0139Dd;
import android.os.Build;
import android.os.Handler;
import com.facebook.acra.anr.ANRDetectorConfig;

public class SigquitDetectorAcra implements SigquitDetector {
    public static final String TAG = "SigquitDetectorAcra";
    public static SigquitDetector sInstance;
    public static boolean sIsArt;
    public SigquitDetectorListener mListener;
    public Handler mMainThreadHandler;

    public static native void nativeAddSignalHandler();

    public static native void nativeCleanupAppStateFile();

    public static native boolean nativeHookMethods();

    public static native void nativeInit(Object obj, boolean z, int i, String str, String str2, String str3, String str4, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str5, boolean z7);

    public static native void nativeStartDetector();

    public static native void nativeStopDetector();

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0017, code lost:
        if (r1.startsWith("0.") != false) goto L_0x0019;
     */
    static {
        /*
            java.lang.String r0 = "java.vm.version"
            java.lang.String r1 = java.lang.System.getProperty(r0)
            if (r1 == 0) goto L_0x0019
            java.lang.String r0 = "1."
            boolean r0 = r1.startsWith(r0)
            if (r0 != 0) goto L_0x0019
            java.lang.String r0 = "0."
            boolean r1 = r1.startsWith(r0)
            r0 = 1
            if (r1 == 0) goto L_0x001a
        L_0x0019:
            r0 = 0
        L_0x001a:
            com.facebook.acra.anr.sigquit.SigquitDetectorAcra.sIsArt = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.anr.sigquit.SigquitDetectorAcra.<clinit>():void");
    }

    public static SigquitDetector getInstance(SigquitDetectorListener sigquitDetectorListener) {
        SigquitDetector sigquitDetector = sInstance;
        if (sigquitDetector != null) {
            return sigquitDetector;
        }
        SigquitDetectorAcra sigquitDetectorAcra = new SigquitDetectorAcra(sigquitDetectorListener);
        sInstance = sigquitDetectorAcra;
        return sigquitDetectorAcra;
    }

    private void sigquitDetected(String str, String str2, boolean z, boolean z2) {
        C0139Dd.A0P(TAG, "sigquitDetected inFgV1: %b inFgV2: %b", Boolean.valueOf(z), Boolean.valueOf(z2));
        this.mListener.sigquitDetected(str, str2, z, z2);
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    public void init(ANRDetectorConfig aNRDetectorConfig, boolean z) {
        this.mMainThreadHandler = aNRDetectorConfig.mMainThreadHandler;
        C0139Dd.A0D(TAG, "nativeInit");
        nativeInit(this, sIsArt, Build.VERSION.SDK_INT, aNRDetectorConfig.mAppVersionCode, aNRDetectorConfig.mAppVersionName, aNRDetectorConfig.mTracesPath, aNRDetectorConfig.mTracesExtension, aNRDetectorConfig.processShouldSendAnrReports(), z, aNRDetectorConfig.mShouldReportSoftErrors, aNRDetectorConfig.mShouldLogOnSignalHandler, aNRDetectorConfig.mShouldAvoidMutexOnSignalHandler, aNRDetectorConfig.getSigquitTimeFilePath(), aNRDetectorConfig.mShouldRecordSignalTime);
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    public void installSignalHandler(final Handler handler, final boolean z) {
        final AnonymousClass1 r2 = new Runnable() {
            /* class com.facebook.acra.anr.sigquit.SigquitDetectorAcra.AnonymousClass1 */

            public void run() {
                boolean nativeHookMethods = SigquitDetectorAcra.nativeHookMethods();
                SigquitDetectorAcra.this.mListener.onHookedMethods(nativeHookMethods);
                if (nativeHookMethods && z) {
                    SigquitDetectorAcra.this.startDetector();
                }
            }
        };
        this.mMainThreadHandler.post(new Runnable() {
            /* class com.facebook.acra.anr.sigquit.SigquitDetectorAcra.AnonymousClass2 */

            public void run() {
                SigquitDetectorAcra.nativeAddSignalHandler();
                handler.post(r2);
            }
        });
    }

    public SigquitDetectorAcra(SigquitDetectorListener sigquitDetectorListener) {
        this.mListener = sigquitDetectorListener;
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    public void cleanupAppStateFile() {
        nativeCleanupAppStateFile();
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    public void startDetector() {
        nativeStartDetector();
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    public void stopDetector() {
        nativeStopDetector();
    }
}
