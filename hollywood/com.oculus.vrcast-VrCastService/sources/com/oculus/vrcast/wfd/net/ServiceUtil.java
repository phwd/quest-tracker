package com.oculus.vrcast.wfd.net;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import com.qualcomm.wfd.service.ISessionManagerService;

public class ServiceUtil {
    private static final String TAG = "ServiceUtil";
    protected static ServiceConnection mConnection = new ServiceConnection() {
        /* class com.oculus.vrcast.wfd.net.ServiceUtil.AnonymousClass1 */

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(ServiceUtil.TAG, "Connection object created");
            boolean unused = ServiceUtil.mServiceAlreadyBound = true;
            ISessionManagerService unused2 = ServiceUtil.mUniqueInstance = ISessionManagerService.Stub.asInterface(iBinder);
            synchronized (ServiceUtil.class) {
                ServiceUtil.class.notifyAll();
            }
            ServiceUtil.mEventHandler.sendMessage(ServiceUtil.mEventHandler.obtainMessage(7));
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(ServiceUtil.TAG, "Remote service disconnected");
            boolean unused = ServiceUtil.mServiceAlreadyBound = false;
            ISessionManagerService unused2 = ServiceUtil.mUniqueInstance = null;
        }
    };
    private static Handler mEventHandler = null;
    private static boolean mServiceAlreadyBound = false;
    private static ISessionManagerService mUniqueInstance;

    public static boolean getServiceAlreadyBound() {
        return mServiceAlreadyBound;
    }

    public static void bindService(Context context, Handler handler) throws ServiceFailedToBindException {
        if (!mServiceAlreadyBound || mUniqueInstance == null) {
            Log.d(TAG, "bindService- !mServiceAlreadyBound  || mUniqueInstance == null");
            Intent intent = new Intent("com.qualcomm.wfd.service.WfdService");
            intent.setPackage("com.qualcomm.wfd.service");
            mEventHandler = handler;
            if (!context.bindService(intent, mConnection, 1)) {
                Log.e(TAG, "Failed to connect to Provider service");
                throw new ServiceFailedToBindException("Failed to connect to Provider service");
            }
        }
    }

    public static void unbindService(Context context) {
        if (mServiceAlreadyBound) {
            context.unbindService(mConnection);
            mServiceAlreadyBound = false;
            mUniqueInstance = null;
        }
    }

    public static synchronized ISessionManagerService getInstance() {
        ISessionManagerService iSessionManagerService;
        synchronized (ServiceUtil.class) {
            while (mUniqueInstance == null) {
                Log.d(TAG, "Waiting for service to bind ...");
                try {
                    ServiceUtil.class.wait();
                } catch (InterruptedException e) {
                    Log.e(TAG, "InterruptedException: " + e);
                }
            }
            iSessionManagerService = mUniqueInstance;
        }
        return iSessionManagerService;
    }

    public static class ServiceFailedToBindException extends Exception {
        public static final long serialVersionUID = 1;

        private ServiceFailedToBindException(String str) {
            super(str);
        }
    }
}
