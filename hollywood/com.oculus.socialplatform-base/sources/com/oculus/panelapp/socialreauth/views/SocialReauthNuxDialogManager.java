package com.oculus.panelapp.socialreauth.views;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.vrshell.SystemUXRoute;

public class SocialReauthNuxDialogManager {
    public static final String CONTINUE = "continue";
    public static final String CONTINUE_WITH_OCULUS_CHATS = "continue_with_oculus_chats";
    public static final String OPEN_MESSENGER = "open_messenger";
    public static final String SIGN_IN_TO_MESSENGER = "sign_in_to_messenger";
    public static String TAG = LoggingUtil.tag(SocialReauthNuxDialogManager.class);
    @Nullable
    public NavigateCallback mNavigateCallback;
    @Nullable
    public Resources mResources;
    @Nullable
    public SocialLogger mSocialLogger;

    public interface NavigateCallback {
        void navigate(SystemUXRoute systemUXRoute, String str);
    }

    public void destroy() {
        this.mSocialLogger = null;
        this.mResources = null;
        this.mNavigateCallback = null;
    }

    @VisibleForTesting
    public String encodeHtmlString(String str) {
        return Html.toHtml(Html.fromHtml(str, 0), 0);
    }

    private void handleButtonClick(Context context, ClickEventButtonId clickEventButtonId, boolean z, SystemUXRoute systemUXRoute, String str) {
        this.mSocialLogger.logButtonClick(clickEventButtonId, SurfaceType.REAUTH_NUX_DIALOG);
        if (z) {
            MessengerVrAccountsContentProviderClient.setHasSeenNewUserAuthenticationDialog(context, true);
        }
        if (systemUXRoute != null) {
            this.mNavigateCallback.navigate(systemUXRoute, str);
        }
    }

    public DialogDefinitionCustom constructDialog(Context context, boolean z, boolean z2) {
        String string;
        String str;
        int i;
        String string2 = this.mResources.getString(R.string.anytime_tablet_social_reauth_messenger_nux_dialog_body);
        if (z) {
            Resources resources = this.mResources;
            if (z2) {
                i = R.string.anytime_tablet_social_reauth_messenger_nux_dialog_reduced_friction_body;
            } else {
                i = R.string.anytime_tablet_social_reauth_messenger_nux_dialog_reauth_skip_body;
            }
            string2 = resources.getString(i);
        }
        DialogButtonText dialogButtonText = new DialogButtonText(SIGN_IN_TO_MESSENGER, this.mResources.getString(R.string.anytime_tablet_social_reauth_messenger_nux_dialog_sign_in_btn));
        if (z) {
            Resources resources2 = this.mResources;
            if (z2) {
                string = resources2.getString(R.string.anytime_tablet_social_reauth_messenger_nux_dialog_continue_btn);
                str = "continue";
            } else {
                string = resources2.getString(R.string.anytime_tablet_social_reauth_messenger_nux_dialog_open_messenger_btn);
                str = OPEN_MESSENGER;
            }
            dialogButtonText = new DialogButtonText(str, string);
        }
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("messenger_nux", this.mResources.getString(R.string.anytime_tablet_social_reauth_messenger_nux_dialog_title), encodeHtmlString(string2));
        dialogDefinitionCustom.mDialogPrimaryButton = dialogButtonText;
        if (!z2) {
            dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText(CONTINUE_WITH_OCULUS_CHATS, this.mResources.getString(R.string.anytime_tablet_social_reauth_messenger_nux_dialog_continue_with_oculus_chats_btn));
        }
        dialogDefinitionCustom.mDialogResultHandler = new DialogResultHandler(context) {
            /* class com.oculus.panelapp.socialreauth.views.$$Lambda$SocialReauthNuxDialogManager$33cr169jptr8EEPPIU6_WU6i9Qc2 */
            public final /* synthetic */ Context f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialReauthNuxDialogManager.this.lambda$constructDialog$0$SocialReauthNuxDialogManager(this.f$1, dialogResult);
            }
        };
        return dialogDefinitionCustom;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0053 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ boolean lambda$constructDialog$0$SocialReauthNuxDialogManager(android.content.Context r11, com.oculus.systemdialog.DialogResult r12) {
        /*
        // Method dump skipped, instructions count: 102
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialreauth.views.SocialReauthNuxDialogManager.lambda$constructDialog$0$SocialReauthNuxDialogManager(android.content.Context, com.oculus.systemdialog.DialogResult):boolean");
    }

    public SocialReauthNuxDialogManager(SocialLogger socialLogger, Resources resources, NavigateCallback navigateCallback) {
        this.mSocialLogger = socialLogger;
        this.mResources = resources;
        this.mNavigateCallback = navigateCallback;
    }
}
