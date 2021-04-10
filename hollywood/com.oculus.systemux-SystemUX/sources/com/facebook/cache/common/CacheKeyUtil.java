package com.facebook.cache.common;

import com.bumptech.glide.load.Key;
import com.facebook.common.util.SecureHashUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public final class CacheKeyUtil {
    public static List<String> getResourceIds(CacheKey cacheKey) {
        ArrayList arrayList;
        try {
            if (cacheKey instanceof MultiCacheKey) {
                List<CacheKey> cacheKeys = ((MultiCacheKey) cacheKey).getCacheKeys();
                arrayList = new ArrayList(cacheKeys.size());
                for (int i = 0; i < cacheKeys.size(); i++) {
                    arrayList.add(secureHashKey(cacheKeys.get(i)));
                }
            } else {
                arrayList = new ArrayList(1);
                arrayList.add(cacheKey.isResourceIdForDebugging() ? cacheKey.getUriString() : secureHashKey(cacheKey));
            }
            return arrayList;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFirstResourceId(CacheKey cacheKey) {
        try {
            if (cacheKey instanceof MultiCacheKey) {
                return secureHashKey(((MultiCacheKey) cacheKey).getCacheKeys().get(0));
            }
            return secureHashKey(cacheKey);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String secureHashKey(CacheKey cacheKey) throws UnsupportedEncodingException {
        return SecureHashUtil.makeSHA1HashBase64(cacheKey.getUriString().getBytes(Key.STRING_CHARSET_NAME));
    }
}
