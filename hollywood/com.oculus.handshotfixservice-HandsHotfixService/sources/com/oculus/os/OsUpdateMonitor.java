package com.oculus.os;

import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import oculus.internal.Constants;
import oculus.internal.IOsUpdateMonitor;

public class OsUpdateMonitor {
    private final Context mContext;
    private final IOsUpdateMonitor mService = IOsUpdateMonitor.Stub.asInterface(ServiceManager.getService(Constants.OS_UPDATE_MONITOR_SERVICE));

    public OsUpdateMonitor(Context context) {
        this.mContext = context;
    }

    public int getUpdateEngineState() throws RemoteException {
        return this.mService.getUpdateEngineState();
    }

    public int getUpdateEngineErrorCode() throws RemoteException {
        return this.mService.getUpdateEngineErrorCode();
    }

    public void applyPayload(String url, long offset, long size, String[] headerKeyValuePairs) throws RemoteException {
        this.mService.applyPayload(url, offset, size, headerKeyValuePairs);
    }

    public void cancel() throws RemoteException {
        this.mService.cancel();
    }

    public void suspend() throws RemoteException {
        this.mService.suspend();
    }

    public void resume() throws RemoteException {
        this.mService.resume();
    }

    public void resetStatus() throws RemoteException {
        this.mService.resetStatus();
    }
}
