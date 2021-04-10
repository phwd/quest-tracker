package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.oculus.notifications.NotificationDataSetListener;
import com.oculus.notifications.NotificationDataSetService;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.vrshell.notifications.NotificationsType;
import com.oculus.vrshell.util.NotificationsActionsUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NotificationManagerSourceHandler extends ListenerBasedNotificationSourceHandler<NativeNotification> implements INotificationSourceHandler<NativeNotification>, NotificationDataSetListener {
    private static final int MAX_NOTIFS = 48;
    private static String TAG = "NotificationManagerSourceHandler";
    private List<NativeNotification> mNativeNotifs = new ArrayList();
    private NotificationDataSetService mService = NotificationDataSetService.getInstance();

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.ListenerBasedNotificationSourceHandler
    public /* bridge */ /* synthetic */ void acceptListener(INotificationProxyListener iNotificationProxyListener) {
        super.acceptListener(iNotificationProxyListener);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.ListenerBasedNotificationSourceHandler, com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationSourceHandler
    public /* bridge */ /* synthetic */ boolean acceptsListeners() {
        return super.acceptsListeners();
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.ListenerBasedNotificationSourceHandler
    public /* bridge */ /* synthetic */ void removeListener(INotificationProxyListener iNotificationProxyListener) {
        super.removeListener(iNotificationProxyListener);
    }

    public NotificationManagerSourceHandler(Context context) {
        super(context);
        this.mService.connectToProvider(this.mContext);
        this.mService.registerDataSetListener(this);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationSourceHandler
    public List<NativeNotification> initialFetch() {
        for (StatusBarNotification statusBarNotification : this.mService.getAllPersistentNotificationsExcept(NotificationsType.DOWNLOAD_IN_PROGRESS)) {
            NativeNotification nativeNotification = new NativeNotification(statusBarNotification);
            nativeNotification.setSeenState(AbstractVRNotification.NotificationSeenState.SEEN_BUT_UNREAD);
            this.mNativeNotifs.add(nativeNotification);
        }
        Log.v(TAG, String.format("Notifs retrieved from NotificationDataset %s", this.mNativeNotifs));
        return this.mNativeNotifs;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.ListenerBasedNotificationSourceHandler
    public Class<NativeNotification> handlesType() {
        return NativeNotification.class;
    }

    @Override // com.oculus.notifications.NotificationDataSetListener
    public void onDataSetChanged() {
        Log.i(TAG, "Dataset has changed");
        HashMap hashMap = new HashMap();
        this.mNativeNotifs.forEach(new Consumer(hashMap) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationManagerSourceHandler$w1VzSRJKB69Vpy7a5IM9wVkL6Us */
            private final /* synthetic */ Map f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NotificationManagerSourceHandler.lambda$onDataSetChanged$210(this.f$0, (NativeNotification) obj);
            }
        });
        HashMap hashMap2 = new HashMap();
        toNativeNotifs(this.mService.getAllPersistentNotifications()).forEach(new Consumer(hashMap2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationManagerSourceHandler$INFMHAy7nydXygTcAUdu1PQKwmQ */
            private final /* synthetic */ Map f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NotificationManagerSourceHandler.lambda$onDataSetChanged$211(this.f$0, (NativeNotification) obj);
            }
        });
        List list = (List) hashMap2.values().stream().filter(new Predicate(hashMap) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationManagerSourceHandler$3UJndRkTPGof_fyVEQ1eCWTD80 */
            private final /* synthetic */ Map f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return NotificationManagerSourceHandler.lambda$onDataSetChanged$212(this.f$0, (NativeNotification) obj);
            }
        }).collect(Collectors.toList());
        List list2 = (List) hashMap.values().stream().filter(new Predicate(hashMap2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationManagerSourceHandler$TvFuCLQkS1v4Cp6EKBd6gdNTPwo */
            private final /* synthetic */ Map f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return NotificationManagerSourceHandler.lambda$onDataSetChanged$213(this.f$0, (NativeNotification) obj);
            }
        }).collect(Collectors.toList());
        List list3 = (List) hashMap2.values().stream().filter(new Predicate(hashMap) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationManagerSourceHandler$0WdLKHIHuQxoK9CPAA0FkDBg0s */
            private final /* synthetic */ Map f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return NotificationManagerSourceHandler.this.lambda$onDataSetChanged$214$NotificationManagerSourceHandler(this.f$1, (NativeNotification) obj);
            }
        }).collect(Collectors.toList());
        if (!list3.isEmpty()) {
            list3.forEach(new Consumer(hashMap) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationManagerSourceHandler$xhQCFSkZQ8G8fQmPr_eJS85xsoA */
                private final /* synthetic */ Map f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    NotificationManagerSourceHandler.this.lambda$onDataSetChanged$215$NotificationManagerSourceHandler(this.f$1, (NativeNotification) obj);
                }
            });
        }
        if (!list2.isEmpty()) {
            Log.v(TAG, "Notifications removed from manager set");
            this.mNativeNotifs.removeAll(list2);
            list2.forEach(new Consumer() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$CQ0C9d87N7F1tNYX6xXdjYKoa0w */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    NotificationManagerSourceHandler.this.notifyRemoved((NativeNotification) obj);
                }
            });
        }
        if (!list.isEmpty()) {
            this.mNativeNotifs.addAll(list);
            Log.d(TAG, String.format("Notifications added to manager set. Current count is %s", Integer.valueOf(this.mNativeNotifs.size())));
            list.forEach(new Consumer() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$VYH4FyaohEVoG3sbScwSpn1w5Z8 */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    NotificationManagerSourceHandler.this.notifyAdded((NativeNotification) obj);
                }
            });
            handleMaxLimitReached(this.mNativeNotifs.size());
        }
    }

    static /* synthetic */ void lambda$onDataSetChanged$210(Map map, NativeNotification nativeNotification) {
        NativeNotification nativeNotification2 = (NativeNotification) map.put(nativeNotification.getRaw().getKey(), nativeNotification);
    }

    static /* synthetic */ void lambda$onDataSetChanged$211(Map map, NativeNotification nativeNotification) {
        NativeNotification nativeNotification2 = (NativeNotification) map.put(nativeNotification.getRaw().getKey(), nativeNotification);
    }

    static /* synthetic */ boolean lambda$onDataSetChanged$212(Map map, NativeNotification nativeNotification) {
        return !map.containsKey(nativeNotification.getRaw().getKey());
    }

    static /* synthetic */ boolean lambda$onDataSetChanged$213(Map map, NativeNotification nativeNotification) {
        return !map.containsKey(nativeNotification.getRaw().getKey());
    }

    public /* synthetic */ boolean lambda$onDataSetChanged$214$NotificationManagerSourceHandler(Map map, NativeNotification nativeNotification) {
        return map.containsKey(nativeNotification.getRaw().getKey()) && compareUpdatedNotifs((NativeNotification) map.get(nativeNotification.getRaw().getKey()), nativeNotification);
    }

    public /* synthetic */ void lambda$onDataSetChanged$215$NotificationManagerSourceHandler(Map map, NativeNotification nativeNotification) {
        this.mNativeNotifs.remove(map.get(nativeNotification.getRaw().getKey()));
        this.mNativeNotifs.add(nativeNotification);
        Log.v(TAG, String.format("Updating a notification %s", nativeNotification));
        notifyUpdated(nativeNotification);
    }

    private boolean compareUpdatedNotifs(NativeNotification nativeNotification, NativeNotification nativeNotification2) {
        if (nativeNotification.isDownloadNotification() || nativeNotification.isDownloadInProgressNotification() || NativeNotification.isFBUploadInProgressNotification(nativeNotification)) {
            return NotificationsActionsUtil.getNotificationProgress(nativeNotification2.getRaw().getNotification()) != NotificationsActionsUtil.getNotificationProgress(nativeNotification.getRaw().getNotification()) || !nativeNotification2.getTitle().equals(nativeNotification.getTitle()) || !nativeNotification2.getLongText().equals(nativeNotification.getLongText());
        }
        return nativeNotification.contentsAreUpdated(nativeNotification2);
    }

    public void publishRemoval(NativeNotification nativeNotification) {
        Log.v(TAG, String.format("Publishing removal to NotificationDatasetService %s", nativeNotification));
        this.mService.removeNotification(nativeNotification.getRaw().getKey());
        this.mListeners.forEach(new Consumer() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationManagerSourceHandler$aKGUtCe76xk4rTEtzUa8sfKGqTQ */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NativeNotification nativeNotification;
                ((INotificationProxyListener) obj).onNotificationRemoved(nativeNotification.getClass(), NativeNotification.this);
            }
        });
    }

    private void handleMaxLimitReached(int i) {
        if (i >= 48) {
            this.mNativeNotifs.sort($$Lambda$NotificationManagerSourceHandler$4Lk9xmGS0PiW_OzdU1ZkUFSTg0.INSTANCE);
            while (i > 48) {
                String str = TAG;
                List<NativeNotification> list = this.mNativeNotifs;
                Log.v(str, String.format("Notification maximum limit reached. Removing %s", list.get(list.size() - 1)));
                List<NativeNotification> list2 = this.mNativeNotifs;
                publishRemoval(list2.get(list2.size() - 1));
                i--;
            }
        }
    }

    private static List<NativeNotification> toNativeNotifs(List<StatusBarNotification> list) {
        ArrayList arrayList = new ArrayList();
        for (StatusBarNotification statusBarNotification : list) {
            arrayList.add(new NativeNotification(statusBarNotification));
        }
        return arrayList;
    }
}
