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
import android.text.TextUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public final class MobileConfigBaseClient {
    private final String BEFORE_SERVICE_REQUEST_SENT = "BEFORE_SERVICE_REQUEST_SENT";
    private final String EXTRA = "EXTRA";
    private final String FAILURE_CAUSE = "FAILURE_CAUSE";
    private final String FUNCTION_NAME = "FUNCTION_NAME";
    private final String SERVICE_RESPONSE_RECEIVED = "SERVICE_RESPONSE_RECEIVED";
    private final String VALUE_SOURCE = "VALUE_SOURCE";
    public final Context mContext;
    public final MobileConfigClientDataLogger mLogger;
    public final Map<String, List<MobileConfigContentObserver>> mMobileConfigContentObservers = new HashMap();
    public QuickPerformanceLogger mQpl = null;
    final String mServiceAppPackage;

    public MobileConfigBaseClient(String str, Context context) {
        this.mContext = context;
        this.mLogger = new MobileConfigClientDataLogger();
        this.mServiceAppPackage = str;
    }

    public final void subscribe(final MobileConfigServiceSubscribeCallback mobileConfigServiceSubscribeCallback) {
        final String paramsMapContent = getParamsMapContent();
        if (paramsMapContent == null) {
            mobileConfigServiceSubscribeCallback.onMobileConfigSubscribeFailure("Can't fetch client params_map.txt");
            return;
        }
        String processName = getProcessName();
        final Uri withAppendedPath = Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_SUCCESS_URI, processName);
        AnonymousClass1 r3 = new ContentObserver(new Handler(this.mContext.getMainLooper())) {
            /* class com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient.AnonymousClass1 */

            public final void onChange(boolean z, Uri uri) {
                if (MobileConfigBaseClient.this.mQpl != null) {
                    QuickPerformanceLogger quickPerformanceLogger = MobileConfigBaseClient.this.mQpl;
                }
                super.onChange(z);
                if (uri.equals(withAppendedPath)) {
                    mobileConfigServiceSubscribeCallback.onMobileConfigSubscribeSuccess();
                    MobileConfigBaseClient.this.mLogger.logApiResponse("subscribe", BuildConfig.PROVIDER_SUFFIX, true, BuildConfig.PROVIDER_SUFFIX);
                    if (MobileConfigBaseClient.this.mQpl != null) {
                        QuickPerformanceLogger quickPerformanceLogger2 = MobileConfigBaseClient.this.mQpl;
                        return;
                    }
                    return;
                }
                String[] split = uri.getPath().split("/");
                mobileConfigServiceSubscribeCallback.onMobileConfigSubscribeFailure(split[1]);
                MobileConfigBaseClient.this.mLogger.logApiResponse("subscribe", BuildConfig.PROVIDER_SUFFIX, false, split[1]);
                if (MobileConfigBaseClient.this.mQpl != null) {
                    QuickPerformanceLogger quickPerformanceLogger3 = MobileConfigBaseClient.this.mQpl;
                    QuickPerformanceLogger quickPerformanceLogger4 = MobileConfigBaseClient.this.mQpl;
                }
            }
        };
        try {
            this.mContext.getContentResolver().registerContentObserver(withAppendedPath, false, r3);
            this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_FAILED_TO_SAVE_URI, processName), false, r3);
            this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_PARAMS_MAP_PARSE_FAILED_URI, processName), false, r3);
            this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_EXCEPTION, processName), false, r3);
            BLog.i("MobileConfigBaseClient", "Start thread to subscribe with paramsMapContent file descriptor from mobileconfig service");
            new Thread(new Runnable() {
                /* class com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient.AnonymousClass2 */

                public final void run() {
                    MobileConfigBaseClient mobileConfigBaseClient = MobileConfigBaseClient.this;
                    McConnection mcConnection = new McConnection(mobileConfigBaseClient, mobileConfigBaseClient.mServiceAppPackage, (byte) 0);
                    if (MobileConfigBaseClient.this.mQpl != null) {
                        QuickPerformanceLogger quickPerformanceLogger = MobileConfigBaseClient.this.mQpl;
                    }
                    Log.d("MobileConfigBaseClient", "connect()");
                    ComponentName componentName = new ComponentName(mcConnection.mServiceAppPackage, "com.facebook.mobileconfigservice.service.MobileConfigService");
                    Intent intent = new Intent();
                    intent.setComponent(componentName);
                    if (!MobileConfigBaseClient.this.mContext.bindService(intent, mcConnection, 1)) {
                        mobileConfigServiceSubscribeCallback.onMobileConfigSubscribeFailure("Failed to connect to MobileConfig Service");
                        return;
                    }
                    try {
                        int myPid = Process.myPid();
                        if (mcConnection.mService == null) {
                            Log.d("MobileConfigBaseClient", "awaitService()");
                            try {
                                mcConnection.mLatch.await(30, TimeUnit.SECONDS);
                                if (mcConnection.mService == null) {
                                    Log.e("MobileConfigBaseClient", "Timed out trying to connect to MobileConfigService.");
                                }
                            } catch (InterruptedException e) {
                                Log.e("MobileConfigBaseClient", "Interrupted Exception during connecting to service", e);
                                Thread.currentThread().interrupt();
                            }
                            if (mcConnection.mService == null) {
                                mobileConfigServiceSubscribeCallback.onMobileConfigSubscribeFailure("Could not connect to MobileConfigService");
                                return;
                            }
                        }
                        mcConnection.mService.subscribeWithProcessName(paramsMapContent, MobileConfigBaseClient.this.getProcessName(), myPid);
                        mcConnection.disconnect();
                    } catch (Exception e2) {
                        BLog.e("MobileConfigBaseClient", "Exception while connecting to mc service", e2);
                        String message = e2.getMessage();
                        if (message == null) {
                            message = "Empty exception message";
                        }
                        mobileConfigServiceSubscribeCallback.onMobileConfigSubscribeFailure(message);
                    } finally {
                        mcConnection.disconnect();
                    }
                }
            }).start();
        } catch (SecurityException e) {
            mobileConfigServiceSubscribeCallback.onMobileConfigSubscribeFailure(e.getMessage());
        }
    }

    public final boolean getBooleanWithOptions(String str, boolean z, MobileConfigOptions mobileConfigOptions) {
        try {
            boolean z2 = false;
            if (str.isEmpty()) {
                this.mLogger.logApiResponse("getBoolean", str, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                mobileConfigOptions.mValueSource = MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                if (this.mQpl != null) {
                    MobileConfigValueSource mobileConfigValueSource = MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                }
                return z;
            }
            MobileConfigOptions requestForValueSource = mobileConfigOptions.requestForValueSource();
            Cursor queryMobileConfigContentProvider = queryMobileConfigContentProvider(str, Boolean.toString(z), "bool", requestForValueSource);
            if (queryMobileConfigContentProvider == null) {
                this.mLogger.logApiResponse("getBoolean", str, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                requestForValueSource.mValueSource = MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND;
                if (this.mQpl != null) {
                    MobileConfigValueSource mobileConfigValueSource2 = MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND;
                }
                closeSilently(queryMobileConfigContentProvider);
                return z;
            }
            if (queryMobileConfigContentProvider.getInt(0) > 0) {
                z2 = true;
            }
            MobileConfigValueSource fromInt = MobileConfigValueSource.fromInt(queryMobileConfigContentProvider.getInt(1));
            requestForValueSource.mValueSource = fromInt;
            queryMobileConfigContentProvider.close();
            this.mLogger.logApiResponse("getBoolean", str, true, requestForValueSource.mValueSource.toString());
            if (this.mQpl != null) {
                int i = fromInt.source;
            }
            closeSilently(queryMobileConfigContentProvider);
            return z2;
        } catch (Exception e) {
            BLog.e("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            return z;
        } finally {
            closeSilently(null);
        }
    }

    public final long getLongWithOptions(String str, long j, MobileConfigOptions mobileConfigOptions) {
        try {
            if (str.isEmpty()) {
                this.mLogger.logApiResponse("getLong", str, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                mobileConfigOptions.mValueSource = MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                if (this.mQpl != null) {
                    MobileConfigValueSource mobileConfigValueSource = MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                }
                return j;
            }
            MobileConfigOptions requestForValueSource = mobileConfigOptions.requestForValueSource();
            Cursor queryMobileConfigContentProvider = queryMobileConfigContentProvider(str, Long.toString(j), "long", requestForValueSource);
            if (queryMobileConfigContentProvider == null) {
                this.mLogger.logApiResponse("getLong", str, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                requestForValueSource.mValueSource = MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND;
                if (this.mQpl != null) {
                    MobileConfigValueSource mobileConfigValueSource2 = MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND;
                }
                closeSilently(queryMobileConfigContentProvider);
                return j;
            }
            long j2 = queryMobileConfigContentProvider.getLong(0);
            MobileConfigValueSource fromInt = MobileConfigValueSource.fromInt(queryMobileConfigContentProvider.getInt(1));
            requestForValueSource.mValueSource = fromInt;
            this.mLogger.logApiResponse("getLong", str, true, requestForValueSource.mValueSource.toString());
            if (this.mQpl != null) {
                int i = fromInt.source;
            }
            closeSilently(queryMobileConfigContentProvider);
            return j2;
        } catch (Exception e) {
            BLog.e("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            return j;
        } finally {
            closeSilently(null);
        }
    }

    public final String getStringWithOptions(String str, String str2, MobileConfigOptions mobileConfigOptions) {
        try {
            if (str.isEmpty()) {
                this.mLogger.logApiResponse("getString", str, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                mobileConfigOptions.mValueSource = MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                if (this.mQpl != null) {
                    MobileConfigValueSource mobileConfigValueSource = MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                }
                return str2;
            }
            MobileConfigOptions requestForValueSource = mobileConfigOptions.requestForValueSource();
            Cursor queryMobileConfigContentProvider = queryMobileConfigContentProvider(str, str2, "string", requestForValueSource);
            if (queryMobileConfigContentProvider == null) {
                this.mLogger.logApiResponse("getString", str, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                requestForValueSource.mValueSource = MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND;
                if (this.mQpl != null) {
                    MobileConfigValueSource mobileConfigValueSource2 = MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                }
                if (this.mQpl != null) {
                    MobileConfigValueSource mobileConfigValueSource3 = MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND;
                }
                closeSilently(queryMobileConfigContentProvider);
                return str2;
            }
            String string = queryMobileConfigContentProvider.getString(0);
            MobileConfigValueSource fromInt = MobileConfigValueSource.fromInt(queryMobileConfigContentProvider.getInt(1));
            requestForValueSource.mValueSource = fromInt;
            this.mLogger.logApiResponse("getString", str, true, requestForValueSource.mValueSource.toString());
            if (this.mQpl != null) {
                int i = fromInt.source;
            }
            closeSilently(queryMobileConfigContentProvider);
            return string;
        } catch (Exception e) {
            BLog.e("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            return str2;
        } finally {
            closeSilently(null);
        }
    }

    public final double getDoubleWithOptions(String str, double d, MobileConfigOptions mobileConfigOptions) {
        try {
            if (str.isEmpty()) {
                this.mLogger.logApiResponse("getDouble", str, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                mobileConfigOptions.mValueSource = MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                if (this.mQpl != null) {
                    MobileConfigValueSource mobileConfigValueSource = MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                }
                return d;
            }
            MobileConfigOptions requestForValueSource = mobileConfigOptions.requestForValueSource();
            Cursor queryMobileConfigContentProvider = queryMobileConfigContentProvider(str, Double.toString(d), "double", requestForValueSource);
            if (queryMobileConfigContentProvider == null) {
                this.mLogger.logApiResponse("getDouble", str, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                requestForValueSource.mValueSource = MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND;
                if (this.mQpl != null) {
                    MobileConfigValueSource mobileConfigValueSource2 = MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND;
                }
                closeSilently(queryMobileConfigContentProvider);
                return d;
            }
            double d2 = queryMobileConfigContentProvider.getDouble(0);
            MobileConfigValueSource fromInt = MobileConfigValueSource.fromInt(queryMobileConfigContentProvider.getInt(1));
            requestForValueSource.mValueSource = fromInt;
            this.mLogger.logApiResponse("getDouble", str, true, requestForValueSource.mValueSource.toString());
            if (this.mQpl != null) {
                int i = fromInt.source;
            }
            closeSilently(queryMobileConfigContentProvider);
            return d2;
        } catch (Exception e) {
            BLog.e("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            return d;
        } finally {
            closeSilently(null);
        }
    }

    public final Map<String, ValueInfo> getMultiple(String[] strArr) {
        String str;
        ValueInfo valueInfo;
        HashMap hashMap = new HashMap();
        try {
            ContentProviderClient acquireUnstableContentProviderClient = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.GET_MULTIPLE_CONFIGS_URI);
            if (acquireUnstableContentProviderClient == null) {
                try {
                    BLog.e("MobileConfigBaseClient", "Content provider for the mobileconfig service not found");
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                    return hashMap;
                } catch (Throwable unused) {
                }
            } else {
                Cursor query = acquireUnstableContentProviderClient.query(MobileConfigServiceConstants.GET_MULTIPLE_CONFIGS_URI, strArr, BuildConfig.PROVIDER_SUFFIX, new String[0], null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
                                String string = query.getString(0);
                                String string2 = query.getString(1);
                                String string3 = query.getString(2);
                                String string4 = query.getString(3);
                                String string5 = query.getString(4);
                                String string6 = query.getString(5);
                                MobileConfigValueSource mobileConfigValueSource = MobileConfigValueSource.UNKNOWN;
                                String string7 = query.getString(6);
                                if (string7 != null) {
                                    try {
                                        mobileConfigValueSource = MobileConfigValueSource.fromInt(Integer.parseInt(string7));
                                    } catch (NumberFormatException unused2) {
                                    }
                                }
                                if (string2 == null) {
                                    BLog.e("MobileConfigBaseClient", "Null type for config param %s", string);
                                } else {
                                    if (string6 == null) {
                                        BLog.e("MobileConfigBaseClient", "Null query passed for config param %s", string);
                                        str = string;
                                    } else {
                                        str = string6;
                                    }
                                    boolean parseBoolean = Boolean.parseBoolean(string5);
                                    try {
                                        int parseInt = Integer.parseInt(string2);
                                        Object obj = string3;
                                        if (parseInt == 1) {
                                            obj = Boolean.valueOf(Boolean.parseBoolean(string3));
                                        } else if (parseInt == 2) {
                                            obj = Long.valueOf(Long.parseLong(string3));
                                        } else if (parseInt != 3) {
                                            if (parseInt != 4) {
                                                BLog.e("MobileConfigBaseClient", "Unrecognized type %s for config param %s", string2, string);
                                            } else {
                                                obj = Double.valueOf(Double.parseDouble(string3));
                                            }
                                        }
                                        if (mobileConfigValueSource == null) {
                                            valueInfo = new ValueInfo(parseInt, parseBoolean, obj, string4, str);
                                        } else {
                                            valueInfo = new ValueInfo(parseInt, parseBoolean, obj, string4, str, mobileConfigValueSource);
                                        }
                                        hashMap.put(string, valueInfo);
                                    } catch (NumberFormatException unused3) {
                                        BLog.e("MobileConfigBaseClient", "Unrecognized type %s for config param %s", string2, string);
                                    }
                                }
                            }
                            if (this.mQpl != null) {
                                hashMap.keySet().toArray(new String[0]);
                            }
                            query.close();
                            acquireUnstableContentProviderClient.close();
                            return hashMap;
                        }
                    } catch (Throwable unused4) {
                    }
                }
                BLog.e("MobileConfigBaseClient", "no results returned for queryString %s", TextUtils.join(",", strArr));
                if (this.mQpl != null) {
                    MobileConfigValueSource mobileConfigValueSource2 = MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND;
                }
                if (query != null) {
                    query.close();
                }
                acquireUnstableContentProviderClient.close();
                return hashMap;
            }
            throw th;
            throw th;
        } catch (RemoteException | SecurityException e) {
            BLog.e("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", e);
            return hashMap;
        }
    }

    public static class MobileConfigContentObserver extends ContentObserver {
        private final IMobileConfigChangeListener mConfigChangeListener;
        private final String mConfigName;

        public MobileConfigContentObserver(String str, IMobileConfigChangeListener iMobileConfigChangeListener, Context context) {
            super(new Handler(context.getMainLooper()));
            this.mConfigName = str;
            this.mConfigChangeListener = iMobileConfigChangeListener;
        }

        public final void onChange(boolean z, Uri uri) {
            super.onChange(z);
            this.mConfigChangeListener.onConfigChanged(this.mConfigName);
        }
    }

    private Cursor queryMobileConfigContentProvider(String str, String str2, String str3, MobileConfigOptions mobileConfigOptions) {
        try {
            ContentProviderClient acquireUnstableContentProviderClient = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.CONTENT_URI_GET_PATH);
            if (acquireUnstableContentProviderClient == null) {
                try {
                    BLog.e("MobileConfigBaseClient", "Content provider for the mobileconfig service not found");
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                    return null;
                } catch (Throwable unused) {
                }
            } else {
                String[] strArr = new String[1];
                strArr[0] = mobileConfigOptions.mRequestForValueSource ? "request_value_source" : BuildConfig.PROVIDER_SUFFIX;
                Cursor query = acquireUnstableContentProviderClient.query(MobileConfigServiceConstants.CONTENT_URI_GET_PATH, new String[]{str3, str2}, str, strArr, null);
                if (query == null || !query.moveToFirst()) {
                    BLog.e("MobileConfigBaseClient", "no results returned for %s", str);
                    acquireUnstableContentProviderClient.close();
                    return null;
                }
                acquireUnstableContentProviderClient.close();
                return query;
            }
            throw th;
        } catch (RemoteException | SecurityException e) {
            BLog.e("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", e);
            return null;
        }
    }

    public final String getParamsMapContent() {
        AssetManager assets = this.mContext.getAssets();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assets.open("params_map.txt")));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                        sb.append('\n');
                    } else {
                        bufferedReader.close();
                        return sb.toString();
                    }
                } catch (Throwable unused) {
                }
            }
            throw th;
        } catch (IOException e) {
            BLog.e("MobileConfigBaseClient", "IOException while trying to read params map", e);
            return null;
        }
    }

    public String getProcessName() {
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

    @SuppressLint({"CatchGeneralException"})
    public static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    class McConnection implements ServiceConnection {
        final CountDownLatch mLatch;
        volatile IMobileConfig mService;
        final String mServiceAppPackage;

        /* synthetic */ McConnection(MobileConfigBaseClient mobileConfigBaseClient, String str, byte b) {
            this(str);
        }

        private McConnection(String str) {
            this.mLatch = new CountDownLatch(1);
            this.mServiceAppPackage = str;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("MobileConfigBaseClient", "Connected to MobileConfigService");
            this.mService = IMobileConfig.Stub.asInterface(iBinder);
            this.mLatch.countDown();
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            Log.d("MobileConfigBaseClient", "Disconnected from MobileConfigService");
            this.mService = null;
        }

        public final void onBindingDied(ComponentName componentName) {
            this.mService = null;
        }

        public final void disconnect() {
            BLog.d("MobileConfigBaseClient", "disconnect()");
            MobileConfigBaseClient.this.mContext.unbindService(this);
            this.mService = null;
        }
    }
}
