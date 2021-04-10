package com.oculus.panelapp.messenger.views;

public interface ThreadListAdapterItem {
    boolean equals(ThreadListAdapterItem threadListAdapterItem);

    long getID();

    ThreadListAdapterItemType getItemViewType();
}
