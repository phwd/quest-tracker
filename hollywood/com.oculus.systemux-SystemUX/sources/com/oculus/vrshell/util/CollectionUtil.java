package com.oculus.vrshell.util;

import java.util.HashMap;

public final class CollectionUtil {
    private CollectionUtil() {
    }

    public static String[] toArray(HashMap<String, String> hashMap) {
        String[] strArr = new String[(hashMap.size() * 2)];
        int i = 0;
        for (String str : hashMap.keySet()) {
            strArr[i] = str;
            strArr[i + 1] = hashMap.get(str);
            i += 2;
        }
        return strArr;
    }

    public static HashMap<String, String> fromArray(String[] strArr) {
        if (strArr.length % 2 == 0) {
            HashMap<String, String> hashMap = new HashMap<>();
            for (int i = 0; i < strArr.length; i += 2) {
                hashMap.put(strArr[i], strArr[i + 1]);
            }
            return hashMap;
        }
        throw new RuntimeException("Array length must be a multiple of 2!");
    }
}
