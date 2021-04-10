package com.oculus.horizon.library.push;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import X.C003108z;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.google.common.collect.ImmutableSet;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.horizon.notifications.legacy.contract.NotificationsContract;
import com.oculus.horizon.platformsdk.DeeplinkIntentUtils;
import com.oculus.horizon.push.FbnsPushHandler;
import com.oculus.horizon.remotelaunchlogger.RemoteLaunchLogger;
import com.oculus.library.model.App;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_platformsdk_DeeplinkIntentUtils_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_remotelaunchlogger_RemoteLaunchLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID"})
public class OVRLibraryPushHandler implements FbnsPushHandler {
    public static final ImmutableSet<String> HANDLED_TYPES = ImmutableSet.A06(4, "app_start", TYPE_APP_INSTALL, TYPE_APP_UNINSTALL, TYPE_LIBRARY_REFRESH);
    public static final String KEY_APP_ID = "app_id";
    public static final String KEY_DEEPLINK_MESSAGE = "deeplink_message";
    public static final String KEY_EXTRA_DATA = "extra_data";
    public static final String KEY_PACKAGE_NAME = "package_name";
    public static final String KEY_PARAMS = "params";
    public static final String REMOTE_LAUNCH_LOGGING_SOURCE = "app_start";
    public static final String TAG = "OVRLibraryPushHandler";
    public static final String TYPE_APP_INSTALL = "app_install";
    public static final String TYPE_APP_START = "app_start";
    public static final String TYPE_APP_UNINSTALL = "app_uninstall";
    public static final String TYPE_LIBRARY_REFRESH = "oc_library_update";
    public AnonymousClass0QC _UL_mInjectionContext;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Inject
    @Eager
    public final OVRLibrary mOVRLibrary;

