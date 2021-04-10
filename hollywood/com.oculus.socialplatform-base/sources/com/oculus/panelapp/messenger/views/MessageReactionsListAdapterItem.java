package com.oculus.panelapp.messenger.views;

public interface MessageReactionsListAdapterItem {
    boolean equals(MessageReactionsListAdapterItem messageReactionsListAdapterItem);

    long getID();

    MessageReactionsListAdapterItemType getItemViewType();
}
