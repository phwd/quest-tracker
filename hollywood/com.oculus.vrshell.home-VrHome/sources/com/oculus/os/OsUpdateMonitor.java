package com.oculus.os;

import android.content.Context;
import android.os.RemoteException;

public class OsUpdateMonitor {
    public OsUpdateMonitor(Context context) {
        throw new RuntimeException("Stub!");
    }

    public int getUpdateEngineState() throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public int getUpdateEngineErrorCode() throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public void applyPayload(String url, long offset, long size, String[] headerKeyValuePairs) throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public void cancel() throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public void suspend() throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public void resume() throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public void resetStatus() throws RemoteException {
        throw new RuntimeException("Stub!");
    }
}
