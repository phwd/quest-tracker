package com.oculus.os.enterprise;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import com.oculus.common.build.BuildConstants;
import com.oculus.os.SettingsManager;
import com.oculus.os.enterprise.internal.AppInstallInfo;
import com.oculus.os.enterprise.internal.BindServiceWrapper;
import com.oculus.os.enterprise.internal.Configuration;
import com.oculus.os.enterprise.internal.IAppInstallStatusChangeCallback;
import com.oculus.os.enterprise.internal.IEnterpriseServer;
import com.oculus.os.enterprise.internal.Version;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class EnterpriseServer {
    private static final String ENTERPRISE_SERVER_PACKAGE = "com.oculus.alpenglow";
    private static final String ENTERPRISE_SERVER_SERVICE = "com.oculus.alpenglow.enterpriseserver.EnterpriseServer";
    private static Handler mHandler;
    private static HandlerThread mHandlerThread;
    private final Object mConnectionLock = new Object();
    private final Context mContext;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        /* class com.oculus.os.enterprise.EnterpriseServer.AnonymousClass1 */

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            EnterpriseServer.this.mServiceImpl = IEnterpriseServer.Stub.asInterface(iBinder);
            synchronized (EnterpriseServer.this.mConnectionLock) {
                EnterpriseServer.this.mConnectionLock.notifyAll();
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            EnterpriseServer.this.mServiceImpl = null;
        }
    };
    private volatile IEnterpriseServer mServiceImpl;

    private EnterpriseServer(Context context) {
        this.mContext = context;
    }

    private void ensureBinderConnection() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (mHandler == null) {
            mHandlerThread = new HandlerThread("EnterpriseServerConnection");
            mHandlerThread.start();
            mHandler = new Handler(mHandlerThread.getLooper());
        }
        if (this.mServiceImpl == null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.oculus.alpenglow", ENTERPRISE_SERVER_SERVICE));
            synchronized (this.mConnectionLock) {
                BindServiceWrapper.bindServiceWithHandler(this.mContext, intent, this.mServiceConnection, 1, mHandler);
                try {
                    this.mConnectionLock.wait(30000);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    private void releaseBinderConnection() {
        if (this.mServiceImpl != null) {
            this.mContext.unbindService(this.mServiceConnection);
        }
    }

    private static boolean isStandaloneHmd(Context context) {
        return context.getPackageManager().hasSystemFeature(BuildConstants.PACKAGE_NAME_OVR_STANDALONE);
    }

    public static boolean isInEnterpriseMode(Context context) {
        if (isStandaloneHmd(context) && new SettingsManager().getInt(SettingsManager.MANAGED_DEVICE, 0) > 1) {
            return true;
        }
        return false;
    }

    public static Configuration getConfiguration(Context context) {
        if (isStandaloneHmd(context)) {
            EnterpriseServer enterpriseServer = new EnterpriseServer(context);
            try {
                enterpriseServer.ensureBinderConnection();
                if (enterpriseServer.mServiceImpl == null) {
                    Configuration buildEmptyConfiguration = buildEmptyConfiguration();
                    enterpriseServer.releaseBinderConnection();
                    return buildEmptyConfiguration;
                }
                Configuration configurationWithVersion = enterpriseServer.mServiceImpl.getConfigurationWithVersion(Version.latest());
                if (configurationWithVersion == null) {
                    Configuration buildEmptyConfiguration2 = buildEmptyConfiguration();
                    enterpriseServer.releaseBinderConnection();
                    return buildEmptyConfiguration2;
                }
                enterpriseServer.releaseBinderConnection();
                return configurationWithVersion;
            } catch (RemoteException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                enterpriseServer.releaseBinderConnection();
                throw th;
            }
        } else {
            throw new UnsupportedOperationException("Enterprise features not supported on this device");
        }
    }

    public static AppInstallInfo getAppInstallInfo(Context context, String str, String str2) {
        EnterpriseServer enterpriseServer = new EnterpriseServer(context);
        try {
            enterpriseServer.ensureBinderConnection();
            if (enterpriseServer.mServiceImpl == null) {
                enterpriseServer.releaseBinderConnection();
                return null;
            }
            AppInstallInfo appInstallInfo = enterpriseServer.mServiceImpl.getAppInstallInfo(str, str2);
            enterpriseServer.releaseBinderConnection();
            return appInstallInfo;
        } catch (RemoteException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            enterpriseServer.releaseBinderConnection();
            throw th;
        }
    }

    public static Map getAppInstallInfos(Context context) {
        EnterpriseServer enterpriseServer = new EnterpriseServer(context);
        try {
            enterpriseServer.ensureBinderConnection();
            if (enterpriseServer.mServiceImpl == null) {
                enterpriseServer.releaseBinderConnection();
                return null;
            }
            Map appInstallInfos = enterpriseServer.mServiceImpl.getAppInstallInfos();
            enterpriseServer.releaseBinderConnection();
            return appInstallInfos;
        } catch (RemoteException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            enterpriseServer.releaseBinderConnection();
            throw th;
        }
    }

    public static AppInstallInfo registerCallback(Context context, String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) {
        EnterpriseServer enterpriseServer = new EnterpriseServer(context);
        try {
            enterpriseServer.ensureBinderConnection();
            if (enterpriseServer.mServiceImpl == null) {
                enterpriseServer.releaseBinderConnection();
                return null;
            }
            AppInstallInfo registerCallback = enterpriseServer.mServiceImpl.registerCallback(str, iAppInstallStatusChangeCallback);
            enterpriseServer.releaseBinderConnection();
            return registerCallback;
        } catch (RemoteException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            enterpriseServer.releaseBinderConnection();
            throw th;
        }
    }

    public static Map registerCallbackForAllApps(Context context, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) {
        EnterpriseServer enterpriseServer = new EnterpriseServer(context);
        try {
            enterpriseServer.ensureBinderConnection();
            if (enterpriseServer.mServiceImpl == null) {
                enterpriseServer.releaseBinderConnection();
                return null;
            }
            Map registerCallbackForAllApps = enterpriseServer.mServiceImpl.registerCallbackForAllApps(iAppInstallStatusChangeCallback);
            enterpriseServer.releaseBinderConnection();
            return registerCallbackForAllApps;
        } catch (RemoteException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            enterpriseServer.releaseBinderConnection();
            throw th;
        }
    }

    public static void unregisterCallback(Context context, String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) {
        EnterpriseServer enterpriseServer = new EnterpriseServer(context);
        try {
            enterpriseServer.ensureBinderConnection();
            if (enterpriseServer.mServiceImpl != null) {
                enterpriseServer.mServiceImpl.unregisterCallback(str, iAppInstallStatusChangeCallback);
            }
            enterpriseServer.releaseBinderConnection();
        } catch (RemoteException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            enterpriseServer.releaseBinderConnection();
            throw th;
        }
    }

    private static Configuration buildEmptyConfiguration() {
        return new Configuration() {
            /* class com.oculus.os.enterprise.EnterpriseServer.AnonymousClass2 */
        };
    }
}
