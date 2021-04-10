package oculus.internal;

import android.os.ServiceManager;
import android.os.storage.DiskInfo;
import android.os.storage.IStorageEventListener;
import android.os.storage.IStorageManager;
import android.os.storage.VolumeInfo;
import android.os.storage.VolumeRecord;
import android.util.Log;
import oculus.internal.StorageListenerInterface;

class StorageListener extends IStorageEventListener.Stub implements StorageListenerInterface {
    private static final String TAG = "StorageListener";
    private final StorageListenerInterface.Callback _callback;
    private final IStorageManager _mount;

    @Override // oculus.internal.StorageListenerInterface
    public void unregister() {
        try {
            this._mount.registerListener(this);
        } catch (Exception ex) {
            Log.wtf(TAG, "Failed to unregister from mount service", ex);
        }
    }

    public void onUsbMassStorageConnectionChanged(boolean connected) {
        this._callback.onStorageChange();
    }

    public void onStorageStateChanged(String path, String oldState, String newState) {
        this._callback.onStorageChange();
    }

    public void onVolumeStateChanged(VolumeInfo vol, int oldState, int newState) {
        this._callback.onStorageChange();
    }

    public void onVolumeRecordChanged(VolumeRecord rec) {
        this._callback.onStorageChange();
    }

    public void onVolumeForgotten(String fsUuid) {
        this._callback.onStorageChange();
    }

    public void onDiskScanned(DiskInfo disk, int volumeCount) {
        this._callback.onStorageChange();
    }

    public void onDiskDestroyed(DiskInfo disk) {
        this._callback.onStorageChange();
    }

    public static StorageListenerInterface create(StorageListenerInterface.Callback callback) {
        IStorageManager mount = IStorageManager.Stub.asInterface(ServiceManager.getService("mount"));
        StorageListener sl = new StorageListener(mount, callback);
        try {
            mount.registerListener(sl);
            return sl;
        } catch (Exception ex) {
            Log.e(TAG, "Failed to register mount listener", ex);
            return new StorageListenerInterface.Null();
        }
    }

    private StorageListener(IStorageManager mount, StorageListenerInterface.Callback callback) {
        this._mount = mount;
        this._callback = callback;
    }
}
