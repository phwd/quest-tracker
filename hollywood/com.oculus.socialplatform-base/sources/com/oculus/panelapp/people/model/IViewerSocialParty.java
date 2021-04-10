package com.oculus.panelapp.people.model;

import androidx.annotation.Nullable;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;

public interface IViewerSocialParty {
    boolean getHasLinkSharing();

    @Nullable
    String getID();

    SocialParty.PartyMembership getUserPartyMembership(SocialUser socialUser);

    boolean isFull();

    boolean isViewerInvitedToParty(String str);
}
