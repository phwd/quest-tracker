package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonLigerRequestInfo {
    @Nullable
    private final Map<String, String> mProperties;
    private final boolean mReplaySafe;
    private final UniqueConnectionSettings mUniqueConnectionSettings;

    public TigonLigerRequestInfo(boolean z, UniqueConnectionSettings uniqueConnectionSettings, @Nullable Map<String, String> map) {
        this.mReplaySafe = z;
        this.mUniqueConnectionSettings = uniqueConnectionSettings;
        this.mProperties = map;
    }

    public boolean replaySafe() {
        return this.mReplaySafe;
    }

    public UniqueConnectionSettings uniqueConnectionSettings() {
        return this.mUniqueConnectionSettings;
    }

    @Nullable
    public Map<String, String> properties() {
        return this.mProperties;
    }
}
