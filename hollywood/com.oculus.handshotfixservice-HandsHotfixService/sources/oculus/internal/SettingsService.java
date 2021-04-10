package oculus.internal;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import oculus.internal.ISettingsService;

public class SettingsService implements IBinder.DeathRecipient {
    private static final String SETTINGS_SERVICE = "SettingsService";
    private static final String TAG = SettingsService.class.getSimpleName();
    private static SettingsService sInstance;
    private ISettingsService mService;

    private SettingsService() {
        ensureServiceConnected();
    }

    private void ensureServiceConnected() {
        if (this.mService == null) {
            IBinder b = ServiceManager.getService(SETTINGS_SERVICE);
            this.mService = ISettingsService.Stub.asInterface(b);
            if (this.mService == null) {
                Log.wtf(TAG, "Failed to get SettingsService");
                return;
            }
            try {
                b.linkToDeath(this, 0);
            } catch (RemoteException e) {
                Log.e(TAG, "linkToDeath failed", e);
            }
        }
    }

    public static synchronized SettingsService getInstance() {
        SettingsService settingsService;
        synchronized (SettingsService.class) {
            if (sInstance == null) {
                sInstance = new SettingsService();
            }
            settingsService = sInstance;
        }
        return settingsService;
    }

    public void binderDied() {
        Log.d(TAG, "Remote service died, resetting mService to null");
        this.mService = null;
    }

    public String getSetting(String name, String defaultValue) {
        ensureServiceConnected();
        try {
            String returnValue = this.mService.getSetting(name);
            return returnValue != null ? returnValue : defaultValue;
        } catch (RemoteException e) {
            Log.e(TAG, "Exception while calling SettingsService getSetting method: ", e);
            return defaultValue;
        }
    }

    public String getUserSetting(String name, String defaultValue, int userId) {
        ensureServiceConnected();
        try {
            String returnValue = this.mService.getUserSetting(name, userId);
            return returnValue != null ? returnValue : defaultValue;
        } catch (RemoteException e) {
            Log.e(TAG, "Exception while calling SettingsService getUserSetting method: ", e);
            return defaultValue;
        }
    }

    public boolean setSetting(String name, String value) {
        if (value == null) {
            return false;
        }
        ensureServiceConnected();
        try {
            return this.mService.setSetting(name, value);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception while calling SettingsService setSetting method: ", e);
            return false;
        }
    }

    public boolean setUserSetting(String name, String value, int userId) {
        if (value == null) {
            return false;
        }
        ensureServiceConnected();
        try {
            return this.mService.setUserSetting(name, value, userId);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception while calling SettingsService setUserSetting method: ", e);
            return false;
        }
    }

    public void registerSettingsObserver(String name, ISettingsObserver observer) {
        ensureServiceConnected();
        try {
            this.mService.registerSettingsObserver(name, observer);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception while calling SettingsService registerSettingsObserver method: ", e);
        }
    }

    public void unregisterSettingsObserver(ISettingsObserver observer) {
        ensureServiceConnected();
        try {
            this.mService.unregisterSettingsObserver(observer);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception while calling SettingsService unregisterSettingsObserver method: ", e);
        }
    }

    public void createUserDatabase(int userId) {
        ensureServiceConnected();
        try {
            this.mService.createUserDatabase(userId);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception while calling SettingsService createUserDatabase method: ", e);
        }
    }

    public void deleteUserDatabase(int userId) {
        ensureServiceConnected();
        try {
            this.mService.deleteUserDatabase(userId);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception while calling SettingsService deleteUserDatabase method: ", e);
        }
    }
}
