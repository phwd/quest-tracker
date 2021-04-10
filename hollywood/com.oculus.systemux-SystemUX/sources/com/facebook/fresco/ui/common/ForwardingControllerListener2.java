package com.facebook.fresco.ui.common;

import android.util.Log;
import com.facebook.fresco.ui.common.ControllerListener2;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class ForwardingControllerListener2<I> extends BaseControllerListener2<I> {
    private static final String TAG = "FwdControllerListener2";
    private final List<ControllerListener2<I>> mListeners = new ArrayList(2);

    public synchronized void addListener(ControllerListener2<I> controllerListener2) {
        this.mListeners.add(controllerListener2);
    }

    public synchronized void removeListener(ControllerListener2<I> controllerListener2) {
        int indexOf = this.mListeners.indexOf(controllerListener2);
        if (indexOf != -1) {
            this.mListeners.set(indexOf, null);
        }
    }

    public synchronized void removeAllListeners() {
        this.mListeners.clear();
    }

    private synchronized void onException(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    @Override // com.facebook.fresco.ui.common.ControllerListener2, com.facebook.fresco.ui.common.BaseControllerListener2
    public void onSubmit(String str, Object obj, ControllerListener2.Extras extras) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener2<I> controllerListener2 = this.mListeners.get(i);
                if (controllerListener2 != null) {
                    controllerListener2.onSubmit(str, obj, extras);
                }
            } catch (Exception e) {
                onException("ForwardingControllerListener2 exception in onSubmit", e);
            }
        }
    }

    @Override // com.facebook.fresco.ui.common.ControllerListener2, com.facebook.fresco.ui.common.BaseControllerListener2
    public void onFinalImageSet(String str, @Nullable I i, ControllerListener2.Extras extras) {
        int size = this.mListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ControllerListener2<I> controllerListener2 = this.mListeners.get(i2);
                if (controllerListener2 != null) {
                    controllerListener2.onFinalImageSet(str, i, extras);
                }
            } catch (Exception e) {
                onException("ForwardingControllerListener2 exception in onFinalImageSet", e);
            }
        }
    }

    @Override // com.facebook.fresco.ui.common.ControllerListener2, com.facebook.fresco.ui.common.BaseControllerListener2
    public void onFailure(String str, Throwable th, ControllerListener2.Extras extras) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener2<I> controllerListener2 = this.mListeners.get(i);
                if (controllerListener2 != null) {
                    controllerListener2.onFailure(str, th, extras);
                }
            } catch (Exception e) {
                onException("ForwardingControllerListener2 exception in onFailure", e);
            }
        }
    }

    @Override // com.facebook.fresco.ui.common.ControllerListener2, com.facebook.fresco.ui.common.BaseControllerListener2
    public void onRelease(String str, ControllerListener2.Extras extras) {
        int size = this.mListeners.size();
        for (int i = 0; i < size; i++) {
            try {
                ControllerListener2<I> controllerListener2 = this.mListeners.get(i);
                if (controllerListener2 != null) {
                    controllerListener2.onRelease(str, extras);
                }
            } catch (Exception e) {
                onException("ForwardingControllerListener2 exception in onRelease", e);
            }
        }
    }
}
