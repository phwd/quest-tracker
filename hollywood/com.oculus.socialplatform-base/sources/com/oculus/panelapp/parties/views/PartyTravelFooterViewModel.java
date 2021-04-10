package com.oculus.panelapp.parties.views;

import X.AnonymousClass1uc;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.utils.PartyTravelStateUtils;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.ViewModelLifecycle;

public class PartyTravelFooterViewModel extends AnonymousClass1uc implements ViewModelLifecycle {
    public static final String TAG = LoggingUtil.tag(PartyTravelFooterViewModel.class);
    public PartyTravelFooterAsyncSource mAsyncSource;
    public final Context mContext;
    public final String mLocalUserId;
    public final PartiesTabletPanelApp mPanelApp;
    public SocialParty mParty;
    public boolean mPartyHasUninstalledAppSelected;
    public PartyTravelStateUtils.PartyTravelState mPartyTravelState = PartyTravelStateUtils.PartyTravelState.PARTY_NULL;
    public final Resources mResources;

    /* renamed from: com.oculus.panelapp.parties.views.PartyTravelFooterViewModel$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$parties$utils$PartyTravelStateUtils$PartyTravelState;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0070 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x008e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0098 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00a2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
            // Method dump skipped, instructions count: 173
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.views.PartyTravelFooterViewModel.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
    }

    @Bindable({"party"})
    public String getFooterAppText() {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = socialParty.mSocialProposedGroupLaunchAppDestination;
            if (socialGroupLaunchAppDestination != null) {
                return socialGroupLaunchAppDestination.mAppDisplayName;
            }
            if (!TextUtils.isEmpty(socialParty.mSocialActivityId)) {
                return this.mParty.mSocialActivityApplicationDisplayName;
            }
        }
        return null;
    }

    @Bindable({"party"})
    public String getFooterDestinationText() {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = socialParty.mSocialProposedGroupLaunchAppDestination;
            if (socialGroupLaunchAppDestination != null) {
                return socialGroupLaunchAppDestination.mDestinationDisplayName;
            }
            if (!TextUtils.isEmpty(socialParty.mSocialActivityId)) {
                return this.mParty.mSocialActivityTitle;
            }
        }
        return null;
    }

    @Bindable({"party"})
    public Drawable getFooterPrimaryActionButtonBackground() {
        Resources resources;
        int i;
        if (this.mPartyHasUninstalledAppSelected || this.mPartyTravelState.ordinal() == 12) {
            resources = this.mResources;
            i = R.drawable.ocbutton_secondary;
        } else {
            resources = this.mResources;
            i = R.drawable.ocbutton_primary;
        }
        return resources.getDrawable(i, this.mContext.getTheme());
    }

    @Bindable({"party"})
    public boolean getFooterShouldEnableButtons() {
        if (this.mAsyncSource == null) {
            return true;
        }
        return false;
    }

    @Bindable({"party"})
    public boolean getFooterShouldShowChangeDestinationButton() {
        switch (this.mPartyTravelState.ordinal()) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 13:
            case 14:
                return true;
            case 8:
            default:
                return false;
            case 9:
            case 10:
            case 11:
            case 12:
            case 15:
            case 16:
                PartiesTabletPanelApp partiesTabletPanelApp = this.mPanelApp;
                return DeviceConfigHelper.getBoolean(partiesTabletPanelApp.mContext, DeviceConfigSocialPlatformMC.OCULUS_PARTY_ANYONE_CAN_SET_DESTINATION);
        }
    }

    @Bindable({"party"})
    public boolean getFooterShouldShowDestinationInfo() {
        SocialParty socialParty = this.mParty;
        if (socialParty == null) {
            return false;
        }
        if (socialParty.mSocialProposedGroupLaunchAppDestination != null || !TextUtils.isEmpty(socialParty.mSocialActivityId)) {
            return true;
        }
        return false;
    }

    @Bindable({"party"})
    public boolean getFooterShouldShowPrimaryActionButton() {
        if (!this.mPartyHasUninstalledAppSelected) {
            switch (this.mPartyTravelState.ordinal()) {
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    break;
                case 8:
                    PartiesTabletPanelApp partiesTabletPanelApp = this.mPanelApp;
                    return DeviceConfigHelper.getBoolean(partiesTabletPanelApp.mContext, DeviceConfigSocialPlatformMC.OCULUS_PARTY_ANYONE_CAN_SET_DESTINATION);
                default:
                    return false;
            }
        }
        return true;
    }

    @Bindable({"party"})
    public boolean getFooterShouldShowPrimaryActionButtonSpinner() {
        if (this.mAsyncSource == PartyTravelFooterAsyncSource.PARTY_FOOTER_PRIMARY_BUTTON) {
            return true;
        }
        return false;
    }

    @Bindable({"party"})
    public boolean getFooterShouldShowSecondaryActionButton() {
        switch (this.mPartyTravelState.ordinal()) {
            case 6:
            case 7:
                return true;
            default:
                return false;
        }
    }

    @Bindable({"party"})
    public boolean getFooterShouldShowSecondaryActionButtonSpinner() {
        if (this.mAsyncSource == PartyTravelFooterAsyncSource.PARTY_FOOTER_SECONDARY_BUTTON) {
            return true;
        }
        return false;
    }

    @Bindable({"party"})
    public String getFooterStatusText() {
        Resources resources;
        int i;
        switch (this.mPartyTravelState.ordinal()) {
            case 1:
                return null;
            case 2:
                resources = this.mResources;
                i = R.string.parties_tablet_footer_status_text_leader_destinationless;
                return resources.getString(i);
            case 3:
            case 9:
                resources = this.mResources;
                i = R.string.parties_tablet_footer_status_text_travel_to_activity;
                return resources.getString(i);
            case 4:
                resources = this.mResources;
                i = R.string.parties_tablet_footer_status_text_leader_alone;
                return resources.getString(i);
            case 5:
                resources = this.mResources;
                i = R.string.parties_tablet_footer_status_text_leader_no_others_accepted;
                return resources.getString(i);
            case 6:
            case 7:
                return this.mResources.getQuantityString(R.plurals.parties_tablet_footer_status_text_leader_group_ready_to_launch, this.mParty.mSocialProposedGroupLaunchAppDestination.getNumAcceptedUsers(), Integer.valueOf(this.mParty.mSocialProposedGroupLaunchAppDestination.getNumAcceptedUsers()));
            case 8:
                PartiesTabletPanelApp partiesTabletPanelApp = this.mPanelApp;
                if (!DeviceConfigHelper.getBoolean(partiesTabletPanelApp.mContext, DeviceConfigSocialPlatformMC.OCULUS_PARTY_ANYONE_CAN_SET_DESTINATION)) {
                    resources = this.mResources;
                    i = R.string.parties_tablet_footer_status_text_member_destinationless;
                    return resources.getString(i);
                }
                resources = this.mResources;
                i = R.string.parties_tablet_footer_status_text_leader_destinationless;
                return resources.getString(i);
            case 10:
                resources = this.mResources;
                i = R.string.parties_tablet_footer_status_text_invited;
                return resources.getString(i);
            case 11:
                resources = this.mResources;
                i = R.string.parties_tablet_footer_status_text_group_max_capacity_reached;
                return resources.getString(i);
            case 12:
                resources = this.mResources;
                i = R.string.parties_tablet_footer_status_text_awaiting_group_launch;
                return resources.getString(i);
            case 13:
            case 14:
            case 15:
            case 16:
                resources = this.mResources;
                i = R.string.parties_tablet_footer_status_text_with_others;
                return resources.getString(i);
            default:
                Log.w(TAG, "Got Unknown Party Travel State");
                return null;
        }
    }

    @Bindable
    public SocialParty getParty() {
        return this.mParty;
    }

    public boolean getPartyHasUninstalledAppSelected() {
        return this.mPartyHasUninstalledAppSelected;
    }

    public PartyTravelStateUtils.PartyTravelState getPartyTravelState() {
        return this.mPartyTravelState;
    }

    public void setAsyncActionSource(PartyTravelFooterAsyncSource partyTravelFooterAsyncSource) {
        this.mAsyncSource = partyTravelFooterAsyncSource;
        notifyPropertyChanged(29);
    }

    public void setParty(SocialParty socialParty) {
        this.mParty = socialParty;
        this.mPartyTravelState = PartyTravelStateUtils.getPartyTravelState(socialParty, this.mLocalUserId);
        this.mPartyHasUninstalledAppSelected = PartyTravelStateUtils.partyHasUninstalledAppSelected(this.mParty, this.mContext);
        notifyPropertyChanged(29);
    }

    public PartyTravelFooterViewModel(PartiesTabletPanelApp partiesTabletPanelApp, @NonNull Context context, @NonNull String str) {
        this.mPanelApp = partiesTabletPanelApp;
        this.mContext = context;
        this.mResources = context.getResources();
        this.mLocalUserId = str;
    }

    @Bindable({"party"})
    public String getFooterPrimaryActionButtonText() {
        Resources resources;
        int i;
        if (getFooterShouldShowPrimaryActionButtonSpinner()) {
            return null;
        }
        if (this.mPartyHasUninstalledAppSelected) {
            resources = this.mResources;
            i = R.string.parties_tablet_footer_button_text_view_app_details;
        } else {
            switch (this.mPartyTravelState.ordinal()) {
                case 2:
                    resources = this.mResources;
                    i = R.string.parties_tablet_footer_button_text_propose_app;
                    break;
                case 3:
                case 4:
                case 5:
                case 9:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    resources = this.mResources;
                    i = R.string.parties_tablet_footer_button_text_launch_solo;
                    break;
                case 6:
                case 7:
                    resources = this.mResources;
                    i = R.string.parties_tablet_footer_button_text_open_for_all;
                    break;
                case 8:
                    PartiesTabletPanelApp partiesTabletPanelApp = this.mPanelApp;
                    if (DeviceConfigHelper.getBoolean(partiesTabletPanelApp.mContext, DeviceConfigSocialPlatformMC.OCULUS_PARTY_ANYONE_CAN_SET_DESTINATION)) {
                        return this.mResources.getString(R.string.parties_tablet_footer_button_text_propose_app);
                    }
                    return null;
                case 10:
                    resources = this.mResources;
                    i = R.string.parties_tablet_footer_button_text_ready_to_travel;
                    break;
                default:
                    return null;
            }
        }
        return resources.getString(i);
    }

    @Bindable({"party"})
    public String getFooterSecondaryActionButtonText() {
        if (!getFooterShouldShowSecondaryActionButtonSpinner()) {
            switch (this.mPartyTravelState.ordinal()) {
                case 6:
                case 7:
                    return this.mResources.getString(R.string.parties_tablet_footer_button_text_launch_solo);
            }
        }
        return null;
    }
}
