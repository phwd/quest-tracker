package com.facebook.secure.receiver;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import java.util.Collection;

public class DynamicSecureBroadcastReceiver extends SecureBroadcastReceiver {
    private final SimpleArrayMap<String, ActionReceiver> mActionMap = new SimpleArrayMap<>(1);
    private Collection<String> mActionRemoved;

    public DynamicSecureBroadcastReceiver(String str, ActionReceiver actionReceiver) {
        SimpleArrayMap<String, ActionReceiver> simpleArrayMap = this.mActionMap;
        SecureBroadcastReceiver.checkNotNull(str);
        SecureBroadcastReceiver.checkNotNull(actionReceiver);
        simpleArrayMap.put(str, actionReceiver);
    }

    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public final synchronized boolean isActionRemoved(String str) {
        return this.mActionRemoved != null && this.mActionRemoved.contains(str);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.receiver.SecureBroadcastReceiver
    public final synchronized ActionReceiver findReceiverForIntent(Context context, String str) {
        ActionReceiver actionReceiver;
        actionReceiver = null;
        if (str != null) {
            actionReceiver = this.mActionMap.get(str);
        }
        return actionReceiver;
    }
}
