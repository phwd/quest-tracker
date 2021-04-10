package com.facebook.common.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.debug.log.BLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

public class FbLocalBroadcastManager {
    private static final boolean DEBUG = false;
    @VisibleForTesting
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "FbLocalBroadcastManager";
    private static FbLocalBroadcastManager sInstance;
    private static final Object sLock = new Object();
    private final Map<String, List<ReceiverRecord>> mActions = Maps.newHashMap();
    private final Context mAppContext;
    private final Map<Long, LocalBroadcastHandler> mHandlers = Maps.newHashMap();
    private final LocalBroadcastManager mLocalBroadcastManager;
    private final Multimap<Long, BroadcastRecord> mPendingBroadcasts = ArrayListMultimap.create();
    private final Map<BroadcastReceiver, List<IntentFilter>> mReceivers = Maps.newHashMap();

    /* access modifiers changed from: private */
    public static class ReceiverRecord {
        boolean broadcasting;
        final IntentFilter filter;
        final LocalBroadcastHandler handler;
        final long looperId;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver, long j, LocalBroadcastHandler localBroadcastHandler) {
            this.filter = intentFilter;
            this.receiver = broadcastReceiver;
            this.looperId = j;
            this.handler = (LocalBroadcastHandler) Preconditions.checkNotNull(localBroadcastHandler);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.receiver);
            sb.append(" filter=");
            sb.append(this.filter);
            sb.append(" looperId=");
            sb.append(this.looperId);
            sb.append("}");
            return sb.toString();
        }
    }

    /* access modifiers changed from: private */
    public static class BroadcastRecord {
        final Intent intent;
        final Collection<ReceiverRecord> receivers;

        BroadcastRecord(Intent intent2, Collection<ReceiverRecord> collection) {
            this.intent = intent2;
            this.receivers = collection;
        }
    }

    /* access modifiers changed from: private */
    public class LocalBroadcastHandler extends Handler {
        public final long looperId;
        private final AtomicInteger mRefCount = new AtomicInteger(0);

        public LocalBroadcastHandler(Looper looper, long j) {
            super(looper);
            this.looperId = j;
        }

        public void handleMessage(Message message) {
            if (message.what != 1) {
                super.handleMessage(message);
            } else {
                FbLocalBroadcastManager.this.executePendingBroadcastsForThread(this.looperId);
            }
        }

        public void incrementRefCount() {
            this.mRefCount.getAndIncrement();
        }

        public boolean decrementRefCount() {
            return this.mRefCount.decrementAndGet() <= 0;
        }
    }

    public static FbLocalBroadcastManager getInstance(Context context) {
        FbLocalBroadcastManager fbLocalBroadcastManager;
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new FbLocalBroadcastManager(context.getApplicationContext());
            }
            fbLocalBroadcastManager = sInstance;
        }
        return fbLocalBroadcastManager;
    }

    @VisibleForTesting
    FbLocalBroadcastManager(Context context) {
        this.mAppContext = context;
        this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    public void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        registerReceiver(broadcastReceiver, intentFilter, (Looper) null);
    }

    public void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable Handler handler) {
        registerReceiver(broadcastReceiver, intentFilter, handler != null ? handler.getLooper() : null);
    }

    public void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable Looper looper) {
        if (looper == null) {
            this.mLocalBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);
            return;
        }
        synchronized (this.mReceivers) {
            long idFor = getIdFor(looper);
            LocalBroadcastHandler localBroadcastHandler = this.mHandlers.get(Long.valueOf(idFor));
            if (localBroadcastHandler == null) {
                localBroadcastHandler = new LocalBroadcastHandler(looper, idFor);
                this.mHandlers.put(Long.valueOf(idFor), localBroadcastHandler);
            }
            localBroadcastHandler.incrementRefCount();
            ReceiverRecord receiverRecord = new ReceiverRecord(intentFilter, broadcastReceiver, idFor, localBroadcastHandler);
            List<IntentFilter> list = this.mReceivers.get(broadcastReceiver);
            if (list == null) {
                list = Lists.newArrayListWithExpectedSize(1);
                this.mReceivers.put(broadcastReceiver, list);
            }
            list.add(intentFilter);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                List<ReceiverRecord> list2 = this.mActions.get(action);
                if (list2 == null) {
                    list2 = Lists.newArrayListWithExpectedSize(1);
                    this.mActions.put(action, list2);
                }
                list2.add(receiverRecord);
            }
        }
    }

    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        synchronized (this.mReceivers) {
            this.mLocalBroadcastManager.unregisterReceiver(broadcastReceiver);
            List<IntentFilter> remove = this.mReceivers.remove(broadcastReceiver);
            if (remove != null) {
                for (int i = 0; i < remove.size(); i++) {
                    IntentFilter intentFilter = remove.get(i);
                    for (int i2 = 0; i2 < intentFilter.countActions(); i2++) {
                        String action = intentFilter.getAction(i2);
                        List<ReceiverRecord> list = this.mActions.get(action);
                        if (list != null) {
                            int i3 = 0;
                            while (i3 < list.size()) {
                                if (list.get(i3).receiver == broadcastReceiver) {
                                    ReceiverRecord remove2 = list.remove(i3);
                                    if (remove2.handler.decrementRefCount()) {
                                        this.mHandlers.remove(Long.valueOf(remove2.handler.looperId));
                                    }
                                    i3--;
                                }
                                i3++;
                            }
                            if (list.isEmpty()) {
                                this.mActions.remove(action);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean sendBroadcast(Intent intent) {
        boolean sendScheduledBroadcast;
        synchronized (this.mReceivers) {
            sendScheduledBroadcast = sendScheduledBroadcast(intent) | this.mLocalBroadcastManager.sendBroadcast(intent);
        }
        return sendScheduledBroadcast;
    }

    private boolean sendScheduledBroadcast(Intent intent) {
        synchronized (this.mReceivers) {
            Set<Long> entriesThreadId = getEntriesThreadId(intent, this.mPendingBroadcasts);
            if (entriesThreadId == null) {
                return false;
            }
            for (Long l : entriesThreadId) {
                LocalBroadcastHandler localBroadcastHandler = this.mHandlers.get(l);
                if (localBroadcastHandler != null) {
                    if (!localBroadcastHandler.hasMessages(1)) {
                        localBroadcastHandler.sendEmptyMessage(1);
                    }
                }
            }
            return true;
        }
    }

    @Nullable
    private Set<Long> getEntriesThreadId(Intent intent, Multimap<Long, BroadcastRecord> multimap) {
        ArrayListMultimap arrayListMultimap;
        boolean z;
        List<ReceiverRecord> list;
        int i;
        synchronized (this.mReceivers) {
            String action = intent.getAction();
            String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z2 = (intent.getFlags() & 8) != 0;
            if (z2) {
                BLog.v(TAG, "Resolving type %s scheme %s of intent %s", resolveTypeIfNeeded, scheme, intent);
            }
            List<ReceiverRecord> list2 = this.mActions.get(intent.getAction());
            if (list2 != null) {
                if (z2) {
                    BLog.v(TAG, "Action list: %s", list2);
                }
                ArrayListMultimap arrayListMultimap2 = null;
                int i2 = 0;
                while (i2 < list2.size()) {
                    ReceiverRecord receiverRecord = list2.get(i2);
                    if (z2) {
                        BLog.v(TAG, "Matching against filter %s", receiverRecord.filter);
                    }
                    if (receiverRecord.broadcasting) {
                        if (z2) {
                            BLog.v(TAG, "  Filter's target already added");
                        }
                        i = i2;
                        list = list2;
                    } else {
                        i = i2;
                        list = list2;
                        int match = receiverRecord.filter.match(action, resolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                        if (match >= 0) {
                            if (z2) {
                                BLog.v(TAG, "  Filter matched!  match=%d", Integer.valueOf(match));
                            }
                            ArrayListMultimap create = arrayListMultimap2 == null ? ArrayListMultimap.create() : arrayListMultimap2;
                            create.put(Long.valueOf(receiverRecord.looperId), receiverRecord);
                            receiverRecord.broadcasting = true;
                            arrayListMultimap2 = create;
                        } else if (z2) {
                            BLog.v(TAG, "  Filter did not match: %s", match != -4 ? match != -3 ? match != -2 ? match != -1 ? "unknown reason" : "type" : "data" : "action" : "category");
                        }
                    }
                    i2 = i + 1;
                    list2 = list;
                }
                z = false;
                arrayListMultimap = arrayListMultimap2;
            } else {
                z = false;
                arrayListMultimap = null;
            }
            if (arrayListMultimap == null) {
                return null;
            }
            for (ReceiverRecord receiverRecord2 : arrayListMultimap.values()) {
                receiverRecord2.broadcasting = z;
            }
            for (Long l : arrayListMultimap.keySet()) {
                multimap.put(l, new BroadcastRecord(intent, arrayListMultimap.get((Object) l)));
            }
            return Sets.newHashSet(arrayListMultimap.keySet());
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public Set<Long> getRegisteredNonMainHandlerThreads() {
        return Collections.unmodifiableSet(this.mHandlers.keySet());
    }

    private static long getIdFor(Looper looper) {
        return looper.getThread().getId();
    }

    public void sendBroadcastSync(Intent intent) {
        this.mLocalBroadcastManager.sendBroadcastSync(intent);
        ArrayListMultimap create = ArrayListMultimap.create();
        if (getEntriesThreadId(intent, create) != null) {
            executePendingBroadcasts(create);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void executePendingBroadcastsForThread(long j) {
        BroadcastRecord[] broadcastRecordArr;
        while (true) {
            synchronized (this.mReceivers) {
                if (this.mPendingBroadcasts.size() > 0) {
                    Collection<BroadcastRecord> collection = this.mPendingBroadcasts.get(Long.valueOf(j));
                    if (collection != null) {
                        int size = collection.size();
                        if (size > 0) {
                            broadcastRecordArr = new BroadcastRecord[size];
                            collection.toArray(broadcastRecordArr);
                            this.mPendingBroadcasts.removeAll(Long.valueOf(j));
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            callBroadcastsRecords(broadcastRecordArr);
        }
    }

    private void executePendingBroadcasts(Multimap<Long, BroadcastRecord> multimap) {
        BroadcastRecord[] broadcastRecordArr;
        while (true) {
            synchronized (this.mReceivers) {
                if (multimap.size() > 0) {
                    Collection<BroadcastRecord> values = multimap.values();
                    if (values != null) {
                        int size = values.size();
                        if (size > 0) {
                            broadcastRecordArr = new BroadcastRecord[size];
                            values.toArray(broadcastRecordArr);
                            multimap.clear();
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            callBroadcastsRecords(broadcastRecordArr);
        }
    }

    private void callBroadcastsRecords(BroadcastRecord[] broadcastRecordArr) {
        for (BroadcastRecord broadcastRecord : broadcastRecordArr) {
            for (ReceiverRecord receiverRecord : broadcastRecord.receivers) {
                receiverRecord.receiver.onReceive(this.mAppContext, broadcastRecord.intent);
            }
        }
    }
}
