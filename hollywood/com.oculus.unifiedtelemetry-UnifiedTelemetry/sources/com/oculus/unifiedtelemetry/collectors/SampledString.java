package com.oculus.unifiedtelemetry.collectors;

import java.util.HashMap;

public class SampledString {
    public static final int DEFAULT_INITIAL_CAPACITY = 11;
    public int mK = 3;
    public HashMap<String, Integer> mStringFrequency = new HashMap<>();

    public final void A00(String str) {
        HashMap<String, Integer> hashMap;
        int i;
        int intValue;
        if (this.mStringFrequency.containsKey(str)) {
            Integer num = this.mStringFrequency.get(str);
            hashMap = this.mStringFrequency;
            if (num == null) {
                intValue = 0;
            } else {
                intValue = num.intValue();
            }
            i = Integer.valueOf(intValue + 1);
        } else {
            hashMap = this.mStringFrequency;
            i = 1;
        }
        hashMap.put(str, i);
    }
}
