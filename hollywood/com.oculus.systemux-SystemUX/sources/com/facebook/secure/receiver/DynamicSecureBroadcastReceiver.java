package com.facebook.secure.receiver;

import android.content.Context;
import android.content.IntentFilter;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

public class DynamicSecureBroadcastReceiver extends SecureBroadcastReceiver {
    private final SimpleArrayMap<String, ActionReceiver> mActionMap;
    @Nullable
    private Collection<String> mActionRemoved;
    @Nullable
    private IntentFilter mCachedIntentFilter;

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicSecureBroadcastReceiver(Iterator<? extends Map.Entry<String, ? extends ActionReceiver>> it) {
        checkNotNull(it);
        this.mActionMap = new SimpleArrayMap<>();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
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
    public DynamicSecureBroadcastReceiver(String str, ActionReceiver actionReceiver) {
        this.mActionMap = new SimpleArrayMap<>(1);
        this.mActionMap.put(checkNotNull(str), checkNotNull(actionReceiver));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicSecureBroadcastReceiver(String str, ActionReceiver actionReceiver, String str2, ActionReceiver actionReceiver2) {
        this.mActionMap = new SimpleArrayMap<>(2);
        this.mActionMap.put(checkNotNull(str), checkNotNull(actionReceiver));
        this.mActionMap.put(checkNotNull(str2), checkNotNull(actionReceiver2));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v3, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicSecureBroadcastReceiver(String str, ActionReceiver actionReceiver, String str2, ActionReceiver actionReceiver2, String str3, ActionReceiver actionReceiver3) {
        this.mActionMap = new SimpleArrayMap<>(3);
        this.mActionMap.put(checkNotNull(str), checkNotNull(actionReceiver));
        this.mActionMap.put(checkNotNull(str2), checkNotNull(actionReceiver2));
        this.mActionMap.put(checkNotNull(str3), checkNotNull(actionReceiver3));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v3, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v4, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicSecureBroadcastReceiver(String str, ActionReceiver actionReceiver, String str2, ActionReceiver actionReceiver2, String str3, ActionReceiver actionReceiver3, String str4, ActionReceiver actionReceiver4) {
        this.mActionMap = new SimpleArrayMap<>(4);
        this.mActionMap.put(checkNotNull(str), checkNotNull(actionReceiver));
        this.mActionMap.put(checkNotNull(str2), checkNotNull(actionReceiver2));
        this.mActionMap.put(checkNotNull(str3), checkNotNull(actionReceiver3));
        this.mActionMap.put(checkNotNull(str4), checkNotNull(actionReceiver4));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v3, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v4, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX DEBUG: Multi-variable search result rejected for r3v5, resolved type: androidx.collection.SimpleArrayMap<java.lang.String, com.facebook.secure.receiver.ActionReceiver> */
    /* JADX WARN: Multi-variable type inference failed */
    public DynamicSecureBroadcastReceiver(String str, ActionReceiver actionReceiver, String str2, ActionReceiver actionReceiver2, String str3, ActionReceiver actionReceiver3, String str4, ActionReceiver actionReceiver4, String str5, ActionReceiver actionReceiver5) {
        this.mActionMap = new SimpleArrayMap<>(5);
        this.mActionMap.put(checkNotNull(str), checkNotNull(actionReceiver));
        this.mActionMap.put(checkNotNull(str2), checkNotNull(actionReceiver2));
        this.mActionMap.put(checkNotNull(str3), checkNotNull(actionReceiver3));
        this.mActionMap.put(checkNotNull(str4), checkNotNull(actionReceiver4));
        this.mActionMap.put(checkNotNull(str5), checkNotNull(actionReceiver5));
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

    public final synchronized boolean removeActionAndCheckEmpty(String str) {
        this.mActionMap.remove(str);
        if (this.mActionRemoved == null) {
            this.mActionRemoved = new ArrayList();
        }
        if (!this.mActionRemoved.contains(str)) {
            this.mActionRemoved.add(str);
        }
        this.mCachedIntentFilter = null;
        return this.mActionMap.isEmpty();
    }

    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public final synchronized boolean isActionRemoved(String str) {
        return this.mActionRemoved != null && this.mActionRemoved.contains(str);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    @Nullable
    public final synchronized ActionReceiver findReceiverForIntent(Context context, String str) {
        return this.mActionMap.get(str);
    }
}
