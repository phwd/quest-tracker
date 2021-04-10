package com.facebook.assistant.listeners;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ListenerManager<T> {
    private final Handler mHandler;
    private final CopyOnWriteArrayList<T> mListeners;

    public ListenerManager() {
        this(new Handler(Looper.getMainLooper()));
    }

    public ListenerManager(Handler handler) {
        this.mListeners = new CopyOnWriteArrayList<>();
        this.mHandler = handler;
    }

    @Nullable
    public T registerListener(@Nullable T t) {
        if (t == null || this.mListeners.contains(t)) {
            return null;
        }
        this.mListeners.add(t);
        return t;
    }

    @Nullable
    public T unregisterListener(@Nullable T t) {
        if (t == null || !this.mListeners.remove(t)) {
            return null;
        }
        return t;
    }

    public boolean fire(Callback<T> callback) {
        Iterator<T> it = this.mListeners.iterator();
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
            /* class com.facebook.assistant.listeners.ListenerManager.AnonymousClass1 */

            @Override // com.facebook.assistant.listeners.Callback
            public boolean run(T t) {
                post.run(t);
                return false;
            }
        }, null);
    }

    public void post(final Callback<T> callback, @Nullable final Post<Boolean> post) {
        this.mHandler.post(new Runnable() {
            /* class com.facebook.assistant.listeners.ListenerManager.AnonymousClass2 */

            /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: com.facebook.assistant.listeners.Post */
            /* JADX WARN: Multi-variable type inference failed */
            public void run() {
                boolean fire = ListenerManager.this.fire(callback);
                Post post = post;
                if (post != 0) {
                    post.run(Boolean.valueOf(fire));
                }
            }
        });
    }

    public int size() {
        return this.mListeners.size();
    }

    public void clear() {
        this.mListeners.clear();
    }
}
