package com.facebook.secure.receiver;

import android.content.Context;
import android.content.IntentFilter;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class DynamicSecureBroadcastReceiver extends SecureBroadcastReceiver {
    private final SimpleArrayMap<String, ActionReceiver> mActionMap;
    private Collection<String> mActionRemoved;
    private IntentFilter mCachedIntentFilter;

    /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicSecureBroadcastReceiver(Iterator<? extends Map.Entry<String, ? extends ActionReceiver>> actionMapEntries) {
        checkNotNull(actionMapEntries);
        this.mActionMap = new SimpleArrayMap<>();
        while (actionMapEntries.hasNext()) {
            Map.Entry<String, ? extends ActionReceiver> entry = (Map.Entry) actionMapEntries.next();
            if (this.mActionMap.put(entry.getKey(), entry.getValue()) != null) {
                throw new IllegalArgumentException(String.format("action '%s' found more than once in action map", entry.getKey()));
            }
        }
        if (this.mActionMap.isEmpty()) {
            throw new IllegalArgumentException("Must include an entry for at least one action");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicSecureBroadcastReceiver(String action, ActionReceiver receiver) {
        this.mActionMap = new SimpleArrayMap<>(1);
        this.mActionMap.put(checkNotNull(action), checkNotNull(receiver));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicSecureBroadcastReceiver(String action0, ActionReceiver receiver0, String action1, ActionReceiver receiver1) {
        this.mActionMap = new SimpleArrayMap<>(2);
        this.mActionMap.put(checkNotNull(action0), checkNotNull(receiver0));
        this.mActionMap.put(checkNotNull(action1), checkNotNull(receiver1));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicSecureBroadcastReceiver(String action0, ActionReceiver receiver0, String action1, ActionReceiver receiver1, String action2, ActionReceiver receiver2) {
        this.mActionMap = new SimpleArrayMap<>(3);
        this.mActionMap.put(checkNotNull(action0), checkNotNull(receiver0));
        this.mActionMap.put(checkNotNull(action1), checkNotNull(receiver1));
        this.mActionMap.put(checkNotNull(action2), checkNotNull(receiver2));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicSecureBroadcastReceiver(String action0, ActionReceiver receiver0, String action1, ActionReceiver receiver1, String action2, ActionReceiver receiver2, String action3, ActionReceiver receiver3) {
        this.mActionMap = new SimpleArrayMap<>(4);
        this.mActionMap.put(checkNotNull(action0), checkNotNull(receiver0));
        this.mActionMap.put(checkNotNull(action1), checkNotNull(receiver1));
        this.mActionMap.put(checkNotNull(action2), checkNotNull(receiver2));
        this.mActionMap.put(checkNotNull(action3), checkNotNull(receiver3));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicSecureBroadcastReceiver(String action0, ActionReceiver receiver0, String action1, ActionReceiver receiver1, String action2, ActionReceiver receiver2, String action3, ActionReceiver receiver3, String action4, ActionReceiver receiver4) {
        this.mActionMap = new SimpleArrayMap<>(5);
        this.mActionMap.put(checkNotNull(action0), checkNotNull(receiver0));
        this.mActionMap.put(checkNotNull(action1), checkNotNull(receiver1));
        this.mActionMap.put(checkNotNull(action2), checkNotNull(receiver2));
        this.mActionMap.put(checkNotNull(action3), checkNotNull(receiver3));
        this.mActionMap.put(checkNotNull(action4), checkNotNull(receiver4));
    }

    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public final synchronized IntentFilter getIntentFilter() {
        if (this.mCachedIntentFilter == null) {
            this.mCachedIntentFilter = new IntentFilter();
            int size = this.mActionMap.size();
            for (int i = 0; i < size; i++) {
                this.mCachedIntentFilter.addAction(this.mActionMap.keyAt(i));
            }
        }
        return this.mCachedIntentFilter;
    }

    public final synchronized boolean removeActionAndCheckEmpty(String action) {
        this.mActionMap.remove(action);
        if (this.mActionRemoved == null) {
            this.mActionRemoved = new ArrayList();
        }
        if (!this.mActionRemoved.contains(action)) {
            this.mActionRemoved.add(action);
        }
        this.mCachedIntentFilter = null;
        return this.mActionMap.isEmpty();
    }

    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public final synchronized boolean isActionRemoved(String action) {
        return this.mActionRemoved != null && this.mActionRemoved.contains(action);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public final synchronized ActionReceiver findReceiverForIntent(Context context, String action) {
        return this.mActionMap.get(action);
    }
}
