package com.oculus.panelapp.parties.views;

import X.AnonymousClass1uW;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.databinding.PartiesTabletMainBinding;
import com.oculus.panelapp.parties.databinding.PartiesTabletPartyHeaderBinding;
import com.oculus.panelapp.parties.utils.PartyUtils;
import com.oculus.provider.OculusContent;
import com.oculus.socialdialogs.SocialPartyDialogs;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import java.util.ArrayList;

public class PartiesView extends BaseView implements PartyDataObserver {
    public static final String TAG = LoggingUtil.tag(PartiesView.class);
    public PartiesTabletMainBinding mBinding;
    public final Context mContext;
    @Nullable
    public AsyncQueryHandle mLeavePartyAsyncQueryHandle;
    public PartiesTabletPanelApp mPanelApp;
    public PartiesViewModel mPartiesViewModel;
    @Nullable
    public SocialParty mParty;
    public PartyAdapter mPartyAdapter;
    public PartyTravelFooterView mPartyTravelFooterView;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateVolume(SeekBar seekBar, int i, boolean z) {
        float f = ((float) i) / 100.0f;
        if (!z) {
            seekBar.setProgress(this.mPartiesViewModel.getPartyVolume());
        } else {
            this.mPartiesViewModel.setPartyVolume(f);
        }
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
    }

    private void initPartyMemberList() {
        this.mBinding.horizontalRecycler.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        OCRecyclerView oCRecyclerView = this.mBinding.horizontalRecycler;
        oCRecyclerView.mHasFixedSize = true;
        oCRecyclerView.setAdapter(this.mPartyAdapter);
    }

