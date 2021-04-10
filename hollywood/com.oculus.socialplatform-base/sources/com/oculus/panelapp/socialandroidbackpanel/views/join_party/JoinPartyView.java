package com.oculus.panelapp.socialandroidbackpanel.views.join_party;

import X.AnonymousClass1uW;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import com.google.common.base.Stopwatch;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp;
import com.oculus.panelapp.socialandroidbackpanel.databinding.JoinPartyViewBinding;
import com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorCallback;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorType;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorView;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorViewAction;
import com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel;
import com.oculus.socialplatform.R;
import com.oculus.tablet.utils.PartiesBroadcastActions;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.concurrent.TimeUnit;

public class JoinPartyView extends BaseView implements SocialAndroidBackPanelView {
    public static final String CORRELATION_ID_PARAM = "correlation_id";
    public static final String DEEPLINK_TARGET_ID_PARAM = "deeplink_target_id";
    public static final String LINK_NONCE_PARAM = "nonce";
    public static final String PARTY_ID_PARAM = "party_id";
    public static final String SOURCE_PARAM = "source";
    public static final String TAG = LoggingUtil.tag(JoinPartyView.class);
    public JoinPartyViewBinding mBinding;
    public final Context mContext;
    public ErrorCallback mErrorCallback;
    public SocialAndroidBackPanelApp mPanelApp;
    public final JoinPartyViewModel mViewModel;

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    public static String coerceEmptyToNull(String str) {
        if ("".equals(str)) {
            return null;
        }
        return str;
    }

