package com.oculus.appmanager.eventbus;

import com.oculus.util.thread.ThreadUtils;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public abstract class BaseEventBus {
    private Bus mNonUIBus = new Bus(ThreadEnforcer.ANY);
    private Bus mUIBus = new Bus();

    protected BaseEventBus() {
    }

    public void register(Object obj) {
        if (ThreadUtils.isUiThread()) {
            this.mUIBus.register(obj);
        } else {
            this.mNonUIBus.register(obj);
        }
    }

    public void unregister(Object obj) {
        if (ThreadUtils.isUiThread()) {
            this.mUIBus.unregister(obj);
        } else {
            this.mNonUIBus.unregister(obj);
        }
    }

    public void post(Object obj) {
        postOnUIBus(obj);
        this.mNonUIBus.post(obj);
    }

    private void postOnUIBus(final Object obj) {
        ThreadUtils.runOnUiThread(new Runnable() {
            /* class com.oculus.appmanager.eventbus.BaseEventBus.AnonymousClass1 */

            public void run() {
                BaseEventBus.this.mUIBus.post(obj);
            }
        });
    }
}
