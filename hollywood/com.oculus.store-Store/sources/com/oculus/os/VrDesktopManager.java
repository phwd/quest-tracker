package com.oculus.os;

import android.content.Intent;
import android.os.IBinder;
import android.view.Surface;
import com.oculus.vrdesktop.IContainer;
import com.oculus.vrdesktop.IVrDesktopService;

public class VrDesktopManager {
    public VrDesktopManager(IVrDesktopService service) {
        throw new RuntimeException("Stub!");
    }

    public static VrDesktopManager getInstance() {
        throw new RuntimeException("Stub!");
    }

    public IContainer startActivity(IBinder parentActivityToken, Surface surface, int width, int height, int density, Intent intent) {
        throw new RuntimeException("Stub!");
    }
}
