package com.android.volley;

import com.android.volley.Cache;

public class Response<T> {
    public final Cache.Entry cacheEntry;
    public final VolleyError error;
    public boolean intermediate;
    public final T result;

    public interface ErrorListener {
        void onErrorResponse(VolleyError volleyError);
    }

    public interface Listener<T> {
        void onResponse(T t);
    }

    public static <T> Response<T> success(T result2, Cache.Entry cacheEntry2) {
        return new Response<>(result2, cacheEntry2);
    }

    public static <T> Response<T> error(VolleyError error2) {
        return new Response<>(error2);
    }

    public boolean isSuccess() {
        return this.error == null;
    }

    private Response(T result2, Cache.Entry cacheEntry2) {
        this.intermediate = false;
        this.result = result2;
        this.cacheEntry = cacheEntry2;
        this.error = null;
    }

    private Response(VolleyError error2) {
        this.intermediate = false;
        this.result = null;
        this.cacheEntry = null;
        this.error = error2;
    }
}
