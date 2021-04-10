package com.oculus.extras;

import java.util.HashMap;
import java.util.Map;

public class ExtrasBuilder {
    public Map<String, String> mData;

    public final void A00(String str, String str2) {
        if (str2 == null) {
            this.mData.remove(str);
        } else {
            this.mData.put(str, str2);
        }
    }

    public ExtrasBuilder() {
        this(new HashMap());
    }

    public ExtrasBuilder(Map<String, String> map) {
        this.mData = new HashMap(map);
    }
}
