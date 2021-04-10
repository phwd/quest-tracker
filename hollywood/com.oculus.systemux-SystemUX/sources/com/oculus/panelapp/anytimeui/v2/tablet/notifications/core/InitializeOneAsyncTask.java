package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.oculus.vrshell.notifications.NotificationsType;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InitializeOneAsyncTask extends AsyncTask<INotificationSourceHandler, IOnNotificationsInitialized, List<? extends IBaseVRNotification>> {
    private static final String TAG;
    private SoftReference<Context> mContext;
    private IOnNotificationsInitialized mInitializedListener;
    private SoftReference<NotificationDatasetProxy> mProxy;

    public InitializeOneAsyncTask(IOnNotificationsInitialized iOnNotificationsInitialized, NotificationDatasetProxy notificationDatasetProxy, Context context) {
        this.mInitializedListener = iOnNotificationsInitialized;
        this.mProxy = new SoftReference<>(notificationDatasetProxy);
        this.mContext = new SoftReference<>(context.getApplicationContext());
    }

    public List<? extends IBaseVRNotification> doInBackground(INotificationSourceHandler... iNotificationSourceHandlerArr) {
        ArrayList arrayList = new ArrayList();
        for (INotificationSourceHandler iNotificationSourceHandler : iNotificationSourceHandlerArr) {
            if (this.mContext.get() != null) {
                arrayList.addAll(performBackgroundFetch(iNotificationSourceHandler, this.mContext.get()));
            }
        }
        return arrayList;
    }

    public void onPostExecute(List<? extends IBaseVRNotification> list) {
        NotificationDatasetProxy notificationDatasetProxy = this.mProxy.get();
        if (notificationDatasetProxy != null) {
            list.forEach(new Consumer() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$InitializeOneAsyncTask$WTALhd9fDR_xicWafz0EJmv82w8 */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    InitializeOneAsyncTask.lambda$onPostExecute$205(NotificationDatasetProxy.this, (IBaseVRNotification) obj);
                }
            });
            notificationDatasetProxy.setIsInitialized();
            Log.v(TAG, String.format("Initialization finished with %s notifs", notificationDatasetProxy.getNotificationsByCategory(NotificationsType.ALL)));
            IOnNotificationsInitialized iOnNotificationsInitialized = this.mInitializedListener;
            if (iOnNotificationsInitialized != null) {
                iOnNotificationsInitialized.onNotificationsInitialized();
            }
            notificationDatasetProxy.executeRefetchAtFrequency();
        }
    }

    public static List<IBaseVRNotification> performBackgroundFetch(INotificationSourceHandler iNotificationSourceHandler, Context context) {
        List<IBaseVRNotification> initialFetch = iNotificationSourceHandler.initialFetch();
        initialFetch.forEach(new Consumer(context) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$InitializeOneAsyncTask$uE76k889LO7OKgBafBjRDFD2IoI */
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                InitializeOneAsyncTask.lambda$performBackgroundFetch$206(this.f$0, (IBaseVRNotification) obj);
            }
        });
        Log.v(TAG, "Retrieving notif icons complete");
        return initialFetch;
    }
}
