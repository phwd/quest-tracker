package com.oculus.tablet.utils.backtotop;

public interface ScrollCallback {
    void addPendingScrollToPosition(int i);

    void onUpdate();

    void smoothScrollToPosition(int i);
}
