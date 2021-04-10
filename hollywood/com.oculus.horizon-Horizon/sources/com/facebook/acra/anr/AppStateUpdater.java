package com.facebook.acra.anr;

import X.AnonymousClass0VV;
import X.AnonymousClass0VW;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class AppStateUpdater {
    public final List<AnonymousClass0VW> mListeners = new ArrayList();

    public boolean isAppInForegroundV1() {
        return false;
    }

    public boolean isAppInForegroundV2() {
        return false;
    }

    public void addForegroundTransitionListener(AnonymousClass0VW r3) {
        synchronized (this.mListeners) {
            this.mListeners.add(r3);
        }
    }

    public void callListenerBackground() {
        synchronized (this.mListeners) {
            Iterator<AnonymousClass0VW> it = this.mListeners.iterator();
            if (it.hasNext()) {
                it.next();
                throw null;
            }
        }
    }

    public void callListenersForeground() {
        synchronized (this.mListeners) {
            Iterator<AnonymousClass0VW> it = this.mListeners.iterator();
            if (it.hasNext()) {
                it.next();
                throw null;
            }
        }
    }

    public int getForegroundTransitionListenerCount() {
        int size;
        synchronized (this.mListeners) {
            size = this.mListeners.size();
        }
        return size;
    }

    public void removeForegroundTransitionListener(AnonymousClass0VW r3) {
        synchronized (this.mListeners) {
            this.mListeners.remove(r3);
        }
    }

    public void updateAnrState(AnonymousClass0VV r3) {
        throw new AbstractMethodError("Method needs to be overridden");
    }

    public void updateAnrState(AnonymousClass0VV r3, @Nullable Runnable runnable) {
        updateAnrState(r3);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public void updateAnrState(AnonymousClass0VV r1, @Nullable Runnable runnable, boolean z) {
        updateAnrState(r1, runnable);
    }
}
