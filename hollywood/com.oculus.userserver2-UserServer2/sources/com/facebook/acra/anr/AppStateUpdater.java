package com.facebook.acra.anr;

import X.AbstractC0194ec;
import X.EnumC0193eb;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class AppStateUpdater {
    public final List<AbstractC0194ec> mListeners = new ArrayList();

    public boolean isAppInForegroundV1() {
        return false;
    }

    public boolean isAppInForegroundV2() {
        return false;
    }

    public void addForegroundTransitionListener(AbstractC0194ec ecVar) {
        synchronized (this.mListeners) {
            this.mListeners.add(ecVar);
        }
    }

    public void callListenerBackground() {
        synchronized (this.mListeners) {
            Iterator<AbstractC0194ec> it = this.mListeners.iterator();
            if (it.hasNext()) {
                it.next();
                throw null;
            }
        }
    }

    public void callListenersForeground() {
        synchronized (this.mListeners) {
            Iterator<AbstractC0194ec> it = this.mListeners.iterator();
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

    public void removeForegroundTransitionListener(AbstractC0194ec ecVar) {
        synchronized (this.mListeners) {
            this.mListeners.remove(ecVar);
        }
    }

    public void updateAnrState(EnumC0193eb ebVar) {
        throw new AbstractMethodError("Method needs to be overridden");
    }

    public void updateAnrState(EnumC0193eb ebVar, @Nullable Runnable runnable) {
        updateAnrState(ebVar);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public void updateAnrState(EnumC0193eb ebVar, @Nullable Runnable runnable, boolean z) {
        updateAnrState(ebVar, runnable);
    }
}
