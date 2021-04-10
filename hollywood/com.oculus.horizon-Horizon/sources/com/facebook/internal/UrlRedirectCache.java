package com.facebook.internal;

import X.AnonymousClass006;
import android.net.Uri;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.internal.FileLruCache;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class UrlRedirectCache {
    public static final String REDIRECT_CONTENT_TAG = "UrlRedirectCache_Redirect";
    public static final String TAG = "UrlRedirectCache";
    public static volatile FileLruCache urlRedirectCache;

    public static Uri getRedirectedUri(Uri uri) {
        InputStreamReader inputStreamReader;
        Throwable th;
        if (uri != null) {
            String obj = uri.toString();
            try {
                FileLruCache cache = getCache();
                InputStreamReader inputStreamReader2 = null;
                boolean z = false;
                while (true) {
                    try {
                        InputStream inputStream = cache.get(obj, REDIRECT_CONTENT_TAG);
                        if (inputStream == null) {
                            break;
                        }
                        z = true;
                        inputStreamReader = new InputStreamReader(inputStream);
                        try {
                            char[] cArr = new char[FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE];
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                int read = inputStreamReader.read(cArr, 0, FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE);
                                if (read <= 0) {
                                    break;
                                }
                                sb.append(cArr, 0, read);
                            }
                            Utility.closeQuietly(inputStreamReader);
                            obj = sb.toString();
                            inputStreamReader2 = inputStreamReader;
                        } catch (IOException unused) {
                            Utility.closeQuietly(inputStreamReader);
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            Utility.closeQuietly(inputStreamReader);
                            throw th;
                        }
                    } catch (IOException unused2) {
                        inputStreamReader = inputStreamReader2;
                        Utility.closeQuietly(inputStreamReader);
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        inputStreamReader = inputStreamReader2;
                        Utility.closeQuietly(inputStreamReader);
                        throw th;
                    }
                }
                if (z) {
                    Uri parse = Uri.parse(obj);
                    Utility.closeQuietly(inputStreamReader2);
                    return parse;
                }
                Utility.closeQuietly(inputStreamReader2);
                return null;
            } catch (IOException unused3) {
                inputStreamReader = null;
                Utility.closeQuietly(inputStreamReader);
                return null;
            } catch (Throwable th4) {
                th = th4;
                inputStreamReader = null;
                Utility.closeQuietly(inputStreamReader);
                throw th;
            }
        }
        return null;
    }

    public static void cacheUriRedirect(Uri uri, Uri uri2) {
        if (uri != null && uri2 != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getCache().openPutStream(uri.toString(), REDIRECT_CONTENT_TAG);
                outputStream.write(uri2.toString().getBytes());
            } catch (IOException unused) {
            } catch (Throwable th) {
                Utility.closeQuietly(outputStream);
                throw th;
            }
            Utility.closeQuietly(outputStream);
        }
    }

    public static synchronized FileLruCache getCache() throws IOException {
        FileLruCache fileLruCache;
        synchronized (UrlRedirectCache.class) {
            if (urlRedirectCache == null) {
                urlRedirectCache = new FileLruCache(TAG, new FileLruCache.Limits());
            }
            fileLruCache = urlRedirectCache;
        }
        return fileLruCache;
    }

    public static void clearCache() {
        try {
            getCache().clearCache();
        } catch (IOException e) {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, AnonymousClass006.A05("clearCache failed ", e.getMessage()));
        }
    }
}
