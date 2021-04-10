package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@Beta
@GwtCompatible
public abstract class Ticker {
    public static final Ticker SYSTEM_TICKER = new Ticker() {
        /* class com.google.common.base.Ticker.AnonymousClass1 */

        @Override // com.google.common.base.Ticker
        public long read() {
            return System.nanoTime();
        }
    };

    @CanIgnoreReturnValue
    public abstract long read();
}