    private void initPartyVolume() {
        this.mBinding.partyHeader.partyVolumeSlider.seekbar.setProgress(this.mPartiesViewModel.getPartyVolume());
        this.mBinding.partyHeader.partyVolumeSlider.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /* class com.oculus.panelapp.parties.views.PartiesView.AnonymousClass1 */

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PartiesView.this.updateVolume(seekBar, i, z);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                PartiesView.this.mPanelApp.mSocialLogger.logButtonClick(ClickEventButtonId.PARTIES_PANEL_ADJUST_AUDIO_PLAYBACK_VOLUME, SurfaceType.PARTIES_HEADER);
            }
        });
    }

    private void initializePartyHeaderButtons() {
        OCButton oCButton = this.mBinding.partyHeader.navigateBackButton.button;
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.parties.views.$$Lambda$PartiesView$7ZPKT2zGiXWr6PIhlmKQLnEI8Xw2 */

            public final void onClick(View view) {
                PartiesView.this.lambda$initializePartyHeaderButtons$0$PartiesView(view);
            }
        });
        $$Lambda$PartiesView$lGTKymY4397xhMIDKIiMwtmGK82 r1 = new DialogResultHandler() {
            /* class com.oculus.panelapp.parties.views.$$Lambda$PartiesView$lGTKymY4397xhMIDKIiMwtmGK82 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return PartiesView.this.lambda$initializePartyHeaderButtons$1$PartiesView(dialogResult);
            }
        };
        DialogDefinitionBase leaveInviteOnlyPartyDialog = SocialPartyDialogs.getLeaveInviteOnlyPartyDialog(this.mContext.getResources());
        leaveInviteOnlyPartyDialog.mDialogResultHandler = r1;
        DialogDefinitionBase leaveDirectJoinPartyDialog = SocialPartyDialogs.getLeaveDirectJoinPartyDialog(this.mContext.getResources());
        leaveDirectJoinPartyDialog.mDialogResultHandler = r1;
        PartiesTabletPartyHeaderBinding partiesTabletPartyHeaderBinding = this.mBinding.partyHeader;
        OCButton oCButton2 = partiesTabletPartyHeaderBinding.endCallButton;
        PartiesTabletPanelApp partiesTabletPanelApp = this.mPanelApp;
        oCButton2.mEventHandler = partiesTabletPanelApp;
        oCButton2.setOnClickListener(new View.OnClickListener(leaveInviteOnlyPartyDialog, leaveDirectJoinPartyDialog) {
            /* class com.oculus.panelapp.parties.views.$$Lambda$PartiesView$5xwOJPGCHTplw8Nwo6aP5OdPO42 */
            public final /* synthetic */ DialogDefinitionBase f$1;
            public final /* synthetic */ DialogDefinitionBase f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                PartiesView.this.lambda$initializePartyHeaderButtons$2$PartiesView(this.f$1, this.f$2, view);
            }
        });
        OCButton oCButton3 = partiesTabletPartyHeaderBinding.privacySettingsButton;
        oCButton3.mEventHandler = partiesTabletPanelApp;
        oCButton3.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.parties.views.$$Lambda$PartiesView$pYMoozUJpdcWJNDMIs3J3RIAr42 */

            public final void onClick(View view) {
                PartiesView.this.lambda$initializePartyHeaderButtons$3$PartiesView(view);
            }
        });
    }

    private void updatePartyList() {
        ArrayList arrayList = new ArrayList();
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            for (SocialUser socialUser : socialParty.mPartyMembers) {
                String str = socialUser.mID;
                if (str == null || !str.equals(this.mPanelApp.getLocalUserId())) {
                    arrayList.add(new PartyUserCardAdapterItem(socialUser, this.mParty));
                } else {
                    socialUser.mUserType = SocialUser.UserRowType.PARTY_CONTROLS;
                    arrayList.add(0, new PartyUserCardAdapterItem(socialUser, this.mParty));
                }
            }
            for (SocialUser socialUser2 : this.mParty.getInvitedUsers()) {
                arrayList.add(new PartyUserCardAdapterItem(socialUser2, this.mParty));
            }
            for (SocialUser socialUser3 : this.mParty.getBlockedMembers()) {
                arrayList.add(new PartyUserCardAdapterItem(socialUser3, this.mParty));
            }
            for (SocialUser socialUser4 : this.mParty.getBlockedInvitedUsers()) {
                arrayList.add(new PartyUserCardAdapterItem(socialUser4, this.mParty));
            }
            arrayList.add(0, new InviteToPartyCardAdapterItem(this.mPanelApp, this.mParty));
        }
        this.mPartyAdapter.setData(arrayList);
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        this.mPartiesViewModel.removeObserver(this);
        PartiesTabletPartyHeaderBinding partiesTabletPartyHeaderBinding = this.mBinding.partyHeader;
        OCButton oCButton = partiesTabletPartyHeaderBinding.navigateBackButton.button;
        oCButton.mEventHandler = null;
        oCButton.setOnClickListener(null);
        OCButton oCButton2 = partiesTabletPartyHeaderBinding.endCallButton;
        oCButton2.mEventHandler = null;
        oCButton2.setOnClickListener(null);
        OCButton oCButton3 = partiesTabletPartyHeaderBinding.privacySettingsButton;
        oCButton3.mEventHandler = null;
        oCButton3.setOnClickListener(null);
        partiesTabletPartyHeaderBinding.partyVolumeSlider.seekbar.setOnSeekBarChangeListener(null);
        this.mPartyAdapter.destroy();
        this.mPartyTravelFooterView.destroy();
    }

    public /* synthetic */ void lambda$initializePartyHeaderButtons$0$PartiesView(View view) {
        this.mPanelApp.mSocialLogger.logButtonClick(ClickEventButtonId.PARTIES_PANEL_BACK_BUTTON, SurfaceType.PARTIES_HEADER);
        PartyUtils.navigateToLastOpenedSocialTablet(this.mPanelApp);
    }

    public /* synthetic */ boolean lambda$initializePartyHeaderButtons$1$PartiesView(DialogResult dialogResult) {
        String str = dialogResult.mDialogAction;
        int hashCode = str.hashCode();
        if (hashCode != -1367724422) {
            if (hashCode == 102846135 && str.equals(SocialPartyDialogs.LEAVE_ACTION)) {
                this.mPartiesViewModel.leaveParty(this.mPanelApp, ClickEventButtonId.PARTIES_PANEL_LEAVE_PARTY, SurfaceType.PARTIES_HEADER);
            }
        } else if (str.equals("cancel")) {
            return false;
        }
        return true;
    }

    public /* synthetic */ void lambda$initializePartyHeaderButtons$2$PartiesView(DialogDefinitionBase dialogDefinitionBase, DialogDefinitionBase dialogDefinitionBase2, View view) {
        this.mPanelApp.mSocialLogger.logButtonClick(ClickEventButtonId.PARTIES_PANEL_LEAVE_PARTY, SurfaceType.PARTIES_HEADER);
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            if (socialParty.mJoinType == SocialPartyType.JOINABLE_BY_FRIENDS) {
                dialogDefinitionBase = dialogDefinitionBase2;
            }
            this.mPanelApp.mDialogManager.showDialog(dialogDefinitionBase);
        }
    }

    public /* synthetic */ void lambda$initializePartyHeaderButtons$3$PartiesView(View view) {
        this.mPanelApp.mSocialLogger.logButtonClick(ClickEventButtonId.PARTIES_PANEL_PARTY_PRIVACY, SurfaceType.PARTIES_HEADER);
        if (this.mParty == null) {
            this.mPanelApp.mSocialLogger.logImpressionFailure(SurfaceType.PARTY_PRIVACY, "mParty was null");
            return;
        }
        PartiesTabletPanelApp partiesTabletPanelApp = this.mPanelApp;
        if (DeviceConfigHelper.getBoolean(partiesTabletPanelApp.mContext, DeviceConfigSocialPlatformMC.PARTY_PRIVACY_FAR_FIELD_VIEW)) {
            this.mPanelApp.actionNavigate(SystemUXRoute.SOCIAL_OVERLAY_PANEL, Uri.parse("").buildUpon().appendPath(OculusContent.FriendList.PARTY_PRIVACY).appendQueryParameter("party_id", String.valueOf(this.mParty.mID)).build().toString());
            return;
        }
        this.mPanelApp.actionNavigate(Uri.parse(SystemUXRoute.SOCIAL_PARTY_PRIVACY.path()).buildUpon().appendQueryParameter("party_id", String.valueOf(this.mParty.mID)).build().toString());
    }

    @Override // com.oculus.panelapp.parties.views.PartyDataObserver
    public void onUpdateParty(SocialParty socialParty) {
        this.mParty = socialParty;
        updatePartyList();
    }

    public PartiesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mPartyTravelFooterView = new PartyTravelFooterView(context, attributeSet);
    }

    public void initialize(PartiesTabletPanelApp partiesTabletPanelApp, AnonymousClass1uW r4) {
        super.initialize((AndroidPanelApp) partiesTabletPanelApp, r4);
        this.mPanelApp = partiesTabletPanelApp;
        PartiesViewModel partiesViewModel = partiesTabletPanelApp.mPartiesViewModel;
        this.mPartiesViewModel = partiesViewModel;
        partiesViewModel.registerObserver(this);
        PartiesTabletMainBinding partiesTabletMainBinding = (PartiesTabletMainBinding) r4;
        this.mBinding = partiesTabletMainBinding;
        partiesTabletMainBinding.setPartiesViewModel(this.mPartiesViewModel);
        this.mPartyAdapter = new PartyAdapter(this.mPanelApp);
        this.mPartyTravelFooterView.initialize(partiesTabletPanelApp, (AnonymousClass1uW) this.mBinding.partyFooter);
        initPartyVolume();
        initializePartyHeaderButtons();
        initPartyMemberList();
    }
}
