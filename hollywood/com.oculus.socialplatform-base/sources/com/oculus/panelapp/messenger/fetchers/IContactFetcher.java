package com.oculus.panelapp.messenger.fetchers;

import java.util.List;

public interface IContactFetcher {

    public interface ContactQueryCallback {
        void onError();

        void onResult(List<MessengerContact> list);
    }

    void destroy();

    void queryContacts(String str, ContactQueryCallback contactQueryCallback);
}
