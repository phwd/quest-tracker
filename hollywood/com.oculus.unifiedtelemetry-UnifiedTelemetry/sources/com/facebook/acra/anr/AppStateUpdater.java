package com.facebook.acra.anr;

import X.gL;
import X.gM;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class AppStateUpdater {
    public final List<gM> mListeners = new ArrayList();

    public boolean isAppInForegroundV1() {
        return false;
    }

    public boolean isAppInForegroundV2() {
        return false;
    }

    public void addForegroundTransitionListener(gM gMVar) {
        synchronized (this.mListeners) {
            this.mListeners.add(gMVar);
        }
    }

    public void callListenerBackground() {
        synchronized (this.mListeners) {
            Iterator<gM> it = this.mListeners.iterator();
            if (it.hasNext()) {
                it.next();
                throw null;
            }
        }
    }

    public void callListenersForeground() {
        synchronized (this.mListeners) {
            Iterator<gM> it = this.mListeners.iterator();
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

    public void removeForegroundTransitionListener(gM gMVar) {
        synchronized (this.mListeners) {
            this.mListeners.remove(gMVar);
        }
    }

    public void updateAnrState(gL gLVar) {
        throw new AbstractMethodError("Method needs to be overridden");
    }

    public void updateAnrState(gL gLVar, @Nullable Runnable runnable) {
        updateAnrState(gLVar);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public void updateAnrState(gL gLVar, @Nullable Runnable runnable, boolean z) {
        updateAnrState(gLVar, runnable);
    }
}
