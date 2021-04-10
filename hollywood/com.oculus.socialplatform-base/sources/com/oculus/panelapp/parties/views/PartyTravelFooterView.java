package com.oculus.panelapp.parties.views;

import X.AnonymousClass006;
import X.AnonymousClass1uW;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.horizon.platformsdk.DeeplinkIntentUtils;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.horizoncontent.social.SocialGroupLaunchResponse;
import com.oculus.horizoncontent.social.SocialGroupLaunchState;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.databinding.PartyTravelFooterBinding;
import com.oculus.panelapp.parties.utils.PartyTravelNetworkHelper;
import com.oculus.panelapp.parties.views.actions.PartyActionHandler;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class PartyTravelFooterView extends BaseView implements PartyDataObserver {
    public static final long GROUP_LAUNCH_DELAY_MS = 10000;
    public static final String TAG = LoggingUtil.tag(PartyTravelFooterView.class);
    public PartyTravelFooterBinding mBinding;
    public final Context mContext;
    public Handler mHandler;
    public String mImageUri;
    public PartiesTabletPanelApp mPanelApp;
    public SocialParty mParty;
    public PartyTravelFooterViewModel mPartyTravelFooterViewModel;
    public PartyTravelNetworkHelper mPartyTravelNetworkHelper;

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    /* renamed from: com.oculus.panelapp.parties.views.PartyTravelFooterView$6  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass6 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizoncontent$social$SocialGroupLaunchResponse;
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$parties$utils$PartyTravelStateUtils$PartyTravelState;

        /* JADX WARNING: Can't wrap try/catch for region: R(43:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|41|42|43|44|45|46|48) */
        /* JADX WARNING: Can't wrap try/catch for region: R(44:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|41|42|43|44|45|46|48) */
        /* JADX WARNING: Can't wrap try/catch for region: R(45:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|41|42|43|44|45|46|48) */
        /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x002d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0070 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x008e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0098 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00a2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00bd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00c5 */
        static {
            /*
            // Method dump skipped, instructions count: 206
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.views.PartyTravelFooterView.AnonymousClass6.<clinit>():void");
        }
    }

    private String buildSocialActivityDeeplink() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(DeeplinkIntentUtils.INTENT_KEY_SOCIAL_LAUNCH, this.mParty.mSocialActivityDeeplink);
        return jSONObject.toString();
    }

    private void cancelGroupLaunch() {
        this.mHandler.removeCallbacksAndMessages(null);
    }

    private void launchDestinationSelector(String str) {
        this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_CREATE_VR_INVITE_DIALOG, new Uri.Builder().appendQueryParameter("party_id", str).appendQueryParameter("correlation_id", UUID.randomUUID().toString()).appendQueryParameter("source", SourceConstants.PARTY_TRAVEL_FOOTER_DESTINATION_SELECT).build().toString());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logActionFailure(ClickEventButtonId clickEventButtonId, ActionId actionId, String str) {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            this.mPanelApp.mSocialLogger.logActionFailure(actionId, clickEventButtonId, SurfaceType.PARTIES_FOOTER, str, "party_id", socialParty.mID);
        } else {
            this.mPanelApp.mSocialLogger.logActionFailure(actionId, clickEventButtonId, SurfaceType.PARTIES_FOOTER, str);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logActionSuccess(ClickEventButtonId clickEventButtonId, ActionId actionId) {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            this.mPanelApp.mSocialLogger.logActionSuccess(actionId, clickEventButtonId, SurfaceType.PARTIES_FOOTER, "party_id", socialParty.mID);
        } else {
            this.mPanelApp.mSocialLogger.logActionSuccess(actionId, clickEventButtonId, SurfaceType.PARTIES_FOOTER);
        }
    }

    private void maybeReloadImage() {
        String str;
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = socialParty.mSocialProposedGroupLaunchAppDestination;
            if (socialGroupLaunchAppDestination != null) {
                str = socialGroupLaunchAppDestination.mImageUrl;
            } else if (!TextUtils.isEmpty(socialParty.mSocialActivityIconUrl)) {
                str = this.mParty.mSocialActivityIconUrl;
            } else {
                str = null;
            }
            if (this.mImageUri == null) {
                if (str == null) {
                    return;
                }
            } else if (str == null) {
                this.mImageUri = null;
                this.mBinding.travelFooterPreview.setImageDrawable(this.mContext.getDrawable(R.drawable.oc_icon_destination_filled_48_d2d2d2));
            }
            if (!str.equals(this.mImageUri)) {
                this.mImageUri = str;
                this.mPanelApp.mImageHandler.loadToView(str, Integer.MIN_VALUE, Integer.MIN_VALUE, this.mBinding.travelFooterPreview);
            }
        }
    }

    private void maybeShowGroupLaunchDialog() {
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination;
        SocialParty socialParty = this.mParty;
        if (socialParty != null && !socialParty.mPartyLeader.mID.equals(this.mPanelApp.getLocalUserId()) && (socialGroupLaunchAppDestination = this.mParty.mSocialProposedGroupLaunchAppDestination) != null && socialGroupLaunchAppDestination.mGroupLaunchState == SocialGroupLaunchState.PREPARING && socialGroupLaunchAppDestination.getUserResponse(this.mPanelApp.getLocalUserId()) == SocialGroupLaunchResponse.ACCEPT) {
            showGroupLaunchAppLaunchDialog(socialGroupLaunchAppDestination);
        }
    }

    private void onClickCreateGroupLaunch() {
        if (this.mParty != null) {
            logButtonClick(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_CREATE);
            launchDestinationSelector(this.mParty.mID);
        }
    }

    private void onClickLaunchSocialActivity() {
        if (this.mParty != null) {
            try {
                logButtonClick(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_TRAVEL_TO_LAUNCH_APP);
                this.mPanelApp.launchApp(this.mParty.mSocialActivityApplicationPackageName, buildSocialActivityDeeplink());
                logActionSuccess(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_TRAVEL_TO_LAUNCH_APP, ActionId.PARTY_FOOTER_GO_TO_APP_LAUNCH);
            } catch (JSONException e) {
                Log.e(TAG, "Error launching social activity", e);
                logActionFailure(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_TRAVEL_TO_LAUNCH_APP, ActionId.PARTY_FOOTER_GO_TO_APP_LAUNCH, e.getMessage());
            }
        }
    }

    private void onClickSelectNewApp() {
        if (this.mParty != null) {
            logButtonClick(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_SELECT_NEW_DESTINATION);
            launchDestinationSelector(this.mParty.mID);
        }
    }

    private void onClickViewAppDetails() {
        PartiesTabletPanelApp partiesTabletPanelApp;
        SystemUXRoute systemUXRoute;
        StringBuilder sb;
        String str;
        if (this.mParty != null) {
            logButtonClick(ClickEventButtonId.PARTY_TRAVEL_FOOTER_VIEW_APP_DETAILS);
            SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = this.mParty.mSocialProposedGroupLaunchAppDestination;
            if (socialGroupLaunchAppDestination != null && !TextUtils.isEmpty(socialGroupLaunchAppDestination.mAppID)) {
                partiesTabletPanelApp = this.mPanelApp;
                systemUXRoute = SystemUXRoute.STORE;
                sb = new StringBuilder();
                sb.append(DeeplinkIntentUtils.HOME_LINK_PDP);
                str = this.mParty.mSocialProposedGroupLaunchAppDestination.mAppID;
            } else if (!TextUtils.isEmpty(this.mParty.mSocialActivityId) && !TextUtils.isEmpty(this.mParty.mSocialActivityApplicationId)) {
                partiesTabletPanelApp = this.mPanelApp;
                systemUXRoute = SystemUXRoute.STORE;
                sb = new StringBuilder();
                sb.append(DeeplinkIntentUtils.HOME_LINK_PDP);
                str = this.mParty.mSocialActivityApplicationId;
            } else {
                return;
            }
            sb.append(str);
            partiesTabletPanelApp.actionNavigate(systemUXRoute, sb.toString());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showGroupLaunchAppLaunchDialog(@NonNull SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        DialogDefinitionBase appLaunchDialog = PartiesGroupLaunchDialogs.getAppLaunchDialog(this.mContext.getResources(), socialGroupLaunchAppDestination);
        appLaunchDialog.mDialogResultHandler = new DialogResultHandler(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_APP_LAUNCH_DIALOG_LEADER_VIEW_CANCEL, socialGroupLaunchAppDestination) {
            /* class com.oculus.panelapp.parties.views.$$Lambda$PartyTravelFooterView$71SPKsnmLAPjNdKc7WkUV3hG7o2 */
            public final /* synthetic */ ClickEventButtonId f$1;
            public final /* synthetic */ SocialGroupLaunchAppDestination f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return PartyTravelFooterView.this.lambda$showGroupLaunchAppLaunchDialog$3$PartyTravelFooterView(this.f$1, this.f$2, dialogResult);
            }
        };
        PartiesTabletPanelApp partiesTabletPanelApp = this.mPanelApp;
        partiesTabletPanelApp.mDialogManager.showDialog(appLaunchDialog);
        if (this.mParty.mPartyLeader.mID.equals(partiesTabletPanelApp.getLocalUserId())) {
            this.mHandler.postDelayed(new Runnable(socialGroupLaunchAppDestination) {
                /* class com.oculus.panelapp.parties.views.$$Lambda$PartyTravelFooterView$xNBsZ_iTmkumSvAhvXBdEL4fmX82 */
                public final /* synthetic */ SocialGroupLaunchAppDestination f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    PartyTravelFooterView.this.lambda$showGroupLaunchAppLaunchDialog$4$PartyTravelFooterView(this.f$1);
                }
            }, 10000);
        }
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        this.mPanelApp.mPartiesViewModel.removeObserver(this);
        PartyTravelFooterBinding partyTravelFooterBinding = this.mBinding;
        partyTravelFooterBinding.primaryActionButton.mEventHandler = null;
        partyTravelFooterBinding.secondaryActionButton.mEventHandler = null;
        partyTravelFooterBinding.changeDestinationButton.mEventHandler = null;
        this.mPartyTravelNetworkHelper.destroy();
        cancelGroupLaunch();
    }

    @VisibleForTesting
    public void initializeChangeDestinationButton(OCButton oCButton) {
        if (oCButton != null) {
            oCButton.mEventHandler = this.mPanelApp;
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.parties.views.$$Lambda$PartyTravelFooterView$UYtz4ASQYuNAGAWWRcTVODIlsUU2 */

                public final void onClick(View view) {
                    PartyTravelFooterView.this.lambda$initializeChangeDestinationButton$2$PartyTravelFooterView(view);
                }
            });
        }
    }

    @VisibleForTesting
    public void initializePrimaryButton(OCButton oCButton) {
        if (oCButton != null) {
            oCButton.mEventHandler = this.mPanelApp;
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.parties.views.$$Lambda$PartyTravelFooterView$KL8FcqP8r21DxQD9QEKXZZ6C6942 */

                public final void onClick(View view) {
                    PartyTravelFooterView.this.lambda$initializePrimaryButton$0$PartyTravelFooterView(view);
                }
            });
        }
    }

    @VisibleForTesting
    public void initializeSecondaryButton(OCButton oCButton) {
        if (oCButton != null) {
            oCButton.mEventHandler = this.mPanelApp;
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.parties.views.$$Lambda$PartyTravelFooterView$ovmwp6rTWsEVybNDnMxb5veLP5M2 */

                public final void onClick(View view) {
                    PartyTravelFooterView.this.lambda$initializeSecondaryButton$1$PartyTravelFooterView(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initializeChangeDestinationButton$2$PartyTravelFooterView(View view) {
        if (this.mPartyTravelFooterViewModel.getFooterShouldShowChangeDestinationButton()) {
            onClickSelectNewApp();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public /* synthetic */ void lambda$initializePrimaryButton$0$PartyTravelFooterView(View view) {
        PartyTravelFooterViewModel partyTravelFooterViewModel = this.mPartyTravelFooterViewModel;
        if (partyTravelFooterViewModel.mPartyHasUninstalledAppSelected) {
            onClickViewAppDetails();
            return;
        }
        switch (partyTravelFooterViewModel.mPartyTravelState.ordinal()) {
            case 2:
                break;
            case 3:
            case 9:
                onClickLaunchSocialActivity();
                return;
            case 4:
            case 5:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                partyTravelFooterViewModel.setAsyncActionSource(PartyTravelFooterAsyncSource.PARTY_FOOTER_PRIMARY_BUTTON);
                onClickLaunchSolo();
                return;
            case 6:
            case 7:
                partyTravelFooterViewModel.setAsyncActionSource(PartyTravelFooterAsyncSource.PARTY_FOOTER_PRIMARY_BUTTON);
                onClickLaunchGroup();
                return;
            case 8:
                PartiesTabletPanelApp partiesTabletPanelApp = this.mPanelApp;
                if (!DeviceConfigHelper.getBoolean(partiesTabletPanelApp.mContext, DeviceConfigSocialPlatformMC.OCULUS_PARTY_ANYONE_CAN_SET_DESTINATION)) {
                    return;
                }
                break;
            case 10:
                partyTravelFooterViewModel.setAsyncActionSource(PartyTravelFooterAsyncSource.PARTY_FOOTER_PRIMARY_BUTTON);
                onClickSetUserResponse(SocialGroupLaunchResponse.ACCEPT);
                return;
            default:
                return;
        }
        onClickCreateGroupLaunch();
    }

    public /* synthetic */ void lambda$initializeSecondaryButton$1$PartyTravelFooterView(View view) {
        PartyTravelFooterViewModel partyTravelFooterViewModel = this.mPartyTravelFooterViewModel;
        if (!partyTravelFooterViewModel.mPartyHasUninstalledAppSelected) {
            switch (partyTravelFooterViewModel.mPartyTravelState.ordinal()) {
                case 6:
                case 7:
                    partyTravelFooterViewModel.setAsyncActionSource(PartyTravelFooterAsyncSource.PARTY_FOOTER_SECONDARY_BUTTON);
                    onClickLaunchSolo();
                    return;
                default:
                    return;
            }
        }
    }

    public /* synthetic */ boolean lambda$showGroupLaunchAppLaunchDialog$3$PartyTravelFooterView(final ClickEventButtonId clickEventButtonId, SocialGroupLaunchAppDestination socialGroupLaunchAppDestination, DialogResult dialogResult) {
        if (!"cancel".equals(dialogResult.mDialogAction)) {
            return true;
        }
        if (this.mParty.mPartyLeader.mID.equals(this.mPanelApp.getLocalUserId())) {
            logButtonClick(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_APP_LAUNCH_DIALOG_LEADER_VIEW_CANCEL);
            cancelGroupLaunch();
        } else {
            logButtonClick(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_APP_LAUNCH_DIALOG_MEMBER_VIEW_CANCEL);
            this.mPartyTravelNetworkHelper.setGroupLaunchUserResponse(socialGroupLaunchAppDestination.mGroupLaunchID, SocialGroupLaunchResponse.PENDING_RESPONSE, this.mContext, new PartyActionHandler() {
                /* class com.oculus.panelapp.parties.views.PartyTravelFooterView.AnonymousClass4 */

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onError() {
                    PartyTravelFooterView.this.mPartyTravelFooterViewModel.setAsyncActionSource(null);
                    PartyTravelFooterView.this.logActionFailure(clickEventButtonId, ActionId.PARTY_FOOTER_SET_USER_RESPONSE, "Failed to cancel group launch for party member");
                }

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onSuccess() {
                    PartyTravelFooterView.this.mPartyTravelFooterViewModel.setAsyncActionSource(null);
                    PartyTravelFooterView.this.mPanelApp.mPartiesViewModel.loadPartyData();
                    PartyTravelFooterView.this.logActionSuccess(clickEventButtonId, ActionId.PARTY_FOOTER_SET_USER_RESPONSE);
                }
            });
        }
        this.mPartyTravelFooterViewModel.setAsyncActionSource(null);
        return true;
    }

    public /* synthetic */ void lambda$showGroupLaunchAppLaunchDialog$4$PartyTravelFooterView(final SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        this.mPartyTravelNetworkHelper.launchGroupLaunch(socialGroupLaunchAppDestination.mGroupLaunchID, this.mContext, new PartyActionHandler() {
            /* class com.oculus.panelapp.parties.views.PartyTravelFooterView.AnonymousClass5 */

            @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
            public void onError() {
                PartyTravelFooterView.this.mPartyTravelFooterViewModel.setAsyncActionSource(null);
                PartyTravelFooterView.this.logActionFailure(ClickEventButtonId.NO_BUTTON, ActionId.PARTY_FOOTER_LAUNCH_GROUP_LAUNCH, AnonymousClass006.A07("Failed to launch group launch: ", socialGroupLaunchAppDestination.mGroupLaunchID));
            }

            @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
            public void onSuccess() {
                PartyTravelFooterView.this.mPartyTravelFooterViewModel.setAsyncActionSource(null);
                PartyTravelFooterView.this.mPanelApp.mPartiesViewModel.loadPartyData();
                PartyTravelFooterView.this.logActionSuccess(ClickEventButtonId.NO_BUTTON, ActionId.PARTY_FOOTER_LAUNCH_GROUP_LAUNCH);
            }
        });
    }

    @VisibleForTesting
    public void logButtonClick(ClickEventButtonId clickEventButtonId) {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            this.mPanelApp.mSocialLogger.logButtonClick(clickEventButtonId, SurfaceType.PARTIES_FOOTER, "party_id", socialParty.mID);
        } else {
            this.mPanelApp.mSocialLogger.logButtonClick(clickEventButtonId, SurfaceType.PARTIES_FOOTER);
        }
    }

    @VisibleForTesting
    public void onClickLaunchGroup() {
        SocialParty socialParty = this.mParty;
        if (socialParty != null && socialParty.mSocialProposedGroupLaunchAppDestination != null) {
            logButtonClick(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_LAUNCH);
            final SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = this.mParty.mSocialProposedGroupLaunchAppDestination;
            this.mPartyTravelNetworkHelper.prepareGroupLaunch(socialGroupLaunchAppDestination.mGroupLaunchID, this.mContext, new PartyActionHandler() {
                /* class com.oculus.panelapp.parties.views.PartyTravelFooterView.AnonymousClass3 */

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onError() {
                    PartyTravelFooterView.this.mPartyTravelFooterViewModel.setAsyncActionSource(null);
                    PartyTravelFooterView.this.logActionFailure(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_LAUNCH, ActionId.PARTY_FOOTER_PREPARE_GROUP_LAUNCH, AnonymousClass006.A07("Failed to prepare group launch for group launch: ", socialGroupLaunchAppDestination.mGroupLaunchID));
                }

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onSuccess() {
                    PartyTravelFooterView.this.mPartyTravelFooterViewModel.setAsyncActionSource(null);
                    PartyTravelFooterView.this.showGroupLaunchAppLaunchDialog(socialGroupLaunchAppDestination);
                    PartyTravelFooterView.this.logActionSuccess(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_LAUNCH, ActionId.PARTY_FOOTER_PREPARE_GROUP_LAUNCH);
                }
            });
        }
    }

    @VisibleForTesting
    public void onClickLaunchSolo() {
        SocialParty socialParty = this.mParty;
        if (socialParty != null && socialParty.mSocialProposedGroupLaunchAppDestination != null) {
            logButtonClick(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_LAUNCH_SOLO);
            final SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = this.mParty.mSocialProposedGroupLaunchAppDestination;
            this.mPartyTravelNetworkHelper.launchGroupLaunchSolo(socialGroupLaunchAppDestination.mGroupLaunchID, this.mContext, new PartyActionHandler() {
                /* class com.oculus.panelapp.parties.views.PartyTravelFooterView.AnonymousClass1 */

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onError() {
                    PartyTravelFooterView.this.mPartyTravelFooterViewModel.setAsyncActionSource(null);
                    PartyTravelFooterView.this.logActionFailure(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_LAUNCH_SOLO, ActionId.PARTY_FOOTER_LAUNCH_SOLO, AnonymousClass006.A07("Failed to launch solo for group launch: ", socialGroupLaunchAppDestination.mGroupLaunchID));
                }

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onSuccess() {
                    PartyTravelFooterView.this.mPartyTravelFooterViewModel.setAsyncActionSource(null);
                    PartyTravelFooterView.this.mPanelApp.mPartiesViewModel.loadPartyData();
                    PartyTravelFooterView.this.logActionSuccess(ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_LAUNCH_SOLO, ActionId.PARTY_FOOTER_LAUNCH_SOLO);
                }
            });
        }
    }

    @VisibleForTesting
    public void onClickSetUserResponse(final SocialGroupLaunchResponse socialGroupLaunchResponse) {
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination;
        SocialParty socialParty = this.mParty;
        if (socialParty != null && (socialGroupLaunchAppDestination = socialParty.mSocialProposedGroupLaunchAppDestination) != null) {
            final ClickEventButtonId clickEventButtonId = ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_ACCEPT_RESPONSE;
            switch (socialGroupLaunchResponse.ordinal()) {
                case 0:
                case 1:
                case 2:
                    clickEventButtonId = ClickEventButtonId.PARTY_TRAVEL_FOOTER_GROUP_LAUNCH_CANCEL_RESPONSE;
                    break;
            }
            logButtonClick(clickEventButtonId);
            this.mPartyTravelNetworkHelper.setGroupLaunchUserResponse(socialGroupLaunchAppDestination.mGroupLaunchID, socialGroupLaunchResponse, this.mContext, new PartyActionHandler() {
                /* class com.oculus.panelapp.parties.views.PartyTravelFooterView.AnonymousClass2 */

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onError() {
                    PartyTravelFooterView.this.mPartyTravelFooterViewModel.setAsyncActionSource(null);
                    PartyTravelFooterView.this.logActionFailure(clickEventButtonId, ActionId.PARTY_FOOTER_SET_USER_RESPONSE, AnonymousClass006.A07("Failed to set user response to: ", socialGroupLaunchResponse.toString()));
                }

                @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
                public void onSuccess() {
                    PartyTravelFooterView.this.mPartyTravelFooterViewModel.setAsyncActionSource(null);
                    PartyTravelFooterView.this.mPanelApp.mPartiesViewModel.loadPartyData();
                    PartyTravelFooterView.this.logActionSuccess(clickEventButtonId, ActionId.PARTY_FOOTER_SET_USER_RESPONSE);
                }
            });
        }
    }

    @Override // com.oculus.panelapp.parties.views.PartyDataObserver
    public void onUpdateParty(SocialParty socialParty) {
        this.mParty = socialParty;
        this.mPartyTravelFooterViewModel.setParty(socialParty);
        maybeReloadImage();
        maybeShowGroupLaunchDialog();
    }

    public PartyTravelFooterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
    }

    public void initialize(PartiesTabletPanelApp partiesTabletPanelApp, AnonymousClass1uW r5) {
        super.initialize((AndroidPanelApp) partiesTabletPanelApp, r5);
        this.mPanelApp = partiesTabletPanelApp;
        this.mBinding = (PartyTravelFooterBinding) r5;
        PartyTravelFooterViewModel partyTravelFooterViewModel = new PartyTravelFooterViewModel(partiesTabletPanelApp, this.mContext, partiesTabletPanelApp.getLocalUserId());
        this.mPartyTravelFooterViewModel = partyTravelFooterViewModel;
        this.mBinding.setPartyTravelFooterViewModel(partyTravelFooterViewModel);
        this.mPanelApp.mPartiesViewModel.registerObserver(this);
        PartyTravelFooterBinding partyTravelFooterBinding = this.mBinding;
        initializePrimaryButton(partyTravelFooterBinding.primaryActionButton);
        initializeSecondaryButton(partyTravelFooterBinding.secondaryActionButton);
        initializeChangeDestinationButton(partyTravelFooterBinding.changeDestinationButton);
        this.mPartyTravelNetworkHelper = new PartyTravelNetworkHelper();
    }
}
