package com.facebook.analytics2.logger;

import android.content.Context;
import android.os.HandlerThread;

public class DefaultHandlerThreadFactory implements HandlerThreadFactory {
    @Override // com.facebook.analytics2.logger.HandlerThreadFactory
    public final boolean A4b() {
        return true;
    }

    @Override // com.facebook.analytics2.logger.HandlerThreadFactory
    public final HandlerThread A1W(String str, int i) {
        HandlerThread handlerThread = new HandlerThread(str, i);
        handlerThread.start();
        return handlerThread;
    }

    public DefaultHandlerThreadFactory(Context context) {
    }
}
