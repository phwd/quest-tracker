package com.oculus.horizon.service;

import X.AbstractC06640p5;
import X.AnonymousClass0CC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import com.facebook.ultralight.Eager;
import com.google.common.collect.ImmutableSet;
import com.oculus.horizon.platform.PresenceManager;
import com.oculus.horizon.service.ExternalPlatformLocal;
import com.oculus.library.model.App;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.logging.sdk_logging.SDKLoggingInterface;
import com.oculus.security.basecomponent.OculusPublicService;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

public class SDKLoggingService extends OculusPublicService {
    public static final ImmutableSet EXCLUDED_APP_ID;
    public static final String JSON_NUM_SECONDS = "num_seconds";
    public static final String TAG = "SDKLoggingService";
    public final SDKLoggingInterface.Stub mBinder = new SDKLoggingInterface.Stub() {
        /* class com.oculus.horizon.service.SDKLoggingService.AnonymousClass1 */

        private final boolean isStandaloneDevice() {
            return SDKLoggingService.this.getPackageManager().hasSystemFeature("oculus.hardware.standalone_vr");
        }

        private String getPackageName() {
            int callingUid = Binder.getCallingUid();
            if (isStandaloneDevice() && callingUid == 1000) {
                return "com.oculus.telemetry";
            }
            String[] packagesForUid = SDKLoggingService.this.getPackageManager().getPackagesForUid(callingUid);
            for (String str : packagesForUid) {
                if ("com.oculus.home".equals(str)) {
                    return str;
                }
            }
            return packagesForUid[0];
        }

        @Override // com.oculus.logging.sdk_logging.SDKLoggingInterface
        public void log(String str) {
            String packageName = getPackageName();
            String appID = SDKLoggingService.this.mExternalPlatformLocal.getAppID(packageName);
            try {
                long optLong = new JSONObject(str).optLong(SDKLoggingService.JSON_NUM_SECONDS);
                if (optLong > 0) {
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    App A02 = SDKLoggingService.this.mOVRLibrary.A02(appID);
                    if (A02 != null) {
                        long millis = A02.totalActivityMs + TimeUnit.SECONDS.toMillis(optLong);
                        OVRLibrary oVRLibrary = SDKLoggingService.this.mOVRLibrary;
                        App.Editor editor = new App.Editor(A02.packageName);
                        editor.totalActivityMs = Optional.of(Long.valueOf(millis));
                        oVRLibrary.A06(editor);
                    } else {
                        AnonymousClass0NO.A0F(SDKLoggingService.TAG, "Unable to update activity ms %s, not in library.", packageName);
                    }
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            } catch (JSONException e) {
                try {
                    AnonymousClass0NO.A0K(SDKLoggingService.TAG, e, "Unable to parse json content (%s)", str);
                } catch (ExternalPlatformLocal.PackageNotInLibraryException | IOException unused) {
                }
            }
            SDKLoggingService.this.mLogger.reportSDKLogs(appID, packageName, str);
            SDKLoggingService.this.mPresenceManager.logWasCalled(packageName, appID);
        }

        @Override // com.oculus.logging.sdk_logging.SDKLoggingInterface
        public void reportEvent(String str, String str2) {
            String packageName = getPackageName();
            String str3 = null;
            if (!"com.oculus.telemetry".equals(packageName) || !ServiceLogger.EVENT_SDK_LOGGING.equals(str)) {
                try {
                    str3 = SDKLoggingService.this.mExternalPlatformLocal.getAppID(packageName);
                } catch (ExternalPlatformLocal.PackageNotInLibraryException | IOException e) {
                    AnonymousClass0NO.A0F(SDKLoggingService.TAG, "Unable to find AppId for package (%s)", packageName, e);
                }
                SDKLoggingService.this.mLogger.reportSDKEvent(str, str3, packageName, str2);
                SDKLoggingService.this.mPresenceManager.logWasCalled(packageName, str3);
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str2);
                String string = jSONObject.getString(ServiceLogger.KEY_SDK_APP_PACKAGE);
                if (!TextUtils.isEmpty(string)) {
                    String string2 = jSONObject.getString(ServiceLogger.KEY_SDK_APP_ID);
                    if (TextUtils.isEmpty(string2) || SDKLoggingService.EXCLUDED_APP_ID.contains(string2)) {
                        string2 = null;
                    }
                    SDKLoggingService.this.mPresenceManager.logWasCalled(string, string2);
                    return;
                }
                AnonymousClass0NO.A08(SDKLoggingService.TAG, "reportEvent: failed to call presence manager because appPackageName was null or empty");
            } catch (JSONException e2) {
                AnonymousClass0NO.A0E(SDKLoggingService.TAG, "reportEvent: Unable to parse content for event %s", str, e2);
            }
        }

        @Override // com.oculus.logging.sdk_logging.SDKLoggingInterface
        public void reportLowLatencyEvent(String str, String str2) {
            String str3;
            String packageName = getPackageName();
            try {
                str3 = SDKLoggingService.this.mExternalPlatformLocal.getAppID(packageName);
            } catch (ExternalPlatformLocal.PackageNotInLibraryException | IOException e) {
                AnonymousClass0NO.A0K(SDKLoggingService.TAG, e, "Unable to find AppId for package (%s)", packageName);
                str3 = null;
            }
            SDKLoggingService.this.mLogger.reportLowLatencySDKEvent(str, str3, packageName, str2);
            SDKLoggingService.this.mPresenceManager.logWasCalled(packageName, str3);
        }
    };
    @Inject
    @Eager
    public ExternalPlatformLocal mExternalPlatformLocal;
    @Inject
    @Eager
    public ServiceLogger mLogger;
    @Inject
    @Eager
    public OVRLibrary mOVRLibrary;
    @Inject
    @Eager
    public PresenceManager mPresenceManager;

    static {
        AnonymousClass0CC r1 = new AnonymousClass0CC();
        r1.A04("0");
        r1.A04("1000");
        EXCLUDED_APP_ID = r1.build();
    }

    public static final void _UL_injectMe(Context context, SDKLoggingService sDKLoggingService) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), sDKLoggingService);
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r1, SDKLoggingService sDKLoggingService) {
        sDKLoggingService.mExternalPlatformLocal = ExternalPlatformLocal._UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_ACCESS_METHOD(r1);
        sDKLoggingService.mPresenceManager = PresenceManager._UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_ACCESS_METHOD(r1);
        sDKLoggingService.mOVRLibrary = OVRLibraryModule.A00(r1);
        sDKLoggingService.mLogger = ServiceLogger._UL__ULSEP_com_oculus_horizon_service_ServiceLogger_ULSEP_ACCESS_METHOD(r1);
    }

    public static /* synthetic */ String access$200() {
        return TAG;
    }

    @Override // X.AnonymousClass1U9, com.oculus.security.basecomponent.OculusPublicService
    public void doCreate() {
        super.doCreate();
        _UL_injectMe((Context) this, this);
    }

    @Override // X.AnonymousClass1U9
    public IBinder doBind(Intent intent) {
        return this.mBinder;
    }
}
