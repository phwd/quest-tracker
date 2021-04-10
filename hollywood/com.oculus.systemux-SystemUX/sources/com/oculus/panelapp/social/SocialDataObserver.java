package com.oculus.panelapp.social;

import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import java.util.List;

public interface SocialDataObserver {
    void onEnoughDataFetched();

    void updateFriendsList(List<SocialUser> list, List<SocialUser> list2, List<SocialParty> list3, SocialParty socialParty);

    void updateSocialViewer();
}
