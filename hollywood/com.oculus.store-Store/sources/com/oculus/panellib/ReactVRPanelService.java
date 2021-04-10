package com.oculus.panellib;

import android.os.Handler;
import android.view.Surface;
import com.oculus.vrshell.panel.SingletonPanelService;

public class ReactVRPanelService extends SingletonPanelService {
    private static ReactVRPanelService Instance = null;
    private static final String TAG = "ReactVRPanelService";
    protected String mFbAppId;
    protected String mFbAppName;
    protected String mJsBundleName;
    protected Handler mMainHandler;

    protected static final native void nativeOnTrimMemory(int i);

    public ReactVRPanelService(String serviceName, String fbAppId, String fbAppName, String jsBundleName) {
        super(serviceName);
        this.mFbAppId = fbAppId;
        this.mFbAppName = fbAppName;
        this.mJsBundleName = jsBundleName;
        Instance = this;
        this.mMainHandler = new Handler();
    }

    public ReactVRPanelService(String serviceName, String fbAppId, String fbAppName) {
        this(serviceName, fbAppId, fbAppName, "");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007c, code lost:
        r8 = r6;
        r6 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008a, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008e, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b2, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b3, code lost:
        r7 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008a A[ExcHandler: all (th java.lang.Throwable)] */
    @Override // com.oculus.vrshell.panel.PanelService, com.oculus.vrshell.panel.SingletonPanelService
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate() {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.ReactVRPanelService.onCreate():void");
    }

    public void onTrimMemory(int level) {
        nativeOnTrimMemory(level);
    }

    @Override // com.oculus.vrshell.panel.PanelService
    public void onDestroy() {
        CrashReporting.shutdown();
        ReactVRApplication.onServiceDestroy();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panel.PanelService, com.oculus.vrshell.panel.SingletonPanelService
    public void createPanelApp(int fd, Surface[] surfaces, String[] surfaceNames, String[] env) {
        PanelEnvironment.onPanelEnvironment(env);
        super.createPanelApp(fd, surfaces, surfaceNames, env);
    }

    /* access modifiers changed from: protected */
    public void createService() {
    }

    public static ReactVRPanelService getInstance() {
        return Instance;
    }
}
