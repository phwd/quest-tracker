package com.oculus.panelapp.parties.views;

public interface PartyAdapterItem {
    boolean equals(PartyAdapterItem partyAdapterItem);

    String getID();

    PartyAdapterItemType getItemViewType();
}
