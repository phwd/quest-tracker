package com.facebook.gk.store;

import com.facebook.common.preconditions.Preconditions;
import com.facebook.common.util.TriState;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class GatekeeperCache {
    private final TriState[] mFinalStates;
    private final TriState[] mOverriddenStates;
    private final TriState[] mStates;

    public GatekeeperCache(int i) {
        this.mStates = createNonInitializedArray(i);
        this.mOverriddenStates = createNonInitializedArray(i);
        this.mFinalStates = createNonInitializedArray(i);
    }

    private static TriState[] createNonInitializedArray(int i) {
        TriState[] triStateArr = new TriState[i];
        for (int i2 = 0; i2 < i; i2++) {
            triStateArr[i2] = TriState.UNSET;
        }
        return triStateArr;
    }

    public synchronized TriState get(int i) {
        return this.mFinalStates[i];
    }

    public synchronized TriState getActual(int i) {
        return this.mStates[i];
    }

    public synchronized TriState getOverridden(int i) {
        return this.mOverriddenStates[i];
    }

    public synchronized boolean isInitialized(int i) {
        return !TriState.UNSET.equals(this.mFinalStates[i]);
    }

    public synchronized boolean isOverridden(int i) {
        return !TriState.UNSET.equals(this.mOverriddenStates[i]);
    }

    private static TriState calculateFinalState(TriState triState, TriState triState2) {
        return TriState.UNSET.equals(triState2) ? triState : triState2;
    }

    private void recalculateFinalState(int i) {
        this.mFinalStates[i] = calculateFinalState(this.mStates[i], this.mOverriddenStates[i]);
    }

    public synchronized void set(int i, boolean z) {
        this.mStates[i] = TriState.valueOf(z);
        recalculateFinalState(i);
    }

    public synchronized void setOverride(int i, boolean z) {
        this.mOverriddenStates[i] = TriState.valueOf(z);
        recalculateFinalState(i);
    }

    public synchronized void unset(int i) {
        this.mStates[i] = TriState.UNSET;
        recalculateFinalState(i);
    }

    public synchronized void clearOverride(int i) {
        this.mOverriddenStates[i] = TriState.UNSET;
        recalculateFinalState(i);
    }

    public synchronized void unsetAll() {
        int length = this.mStates.length;
        for (int i = 0; i < length; i++) {
            unset(i);
        }
    }

    public void clearOverrideAll() {
        int length = this.mStates.length;
        for (int i = 0; i < length; i++) {
            clearOverride(i);
        }
    }

    public void copyFrom(GatekeeperCache gatekeeperCache) {
        Preconditions.checkArgument(gatekeeperCache.mStates.length == this.mStates.length);
        int length = this.mStates.length;
        for (int i = 0; i < length; i++) {
            this.mStates[i] = gatekeeperCache.mStates[i];
            this.mOverriddenStates[i] = gatekeeperCache.mOverriddenStates[i];
            this.mFinalStates[i] = gatekeeperCache.mFinalStates[i];
        }
    }

    public synchronized boolean areAllInitialized() {
        int length = this.mStates.length;
        for (int i = 0; i < length; i++) {
            if (!isInitialized(i)) {
                return false;
            }
        }
        return true;
    }

    public synchronized boolean containsInitialized() {
        int length = this.mStates.length;
        for (int i = 0; i < length; i++) {
            if (isInitialized(i)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.mStates.length;
    }
}