    @Override // com.oculus.horizon.push.FbnsPushHandler
    public final void A6M(String str, JSONObject jSONObject) {
        try {
            switch (str.hashCode()) {
                case 164615832:
                    if (str.equals(TYPE_LIBRARY_REFRESH)) {
                        this.mOVRLibrary.A05(null);
                        return;
                    }
                    return;
                case 704091517:
                    if (str.equals(TYPE_APP_INSTALL)) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("params");
                        if (jSONObject2 == null) {
                            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A96(TAG, "Missing params");
                            return;
                        }
                        String string = jSONObject2.getString("app_id");
                        final String string2 = jSONObject2.getString("package_name");
                        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A96(TAG, "Missing app_id or package_name");
                            return;
                        } else if (this.mOVRLibrary.A01(string2) == null) {
                            this.mOVRLibrary.A05(new ResultReceiver() {
                                /* class com.oculus.horizon.library.push.OVRLibraryPushHandler.AnonymousClass1 */

                                public final void onReceiveResult(int i, Bundle bundle) {
                                    OVRLibraryPushHandler oVRLibraryPushHandler = OVRLibraryPushHandler.this;
                                    String str = string2;
                                    if (i != 0) {
                                        AnonymousClass0NO.A0E(OVRLibraryPushHandler.TAG, "Failed to refetch library, cannot install %s", str);
                                    } else if (oVRLibraryPushHandler.mOVRLibrary.A01(str) == null) {
                                        ((IErrorReporter) AnonymousClass0J2.A03(0, 428, oVRLibraryPushHandler._UL_mInjectionContext)).A96(OVRLibraryPushHandler.TAG, "Missing entitlement");
                                    } else {
                                        OVRLibraryPushHandler.A00(oVRLibraryPushHandler, str);
                                    }
                                }
                            });
                            return;
                        } else {
                            A00(this, string2);
                            return;
                        }
                    } else {
                        return;
                    }
                case 1667442756:
                    if (str.equals(TYPE_APP_UNINSTALL)) {
                        JSONObject jSONObject3 = jSONObject.getJSONObject("params");
                        if (jSONObject3 == null) {
                            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A96(TAG, "Missing params");
                            return;
                        }
                        String string3 = jSONObject3.getString("app_id");
                        String string4 = jSONObject3.getString("package_name");
                        if (string3 == null || string4 == null) {
                            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A96(TAG, "Missing app_id or package_name");
                        }
                        OVRLibrary oVRLibrary = this.mOVRLibrary;
                        RequestOrigin requestOrigin = RequestOrigin.REMOTE_UNINSTALL;
                        try {
                            if (oVRLibrary.mContext.getPackageManager().getPackageInfo(string4, 0) != null) {
                                Bundle bundle = new Bundle();
                                bundle.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, requestOrigin.name());
                                oVRLibrary.mContext.getContentResolver().call(OCMSLibraryContract.A00(), OCMSLibraryContract.METHOD_UNINSTALL, string4, bundle);
                                return;
                            }
                            return;
                        } catch (PackageManager.NameNotFoundException unused) {
                            return;
                        }
                    } else {
                        return;
                    }
                case 1842529476:
                    if (str.equals("app_start") && ((AnonymousClass0Rg) AnonymousClass0J2.A03(2, 399, this._UL_mInjectionContext)).A36(36310310651953202L)) {
                        JSONObject jSONObject4 = jSONObject.getJSONObject("params");
                        if (jSONObject4 == null) {
                            ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A02("app_start", "null", false, "Missing params");
                            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A96(TAG, "Missing params");
                            return;
                        }
                        String string5 = jSONObject4.getString("app_id");
                        String string6 = jSONObject4.getString("package_name");
                        if (TextUtils.isEmpty(string5) || TextUtils.isEmpty(string6)) {
                            ((RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext)).A02("app_start", string5, false, "Missing app_id or package_name");
                            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A96(TAG, "Missing app_id or package_name");
                            return;
                        }
                        String optString = jSONObject4.optString("extra_data");
                        String str2 = null;
                        if (optString != null) {
                            try {
                                str2 = new JSONObject(optString).optString("deeplink_message", null);
                            } catch (JSONException e) {
                                AnonymousClass0NO.A0B(TAG, "Cannot parse the extra data", e);
                            }
                        }
                        boolean z = true;
                        try {
                            Intent A02 = ((DeeplinkIntentUtils) AnonymousClass0J2.A03(1, 66, this._UL_mInjectionContext)).A02(string6, DeeplinkIntentUtils.A00(str2));
                            if (A02 != null) {
                                try {
                                    ((DeeplinkIntentUtils) AnonymousClass0J2.A03(1, 66, this._UL_mInjectionContext)).A03(A02, this.mContext);
                                    RemoteLaunchLogger remoteLaunchLogger = (RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext);
                                    boolean z2 = false;
                                    if (str2 != null) {
                                        z2 = true;
                                    }
                                    Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, remoteLaunchLogger._UL_mInjectionContext)).A22(RemoteLaunchLogger.EVENT_LAUNCH_SUCCESS);
                                    A22.A15("source", "app_start");
                                    A22.A15(RemoteLaunchLogger.KEY_APP_ID_LAUNCHED, string5);
                                    A22.A16(RemoteLaunchLogger.KEY_HAS_DEEPLINK_MESSAGE, z2);
                                    A22.A5L();
                                    return;
                                } catch (SecurityException e2) {
                                    RemoteLaunchLogger remoteLaunchLogger2 = (RemoteLaunchLogger) AnonymousClass0J2.A03(3, NotificationsContract.EVENT_NOTIFICATION_ID, this._UL_mInjectionContext);
                                    if (str2 == null) {
                                        z = false;
                                    }
                                    remoteLaunchLogger2.A02("app_start", string5, z, "App launch failed");
                                    ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A97(TAG, "App launch failed", e2);
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (JSONException e3) {
                            AnonymousClass0NO.A0B(DeeplinkIntentUtils.TAG, "Failed to parse arguments", e3);
                            return;
                        }
                    } else {
                        return;
                    }
                default:
                    return;
            }
        } catch (JSONException e4) {
            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A97(TAG, "Invalid notification JSON", e4);
        }
    }

    public static final void A00(OVRLibraryPushHandler oVRLibraryPushHandler, String str) {
        OVRLibrary oVRLibrary = oVRLibraryPushHandler.mOVRLibrary;
        App.Editor editor = new App.Editor(str);
        editor.recentActivityMs = Optional.of(Long.valueOf(System.currentTimeMillis()));
        oVRLibrary.A06(editor);
        OVRLibrary oVRLibrary2 = oVRLibraryPushHandler.mOVRLibrary;
        RequestOrigin requestOrigin = RequestOrigin.REMOTE_INSTALL;
        Bundle bundle = new Bundle();
        bundle.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, requestOrigin.name());
        oVRLibrary2.mContext.getContentResolver().call(OCMSLibraryContract.A00(), OCMSLibraryContract.METHOD_INSTALL_PACKAGE, str, bundle);
    }

    @Inject
    public OVRLibraryPushHandler(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(5, r3);
        this.mOVRLibrary = OVRLibraryModule.A00(r3);
        this.mContext = C003108z.A02(r3);
    }

    @Override // com.oculus.horizon.push.FbnsPushHandler
    public final Set<String> A3T() {
        return HANDLED_TYPES;
    }
}
