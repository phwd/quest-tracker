package com.facebook.assistant.listeners;

import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class RemoteServiceListenerManager<T> {
    private final Handler mHandler;
    @GuardedBy("this")
    private final Map<String, T> mListeners;
    private final String mTag;

    public RemoteServiceListenerManager(String str) {
        this(str, new Handler(Looper.getMainLooper()));
    }

    public RemoteServiceListenerManager(String str, Handler handler) {
        this.mListeners = Collections.synchronizedMap(new HashMap());
        this.mHandler = handler;
        this.mTag = str;
    }

    @Nullable
    public String registerListener(@Nullable T t) {
        return registerListener(null, t);
    }

    @Nullable
    public synchronized String registerListener(@Nullable String str, @Nullable T t) {
        if (t == null) {
            return null;
        }
        if (str == null) {
            str = UUID.randomUUID().toString();
            registerListener(str, t);
        } else {
            this.mListeners.put(str, t);
        }
        return str;
    }

    @Nullable
    public synchronized T unregisterListener(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return this.mListeners.remove(str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.facebook.assistant.listeners.RemoteServiceListenerManager<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public synchronized boolean fire(RemoteServiceCallback<T> remoteServiceCallback) {
        boolean z;
        z = false;
        ArrayList<Map.Entry> arrayList = new ArrayList();
        arrayList.addAll(this.mListeners.entrySet());
        for (Map.Entry entry : arrayList) {
            z |= onFire(remoteServiceCallback, (String) entry.getKey(), entry.getValue());
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public boolean onFire(RemoteServiceCallback<T> remoteServiceCallback, String str, T t) {
        try {
            return remoteServiceCallback.run(str, t);
        } catch (DeadObjectException unused) {
            String str2 = this.mTag;
            Log.w(str2, "Client " + str + " lost connection or died. Unregistering for future requests");
            unregisterListener(str);
        } catch (RemoteException e) {
            Log.e(this.mTag, e.getMessage(), e);
        } catch (Exception e2) {
            String str3 = this.mTag;
            Log.e(str3, "Client threw an exception. Catching it here to prevent service crash. Error: " + e2.getMessage(), e2);
        }
        return false;
    }

    public void post(final RemoteServicePost<T> remoteServicePost) {
        post(new RemoteServiceCallback<T>() {
            /* class com.facebook.assistant.listeners.RemoteServiceListenerManager.AnonymousClass1 */

            @Override // com.facebook.assistant.listeners.RemoteServiceCallback
            public boolean run(String str, T t) {
                try {
                    remoteServicePost.run(str, t);
                    return false;
                } catch (DeadObjectException unused) {
                    String str2 = RemoteServiceListenerManager.this.mTag;
                    Log.w(str2, "Client " + str + " lost connection or died. Unregistering for future requests");
                    RemoteServiceListenerManager.this.unregisterListener(str);
                    return false;
                } catch (RemoteException e) {
                    Log.e(RemoteServiceListenerManager.this.mTag, e.getMessage(), e);
                    return false;
                }
            }
        }, null);
    }

    public void post(final RemoteServiceCallback<T> remoteServiceCallback, @Nullable final Post<Boolean> post) {
        this.mHandler.post(new Runnable() {
            /* class com.facebook.assistant.listeners.RemoteServiceListenerManager.AnonymousClass2 */

            /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: com.facebook.assistant.listeners.Post */
            /* JADX WARN: Multi-variable type inference failed */
            public void run() {
                boolean fire = RemoteServiceListenerManager.this.fire(remoteServiceCallback);
                Post post = post;
                if (post != 0) {
                    post.run(Boolean.valueOf(fire));
                }
            }
        });
    }

    public synchronized boolean hasListener(String str) {
        return this.mListeners.containsKey(str);
    }

    public int size() {
        return this.mListeners.size();
    }

    public Collection<T> getListeners() {
        return this.mListeners.values();
    }

    public Set<String> getKeys() {
        return this.mListeners.keySet();
    }
}
