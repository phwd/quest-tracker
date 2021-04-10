package libraries.marauder.analytics.utils.json;

import javax.annotation.Nullable;

public class JsonString implements JsonType {
    public String array;

    public String toString() {
        return this.array;
    }

    public JsonString(double d) {
        this.array = String.valueOf(d);
    }

    public JsonString(long j) {
        this.array = String.valueOf(j);
    }

    public JsonString(@Nullable String str) {
        this.array = JsonUtils.serializeV2(str);
    }

    public JsonString(boolean z) {
        this.array = String.valueOf(z);
    }
}
