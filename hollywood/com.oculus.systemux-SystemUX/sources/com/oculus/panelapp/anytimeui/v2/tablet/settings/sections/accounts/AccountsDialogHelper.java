package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.accounts;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.VisibleForTesting;
import com.oculus.authapi.AuthError;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsAccountsSection;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import com.oculus.userserver.api.user.OculusUser;

public final class AccountsDialogHelper {
    private static final String ADD_USER_CONFIRMATION_DIALOG_ID = "add_user_confirmation_dialog";
    private static final String ADD_USER_DIALOG_IMAGE = "apk://com.oculus.systemutilities/assets/user_creation_transfer_step_miramar_949x534.ktx";
    private static final float ADD_USER_DIALOG_IMAGE_ASPECT_RATIO = 1.7771536f;
    private static final String DELETE_USER_DIALOG_ID = "delete_user_dialog";
    private static final String ERROR_DIALOG_ID = "multi_user_error_dialog";
    @VisibleForTesting
    public static final String GUARDIAN_REQUIRED_DIALOG_ID = "guardian_required_dialog";
    @VisibleForTesting
    public static final String INTERNET_REQUIRED_DIALOG_ID = "internet_wifi_dialog_required_dialog";
    private static final String LIBRARY_SHARING_ERROR_DIALOG_ID = "library_sharing_error_dialog";
    public static final String LIBRARY_SHARING_ERROR_DIALOG_SUPPORT_BUTTON = "support";
    private static final String LIBRARY_SHARING_UPSELL_DIALOG_ID = "library_sharing_upsell_dialog";
    @VisibleForTesting
    public static final String MULTI_USER_EDUCATION_DIALOG_ID = "multi_user_accounts_tab_education_dialog";

