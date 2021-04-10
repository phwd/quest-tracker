package com.facebook.internal;

import android.content.Intent;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import java.util.HashMap;
import java.util.Map;

public final class CallbackManagerImpl implements CallbackManager {
    public static Map<Integer, Callback> staticCallbacks = new HashMap();
    public Map<Integer, Callback> callbacks = new HashMap();

    public interface Callback {
        boolean onActivityResult(int i, Intent intent);
    }

    public enum RequestCodeOffset {
        Login(0),
        Share(1),
        Message(2),
        Like(3),
        GameRequest(4),
        AppGroupCreate(5),
        AppGroupJoin(6),
        AppInvite(7);
        
        public final int offset;

        /* access modifiers changed from: public */
        RequestCodeOffset(int i) {
            this.offset = i;
        }

        public int toRequestCode() {
            Validate.sdkInitialized();
            return FacebookSdk.callbackRequestCodeOffset + this.offset;
        }
    }

    public static synchronized Callback getStaticCallback(Integer num) {
        Callback callback;
        synchronized (CallbackManagerImpl.class) {
            callback = staticCallbacks.get(num);
        }
        return callback;
    }

    public static synchronized void registerStaticCallback(int i, Callback callback) {
        synchronized (CallbackManagerImpl.class) {
            Validate.notNull(callback, "callback");
            Map<Integer, Callback> map = staticCallbacks;
            Integer valueOf = Integer.valueOf(i);
            if (!map.containsKey(valueOf)) {
                staticCallbacks.put(valueOf, callback);
            }
        }
    }

    @Override // com.facebook.CallbackManager
    public boolean onActivityResult(int i, int i2, Intent intent) {
        Callback callback = this.callbacks.get(Integer.valueOf(i));
        if (callback != null) {
            return callback.onActivityResult(i2, intent);
        }
        return runStaticCallback(i, i2, intent);
    }

    public void registerCallback(int i, Callback callback) {
        Validate.notNull(callback, "callback");
        this.callbacks.put(Integer.valueOf(i), callback);
    }

    public static boolean runStaticCallback(int i, int i2, Intent intent) {
        Callback staticCallback = getStaticCallback(Integer.valueOf(i));
        if (staticCallback != null) {
            return staticCallback.onActivityResult(i2, intent);
        }
        return false;
    }
}
