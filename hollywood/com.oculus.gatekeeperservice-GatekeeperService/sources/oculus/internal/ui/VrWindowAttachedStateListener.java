package oculus.internal.ui;

import android.view.View;

public class VrWindowAttachedStateListener implements View.OnAttachStateChangeListener {
    private final Runnable mCallback;
    private boolean mIsWaitingForAttached;
    private final View mView;

    public VrWindowAttachedStateListener(View view, Runnable callback) {
        this.mView = view;
        this.mCallback = callback;
    }

    public void onViewAttachedToWindow(View view) {
        if (this.mIsWaitingForAttached) {
            this.mCallback.run();
            this.mIsWaitingForAttached = false;
            this.mView.removeOnAttachStateChangeListener(this);
        }
    }

    public void onViewDetachedFromWindow(View view) {
    }

    public void start() {
        this.mIsWaitingForAttached = true;
        this.mView.addOnAttachStateChangeListener(this);
    }

    public void stop() {
        this.mIsWaitingForAttached = false;
        this.mView.removeOnAttachStateChangeListener(this);
    }

    public boolean wasWindowAttached() {
        return !this.mIsWaitingForAttached;
    }
}
