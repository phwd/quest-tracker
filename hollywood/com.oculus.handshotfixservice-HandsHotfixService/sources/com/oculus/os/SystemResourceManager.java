package com.oculus.os;

import android.os.ServiceManager;
import android.util.Log;
import oculus.internal.Constants;
import oculus.internal.ISystemResource;

public final class SystemResourceManager {
    private static final String TAG = SystemResourceManager.class.getSimpleName();
    private static ISystemResource sService;

    private SystemResourceManager() {
    }

    public static float getMemoryPressure() {
        try {
            ISystemResource service = getService();
            if (service != null) {
                return service.getMemoryPressure();
            }
            return -1.0f;
        } catch (Exception e) {
            Log.e(TAG, "Could not get memory pressure", e);
            return -1.0f;
        }
    }

    private static synchronized ISystemResource getService() {
        ISystemResource iSystemResource;
        synchronized (SystemResourceManager.class) {
            if (sService == null) {
                sService = ISystemResource.Stub.asInterface(ServiceManager.getService(Constants.SYSTEM_RESOURCE_SERVICE));
            }
            iSystemResource = sService;
        }
        return iSystemResource;
    }
}
