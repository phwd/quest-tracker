package com.oculus.os;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import oculus.internal.ITrackingEnvironment;

public class MapManager {
    private static final boolean DEBUG = false;
    private static final String TAG = "MapManagerJava";
    private IBinder binder;
    private ITrackingEnvironment trackingEnvService;

    public long readMap(ParcelFileDescriptor pipeWriteEnd, String mapId) {
        ITrackingEnvironment service = getService();
        if (service == null) {
            return 0;
        }
        try {
            return service.readMap(pipeWriteEnd, mapId);
        } catch (RemoteException e) {
            return 0;
        }
    }

    public boolean writeMap(ParcelFileDescriptor pipeReadEnd, String mapId, long fileSize) {
        ITrackingEnvironment service = getService();
        if (service == null) {
            return DEBUG;
        }
        try {
            return service.writeMap(pipeReadEnd, mapId, fileSize);
        } catch (RemoteException e) {
            return DEBUG;
        }
    }

    public String[] listMaps() {
        ITrackingEnvironment service = getService();
        if (service == null) {
            return null;
        }
        try {
            return service.listMaps();
        } catch (RemoteException e) {
            return null;
        }
    }

    public String getCurrentMapUUID() {
        ITrackingEnvironment service = getService();
        if (service == null) {
            return null;
        }
        try {
            return service.getCurrentMapUUID();
        } catch (RemoteException e) {
            return null;
        }
    }

    private boolean ensureConnection() {
        IBinder iBinder = this.binder;
        if (iBinder == null || !iBinder.pingBinder() || !this.binder.isBinderAlive()) {
            return connect();
        }
        return true;
    }

    private boolean connect() {
        this.binder = ServiceManager.checkService("TrackingEnvironment");
        IBinder iBinder = this.binder;
        if (iBinder == null) {
            this.trackingEnvService = null;
            return DEBUG;
        }
        this.trackingEnvService = ITrackingEnvironment.Stub.asInterface(iBinder);
        return true;
    }

    private synchronized ITrackingEnvironment getService() {
        if (!ensureConnection()) {
            return null;
        }
        return this.trackingEnvService;
    }
}
