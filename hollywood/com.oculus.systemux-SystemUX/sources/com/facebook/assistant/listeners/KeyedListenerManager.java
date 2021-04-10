package com.facebook.assistant.listeners;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class KeyedListenerManager<T> {
    private final Handler mHandler;
    private final Map<String, T> mListeners;

    public KeyedListenerManager() {
        this(new Handler(Looper.getMainLooper()));
    }

    public KeyedListenerManager(Handler handler) {
        this.mListeners = Collections.synchronizedMap(new HashMap());
        this.mHandler = handler;
    }

    @Nullable
    public String registerListener(@Nullable T t) {
        return registerListener(null, t);
    }

    @Nullable
    public String registerListener(@Nullable String str, @Nullable T t) {
        if (t == null) {
            return null;
        }
        if (str == null) {
            String uuid = UUID.randomUUID().toString();
            registerListener(uuid, t);
            return uuid;
        }
        this.mListeners.put(str, t);
        return str;
    }

    @Nullable
    public T unregisterListener(@Nullable String str) {
        if (str != null) {
            return this.mListeners.remove(str);
        }
        return null;
    }

    public boolean fire(Callback<T> callback) {
        Iterator<T> it = this.mListeners.values().iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                boolean run = callback.run(it.next());
                if (z || run) {
                    z = true;
                }
            }
        }
    }

    public void post(final Post<T> post) {
        post(new Callback<T>() {
            /* class com.facebook.assistant.listeners.KeyedListenerManager.AnonymousClass1 */

            @Override // com.facebook.assistant.listeners.Callback
            public boolean run(T t) {
                post.run(t);
                return false;
            }
        }, null);
    }

    public void post(final Callback<T> callback, @Nullable final Post<Boolean> post) {
        this.mHandler.post(new Runnable() {
            /* class com.facebook.assistant.listeners.KeyedListenerManager.AnonymousClass2 */

            /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: com.facebook.assistant.listeners.Post */
            /* JADX WARN: Multi-variable type inference failed */
            public void run() {
                boolean fire = KeyedListenerManager.this.fire(callback);
                Post post = post;
                if (post != 0) {
                    post.run(Boolean.valueOf(fire));
                }
            }
        });
    }
}
