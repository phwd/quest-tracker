package com.oculus.vrshell.util;

import java.util.HashMap;

public final class CollectionUtil {
    public static HashMap<String, String> fromArray(String[] strArr) {
        int length = strArr.length;
        if (length % 2 == 0) {
            HashMap<String, String> hashMap = new HashMap<>();
            for (int i = 0; i < length; i += 2) {
                hashMap.put(strArr[i], strArr[i + 1]);
            }
            return hashMap;
        }
        throw new RuntimeException("Array length must be a multiple of 2!");
    }

    public static String[] toArray(HashMap<String, String> hashMap) {
        String[] strArr = new String[(hashMap.size() << 1)];
        int i = 0;
        for (String str : hashMap.keySet()) {
            strArr[i] = str;
            strArr[i + 1] = hashMap.get(str);
            i += 2;
        }
        return strArr;
    }
}
