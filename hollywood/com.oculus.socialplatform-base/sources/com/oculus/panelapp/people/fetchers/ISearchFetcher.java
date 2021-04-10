package com.oculus.panelapp.people.fetchers;

public interface ISearchFetcher extends IFetcher {
    int getCount();

    String getSearchString();

    void searchForUser(String str);
}
