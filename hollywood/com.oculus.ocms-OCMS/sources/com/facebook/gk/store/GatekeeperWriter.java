package com.facebook.gk.store;

import com.facebook.common.util.TriState;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface GatekeeperWriter {

    public interface Editor {
        Editor clearOverride(int i);

        Editor clearOverride(String str);

        Editor clearOverrideAll();

        void commit();

        void commit(boolean z);

        Editor override(int i, TriState triState);

        Editor override(int i, boolean z);

        Editor override(String str, TriState triState);

        Editor override(String str, boolean z);

        Editor set(int i, TriState triState);

        Editor set(int i, boolean z);

        Editor set(String str, TriState triState);

        Editor set(String str, boolean z);

        Editor setAll(boolean[] zArr);

        Editor skipInitialized();

        Editor unset(int i);

        Editor unset(String str);
    }

    Editor edit();
}
