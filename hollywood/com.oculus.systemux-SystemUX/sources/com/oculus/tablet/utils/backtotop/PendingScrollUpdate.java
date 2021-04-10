package com.oculus.tablet.utils.backtotop;

public class PendingScrollUpdate {
    private boolean mHasPendingScrollAction;
    private int mScrollToPosition;

    public boolean hasPendingScrollAction() {
        return this.mHasPendingScrollAction;
    }

    public void resetPendingScrollAction() {
        this.mHasPendingScrollAction = false;
    }

    public int getScrollToPosition() {
        return this.mScrollToPosition;
    }

    public void addPendingScrollToPosition(int i) {
        if (i >= 0) {
            this.mScrollToPosition = i;
            this.mHasPendingScrollAction = true;
        }
    }
}
