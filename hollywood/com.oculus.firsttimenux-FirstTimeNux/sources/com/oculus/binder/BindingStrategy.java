package com.oculus.binder;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public interface BindingStrategy {
    public static final BindingStrategy DEFAULT = new BindingStrategy() {
        /* class com.oculus.binder.BindingStrategy.AnonymousClass1 */

        @Override // com.oculus.binder.BindingStrategy
        public boolean bindService(Context context, Intent intent, ServiceConnection connection, int flags) {
            return context.bindService(intent, connection, flags);
        }
    };

    boolean bindService(Context context, Intent intent, ServiceConnection serviceConnection, int i);
}
