package com.oculus.appmanager.installer.common;

import com.facebook.internal.Utility;
import com.facebook.ultralight.Dependencies;
import java.util.HashMap;
import java.util.Map;

@Dependencies({})
public class CryptoMethods {
    public static final Map<String, Integer> HASH_SIZE_MAP = new HashMap<String, Integer>() {
        /* class com.oculus.appmanager.installer.common.CryptoMethods.AnonymousClass1 */

        {
            put(Utility.HASH_ALGORITHM_MD5, 32);
            put(Utility.HASH_ALGORITHM_SHA1, 40);
            put("SHA-256", 64);
        }
    };
}
