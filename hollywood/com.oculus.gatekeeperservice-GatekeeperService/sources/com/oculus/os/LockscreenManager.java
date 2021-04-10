package com.oculus.os;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import oculus.internal.Constants;
import oculus.internal.ILockscreen;

public class LockscreenManager {
    private static final String KEYGUARD_SERVICE = "keyguard";
    private final Context mContext;
    private final ILockscreen mService = ILockscreen.Stub.asInterface(ServiceManager.getService(Constants.LOCK_SCREEN_SERVICE));

    public LockscreenManager(Context context) {
        this.mContext = context;
    }

    public boolean canShowLockscreen() throws RemoteException {
        return currentUserHasLockPattern() || canShowUserSwitcher();
    }

    private boolean currentUserHasLockPattern() {
        return ((KeyguardManager) this.mContext.getSystemService(KEYGUARD_SERVICE)).isKeyguardSecure();
    }

    public void showLockscreen() throws RemoteException {
        this.mService.showLockscreen();
    }

    public boolean canShowUserSwitcher() throws RemoteException {
        return this.mService.canShowUserSwitcher();
    }

    public void showUserSwitcher() throws RemoteException {
        this.mService.showUserSwitcher();
    }

    public void showTooManyDevicesScreen() throws RemoteException {
        this.mService.showTooManyDevicesScreen();
    }
}
