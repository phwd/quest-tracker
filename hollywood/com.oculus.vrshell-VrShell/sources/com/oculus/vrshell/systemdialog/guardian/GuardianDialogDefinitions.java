package com.oculus.vrshell.systemdialog.guardian;

import android.content.Context;
import android.net.UrlQuerySanitizer;
import android.util.Log;
import androidx.core.text.HtmlCompat;
import androidx.core.view.MotionEventCompat;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianAdjustDeskHeightDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianBoundaryCompleteDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianBoundaryCompleteNotOptimalDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianBoundaryConfirmDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianBoundaryPlayspaceClearDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianBoundaryPlayspaceWarningDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianBoundarySetupFailedDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianConfirmCouchDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianConfirmCouchGuardianDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianConfirmDeskDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianConfirmDeskGuardianDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianConfirmFloorDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianConfirmPassthroughPortalDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianConfirmSurfaceEditingDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianConfirmWhiteboardDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianConfirmWhiteboardGuardianDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianControllerRequiredDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianControllerRequiredPassthroughPortalDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianDrawBoundaryDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianDrawCouchDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianDrawDeskDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianDrawPassthroughPortalDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianDrawWhiteboardDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianEnterPassthroughQuickbootNUXDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianExistingCouchDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianExistingDeskDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianExistingPassthroughPortalDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianExistingWhiteboardDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianLeftBoundsDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianLeftBoundsInBlackDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianLeftBoundsInQuickbootDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianLeftBoundsStationaryDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianMapFoundDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianMapFoundDialogPlayspaceWarningDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianMapFoundWithSurfacesDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianMapRelocDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianMapSetupDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianPTWarningDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianPTWarningSetupDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianQuitAppToPassthroughDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianSelectSurfaceTypeDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianSizeNotOptimalDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianStationaryCompleteDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianSurfaceCreationControllersReqDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianSwitchSpaceCouchDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianSwitchSpaceDeskAndQuitAppDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianSwitchSpaceDeskDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianSwitchSpaceRoomscaleDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianSwitchSpaceStationaryDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingBadTextureContinueDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingBadTextureDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingBadTextureQuitDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingLostContinueDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingLostDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingLostQuitDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingTooBrightContinueDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingTooBrightDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingTooBrightQuitDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingTooDarkContinueDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingTooDarkDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianTrackingTooDarkQuitDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.definitions.GuardianWelcomeDialogDefinition;

public final class GuardianDialogDefinitions {
    private static final String CAN_CANCEL_GUARDIAN_SETUP_PARAMETER = "canCancelGuardianSetup";
    private static final String DIALOG_NAME_PARAMETER = "dialogName";
    private static final String HANDTRACKING_MODE_PARAMETER = "handTrackingGuardianSetup";
    private static final String QUICKBOOT_PARAMETER = "quickbootEnabled";
    private static final String RETURN_PACKAGE_WORKROOMS_PARAMETER = "returnPackageIsWorkrooms";
    private static final String STATIONARY_V2_PARAMETER = "usingStationaryV2";
    private static final String TAG = LoggingUtil.tag(GuardianDialogDefinitions.class);

    public static String getGuardianDialogId(GuardianDialogType guardianDialogType) {
        StringBuilder sb = new StringBuilder();
        sb.append("GUARDIAN_");
        sb.append(guardianDialogType == null ? "UNKNOWN" : guardianDialogType.name());
        return sb.toString();
    }

    public static boolean useImperialMeasurement(Context context) {
        return context.getResources().getConfiguration().locale.getCountry().equals("US");
    }

