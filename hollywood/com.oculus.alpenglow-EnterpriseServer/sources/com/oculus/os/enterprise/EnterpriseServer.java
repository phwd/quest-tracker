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
import com.oculus.os.enterprise.Configuration;
import com.oculus.os.enterprise.internal.AppInstallInfo;
import com.oculus.os.enterprise.internal.BindServiceWrapper;
import com.oculus.os.enterprise.internal.IAppInstallStatusChangeCallback;
import com.oculus.os.enterprise.internal.IEnterpriseServer;
import com.oculus.os.enterprise.internal.Version;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import javax.annotation.Nullable;

public class EnterpriseServer {
    public static final String ENTERPRISE_SERVER_PACKAGE = "com.oculus.alpenglow";
    public static final String ENTERPRISE_SERVER_SERVICE = "com.oculus.alpenglow.enterpriseserver.EnterpriseServer";
    public static Handler mHandler;
    public static HandlerThread mHandlerThread;
    public final Object mConnectionLock = new Object();
    public final Context mContext;
    public final ServiceConnection mServiceConnection = new ServiceConnection() {
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
    public volatile IEnterpriseServer mServiceImpl;

    public static Configuration buildEmptyConfiguration() {
        return new Configuration() {
            /* class com.oculus.os.enterprise.EnterpriseServer.AnonymousClass2 */

            @Override // com.oculus.os.enterprise.Configuration
            public /* synthetic */ String[] getDynamicConfig() {
                return new String[0];
            }

            @Override // com.oculus.os.enterprise.Configuration
            public /* synthetic */ Date getLicenseExpirationDate() {
                return Configuration.CC.$default$getLicenseExpirationDate(this);
            }

            @Override // com.oculus.os.enterprise.Configuration
            public /* synthetic */ Mode[] getModes() {
                return Configuration.CC.$default$getModes(this);
            }

            @Override // com.oculus.os.enterprise.Configuration
            public /* synthetic */ ControllerMode getControllerMode() {
                return ControllerMode.GAZE_MODE_DISABLED;
            }

            @Override // com.oculus.os.enterprise.Configuration
            public /* synthetic */ int getDefaultModeIndex() {
                return 0;
            }

            @Override // com.oculus.os.enterprise.Configuration
            public /* synthetic */ GuardianMode getGuardianMode() {
                return GuardianMode.ENABLED;
            }

            @Override // com.oculus.os.enterprise.Configuration
            public /* synthetic */ HandTracking getHandTracking() {
                return HandTracking.DISABLED;
            }

            @Override // com.oculus.os.enterprise.Configuration
            public /* synthetic */ Date getLastFetchTime() {
                return new Date(0);
            }

            @Override // com.oculus.os.enterprise.Configuration
            @Nullable
            public /* synthetic */ String getOwnerName() {
                return null;
            }

            @Override // com.oculus.os.enterprise.Configuration
            public /* synthetic */ boolean getShowUnknownSources() {
                return false;
            }

            @Override // com.oculus.os.enterprise.Configuration
            public /* synthetic */ Date getTimestamp() {
                return new Date(0);
            }

            @Override // com.oculus.os.enterprise.Configuration
            public /* synthetic */ String toMarshalledString() throws UnsupportedEncodingException {
                return "";
            }
        };
    }

    private void ensureBinderConnection() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (mHandler == null) {
            HandlerThread handlerThread = new HandlerThread("EnterpriseServerConnection");
            mHandlerThread = handlerThread;
            handlerThread.start();
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

    public static AppInstallInfo getAppInstallInfo(Context context, String str, String str2) {
        AppInstallInfo appInstallInfo;
        EnterpriseServer enterpriseServer = new EnterpriseServer(context);
        try {
            enterpriseServer.ensureBinderConnection();
            if (enterpriseServer.mServiceImpl == null) {
                appInstallInfo = null;
            } else {
                appInstallInfo = enterpriseServer.mServiceImpl.getAppInstallInfo(str, str2);
            }
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
        Map map;
        EnterpriseServer enterpriseServer = new EnterpriseServer(context);
        try {
            enterpriseServer.ensureBinderConnection();
            if (enterpriseServer.mServiceImpl == null) {
                map = null;
            } else {
                map = enterpriseServer.mServiceImpl.getAppInstallInfos();
            }
            enterpriseServer.releaseBinderConnection();
            return map;
        } catch (RemoteException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            enterpriseServer.releaseBinderConnection();
            throw th;
        }
    }

    public static AppInstallInfo registerCallback(Context context, String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) {
        AppInstallInfo appInstallInfo;
        EnterpriseServer enterpriseServer = new EnterpriseServer(context);
        try {
            enterpriseServer.ensureBinderConnection();
            if (enterpriseServer.mServiceImpl == null) {
                appInstallInfo = null;
            } else {
                appInstallInfo = enterpriseServer.mServiceImpl.registerCallback(str, iAppInstallStatusChangeCallback);
            }
            enterpriseServer.releaseBinderConnection();
            return appInstallInfo;
        } catch (RemoteException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            enterpriseServer.releaseBinderConnection();
            throw th;
        }
    }

    public static Map registerCallbackForAllApps(Context context, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) {
        Map map;
        EnterpriseServer enterpriseServer = new EnterpriseServer(context);
        try {
            enterpriseServer.ensureBinderConnection();
            if (enterpriseServer.mServiceImpl == null) {
                map = null;
            } else {
                map = enterpriseServer.mServiceImpl.registerCallbackForAllApps(iAppInstallStatusChangeCallback);
            }
            enterpriseServer.releaseBinderConnection();
            return map;
        } catch (RemoteException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            enterpriseServer.releaseBinderConnection();
            throw th;
        }
    }

    private void releaseBinderConnection() {
        if (this.mServiceImpl != null) {
            this.mContext.unbindService(this.mServiceConnection);
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

    public EnterpriseServer(Context context) {
        this.mContext = context;
    }

    public static Configuration getConfiguration(Context context) {
        AnonymousClass2 r0;
        if (isStandaloneHmd(context)) {
            EnterpriseServer enterpriseServer = new EnterpriseServer(context);
            try {
                enterpriseServer.ensureBinderConnection();
                if (enterpriseServer.mServiceImpl == null) {
                    r0 = new Configuration() {
                        /* class com.oculus.os.enterprise.EnterpriseServer.AnonymousClass2 */

                        @Override // com.oculus.os.enterprise.Configuration
                        public /* synthetic */ String[] getDynamicConfig() {
                            return new String[0];
                        }

                        @Override // com.oculus.os.enterprise.Configuration
                        public /* synthetic */ Date getLicenseExpirationDate() {
                            return Configuration.CC.$default$getLicenseExpirationDate(this);
                        }

                        @Override // com.oculus.os.enterprise.Configuration
                        public /* synthetic */ Mode[] getModes() {
                            return Configuration.CC.$default$getModes(this);
                        }

                        @Override // com.oculus.os.enterprise.Configuration
                        public /* synthetic */ ControllerMode getControllerMode() {
                            return ControllerMode.GAZE_MODE_DISABLED;
                        }

                        @Override // com.oculus.os.enterprise.Configuration
                        public /* synthetic */ int getDefaultModeIndex() {
                            return 0;
                        }

                        @Override // com.oculus.os.enterprise.Configuration
                        public /* synthetic */ GuardianMode getGuardianMode() {
                            return GuardianMode.ENABLED;
                        }

                        @Override // com.oculus.os.enterprise.Configuration
                        public /* synthetic */ HandTracking getHandTracking() {
                            return HandTracking.DISABLED;
                        }

                        @Override // com.oculus.os.enterprise.Configuration
                        public /* synthetic */ Date getLastFetchTime() {
                            return new Date(0);
                        }

                        @Override // com.oculus.os.enterprise.Configuration
                        @Nullable
                        public /* synthetic */ String getOwnerName() {
                            return null;
                        }

                        @Override // com.oculus.os.enterprise.Configuration
                        public /* synthetic */ boolean getShowUnknownSources() {
                            return false;
                        }

                        @Override // com.oculus.os.enterprise.Configuration
                        public /* synthetic */ Date getTimestamp() {
                            return new Date(0);
                        }

                        @Override // com.oculus.os.enterprise.Configuration
                        public /* synthetic */ String toMarshalledString() throws UnsupportedEncodingException {
                            return "";
                        }
                    };
                } else {
                    com.oculus.os.enterprise.internal.Configuration configurationWithVersion = enterpriseServer.mServiceImpl.getConfigurationWithVersion(Version.latest());
                    if (configurationWithVersion == null) {
                        r0 = new Configuration() {
                            /* class com.oculus.os.enterprise.EnterpriseServer.AnonymousClass2 */

                            @Override // com.oculus.os.enterprise.Configuration
                            public /* synthetic */ String[] getDynamicConfig() {
                                return new String[0];
                            }

                            @Override // com.oculus.os.enterprise.Configuration
                            public /* synthetic */ Date getLicenseExpirationDate() {
                                return Configuration.CC.$default$getLicenseExpirationDate(this);
                            }

                            @Override // com.oculus.os.enterprise.Configuration
                            public /* synthetic */ Mode[] getModes() {
                                return Configuration.CC.$default$getModes(this);
                            }

                            @Override // com.oculus.os.enterprise.Configuration
                            public /* synthetic */ ControllerMode getControllerMode() {
                                return ControllerMode.GAZE_MODE_DISABLED;
                            }

                            @Override // com.oculus.os.enterprise.Configuration
                            public /* synthetic */ int getDefaultModeIndex() {
                                return 0;
                            }

                            @Override // com.oculus.os.enterprise.Configuration
                            public /* synthetic */ GuardianMode getGuardianMode() {
                                return GuardianMode.ENABLED;
                            }

                            @Override // com.oculus.os.enterprise.Configuration
                            public /* synthetic */ HandTracking getHandTracking() {
                                return HandTracking.DISABLED;
                            }

                            @Override // com.oculus.os.enterprise.Configuration
                            public /* synthetic */ Date getLastFetchTime() {
                                return new Date(0);
                            }

                            @Override // com.oculus.os.enterprise.Configuration
                            @Nullable
                            public /* synthetic */ String getOwnerName() {
                                return null;
                            }

                            @Override // com.oculus.os.enterprise.Configuration
                            public /* synthetic */ boolean getShowUnknownSources() {
                                return false;
                            }

                            @Override // com.oculus.os.enterprise.Configuration
                            public /* synthetic */ Date getTimestamp() {
                                return new Date(0);
                            }

                            @Override // com.oculus.os.enterprise.Configuration
                            public /* synthetic */ String toMarshalledString() throws UnsupportedEncodingException {
                                return "";
                            }
                        };
                    } else {
                        enterpriseServer.releaseBinderConnection();
                        return configurationWithVersion;
                    }
                }
                enterpriseServer.releaseBinderConnection();
                return r0;
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

    public static boolean isInEnterpriseMode(Context context) {
        if (!isStandaloneHmd(context) || new SettingsManager().getInt("managed_device", 0) <= 1) {
            return false;
        }
        return true;
    }

    public static boolean isStandaloneHmd(Context context) {
        return context.getPackageManager().hasSystemFeature(BuildConstants.PACKAGE_NAME_OVR_STANDALONE);
    }
}
