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
import javax.annotation.Nullable;

public class MobileConfigBaseClient {
    private static final String SERVICE_CLS = "com.facebook.mobileconfigservice.service.MobileConfigService";
    private static final String TAG = "MobileConfigBaseClient";
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

    public MobileConfigBaseClient(String str, Context context) {
        this.mContext = context;
        this.mConnection = new McConnection(str);
        this.mLogger = new MobileConfigClientDataLogger();
        try {
            this.mConnection.connect();
        } catch (RuntimeException e) {
            BLog.e(TAG, e, "Failed to connect to MobileConfig Service");
        }
    }

    public void initLogging(@Nullable MobileConfigMarauderLogger mobileConfigMarauderLogger, @Nullable QuickPerformanceLogger quickPerformanceLogger) {
        if (mobileConfigMarauderLogger != null) {
            this.mLogger.setMarauderLogger(mobileConfigMarauderLogger);
        }
        this.mQpl = quickPerformanceLogger;
    }

    public void subscribe(MobileConfigServiceSubscribeCallback mobileConfigServiceSubscribeCallback) {
        QuickPerformanceLogger quickPerformanceLogger = this.mQpl;
        if (quickPerformanceLogger != null) {
            quickPerformanceLogger.markerStart(645021198);
        }
        subscribe(mobileConfigServiceSubscribeCallback, getParamsMapContent());
    }

