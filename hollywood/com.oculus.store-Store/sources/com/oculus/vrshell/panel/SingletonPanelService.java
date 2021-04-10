package com.oculus.vrshell.panel;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;

public class SingletonPanelService extends PanelService implements IBinder.DeathRecipient {
    private static final String TAG = "SingletonPanelService";
    private final String nativeLibraryName;

    private native void nativeCreatePanelApp(int i, Surface[] surfaceArr, String[] strArr, String[] strArr2);

    private native void nativeDestroyPanelApp();

    private native void nativeLoadPanelAppLibrary(String str);

    static {
        System.loadLibrary("reactpanelservice");
    }

    public SingletonPanelService(String nativeLibraryName2) {
        this.nativeLibraryName = nativeLibraryName2;
    }

    public SingletonPanelService() {
        this(null);
    }

    @Override // com.oculus.vrshell.panel.PanelService
    public void onCreate() {
        super.onCreate();
        String libraryName = this.nativeLibraryName == null ? getDefaultLibraryName() : this.nativeLibraryName;
        Log.i(TAG, String.format("Loading library %s", libraryName));
        nativeLoadPanelAppLibrary(libraryName);
    }

    @Override // com.oculus.vrshell.panel.PanelService
    public IBinder onBind(Intent intent) {
        try {
            IBinder ib = super.onBind(intent);
            ib.linkToDeath(this, 0);
            return ib;
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to install DeathRecipient on Binder instance", e);
            return null;
        }
    }

    @Override // com.oculus.vrshell.panel.PanelService
    public boolean onUnbind(Intent intent) {
        super.onUnbind(intent);
        nativeDestroyPanelApp();
        return false;
    }

    public void binderDied() {
        Log.w(TAG, "Binder died; destroying panel app");
        nativeDestroyPanelApp();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panel.PanelService
    public void createPanelApp(int fd, Surface[] surfaces, String[] surfaceNames, String[] env) {
        nativeCreatePanelApp(fd, surfaces, surfaceNames, env);
    }
}
