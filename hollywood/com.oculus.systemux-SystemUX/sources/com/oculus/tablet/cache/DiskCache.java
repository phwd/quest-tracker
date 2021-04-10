package com.oculus.tablet.cache;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class DiskCache implements Cache {
    private static final String TAG = LoggingUtil.tag(DiskCache.class);
    private final String CACHE_LOG_EXPIRATION_TIME_KEY = "expiration_time_seconds";
    private final String CACHE_LOG_FILENAME = "cache_log.json";
    private File mCacheDirectory;

    public DiskCache(Context context, String str) {
        initializeCacheDirectory(context, str);
        initializeCacheLog();
    }

    private void initializeCacheDirectory(Context context, String str) {
        File file = new File(context.getCacheDir() + File.separator + str);
        if (!file.exists()) {
            file.mkdir();
        }
        this.mCacheDirectory = file;
    }

    private void initializeCacheLog() {
        String cacheFilePath = getCacheFilePath("cache_log.json");
        File file = new File(cacheFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                String str = TAG;
                Log.e(str, "Failed to create file: " + e.getMessage());
            }
            try {
                writeBytesToDisk(cacheFilePath, new JSONObject().toString().getBytes());
            } catch (IOException e2) {
                String str2 = TAG;
                Log.e(str2, "Failed to write cache log to disk: " + e2.getMessage());
            }
            String str3 = TAG;
            Log.d(str3, "Wrote cache log to file at " + cacheFilePath);
        }
    }

    private String getCacheFilePath(String str) {
        return this.mCacheDirectory.getAbsolutePath() + File.separator + str;
    }

    @Override // com.oculus.tablet.cache.Cache
    public CacheItem get(String str) {
        String cacheFilePath = getCacheFilePath(str);
        try {
            CacheItem cacheItem = new CacheItem(readBytesFromDisk(cacheFilePath));
            String str2 = TAG;
            Log.d(str2, "Read cached file from cache at " + cacheFilePath);
            try {
                cacheItem.setExpirationTime(new JSONObject(new String(readBytesFromDisk(getCacheFilePath("cache_log.json")))).getJSONObject(str).getLong("expiration_time_seconds"));
            } catch (IOException unused) {
                Log.e(TAG, "Failed to read cache log from disk");
            } catch (JSONException unused2) {
                Log.e(TAG, "Failed to read JSON from cache log");
            }
            return cacheItem;
        } catch (IOException unused3) {
            String str3 = TAG;
            Log.e(str3, "Failed to read cached file at path: " + cacheFilePath);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x006b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0070, code lost:
        if (r0 != null) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0076, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0077, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007b, code lost:
        r1.close();
     */
    @Override // com.oculus.tablet.cache.Cache
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void put(java.lang.String r7, com.oculus.tablet.cache.CacheItem r8) {
        /*
        // Method dump skipped, instructions count: 245
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.tablet.cache.DiskCache.put(java.lang.String, com.oculus.tablet.cache.CacheItem):void");
    }

    private byte[] readBytesFromDisk(String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        byte[] bArr = new byte[fileInputStream.available()];
        fileInputStream.read(bArr);
        return bArr;
    }

    private void writeBytesToDisk(String str, byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(str);
        fileOutputStream.write(bArr);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
