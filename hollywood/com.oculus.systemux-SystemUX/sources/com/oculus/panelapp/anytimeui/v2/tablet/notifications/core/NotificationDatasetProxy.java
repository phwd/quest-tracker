package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.notifications.R;
import com.oculus.notifications.NotificationConstants;
import com.oculus.os.ActivityManagerUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.NotificationDatasetProxy;
import com.oculus.vrshell.notifications.NotificationsType;
import com.oculus.vrshell.util.PackageUtil;
import com.oculus.vrshell.util.ThreadExecutor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NotificationDatasetProxy implements INotificationProxyListener {
    private static long FEED_REFRESH_CAP = 10000;
    private static String TAG = "NotificationDatasetProxy";
    private static volatile boolean mIsInitialized = false;
    private static volatile boolean mIsInitializing = false;
    private AtomicInteger localNotificationIds;
    private boolean mAllowBackgroundRefresh;
    private Context mContext;
    private Set<INotificationSourceHandler> mHandlers;
    private boolean mIsFeedRefreshing;
    private long mLastFeedRefresh;
    private ConcurrentHashMap<String, Pair<INotificationProxyListener, List<String>>> mNotificationDataSetListeners;
    private ConcurrentHashMap<NotificationsType, List<IBaseVRNotification>> mNotifsByType;
    private HashSet<String> mRemovedFBIDs;
    private ConcurrentLinkedQueue<Pair<INotificationSourceHandler, IOnNotificationsInitialized>> mUninitializedQueue;

    private NotificationDatasetProxy(Context context, @Nullable IOnNotificationsInitialized iOnNotificationsInitialized, @Nullable INotificationSourceHandler... iNotificationSourceHandlerArr) {
        this.localNotificationIds = new AtomicInteger(50000);
        this.mHandlers = new HashSet();
        this.mUninitializedQueue = new ConcurrentLinkedQueue<>();
        this.mNotificationDataSetListeners = new ConcurrentHashMap<>();
        this.mNotifsByType = new ConcurrentHashMap<>();
        this.mRemovedFBIDs = new HashSet<>();
        this.mAllowBackgroundRefresh = false;
        this.mIsFeedRefreshing = false;
        this.mContext = context.getApplicationContext();
        if (iNotificationSourceHandlerArr != null) {
            for (INotificationSourceHandler iNotificationSourceHandler : iNotificationSourceHandlerArr) {
                if (iNotificationSourceHandler != null && !this.mHandlers.contains(iNotificationSourceHandler)) {
                    this.mHandlers.add(iNotificationSourceHandler);
                }
            }
            if (this.mNotifsByType.isEmpty()) {
                for (NotificationsType notificationsType : NotificationsType.values()) {
                    this.mNotifsByType.put(notificationsType, new ArrayList());
                }
            }
            initialize(iOnNotificationsInitialized);
            return;
        }
        throw new Error("Attempting to initialize the notification proxy without any NotificaitonHandlers is not allowed");
    }

    public void setAllowBackgroundRefresh(boolean z) {
        this.mAllowBackgroundRefresh = z;
    }

    public void setIsInitialized() {
        mIsInitialized = true;
        mIsInitializing = false;
    }

    public ConcurrentLinkedQueue<Pair<INotificationSourceHandler, IOnNotificationsInitialized>> getUninitializedQueue() {
        return this.mUninitializedQueue;
    }

    public List<INotificationSourceHandler> getHandlers() {
        return new ArrayList(this.mHandlers);
    }

    public void addHandler(INotificationSourceHandler iNotificationSourceHandler, IOnNotificationsInitialized iOnNotificationsInitialized) {
        Set<INotificationSourceHandler> set = this.mHandlers;
        if (set != null) {
            if (set.contains(iNotificationSourceHandler)) {
                Log.e(TAG, "Trying to add an INotificationSourceHandler that already exists is a no-op");
                return;
            }
            this.mHandlers.add(iNotificationSourceHandler);
            if (iNotificationSourceHandler instanceof ListenerBasedNotificationSourceHandler) {
                ((ListenerBasedNotificationSourceHandler) iNotificationSourceHandler).acceptListener(this);
            }
            initializeOne(iNotificationSourceHandler, iOnNotificationsInitialized);
        }
    }

    public ConcurrentHashMap<NotificationsType, List<IBaseVRNotification>> listen(INotificationProxyListener iNotificationProxyListener, List<String> list) {
        addListener(iNotificationProxyListener, list);
        ConcurrentHashMap<NotificationsType, List<IBaseVRNotification>> concurrentHashMap = new ConcurrentHashMap<>(this.mNotifsByType);
        NotificationsType[] values = NotificationsType.values();
        for (NotificationsType notificationsType : values) {
            if (concurrentHashMap.containsKey(notificationsType)) {
                concurrentHashMap.compute(notificationsType, new BiFunction(list) {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$o65TC79PTdo9nuJk4OAE0gnpU */
                    private final /* synthetic */ List f$0;

                    {
                        this.f$0 = r1;
                    }

                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return NotificationDatasetProxy.lambda$listen$28(this.f$0, (NotificationsType) obj, (List) obj2);
                    }
                });
            }
        }
        return concurrentHashMap;
    }

    static /* synthetic */ List lambda$listen$28(List list, NotificationsType notificationsType, List list2) {
        return (List) list2.stream().filter(new Predicate(list) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$8WC_Hwmelb929HqiWZ7eZUWQxIE */
            private final /* synthetic */ List f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return NotificationDatasetProxy.lambda$null$26(this.f$0, (IBaseVRNotification) obj);
            }
        }).sorted($$Lambda$NotificationDatasetProxy$YrAasvKQmWJeGqb0nllfSqnTE6o.INSTANCE).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$null$26(List list, IBaseVRNotification iBaseVRNotification) {
        return !list.contains(iBaseVRNotification.getNotificationType());
    }

    static /* synthetic */ int lambda$null$27(IBaseVRNotification iBaseVRNotification, IBaseVRNotification iBaseVRNotification2) {
        return iBaseVRNotification2.getPostedTimeSeconds() > iBaseVRNotification.getPostedTimeSeconds() ? 1 : -1;
    }

    public void removeListener(INotificationProxyListener iNotificationProxyListener) {
        if (iNotificationProxyListener != null) {
            try {
                Log.v(TAG, String.format("Removing listener %s", iNotificationProxyListener));
                this.mNotificationDataSetListeners.remove(iNotificationProxyListener.getClass().getSimpleName());
            } catch (Exception e) {
                Log.e(TAG, "Could not remove listener", e);
            }
        }
    }

    public void publishRemoval(IBaseVRNotification iBaseVRNotification) {
        Log.v(TAG, String.format("Publishing removal of a notif %s", iBaseVRNotification));
        this.mHandlers.forEach(new Consumer() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$4TtGRBzRHxboGK5xWNuFfqbHlg */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NotificationDatasetProxy.lambda$publishRemoval$29(IBaseVRNotification.this, (INotificationSourceHandler) obj);
            }
        });
    }

    static /* synthetic */ void lambda$publishRemoval$29(IBaseVRNotification iBaseVRNotification, INotificationSourceHandler iNotificationSourceHandler) {
        if (iNotificationSourceHandler instanceof ListenerBasedNotificationSourceHandler) {
            ListenerBasedNotificationSourceHandler listenerBasedNotificationSourceHandler = (ListenerBasedNotificationSourceHandler) iNotificationSourceHandler;
            if (listenerBasedNotificationSourceHandler.handlesType() == iBaseVRNotification.getClass()) {
                listenerBasedNotificationSourceHandler.publishRemoval(iBaseVRNotification);
            }
        }
    }

    public synchronized void refetchPersistedNotifs() {
        if (shouldExecuteRefresh()) {
            setFeedRefreshingState(true);
            Log.v(TAG, "Starting the background refetch of notifications");
            for (INotificationSourceHandler iNotificationSourceHandler : this.mHandlers) {
                if (iNotificationSourceHandler instanceof PersistedNotificationSourceHandler) {
                    ((PersistedNotificationSourceHandler) iNotificationSourceHandler).refetchNotifs();
                }
            }
            setFeedRefreshingState(false);
            this.mLastFeedRefresh = System.currentTimeMillis();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized boolean shouldExecuteRefresh() {
        for (INotificationSourceHandler iNotificationSourceHandler : this.mHandlers) {
            if ((iNotificationSourceHandler instanceof PersistedNotificationSourceHandler) && !((PersistedNotificationSourceHandler) iNotificationSourceHandler).getInitializationState()) {
                Log.d(TAG, "Background fetch stopped early. Reason: Handler not initialized");
                return false;
            }
        }
        long currentTimeMillis = System.currentTimeMillis() - this.mLastFeedRefresh;
        if (this.mLastFeedRefresh >= 0 && currentTimeMillis <= FEED_REFRESH_CAP) {
            Log.d(TAG, String.format("Background fetch stopped early. Reason: Feed refresh occurred %s ms ago, which is less than %s ms", Long.valueOf(currentTimeMillis), Long.valueOf(FEED_REFRESH_CAP)));
            return false;
        } else if (isFeedRefreshing()) {
            Log.d(TAG, "Background fetch stopped early. Reason: Feed is already refreshing");
            return false;
        } else if (!((PowerManager) this.mContext.getSystemService("power")).isInteractive()) {
            return false;
        } else {
            if (!isWifiEnabled()) {
                Log.d(TAG, "Background fetch stopped early. Reason: No wifi");
                return false;
            }
            Log.d(TAG, "Background fetch allowed. Continuing");
            return true;
        }
    }

    private boolean isWifiEnabled() {
        return ((WifiManager) this.mContext.getSystemService("wifi")).isWifiEnabled() || ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetwork() != null;
    }

    private boolean notifExistsByFbid(String str) {
        return this.mNotifsByType.get(NotificationsType.ALL).stream().anyMatch(new Predicate(str) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$7lIvsduZSIsdHOaCQtpjx0DYDD8 */
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.equals(((IBaseVRNotification) obj).getFBID());
            }
        });
    }

    public void executeRefetchAtFrequency() {
        if (this.mAllowBackgroundRefresh) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            intentFilter.addAction("com.oculus.intent.action.MOUNT_STATE_CHANGED");
            final ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            this.mContext.registerReceiver(new BroadcastReceiver("com.oculus.intent.action.MOUNT_STATE_CHANGED") {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.NotificationDatasetProxy.AnonymousClass1 */

                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals("android.intent.action.SCREEN_ON") && NotificationDatasetProxy.this.shouldExecuteRefresh()) {
                        Log.v(NotificationDatasetProxy.TAG, "Screen on, notifications refetch started");
                        newSingleThreadScheduledExecutor.execute(new Runnable() {
                            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$1$hiKQgGdnu604IQBPqjZ9xvoMk5U */

                            public final void run() {
                                NotificationDatasetProxy.AnonymousClass1.this.lambda$onReceive$31$NotificationDatasetProxy$1();
                            }
                        });
                    }
                    if (intent.getAction().equals("android.net.wifi.STATE_CHANGE") && NotificationDatasetProxy.this.shouldExecuteRefresh()) {
                        Log.v(NotificationDatasetProxy.TAG, "Wifi on, notifications refetch started");
                        newSingleThreadScheduledExecutor.execute(new Runnable() {
                            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$1$DLdzn5muYSIPOqmH31VF_AqYLVY */

                            public final void run() {
                                NotificationDatasetProxy.AnonymousClass1.this.lambda$onReceive$32$NotificationDatasetProxy$1();
                            }
                        });
                    }
                    if (intent.getAction().equals("com.oculus.intent.action.MOUNT_STATE_CHANGED") && NotificationDatasetProxy.this.shouldExecuteRefresh()) {
                        Log.v(NotificationDatasetProxy.TAG, "Headset mounted, notifications refetch started");
                        newSingleThreadScheduledExecutor.execute(new Runnable() {
                            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$1$4OnLQK7AAxQhbqEKbYlPLSvoMaQ */

                            public final void run() {
                                NotificationDatasetProxy.AnonymousClass1.this.lambda$onReceive$33$NotificationDatasetProxy$1();
                            }
                        });
                    }
                }

                public /* synthetic */ void lambda$onReceive$31$NotificationDatasetProxy$1() {
                    NotificationDatasetProxy.this.refetchPersistedNotifs();
                }

                public /* synthetic */ void lambda$onReceive$32$NotificationDatasetProxy$1() {
                    NotificationDatasetProxy.this.refetchPersistedNotifs();
                }

                public /* synthetic */ void lambda$onReceive$33$NotificationDatasetProxy$1() {
                    NotificationDatasetProxy.this.refetchPersistedNotifs();
                }
            }, intentFilter);
            scheduleFetch(newSingleThreadScheduledExecutor, fetchDeviceConfigFeedRefresh());
        }
    }

    public void scheduleFetch(ScheduledExecutorService scheduledExecutorService, long j) {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$4c7ilBJ2hGYeZOYVvVjlwY9u8 */

            public final void run() {
                NotificationDatasetProxy.this.refetchPersistedNotifs();
            }
        }, j, j, TimeUnit.SECONDS);
    }

    private long fetchDeviceConfigFeedRefresh() {
        long j = 180;
        try {
            j = DeviceConfigHelper.getLong(this.mContext, Gatekeeper.NOTIFICATIONS_FEED_REFRESH_DELAY);
            Log.v(TAG, String.format("Fetched refresh time is %s", Long.valueOf(j)));
            return j;
        } catch (Exception e) {
            Log.e(TAG, "Could not fetch default refresh rate from device config", e);
            return j;
        }
    }

    private synchronized void setFeedRefreshingState(boolean z) {
        this.mIsFeedRefreshing = z;
    }

    private synchronized boolean isFeedRefreshing() {
        return this.mIsFeedRefreshing;
    }

    private void addListener(INotificationProxyListener iNotificationProxyListener, @Nullable List<String> list) {
        this.mNotificationDataSetListeners.put(iNotificationProxyListener.getClass().getSimpleName(), new Pair<>(iNotificationProxyListener, list));
    }

    /* access modifiers changed from: package-private */
    public void initialize(IOnNotificationsInitialized iOnNotificationsInitialized) {
        Log.v(TAG, "Initializing the source handlers");
        if (!mIsInitializing && !mIsInitialized) {
            mIsInitializing = true;
            this.mHandlers.forEach(new Consumer() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$HpbTr818SN6Gi7uflDuYEAr172w */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    NotificationDatasetProxy.this.lambda$initialize$34$NotificationDatasetProxy((INotificationSourceHandler) obj);
                }
            });
            Log.v(TAG, "Running the initial fetches");
            this.mNotifsByType.forEach($$Lambda$NotificationDatasetProxy$rHEvF1rL9bm0aIqkJLO4K54Mg6Q.INSTANCE);
            INotificationSourceHandler[] iNotificationSourceHandlerArr = new INotificationSourceHandler[this.mHandlers.size()];
            new ArrayList(this.mHandlers).toArray(iNotificationSourceHandlerArr);
            new InitializeAsyncTask(iOnNotificationsInitialized, this, this.mContext).execute(iNotificationSourceHandlerArr);
        }
    }

    public /* synthetic */ void lambda$initialize$34$NotificationDatasetProxy(INotificationSourceHandler iNotificationSourceHandler) {
        if (iNotificationSourceHandler instanceof ListenerBasedNotificationSourceHandler) {
            ((ListenerBasedNotificationSourceHandler) iNotificationSourceHandler).acceptListener(this);
        }
    }

    public void initializeOne(INotificationSourceHandler iNotificationSourceHandler, IOnNotificationsInitialized iOnNotificationsInitialized) {
        if (mIsInitializing) {
            this.mUninitializedQueue.add(new Pair<>(iNotificationSourceHandler, iOnNotificationsInitialized));
        } else if (!mIsInitialized) {
            Log.e(TAG, "Cannot add a single INotificationSourceHandler if intialization hasnot already occurred");
        } else {
            new InitializeOneAsyncTask(iOnNotificationsInitialized, this, this.mContext).execute(iNotificationSourceHandler);
        }
    }

    private void destroy() {
        this.mHandlers.forEach(new Consumer() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$ZLmplbZRZbLbVtDjqLaikv720r4 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NotificationDatasetProxy.this.lambda$destroy$36$NotificationDatasetProxy((INotificationSourceHandler) obj);
            }
        });
        Log.v(TAG, "All notification handlers removed");
    }

    public /* synthetic */ void lambda$destroy$36$NotificationDatasetProxy(INotificationSourceHandler iNotificationSourceHandler) {
        if (iNotificationSourceHandler instanceof ListenerBasedNotificationSourceHandler) {
            ((ListenerBasedNotificationSourceHandler) iNotificationSourceHandler).removeListener(this);
        }
    }

    private void attemptToastViaManager(PersistedNotification persistedNotification) {
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.mContext);
        Bundle bundle = new Bundle();
        bundle.putBoolean(NotificationConstants.KEY_AUI_PERSIST, true);
        bundle.putString(NotificationConstants.KEY_OCULUS_CATEGORY, "social");
        bundle.putBoolean("action_on_click", true);
        bundle.putString("oculus_notification_fbid", persistedNotification.getFBID());
        bundle.putString("oculus_notification_ndid", persistedNotification.getNDID());
        bundle.putString(AbstractVRNotification.NOTIF_XTYPE_FIELD, persistedNotification.getNotificationType());
        bundle.putString("oculus_notification_type", persistedNotification.getNotificationType());
        bundle.putString(NativeNotification.NOTIF_CATEGORY_FIELD, persistedNotification.getCategory().toString().toLowerCase(Locale.getDefault()));
        ThreadExecutor.getInstance().execute(new Callable(persistedNotification, builder, this.localNotificationIds.incrementAndGet(), PackageUtil.isShellApp(new ActivityManagerUtils().getForegroundApp(this.mContext)) ? 1 : 0, bundle, notificationManager) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$BwFmfNSw4_suHzhKlMPQwUGVKkg */
            private final /* synthetic */ PersistedNotification f$1;
            private final /* synthetic */ NotificationCompat.Builder f$2;
            private final /* synthetic */ int f$3;
            private final /* synthetic */ int f$4;
            private final /* synthetic */ Bundle f$5;
            private final /* synthetic */ NotificationManager f$6;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
                this.f$6 = r7;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return NotificationDatasetProxy.this.lambda$attemptToastViaManager$37$NotificationDatasetProxy(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
            }
        });
    }

    public /* synthetic */ Object lambda$attemptToastViaManager$37$NotificationDatasetProxy(PersistedNotification persistedNotification, NotificationCompat.Builder builder, int i, int i2, Bundle bundle, NotificationManager notificationManager) throws Exception {
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(this.mContext.getResources(), DownloadImageTask.loadBitmapFromUrl(persistedNotification.getIconUri()));
        create.setAntiAlias(true);
        create.setCircular(true);
        builder.setContentText(persistedNotification.getLongText()).setTicker(persistedNotification.getTitle()).setContentTitle(persistedNotification.getTitle()).setSmallIcon(R.drawable.ic_notifications_default).setLargeIcon(create.getBitmap()).setContentIntent(PendingIntent.getBroadcast(this.mContext, i, (Intent) persistedNotification.getPrimaryAction(), 0)).setPriority(i2).setAutoCancel(true).addExtras(bundle);
        Log.v(TAG, "Toasting a notif from a background fetch");
        notificationManager.notify(i, builder.build());
        return null;
    }

    private String getCurrentForegroundProcess() {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.mContext.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.importance == 100) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public synchronized void add(IBaseVRNotification iBaseVRNotification) {
        Log.v(TAG, String.format("Adding new notif %s to central list", iBaseVRNotification));
        if (!this.mNotifsByType.get(NotificationsType.ALL).contains(iBaseVRNotification)) {
            if (!isNotificationAlreadyAdded(iBaseVRNotification)) {
                this.mNotifsByType.compute(coerceType(iBaseVRNotification.getCategory()), new BiFunction() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$Qa2GiQC_bD44HGuj_x4CTtaMZw */

                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return NotificationDatasetProxy.lambda$add$39(IBaseVRNotification.this, (NotificationsType) obj, (List) obj2);
                    }
                });
                this.mNotifsByType.compute(NotificationsType.ALL, new BiFunction() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$op3UdNGNhMp7K_Ayyh0JHpX3vn8 */

                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return NotificationDatasetProxy.lambda$add$41(IBaseVRNotification.this, (NotificationsType) obj, (List) obj2);
                    }
                });
                this.mNotificationDataSetListeners.forEach(new BiConsumer() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$8c8T580xiQq1No_dnmqSonS_lBw */

                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        NotificationDatasetProxy.lambda$add$42(IBaseVRNotification.this, (String) obj, (Pair) obj2);
                    }
                });
            }
        }
    }

    static /* synthetic */ List lambda$add$39(IBaseVRNotification iBaseVRNotification, NotificationsType notificationsType, List list) {
        list.add(0, iBaseVRNotification);
        list.sort($$Lambda$NotificationDatasetProxy$8ACd8Lxv596gaSCivY73tkEdvpU.INSTANCE);
        return list;
    }

    static /* synthetic */ int lambda$null$38(IBaseVRNotification iBaseVRNotification, IBaseVRNotification iBaseVRNotification2) {
        return iBaseVRNotification2.getPostedTimeSeconds() > iBaseVRNotification.getPostedTimeSeconds() ? 1 : -1;
    }

    static /* synthetic */ List lambda$add$41(IBaseVRNotification iBaseVRNotification, NotificationsType notificationsType, List list) {
        list.add(0, iBaseVRNotification);
        list.sort($$Lambda$NotificationDatasetProxy$DBOZCzwT2GvO67IDhCfi1xJn5FA.INSTANCE);
        return list;
    }

    static /* synthetic */ int lambda$null$40(IBaseVRNotification iBaseVRNotification, IBaseVRNotification iBaseVRNotification2) {
        return iBaseVRNotification2.getPostedTimeSeconds() > iBaseVRNotification.getPostedTimeSeconds() ? 1 : -1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener */
    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void lambda$add$42(IBaseVRNotification iBaseVRNotification, String str, Pair pair) {
        if (!shouldFilterNotifType(iBaseVRNotification, (List) pair.second)) {
            ((INotificationProxyListener) pair.first).onNotificationAdded(iBaseVRNotification.getClass(), iBaseVRNotification);
        }
    }

    private void remove(IBaseVRNotification iBaseVRNotification) {
        Log.v(TAG, String.format("Removing notif %s from central list", iBaseVRNotification));
        this.mNotifsByType.compute(coerceType(iBaseVRNotification.getCategory()), new BiFunction() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$rzFrRWUnABq_51Cbl12ONlTrLXU */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                NotificationsType notificationsType = (NotificationsType) obj;
                return ((List) obj2).remove(IBaseVRNotification.this);
            }
        });
        this.mNotifsByType.compute(NotificationsType.ALL, new BiFunction() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$CBh8CbTtzgdJLJKOBH75h3jeXRs */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                NotificationsType notificationsType = (NotificationsType) obj;
                return ((List) obj2).remove(IBaseVRNotification.this);
            }
        });
        this.mNotificationDataSetListeners.forEach(new BiConsumer() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$74KIjDTsrhuQod5mVGtDWSheY */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                NotificationDatasetProxy.lambda$remove$45(IBaseVRNotification.this, (String) obj, (Pair) obj2);
            }
        });
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener */
    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void lambda$remove$45(IBaseVRNotification iBaseVRNotification, String str, Pair pair) {
        if (!shouldFilterNotifType(iBaseVRNotification, (List) pair.second)) {
            ((INotificationProxyListener) pair.first).onNotificationRemoved(iBaseVRNotification.getClass(), iBaseVRNotification);
        }
    }

    private synchronized void update(IBaseVRNotification iBaseVRNotification) {
        Log.v(TAG, String.format("Updating notif %s in central list", iBaseVRNotification));
        this.mNotifsByType.compute(NotificationsType.ALL, new BiFunction() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$XeDY7aUggvpZv7mMT2UN6j93oiY */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return NotificationDatasetProxy.lambda$update$46(IBaseVRNotification.this, (NotificationsType) obj, (List) obj2);
            }
        });
        this.mNotifsByType.compute(coerceType(iBaseVRNotification.getCategory()), new BiFunction() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$TMZ2nIpzeNtOQX56pAeKlY3_12M */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return NotificationDatasetProxy.lambda$update$47(IBaseVRNotification.this, (NotificationsType) obj, (List) obj2);
            }
        });
        this.mNotificationDataSetListeners.forEach(new BiConsumer() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$FXuxixHkGcqIEi7g5IS6wnXLgj8 */

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                NotificationDatasetProxy.lambda$update$48(IBaseVRNotification.this, (String) obj, (Pair) obj2);
            }
        });
    }

    static /* synthetic */ List lambda$update$46(IBaseVRNotification iBaseVRNotification, NotificationsType notificationsType, List list) {
        int indexOf = list.indexOf(iBaseVRNotification);
        if (indexOf >= 0) {
            list.set(indexOf, iBaseVRNotification);
        }
        return list;
    }

    static /* synthetic */ List lambda$update$47(IBaseVRNotification iBaseVRNotification, NotificationsType notificationsType, List list) {
        int indexOf = list.indexOf(iBaseVRNotification);
        if (indexOf >= 0) {
            list.set(indexOf, iBaseVRNotification);
        }
        return list;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener */
    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void lambda$update$48(IBaseVRNotification iBaseVRNotification, String str, Pair pair) {
        if (!shouldFilterNotifType(iBaseVRNotification, (List) pair.second)) {
            ((INotificationProxyListener) pair.first).onNotificationUpdated(iBaseVRNotification.getClass(), iBaseVRNotification);
        }
    }

    public List<IBaseVRNotification> getAllNotifications() {
        return new ArrayList(this.mNotifsByType.get(NotificationsType.ALL));
    }

    public List<IBaseVRNotification> getNotificationsByCategory(NotificationsType notificationsType) {
        return new ArrayList(this.mNotifsByType.get(notificationsType));
    }

    public IBaseVRNotification getVrNotificiationFromNotifKey(String str) {
        List list = (List) this.mNotifsByType.get(NotificationsType.ALL).stream().filter(new Predicate(str) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$SUJBAZkNI2lpuF_6tN2nPqA1Zx0 */
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return NotificationDatasetProxy.lambda$getVrNotificiationFromNotifKey$49(this.f$0, (IBaseVRNotification) obj);
            }
        }).collect(Collectors.toList());
        if (list.isEmpty()) {
            return null;
        }
        return (IBaseVRNotification) list.get(0);
    }

    static /* synthetic */ boolean lambda$getVrNotificiationFromNotifKey$49(String str, IBaseVRNotification iBaseVRNotification) {
        if (!(iBaseVRNotification instanceof NativeNotification)) {
            return false;
        }
        return ((StatusBarNotification) iBaseVRNotification.getRaw()).getKey().equals(str);
    }

    public void updateVrNotifFromStatusBarNotif(StatusBarNotification statusBarNotification, AbstractVRNotification.NotificationSeenState notificationSeenState) {
        for (IBaseVRNotification iBaseVRNotification : this.mNotifsByType.get(NotificationsType.ALL)) {
            if (((long) statusBarNotification.getId()) == iBaseVRNotification.getId()) {
                iBaseVRNotification.setSeenState(notificationSeenState);
                return;
            }
        }
    }

    public List<IBaseVRNotification> getNotificationsByType(String str) {
        return (List) this.mNotifsByType.get(NotificationsType.ALL).stream().filter(new Predicate(str) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.$$Lambda$NotificationDatasetProxy$YY1dA4DJEvD7uN9XNYVgO866fxA */
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.equals(((IBaseVRNotification) obj).getNotificationType());
            }
        }).filter($$Lambda$NotificationDatasetProxy$3_1d2ePv6mMlLgEd0TLFYYD6Ftc.INSTANCE).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$getNotificationsByType$51(IBaseVRNotification iBaseVRNotification) {
        return iBaseVRNotification.getSeenState() != AbstractVRNotification.NotificationSeenState.SEEN_BUT_UNREAD;
    }

    public boolean includesNotificationType(NotificationsType notificationsType) {
        return !this.mNotifsByType.get(notificationsType).isEmpty();
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener
    public void onNotificationAdded(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification) {
        if (!(iBaseVRNotification instanceof PersistedNotification)) {
            add(iBaseVRNotification);
        } else if (!notifExistsByFbid(iBaseVRNotification.getFBID()) && !this.mRemovedFBIDs.contains(iBaseVRNotification.getFBID())) {
            attemptToastViaManager((PersistedNotification) iBaseVRNotification);
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener
    public void onNotificationRemoved(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification) {
        this.mRemovedFBIDs.add(iBaseVRNotification.getFBID());
        remove(iBaseVRNotification);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener
    public void onNotificationUpdated(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification) {
        update(iBaseVRNotification);
    }

    public boolean isNotificationAlreadyAdded(IBaseVRNotification iBaseVRNotification) {
        if (TextUtils.isEmpty(iBaseVRNotification.getFBID()) || (!notifExistsByFbid(iBaseVRNotification.getFBID()) && !this.mRemovedFBIDs.contains(iBaseVRNotification.getFBID()))) {
            return false;
        }
        Log.v(TAG, String.format("Notif with id %s was already added to set. Skipping addition", iBaseVRNotification.getFBID()));
        return true;
    }

    public static boolean shouldFilterNotifType(IBaseVRNotification iBaseVRNotification, @Nullable List<String> list) {
        if (list != null) {
            return list.contains(iBaseVRNotification.getNotificationType());
        }
        return false;
    }

    public static boolean isInitialized() {
        return mIsInitialized;
    }

    public static boolean isInitializing() {
        return mIsInitializing;
    }

    public static NotificationDatasetProxy getInstance(Context context, @Nullable IOnNotificationsInitialized iOnNotificationsInitialized, @Nullable INotificationSourceHandler... iNotificationSourceHandlerArr) {
        return Loader.getInstance(context.getApplicationContext(), iOnNotificationsInitialized, iNotificationSourceHandlerArr);
    }

    private static NotificationsType coerceType(NotificationsType notificationsType) {
        if (notificationsType == NotificationsType.DOWNLOADS || notificationsType == NotificationsType.DOWNLOAD_IN_PROGRESS) {
            return NotificationsType.DOWNLOADS;
        }
        return notificationsType;
    }

    /* access modifiers changed from: private */
    public static class Loader {
        private static volatile NotificationDatasetProxy sInstance;

        private Loader() {
        }

        /* access modifiers changed from: private */
        public static final NotificationDatasetProxy getInstance(Context context, @Nullable IOnNotificationsInitialized iOnNotificationsInitialized, @Nullable INotificationSourceHandler... iNotificationSourceHandlerArr) {
            if (sInstance == null) {
                sInstance = new NotificationDatasetProxy(context.getApplicationContext(), iOnNotificationsInitialized, iNotificationSourceHandlerArr);
            }
            return sInstance;
        }
    }
}
