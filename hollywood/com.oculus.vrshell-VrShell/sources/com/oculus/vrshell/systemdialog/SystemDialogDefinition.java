package com.oculus.vrshell.systemdialog;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.definitions.AppModeIncompatibleDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.DeveloperErrorDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.EnterpriseCertificateExpiredDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.EnterpriseCertificateExpiringWarningDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.EnterpriseGuardianDisabledWarningDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.GuardianAdjustDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.PTOnDemandNUXDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.PanelReorientDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.QuitAndLaunchDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.ScreenshotShortcutNUXDefinition;
import com.oculus.vrshell.systemdialog.definitions.SystemFailureDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.TrackingMode3DOFIncompatDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.TrackingOffDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.UnofficialAppDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.launchcheck.LaunchCheckAppDegradedDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.launchcheck.LaunchCheckAppDisabledDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.launchcheck.LaunchCheckAppUpdateDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.launchcheck.LaunchCheckBlockDeskDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.launchcheck.LaunchCheckEntitlementSharing;
import com.oculus.vrshell.systemdialog.definitions.launchcheck.LaunchCheckNonCompliantDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.launchcheck.LaunchCheckRequires6dofDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.launchcheck.LaunchCheckRequiresAvatarDialogDefinition;
import com.oculus.vrshell.systemdialog.definitions.launchcheck.LaunchCheckRequiresExclusiveMicrophoneDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;

public abstract class SystemDialogDefinition {
    private static final String TAG = LoggingUtil.tag(SystemDialogDefinition.class);

    public static String getSystemDialogDefinition(Context context, String str, String str2) {
        SystemUXRoute fromPath = SystemUXRoute.fromPath(str);
        CustomSystemDialogDefinition customSystemDialogDefinition = null;
        if (fromPath == null) {
            Log.w(TAG, String.format("No SystemUXRoute exists for dialog uri %s", str));
            return null;
        }
        CommonSystemDialog fromRoute = CommonSystemDialog.fromRoute(fromPath);
        if (fromRoute != null) {
            return new CommonSystemDialogDefinition(fromRoute, str2).getPendingDialogConfigurationIPCParcel();
        }
        switch (fromPath) {
            case APP_MODE_INCOMPATIBLE:
                customSystemDialogDefinition = new AppModeIncompatibleDialogDefinition(context);
                break;
            case DEVELOPER_ERROR:
                customSystemDialogDefinition = new DeveloperErrorDialogDefinition(context, str2);
                break;
            case ENTERPRISE_CERTIFICATE_EXPIRED:
                customSystemDialogDefinition = new EnterpriseCertificateExpiredDialogDefinition(context);
                break;
            case ENTERPRISE_CERTIFICATE_EXPIRING_WARNING:
                customSystemDialogDefinition = new EnterpriseCertificateExpiringWarningDialogDefinition(context);
                break;
            case ENTERPRISE_GUARDIAN_DISABLED_WARNING:
                customSystemDialogDefinition = new EnterpriseGuardianDisabledWarningDialogDefinition(context);
                break;
            case GUARDIAN_ADJUST_DIALOG:
                customSystemDialogDefinition = new GuardianAdjustDialogDefinition(context);
                break;
            case GUARDIAN_DIALOG:
                customSystemDialogDefinition = GuardianDialogDefinitions.getGuardianDialogDefinition(context, str2);
                break;
            case LAUNCH_CHECK_APP_DEGRADED:
                customSystemDialogDefinition = new LaunchCheckAppDegradedDialogDefinition(context, str2);
                break;
            case LAUNCH_CHECK_APP_DISABLED:
                customSystemDialogDefinition = new LaunchCheckAppDisabledDialogDefinition(context, str2);
                break;
            case LAUNCH_CHECK_APP_UPDATE:
                customSystemDialogDefinition = new LaunchCheckAppUpdateDialogDefinition(context, str2);
                break;
            case LAUNCH_CHECK_BLOCK_DESK:
                customSystemDialogDefinition = new LaunchCheckBlockDeskDialogDefinition(context);
                break;
            case LAUNCH_CHECK_ENTITLEMENT_SHARING:
                customSystemDialogDefinition = new LaunchCheckEntitlementSharing(context);
                break;
            case LAUNCH_CHECK_NON_COMPLIANT:
                customSystemDialogDefinition = new LaunchCheckNonCompliantDialogDefinition(context);
                break;
            case LAUNCH_CHECK_REQUIRES_6DOF:
                customSystemDialogDefinition = new LaunchCheckRequires6dofDialogDefinition(context);
                break;
            case LAUNCH_CHECK_REQUIRES_AVATAR:
                customSystemDialogDefinition = new LaunchCheckRequiresAvatarDialogDefinition(context);
                break;
            case LAUNCH_CHECK_REQUIRES_EXCLUSIVE_MICROPHONE:
                customSystemDialogDefinition = new LaunchCheckRequiresExclusiveMicrophoneDialogDefinition(context);
                break;
            case PANEL_REORIENT:
                customSystemDialogDefinition = new PanelReorientDialogDefinition(context);
                break;
            case PT_ONDEMAND_NUX_DIALOG:
                customSystemDialogDefinition = new PTOnDemandNUXDialogDefinition(context);
                break;
            case QUIT_AND_LAUNCH:
                customSystemDialogDefinition = new QuitAndLaunchDialogDefinition(context, str2);
                break;
            case SCREENSHOT_SHORTCUT_NUX:
                customSystemDialogDefinition = new ScreenshotShortcutNUXDefinition(context);
                break;
            case SYSTEM_FAILURE_MESSAGE:
                customSystemDialogDefinition = new SystemFailureDialogDefinition(context, str2);
                break;
            case TRACKING_MODE_3DOF_INCOMPAT_DIALOG:
                customSystemDialogDefinition = new TrackingMode3DOFIncompatDialogDefinition(context);
                break;
            case TRACKING_OFF_DIALOG:
                customSystemDialogDefinition = new TrackingOffDialogDefinition(context);
                break;
            case UNOFFICIAL_APP_INSTALLED_DIALOG:
                customSystemDialogDefinition = new UnofficialAppDialogDefinition(context, str2, "install");
                break;
            case UNOFFICIAL_APP_LAUNCHED_DIALOG:
                customSystemDialogDefinition = new UnofficialAppDialogDefinition(context, str2, UnofficialAppDialogDefinition.EVENT_LAUNCH);
                break;
            default:
                Log.i(TAG, String.format("Unable to find system dialog definition for dialog URI \"%s\".", str));
                break;
        }
        return customSystemDialogDefinition != null ? customSystemDialogDefinition.getPendingDialogConfigurationIPCParcel() : "";
    }
}
