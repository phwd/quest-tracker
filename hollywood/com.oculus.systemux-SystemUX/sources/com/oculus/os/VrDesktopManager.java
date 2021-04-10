package com.oculus.os;

import android.content.Intent;
import android.os.IBinder;
import android.view.Surface;
import com.oculus.vrdesktop.IContainer;
import com.oculus.vrdesktop.IVrDesktopService;

public class VrDesktopManager {
    public VrDesktopManager(IVrDesktopService iVrDesktopService) {
        throw new RuntimeException("Stub!");
    }

    public static VrDesktopManager getInstance() {
        throw new RuntimeException("Stub!");
    }

    public IContainer startActivity(IBinder iBinder, Surface surface, int i, int i2, int i3, Intent intent) {
        throw new RuntimeException("Stub!");
    }
}
