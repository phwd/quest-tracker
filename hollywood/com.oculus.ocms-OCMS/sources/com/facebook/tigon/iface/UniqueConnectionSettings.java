package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class UniqueConnectionSettings {
    private final String mId;
    @Nullable
    private final Map<String, String> mTransportSettings;

    public UniqueConnectionSettings(String str, @Nullable Map<String, String> map) {
        this.mId = str;
        this.mTransportSettings = map;
    }

    public UniqueConnectionSettings(String str) {
        this(str, null);
    }

    public String uniqueConnectionId() {
        return this.mId;
    }

    @Nullable
    public Map<String, String> transportSettings() {
        return this.mTransportSettings;
    }
}
