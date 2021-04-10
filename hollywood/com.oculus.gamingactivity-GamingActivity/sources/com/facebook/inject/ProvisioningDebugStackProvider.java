package com.facebook.inject;

import com.facebook.debug.log.BLog;
import com.facebook.debug.tracer.Tracer;
import com.facebook.inject.ProvisioningDebugStack;
import com.google.inject.Key;
import javax.inject.Provider;

public class ProvisioningDebugStackProvider<T> implements Provider<T> {
    private final Provider<T> mDelegate;
    private final Key<T> mKey;

    public ProvisioningDebugStackProvider(Key<T> key, Provider<T> delegate) {
        this.mKey = key;
        this.mDelegate = delegate;
    }

    @Override // javax.inject.Provider
    public T get() {
        ProvisioningDebugStack.push(ProvisioningDebugStack.StackType.INSTANCE_GET, this.mKey);
        try {
            boolean shouldTrace = BLog.isLoggable(2);
            if (shouldTrace) {
                Tracer.startTracer("Provider.get %s", this.mKey);
            }
            try {
                return this.mDelegate.get();
            } finally {
                if (shouldTrace) {
                    Tracer.stopTracer(10);
                }
            }
        } finally {
            ProvisioningDebugStack.pop();
        }
    }
}
