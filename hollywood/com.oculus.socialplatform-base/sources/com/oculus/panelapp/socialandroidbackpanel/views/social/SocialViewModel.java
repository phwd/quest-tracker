package com.oculus.panelapp.socialandroidbackpanel.views.social;

import X.AnonymousClass1uc;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class SocialViewModel extends AnonymousClass1uc {
    public final Map<String, AsyncQueryHandle> mQueryHandleMap = new HashMap();

    public void clearHandle(String str) {
        this.mQueryHandleMap.remove(str);
    }

    public void destroy() {
        for (String str : this.mQueryHandleMap.keySet()) {
            AsyncQueryHandle asyncQueryHandle = this.mQueryHandleMap.get(str);
            if (asyncQueryHandle != null) {
                asyncQueryHandle.destroy();
            }
        }
        this.mQueryHandleMap.clear();
    }

    public boolean doesHandleExist(String str) {
        return this.mQueryHandleMap.containsKey(str);
    }

    public void registerQueryHandle(String str, Function<String, AsyncQueryHandle> function) {
        registerQueryHandle(str, function, false);
    }

    public void registerQueryHandle(String str, Function<String, AsyncQueryHandle> function, boolean z) {
        if (!z || !this.mQueryHandleMap.containsKey(str)) {
            AsyncQueryHandle asyncQueryHandle = this.mQueryHandleMap.get(str);
            if (asyncQueryHandle != null) {
                asyncQueryHandle.destroy();
            }
            this.mQueryHandleMap.put(str, function.apply(str));
        }
    }
}
