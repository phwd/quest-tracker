package com.oculus.panelapp.people.fetchers;

import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.vrshell.panels.AndroidPanelApp;
import java.util.List;

public interface IFetcher extends AndroidPanelApp.PanelFrameCallback {
    void destroy();

    List<SocialUser> getData();

    boolean getEnoughDataFetched();

    boolean getIsErrored();

    boolean hasNoData();

    void notifyPeopleListObservers();

    void registerPeopleListObserver(PeopleListDataObserver peopleListDataObserver);

    void removePeopleListObserver(PeopleListDataObserver peopleListDataObserver);
}
