package com.oculus.os;

import android.content.Intent;
import android.os.IBinder;
import android.os.ServiceManager;
import android.util.Log;
import android.view.Surface;
import com.android.internal.annotations.VisibleForTesting;
import com.oculus.vrdesktop.IContainer;
import com.oculus.vrdesktop.IVrDesktopService;

public class VrDesktopManager {
    private static final String TAG = "VrDesktopManager";
    private static final String VRDESKTOP_SERVICE_NAME = "vrdesktop";
    private static VrDesktopManager sInstance;
    private IVrDesktopService mService;

    public static VrDesktopManager getInstance() {
        if (sInstance == null) {
            sInstance = new VrDesktopManager();
        }
        return sInstance;
    }

    private VrDesktopManager() {
        ensureServiceConnected();
    }

    @VisibleForTesting
    public VrDesktopManager(IVrDesktopService service) {
        this.mService = service;
    }

    private void ensureServiceConnected() {
        if (this.mService == null) {
            this.mService = IVrDesktopService.Stub.asInterface(ServiceManager.getService(VRDESKTOP_SERVICE_NAME));
            if (this.mService == null) {
                Log.wtf(TAG, "Failed to get VrDesktop service");
            }
        }
    }

    public IContainer startActivity(IBinder parentActivityToken, Surface surface, int width, int height, int density, Intent intent) {
        ensureServiceConnected();
        try {
            return this.mService.startActivity(parentActivityToken, surface, width, height, density, intent);
        } catch (Exception e) {
            Log.e(TAG, "startActivity: encountered exception", e);
            return null;
        }
    }
}
