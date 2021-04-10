package com.oculus.vrshell.panel;

import android.util.Log;
import android.view.Surface;

public class MultiInstancePanelService extends PanelService {
    private static final String TAG = "MultiInstancePanelServi";
    private final String nativeLibraryName;

    private native void nativeCreatePanelApp(int i, Surface[] surfaceArr, String[] strArr, String[] strArr2);

    private native void nativeLoadPanelAppLibrary(String str);

    static {
        System.loadLibrary("reactpanelservice");
    }

    public MultiInstancePanelService(String nativeLibraryName2) {
        this.nativeLibraryName = nativeLibraryName2;
    }

    public MultiInstancePanelService() {
        this(null);
    }

    @Override // com.oculus.vrshell.panel.PanelService
    public void onCreate() {
        super.onCreate();
        String libraryName = this.nativeLibraryName == null ? getDefaultLibraryName() : this.nativeLibraryName;
        Log.i(TAG, String.format("Loading library %s", libraryName));
        nativeLoadPanelAppLibrary(libraryName);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panel.PanelService
    public void createPanelApp(int fd, Surface[] surfaces, String[] surfaceNames, String[] env) {
        nativeCreatePanelApp(fd, surfaces, surfaceNames, env);
    }
}
