package com.oculus.panelapp.parties.views.actions;

import android.content.Context;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.parties.views.PartyUserCardAdapterItem;
import com.oculus.panelapp.parties.views.PartyUserCardViewModel;

public class SelfMute extends PartyAction {
    public static final String TAG = LoggingUtil.tag(SelfMute.class);
    public PartyUserCardAdapterItem mPartyUserCardAdapterItem;
    public PartyUserCardViewModel mPartyUserCardViewModel;

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public PartyActionType getType() {
        if (this.mPartyUserCardViewModel.mCurrentSelfMuteState == 1) {
            return PartyActionType.SELF_UNMUTE;
        }
        return PartyActionType.SELF_MUTE;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public boolean isRelevant() {
        SocialUser socialUser = this.mPartyUserCardAdapterItem.mUser;
        if (socialUser == null || socialUser.mUserType != SocialUser.UserRowType.PARTY_CONTROLS) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public void performAction(Context context, final PartyActionHandler partyActionHandler) {
        PartyUserCardAdapterItem partyUserCardAdapterItem = this.mPartyUserCardAdapterItem;
        SocialUser socialUser = partyUserCardAdapterItem.mUser;
        SocialParty socialParty = partyUserCardAdapterItem.mParty;
        if (socialUser != null && socialParty != null) {
            final int i = 2;
            if (getType() == PartyActionType.SELF_MUTE) {
                i = 1;
            }
            HorizonContentProviderHelper.setPartyLocalMuteAsync(context, i, new HorizonContentProviderHelper.MuteStateCallback() {
                /* class com.oculus.panelapp.parties.views.actions.SelfMute.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
                public void onError() {
                    partyActionHandler.onError();
                }

                @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MuteStateCallback
                public void onSuccess(int i) {
                    if (i == i) {
                        SelfMute.this.mPartyUserCardViewModel.updateSelfMuteState(i);
                    }
                    partyActionHandler.onSuccess();
                }
            });
        }
    }

    public SelfMute(PartyUserCardAdapterItem partyUserCardAdapterItem, PartyUserCardViewModel partyUserCardViewModel, Context context) {
        this.mPartyUserCardAdapterItem = partyUserCardAdapterItem;
        this.mPartyUserCardViewModel = partyUserCardViewModel;
    }
}
