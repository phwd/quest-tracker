package androidx.activity;

import android.annotation.SuppressLint;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayDeque;
import java.util.Iterator;

public final class OnBackPressedDispatcher {
    private final Runnable mFallbackOnBackPressed;
    final ArrayDeque<OnBackPressedCallback> mOnBackPressedCallbacks;

    public OnBackPressedDispatcher() {
        this(null);
    }

    public OnBackPressedDispatcher(Runnable fallbackOnBackPressed) {
        this.mOnBackPressedCallbacks = new ArrayDeque<>();
        this.mFallbackOnBackPressed = fallbackOnBackPressed;
    }

    /* access modifiers changed from: package-private */
    public Cancellable addCancellableCallback(OnBackPressedCallback onBackPressedCallback) {
        this.mOnBackPressedCallbacks.add(onBackPressedCallback);
        OnBackPressedCancellable cancellable = new OnBackPressedCancellable(onBackPressedCallback);
        onBackPressedCallback.addCancellable(cancellable);
        return cancellable;
    }

    @SuppressLint({"LambdaLast"})
    public void addCallback(LifecycleOwner owner, OnBackPressedCallback onBackPressedCallback) {
        Lifecycle lifecycle = owner.getLifecycle();
        if (lifecycle.getCurrentState() != Lifecycle.State.DESTROYED) {
            onBackPressedCallback.addCancellable(new LifecycleOnBackPressedCancellable(lifecycle, onBackPressedCallback));
        }
    }

    public void onBackPressed() {
        Iterator<OnBackPressedCallback> iterator = this.mOnBackPressedCallbacks.descendingIterator();
        while (iterator.hasNext()) {
            OnBackPressedCallback callback = iterator.next();
            if (callback.isEnabled()) {
                callback.handleOnBackPressed();
                return;
            }
        }
        if (this.mFallbackOnBackPressed != null) {
            this.mFallbackOnBackPressed.run();
        }
    }

    /* access modifiers changed from: private */
    public class OnBackPressedCancellable implements Cancellable {
        private final OnBackPressedCallback mOnBackPressedCallback;

        OnBackPressedCancellable(OnBackPressedCallback onBackPressedCallback) {
            this.mOnBackPressedCallback = onBackPressedCallback;
        }

        @Override // androidx.activity.Cancellable
        public void cancel() {
            OnBackPressedDispatcher.this.mOnBackPressedCallbacks.remove(this.mOnBackPressedCallback);
            this.mOnBackPressedCallback.removeCancellable(this);
        }
    }

    private class LifecycleOnBackPressedCancellable implements Cancellable, LifecycleEventObserver {
        private Cancellable mCurrentCancellable;
        private final Lifecycle mLifecycle;
        private final OnBackPressedCallback mOnBackPressedCallback;

        LifecycleOnBackPressedCancellable(Lifecycle lifecycle, OnBackPressedCallback onBackPressedCallback) {
            this.mLifecycle = lifecycle;
            this.mOnBackPressedCallback = onBackPressedCallback;
            lifecycle.addObserver(this);
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.mCurrentCancellable = OnBackPressedDispatcher.this.addCancellableCallback(this.mOnBackPressedCallback);
            } else if (event == Lifecycle.Event.ON_STOP) {
                if (this.mCurrentCancellable != null) {
                    this.mCurrentCancellable.cancel();
                }
            } else if (event == Lifecycle.Event.ON_DESTROY) {
                cancel();
            }
        }

        @Override // androidx.activity.Cancellable
        public void cancel() {
            this.mLifecycle.removeObserver(this);
            this.mOnBackPressedCallback.removeCancellable(this);
            if (this.mCurrentCancellable != null) {
                this.mCurrentCancellable.cancel();
                this.mCurrentCancellable = null;
            }
        }
    }
}
