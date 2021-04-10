package com.facebook.acra.anr;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.reliability.anr.AnrState;
import com.facebook.reliability.anr.ForegroundTransitionListener;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class AppStateUpdater {
    private final List<ForegroundTransitionListener> mListeners = new ArrayList();

    public void updateAnrState(AnrState state) {
        throw new AbstractMethodError("Method needs to be overridden");
    }

    public void updateAnrState(AnrState state, @Nullable Runnable afterUpdateRunnable) {
        updateAnrState(state);
        if (afterUpdateRunnable != null) {
            afterUpdateRunnable.run();
        }
    }

    public void updateAnrState(AnrState state, @Nullable Runnable afterUpdateRunnable, boolean inForeground) {
        updateAnrState(state, afterUpdateRunnable);
    }

    public boolean isAppInForegroundV1() {
        return false;
    }

    public boolean isAppInForegroundV2() {
        return false;
    }

    public void addForegroundTransitionListener(ForegroundTransitionListener listener) {
        synchronized (this.mListeners) {
            this.mListeners.add(listener);
        }
    }

    public void removeForegroundTransitionListener(ForegroundTransitionListener listener) {
        synchronized (this.mListeners) {
            this.mListeners.remove(listener);
        }
    }

    /* access modifiers changed from: protected */
    public void callListenersForeground() {
        synchronized (this.mListeners) {
            for (ForegroundTransitionListener l : this.mListeners) {
                l.onForeground();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void callListenerBackground() {
        synchronized (this.mListeners) {
            for (ForegroundTransitionListener l : this.mListeners) {
                l.onBackground();
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getForegroundTransitionListenerCount() {
        int size;
        synchronized (this.mListeners) {
            size = this.mListeners.size();
        }
        return size;
    }
}