    private void initializePrimaryButton(OCButton oCButton) {
        if (oCButton != null) {
            oCButton.mEventHandler = this.mPanelApp;
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.$$Lambda$JoinPartyView$4QARNgPlSQUxuJ5AiQhdbAKB8Hg2 */

                public final void onClick(View view) {
                    JoinPartyView.this.lambda$initializePrimaryButton$2$JoinPartyView(view);
                }
            });
        }
    }

    private void initializeSecondaryButton(OCButton oCButton) {
        if (oCButton != null) {
            oCButton.mEventHandler = this.mPanelApp;
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.$$Lambda$JoinPartyView$nx2ZaQ561humusRQiqoWhoIFIE2 */

                public final void onClick(View view) {
                    JoinPartyView.this.lambda$initializeSecondaryButton$3$JoinPartyView(view);
                }
            });
        }
    }

    private ErrorViewAction joinErrorAction() {
        ErrorViewAction.ErrorActionBuilder errorActionBuilder = new ErrorViewAction.ErrorActionBuilder(new ErrorView.ErrorViewCallback.Dismiss() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.$$Lambda$JoinPartyView$EK4UuU9lcC6fVnlCzI_YKL7Yv602 */

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorView.ErrorViewCallback.Dismiss
            public final void onDismiss() {
                JoinPartyView.this.lambda$joinErrorAction$6$JoinPartyView();
            }
        });
        errorActionBuilder.mDismissText = R.string.party_error_dialog_dismiss_text;
        errorActionBuilder.mCtaText = Integer.valueOf((int) R.string.party_error_dialog_cta_text);
        errorActionBuilder.mOnCTA = new ErrorView.ErrorViewCallback.CTA() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.$$Lambda$JoinPartyView$epb6AyRRz3YFgtheuiMWmDiWOpM2 */

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorView.ErrorViewCallback.CTA
            public final void onCTA() {
                JoinPartyView.this.lambda$joinErrorAction$7$JoinPartyView();
            }
        };
        return errorActionBuilder.build();
    }

    private void navigateToPartiesPanel(boolean z) {
        SocialAndroidBackPanelApp socialAndroidBackPanelApp;
        SystemUXRoute systemUXRoute;
        String str;
        String str2;
        if (this.mPanelApp.isGKEnabled(Gatekeeper.SOCIAL_NEW_PARTIES_PANEL_APP)) {
            if (z) {
                str2 = SourceConstants.JOIN_PARTY_DIALOG_REDIRECT;
            } else {
                str2 = SourceConstants.JOIN_PARTY_DIALOG;
            }
            Uri build = new Uri.Builder().appendQueryParameter("correlation_id", this.mViewModel.mCorrelationId).appendQueryParameter("source", str2).build();
            socialAndroidBackPanelApp = this.mPanelApp;
            systemUXRoute = SystemUXRoute.AUI_PARTIES;
            str = build.toString();
        } else {
            socialAndroidBackPanelApp = this.mPanelApp;
            systemUXRoute = SystemUXRoute.AUI_SOCIAL;
            str = "";
        }
        socialAndroidBackPanelApp.actionNavigate(systemUXRoute, str);
        this.mPanelApp.quitApp();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView, com.oculus.tablet.view.BaseView
    public void destroy() {
    }

    public /* synthetic */ void lambda$initializePrimaryButton$2$JoinPartyView(View view) {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.JOIN_PARTY_CONFIRM, SurfaceType.JOIN_PARTY, buildLogParams(null));
        this.mBinding.joinPartyPrimaryButton.setLoading(true);
        Stopwatch createStarted = Stopwatch.createStarted();
        this.mViewModel.joinPartyAndStartVoip(this.mContext, new JoinPartyViewModel.Callback(createStarted) {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.$$Lambda$JoinPartyView$7UJKD87KFHOzxPymWPTC8cIgEEs2 */
            public final /* synthetic */ Stopwatch f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel.Callback
            public final void execute() {
                JoinPartyView.this.lambda$null$0$JoinPartyView(this.f$1);
            }
        }, new JoinPartyViewModel.Error(createStarted) {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.$$Lambda$JoinPartyView$PN4ocGd1LrFvAiMrCsb2LKSDAU2 */
            public final /* synthetic */ Stopwatch f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel.Error
            public final void onError(ErrorType errorType) {
                JoinPartyView.this.lambda$null$1$JoinPartyView(this.f$1, errorType);
            }
        });
    }

    public /* synthetic */ void lambda$initializeSecondaryButton$3$JoinPartyView(View view) {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.JOIN_PARTY_CANCEL, SurfaceType.JOIN_PARTY, buildLogParams(null));
        this.mPanelApp.quitApp();
    }

    public /* synthetic */ void lambda$joinErrorAction$6$JoinPartyView() {
        this.mPanelApp.quitApp();
    }

    public /* synthetic */ void lambda$joinErrorAction$7$JoinPartyView() {
        this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_CREATE_VR_INVITE_DIALOG, new Uri.Builder().appendQueryParameter("correlation_id", this.mViewModel.mCorrelationId).appendQueryParameter("source", SourceConstants.JOIN_PARTY_ERROR_DIALOG).build().toString());
        this.mPanelApp.quitApp();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView
    public boolean onControllerBackButton() {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.JOIN_PARTY_CANCEL, SurfaceType.JOIN_PARTY, buildLogParams(null));
        this.mPanelApp.quitApp();
        return true;
    }

    public JoinPartyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mViewModel = new JoinPartyViewModel(context);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0016, code lost:
        if (com.google.common.base.Strings.isNullOrEmpty(r3) == false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<java.lang.String, java.lang.String> buildLogParams(java.lang.Long r6) {
        /*
            r5 = this;
            com.google.common.collect.ImmutableMap$Builder r2 = com.google.common.collect.ImmutableMap.A04()
            com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel r4 = r5.mViewModel
            java.lang.String r3 = r4.mPartyId
            boolean r0 = com.google.common.base.Strings.isNullOrEmpty(r3)
            java.lang.String r1 = "party_id"
            if (r0 == 0) goto L_0x0018
            java.lang.String r3 = r4.mDeeplinkTargetId
            boolean r0 = com.google.common.base.Strings.isNullOrEmpty(r3)
            if (r0 != 0) goto L_0x001b
        L_0x0018:
            r2.put(r1, r3)
        L_0x001b:
            if (r6 == 0) goto L_0x002a
            long r0 = r6.longValue()
            java.lang.String r1 = java.lang.Long.toString(r0)
            java.lang.String r0 = "time_to_complete_ms"
            r2.put(r0, r1)
        L_0x002a:
            com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel r0 = r5.mViewModel
            java.lang.String r1 = r0.mCorrelationId
            if (r1 == 0) goto L_0x0035
            java.lang.String r0 = "correlation_id"
            r2.put(r0, r1)
        L_0x0035:
            com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel r0 = r5.mViewModel
            java.lang.String r1 = r0.mSource
            if (r1 == 0) goto L_0x0040
            java.lang.String r0 = "source"
            r2.put(r0, r1)
        L_0x0040:
            com.google.common.collect.ImmutableMap r0 = r2.build()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyView.buildLogParams(java.lang.Long):java.util.Map");
    }

    public void initialize(SocialAndroidBackPanelApp socialAndroidBackPanelApp, AnonymousClass1uW r4, JoinPartyRequestFactory joinPartyRequestFactory, ErrorCallback errorCallback) {
        super.initialize(socialAndroidBackPanelApp, r4);
        this.mPanelApp = socialAndroidBackPanelApp;
        this.mErrorCallback = errorCallback;
        JoinPartyViewBinding joinPartyViewBinding = (JoinPartyViewBinding) r4;
        this.mBinding = joinPartyViewBinding;
        JoinPartyViewModel joinPartyViewModel = this.mViewModel;
        joinPartyViewModel.mJoinPartyRequestFactory = joinPartyRequestFactory;
        joinPartyViewBinding.setJoinPartyViewModel(joinPartyViewModel);
        JoinPartyViewBinding joinPartyViewBinding2 = this.mBinding;
        initializePrimaryButton(joinPartyViewBinding2.joinPartyPrimaryButton);
        initializeSecondaryButton(joinPartyViewBinding2.joinPartySecondaryButton);
    }

    public /* synthetic */ void lambda$null$0$JoinPartyView(Stopwatch stopwatch) {
        stopwatch.stop();
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        this.mBinding.joinPartyPrimaryButton.setLoading(false);
        getContext().sendBroadcast(new Intent(PartiesBroadcastActions.ACTION_PARTY_JOINED));
        navigateToPartiesPanel(false);
        this.mPanelApp.getLogger().logActionSuccess(ActionId.PARTY_JOIN, ClickEventButtonId.JOIN_PARTY_CONFIRM, SurfaceType.JOIN_PARTY, buildLogParams(Long.valueOf(elapsed)));
    }

    public /* synthetic */ void lambda$null$1$JoinPartyView(Stopwatch stopwatch, ErrorType errorType) {
        stopwatch.stop();
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        this.mBinding.joinPartyPrimaryButton.setLoading(false);
        this.mErrorCallback.onError(errorType, joinErrorAction());
        this.mPanelApp.getLogger().logActionFailure(ActionId.PARTY_JOIN, ClickEventButtonId.JOIN_PARTY_CONFIRM, SurfaceType.JOIN_PARTY, errorType.getMessage(getContext()), buildLogParams(Long.valueOf(elapsed)));
    }

    public /* synthetic */ void lambda$onShow$4$JoinPartyView(Stopwatch stopwatch) {
        stopwatch.stop();
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        JoinPartyViewModel joinPartyViewModel = this.mViewModel;
        String str = joinPartyViewModel.mCurrentPartyId;
        if (str == null || !str.equals(joinPartyViewModel.mPartyId)) {
            this.mPanelApp.getLogger().logImpression(SurfaceType.JOIN_PARTY, buildLogParams(Long.valueOf(elapsed)));
        } else {
            navigateToPartiesPanel(true);
        }
    }

    public /* synthetic */ void lambda$onShow$5$JoinPartyView(Stopwatch stopwatch, Uri uri, ErrorType errorType) {
        stopwatch.stop();
        long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        this.mErrorCallback.onError(errorType, joinErrorAction());
        String str = "party_id";
        if (coerceEmptyToNull(uri.getQueryParameter(str)) == null) {
            str = "deeplink_target_id";
        }
        this.mPanelApp.getLogger().logImpressionFailure(SurfaceType.JOIN_PARTY, String.format("Party join fetch failed for id %s", uri.getQueryParameter(str)), buildLogParams(Long.valueOf(elapsed)));
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView, com.oculus.tablet.view.BaseView
    public void onShow(@Nullable String str) {
        try {
            Uri parse = Uri.parse(str);
            this.mViewModel.mPartyId = coerceEmptyToNull(parse.getQueryParameter("party_id"));
            this.mViewModel.mLinkNonce = coerceEmptyToNull(parse.getQueryParameter("nonce"));
            this.mViewModel.mDeeplinkTargetId = coerceEmptyToNull(parse.getQueryParameter("deeplink_target_id"));
            this.mViewModel.mCorrelationId = coerceEmptyToNull(parse.getQueryParameter("correlation_id"));
            this.mViewModel.mSource = coerceEmptyToNull(parse.getQueryParameter("source"));
            Stopwatch createStarted = Stopwatch.createStarted();
            this.mViewModel.fetch(this.mPanelApp.getGraphQLService(), new JoinPartyViewModel.Callback(createStarted) {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.$$Lambda$JoinPartyView$y4aon59MyKTMn6HlVYV03PUJIHo2 */
                public final /* synthetic */ Stopwatch f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel.Callback
                public final void execute() {
                    JoinPartyView.this.lambda$onShow$4$JoinPartyView(this.f$1);
                }
            }, new JoinPartyViewModel.Error(createStarted, parse) {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.join_party.$$Lambda$JoinPartyView$vXG5xwlYC2EbPNC0vV4JvYtmZb42 */
                public final /* synthetic */ Stopwatch f$1;
                public final /* synthetic */ Uri f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel.Error
                public final void onError(ErrorType errorType) {
                    JoinPartyView.this.lambda$onShow$5$JoinPartyView(this.f$1, this.f$2, errorType);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            this.mPanelApp.getLogger().logImpressionFailure(SurfaceType.JOIN_PARTY, String.format("onShow failed for %s", str));
            this.mErrorCallback.onError(ErrorType.PARTY_GENERAL, joinErrorAction());
        }
    }
}
