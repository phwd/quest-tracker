package com.oculus.panelapp.parties.views.actions;

import android.content.Context;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.parties.views.PartyUserCardAdapterItem;

public class ManageVoice extends PartyAction {
    public static final String TAG = LoggingUtil.tag(ManageVoice.class);
    public PartyUserCardAdapterItem mPartyUserCardAdapterItem;

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public void performAction(Context context, @Nullable PartyActionHandler partyActionHandler) {
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public PartyActionType getType() {
        return PartyActionType.MANAGE_VOICE;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public boolean isRelevant() {
        SocialUser socialUser = this.mPartyUserCardAdapterItem.mUser;
        if (socialUser == null || socialUser.mUserType != SocialUser.UserRowType.PARTY_CONTROLS) {
            return false;
        }
        return true;
    }

    public ManageVoice(PartyUserCardAdapterItem partyUserCardAdapterItem) {
        this.mPartyUserCardAdapterItem = partyUserCardAdapterItem;
    }
}
