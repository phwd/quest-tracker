package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import com.oculus.vrshell.notifications.NotificationsType;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class InitializeAsyncTask extends AsyncTask<INotificationSourceHandler, IOnNotificationsInitialized, List<? extends IBaseVRNotification>> {
    private static final String TAG = "InitializeAsyncTask";
    private SoftReference<Context> mContext;
    private IOnNotificationsInitialized mInitializedListener;
    private SoftReference<NotificationDatasetProxy> mProxy;

    public InitializeAsyncTask(IOnNotificationsInitialized iOnNotificationsInitialized, NotificationDatasetProxy notificationDatasetProxy, Context context) {
        this.mInitializedListener = iOnNotificationsInitialized;
        this.mProxy = new SoftReference<>(notificationDatasetProxy);
        this.mContext = new SoftReference<>(context.getApplicationContext());
    }

    /* access modifiers changed from: protected */
    public List<? extends IBaseVRNotification> doInBackground(INotificationSourceHandler... iNotificationSourceHandlerArr) {
        ArrayList arrayList = new ArrayList();
        for (INotificationSourceHandler iNotificationSourceHandler : iNotificationSourceHandlerArr) {
            if (this.mContext.get() != null) {
                arrayList.addAll(InitializeOneAsyncTask.performBackgroundFetch(iNotificationSourceHandler, this.mContext.get()));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(List<? extends IBaseVRNotification> list) {
        NotificationDatasetProxy notificationDatasetProxy = this.mProxy.get();
        if (notificationDatasetProxy != null) {
            list.forEach(new Consumer() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$InitializeAsyncTask$fjcBWtlRKdecYUOa4dFfwX4h0zw */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    InitializeAsyncTask.lambda$onPostExecute$204(NotificationDatasetProxy.this, (IBaseVRNotification) obj);
                }
            });
            notificationDatasetProxy.setIsInitialized();
            Log.v(TAG, String.format("Initialization finished with %s notifs", notificationDatasetProxy.getNotificationsByCategory(NotificationsType.ALL)));
            IOnNotificationsInitialized iOnNotificationsInitialized = this.mInitializedListener;
            if (iOnNotificationsInitialized != null) {
                iOnNotificationsInitialized.onNotificationsInitialized();
            }
            ConcurrentLinkedQueue<Pair<INotificationSourceHandler, IOnNotificationsInitialized>> uninitializedQueue = notificationDatasetProxy.getUninitializedQueue();
            if (!uninitializedQueue.isEmpty()) {
                while (!uninitializedQueue.isEmpty()) {
                    Pair<INotificationSourceHandler, IOnNotificationsInitialized> poll = uninitializedQueue.poll();
                    notificationDatasetProxy.initializeOne((INotificationSourceHandler) poll.first, (IOnNotificationsInitialized) poll.second);
                }
            }
        }
    }
}
