package com.oculus.os;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import oculus.internal.Constants;
import oculus.internal.PermissionUtils;
import oculus.internal.dumpsysproxy.IDumpsysProxyClient;
import oculus.internal.dumpsysproxy.IDumpsysProxyService;

public abstract class DumpsysProxyClientService extends Service {
    private static final boolean DEBUG = false;
    public static final String DUMP_FILES_KEY = "dumpFileList";
    public static final String DUMP_STATE_KEY = "dumpState";
    private static final String TAG = DumpsysProxyClientService.class.getSimpleName();
    private final String mAppName = getAppName();

    public abstract String dumpState();

    public abstract String getAppName();

    public void onCreate() {
        IDumpsysProxyService service = IDumpsysProxyService.Stub.asInterface(ServiceManager.getService(Constants.DUMPSYS_PROXY_SERVICE));
        if (service == null) {
            Log.e(TAG, "DumpsysProxyService is not running! Has it been brought up on this device?");
            return;
        }
        try {
            service.registerClient(new ComponentName(this, getClass()), this.mAppName);
        } catch (RemoteException e) {
            String str = TAG;
            Log.e(str, "Failed to register " + this.mAppName, e);
        }
    }

    public IBinder onBind(Intent intent) {
        return new DumpsysProxyClientBinder();
    }

    public List<File> getDumpFiles() {
        return new ArrayList();
    }

    private class DumpsysProxyClientBinder extends IDumpsysProxyClient.Stub {
        private DumpsysProxyClientBinder() {
        }

        @Override // oculus.internal.dumpsysproxy.IDumpsysProxyClient
        public Bundle dumpState() {
            PermissionUtils.checkCallingPermission(DumpsysProxyClientService.TAG, DumpsysProxyClientService.this, "android.permission.DUMP");
            ArrayList<ParcelFileDescriptor> parcelFileDescriptors = new ArrayList<>();
            for (File file : DumpsysProxyClientService.this.getDumpFiles()) {
                try {
                    parcelFileDescriptors.add(ParcelFileDescriptor.open(file, 268435456));
                } catch (FileNotFoundException e) {
                    Log.e(DumpsysProxyClientService.TAG, "Cannot open dump file", e);
                }
            }
            Bundle bundle = new Bundle();
            bundle.putString(DumpsysProxyClientService.DUMP_STATE_KEY, DumpsysProxyClientService.this.dumpState());
            bundle.putParcelableArrayList(DumpsysProxyClientService.DUMP_FILES_KEY, parcelFileDescriptors);
            return bundle;
        }
    }
}
