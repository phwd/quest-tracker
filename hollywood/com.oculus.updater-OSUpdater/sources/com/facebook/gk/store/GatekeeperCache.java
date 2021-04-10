package com.facebook.gk.store;

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
}
