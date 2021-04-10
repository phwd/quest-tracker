package com.oculus.panelapp.androiddialog.dialogs.social;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.Dialog;
import com.oculus.panelapp.androiddialog.databinding.SocialPartyPrivacyDialogBinding;
import com.oculus.panelapp.androiddialog.dialogs.error.DialogErrorCallback;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialog;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialogAction;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorType;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel;
import com.oculus.panelapp.androiddialog.logging.social.SocialLogger;
import com.oculus.vrshell.SystemUXRoute;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class SocialPartyPrivacyDialog extends ConstraintLayout implements Dialog {
    private static final String TAG = LoggingUtil.tag(SocialPartyPrivacyDialog.class);
    private SocialPartyPrivacyDialogBinding mBinding;
    private DialogErrorCallback mDialogErrorCallback;
    private AndroidDialogPanelApp mPanelApp;
    private String mPartyId;
    private SocialPartyPrivacyViewModel mPrivacyViewModal;
    private SocialLogger mSocialLogger;

    public SocialPartyPrivacyDialog(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(AndroidDialogPanelApp androidDialogPanelApp, SocialPartyPrivacyDialogBinding socialPartyPrivacyDialogBinding, SocialLogger socialLogger, DialogErrorCallback dialogErrorCallback, String str, SocialPartyPrivacyRequestFactory socialPartyPrivacyRequestFactory) {
        Log.d(TAG, str);
        this.mPanelApp = androidDialogPanelApp;
        this.mBinding = socialPartyPrivacyDialogBinding;
        this.mDialogErrorCallback = dialogErrorCallback;
        this.mPartyId = str;
        this.mSocialLogger = socialLogger;
        this.mPrivacyViewModal = new SocialPartyPrivacyViewModel(getContext(), this.mSocialLogger, socialPartyPrivacyRequestFactory);
        socialPartyPrivacyDialogBinding.setPrivacyViewModel(this.mPrivacyViewModal);
        setupButtons(getContext().getResources());
    }

    private void setupButtons(Resources resources) {
        this.mBinding.closeButton.setEventHandler(this.mPanelApp);
        this.mBinding.closeButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$SocialPartyPrivacyDialog$vGduSrtiSssaVtIDt5djbyMf6k */

            public final void onClick(View view) {
                SocialPartyPrivacyDialog.this.lambda$setupButtons$76$SocialPartyPrivacyDialog(view);
            }
        });
        this.mBinding.joinPolicyToggleMenuItem.toggle.setEventHandler(this.mPanelApp);
        this.mBinding.joinPolicyToggleMenuItem.toggle.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$SocialPartyPrivacyDialog$SQG9qSXFTaaAsP_coJkbaQ3RyS8 */

            public final void onClick(View view) {
                SocialPartyPrivacyDialog.this.lambda$setupButtons$78$SocialPartyPrivacyDialog(view);
            }
        });
        this.mBinding.linkPolicyToggleMenuItem.toggle.setEventHandler(this.mPanelApp);
        this.mBinding.linkPolicyToggleMenuItem.toggle.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$SocialPartyPrivacyDialog$2_Y8wfUWAZDlSTnS18MVNreRXnA */

            public final void onClick(View view) {
                SocialPartyPrivacyDialog.this.lambda$setupButtons$80$SocialPartyPrivacyDialog(view);
            }
        });
        this.mBinding.shareButton.setEventHandler(this.mPanelApp);
        this.mBinding.shareButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$SocialPartyPrivacyDialog$8kOUqcywm1o4hhDasNLj8j_IggQ */

            public final void onClick(View view) {
                SocialPartyPrivacyDialog.this.lambda$setupButtons$81$SocialPartyPrivacyDialog(view);
            }
        });
    }

    public /* synthetic */ void lambda$setupButtons$76$SocialPartyPrivacyDialog(View view) {
        closeDialog();
    }

    public /* synthetic */ void lambda$setupButtons$78$SocialPartyPrivacyDialog(View view) {
        this.mSocialLogger.logButtonClick(ClickEventButtonId.PARTY_PRIVACY_PARTY_TYPE, SurfaceType.PARTY_PRIVACY, "party_id", this.mPartyId);
        this.mPrivacyViewModal.togglePartyType(getContext(), new SocialPartyPrivacyViewModel.OnErrorCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$SocialPartyPrivacyDialog$886FmSkjXebd7JhNc4iRSDeO0Hc */

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.OnErrorCallback
            public final void onError(ErrorType errorType) {
                SocialPartyPrivacyDialog.this.lambda$null$77$SocialPartyPrivacyDialog(errorType);
            }
        });
    }

    public /* synthetic */ void lambda$null$77$SocialPartyPrivacyDialog(ErrorType errorType) {
        this.mDialogErrorCallback.onError(errorType, partyErrorAction());
    }

    public /* synthetic */ void lambda$setupButtons$80$SocialPartyPrivacyDialog(View view) {
        this.mSocialLogger.logButtonClick(ClickEventButtonId.PARTY_PRIVACY_LINK_INVITE, SurfaceType.PARTY_PRIVACY, "party_id", this.mPartyId);
        this.mPrivacyViewModal.toggleLinkInvite(getContext(), new SocialPartyPrivacyViewModel.OnErrorCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$SocialPartyPrivacyDialog$VknRDXaNDCpBhOP0J2JskS9qnsg */

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.OnErrorCallback
            public final void onError(ErrorType errorType) {
                SocialPartyPrivacyDialog.this.lambda$null$79$SocialPartyPrivacyDialog(errorType);
            }
        });
    }

    public /* synthetic */ void lambda$null$79$SocialPartyPrivacyDialog(ErrorType errorType) {
        this.mDialogErrorCallback.onError(errorType, partyErrorAction());
    }

    public /* synthetic */ void lambda$setupButtons$81$SocialPartyPrivacyDialog(View view) {
        this.mSocialLogger.logButtonClick(ClickEventButtonId.PARTY_PRIVACY_SHARE, SurfaceType.PARTY_PRIVACY, "party_id", this.mPartyId);
        navigateToSocialPartyShareSheet();
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public void destroy() {
        this.mBinding.closeButton.setEventHandler(null);
        this.mBinding.closeButton.setOnClickListener(null);
        this.mBinding.joinPolicyToggleMenuItem.toggle.setEventHandler(null);
        this.mBinding.joinPolicyToggleMenuItem.toggle.setOnClickListener(null);
        this.mBinding.linkPolicyToggleMenuItem.toggle.setEventHandler(null);
        this.mBinding.linkPolicyToggleMenuItem.toggle.setOnClickListener(null);
        this.mBinding.shareButton.setEventHandler(null);
        this.mBinding.shareButton.setOnClickListener(null);
        this.mPrivacyViewModal.destroy();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mPrivacyViewModal.fetch(getContext(), this.mPartyId, new SocialPartyPrivacyViewModel.OnSuccessCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$SocialPartyPrivacyDialog$pXlVNTQDLE9LRkR55jBP14GEcJI */

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.OnSuccessCallback
            public final void onSuccess() {
                SocialPartyPrivacyDialog.this.lambda$onAttachedToWindow$82$SocialPartyPrivacyDialog();
            }
        }, new SocialPartyPrivacyViewModel.OnErrorCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$SocialPartyPrivacyDialog$h1ZJfw8jnJg7Elrqd0_Cyjc0sI */

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel.OnErrorCallback
            public final void onError(ErrorType errorType) {
                SocialPartyPrivacyDialog.this.lambda$onAttachedToWindow$83$SocialPartyPrivacyDialog(errorType);
            }
        });
    }

    public /* synthetic */ void lambda$onAttachedToWindow$82$SocialPartyPrivacyDialog() {
        this.mSocialLogger.logImpression(SurfaceType.PARTY_PRIVACY, new HashMap<String, String>() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyDialog.AnonymousClass1 */

            {
                put("party_id", SocialPartyPrivacyDialog.this.mPartyId);
            }
        });
    }

    public /* synthetic */ void lambda$onAttachedToWindow$83$SocialPartyPrivacyDialog(ErrorType errorType) {
        this.mSocialLogger.logImpressionFailure(SurfaceType.PARTY_PRIVACY, String.format("Party privacy fetch failed for id %s", this.mPartyId));
        this.mDialogErrorCallback.onError(errorType, partyErrorAction());
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        closeDialog();
        return true;
    }

    public void navigateToSocialPartyShareSheet() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("deeplinkTargetId", this.mPartyId);
            this.mPanelApp.actionNavigate(SystemUXRoute.SHARE_SHEET_V2, new Uri.Builder().encodedPath("").appendQueryParameter("type", "deeplink_target").appendQueryParameter("source", "parties_privacy_dialog").appendQueryParameter("payload", jSONObject.toString()).build().toString());
        } catch (JSONException e) {
            this.mSocialLogger.logActionFailure(ActionId.NAVIGATE_TO_SHARE_SHEET, ClickEventButtonId.PARTY_PRIVACY_SHARE, SurfaceType.PARTY_PRIVACY, "Error constructing share sheet route", "party_id", this.mPartyId);
            Log.e(TAG, "Error constructing share sheet route", e);
        }
    }

    private ErrorDialogAction partyErrorAction() {
        return ErrorDialogAction.onDismiss(new ErrorDialog.ErrorDialogCallback.Dismiss() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.$$Lambda$SocialPartyPrivacyDialog$WzDsuOaLjUaC_0C9gBjKkoi7A0s */

            @Override // com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialog.ErrorDialogCallback.Dismiss
            public final void onDismiss() {
                SocialPartyPrivacyDialog.this.lambda$partyErrorAction$84$SocialPartyPrivacyDialog();
            }
        }).build();
    }

    public /* synthetic */ void lambda$partyErrorAction$84$SocialPartyPrivacyDialog() {
        this.mPanelApp.closeDialog();
    }

    private void closeDialog() {
        this.mSocialLogger.logButtonClick(ClickEventButtonId.PARTY_PRIVACY_CLOSE, SurfaceType.PARTY_PRIVACY, "party_id", this.mPartyId);
        this.mPanelApp.closeDialog();
    }
}
