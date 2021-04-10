package com.oculus.panelapp.socialandroidbackpanel.views.party_privacy;

import X.AnonymousClass1uW;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.socialandroidbackpanel.SocialAndroidBackPanelApp;
import com.oculus.panelapp.socialandroidbackpanel.databinding.PartyPrivacyViewBinding;
import com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType;
import com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorCallback;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorType;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorView;
import com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorViewAction;
import com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class PartyPrivacyView extends BaseView implements SocialAndroidBackPanelView {
    public static final String CORRELATION_ID_PARAM = "correlation_id";
    public static final String SOURCE_PARAM = "source";
    public static final String TAG = LoggingUtil.tag(PartyPrivacyView.class);
    public PartyPrivacyViewBinding mBinding;
    public ErrorCallback mErrorCallback;
    public SocialAndroidBackPanelApp mPanelApp;
    public final PartyPrivacyViewModel mViewModel = new PartyPrivacyViewModel();

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    /* renamed from: com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyView$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$socialandroidbackpanel$graphql$PartyPrivacyType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType[] r0 = com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyView.AnonymousClass1.$SwitchMap$com$oculus$panelapp$socialandroidbackpanel$graphql$PartyPrivacyType = r2
                com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType r0 = com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType.CLOSED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType r0 = com.oculus.panelapp.socialandroidbackpanel.graphql.PartyPrivacyType.JOINABLE_BY_FRIENDS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyView.AnonymousClass1.<clinit>():void");
        }
    }

    public static String coerceEmptyToNull(String str) {
        if ("".equals(str)) {
            return null;
        }
        return str;
    }

    private ErrorViewAction partyErrorAction() {
        return new ErrorViewAction.ErrorActionBuilder(new ErrorView.ErrorViewCallback.Dismiss() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyView$9iaN5a7Hp3FAvya5lzcUt_Vaw2 */

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.error.ErrorView.ErrorViewCallback.Dismiss
            public final void onDismiss() {
                PartyPrivacyView.this.lambda$partyErrorAction$10$PartyPrivacyView();
            }
        }).build();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView, com.oculus.tablet.view.BaseView
    public void destroy() {
    }

    public /* synthetic */ void lambda$initialize$0$PartyPrivacyView(View view) {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.PARTY_PRIVACY_CLOSE, SurfaceType.PARTY_PRIVACY, buildLogParams());
        this.mPanelApp.quitApp();
    }

    public /* synthetic */ void lambda$initialize$3$PartyPrivacyView(View view) {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.PARTY_PRIVACY_PARTY_TYPE, SurfaceType.PARTY_PRIVACY, buildLogParams());
        PartyPrivacyViewModel partyPrivacyViewModel = this.mViewModel;
        partyPrivacyViewModel.togglePartyType(this.mPanelApp.getGraphQLService(), new PartyPrivacyViewModel.Callback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyView$Jz9KKqGya_Z9ujT2APEB6LpXsg2 */

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel.Callback
            public final void execute() {
                PartyPrivacyView.this.lambda$null$1$PartyPrivacyView();
            }
        }, new PartyPrivacyViewModel.Callback(partyPrivacyViewModel.mPartyType) {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyView$LcVpGunCE6bOxP4JRS3ocKc1cE2 */
            public final /* synthetic */ PartyPrivacyType f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel.Callback
            public final void execute() {
                PartyPrivacyView.this.lambda$null$2$PartyPrivacyView(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$initialize$6$PartyPrivacyView(View view) {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.PARTY_PRIVACY_LINK_INVITE, SurfaceType.PARTY_PRIVACY, buildLogParams());
        PartyPrivacyViewModel partyPrivacyViewModel = this.mViewModel;
        partyPrivacyViewModel.toggleLinkInvite(this.mPanelApp.getGraphQLService(), new PartyPrivacyViewModel.Callback() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyView$xXdhl_l8zf1mFwjQ7eS8eGo5ew2 */

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel.Callback
            public final void execute() {
                PartyPrivacyView.this.lambda$null$4$PartyPrivacyView();
            }
        }, new PartyPrivacyViewModel.Callback(partyPrivacyViewModel.mHasLinkInvite) {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyView$y8lMGN6u5uK0A5CYkCkxLX7KmiQ2 */
            public final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel.Callback
            public final void execute() {
                PartyPrivacyView.this.lambda$null$5$PartyPrivacyView(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$initialize$7$PartyPrivacyView(View view) {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.PARTY_PRIVACY_SHARE, SurfaceType.PARTY_PRIVACY, buildLogParams());
        navigateToSocialPartyShareSheet();
    }

    public /* synthetic */ void lambda$null$1$PartyPrivacyView() {
        this.mPanelApp.getLogger().logActionSuccess(partyTypeToLoggingAction(this.mViewModel.mPartyType), ClickEventButtonId.PARTY_PRIVACY_LINK_INVITE, SurfaceType.PARTY_PRIVACY, buildLogParams());
    }

    public /* synthetic */ void lambda$null$2$PartyPrivacyView(PartyPrivacyType partyPrivacyType) {
        PartyPrivacyType partyPrivacyType2 = PartyPrivacyType.CLOSED;
        if (partyPrivacyType == partyPrivacyType2) {
            partyPrivacyType2 = PartyPrivacyType.JOINABLE_BY_FRIENDS;
        }
        this.mPanelApp.getLogger().logActionFailure(partyTypeToLoggingAction(partyPrivacyType2), ClickEventButtonId.PARTY_PRIVACY_PARTY_TYPE, SurfaceType.PARTY_PRIVACY, String.format("Error setting party join type to %s", partyPrivacyType2.toString()), buildLogParams());
        this.mErrorCallback.onError(ErrorType.PARTY_PRIVACY, partyErrorAction());
    }

    public /* synthetic */ void lambda$null$4$PartyPrivacyView() {
        ActionId actionId;
        if (this.mViewModel.mHasLinkInvite) {
            actionId = ActionId.PARTY_TOGGLE_LINK_INVITE_ON;
        } else {
            actionId = ActionId.PARTY_TOGGLE_LINK_INVITE_OFF;
        }
        this.mPanelApp.getLogger().logActionSuccess(actionId, ClickEventButtonId.PARTY_PRIVACY_LINK_INVITE, SurfaceType.PARTY_PRIVACY, buildLogParams());
    }

    public /* synthetic */ void lambda$null$5$PartyPrivacyView(boolean z) {
        ActionId actionId;
        String str;
        if (z) {
            actionId = ActionId.PARTY_TOGGLE_LINK_INVITE_OFF;
            str = "Error deactivating party link invite";
        } else {
            actionId = ActionId.PARTY_TOGGLE_LINK_INVITE_ON;
            str = "Error activating party link invite";
        }
        this.mPanelApp.getLogger().logActionFailure(actionId, ClickEventButtonId.PARTY_PRIVACY_LINK_INVITE, SurfaceType.PARTY_PRIVACY, str, buildLogParams());
        this.mErrorCallback.onError(ErrorType.PARTY_PRIVACY, partyErrorAction());
    }

    public /* synthetic */ void lambda$onShow$8$PartyPrivacyView() {
        this.mPanelApp.getLogger().logImpression(SurfaceType.PARTY_PRIVACY, buildLogParams());
    }

    public /* synthetic */ void lambda$onShow$9$PartyPrivacyView() {
        this.mPanelApp.getLogger().logImpressionFailure(SurfaceType.PARTY_PRIVACY, String.format("Party privacy fetch failed for id %s", this.mViewModel.mPartyId));
        this.mErrorCallback.onError(ErrorType.PARTY_PRIVACY, partyErrorAction());
    }

    public /* synthetic */ void lambda$partyErrorAction$10$PartyPrivacyView() {
        this.mPanelApp.quitApp();
    }

    public void navigateToSocialPartyShareSheet() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("deeplinkTargetId", this.mViewModel.mPartyId);
            this.mPanelApp.actionNavigate(SystemUXRoute.SHARE_SHEET_V2, new Uri.Builder().encodedPath("").appendQueryParameter("type", "deeplink_target").appendQueryParameter("source", PartyPrivacyView.class.toString()).appendQueryParameter("payload", jSONObject.toString()).build().toString());
        } catch (JSONException e) {
            this.mPanelApp.getLogger().logActionFailure(ActionId.NAVIGATE_TO_SHARE_SHEET, ClickEventButtonId.PARTY_PRIVACY_SHARE, SurfaceType.PARTY_PRIVACY, "Error constructing share sheet route", "party_id", this.mViewModel.mPartyId);
            Log.e(TAG, "Error constructing share sheet route", e);
        }
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView
    public boolean onControllerBackButton() {
        this.mPanelApp.quitApp();
        return true;
    }

    public PartyPrivacyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private Map<String, String> buildLogParams() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        String str = this.mViewModel.mPartyId;
        if (!Strings.isNullOrEmpty(str)) {
            A04.put("party_id", str);
        }
        String str2 = this.mViewModel.mCorrelationId;
        if (str2 != null) {
            A04.put("correlation_id", str2);
        }
        String str3 = this.mViewModel.mSource;
        if (str3 != null) {
            A04.put("source", str3);
        }
        return A04.build();
    }

    private ActionId partyTypeToLoggingAction(PartyPrivacyType partyPrivacyType) {
        switch (partyPrivacyType.ordinal()) {
            case 0:
                return ActionId.PARTY_SET_JOIN_TYPE_INVITE_ONLY;
            case 1:
                return ActionId.PARTY_SET_JOIN_TYPE_FRIENDS_OF_PARTICIPANTS;
            default:
                return ActionId.PARTY_SET_JOIN_TYPE_UNKNOWN;
        }
    }

    public void initialize(SocialAndroidBackPanelApp socialAndroidBackPanelApp, AnonymousClass1uW r6, ErrorCallback errorCallback) {
        super.initialize(socialAndroidBackPanelApp, r6);
        this.mPanelApp = socialAndroidBackPanelApp;
        this.mErrorCallback = errorCallback;
        PartyPrivacyViewBinding partyPrivacyViewBinding = (PartyPrivacyViewBinding) r6;
        this.mBinding = partyPrivacyViewBinding;
        partyPrivacyViewBinding.setPartyPrivacyViewModel(this.mViewModel);
        PartyPrivacyViewBinding partyPrivacyViewBinding2 = this.mBinding;
        OCButton oCButton = partyPrivacyViewBinding2.partyPrivacyCloseButton;
        SocialAndroidBackPanelApp socialAndroidBackPanelApp2 = this.mPanelApp;
        oCButton.mEventHandler = socialAndroidBackPanelApp2;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyView$ln_mNVNsw0WvG5upuIxJ5It58Ws2 */

            public final void onClick(View view) {
                PartyPrivacyView.this.lambda$initialize$0$PartyPrivacyView(view);
            }
        });
        OCToggle oCToggle = partyPrivacyViewBinding2.joinPolicyToggleMenuItem.toggle;
        oCToggle.mEventHandler = socialAndroidBackPanelApp2;
        oCToggle.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyView$W66uU4fznQSn6GZdRiX3KxtlY02 */

            public final void onClick(View view) {
                PartyPrivacyView.this.lambda$initialize$3$PartyPrivacyView(view);
            }
        });
        OCToggle oCToggle2 = partyPrivacyViewBinding2.linkPolicyToggleMenuItem.toggle;
        oCToggle2.mEventHandler = socialAndroidBackPanelApp2;
        oCToggle2.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyView$3VZbgAH2z_N4aUOPkgUjBd25nNw2 */

            public final void onClick(View view) {
                PartyPrivacyView.this.lambda$initialize$6$PartyPrivacyView(view);
            }
        });
        OCButton oCButton2 = partyPrivacyViewBinding2.partyPrivacyShareButton;
        oCButton2.mEventHandler = socialAndroidBackPanelApp2;
        oCButton2.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyView$wK7YIjTlBvl39Xi8n5ik3C5TTno2 */

            public final void onClick(View view) {
                PartyPrivacyView.this.lambda$initialize$7$PartyPrivacyView(view);
            }
        });
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.views.SocialAndroidBackPanelView, com.oculus.tablet.view.BaseView
    public void onShow(@Nullable String str) {
        try {
            Uri parse = Uri.parse(str);
            this.mViewModel.mPartyId = coerceEmptyToNull(parse.getQueryParameter("party_id"));
            this.mViewModel.mCorrelationId = parse.getQueryParameter("correlation_id");
            this.mViewModel.mSource = parse.getQueryParameter("source");
            this.mViewModel.fetch(this.mPanelApp.getGraphQLService(), new PartyPrivacyViewModel.Callback() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyView$FRi3pGss8IxA8t2ysyWy67s7Os2 */

                @Override // com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel.Callback
                public final void execute() {
                    PartyPrivacyView.this.lambda$onShow$8$PartyPrivacyView();
                }
            }, new PartyPrivacyViewModel.Callback() {
                /* class com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.$$Lambda$PartyPrivacyView$ojmD82iSQq_Saveu2lgB6bb1CU2 */

                @Override // com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel.Callback
                public final void execute() {
                    PartyPrivacyView.this.lambda$onShow$9$PartyPrivacyView();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            this.mPanelApp.getLogger().logImpressionFailure(SurfaceType.PARTY_PRIVACY, String.format("onShow failed for %s", str));
            this.mErrorCallback.onError(ErrorType.PARTY_PRIVACY, partyErrorAction());
        }
    }
}
