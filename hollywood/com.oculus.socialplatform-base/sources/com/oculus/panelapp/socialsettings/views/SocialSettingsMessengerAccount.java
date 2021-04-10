package com.oculus.panelapp.socialsettings.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.fetchers.FBProfileFetcher;
import com.oculus.common.socialtablet.util.ImageHandler;
import com.oculus.notifications.NotificationSender;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.socialsettings.SocialSettingsTabletPanelApp;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsMessengerAccountBinding;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.ThreadExecutor;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.concurrent.Callable;

public class SocialSettingsMessengerAccount extends BaseView {
    public static final String CANCEL_ACTION = "cancel";
    public static final String SIGN_OUT_ACTION = "sign_out";
    public static final String TAG = LoggingUtil.tag(SocialSettingsMessengerAccount.class);
    public SocialSettingsMessengerAccountBinding mBinding;
    public FBProfileFetcher mFBProfileFetcher;
    public ImageHandler mImageHandler;
    public DialogDefinitionCustom mSignOutDialogDefinition;
    public SocialLogger mSocialLogger;
    public SocialSettingsMessengerAccountViewModel mViewModel;

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        this.mViewModel = null;
        this.mFBProfileFetcher.destroy();
        this.mFBProfileFetcher = null;
        this.mImageHandler.unloadView(this.mBinding.messengerAccountProfilePicture);
        this.mImageHandler = null;
        this.mSignOutDialogDefinition = null;
        OCButton oCButton = this.mBinding.messengerAccountSignOutButton;
        oCButton.mEventHandler = null;
        oCButton.setOnClickListener(null);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    public /* synthetic */ boolean lambda$getSignOutDialogDefinition$4$SocialSettingsMessengerAccount(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp, DialogResult dialogResult) {
        String str = dialogResult.mDialogAction;
        int hashCode = str.hashCode();
        if (hashCode != -1367724422) {
            if (hashCode == 311662028 && str.equals(SIGN_OUT_ACTION)) {
                socialSettingsTabletPanelApp.logButtonClick(ClickEventButtonId.SOCIAL_SETTINGS_MESSENGER_SIGN_OUT_DIALOG_SIGN_OUT, SurfaceType.MESSENGER_ACCOUNT);
                ThreadExecutor.INSTANCE.execute(new Callable(socialSettingsTabletPanelApp) {
                    /* class com.oculus.panelapp.socialsettings.views.$$Lambda$SocialSettingsMessengerAccount$3xbR7WCjOoHksWhttdq1Rc3G_FA2 */
                    public final /* synthetic */ SocialSettingsTabletPanelApp f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return SocialSettingsMessengerAccount.this.lambda$null$3$SocialSettingsMessengerAccount(this.f$1);
                    }
                });
                return true;
            }
        } else if (str.equals("cancel")) {
            socialSettingsTabletPanelApp.logButtonClick(ClickEventButtonId.SOCIAL_SETTINGS_MESSENGER_SIGN_OUT_DIALOG_CANCEL, SurfaceType.MESSENGER_ACCOUNT);
            this.mViewModel.setIsSigningOut(false);
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$initialize$0$SocialSettingsMessengerAccount(String str, String str2) {
        this.mViewModel.setUserName(str);
        this.mImageHandler.loadCircleCroppedToView(str2, this.mBinding.messengerAccountProfilePicture);
    }

    public /* synthetic */ void lambda$initialize$1$SocialSettingsMessengerAccount(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp, View view) {
        this.mSocialLogger.logButtonClick(ClickEventButtonId.SOCIAL_SETTINGS_MESSENGER_SIGN_OUT, SurfaceType.MESSENGER_ACCOUNT);
        this.mViewModel.setIsSigningOut(true);
        socialSettingsTabletPanelApp.getDialogManager().showDialog(this.mSignOutDialogDefinition);
    }

    public SocialSettingsMessengerAccount(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        SocialSettingsMessengerAccountBinding inflate = SocialSettingsMessengerAccountBinding.inflate(LayoutInflater.from(context), this, true);
        this.mBinding = inflate;
        SocialSettingsMessengerAccountViewModel socialSettingsMessengerAccountViewModel = new SocialSettingsMessengerAccountViewModel();
        this.mViewModel = socialSettingsMessengerAccountViewModel;
        inflate.setViewModel(socialSettingsMessengerAccountViewModel);
        this.mFBProfileFetcher = new FBProfileFetcher(context);
    }

    private DialogDefinitionCustom getSignOutDialogDefinition(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp) {
        Resources resources = getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("social_settings_messenger_sign_out_modal", resources.getString(R.string.social_settings_messenger_sign_out_dialog_title), resources.getString(R.string.social_settings_messenger_sign_out_dialog_body));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText(SIGN_OUT_ACTION, resources.getString(R.string.social_settings_messenger_account_sign_out_button));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.social_settings_messenger_sign_out_dialog_cancel_btn));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        dialogDefinitionCustom.mDialogResultHandler = new DialogResultHandler(socialSettingsTabletPanelApp) {
            /* class com.oculus.panelapp.socialsettings.views.$$Lambda$SocialSettingsMessengerAccount$Y8Ep4YRp651vgbE4ghiwMnYkwg2 */
            public final /* synthetic */ SocialSettingsTabletPanelApp f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SocialSettingsMessengerAccount.this.lambda$getSignOutDialogDefinition$4$SocialSettingsMessengerAccount(this.f$1, dialogResult);
            }
        };
        return dialogDefinitionCustom;
    }

    private void showToast() {
        Resources resources = getResources();
        new NotificationSender.Builder("oculus_mobile_social_settings_tablet_messenger_sign_out", resources.getString(R.string.social_settings_messenger_sign_out_dialog_sign_out_success_toast_title), resources.getString(R.string.social_settings_messenger_sign_out_dialog_sign_out_success_toast_body), R.drawable.ic_notif_alert).send(getContext());
    }

    public void initialize(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp) {
        this.mImageHandler = socialSettingsTabletPanelApp.getImageHandler();
        this.mSocialLogger = socialSettingsTabletPanelApp.getLogger();
        this.mFBProfileFetcher.fetchFBProfile(new FBProfileFetcher.FetchCallback() {
            /* class com.oculus.panelapp.socialsettings.views.$$Lambda$SocialSettingsMessengerAccount$erTmkxrnpv3uHbTm6A5QqvHtP2w2 */

            @Override // com.oculus.common.socialtablet.fetchers.FBProfileFetcher.FetchCallback
            public final void onFBProfileFetched(String str, String str2) {
                SocialSettingsMessengerAccount.this.lambda$initialize$0$SocialSettingsMessengerAccount(str, str2);
            }
        });
        this.mSignOutDialogDefinition = getSignOutDialogDefinition(socialSettingsTabletPanelApp);
        OCButton oCButton = this.mBinding.messengerAccountSignOutButton;
        oCButton.mEventHandler = socialSettingsTabletPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener(socialSettingsTabletPanelApp) {
            /* class com.oculus.panelapp.socialsettings.views.$$Lambda$SocialSettingsMessengerAccount$eDU_szbhoK6dVEDTVpUmvlujcEg2 */
            public final /* synthetic */ SocialSettingsTabletPanelApp f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SocialSettingsMessengerAccount.this.lambda$initialize$1$SocialSettingsMessengerAccount(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$null$2$SocialSettingsMessengerAccount(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp) {
        showToast();
        this.mSocialLogger.logActionSuccess(ActionId.SOCIAL_SETTINGS_MESSENGER_SIGN_OUT, ClickEventButtonId.SOCIAL_SETTINGS_MESSENGER_SIGN_OUT, SurfaceType.MESSENGER_SIGN_OUT_DIALOG);
        socialSettingsTabletPanelApp.actionNavigate(SystemUXRoute.AUI_PEOPLE, "");
    }

    public /* synthetic */ Object lambda$null$3$SocialSettingsMessengerAccount(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp) throws Exception {
        MessengerVrAccountsContentProviderClient.setIsMessengerAuthenticated(getContext(), false);
        this.mViewModel.setIsSigningOut(false);
        UiThreadExecutor.getInstance().execute(new Runnable(socialSettingsTabletPanelApp) {
            /* class com.oculus.panelapp.socialsettings.views.$$Lambda$SocialSettingsMessengerAccount$u7TLnwNNsJjhi_kqFNa8zuSP7c2 */
            public final /* synthetic */ SocialSettingsTabletPanelApp f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SocialSettingsMessengerAccount.this.lambda$null$2$SocialSettingsMessengerAccount(this.f$1);
            }
        });
        return null;
    }
}