    public static CustomSystemDialogDefinition getGuardianDialogDefinition(Context context, String str) {
        UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer(str);
        String value = urlQuerySanitizer.getValue(DIALOG_NAME_PARAMETER);
        if (value == null) {
            String str2 = TAG;
            Log.e(str2, "Dialog name parameter not found in launch data: " + str);
            return null;
        }
        boolean equals = "true".equals(urlQuerySanitizer.getValue(CAN_CANCEL_GUARDIAN_SETUP_PARAMETER));
        boolean equals2 = "true".equals(urlQuerySanitizer.getValue(HANDTRACKING_MODE_PARAMETER));
        boolean equals3 = "true".equals(urlQuerySanitizer.getValue(STATIONARY_V2_PARAMETER));
        boolean equals4 = "true".equals(urlQuerySanitizer.getValue(QUICKBOOT_PARAMETER));
        boolean equals5 = "true".equals(urlQuerySanitizer.getValue(RETURN_PACKAGE_WORKROOMS_PARAMETER));
        try {
            GuardianDialogType valueOf = GuardianDialogType.valueOf(value);
            if (valueOf == null) {
                Log.e(TAG, "Unable to handle null guardian dialog type.");
                return null;
            }
            switch (AnonymousClass1.$SwitchMap$com$oculus$vrshell$systemdialog$guardian$GuardianDialogType[valueOf.ordinal()]) {
                case 1:
                    return new GuardianBoundaryCompleteDialogDefinition(context, equals2);
                case 2:
                    return new GuardianBoundaryCompleteNotOptimalDialogDefinition(context, equals2);
                case 3:
                    return new GuardianBoundaryConfirmDialogDefinition(context, equals2);
                case 4:
                    return new GuardianBoundarySetupFailedDialogDefinition(context, equals2);
                case 5:
                    return new GuardianConfirmFloorDialogDefinition(context, equals, equals2);
                case 6:
                    return new GuardianDrawBoundaryDialogDefinition(context, equals2);
                case 7:
                    return new GuardianEnterPassthroughQuickbootNUXDialogDefinition(context);
                case 8:
                    return new GuardianWelcomeDialogDefinition(context, equals2);
                case 9:
                    return new GuardianLeftBoundsDialogDefinition(context, equals4);
                case 10:
                    return new GuardianLeftBoundsInBlackDialogDefinition(context);
                case 11:
                    return new GuardianLeftBoundsInQuickbootDialogDefinition(context);
                case 12:
                    return new GuardianLeftBoundsStationaryDialogDefinition(context, equals4);
                case 13:
                    return new GuardianMapFoundDialogDefinition(context);
                case 14:
                    return new GuardianMapFoundWithSurfacesDialogDefinition(context);
                case 15:
                    return new GuardianMapRelocDialogDefinition(context);
                case 16:
                    return new GuardianMapSetupDialogDefinition(context, equals4);
                case 17:
                    return new GuardianPTWarningDialogDefinition(context);
                case 18:
                    return new GuardianPTWarningSetupDialogDefinition(context);
                case 19:
                    return new GuardianQuitAppToPassthroughDialogDefinition(context);
                case 20:
                    return new GuardianSizeNotOptimalDialogDefinition(context, equals2);
                case 21:
                    return new GuardianStationaryCompleteDialogDefinition(context, equals, equals2, equals3);
                case 22:
                    return new GuardianSwitchSpaceRoomscaleDialogDefinition(context);
                case 23:
                    return new GuardianSwitchSpaceStationaryDialogDefinition(context);
                case 24:
                    return new GuardianSwitchSpaceCouchDialogDefinition(context);
                case 25:
                    return new GuardianSwitchSpaceDeskDialogDefinition(context);
                case 26:
                    return new GuardianSwitchSpaceDeskAndQuitAppDialogDefinition(context);
                case 27:
                    return new GuardianTrackingBadTextureDialogDefinition(context);
                case MotionEventCompat.AXIS_RELATIVE_Y:
                    return new GuardianTrackingBadTextureContinueDialogDefinition(context);
                case 29:
                    return new GuardianTrackingBadTextureQuitDialogDefinition(context);
                case 30:
                    return new GuardianTrackingLostDialogDefinition(context);
                case 31:
                    return new GuardianTrackingLostContinueDialogDefinition(context);
                case 32:
                    return new GuardianTrackingLostQuitDialogDefinition(context);
                case MotionEventCompat.AXIS_GENERIC_2:
                    return new GuardianTrackingTooBrightDialogDefinition(context);
                case MotionEventCompat.AXIS_GENERIC_3:
                    return new GuardianTrackingTooBrightContinueDialogDefinition(context);
                case MotionEventCompat.AXIS_GENERIC_4:
                    return new GuardianTrackingTooBrightQuitDialogDefinition(context);
                case 36:
                    return new GuardianTrackingTooDarkDialogDefinition(context);
                case MotionEventCompat.AXIS_GENERIC_6:
                    return new GuardianTrackingTooDarkContinueDialogDefinition(context);
                case MotionEventCompat.AXIS_GENERIC_7:
                    return new GuardianTrackingTooDarkQuitDialogDefinition(context);
                case MotionEventCompat.AXIS_GENERIC_8:
                    return new GuardianControllerRequiredDialogDefinition(context, equals2, false);
                case MotionEventCompat.AXIS_GENERIC_9:
                    return new GuardianControllerRequiredDialogDefinition(context, equals2, true);
                case MotionEventCompat.AXIS_GENERIC_10:
                    return new GuardianBoundaryPlayspaceClearDialogDefinition(context, equals2);
                case MotionEventCompat.AXIS_GENERIC_11:
                    return new GuardianBoundaryPlayspaceWarningDialogDefinition(context, equals2);
                case MotionEventCompat.AXIS_GENERIC_12:
                    return new GuardianMapFoundDialogPlayspaceWarningDefinition(context);
                case MotionEventCompat.AXIS_GENERIC_13:
                    return new GuardianExistingCouchDialogDefinition(context);
                case MotionEventCompat.AXIS_GENERIC_14:
                    return new GuardianDrawCouchDialogDefinition(context, equals2);
                case MotionEventCompat.AXIS_GENERIC_15:
                    return new GuardianConfirmCouchDialogDefinition(context);
                case MotionEventCompat.AXIS_GENERIC_16:
                    return new GuardianConfirmCouchGuardianDialogDefinition(context);
                case 48:
                    return new GuardianExistingWhiteboardDialogDefinition(context);
                case 49:
                    return new GuardianDrawWhiteboardDialogDefinition(context, equals2);
                case 50:
                    return new GuardianConfirmWhiteboardDialogDefinition(context);
                case 51:
                    return new GuardianConfirmWhiteboardGuardianDialogDefinition(context);
                case 52:
                    return new GuardianExistingDeskDialogDefinition(context);
                case 53:
                    return new GuardianDrawDeskDialogDefinition(context, equals2, equals5);
                case 54:
                    return new GuardianConfirmDeskDialogDefinition(context, !equals2);
                case 55:
                    return new GuardianConfirmDeskGuardianDialogDefinition(context);
                case 56:
                    return new GuardianAdjustDeskHeightDialogDefinition(context);
                case 57:
                    return new GuardianExistingPassthroughPortalDialogDefinition(context);
                case 58:
                    return new GuardianDrawPassthroughPortalDialogDefinition(context, equals2);
                case 59:
                    return new GuardianConfirmPassthroughPortalDialogDefinition(context);
                case 60:
                    return new GuardianControllerRequiredPassthroughPortalDialogDefinition(context, equals2);
                case 61:
                    return new GuardianSelectSurfaceTypeDialogDefinition(context, false);
                case 62:
                    return new GuardianConfirmSurfaceEditingDialogDefinition(context);
                case HtmlCompat.FROM_HTML_MODE_COMPACT:
                    return new GuardianSelectSurfaceTypeDialogDefinition(context, true);
                case 64:
                    return new GuardianSurfaceCreationControllersReqDialogDefinition(context, equals2);
                default:
                    Log.i(TAG, String.format("Unable to find guardian dialog definition for dialog \"%s\".", value));
                    return null;
            }
        } catch (IllegalArgumentException e) {
            String str3 = TAG;
            Log.e(str3, "Unable to parse guardian dialog type: " + value, e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrshell$systemdialog$guardian$GuardianDialogType = new int[GuardianDialogType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(128:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(3:127|128|130)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(130:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|130) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:101:0x025a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:103:0x0266 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:105:0x0272 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:107:0x027e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:109:0x028a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:111:0x0296 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:113:0x02a2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:115:0x02ae */
        /* JADX WARNING: Missing exception handler attribute for start block: B:117:0x02ba */
        /* JADX WARNING: Missing exception handler attribute for start block: B:119:0x02c6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:121:0x02d2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:123:0x02de */
        /* JADX WARNING: Missing exception handler attribute for start block: B:125:0x02ea */
        /* JADX WARNING: Missing exception handler attribute for start block: B:127:0x02f6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0086 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0092 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00aa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00ce */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00da */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00e6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00f2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00fe */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x010a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x0116 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x0122 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x012e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x013a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x0146 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x0152 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x015e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x016a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0176 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0182 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x018e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x019a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x01a6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x01b2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x01be */
        /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x01ca */
        /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x01d6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:81:0x01e2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:83:0x01ee */
        /* JADX WARNING: Missing exception handler attribute for start block: B:85:0x01fa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x0206 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:89:0x0212 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x021e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:93:0x022a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:95:0x0236 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:97:0x0242 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:99:0x024e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
            // Method dump skipped, instructions count: 771
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions.AnonymousClass1.<clinit>():void");
        }
    }
}
