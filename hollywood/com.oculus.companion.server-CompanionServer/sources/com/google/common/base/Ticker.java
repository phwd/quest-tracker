package com.google.common.base;

public abstract class Ticker {
    private static final Ticker SYSTEM_TICKER = new Ticker() {
        /* class com.google.common.base.Ticker.AnonymousClass1 */

        @Override // com.google.common.base.Ticker
        public long read() {
            return Platform.systemNanoTime();
        }
    };

    public abstract long read();

    protected Ticker() {
    }

    public static Ticker systemTicker() {
        return SYSTEM_TICKER;
    }
}