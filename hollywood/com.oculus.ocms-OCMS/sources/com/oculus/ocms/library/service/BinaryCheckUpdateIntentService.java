package com.oculus.ocms.library.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.facebook.acra.config.StartupBlockingConfig;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.context.SecureContext;
import com.facebook.secure.uriparser.SecureUriParser;
import com.facebook.ultralight.UL;
import com.oculus.common.build.BuildConstants;
import com.oculus.common.vrshell.Constants;
import com.oculus.common.vrshell.SystemUXRoute;
import com.oculus.library.model.App;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.ocms.library.provider.contract.TrustedBinaryContract;
import com.oculus.security.basecomponent.OculusPublicIntentService;

public class BinaryCheckUpdateIntentService extends OculusPublicIntentService {
    private static final String ACTION = "com.oculus.ocms.BINARY_CHECK_UPDATE";
    private static final int DIALOG_PROMPT_WINDOW_MS = 3000;
    private static final String EVENT_SUBTYPE = "event_subtype";
    private static final int INSTALL_CHECK_TIME_WINDOW_MS = 10000;
    private static final String NO_PACKAGE_NAME = "ocms_intent_service_received_no_package_name";
    private static final String NO_RESULT = "ocms_intent_service_received_no_result";
    private static final String NULL_EXTRAS = "ocms_intent_service_received_null_extras";
    private static final String ORIGINAL_SCHEDULE_TIME = "original_schedule_time";
    private static final String PACKAGE_NAME = "package_name";
    private static final String PACKAGE_NOT_INSTALLED = "ocms_intent_service_package_not_installed";
    private static final String PACKAGE_NOT_IN_LIBRARY = "ocms_intent_service_package_not_library";
    private static final String RESULT = "result";
    private static final String SEND_NOTIFICATION = "ocms_intent_service_package_send_notification";
    private static final String STORE_RESULT_IN_LIBRARY = "ocms_intent_service_package_store_result_in_library";
    private static final String TAG = "BinaryCheckUpdateIntentService";
    private static final String TELEMETRY_EVENT_NAME = "oculus_unofficial_binary_check";
    private static final String TIME_SINCE_SCHEDULED_MS = "time_since_scheduled_ms";
    private static final String TRY_OPEN_DIALOG = "ocms_intent_service_package_send_notification";
    private static final String UNSUPPORTED_ACTION = "ocms_intent_service_got_unsupported_action";
    private InjectionContext _UL_mInjectionContext;

    private static final void _UL_injectMe(Context context, BinaryCheckUpdateIntentService binaryCheckUpdateIntentService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe((InjectorLike) FbInjector.get(context), binaryCheckUpdateIntentService);
        } else {
            FbInjector.injectMe(BinaryCheckUpdateIntentService.class, binaryCheckUpdateIntentService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, BinaryCheckUpdateIntentService binaryCheckUpdateIntentService) {
        binaryCheckUpdateIntentService._UL_mInjectionContext = new InjectionContext(2, injectorLike);
    }

    public BinaryCheckUpdateIntentService() {
        super(TAG);
    }

    @Override // com.facebook.secure.service.PublicBaseIntentServiceWithSwitchOff, com.oculus.security.basecomponent.OculusPublicIntentService
    public void onCreate() {
        super.onCreate();
        _UL_injectMe((Context) this, this);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.service.PublicBaseIntentServiceWithSwitchOff
    public void onSecuredHandleIntent(@Nullable Intent intent) {
        if (intent == null || !ACTION.equals(intent.getAction())) {
            getEventBuilder("", UNSUPPORTED_ACTION).logAndRelease();
            return;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            BLog.w(TAG, "Got unexpected null extras");
            getEventBuilder("", NULL_EXTRAS).logAndRelease();
            return;
        }
        long j = extras.getLong(ORIGINAL_SCHEDULE_TIME);
        String string = extras.getString("package_name");
        String string2 = extras.getString("result");
        if (string == null) {
            getEventBuilder("", NO_PACKAGE_NAME).logAndRelease();
        } else if (string2 == null) {
            getEventBuilder(string, NO_RESULT).logAndRelease();
        } else {
            App app = ((OVRLibrary) FbInjector.lazyInstance(1, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getApp(string);
            if (app == null) {
                getEventBuilder(string, PACKAGE_NOT_IN_LIBRARY).logAndRelease();
                return;
            }
            long currentTimeMillis = System.currentTimeMillis() - j;
            if (currentTimeMillis > StartupBlockingConfig.BLOCKING_UPLOAD_MAX_WAIT_MILLIS) {
                try {
                    getPackageManager().getPackageUid(string, 0);
                } catch (PackageManager.NameNotFoundException unused) {
                    BLog.d(TAG, "%s is not installed", string);
                    getEventBuilder(string, PACKAGE_NOT_INSTALLED).addExtra(TIME_SINCE_SCHEDULED_MS, currentTimeMillis).logAndRelease();
                    return;
                }
            }
            ((OVRLibrary) FbInjector.lazyInstance(1, OVRLibraryModule.UL_id._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID, this._UL_mInjectionContext)).save(new App.Editor(string).withTrustedBinaryStatus(string2));
            getEventBuilder(string, STORE_RESULT_IN_LIBRARY).logAndRelease();
            if (TrustedBinaryContract.STATUS_UNTRUSTED.equals(string2)) {
                notify(string, buildNotification(app));
                if (System.currentTimeMillis() - extras.getLong(ORIGINAL_SCHEDULE_TIME) < 3000) {
                    sendDialogIntent(string);
                }
            }
        }
    }

    private Notification buildNotification(App app) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.EXTRA_VRSHELL_AUI_PERSIST, true);
        builder.setSmallIcon(R.drawable.status_icon_filled).setTicker(app.displayName).setContentTitle(getString(R.string.binary_check_notification_title, new Object[]{app.displayName})).setContentText(getString(R.string.binary_check_notification_content)).setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.binary_check_notification_title, new Object[]{app.displayName}))).setExtras(bundle);
        return builder.build();
    }

    private void notify(String str, Notification notification) {
        ((NotificationManager) getSystemService("notification")).notify(str.hashCode(), notification);
        getEventBuilder(str, "ocms_intent_service_package_send_notification").logAndRelease();
    }

    private void sendDialogIntent(String str) {
        Intent intent = new Intent(Constants.ACTION_LAUNCH);
        intent.setData(SecureUriParser.parseStrict(SystemUXRoute.UNOFFICIAL_APP_INSTALLED_DIALOG.path() + "?" + "package_name" + "=" + str)).setComponent(new ComponentName("com.oculus.vrshell", BuildConstants.ACTIVITY_NAME_SHELL)).setFlags(268435456);
        SecureContext.launchFamilyActivity(intent, this);
        getEventBuilder(str, "ocms_intent_service_package_send_notification").logAndRelease();
    }

    private Event getEventBuilder(String str, String str2) {
        return ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(TELEMETRY_EVENT_NAME).addExtra("package_name", str).addExtra(EVENT_SUBTYPE, str2);
    }
}
