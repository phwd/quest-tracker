package com.facebook.acra.anr;

import X.AbstractC0187Iz;
import X.EnumC0186Iy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AppStateUpdater {
    public final List mListeners = new ArrayList();

    public boolean isAppInForegroundV1() {
        return false;
    }

    public boolean isAppInForegroundV2() {
        return false;
    }

    public void addForegroundTransitionListener(AbstractC0187Iz iz) {
        synchronized (this.mListeners) {
            this.mListeners.add(iz);
        }
    }

    public void callListenerBackground() {
        synchronized (this.mListeners) {
            Iterator it = this.mListeners.iterator();
            if (it.hasNext()) {
                it.next();
                throw new NullPointerException("onBackground");
            }
        }
    }

    public void callListenersForeground() {
        synchronized (this.mListeners) {
            Iterator it = this.mListeners.iterator();
            if (it.hasNext()) {
                it.next();
                throw new NullPointerException("onForeground");
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

    public void removeForegroundTransitionListener(AbstractC0187Iz iz) {
        synchronized (this.mListeners) {
            this.mListeners.remove(iz);
        }
    }

    public void updateAnrState(EnumC0186Iy iy) {
        throw new AbstractMethodError("Method needs to be overridden");
    }

    public void updateAnrState(EnumC0186Iy iy, Runnable runnable) {
        updateAnrState(iy);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public void updateAnrState(EnumC0186Iy iy, Runnable runnable, boolean z) {
        updateAnrState(iy, runnable);
    }
}
