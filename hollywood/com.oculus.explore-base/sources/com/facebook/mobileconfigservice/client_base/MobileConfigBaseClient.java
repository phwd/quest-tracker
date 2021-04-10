package com.facebook.mobileconfigservice.client_base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.facebook.debug.log.BLog;
import com.facebook.mobileconfig.factory.MobileConfigOptions;
import com.facebook.mobileconfig.factory.MobileConfigValueSource;
import com.facebook.mobileconfigservice.client_ifaces.IMobileConfigChangeListener;
import com.facebook.mobileconfigservice.client_ifaces.MobileConfigServiceSubscribeCallback;
import com.facebook.mobileconfigservice.service.IMobileConfig;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigServiceConstants;
import com.facebook.quicklog.QuickPerformanceLogger;
import com.oculus.common.build.BuildConfig;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MobileConfigBaseClient {
    private final String BEFORE_SERVICE_REQUEST_SENT = "BEFORE_SERVICE_REQUEST_SENT";
    private final String EXTRA = "EXTRA";
    private final String FAILURE_CAUSE = "FAILURE_CAUSE";
    private final String FUNCTION_NAME = "FUNCTION_NAME";
    private final String SERVICE_RESPONSE_RECEIVED = "SERVICE_RESPONSE_RECEIVED";
    private final String VALUE_SOURCE = "VALUE_SOURCE";
    private final McConnection mConnection;
    private final Context mContext;
    private final MobileConfigClientDataLogger mLogger;
    private final Map<String, List<MobileConfigContentObserver>> mMobileConfigContentObservers = new HashMap();
    private QuickPerformanceLogger mQpl = null;

    public MobileConfigBaseClient(String serviceAppPackage, Context context) {
        this.mContext = context;
        this.mConnection = new McConnection(serviceAppPackage);
        this.mLogger = new MobileConfigClientDataLogger();
        try {
            this.mConnection.connect();
        } catch (RuntimeException e) {
            BLog.e("MobileConfigBaseClient", e, "Failed to connect to MobileConfig Service");
        }
    }

    public void initLogging(MobileConfigMarauderLogger logger, QuickPerformanceLogger qpl) {
        if (logger != null) {
            this.mLogger.setMarauderLogger(logger);
        }
        this.mQpl = qpl;
    }

    public void subscribe(MobileConfigServiceSubscribeCallback callback) {
        if (this.mQpl != null) {
            this.mQpl.markerStart(645021198);
        }
        subscribe(callback, getParamsMapContent());
    }

    /* access modifiers changed from: protected */
    public void handleException(Exception e, String methodName) {
    }

    public void subscribe(final MobileConfigServiceSubscribeCallback callback, String paramsMapContent) {
        String processNameForCallback = getProcessName();
        final Uri successCallbackUri = Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_SUCCESS_URI, processNameForCallback);
        ContentObserver observer = new ContentObserver(new Handler(this.mContext.getMainLooper())) {
            /* class com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient.AnonymousClass1 */

            public void onChange(boolean selfChange, Uri uri) {
                if (MobileConfigBaseClient.this.mQpl != null) {
                    MobileConfigBaseClient.this.mQpl.markerPoint(645021198, "SERVICE_RESPONSE_RECEIVED");
                }
                super.onChange(selfChange);
                if (uri.equals(successCallbackUri)) {
                    callback.onMobileConfigSubscribeSuccess();
                    MobileConfigBaseClient.this.mLogger.logApiResponse("subscribe", BuildConfig.PROVIDER_SUFFIX, true, BuildConfig.PROVIDER_SUFFIX);
                    if (MobileConfigBaseClient.this.mQpl != null) {
                        MobileConfigBaseClient.this.mQpl.markerEnd(645021198, 2);
                        return;
                    }
                    return;
                }
                String[] splitUri = uri.getPath().split("/");
                callback.onMobileConfigSubscribeFailure(splitUri[1]);
                MobileConfigBaseClient.this.mLogger.logApiResponse("subscribe", BuildConfig.PROVIDER_SUFFIX, false, splitUri[1]);
                if (MobileConfigBaseClient.this.mQpl != null) {
                    MobileConfigBaseClient.this.mQpl.markerAnnotate(645021198, "FAILURE_CAUSE", splitUri[1]);
                    MobileConfigBaseClient.this.mQpl.markerEnd(645021198, 3);
                }
            }
        };
        try {
            this.mContext.getContentResolver().registerContentObserver(successCallbackUri, false, observer);
            this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_FAILED_TO_SAVE_URI, processNameForCallback), false, observer);
            this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_PARAMS_MAP_PARSE_FAILED_URI, processNameForCallback), false, observer);
            this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_EXCEPTION, processNameForCallback), false, observer);
            internalSubscribe(paramsMapContent);
        } catch (SecurityException e) {
            callback.onMobileConfigSubscribeFailure(e.getMessage());
        }
    }

    public boolean getBooleanWithOptions(String configParamName, boolean defaultValue, MobileConfigOptions options) {
        boolean retVal = true;
        try {
            if (this.mQpl != null) {
                this.mQpl.markerStart(645036435);
                this.mQpl.markerAnnotate(645036435, "FUNCTION_NAME", "getBoolean");
            }
            if (configParamName.isEmpty()) {
                this.mLogger.logApiResponse("getBoolean", configParamName, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                options.setValueSource(MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                return defaultValue;
            }
            MobileConfigOptions options2 = options.requestForValueSource();
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "BEFORE_SERVICE_REQUEST_SENT");
            }
            Cursor cursor = queryMobileConfigContentProvider(configParamName, Boolean.toString(defaultValue), "bool", options2);
            if (cursor == null) {
                this.mLogger.logApiResponse("getBoolean", configParamName, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                options2.setValueSource(MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                closeSilently(cursor);
                return defaultValue;
            }
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "SERVICE_RESPONSE_RECEIVED");
            }
            if (cursor.getInt(0) <= 0) {
                retVal = false;
            }
            MobileConfigValueSource valueSource = MobileConfigValueSource.fromInt(cursor.getInt(1));
            options2.setValueSource(valueSource);
            cursor.close();
            this.mLogger.logApiResponse("getBoolean", configParamName, true, options2.getValueSource().toString());
            if (this.mQpl != null) {
                this.mQpl.markerAnnotate(645036435, "VALUE_SOURCE", valueSource.getSource());
                this.mQpl.markerEnd(645036435, 2);
            }
            closeSilently(cursor);
            return retVal;
        } catch (Exception e) {
            handleException(e, "getBoolean");
            BLog.e("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            return defaultValue;
        } finally {
            closeSilently(null);
        }
    }

    public long getLongWithOptions(String configParamName, long defaultValue, MobileConfigOptions options) {
        try {
            if (this.mQpl != null) {
                this.mQpl.markerStart(645036435);
                this.mQpl.markerAnnotate(645036435, "FUNCTION_NAME", "getLong");
            }
            if (configParamName.isEmpty()) {
                this.mLogger.logApiResponse("getLong", configParamName, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                options.setValueSource(MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                return defaultValue;
            }
            MobileConfigOptions options2 = options.requestForValueSource();
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "BEFORE_SERVICE_REQUEST_SENT");
            }
            Cursor cursor = queryMobileConfigContentProvider(configParamName, Long.toString(defaultValue), "long", options2);
            if (cursor == null) {
                this.mLogger.logApiResponse("getLong", configParamName, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                options2.setValueSource(MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                closeSilently(cursor);
                return defaultValue;
            }
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "SERVICE_RESPONSE_RECEIVED");
            }
            long retVal = cursor.getLong(0);
            MobileConfigValueSource valueSource = MobileConfigValueSource.fromInt(cursor.getInt(1));
            options2.setValueSource(valueSource);
            this.mLogger.logApiResponse("getLong", configParamName, true, options2.getValueSource().toString());
            if (this.mQpl != null) {
                this.mQpl.markerAnnotate(645036435, "VALUE_SOURCE", valueSource.getSource());
                this.mQpl.markerEnd(645036435, 2);
            }
            closeSilently(cursor);
            return retVal;
        } catch (Exception e) {
            handleException(e, "getLong");
            BLog.e("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            return defaultValue;
        } finally {
            closeSilently(null);
        }
    }

    public String getStringWithOptions(String configParamName, String defaultValue, MobileConfigOptions options) {
        try {
            if (this.mQpl != null) {
                this.mQpl.markerStart(645036435);
                this.mQpl.markerAnnotate(645036435, "FUNCTION_NAME", "getString");
            }
            if (configParamName.isEmpty()) {
                this.mLogger.logApiResponse("getString", configParamName, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                options.setValueSource(MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                return defaultValue;
            }
            MobileConfigOptions options2 = options.requestForValueSource();
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "BEFORE_SERVICE_REQUEST_SENT");
            }
            Cursor cursor = queryMobileConfigContentProvider(configParamName, defaultValue, "string", options2);
            if (cursor == null) {
                this.mLogger.logApiResponse("getString", configParamName, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                options2.setValueSource(MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                closeSilently(cursor);
                return defaultValue;
            }
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "SERVICE_RESPONSE_RECEIVED");
            }
            String retVal = cursor.getString(0);
            MobileConfigValueSource valueSource = MobileConfigValueSource.fromInt(cursor.getInt(1));
            options2.setValueSource(valueSource);
            this.mLogger.logApiResponse("getString", configParamName, true, options2.getValueSource().toString());
            if (this.mQpl != null) {
                this.mQpl.markerAnnotate(645036435, "VALUE_SOURCE", valueSource.getSource());
                this.mQpl.markerEnd(645036435, 2);
            }
            closeSilently(cursor);
            return retVal;
        } catch (Exception e) {
            handleException(e, "getString");
            BLog.e("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            return defaultValue;
        } finally {
            closeSilently(null);
        }
    }

    public double getDoubleWithOptions(String configParamName, double defaultValue, MobileConfigOptions options) {
        try {
            if (this.mQpl != null) {
                this.mQpl.markerStart(645036435);
                this.mQpl.markerAnnotate(645036435, "FUNCTION_NAME", "getDouble");
            }
            if (configParamName.isEmpty()) {
                this.mLogger.logApiResponse("getDouble", configParamName, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                options.setValueSource(MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                return defaultValue;
            }
            MobileConfigOptions options2 = options.requestForValueSource();
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "BEFORE_SERVICE_REQUEST_SENT");
            }
            Cursor cursor = queryMobileConfigContentProvider(configParamName, Double.toString(defaultValue), "double", options2);
            if (cursor == null) {
                this.mLogger.logApiResponse("getDouble", configParamName, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                options2.setValueSource(MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                closeSilently(cursor);
                return defaultValue;
            }
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "SERVICE_RESPONSE_RECEIVED");
            }
            double retVal = cursor.getDouble(0);
            MobileConfigValueSource valueSource = MobileConfigValueSource.fromInt(cursor.getInt(1));
            options2.setValueSource(valueSource);
            this.mLogger.logApiResponse("getDouble", configParamName, true, options2.getValueSource().toString());
            if (this.mQpl != null) {
                this.mQpl.markerAnnotate(645036435, "VALUE_SOURCE", valueSource.getSource());
                this.mQpl.markerEnd(645036435, 2);
            }
            closeSilently(cursor);
            return retVal;
        } catch (Exception e) {
            handleException(e, "getDouble");
            BLog.e("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            return defaultValue;
        } finally {
            closeSilently(null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, com.facebook.mobileconfigservice.client_base.ValueInfo> getMultiple(java.lang.String[] r27) {
        /*
        // Method dump skipped, instructions count: 574
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient.getMultiple(java.lang.String[]):java.util.Map");
    }

    public void logExposure(String loggingId, boolean isSessionLess) {
        try {
            ContentProviderClient client = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.LOG_EXPOSURE_URI);
            if (client == null) {
                try {
                    BLog.e("MobileConfigBaseClient", "Content provider for the mobileconfig service not found");
                    if (client != null) {
                        client.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                String[] projection = new String[1];
                projection[0] = isSessionLess ? "1" : "0";
                closeSilently(client.query(MobileConfigServiceConstants.LOG_EXPOSURE_URI, projection, loggingId, new String[0], null));
                if (client != null) {
                    client.close();
                    return;
                }
                return;
            }
            throw th;
        } catch (RemoteException | SecurityException e) {
            handleException(e, "logExposure");
            BLog.e("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", e);
        }
    }

    public void addChangeListener(String configName, IMobileConfigChangeListener changeListener) {
        Exception e;
        boolean shouldNotifyServer = true;
        synchronized (this.mMobileConfigContentObservers) {
            if (getObserver(configName, changeListener) != null) {
                this.mLogger.logApiResponse("addChangeListener", configName, true, "Already added");
                return;
            }
            MobileConfigContentObserver observer = new MobileConfigContentObserver(configName, changeListener, this.mContext);
            this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(Uri.withAppendedPath(MobileConfigServiceConstants.CHANGE_LISTENER_PATH, configName), getProcessName()), false, observer);
            if (containsOtherObserverForConfig(configName)) {
                shouldNotifyServer = false;
            }
            if (!this.mMobileConfigContentObservers.containsKey(configName)) {
                this.mMobileConfigContentObservers.put(configName, new ArrayList());
            }
            this.mMobileConfigContentObservers.get(configName).add(observer);
            if (!shouldNotifyServer) {
                this.mLogger.logApiResponse("addChangeListener", configName, true, "Already added in server");
                return;
            }
            try {
                ContentProviderClient client = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.CHANGE_LISTENER_PATH);
                if (client == null) {
                    try {
                        BLog.e("MobileConfigBaseClient", "Content provider for the mobileconfig service not found");
                        this.mLogger.logApiResponse("addChangeListener", configName, false, "Service not found");
                        if (client != null) {
                            client.close();
                        }
                        return;
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                } else {
                    closeSilently(client.query(MobileConfigServiceConstants.CHANGE_LISTENER_PATH, new String[0], configName, new String[0], getProcessName()));
                    this.mLogger.logApiResponse("addChangeListener", configName, true, BuildConfig.PROVIDER_SUFFIX);
                    if (client != null) {
                        client.close();
                    }
                    return;
                }
            } catch (RemoteException e2) {
                e = e2;
                handleException(e, "addChangeListener");
                this.mLogger.logApiResponse("addChangeListener", configName, false, "Service not running");
                BLog.e("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", e);
                return;
            } catch (SecurityException e3) {
                e = e3;
                handleException(e, "addChangeListener");
                this.mLogger.logApiResponse("addChangeListener", configName, false, "Service not running");
                BLog.e("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", e);
                return;
            }
        }
        throw th;
    }

    private boolean containsOtherObserverForConfig(String configName) {
        if (!this.mMobileConfigContentObservers.containsKey(configName) || this.mMobileConfigContentObservers.get(configName).size() <= 0) {
            return false;
        }
        return true;
    }

    private MobileConfigContentObserver getObserver(String configName, IMobileConfigChangeListener configChangeListener) {
        if (this.mMobileConfigContentObservers.containsKey(configName)) {
            for (MobileConfigContentObserver m : this.mMobileConfigContentObservers.get(configName)) {
                if (m.mConfigChangeListener.equals(configChangeListener)) {
                    return m;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static class MobileConfigContentObserver extends ContentObserver {
        private final IMobileConfigChangeListener mConfigChangeListener;
        private final String mConfigName;

        public MobileConfigContentObserver(String alohaMobileConfigSchema, IMobileConfigChangeListener configChangeListener, Context context) {
            super(new Handler(context.getMainLooper()));
            this.mConfigName = alohaMobileConfigSchema;
            this.mConfigChangeListener = configChangeListener;
        }

        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange);
            this.mConfigChangeListener.onConfigChanged(this.mConfigName);
        }
    }

    public Cursor queryMobileConfigContentProvider(String configParamName, String defaultValue, String type, MobileConfigOptions options) {
        String str;
        try {
            ContentProviderClient client = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.CONTENT_URI_GET_PATH);
            if (client == null) {
                try {
                    BLog.e("MobileConfigBaseClient", "Content provider for the mobileconfig service not found");
                    if (client != null) {
                        client.close();
                    }
                    return null;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                String[] selectionArgs = new String[1];
                if (options.isValueSourceRequested()) {
                    str = "request_value_source";
                } else {
                    str = BuildConfig.PROVIDER_SUFFIX;
                }
                selectionArgs[0] = str;
                Cursor cursor = client.query(MobileConfigServiceConstants.CONTENT_URI_GET_PATH, new String[]{type, defaultValue}, configParamName, selectionArgs, null);
                if (cursor == null || !cursor.moveToFirst()) {
                    BLog.e("MobileConfigBaseClient", "no results returned for %s", configParamName);
                    if (client != null) {
                        client.close();
                    }
                    return null;
                } else if (client == null) {
                    return cursor;
                } else {
                    client.close();
                    return cursor;
                }
            }
            throw th;
        } catch (RemoteException | SecurityException e) {
            handleException(e, "queryMobileConfigContentProvider");
            BLog.e("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", e);
            return null;
        }
    }

    public String getParamsMapContent() {
        AssetManager am = this.mContext.getAssets();
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(am.open("params_map.txt")));
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    result.append(line);
                    result.append('\n');
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
            handleException(ex, "getParamsMapContent");
            BLog.e("MobileConfigBaseClient", "IOException while trying to read params map", ex);
        }
        return result.toString();
        throw th;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private IMobileConfig getService() throws RemoteException {
        Log.d("MobileConfigBaseClient", "getService()");
        if (this.mConnection.mService != null) {
            return this.mConnection.mService;
        }
        this.mConnection.awaitService();
        if (this.mConnection.mService != null) {
            return this.mConnection.mService;
        }
        throw new RemoteException("Could not connect to MobileConfigService.");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getProcessName() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        try {
            return (String) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentProcessName", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            BLog.e("MobileConfigBaseClient", "Could not retrieve process name, package name will be used instead.", e);
            return this.mContext.getPackageName();
        }
    }

    private void internalSubscribe(final String paramsMapContent) {
        BLog.i("MobileConfigBaseClient", "Start thread to subscribe with paramsMapContent file descriptor from mobileconfig service");
        new Thread(new Runnable() {
            /* class com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient.AnonymousClass2 */

            public void run() {
                try {
                    if (MobileConfigBaseClient.this.mQpl != null) {
                        MobileConfigBaseClient.this.mQpl.markerPoint(645021198, "BEFORE_SERVICE_REQUEST_SENT");
                    }
                    MobileConfigBaseClient.this.getService().subscribeWithProcessName(paramsMapContent, MobileConfigBaseClient.this.getProcessName(), Process.myPid());
                } catch (RemoteException ex) {
                    BLog.e("MobileConfigBaseClient", "Remote exception while connecting to mc service", ex);
                } catch (Exception ex2) {
                    BLog.e("MobileConfigBaseClient", "Generic exception while connecting to mc service", ex2);
                }
            }
        }).start();
    }

    @SuppressLint({"CatchGeneralException"})
    private void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    /* access modifiers changed from: private */
    public class McConnection implements ServiceConnection {
        private final CountDownLatch mLatch;
        private volatile IMobileConfig mService;
        private final String mServiceAppPackage;

        private McConnection(String serviceAppPackage) {
            this.mLatch = new CountDownLatch(1);
            this.mServiceAppPackage = serviceAppPackage;
        }

        public void connect() {
            Log.d("MobileConfigBaseClient", "connect()");
            ComponentName component = new ComponentName(this.mServiceAppPackage, "com.facebook.mobileconfigservice.service.MobileConfigService");
            Intent intent = new Intent();
            intent.setComponent(component);
            if (!MobileConfigBaseClient.this.mContext.bindService(intent, this, 1)) {
                throw new RuntimeException("Error calling bindService to MobileConfigService.");
            }
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("MobileConfigBaseClient", "Connected to MobileConfigService");
            this.mService = IMobileConfig.Stub.asInterface(iBinder);
            this.mLatch.countDown();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("MobileConfigBaseClient", "Disconnected from MobileConfigService");
        }

        public void awaitService() {
            Log.d("MobileConfigBaseClient", "awaitService()");
            try {
                this.mLatch.await(30, TimeUnit.SECONDS);
                if (this.mService == null) {
                    Log.e("MobileConfigBaseClient", "Timed out trying to connect to MobileConfigService.");
                }
            } catch (InterruptedException e) {
                Log.e("MobileConfigBaseClient", "Interrupted Exception during connecting to service", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
