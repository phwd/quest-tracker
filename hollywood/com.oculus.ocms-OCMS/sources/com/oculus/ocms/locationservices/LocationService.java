package com.oculus.ocms.locationservices;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Binder;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.common.util.exception.ExceptionUtil;
import com.facebook.debug.log.BLog;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.facebook.wifiscan.WifiScan;
import com.facebook.wifiscan.WifiScanOperation;
import com.facebook.wifiscan.WifiScanOperationParams;
import com.facebook.wifiscan.WifiScanResult;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.oculus.aidl.ILocationCallback;
import com.oculus.aidl.ILocationServiceInterface;
import com.oculus.executors.ExecutorsModule;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.http.core.base.ApiException;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.ocms.locationservices.LocationServiceModule;
import com.oculus.ocms.locationservices.graphql.ApiDispatcher;
import com.oculus.ocms.locationservices.graphql.LocationGraphQLResponse;
import com.oculus.ocms.locationservices.graphql.LocationWifiGraphQLParams;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LocationService extends Service {
    private static final String BINDER_PACKAGENAME = "binder_package_name";
    private static final String EVENT_LOCATION_SERVICE = "oculus_mobile_location_service";
    private static final String KEY_ERROR = "error";
    private static final String KEY_FETCH_LOCATION = "fetch_location_type";
    private static final String SYSTEM_UID_PACKAGE = "system";
    private static final String TAG = "LocationService";
    private static final long WIFI_SCAN_TIMEOUT = 10000;
    private InjectionContext _UL_mInjectionContext;
    @Inject
    @Eager
    private ApiDispatcher mApiDispatcher;
    private final ILocationServiceInterface.Stub mBinder = new ILocationServiceInterface.Stub() {
        /* class com.oculus.ocms.locationservices.LocationService.AnonymousClass1 */

        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocation(ILocationCallback iLocationCallback) {
            LocationService.this.fetchLocation(iLocationCallback, false, false, 0);
        }

        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocationUsingIPOnly(ILocationCallback iLocationCallback) {
            LocationService.this.fetchLocation(iLocationCallback, true, false, 1);
        }

        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocationWithTimezoneUsingIPOnly(ILocationCallback iLocationCallback) {
            LocationService.this.fetchLocation(iLocationCallback, true, true, 2);
        }

        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocationByType(ILocationCallback iLocationCallback, @FetchLocationType int i) {
            boolean z = false;
            boolean z2 = i == 1 || i == 2;
            if (i == 2) {
                z = true;
            }
            LocationService.this.fetchLocation(iLocationCallback, z2, z, i);
        }
    };

    private static final void _UL_injectMe(Context context, LocationService locationService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), locationService);
        } else {
            FbInjector.injectMe(LocationService.class, locationService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, LocationService locationService) {
        locationService._UL_mInjectionContext = new InjectionContext(5, injectorLike);
        locationService.mApiDispatcher = ApiDispatcher._UL__ULSEP_com_oculus_ocms_locationservices_graphql_ApiDispatcher_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void reportLocation(ILocationCallback iLocationCallback, @Nullable Location location, @Nullable Throwable th, @FetchLocationType int i) {
        BLog.d(TAG, "reportLocation: called.");
        try {
            logLocation(i, th);
            if (location != null) {
                iLocationCallback.report(location);
            }
        } catch (DeadObjectException e) {
            BLog.e(TAG, "DeadObjectException while returning location.", e);
            logLocation(i, e);
        } catch (RemoteException e2) {
            BLog.e(TAG, "RemoteException: while returning location.", e2);
            logLocation(i, e2);
        }
    }

    private String getAppName() {
        int callingUid = Binder.getCallingUid();
        PackageManager packageManager = ((Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getPackageManager();
        String[] packagesForUid = packageManager != null ? packageManager.getPackagesForUid(callingUid) : null;
        if (packagesForUid == null) {
            return "";
        }
        if (callingUid == 1000) {
            return SYSTEM_UID_PACKAGE;
        }
        return TextUtils.join(",", packagesForUid);
    }

    private void logLocation(@FetchLocationType int i, @Nullable Throwable th) {
        ((EventManager) FbInjector.lazyInstance(1, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(EVENT_LOCATION_SERVICE).addExtra(KEY_FETCH_LOCATION, fetchLocationTypeToString(i)).addExtra("error", th == null ? null : ExceptionUtil.getStackTraceString(th)).addExtra(BINDER_PACKAGENAME, getAppName()).logAndRelease();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void fetchLocation(final ILocationCallback iLocationCallback, boolean z, boolean z2, @FetchLocationType final int i) {
        AnonymousClass2 r2 = new FutureCallback<Location>() {
            /* class com.oculus.ocms.locationservices.LocationService.AnonymousClass2 */

            public void onSuccess(@Nullable Location location) {
                LocationService.this.reportLocation(iLocationCallback, location, null, i);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(@Nonnull Throwable th) {
                LocationService.this.reportLocation(iLocationCallback, null, th, i);
            }
        };
        if (!z) {
            startWifiScanAndGetLocation(r2, z2, i != 3);
        } else {
            ursaGraphQLQuery(new ArrayList(), r2, false, true, z2);
        }
    }

    private void startWifiScanAndGetLocation(final FutureCallback<Location> futureCallback, final boolean z, final boolean z2) {
        BLog.d(TAG, "startWifiScanAndGetLocation: called.");
        WifiScanOperationParams withTimeout = WifiScanOperationParams.withTimeout(10000);
        WifiScanOperation newWifiScanOperation = ((WifiScan) FbInjector.lazyInstance(2, LocationServiceModule.UL_id._UL__ULSEP_com_facebook_wifiscan_WifiScan_ULSEP_BINDING_ID, this._UL_mInjectionContext)).newWifiScanOperation();
        newWifiScanOperation.start(withTimeout);
        Futures.addCallback(newWifiScanOperation, new FutureCallback<List<WifiScanResult>>() {
            /* class com.oculus.ocms.locationservices.LocationService.AnonymousClass3 */

            public void onSuccess(@Nullable List<WifiScanResult> list) {
                LocationService.this.ursaGraphQLQuery(list, futureCallback, z2, false, z);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(@Nonnull Throwable th) {
                BLog.d(LocationService.TAG, "onFailure: Throwable", th);
                futureCallback.onFailure(th);
            }
        }, ((OculusThreadExecutor) FbInjector.lazyInstance(4, ExecutorsModule.UL_id._UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getExecutorService());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void ursaGraphQLQuery(@Nullable final List<WifiScanResult> list, final FutureCallback<Location> futureCallback, final boolean z, final boolean z2, final boolean z3) {
        BLog.d(TAG, "ursaGraphQLQuery: called.");
        ((OculusThreadExecutor) FbInjector.lazyInstance(4, ExecutorsModule.UL_id._UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID, this._UL_mInjectionContext)).execute(new Runnable() {
            /* class com.oculus.ocms.locationservices.LocationService.AnonymousClass4 */

            public void run() {
                try {
                    LocationService.this.mApiDispatcher.getLocationFromUrsa(new LocationWifiGraphQLParams(((WifiScan) FbInjector.lazyInstance(2, LocationServiceModule.UL_id._UL__ULSEP_com_facebook_wifiscan_WifiScan_ULSEP_BINDING_ID, LocationService.this._UL_mInjectionContext)).getConnectedWifi(), list, z, z2).getJSONParams(), z3, new Callback<LocationGraphQLResponse>() {
                        /* class com.oculus.ocms.locationservices.LocationService.AnonymousClass4.AnonymousClass1 */

                        public void success(LocationGraphQLResponse locationGraphQLResponse, Response response) {
                            if (locationGraphQLResponse != null) {
                                futureCallback.onSuccess(locationGraphQLResponse.createLocationWithElapsedRealTimeNanos(((LocationServiceModule.OcmsMonotonicNanoClock) FbInjector.lazyInstance(3, LocationServiceModule.UL_id._UL__ULSEP_com_oculus_ocms_locationservices_LocationServiceModule_OcmsMonotonicNanoClock_ULSEP_BINDING_ID, LocationService.this._UL_mInjectionContext)).nowNanos()));
                            } else {
                                futureCallback.onFailure(new Throwable("Ursa API bad response."));
                            }
                        }

                        @Override // retrofit.Callback
                        public void failure(RetrofitError retrofitError) {
                            futureCallback.onFailure(retrofitError.getCause());
                        }
                    });
                } catch (ApiException e) {
                    BLog.e(LocationService.TAG, "ApiDispatcher call failed.", e);
                    futureCallback.onFailure(e.getCause());
                }
            }
        });
    }

    private static String fetchLocationTypeToString(@FetchLocationType int i) {
        if (i == 0) {
            return "NO_IP_NO_TIMEZONE";
        }
        if (i == 1) {
            return "IP_NO_TIMEZONE";
        }
        if (i != 2) {
            return i != 3 ? StringFormatUtil.formatStrLocaleSafe("UNKNOWN_REQUEST_TYPE_%d", Integer.valueOf(i)) : "PRECISE";
        }
        return "IP_AND_TIMEZONE";
    }
}
