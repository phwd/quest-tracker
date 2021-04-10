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

    public boolean isAppInForegroundV1() {
        return false;
    }

    public boolean isAppInForegroundV2() {
        return false;
    }

    public void updateAnrState(AnrState anrState) {
        throw new AbstractMethodError("Method needs to be overridden");
    }

    public void updateAnrState(AnrState anrState, @Nullable Runnable runnable) {
        updateAnrState(anrState);
        if (runnable != null) {
            runnable.run();
        }
    }

    public void updateAnrState(AnrState anrState, @Nullable Runnable runnable, boolean z) {
        updateAnrState(anrState, runnable);
    }

    public void addForegroundTransitionListener(ForegroundTransitionListener foregroundTransitionListener) {
        synchronized (this.mListeners) {
            this.mListeners.add(foregroundTransitionListener);
        }
    }

    public void removeForegroundTransitionListener(ForegroundTransitionListener foregroundTransitionListener) {
        synchronized (this.mListeners) {
            this.mListeners.remove(foregroundTransitionListener);
        }
    }

    /* access modifiers changed from: protected */
    public void callListenersForeground() {
        synchronized (this.mListeners) {
            for (ForegroundTransitionListener foregroundTransitionListener : this.mListeners) {
                foregroundTransitionListener.onForeground();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void callListenerBackground() {
        synchronized (this.mListeners) {
            for (ForegroundTransitionListener foregroundTransitionListener : this.mListeners) {
                foregroundTransitionListener.onBackground();
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
