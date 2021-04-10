package com.oculus.panelapp.messenger.fetchers;

import com.oculus.panelapp.messenger.fetchers.IContactFetcher;
import java.util.ArrayList;

public class TestContactFetcher implements IContactFetcher {
    @Override // com.oculus.panelapp.messenger.fetchers.IContactFetcher
    public void destroy() {
    }

    @Override // com.oculus.panelapp.messenger.fetchers.IContactFetcher
    public void queryContacts(String str, IContactFetcher.ContactQueryCallback contactQueryCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MessengerContact("00000", "", "Michael Scott"));
        arrayList.add(new MessengerContact("00001", "", "Dwight Schrute"));
        arrayList.add(new MessengerContact("00002", "", "Jim Halpert"));
        arrayList.add(new MessengerContact("00003", "", "Pamela Beesly"));
        arrayList.add(new MessengerContact("00004", "", "Andy Bernard"));
        arrayList.add(new MessengerContact("00005", "", "Erin Hannon"));
        contactQueryCallback.onResult(arrayList);
    }
}