    public void subscribe(final MobileConfigServiceSubscribeCallback mobileConfigServiceSubscribeCallback, String str) {
        String processName = getProcessName();
        final Uri withAppendedPath = Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_SUCCESS_URI, processName);
        AnonymousClass1 r2 = new ContentObserver(new Handler(this.mContext.getMainLooper())) {
            /* class com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient.AnonymousClass1 */

            public void onChange(boolean z, Uri uri) {
                if (MobileConfigBaseClient.this.mQpl != null) {
                    MobileConfigBaseClient.this.mQpl.markerPoint(645021198, "SERVICE_RESPONSE_RECEIVED");
                }
                super.onChange(z);
                if (uri.equals(withAppendedPath)) {
                    mobileConfigServiceSubscribeCallback.onMobileConfigSubscribeSuccess();
                    MobileConfigBaseClient.this.mLogger.logApiResponse(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_PATH, "", true, "");
                    if (MobileConfigBaseClient.this.mQpl != null) {
                        MobileConfigBaseClient.this.mQpl.markerEnd(645021198, 2);
                        return;
                    }
                    return;
                }
                String[] split = uri.getPath().split("/");
                mobileConfigServiceSubscribeCallback.onMobileConfigSubscribeFailure(split[1]);
                MobileConfigBaseClient.this.mLogger.logApiResponse(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_PATH, "", false, split[1]);
                if (MobileConfigBaseClient.this.mQpl != null) {
                    MobileConfigBaseClient.this.mQpl.markerAnnotate(645021198, "FAILURE_CAUSE", split[1]);
                    MobileConfigBaseClient.this.mQpl.markerEnd(645021198, 3);
                }
            }
        };
        try {
            this.mContext.getContentResolver().registerContentObserver(withAppendedPath, false, r2);
            this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_FAILED_TO_SAVE_URI, processName), false, r2);
            this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_PARAMS_MAP_PARSE_FAILED_URI, processName), false, r2);
            this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(MobileConfigServiceConstants.SUBSCRIBE_CALLBACK_EXCEPTION, processName), false, r2);
            internalSubscribe(str);
        } catch (SecurityException e) {
            mobileConfigServiceSubscribeCallback.onMobileConfigSubscribeFailure(e.getMessage());
        }
    }

    public boolean getBoolean(String str, boolean z) {
        return getBooleanWithOptions(str, z, MobileConfigOptions.NONE);
    }

    public boolean getBooleanWithOptions(String str, boolean z, MobileConfigOptions mobileConfigOptions) {
        try {
            if (this.mQpl != null) {
                this.mQpl.markerStart(645036435);
                this.mQpl.markerAnnotate(645036435, "FUNCTION_NAME", "getBoolean");
            }
            boolean z2 = false;
            if (str.isEmpty()) {
                this.mLogger.logApiResponse("getBoolean", str, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                mobileConfigOptions.setValueSource(MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                return z;
            }
            MobileConfigOptions requestForValueSource = mobileConfigOptions.requestForValueSource();
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "BEFORE_SERVICE_REQUEST_SENT");
            }
            Cursor queryMobileConfigContentProvider = queryMobileConfigContentProvider(str, Boolean.toString(z), "bool", requestForValueSource);
            if (queryMobileConfigContentProvider == null) {
                this.mLogger.logApiResponse("getBoolean", str, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                requestForValueSource.setValueSource(MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                closeSilently(queryMobileConfigContentProvider);
                return z;
            }
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "SERVICE_RESPONSE_RECEIVED");
            }
            if (queryMobileConfigContentProvider.getInt(0) > 0) {
                z2 = true;
            }
            MobileConfigValueSource fromInt = MobileConfigValueSource.fromInt(queryMobileConfigContentProvider.getInt(1));
            requestForValueSource.setValueSource(fromInt);
            queryMobileConfigContentProvider.close();
            this.mLogger.logApiResponse("getBoolean", str, true, requestForValueSource.getValueSource().toString());
            if (this.mQpl != null) {
                this.mQpl.markerAnnotate(645036435, "VALUE_SOURCE", fromInt.getSource());
                this.mQpl.markerEnd(645036435, 2);
            }
            closeSilently(queryMobileConfigContentProvider);
            return z2;
        } catch (Exception e) {
            BLog.e(TAG, "Cannot retrieve MC value", e);
            return z;
        } finally {
            closeSilently(null);
        }
    }

    public long getLong(String str, long j) {
        return getLongWithOptions(str, j, MobileConfigOptions.NONE);
    }

    public long getLongWithOptions(String str, long j, MobileConfigOptions mobileConfigOptions) {
        try {
            if (this.mQpl != null) {
                this.mQpl.markerStart(645036435);
                this.mQpl.markerAnnotate(645036435, "FUNCTION_NAME", "getLong");
            }
            if (str.isEmpty()) {
                this.mLogger.logApiResponse("getLong", str, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                mobileConfigOptions.setValueSource(MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                return j;
            }
            MobileConfigOptions requestForValueSource = mobileConfigOptions.requestForValueSource();
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "BEFORE_SERVICE_REQUEST_SENT");
            }
            Cursor queryMobileConfigContentProvider = queryMobileConfigContentProvider(str, Long.toString(j), "long", requestForValueSource);
            if (queryMobileConfigContentProvider == null) {
                this.mLogger.logApiResponse("getLong", str, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                requestForValueSource.setValueSource(MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                closeSilently(queryMobileConfigContentProvider);
                return j;
            }
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "SERVICE_RESPONSE_RECEIVED");
            }
            long j2 = queryMobileConfigContentProvider.getLong(0);
            MobileConfigValueSource fromInt = MobileConfigValueSource.fromInt(queryMobileConfigContentProvider.getInt(1));
            requestForValueSource.setValueSource(fromInt);
            this.mLogger.logApiResponse("getLong", str, true, requestForValueSource.getValueSource().toString());
            if (this.mQpl != null) {
                this.mQpl.markerAnnotate(645036435, "VALUE_SOURCE", fromInt.getSource());
                this.mQpl.markerEnd(645036435, 2);
            }
            closeSilently(queryMobileConfigContentProvider);
            return j2;
        } catch (Exception e) {
            BLog.e(TAG, "Cannot retrieve MC value", e);
            return j;
        } finally {
            closeSilently(null);
        }
    }

    public String getString(String str, String str2) {
        return getStringWithOptions(str, str2, MobileConfigOptions.NONE);
    }

    public String getStringWithOptions(String str, String str2, MobileConfigOptions mobileConfigOptions) {
        try {
            if (this.mQpl != null) {
                this.mQpl.markerStart(645036435);
                this.mQpl.markerAnnotate(645036435, "FUNCTION_NAME", "getString");
            }
            if (str.isEmpty()) {
                this.mLogger.logApiResponse("getString", str, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                mobileConfigOptions.setValueSource(MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                return str2;
            }
            MobileConfigOptions requestForValueSource = mobileConfigOptions.requestForValueSource();
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "BEFORE_SERVICE_REQUEST_SENT");
            }
            Cursor queryMobileConfigContentProvider = queryMobileConfigContentProvider(str, str2, "string", requestForValueSource);
            if (queryMobileConfigContentProvider == null) {
                this.mLogger.logApiResponse("getString", str, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                requestForValueSource.setValueSource(MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                closeSilently(queryMobileConfigContentProvider);
                return str2;
            }
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "SERVICE_RESPONSE_RECEIVED");
            }
            String string = queryMobileConfigContentProvider.getString(0);
            MobileConfigValueSource fromInt = MobileConfigValueSource.fromInt(queryMobileConfigContentProvider.getInt(1));
            requestForValueSource.setValueSource(fromInt);
            this.mLogger.logApiResponse("getString", str, true, requestForValueSource.getValueSource().toString());
            if (this.mQpl != null) {
                this.mQpl.markerAnnotate(645036435, "VALUE_SOURCE", fromInt.getSource());
                this.mQpl.markerEnd(645036435, 2);
            }
            closeSilently(queryMobileConfigContentProvider);
            return string;
        } catch (Exception e) {
            BLog.e(TAG, "Cannot retrieve MC value", e);
            return str2;
        } finally {
            closeSilently(null);
        }
    }

    public double getDouble(String str, double d) {
        return getDoubleWithOptions(str, d, MobileConfigOptions.NONE);
    }

    public double getDoubleWithOptions(String str, double d, MobileConfigOptions mobileConfigOptions) {
        try {
            if (this.mQpl != null) {
                this.mQpl.markerStart(645036435);
                this.mQpl.markerAnnotate(645036435, "FUNCTION_NAME", "getDouble");
            }
            if (str.isEmpty()) {
                this.mLogger.logApiResponse("getDouble", str, false, MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                mobileConfigOptions.setValueSource(MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                return d;
            }
            MobileConfigOptions requestForValueSource = mobileConfigOptions.requestForValueSource();
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "BEFORE_SERVICE_REQUEST_SENT");
            }
            Cursor queryMobileConfigContentProvider = queryMobileConfigContentProvider(str, Double.toString(d), "double", requestForValueSource);
            if (queryMobileConfigContentProvider == null) {
                this.mLogger.logApiResponse("getDouble", str, false, MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                requestForValueSource.setValueSource(MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND);
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                closeSilently(queryMobileConfigContentProvider);
                return d;
            }
            if (this.mQpl != null) {
                this.mQpl.markerPoint(645036435, "SERVICE_RESPONSE_RECEIVED");
            }
            double d2 = queryMobileConfigContentProvider.getDouble(0);
            MobileConfigValueSource fromInt = MobileConfigValueSource.fromInt(queryMobileConfigContentProvider.getInt(1));
            requestForValueSource.setValueSource(fromInt);
            this.mLogger.logApiResponse("getDouble", str, true, requestForValueSource.getValueSource().toString());
            if (this.mQpl != null) {
                this.mQpl.markerAnnotate(645036435, "VALUE_SOURCE", fromInt.getSource());
                this.mQpl.markerEnd(645036435, 2);
            }
            closeSilently(queryMobileConfigContentProvider);
            return d2;
        } catch (Exception e) {
            BLog.e(TAG, "Cannot retrieve MC value", e);
            return d;
        } finally {
            closeSilently(null);
        }
    }

    public Map<String, ValueInfo> getMultiple(String[] strArr) {
        String str;
        ValueInfo valueInfo;
        HashMap hashMap = new HashMap();
        try {
            ContentProviderClient acquireUnstableContentProviderClient = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.GET_MULTIPLE_CONFIGS_URI);
            if (acquireUnstableContentProviderClient == null) {
                try {
                    BLog.e(TAG, "Content provider for the mobileconfig service not found");
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                    return hashMap;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                if (this.mQpl != null) {
                    this.mQpl.markerStart(645036435);
                    this.mQpl.markerAnnotate(645036435, "FUNCTION_NAME", "getMultiple");
                }
                Cursor query = acquireUnstableContentProviderClient.query(MobileConfigServiceConstants.GET_MULTIPLE_CONFIGS_URI, strArr, "", new String[0], null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            if (this.mQpl != null) {
                                this.mQpl.markerPoint(645036435, "SERVICE_RESPONSE_RECEIVED");
                            }
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
                                    } catch (NumberFormatException unused) {
                                    }
                                }
                                if (string2 == null) {
                                    BLog.e(TAG, "Null type for config param %s", string);
                                } else {
                                    if (string6 == null) {
                                        BLog.e(TAG, "Null query passed for config param %s", string);
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
                                                BLog.e(TAG, "Unrecognized type %s for config param %s", string2, string);
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
                                    } catch (NumberFormatException unused2) {
                                        BLog.e(TAG, "Unrecognized type %s for config param %s", string2, string);
                                    }
                                }
                            }
                            if (this.mQpl != null) {
                                this.mQpl.markerAnnotate(645036435, "EXTRA", (String[]) hashMap.keySet().toArray(new String[0]));
                                this.mQpl.markerEnd(645036435, 2);
                            }
                            if (query != null) {
                                query.close();
                            }
                            if (acquireUnstableContentProviderClient != null) {
                                acquireUnstableContentProviderClient.close();
                            }
                            return hashMap;
                        }
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                BLog.e(TAG, "no results returned for queryString %s", TextUtils.join(",", strArr));
                if (this.mQpl != null) {
                    this.mQpl.markerAnnotate(645036435, "FAILURE_CAUSE", MobileConfigValueSource.DEFAULT__SERVICE_NOT_FOUND.toString());
                    this.mQpl.markerEnd(645036435, 3);
                }
                if (query != null) {
                    query.close();
                }
                if (acquireUnstableContentProviderClient != null) {
                    acquireUnstableContentProviderClient.close();
                }
                return hashMap;
            }
            throw th;
            throw th;
        } catch (RemoteException | SecurityException e) {
            BLog.e(TAG, "Could not find mobileconfigservice; is the service running?", e);
            return hashMap;
        }
    }

    public void logExposure(ValueInfo valueInfo) {
        String loggingId = valueInfo.getLoggingId();
        if (!TextUtils.isEmpty(loggingId)) {
            logExposure(loggingId, valueInfo.getIsSessionless());
        }
    }

    public void logExposure(String str, boolean z) {
        try {
            ContentProviderClient acquireUnstableContentProviderClient = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.LOG_EXPOSURE_URI);
            if (acquireUnstableContentProviderClient == null) {
                try {
                    BLog.e(TAG, "Content provider for the mobileconfig service not found");
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                String[] strArr = new String[1];
                strArr[0] = z ? "1" : "0";
                closeSilently(acquireUnstableContentProviderClient.query(MobileConfigServiceConstants.LOG_EXPOSURE_URI, strArr, str, new String[0], null));
                if (acquireUnstableContentProviderClient != null) {
                    acquireUnstableContentProviderClient.close();
                    return;
                }
                return;
            }
            throw th;
        } catch (RemoteException | SecurityException e) {
            BLog.e(TAG, "Could not find mobileconfigservice; is the service running?", e);
        }
    }

    public void addChangeListener(String str, IMobileConfigChangeListener iMobileConfigChangeListener) {
        synchronized (this.mMobileConfigContentObservers) {
            if (getObserver(str, iMobileConfigChangeListener) != null) {
                this.mLogger.logApiResponse("addChangeListener", str, true, "Already added");
                return;
            }
            MobileConfigContentObserver mobileConfigContentObserver = new MobileConfigContentObserver(str, iMobileConfigChangeListener, this.mContext);
            this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(Uri.withAppendedPath(MobileConfigServiceConstants.CHANGE_LISTENER_PATH, str), getProcessName()), false, mobileConfigContentObserver);
            boolean z = !containsOtherObserverForConfig(str);
            if (!this.mMobileConfigContentObservers.containsKey(str)) {
                this.mMobileConfigContentObservers.put(str, new ArrayList());
            }
            this.mMobileConfigContentObservers.get(str).add(mobileConfigContentObserver);
            if (!z) {
                this.mLogger.logApiResponse("addChangeListener", str, true, "Already added in server");
                return;
            }
            try {
                ContentProviderClient acquireUnstableContentProviderClient = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.CHANGE_LISTENER_PATH);
                if (acquireUnstableContentProviderClient == null) {
                    try {
                        BLog.e(TAG, "Content provider for the mobileconfig service not found");
                        this.mLogger.logApiResponse("addChangeListener", str, false, "Service not found");
                        if (acquireUnstableContentProviderClient != null) {
                            acquireUnstableContentProviderClient.close();
                        }
                        return;
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                } else {
                    closeSilently(acquireUnstableContentProviderClient.query(MobileConfigServiceConstants.CHANGE_LISTENER_PATH, new String[0], str, new String[0], getProcessName()));
                    this.mLogger.logApiResponse("addChangeListener", str, true, "");
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                    return;
                }
            } catch (RemoteException | SecurityException e) {
                this.mLogger.logApiResponse("addChangeListener", str, false, "Service not running");
                BLog.e(TAG, "Could not find mobileconfigservice; is the service running?", e);
            }
        }
        throw th;
    }

    public void removeChangeListener(String str, IMobileConfigChangeListener iMobileConfigChangeListener) {
        synchronized (this.mMobileConfigContentObservers) {
            MobileConfigContentObserver observer = getObserver(str, iMobileConfigChangeListener);
            if (observer == null) {
                this.mLogger.logApiResponse("removeChangeListener", str, true, "Already removed");
                return;
            }
            if (this.mMobileConfigContentObservers.containsKey(str)) {
                List<MobileConfigContentObserver> list = this.mMobileConfigContentObservers.get(str);
                list.remove(observer);
                if (list.isEmpty()) {
                    this.mMobileConfigContentObservers.remove(str);
                }
            }
            this.mContext.getContentResolver().unregisterContentObserver(observer);
            if (containsOtherObserverForConfig(str)) {
                this.mLogger.logApiResponse("removeChangeListener", str, true, "");
                return;
            }
            try {
                ContentProviderClient acquireUnstableContentProviderClient = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.CHANGE_LISTENER_REMOVE_PATH);
                try {
                    closeSilently(acquireUnstableContentProviderClient.query(MobileConfigServiceConstants.CHANGE_LISTENER_REMOVE_PATH, new String[0], str, new String[0], getProcessName()));
                    this.mLogger.logApiResponse("removeChangeListener", str, true, "");
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                    return;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } catch (RemoteException e) {
                this.mLogger.logApiResponse("removeChangeListener", str, false, "Service not running");
                BLog.e(TAG, "Could not find mobileconfigservice; is the service running?", e);
            }
        }
        throw th;
    }

    private boolean containsOtherObserverForConfig(String str) {
        return this.mMobileConfigContentObservers.containsKey(str) && this.mMobileConfigContentObservers.get(str).size() > 0;
    }

    @Nullable
    private MobileConfigContentObserver getObserver(String str, IMobileConfigChangeListener iMobileConfigChangeListener) {
        if (!this.mMobileConfigContentObservers.containsKey(str)) {
            return null;
        }
        for (MobileConfigContentObserver mobileConfigContentObserver : this.mMobileConfigContentObservers.get(str)) {
            if (mobileConfigContentObserver.mConfigChangeListener.equals(iMobileConfigChangeListener)) {
                return mobileConfigContentObserver;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static class MobileConfigContentObserver extends ContentObserver {
        private final IMobileConfigChangeListener mConfigChangeListener;
        private final String mConfigName;

        public MobileConfigContentObserver(String str, IMobileConfigChangeListener iMobileConfigChangeListener, Context context) {
            super(new Handler(context.getMainLooper()));
            this.mConfigName = str;
            this.mConfigChangeListener = iMobileConfigChangeListener;
        }

        public void onChange(boolean z, Uri uri) {
            super.onChange(z);
            this.mConfigChangeListener.onConfigChanged(this.mConfigName);
        }
    }

    public Cursor queryMobileConfigContentProvider(String str, String str2, String str3, MobileConfigOptions mobileConfigOptions) {
        try {
            ContentProviderClient acquireUnstableContentProviderClient = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.CONTENT_URI_GET_PATH);
            if (acquireUnstableContentProviderClient == null) {
                try {
                    BLog.e(TAG, "Content provider for the mobileconfig service not found");
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                    return null;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                String[] strArr = new String[1];
                strArr[0] = mobileConfigOptions.isValueSourceRequested() ? MobileConfigServiceConstants.REQUEST_VALUE_SOURCE : "";
                Cursor query = acquireUnstableContentProviderClient.query(MobileConfigServiceConstants.CONTENT_URI_GET_PATH, new String[]{str3, str2}, str, strArr, null);
                if (query == null || !query.moveToFirst()) {
                    BLog.e(TAG, "no results returned for %s", str);
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                    return null;
                }
                if (acquireUnstableContentProviderClient != null) {
                    acquireUnstableContentProviderClient.close();
                }
                return query;
            }
            throw th;
        } catch (RemoteException | SecurityException e) {
            BLog.e(TAG, "Could not find mobileconfigservice; is the service running?", e);
            return null;
        }
    }

    public String getParamsMapContent() {
        AssetManager assets = this.mContext.getAssets();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assets.open("params_map.txt")));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append('\n');
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            BLog.e(TAG, "IOException while trying to read params map", e);
        }
        return sb.toString();
        throw th;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private IMobileConfig getService() throws RemoteException {
        Log.d(TAG, "getService()");
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
    @Nullable
    private String getProcessName() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        try {
            return (String) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentProcessName", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            BLog.e(TAG, "Could not retrieve process name, package name will be used instead.", e);
            return this.mContext.getPackageName();
        }
    }

    private void internalSubscribe(final String str) {
        BLog.i(TAG, "Start thread to subscribe with paramsMapContent file descriptor from mobileconfig service");
        new Thread(new Runnable() {
            /* class com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient.AnonymousClass2 */

            public void run() {
                try {
                    if (MobileConfigBaseClient.this.mQpl != null) {
                        MobileConfigBaseClient.this.mQpl.markerPoint(645021198, "BEFORE_SERVICE_REQUEST_SENT");
                    }
                    MobileConfigBaseClient.this.getService().subscribeWithProcessName(str, MobileConfigBaseClient.this.getProcessName(), Process.myPid());
                } catch (RemoteException e) {
                    BLog.e(MobileConfigBaseClient.TAG, "Remote exception while connecting to mc service", e);
                } catch (Exception e2) {
                    BLog.e(MobileConfigBaseClient.TAG, "Generic exception while connecting to mc service", e2);
                }
            }
        }).start();
    }

    @SuppressLint({"CatchGeneralException"})
    private void closeSilently(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public class McConnection implements ServiceConnection {
        private final CountDownLatch mLatch;
        @Nullable
        private volatile IMobileConfig mService;
        private final String mServiceAppPackage;

        private McConnection(String str) {
            this.mLatch = new CountDownLatch(1);
            this.mServiceAppPackage = str;
        }

        public void connect() {
            Log.d(MobileConfigBaseClient.TAG, "connect()");
            ComponentName componentName = new ComponentName(this.mServiceAppPackage, MobileConfigBaseClient.SERVICE_CLS);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            if (!MobileConfigBaseClient.this.mContext.bindService(intent, this, 1)) {
                throw new RuntimeException("Error calling bindService to MobileConfigService.");
            }
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(MobileConfigBaseClient.TAG, "Connected to MobileConfigService");
            this.mService = IMobileConfig.Stub.asInterface(iBinder);
            this.mLatch.countDown();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(MobileConfigBaseClient.TAG, "Disconnected from MobileConfigService");
        }

        public void awaitService() {
            Log.d(MobileConfigBaseClient.TAG, "awaitService()");
            try {
                this.mLatch.await(30, TimeUnit.SECONDS);
                if (this.mService == null) {
                    Log.e(MobileConfigBaseClient.TAG, "Timed out trying to connect to MobileConfigService.");
                }
            } catch (InterruptedException e) {
                Log.e(MobileConfigBaseClient.TAG, "Interrupted Exception during connecting to service", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
