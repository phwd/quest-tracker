package com.oculus.panelapp.messenger.views;

import com.oculus.panelapp.messenger.fetchers.MessengerContact;

public class DraftThreadParticipantAdapterItem implements DraftThreadParticipantListAdapterItem {
    public MessengerContact mMessengerContact;

    @Override // com.oculus.panelapp.messenger.views.DraftThreadParticipantListAdapterItem
    public boolean equals(DraftThreadParticipantListAdapterItem draftThreadParticipantListAdapterItem) {
        if (draftThreadParticipantListAdapterItem == null || draftThreadParticipantListAdapterItem.getItemViewType() == getItemViewType()) {
            return ((DraftThreadParticipantAdapterItem) draftThreadParticipantListAdapterItem).mMessengerContact.equals(this.mMessengerContact);
        }
        return false;
    }

    @Override // com.oculus.panelapp.messenger.views.DraftThreadParticipantListAdapterItem
    public long getID() {
        return (long) this.mMessengerContact.hashCode();
    }

    @Override // com.oculus.panelapp.messenger.views.DraftThreadParticipantListAdapterItem
    public DraftThreadParticipantListAdapterItemType getItemViewType() {
        return DraftThreadParticipantListAdapterItemType.PARTICIPANT;
    }

    public MessengerContact getMessengerContact() {
        return this.mMessengerContact;
    }

    public DraftThreadParticipantAdapterItem(MessengerContact messengerContact) {
        this.mMessengerContact = messengerContact;
    }
}
