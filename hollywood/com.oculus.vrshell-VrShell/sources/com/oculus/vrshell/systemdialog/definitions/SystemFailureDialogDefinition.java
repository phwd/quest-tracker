package com.oculus.vrshell.systemdialog.definitions;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.UrlQuerySanitizer;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.SystemDialogActions;

public class SystemFailureDialogDefinition extends CustomSystemDialogDefinition {
    private static final String FALSE_STRING = "false";
    private static final String MESSAGE = "Message";
    private static final String OTA_COMPONENT_NAME = "com.oculus.updater.core.os.OSUpdateService";
    private static final String OTA_PACKAGE_NAME = "com.oculus.updater";
    private static final String OTA_UPDATE_ACTION = "ext_check_updates";
    private static final String REQUIRES_UPDATE = "RequiresUpdate";
    private static final String RETURN_COMPONENT = "return_component";
    private static final String TAG = LoggingUtil.tag(SystemFailureDialogDefinition.class);

    public SystemFailureDialogDefinition(Context context, String str) {
        super(createSystemFailureDialogDefinitionCustom(context, str));
    }

    private static DialogDefinitionCustom createSystemFailureDialogDefinitionCustom(Context context, String str) {
        DialogDefinitionCustom dialogDefinitionCustom;
        UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer(str);
        if (FALSE_STRING.equals(urlQuerySanitizer.getValue(REQUIRES_UPDATE))) {
            dialogDefinitionCustom = new DialogDefinitionCustom(SystemUXRoute.SYSTEM_FAILURE_MESSAGE.path(), context.getResources().getString(R.string.dialog_system_ota_error_title), urlQuerySanitizer.getValue(MESSAGE));
        } else {
            DialogDefinitionCustom dialogDefinitionCustom2 = new DialogDefinitionCustom(SystemUXRoute.SYSTEM_FAILURE_MESSAGE.path(), context.getResources().getString(R.string.dialog_system_ota_failure_title), context.getResources().getString(R.string.dialog_system_ota_update_instructions));
            requestOTA(context, urlQuerySanitizer.getValue(RETURN_COMPONENT));
            dialogDefinitionCustom = dialogDefinitionCustom2;
        }
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.dialog_system_ota_acknowledge)));
        return dialogDefinitionCustom;
    }

    private static void requestOTA(Context context, String str) {
        Intent intent = new Intent(OTA_UPDATE_ACTION);
        intent.setComponent(new ComponentName("com.oculus.updater", OTA_COMPONENT_NAME));
        try {
            context.startService(intent);
            Log.d(TAG, "OTA Update Service launched. Sending 'ok' response.");
            logOTAServiceResponse(context, SystemDialogActions.OK_ACTION, str);
        } catch (SecurityException e) {
            Log.e(TAG, "Unable to launch OTA service. Sending 'cancel' response.", e);
            logOTAServiceResponse(context, "security_exception", str);
        }
    }

    private static void logOTAServiceResponse(Context context, String str, String str2) {
        UnifiedTelemetryLogger instance = UnifiedTelemetryLogger.getInstance(context);
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_vrshell_ota_dialog_response");
        analyticsEvent.setExtra("intent_result", str);
        if (TextUtils.isEmpty(str2)) {
            str2 = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        analyticsEvent.setExtra(RETURN_COMPONENT, str2);
        instance.reportEvent(analyticsEvent, false);
    }
}
