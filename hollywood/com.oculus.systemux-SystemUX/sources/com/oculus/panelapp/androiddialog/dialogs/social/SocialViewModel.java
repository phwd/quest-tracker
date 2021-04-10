package com.oculus.panelapp.androiddialog.dialogs.social;

import androidx.databinding.BaseObservable;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class SocialViewModel extends BaseObservable {
    private final Map<String, AsyncQueryHandle> mQueryHandleMap = new HashMap();

    /* access modifiers changed from: protected */
    public void registerQueryHandle(String str, Function<String, AsyncQueryHandle> function) {
        registerQueryHandle(str, function, false);
    }

    /* access modifiers changed from: protected */
    public void registerQueryHandle(String str, Function<String, AsyncQueryHandle> function, boolean z) {
        if (!z || !doesHandleExist(str)) {
            AsyncQueryHandle asyncQueryHandle = this.mQueryHandleMap.get(str);
            if (asyncQueryHandle != null) {
                asyncQueryHandle.destroy();
            }
            this.mQueryHandleMap.put(str, function.apply(str));
        }
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

    /* access modifiers changed from: protected */
    public void clearHandle(String str) {
        this.mQueryHandleMap.remove(str);
    }

    /* access modifiers changed from: protected */
    public boolean doesHandleExist(String str) {
        return this.mQueryHandleMap.containsKey(str);
    }
}
