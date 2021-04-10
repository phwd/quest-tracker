package com.oculus.panelapp.messenger.views;

public interface DraftThreadParticipantListAdapterItem {
    boolean equals(DraftThreadParticipantListAdapterItem draftThreadParticipantListAdapterItem);

    long getID();

    DraftThreadParticipantListAdapterItemType getItemViewType();
}
