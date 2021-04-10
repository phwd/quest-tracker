package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import android.content.Context;
import android.util.Log;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/* access modifiers changed from: package-private */
public abstract class ListenerBasedNotificationSourceHandler<T extends IBaseVRNotification> implements INotificationSourceHandler<T> {
    private static final String TAG = "ListenerBasedNotificationSourceHandler";
    protected Context mContext;
    protected Set<INotificationProxyListener> mListeners = new HashSet();

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationSourceHandler
    public boolean acceptsListeners() {
        return true;
    }

    public abstract Class<T> handlesType();

    public abstract void publishRemoval(T t);

    public ListenerBasedNotificationSourceHandler(Context context) {
        this.mContext = context;
    }

    public void acceptListener(INotificationProxyListener iNotificationProxyListener) {
        this.mListeners.add(iNotificationProxyListener);
    }

    public void removeListener(INotificationProxyListener iNotificationProxyListener) {
        if (iNotificationProxyListener != null) {
            try {
                this.mListeners.remove(iNotificationProxyListener);
            } catch (Exception e) {
                Log.e(TAG, "Could not remove listener", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyAdded(IBaseVRNotification iBaseVRNotification) {
        this.mListeners.forEach(new Consumer() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$ListenerBasedNotificationSourceHandler$t1F1LKonhKMDKGtLrTwedLQzFQU */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ListenerBasedNotificationSourceHandler.lambda$notifyAdded$207(IBaseVRNotification.this, (INotificationProxyListener) obj);
            }
        });
    }

    /* access modifiers changed from: protected */
    public final void notifyRemoved(IBaseVRNotification iBaseVRNotification) {
        this.mListeners.forEach(new Consumer() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$ListenerBasedNotificationSourceHandler$IQpokluSD6dV_4wzE1yaVz8IBFM */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ListenerBasedNotificationSourceHandler.lambda$notifyRemoved$208(IBaseVRNotification.this, (INotificationProxyListener) obj);
            }
        });
    }

    /* access modifiers changed from: protected */
    public final void notifyUpdated(IBaseVRNotification iBaseVRNotification) {
        this.mListeners.forEach(new Consumer() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$ListenerBasedNotificationSourceHandler$QUQFyvusb5iKWFpmmIFRSmrZ_eg */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ListenerBasedNotificationSourceHandler.lambda$notifyUpdated$209(IBaseVRNotification.this, (INotificationProxyListener) obj);
            }
        });
    }
}
