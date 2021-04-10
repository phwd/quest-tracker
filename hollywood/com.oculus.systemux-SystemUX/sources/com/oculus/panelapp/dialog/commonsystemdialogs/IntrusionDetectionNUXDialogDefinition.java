package com.oculus.panelapp.dialog.commonsystemdialogs;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.vrshell.SystemUXRoute;

public final class IntrusionDetectionNUXDialogDefinition extends CommonDialog {
    private static final String LOCALE_US = "US";
    private static final float VIDEO_ASPECT_RATIO = 1.778f;
    private static final String VIDEO_BACKGROUND_COLOR = "0xFF1A1A1A";
    private final Resources mResources;

    public IntrusionDetectionNUXDialogDefinition(Context context) {
        this.mResources = context.getResources();
        setPendingDialogConfiguration(getIntrusionDetectionNuxIntroDialog());
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.dialog.CommonDialog
    public void onAction(String str, String[] strArr) {
        if (CommonSystemDialogActions.CONTINUE.equals(str)) {
            setPendingDialogConfiguration(getIntrusionDetectionNuxDemoDialog());
        } else if (CommonSystemDialogActions.INTRUSION_DETECTION_NUX_DEMO.equals(str)) {
            setPendingDialogConfiguration(getIntrusionDetectionNuxSafetyDialog());
        }
    }

    @Override // com.oculus.panelapp.dialog.CommonDialog
    public final boolean shouldSendActionToShell(String str, String[] strArr) {
        return CommonSystemDialogActions.INTRUSION_DETECTION_ENABLE.equals(str) || "cancel".equals(str);
    }

    public boolean useImperialMeasurement() {
        return this.mResources.getConfiguration().locale.getCountry().equals(LOCALE_US);
    }

    /* access modifiers changed from: package-private */
    public DialogDefinitionCustom getIntrusionDetectionNuxIntroDialog() {
        return new DialogDefinitionCustom(SystemUXRoute.INTRUSION_DETECTION_NUX.path(), this.mResources.getString(R.string.intrusion_detection_nux_intro_title), this.mResources.getString(R.string.intrusion_detection_nux_intro_body)).setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, this.mResources.getString(R.string.intrusion_detection_nux_intro_button_continue))).setSecondaryButton(new DialogButtonText("cancel", this.mResources.getString(R.string.intrusion_detection_nux_button_cancel))).setHeroImage(new DialogHeroImage("apk://com.oculus.guardianresources/assets/animatics/ID_01_INTRO.jpg", 1.778f, "0xFF1A1A1A"));
    }

    /* access modifiers changed from: package-private */
    public DialogDefinitionCustom getIntrusionDetectionNuxDemoDialog() {
        return new DialogDefinitionCustom(SystemUXRoute.INTRUSION_DETECTION_NUX.path(), this.mResources.getString(R.string.intrusion_detection_nux_demo_title), this.mResources.getString(useImperialMeasurement() ? R.string.intrusion_detection_nux_demo_body_imperial : R.string.intrusion_detection_nux_demo_body_metric)).setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.INTRUSION_DETECTION_NUX_DEMO, this.mResources.getString(R.string.intrusion_detection_nux_demo_button_continue))).setSecondaryButton(new DialogButtonText("cancel", this.mResources.getString(R.string.intrusion_detection_nux_demo_button_cancel))).setHeroImage(new DialogHeroImage("apk://com.oculus.guardianresources/assets/animatics/ID_02_DEMO.jpg", 1.778f, "0xFF1A1A1A"));
    }

    /* access modifiers changed from: package-private */
    public DialogDefinitionCustom getIntrusionDetectionNuxSafetyDialog() {
        return new DialogDefinitionCustom(SystemUXRoute.INTRUSION_DETECTION_NUX.path(), this.mResources.getString(R.string.intrusion_detection_nux_safety_title), this.mResources.getString(R.string.intrusion_detection_nux_safety_body)).setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.INTRUSION_DETECTION_ENABLE, this.mResources.getString(R.string.intrusion_detection_nux_safety_button_enable))).setSecondaryButton(new DialogButtonText("cancel", this.mResources.getString(R.string.intrusion_detection_nux_button_cancel))).setHeroImage(new DialogHeroImage("apk://com.oculus.guardianresources/assets/animatics/ID_03_SAFETY.jpg", 1.778f, "0xFF1A1A1A"));
    }
}
