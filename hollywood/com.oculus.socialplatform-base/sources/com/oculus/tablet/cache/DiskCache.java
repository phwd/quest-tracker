package com.oculus.tablet.cache;

import X.AnonymousClass006;
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
    public static final String TAG = LoggingUtil.tag(DiskCache.class);
    public final String CACHE_LOG_EXPIRATION_TIME_KEY = "expiration_time_seconds";
    public final String CACHE_LOG_FILENAME = "cache_log.json";
    public File mCacheDirectory;

    private String getCacheFilePath(String str) {
        return AnonymousClass006.A09(this.mCacheDirectory.getAbsolutePath(), File.separator, str);
    }

    private void initializeCacheDirectory(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getCacheDir());
        sb.append(File.separator);
        sb.append(str);
        File file = new File(sb.toString());
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
                Log.e(TAG, AnonymousClass006.A07("Failed to create file: ", e.getMessage()));
            }
            try {
                writeBytesToDisk(cacheFilePath, new JSONObject().toString().getBytes());
            } catch (IOException e2) {
                Log.e(TAG, AnonymousClass006.A07("Failed to write cache log to disk: ", e2.getMessage()));
            }
        }
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

    @Override // com.oculus.tablet.cache.Cache
    public void put(String str, CacheItem cacheItem) {
        String A09 = AnonymousClass006.A09(this.mCacheDirectory.getAbsolutePath(), File.separator, str);
        try {
            new File(A09).createNewFile();
        } catch (IOException e) {
            Log.e(TAG, AnonymousClass006.A07("Failed to create file: ", e.getMessage()));
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(A09);
            try {
                fileOutputStream.write(cacheItem.mBytes);
                fileOutputStream.flush();
                fileOutputStream.close();
                fileOutputStream.close();
                String cacheFilePath = getCacheFilePath("cache_log.json");
                try {
                    JSONObject jSONObject = new JSONObject(new String(readBytesFromDisk(cacheFilePath)));
                    JSONObject optJSONObject = jSONObject.optJSONObject(str);
                    if (optJSONObject == null) {
                        optJSONObject = new JSONObject();
                    }
                    optJSONObject.put("expiration_time_seconds", cacheItem.mExpirationTimeSeconds);
                    jSONObject.put(str, optJSONObject);
                    writeBytesToDisk(cacheFilePath, jSONObject.toString().getBytes());
                    return;
                } catch (IOException unused) {
                    Log.e(TAG, "Failed to read or write cache log from disk");
                    return;
                } catch (JSONException e2) {
                    Log.e(TAG, AnonymousClass006.A07("Failed to update cache item expiration time: ", e2.getMessage()));
                    return;
                }
            } catch (Throwable unused2) {
            }
            throw th;
        } catch (IOException e3) {
            Log.e(TAG, AnonymousClass006.A07("Failed to write bytes to disk: ", e3.getMessage()));
        }
    }

    public DiskCache(Context context, String str) {
        initializeCacheDirectory(context, str);
        initializeCacheLog();
    }

    @Override // com.oculus.tablet.cache.Cache
    public CacheItem get(String str) {
        String str2;
        String str3;
        String cacheFilePath = getCacheFilePath(str);
        try {
            CacheItem cacheItem = new CacheItem(readBytesFromDisk(cacheFilePath));
            try {
                cacheItem.mExpirationTimeSeconds = new JSONObject(new String(readBytesFromDisk(getCacheFilePath("cache_log.json")))).getJSONObject(str).getLong("expiration_time_seconds");
                return cacheItem;
            } catch (IOException unused) {
                str2 = TAG;
                str3 = "Failed to read cache log from disk";
                Log.e(str2, str3);
                return cacheItem;
            } catch (JSONException unused2) {
                str2 = TAG;
                str3 = "Failed to read JSON from cache log";
                Log.e(str2, str3);
                return cacheItem;
            }
        } catch (IOException unused3) {
            Log.e(TAG, AnonymousClass006.A07("Failed to read cached file at path: ", cacheFilePath));
            return null;
        }
    }
}
