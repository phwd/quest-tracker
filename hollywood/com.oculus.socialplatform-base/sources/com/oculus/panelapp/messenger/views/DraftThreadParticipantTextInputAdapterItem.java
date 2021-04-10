package com.oculus.panelapp.messenger.views;

public class DraftThreadParticipantTextInputAdapterItem implements DraftThreadParticipantListAdapterItem {
    @Override // com.oculus.panelapp.messenger.views.DraftThreadParticipantListAdapterItem
    public boolean equals(DraftThreadParticipantListAdapterItem draftThreadParticipantListAdapterItem) {
        if (draftThreadParticipantListAdapterItem == null || draftThreadParticipantListAdapterItem.getID() != getID()) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.messenger.views.DraftThreadParticipantListAdapterItem
    public DraftThreadParticipantListAdapterItemType getItemViewType() {
        return DraftThreadParticipantListAdapterItemType.TEXT_ENTRY;
    }

    @Override // com.oculus.panelapp.messenger.views.DraftThreadParticipantListAdapterItem
    public long getID() {
        return (long) 381508375;
    }
}
