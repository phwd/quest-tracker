package com.oculus.panelapp.social;

/* access modifiers changed from: package-private */
public interface SocialAdapterItem {
    boolean equals(SocialAdapterItem socialAdapterItem);

    String getID();

    SocialAdapterItemType getItemViewType();
}
