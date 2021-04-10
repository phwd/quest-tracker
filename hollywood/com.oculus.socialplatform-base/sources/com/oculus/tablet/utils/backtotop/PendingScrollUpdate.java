package com.oculus.tablet.utils.backtotop;

public class PendingScrollUpdate {
    public boolean mHasPendingScrollAction;
    public int mScrollToPosition;

    public void resetPendingScrollAction() {
        this.mHasPendingScrollAction = false;
    }

    public void addPendingScrollToPosition(int i) {
        if (i >= 0) {
            this.mScrollToPosition = i;
            this.mHasPendingScrollAction = true;
        }
    }

    public int getScrollToPosition() {
        return this.mScrollToPosition;
    }

    public boolean hasPendingScrollAction() {
        return this.mHasPendingScrollAction;
    }
}
