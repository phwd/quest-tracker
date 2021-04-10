package com.oculus.panelapp.social;

public interface SocialAdapterItem {
    boolean equals(SocialAdapterItem socialAdapterItem);

    String getID();

    SocialAdapterItemType getItemViewType();
}
