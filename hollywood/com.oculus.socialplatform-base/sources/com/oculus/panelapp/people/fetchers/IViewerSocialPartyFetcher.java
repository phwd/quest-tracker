package com.oculus.panelapp.people.fetchers;

import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.people.model.IViewerSocialParty;

public interface IViewerSocialPartyFetcher extends IFetcher {
    void addUserToInvitedList(SocialUser socialUser);

    IViewerSocialParty getViewerSocialParty();

    void registerUserObserver(PeopleUserDataObserver peopleUserDataObserver);

    void removeUserObserver(PeopleUserDataObserver peopleUserDataObserver);
}
