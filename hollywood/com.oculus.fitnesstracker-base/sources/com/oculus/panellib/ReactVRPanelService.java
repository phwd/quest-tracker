package com.oculus.panellib;

import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import com.oculus.common.build.BuildConfig;

public class ReactVRPanelService extends CPanelService {
    private static ReactVRPanelService Instance = null;
    private static final String TAG = "ReactVRPanelService";
    protected String mFbAppId;
    protected String mFbAppName;
    protected String mJsBundleName;
    protected Handler mMainHandler;

    protected static final native void nativeOnTrimMemory(int i);

    /* access modifiers changed from: protected */
    public void createService() {
    }

    public ReactVRPanelService(String str, String str2, String str3) {
        this.mFbAppId = str;
        this.mFbAppName = str2;
        this.mJsBundleName = str3;
        Instance = this;
        this.mMainHandler = new Handler();
    }

    public ReactVRPanelService(String str, String str2) {
        this(str, str2, BuildConfig.PROVIDER_SUFFIX);
    }

    @Override // com.oculus.panellib.CPanelService
    public final void onCreate() {
        QplPointRange qplPointRange = new QplPointRange(Qpl.QPL_MARKER_INIT, "service_create");
        try {
            ReactVRApplication.initPanelApp();
            long currentTimeMillis = System.currentTimeMillis();
            super.onCreate();
            if (!this.mFbAppId.isEmpty() && !this.mFbAppName.isEmpty()) {
                CrashReporting.initialize(getApplicationContext(), this.mMainHandler, this.mJsBundleName, this.mFbAppId, this.mFbAppName);
            }
            SystraceBlock systraceBlock = new SystraceBlock(TAG, "createService");
            try {
                createService();
                systraceBlock.close();
                Log.d("PerfMarker", "Service onCreate() took " + (System.currentTimeMillis() - currentTimeMillis) + " msec.");
                qplPointRange.close();
                return;
            } catch (Throwable unused) {
            }
            throw th;
            throw th;
        } catch (Throwable unused2) {
        }
    }

    public void onTrimMemory(int i) {
        nativeOnTrimMemory(i);
    }

    @Override // com.oculus.panellib.CPanelService
    public void onDestroy() {
        CrashReporting.shutdown();
        ReactVRApplication.onServiceDestroy();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.CPanelService
    public void createPanelApp(int i, Surface[] surfaceArr, String[] strArr, String[] strArr2) {
        PanelEnvironment.onPanelEnvironment(strArr2);
        super.createPanelApp(i, surfaceArr, strArr, strArr2);
    }

    public static ReactVRPanelService getInstance() {
        return Instance;
    }
}
