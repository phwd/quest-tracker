package com.oculus.panelapp.messenger.api.models;

import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.fetchers.MessengerContact;
import java.util.LinkedHashSet;
import java.util.Set;

public class DraftThread {
    public final MessengerPanelApp mPanelApp;
    public final Set<MessengerContact> mParticipants = new LinkedHashSet();

    public void addParticipant(MessengerContact messengerContact) {
        this.mParticipants.add(messengerContact);
        this.mPanelApp.getAPIManager().updateDraftThread(this);
    }

    public boolean canSend() {
        if (this.mParticipants.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean containsParticipant(MessengerContact messengerContact) {
        return this.mParticipants.contains(messengerContact);
    }

    public Set<MessengerContact> getParticipants() {
        return this.mParticipants;
    }

    public void removeParticipant(MessengerContact messengerContact) {
        this.mParticipants.remove(messengerContact);
        this.mPanelApp.getAPIManager().updateDraftThread(this);
    }

    public DraftThread(MessengerPanelApp messengerPanelApp) {
        this.mPanelApp = messengerPanelApp;
    }
}
