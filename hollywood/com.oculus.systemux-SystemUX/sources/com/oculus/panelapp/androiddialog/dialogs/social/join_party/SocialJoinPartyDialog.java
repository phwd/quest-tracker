package com.oculus.panelapp.androiddialog.dialogs.social.join_party;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.Dialog;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.databinding.SocialJoinPartyDialogBinding;
import com.oculus.panelapp.androiddialog.dialogs.error.DialogErrorCallback;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialog;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialogAction;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorType;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserRequestFactory;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel;
import com.oculus.tablet.utils.PartiesBroadcastActions;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class SocialJoinPartyDialog extends ConstraintLayout implements Dialog {
    private SocialJoinPartyDialogBinding mBinding;
    private DialogErrorCallback mDialogErrorCallback;
    private AndroidDialogPanelApp mPanelApp;
    private PartyInviteViewModel mPartyViewModel;
    private SocialLogger mSocialLogger;
    private SocialUserViewModel mSocialViewerModel;

    public SocialJoinPartyDialog(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SocialJoinPartyDialog(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initialize(AndroidDialogPanelApp androidDialogPanelApp, SocialJoinPartyDialogBinding socialJoinPartyDialogBinding, PartyInviteRequestFactory partyInviteRequestFactory, SocialUserRequestFactory socialUserRequestFactory, DialogErrorCallback dialogErrorCallback, SocialLogger socialLogger, JSONObject jSONObject) {
        this.mPanelApp = androidDialogPanelApp;
        this.mBinding = socialJoinPartyDialogBinding;
        this.mDialogErrorCallback = dialogErrorCallback;
        this.mSocialLogger = socialLogger;
        this.mPartyViewModel = new PartyInviteViewModel(getContext(), partyInviteRequestFactory, socialUserRequestFactory);
        this.mSocialViewerModel = new SocialUserViewModel(socialUserRequestFactory);
        this.mSocialViewerModel.registerObserver(this.mPartyViewModel);
        this.mPartyViewModel.setPartyId(validNonEmpty(jSONObject.optString("party_id")));
        this.mPartyViewModel.setDeeplinkTargetId(validNonEmpty(jSONObject.optString("deeplink_target_id")));
        this.mPartyViewModel.setLinkNonce(validNonEmpty(jSONObject.optString(ServiceContract.EXTRA_NONCE)));
        this.mPartyViewModel.setCorrelationId(validNonEmpty(jSONObject.optString(LoggingConstants.CORRELATION_ID)));
        this.mPartyViewModel.setSource(validNonEmpty(jSONObject.optString("source")));
        socialJoinPartyDialogBinding.setPartyViewModel(this.mPartyViewModel);
        socialJoinPartyDialogBinding.setViewerViewModel(this.mSocialViewerModel);
        setupButtons(getContext().getResources());
    }

    private void setupButtons(Resources resources) {
        this.mBinding.socialJoinPartyPrimaryButton.setText(resources.getString(R.string.social_join_party_dialog_confirm));
        this.mBinding.socialJoinPartyPrimaryButton.setEventHandler(this.mPanelApp);
        this.mBinding.socialJoinPartyPrimaryButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.$$Lambda$SocialJoinPartyDialog$W3RY0lDEwn_Zq7oWU_JXl4x2VCg */

            public final void onClick(View view) {
                SocialJoinPartyDialog.this.lambda$setupButtons$112$SocialJoinPartyDialog(view);
            }
        });
        this.mBinding.socialJoinPartySecondaryButton.setText(resources.getString(R.string.social_join_party_dialog_cancel));
        this.mBinding.socialJoinPartySecondaryButton.setEventHandler(this.mPanelApp);
        this.mBinding.socialJoinPartySecondaryButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.$$Lambda$SocialJoinPartyDialog$GkzVa5c9rWo0LRDpM1PZRbqGw6s */

            public final void onClick(View view) {
                SocialJoinPartyDialog.this.lambda$setupButtons$113$SocialJoinPartyDialog(view);
            }
        });
    }

    public /* synthetic */ void lambda$setupButtons$112$SocialJoinPartyDialog(View view) {
        this.mSocialLogger.logButtonClick(ClickEventButtonId.JOIN_PARTY_CONFIRM, SurfaceType.JOIN_PARTY, buildLogParams(null));
        this.mBinding.socialJoinPartyPrimaryButton.setLoading(true);
        Stopwatch createStarted = Stopwatch.createStarted();
        this.mPartyViewModel.joinParty(getContext(), new PartyInviteViewModel.PartyDataCallback.Success(createStarted) {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.$$Lambda$SocialJoinPartyDialog$By6f4z5Wt13jUyI78ouxo6OGJkk */
            private final /* synthetic */ Stopwatch f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel.PartyDataCallback.Success
            public final void onSuccess() {
                SocialJoinPartyDialog.this.lambda$null$110$SocialJoinPartyDialog(this.f$1);
            }
        }, new PartyInviteViewModel.PartyDataCallback.Error(createStarted) {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.$$Lambda$SocialJoinPartyDialog$DWfLsl6FrwNWsIkUaph238kT7g */
            private final /* synthetic */ Stopwatch f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel.PartyDataCallback.Error
            public final void onError(ErrorType errorType) {
                SocialJoinPartyDialog.this.lambda$null$111$SocialJoinPartyDialog(this.f$1, errorType);
            }
        });
    }

    public /* synthetic */ void lambda$null$110$SocialJoinPartyDialog(Stopwatch stopwatch) {
        long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
        this.mBinding.socialJoinPartyPrimaryButton.setLoading(false);
        this.mPanelApp.closeDialog();
        getContext().sendBroadcast(new Intent(PartiesBroadcastActions.ACTION_PARTY_JOINED));
        navigateToPartiesPanel(false);
        this.mSocialLogger.logActionSuccess(ActionId.PARTY_JOIN, ClickEventButtonId.JOIN_PARTY_CONFIRM, SurfaceType.JOIN_PARTY, buildLogParams(Long.valueOf(elapsed)));
    }

    public /* synthetic */ void lambda$null$111$SocialJoinPartyDialog(Stopwatch stopwatch, ErrorType errorType) {
        long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
        this.mBinding.socialJoinPartyPrimaryButton.setLoading(false);
        this.mDialogErrorCallback.onError(errorType, partyErrorAction());
        this.mSocialLogger.logActionFailure(ActionId.PARTY_JOIN, ClickEventButtonId.JOIN_PARTY_CONFIRM, SurfaceType.JOIN_PARTY, errorType.getMessage(getContext()), buildLogParams(Long.valueOf(elapsed)));
    }

    public /* synthetic */ void lambda$setupButtons$113$SocialJoinPartyDialog(View view) {
        this.mSocialLogger.logButtonClick(ClickEventButtonId.JOIN_PARTY_CANCEL, SurfaceType.JOIN_PARTY, buildLogParams(null));
        this.mPanelApp.closeDialog();
    }

    private ErrorDialogAction partyErrorAction() {
        return ErrorDialogAction.onDismiss(new ErrorDialog.ErrorDialogCallback.Dismiss() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.$$Lambda$SocialJoinPartyDialog$xJ9zmD6rWKgfkNIXHnhJxXjtGnY */

            @Override // com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialog.ErrorDialogCallback.Dismiss
            public final void onDismiss() {
                SocialJoinPartyDialog.this.lambda$partyErrorAction$114$SocialJoinPartyDialog();
            }
        }).withDismissText(R.string.party_error_dialog_dismiss_text).withCTAText(R.string.party_error_dialog_cta_text).withCTA(new ErrorDialog.ErrorDialogCallback.CTA() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.$$Lambda$SocialJoinPartyDialog$xcDXhk10bs529URh9ZsUR5AkvjM */

            @Override // com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialog.ErrorDialogCallback.CTA
            public final void onCTA() {
                SocialJoinPartyDialog.this.lambda$partyErrorAction$115$SocialJoinPartyDialog();
            }
        }).build();
    }

    public /* synthetic */ void lambda$partyErrorAction$114$SocialJoinPartyDialog() {
        this.mPanelApp.closeDialog();
    }

    public /* synthetic */ void lambda$partyErrorAction$115$SocialJoinPartyDialog() {
        this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_CREATE_VR_INVITE_DIALOG, new Uri.Builder().appendQueryParameter(LoggingConstants.CORRELATION_ID, this.mPartyViewModel.getCorrelationId()).appendQueryParameter("source", SourceConstants.JOIN_PARTY_ERROR_DIALOG).build().toString());
    }

    private void navigateToPartiesPanel(boolean z) {
        if (this.mPanelApp.getDeviceConfig(Gatekeeper.SOCIAL_NEW_PARTIES_PANEL_APP)) {
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_PARTIES, new Uri.Builder().appendQueryParameter(LoggingConstants.CORRELATION_ID, this.mPartyViewModel.getCorrelationId()).appendQueryParameter("source", z ? SourceConstants.JOIN_PARTY_DIALOG_REDIRECT : SourceConstants.JOIN_PARTY_DIALOG).build().toString());
        } else {
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_SOCIAL, "");
        }
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public void destroy() {
        this.mSocialViewerModel.unregisterObserver(this.mPartyViewModel);
        this.mPartyViewModel.destroy();
        this.mSocialViewerModel.destroy();
        this.mSocialLogger.destroy();
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        this.mPanelApp.closeDialog();
        this.mSocialLogger.logButtonClick(ClickEventButtonId.JOIN_PARTY_CANCEL, SurfaceType.JOIN_PARTY, buildLogParams(null));
        return true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Stopwatch createStarted = Stopwatch.createStarted();
        this.mPartyViewModel.fetch(new PartyInviteViewModel.PartyDataCallback.Success(createStarted) {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.$$Lambda$SocialJoinPartyDialog$5S8INdO4E4VgcUKvAamq39grNw */
            private final /* synthetic */ Stopwatch f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel.PartyDataCallback.Success
            public final void onSuccess() {
                SocialJoinPartyDialog.this.lambda$onAttachedToWindow$116$SocialJoinPartyDialog(this.f$1);
            }
        }, new PartyInviteViewModel.PartyDataCallback.Error(createStarted) {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.join_party.$$Lambda$SocialJoinPartyDialog$xcG3B43cixNeuT9KG3sc1C7BqBI */
            private final /* synthetic */ Stopwatch f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel.PartyDataCallback.Error
            public final void onError(ErrorType errorType) {
                SocialJoinPartyDialog.this.lambda$onAttachedToWindow$117$SocialJoinPartyDialog(this.f$1, errorType);
            }
        });
        this.mSocialViewerModel.fetch();
    }

    public /* synthetic */ void lambda$onAttachedToWindow$116$SocialJoinPartyDialog(Stopwatch stopwatch) {
        long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
        if (this.mPartyViewModel.getCurrentPartyId() == null || !this.mPartyViewModel.getCurrentPartyId().equals(this.mPartyViewModel.getPartyId())) {
            this.mSocialLogger.logImpression(SurfaceType.JOIN_PARTY, buildLogParams(Long.valueOf(elapsed)));
            return;
        }
        this.mPanelApp.closeDialog();
        navigateToPartiesPanel(true);
    }

    public /* synthetic */ void lambda$onAttachedToWindow$117$SocialJoinPartyDialog(Stopwatch stopwatch, ErrorType errorType) {
        long elapsed = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
        this.mDialogErrorCallback.onError(errorType, partyErrorAction());
        this.mSocialLogger.logImpressionFailure(SurfaceType.JOIN_PARTY, String.format("Party join fetch failed for id %s", this.mPartyViewModel.getPartyId()), buildLogParams(Long.valueOf(elapsed)));
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mSocialViewerModel.unregisterObserver(this.mPartyViewModel);
        this.mPartyViewModel.destroy();
        this.mSocialViewerModel.destroy();
    }

    private static String validNonEmpty(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        return str;
    }

    private Map<String, String> buildLogParams(Long l) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        if (!Strings.isNullOrEmpty(this.mPartyViewModel.getPartyId())) {
            builder.put("party_id", this.mPartyViewModel.getPartyId());
        }
        if (l != null) {
            builder.put(LoggingConstants.TIME_TO_COMPLETE_MS, Long.toString(l.longValue()));
        }
        if (this.mPartyViewModel.getCorrelationId() != null) {
            builder.put(LoggingConstants.CORRELATION_ID, this.mPartyViewModel.getCorrelationId());
        }
        if (this.mPartyViewModel.getSource() != null) {
            builder.put("source", this.mPartyViewModel.getSource());
        }
        return builder.build();
    }
}