    public static DialogDefinitionCustom getEducationNuxDialog(Context context) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(MULTI_USER_EDUCATION_DIALOG_ID, resources.getString(R.string.settings_multi_user_education_dialog_title), String.format("<b>%s</b> %s \n\n <b>%s</b> %s \n\n <b>%s</b> %s", resources.getString(R.string.settings_multi_user_education_dialog_body_1_main), resources.getString(R.string.settings_multi_user_education_dialog_body_1), resources.getString(R.string.settings_multi_user_education_dialog_body_2_main), resources.getString(R.string.settings_multi_user_education_dialog_body_2), resources.getString(R.string.settings_multi_user_education_dialog_body_3_main), resources.getString(R.string.settings_multi_user_education_dialog_body_3)));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_multi_user_dialog_confirm)));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionCustom getInternetRequiredDialog(Context context) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(INTERNET_REQUIRED_DIALOG_ID, resources.getString(R.string.settings_multi_user_internet_required_dialog_title), resources.getString(R.string.settings_multi_user_internet_required_dialog_body));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_multi_user_dialog_confirm)));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionCustom getGuardianRequiredDialog(Context context) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(GUARDIAN_REQUIRED_DIALOG_ID, resources.getString(R.string.settings_add_account_guardian_required_dialog_title), resources.getString(R.string.settings_add_account_guardian_required_dialog_body));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("close", resources.getString(R.string.settings_multi_user_dialog_confirm)));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionCustom getLibrarySharingUpsellDialog(Context context) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(LIBRARY_SHARING_UPSELL_DIALOG_ID, resources.getString(R.string.settings_multi_user_app_sharing_upsell_dialog_title), String.format("%s \n %s", resources.getString(R.string.settings_multi_user_app_sharing_upsell_dialog_body_p1), resources.getString(R.string.settings_multi_user_app_sharing_upsell_dialog_body_p2)));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_multi_user_app_sharing_upsell_dialog_enable)));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_multi_user_app_sharing_upsell_dialog_skip)));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionCustom getLibrarySharingErrorDialog(Context context, SharingManagerContract.ResultCode resultCode) {
        Resources resources = context.getResources();
        String string = resources.getString(R.string.settings_multi_user_app_sharing_enable_error_dialog_title);
        int i = AnonymousClass1.$SwitchMap$com$oculus$userserver$api$sharing$SharingManagerContract$ResultCode[resultCode.ordinal()];
        if (i == 1) {
            DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(LIBRARY_SHARING_ERROR_DIALOG_ID, string, String.format("%s \n %s", resources.getString(R.string.settings_multi_user_app_sharing_already_enabled_error_dialog_body_p1), resources.getString(R.string.settings_multi_user_app_sharing_already_enabled_error_dialog_body_p2)));
            dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_multi_user_app_sharing_already_enabled_error_dialog_close_button)));
            dialogDefinitionCustom.setTertiaryButton(new DialogButtonText(LIBRARY_SHARING_ERROR_DIALOG_SUPPORT_BUTTON, resources.getString(R.string.settings_multi_user_app_sharing_already_enabled_error_dialog_support_button)));
            return dialogDefinitionCustom;
        } else if (i != 2) {
            return new DialogDefinitionCustom(LIBRARY_SHARING_ERROR_DIALOG_ID, string, "");
        } else {
            return new DialogDefinitionCustom(LIBRARY_SHARING_ERROR_DIALOG_ID, string, resources.getString(R.string.settings_multi_user_app_sharing_enable_connection_error_dialog_body));
        }
    }

    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.accounts.AccountsDialogHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$userserver$api$sharing$SharingManagerContract$ResultCode = new int[SharingManagerContract.ResultCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.userserver.api.sharing.SharingManagerContract$ResultCode[] r0 = com.oculus.userserver.api.sharing.SharingManagerContract.ResultCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.accounts.AccountsDialogHelper.AnonymousClass1.$SwitchMap$com$oculus$userserver$api$sharing$SharingManagerContract$ResultCode = r0
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.accounts.AccountsDialogHelper.AnonymousClass1.$SwitchMap$com$oculus$userserver$api$sharing$SharingManagerContract$ResultCode     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.userserver.api.sharing.SharingManagerContract$ResultCode r1 = com.oculus.userserver.api.sharing.SharingManagerContract.ResultCode.NETWORK_ERROR     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.accounts.AccountsDialogHelper.AnonymousClass1.$SwitchMap$com$oculus$userserver$api$sharing$SharingManagerContract$ResultCode     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.userserver.api.sharing.SharingManagerContract$ResultCode r1 = com.oculus.userserver.api.sharing.SharingManagerContract.ResultCode.CANT_CONNECT_TO_SERVICE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.accounts.AccountsDialogHelper.AnonymousClass1.$SwitchMap$com$oculus$userserver$api$sharing$SharingManagerContract$ResultCode     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.userserver.api.sharing.SharingManagerContract$ResultCode r1 = com.oculus.userserver.api.sharing.SharingManagerContract.ResultCode.ERROR_UNKNOWN     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.accounts.AccountsDialogHelper.AnonymousClass1.<clinit>():void");
        }
    }

    public static DialogDefinitionCustom getRemoveUserDialog(Context context, OculusUser oculusUser, boolean z) {
        String str;
        String str2;
        Resources resources = context.getResources();
        if (z) {
            str = resources.getString(R.string.settings_multi_user_remove_self_dialog_title);
        } else {
            str = resources.getString(R.string.settings_multi_user_remove_user_dialog_title, oculusUser.getUserName());
        }
        Object[] objArr = new Object[3];
        if (z) {
            str2 = resources.getString(R.string.settings_multi_user_remove_self_dialog_body);
        } else {
            str2 = resources.getString(R.string.settings_multi_user_remove_user_dialog_body);
        }
        objArr[0] = str2;
        objArr[1] = SettingsAccountsSection.MULTIUSER_LEARN_MORE_URI;
        objArr[2] = resources.getString(R.string.settings_multi_user_remove_self_dialog_learn_more_link);
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(DELETE_USER_DIALOG_ID, str, String.format("%s <a href=\"%s\">%s</a>", objArr));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_multi_user_remove_self_dialog_cancel)));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_multi_user_remove_self_dialog_confirm)));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionCustom getErrorDialog(Context context) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(ERROR_DIALOG_ID, resources.getString(R.string.settings_multi_user_error_dialog_title), "");
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_multi_user_dialog_confirm)));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionCustom getDeviceOwnershipErrorDialog(Context context, AuthError authError, boolean z) {
        String str;
        Resources resources = context.getResources();
        int errorCode = authError.getErrorCode();
        if (errorCode == -16) {
            str = resources.getString(z ? R.string.settings_multi_user_ownership_error_internal : R.string.settings_multi_user_ownership_error_public);
        } else if (errorCode != -4) {
            str = "";
        } else {
            str = resources.getString(R.string.settings_multi_user_ownership_connection_error);
        }
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(ERROR_DIALOG_ID, resources.getString(R.string.settings_multi_user_error_dialog_title), str);
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_multi_user_dialog_confirm)));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionCustom getAddUserConfirmationDialog(Context context) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(ADD_USER_CONFIRMATION_DIALOG_ID, resources.getString(R.string.settings_multi_user_add_user_dialog_title), resources.getString(R.string.settings_multi_user_add_user_dialog_body));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_multi_user_add_user_dialog_continue)));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_multi_user_add_user_dialog_cancel)));
        dialogDefinitionCustom.setHeroImage(new DialogHeroImage(ADD_USER_DIALOG_IMAGE, ADD_USER_DIALOG_IMAGE_ASPECT_RATIO, null));
        return dialogDefinitionCustom;
    }
}
