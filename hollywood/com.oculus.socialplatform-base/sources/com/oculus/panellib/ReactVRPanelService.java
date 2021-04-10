package com.oculus.panellib;

import android.os.Handler;
import android.view.Surface;
import com.oculus.crashreporter.JavascriptCrashReporter;

public class ReactVRPanelService extends CPanelService {
    public static ReactVRPanelService Instance = null;
    public static final String TAG = "ReactVRPanelService";
    public String mFbAppId;
    public String mFbAppName;
    public String mJsBundleName;
    public Handler mMainHandler;

    public static final native void nativeOnTrimMemory(int i);

    public void createService() {
    }

    @Override // com.oculus.panellib.CPanelService
    public void onDestroy() {
        JavascriptCrashReporter.mHandler = null;
        super.onDestroy();
    }

    public static ReactVRPanelService getInstance() {
        return Instance;
    }

    @Override // com.oculus.panellib.CPanelService
    public void createPanelApp(int i, Surface[] surfaceArr, String[] strArr, String[] strArr2) {
        PanelEnvironment.onPanelEnvironment(strArr2);
        super.createPanelApp(i, surfaceArr, strArr, strArr2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0055, code lost:
        throw r0;
     */
    @Override // com.oculus.panellib.CPanelService
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate() {
        /*
            r6 = this;
            r1 = 51707905(0x3150001, float:4.378717E-37)
            java.lang.String r0 = "service_create"
            com.oculus.panellib.QplPointRange r5 = new com.oculus.panellib.QplPointRange
            r5.<init>(r1, r0)
            com.oculus.panellib.ReactVRApplication.initPanelApp()     // Catch:{ all -> 0x004f }
            java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x004f }
            super.onCreate()     // Catch:{ all -> 0x004f }
            java.lang.String r0 = r6.mFbAppId     // Catch:{ all -> 0x004f }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x004f }
            if (r0 != 0) goto L_0x0032
            java.lang.String r0 = r6.mFbAppName     // Catch:{ all -> 0x004f }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x004f }
            if (r0 != 0) goto L_0x0032
            android.content.Context r4 = r6.getApplicationContext()     // Catch:{ all -> 0x004f }
            android.os.Handler r3 = r6.mMainHandler     // Catch:{ all -> 0x004f }
            java.lang.String r2 = r6.mJsBundleName     // Catch:{ all -> 0x004f }
            java.lang.String r1 = r6.mFbAppId     // Catch:{ all -> 0x004f }
            java.lang.String r0 = r6.mFbAppName     // Catch:{ all -> 0x004f }
            com.oculus.panellib.CrashReporting.initialize(r4, r3, r2, r1, r0)     // Catch:{ all -> 0x004f }
        L_0x0032:
            java.lang.String r2 = "ReactVRPanelService"
            java.lang.String r0 = "createService"
            com.oculus.panellib.SystraceBlock r1 = new com.oculus.panellib.SystraceBlock     // Catch:{ all -> 0x004f }
            r1.<init>(r2, r0)     // Catch:{ all -> 0x004f }
            r6.createService()     // Catch:{ all -> 0x0048 }
            r1.close()
            java.lang.System.currentTimeMillis()
            r5.close()
            return
        L_0x0048:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x004a }
        L_0x004a:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x004e }
        L_0x004e:
            throw r0
        L_0x004f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r0 = move-exception
            r5.close()     // Catch:{ all -> 0x0055 }
        L_0x0055:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.ReactVRPanelService.onCreate():void");
    }

    public void onTrimMemory(int i) {
        nativeOnTrimMemory(i);
    }

    public ReactVRPanelService(String str, String str2) {
        this(str, str2, "");
    }

    public ReactVRPanelService(String str, String str2, String str3) {
        this.mFbAppId = str;
        this.mFbAppName = str2;
        this.mJsBundleName = str3;
        Instance = this;
        this.mMainHandler = new Handler();
    }
}
