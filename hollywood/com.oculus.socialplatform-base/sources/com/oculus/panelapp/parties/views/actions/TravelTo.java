package com.oculus.panelapp.parties.views.actions;

import android.content.Context;
import com.oculus.horizoncontent.social.SocialDeeplinkPresence;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;

public class TravelTo extends PartyAction {
    public PartiesTabletPanelApp mPanelApp;
    public SocialUser mUser;

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public PartyActionType getType() {
        return PartyActionType.TRAVEL_TO;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public boolean isRelevant() {
        SocialUser socialUser = this.mUser;
        if (socialUser == null || socialUser.mDeepLink == null) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public void performAction(Context context, PartyActionHandler partyActionHandler) {
        SocialDeeplinkPresence socialDeeplinkPresence = this.mUser.mDeepLink;
        if (socialDeeplinkPresence != null) {
            this.mPanelApp.launchApp(socialDeeplinkPresence.mPackageName, socialDeeplinkPresence.mDeepLinkLaunchParams);
        }
    }

    public TravelTo(SocialUser socialUser, PartiesTabletPanelApp partiesTabletPanelApp) {
        this.mUser = socialUser;
        this.mPanelApp = partiesTabletPanelApp;
    }
}
