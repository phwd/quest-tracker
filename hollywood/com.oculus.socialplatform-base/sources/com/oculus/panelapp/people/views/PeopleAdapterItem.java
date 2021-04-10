package com.oculus.panelapp.people.views;

public interface PeopleAdapterItem {
    boolean equals(PeopleAdapterItem peopleAdapterItem);

    String getID();

    PeopleAdapterItemType getItemViewType();
}
